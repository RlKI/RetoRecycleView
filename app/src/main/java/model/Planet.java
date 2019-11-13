package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Planet {
    public String name;
    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String surface_water;
    public String population;

    public Planet(JSONObject jPlanet) {
        try {
            name = jPlanet.getString("name");
            rotation_period = jPlanet.getString("rotation_period");
            orbital_period = jPlanet.getString("orbital_period");
            diameter = jPlanet.getString("diameter");
            climate = jPlanet.getString("climate");
            gravity = jPlanet.getString("gravity");
            terrain = jPlanet.getString("terrain");
            surface_water = jPlanet.getString("surface_water");
            population = jPlanet.getString("population");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Planet(String name) {
        this.name = name;
    }

}
