package com.example.flori.zapeytte;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.flori.zapeytte.model.Sondage;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class SondagesPublicFragment extends Fragment {

    private String API_LOGIN = "https://peaceful-eyrie-28628.herokuapp.com/questionnaires?ispublic=true";

    private ListView publicSondagesListView;
    private ArrayAdapter<Sondage> sondageArrayAdapter;
    private ArrayList<Sondage> sondageList;
    private ProgressBar progressBarSondagesPublic;

    public SondagesPublicFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sondages_public, container, false);
        progressBarSondagesPublic = (ProgressBar) view.findViewById(R.id.progressBarSondagesPublic);
        publicSondagesListView = (ListView) view.findViewById(R.id.publicSondagesListView);
        sondageList = new ArrayList<>();
        sondageArrayAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, sondageList);
        publicSondagesListView.setAdapter(sondageArrayAdapter);
        new PublicSondagesAsyncTask().execute(API_LOGIN);
        publicSondagesListView.setOnItemClickListener(sondagesSelectedListener);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private AdapterView.OnItemClickListener sondagesSelectedListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
            Toast.makeText(getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private class PublicSondagesAsyncTask extends AsyncTask<String, Integer, String> {

        protected String doInBackground(String... params) {
            try {
                publishProgress(50);
                HttpRequest request = HttpRequest.get(params[0]);
                String result = null;
                if (request.ok()) {
                    result = request.body();
                }
                publishProgress(100);
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
            // prepare for a progress bar dialog
            progressBarSondagesPublic.setProgress(0);
            progressBarSondagesPublic.setMax(100);
        }

        protected void onProgressUpdate(Integer... values) {
            progressBarSondagesPublic.setProgress(values[0]);
        }
    }
}
