package usa.retos.mireto.datos;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.RequiresApi;
import usa.retos.mireto.casos_uso.CasoUsoSucursal;

public class ApiOraSucursal {
    private RequestQueue queue;
    private CasoUsoSucursal casoUsoSucursal;
    private String urlSucursales = "https://g7be2fcfb5932c8-db202112172319.adb.sa-santiago-1.oraclecloudapps.com/ords/admin/sucursales/sucursales";

    public ApiOraSucursal(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertSucursal(String name, String description, String location, ImageView imageView){
        JSONObject json = new JSONObject();
        casoUsoSucursal = new CasoUsoSucursal();
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

}
