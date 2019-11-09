package com.example.prueba1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {
    String pais, urlpais;

    public Paises(String ppais,String purl) throws JSONException {
        pais = ppais;
        urlpais ="http://www.geognos.com/api/en/countries/flag/"+purl+".png";
    }


    public static ArrayList<Paises> JsonObjectsBuild(JSONObject datos) throws JSONException {
        ArrayList<Paises> pais = new ArrayList<>();

        JSONObject results=datos.getJSONObject("Results");
        JSONArray datosdb=results.names();

        for (int i = 0; i < datosdb.length(); i++) {

            String bd= datosdb.getString(i);
            JSONObject datosBD=results.getJSONObject(bd);
            String nombre=datosBD.getString("Name");

            JSONObject country =datosBD.getJSONObject("CountryCodes");
            String ruta=country.getString("iso2");

            pais.add(new Paises(nombre,ruta));
        }

        return pais;
    }
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUrlpais() {
        return urlpais;
    }

    public void setUrlpais(String urlpais) {
        this.urlpais = urlpais;
    }
}
