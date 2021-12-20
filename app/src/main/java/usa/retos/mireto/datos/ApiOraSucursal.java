package usa.retos.mireto.datos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import usa.retos.mireto.adaptadores.SucursalAdapter;
import usa.retos.mireto.casos_uso.CasoUsoSucursal;
import usa.retos.mireto.modelos.Sucursal;

public class ApiOraSucursal {
    private RequestQueue queue;
    private CasoUsoSucursal casoUsoSucursal;
    private String urlSucursales = "https://g7be2fcfb5932c8-db202112172319.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/sucursales/sucursales";
    private Object ArrayList;
    private Context context;

    public ApiOraSucursal(Context context) {
        this.queue = Volley.newRequestQueue(context);
        this.context = context;
        casoUsoSucursal = new CasoUsoSucursal();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertSucursal(String name, String description, String location, ImageView imageView){
        JSONObject json = new JSONObject();

        String image = casoUsoSucursal.imageViewToString(imageView);
        try {
            json.put("name", name);
            json.put("description", description);
            json.put("location", location);
            json.put("image",image);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlSucursales, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);

    }

    public void getAllSucursales(GridView gridView){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlSucursales, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    ArrayList<Sucursal> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        byte[] image = casoUsoSucursal.stringToByte(jsonObject.getString("image"));

                        Sucursal sucursal = new Sucursal(
                                jsonObject.getInt("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("location"),
                                image
                        );
                        list.add(sucursal);
                    }
                    SucursalAdapter sucursalAdapter = new SucursalAdapter(context,list);
                    gridView.setAdapter(sucursalAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
    public void deleteSucursal(String id){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, urlSucursales + "/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
    public void getSucursalById(String id, ImageView imageView, EditText name, EditText description, TextView location){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlSucursales + "/" + id, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    byte[] image = casoUsoSucursal.stringToByte(jsonObject.getString("image"));

                    Sucursal sucursal = new Sucursal(
                            jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getString("location"),
                            image
                    );
                    byte[] imageSuc = sucursal.getImage();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageSuc, 0, imageSuc.length);
                    imageView.setImageBitmap(bitmap);
                    name.setText(sucursal.getName());
                    location.setText(sucursal.getLocalization());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateSucursal(String id, String name, String description, String location, ImageView imageView){

        JSONObject json = new JSONObject();
        String image = casoUsoSucursal.imageViewToString(imageView);

        try {
            json.put("id", id);
            json.put("name", name);
            json.put("location", location);
            json.put("image", image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, urlSucursales, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

}
