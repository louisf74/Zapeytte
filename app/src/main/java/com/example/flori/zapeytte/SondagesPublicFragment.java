package com.example.flori.zapeytte;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.flori.zapeytte.model.Sondage;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class SondagesPublicFragment extends Fragment {

    private String API_LOGIN = "https://peaceful-eyrie-28628.herokuapp.com/questionnaires?ispublic=true";

    private ListView publicSondagesListView;
    private ArrayAdapter<Sondage> sondageArrayAdapter;
    private ArrayList<Sondage> sondageList;

    public SondagesPublicFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sondages_public, container, false);
        publicSondagesListView = (ListView) view.findViewById(R.id.publicSondagesListView);
        sondageList = new ArrayList<>();
        sondageArrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, sondageList);
        publicSondagesListView.setAdapter(sondageArrayAdapter);
        new PublicSondagesAsyncTask().execute(API_LOGIN);
        //sondageArrayAdapter.add(new Sondage(0, "a", 0, "a", true, new Date()));
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class PublicSondagesAsyncTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            try {
                HttpRequest request = HttpRequest.get(params[0]);
                String result = null;
                if (request.ok()) {
                    result = request.body();
                }
                return result;
            } catch (HttpRequest.HttpRequestException exception) {
                return null;
            }
        }

        protected void onPostExecute(String result) {
            if (result != null) {
                JsonElement jsonResult = new JsonParser().parse(result);
                Gson gson = new Gson();
                sondageList = gson.fromJson(jsonResult, sondageList.getClass());
                sondageArrayAdapter.addAll(sondageList);
            } else {
            }
        }

        protected void onPreExecute() {
        }

        protected void onProgressUpdate(Void... values) {
        }
    }
}
