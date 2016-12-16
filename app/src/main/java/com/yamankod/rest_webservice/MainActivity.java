package com.yamankod.rest_webservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;

/**
 * Murat yaman
 */
public class MainActivity extends AppCompatActivity {

    Button btnrest;
    TextView tvSonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnrest =(Button)findViewById(R.id.btnRest);
        tvSonuc =(TextView)findViewById(R.id.tv1);

        btnrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsycTask myA = new myAsycTask();
                myA.execute();

            }
        });
    }


    class  myAsycTask extends AsyncTask<String ,Void,String >{
        @Override
        protected String doInBackground(String... strings) {
            return getIdContent();
        }
        @Override
        protected void onPostExecute(String s) {
             tvSonuc.setText(s);
            super.onPostExecute(s);
        }
    }

    private  String getIdContent(){

        String line ="";
        String data ="";

        try {
               URL url = new URL("http://rest-service.guides.spring.io/greeting");
               HttpURLConnection con  = (HttpURLConnection)url.openConnection();

               con.setRequestMethod("GET");
               con.setInstanceFollowRedirects(false);

               if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
                   InputStream response = con.getInputStream();
                   InputStreamReader reader = new InputStreamReader(response);
                   BufferedReader br =new BufferedReader(reader);
                   while ((line=br.readLine()) !=null){
                       data +=line;
                   }
               }

           }catch (Exception e){}

        return  data ;
    }
}
