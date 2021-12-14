package usa.retos.mireto.ui.servicios;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import usa.retos.mireto.FormActivity;
import usa.retos.mireto.R;
import usa.retos.mireto.adaptadores.ProductoAdapter;
import usa.retos.mireto.adaptadores.ServicioAdapter;
import usa.retos.mireto.casos_uso.CasoUsoProducto;
import usa.retos.mireto.casos_uso.CasoUsoServicio;
import usa.retos.mireto.datos.DBHelper;
import usa.retos.mireto.modelos.Producto;
import usa.retos.mireto.modelos.Servicio;


public class ServiciosFragment extends Fragment {
    private String TABLE_NAME = "SERVICIOS";
    private CasoUsoServicio casoUsoServicio;
    private GridView gridView;
    private DBHelper dbHelper;
    private ArrayList<Servicio> servicio;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_productos, container,false);
        try{
            casoUsoServicio = new CasoUsoServicio();
            dbHelper = new DBHelper(getContext());
            Cursor cursor = dbHelper.getData(TABLE_NAME);
            servicio = casoUsoServicio.llenarListaServicios(cursor);
            gridView = (GridView) root.findViewById(R.id.gridProductos);
            ServicioAdapter servicioAdapter = new ServicioAdapter(root.getContext(), servicio);
            gridView.setAdapter(servicioAdapter);
        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


        return root;
        }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("name","Servicios");
                getActivity().startActivity(intent);
                //Toast.makeText(getContext(), "Servicios", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
