package com.example.tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;


public class MainActivity extends AppCompatActivity implements Asynchtask {
    private TextView txtInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInformacion = (TextView)findViewById(R.id.txtDatos);
        txtInformacion.setMovementMethod(new ScrollingMovementMethod());
        txtInformacion.setVisibility(View.INVISIBLE);
        Bundle bundle = this.getIntent().getExtras();
        //invocacion del webservice
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://api.androidhive.info/contacts/", datos, MainActivity.this, MainActivity.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        Log.i("processFinish",result);
        //Leer el JSON
        JSONObject jsonObj = new JSONObject(result);
        ArrayList<HashMap<String, String>> contactList = new ArrayList<>();
        JSONArray contacts = jsonObj.getJSONArray("contacts");
        for (int i=0; i<contacts.length();i++){
            JSONObject obj= contacts.getJSONObject(i);
            String id =obj.getString("id");
            String name=obj.getString("name");
            String email=obj.getString("email");
            String address=obj.getString("address");
            String gender=obj.getString("gender");

            JSONObject phone= obj.getJSONObject("phone");
            String mobile= phone.getString("mobile");
            String home = phone.getString("home");
            String office=phone.getString("office");

            HashMap<String, String> dt = new HashMap<String, String>();
            dt.put("id",id);
            dt.put("name",name);
            dt.put("email",email);
            dt.put("address",address);
            dt.put("mobile",mobile);
            dt.put("home",home);
            dt.put("office", office);
            contactList.add(dt);
        }
        //Listar informacion
        String informacion="";
        for(int i=0;i<contactList.size();i++){
            HashMap<String, String> item=contactList.get(i);
            for(Map.Entry<String, String> entry:item.entrySet()){
                informacion = informacion+""+entry.getKey()+": "+ entry.getValue()+ "\n";

            }
            informacion=informacion+"\n";
        }
        txtInformacion.setText(informacion);
        txtInformacion.setVisibility(View.VISIBLE);
    }
}
