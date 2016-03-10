package com.example.flori.zapeytte;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

/**
 * A login screen that offers login via pseudo/password.
 */
public class LoginZapeytte extends Activity {

    private String API_LOGIN = "http://";

    private TextView loginInput;
    private TextView pswInput;
    private Button loginButton;
    private Button anonymousLoginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_zapeytte);

        loginInput = (TextView) findViewById(R.id.pseudo);
        pswInput = (TextView) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.sign_in_button);
        anonymousLoginButton = (Button) findViewById(R.id.anonymous_sign_in_button);

        loginButton.setOnClickListener(loginAction);
        anonymousLoginButton.setOnClickListener(loginAnonymousAction);
    }

    private View.OnClickListener loginAction = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };

    private View.OnClickListener loginAnonymousAction = new View.OnClickListener() {
        public void onClick(View v) {
            Intent sondagesZapeytteIntent = new Intent(LoginZapeytte.this, SondagesZapeytte.class);
            startActivity(sondagesZapeytteIntent);
        }
    };

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

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
                //JsonElement jsonResult = new JsonParser().parse(result);
                Gson gson = new Gson();
            } else {
            }
        }

        protected void onPreExecute() {
        }

        protected void onProgressUpdate(Void... values) {
        }
    }
}