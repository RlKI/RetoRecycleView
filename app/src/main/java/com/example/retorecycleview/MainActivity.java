package com.example.retorecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import model.Planet;

public class MainActivity extends AppCompatActivity {

    private RecyclerView bandsRecyclerView;
    private ArrayList<Planet> listPlanets = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter planetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bandsRecyclerView = findViewById(R.id.planetsRecyclerView);

        fillListOfPlanets();

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute("");
    }

    public void loadInfo(){
        layoutManager = new LinearLayoutManager(this);
        bandsRecyclerView.setLayoutManager(layoutManager);


        planetAdapter = new PlanetAdapter(listPlanets);
        bandsRecyclerView.setAdapter(planetAdapter);
    }

    public void fillListOfPlanets() {
        Planet planet = new Planet("Mercury");
        listPlanets.add(planet);
        planet = new Planet("Venus");
        listPlanets.add(planet);
        planet = new Planet("Earth");
        listPlanets.add(planet);
        planet = new Planet("Then Mars");
        listPlanets.add(planet);
        loadInfo();
    }

    public void getPlanets() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlService = new URL("https://swapi.co/api/planets");
                    HttpURLConnection connection = (HttpURLConnection) urlService.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream responseBody = connection.getInputStream();

                    if (connection.getResponseCode() == 200) {
                        // Success
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        BufferedReader streamReader = new BufferedReader(responseBodyReader);
                        StringBuilder responseStrBuilder = new StringBuilder();

                        String inputStr;
                        while ((inputStr = streamReader.readLine()) != null)
                            responseStrBuilder.append(inputStr);

                        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                        JSONArray jListPlanets = jsonObject.getJSONArray("results");


                        for (int _i = 0; _i < jListPlanets.length(); _i++) {
                            Planet planet = new Planet(jListPlanets.getJSONObject(_i));
                            listPlanets.add(planet);
                        }
                        loadInfo();
                    }

                } catch (Exception e) {

                    loadInfo();
                }
            }
        });
    }


    public class AsyncTaskRunner extends AsyncTask<String, String, ArrayList<Planet>> {

        private String resp;
        private ArrayList<Planet> respListPlanets = new ArrayList<>();

        @Override
        protected ArrayList<Planet> doInBackground(String... params) {
            try {
                URL urlService = new URL("https://swapi.co/api/planets");
                HttpURLConnection connection = (HttpURLConnection) urlService.openConnection();
                connection.setRequestMethod("GET");
                InputStream responseBody = connection.getInputStream();

                if (connection.getResponseCode() == 200) {
                    // Success
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                    BufferedReader streamReader = new BufferedReader(responseBodyReader);
                    StringBuilder responseStrBuilder = new StringBuilder();

                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);

                    JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                    JSONArray jListPlanets = jsonObject.getJSONArray("results");


                    for (int _i = 0; _i < jListPlanets.length(); _i++) {
                        Planet planet = new Planet(jListPlanets.getJSONObject(_i));
                        respListPlanets.add(planet);
                    }
                }

            } catch (Exception e) {

                loadInfo();
            }
            return respListPlanets;
        }

        @Override
        protected void onPostExecute(ArrayList<Planet> result) {
            listPlanets = result;
            loadInfo();
        }
    }
}
