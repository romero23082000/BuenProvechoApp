package usa.retos.mireto.ui.sucursales;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import usa.retos.mireto.FormActivity;
import usa.retos.mireto.R;
import usa.retos.mireto.adaptadores.ProductoAdapter;
import usa.retos.mireto.adaptadores.SucursalAdapter;
import usa.retos.mireto.casos_uso.CasoUsoProducto;
import usa.retos.mireto.casos_uso.CasoUsoSucursal;
import usa.retos.mireto.databinding.FragmentSucursalesBinding;
import usa.retos.mireto.datos.ApiOraSucursal;
import usa.retos.mireto.datos.DBHelper;
import usa.retos.mireto.datos.DBHelperSucursal;
import usa.retos.mireto.modelos.Producto;
import usa.retos.mireto.modelos.Sucursal;
import usa.retos.mireto.ui.SucursalForm;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SucursalesFragment} factory method to
 * create an instance of this fragment.
 */
public class SucursalesFragment extends Fragment {
    private String TABLE_NAME = "SUCURSALES";
    private CasoUsoSucursal casoUsoSucursal;
    private GridView gridView;
    private DBHelperSucursal dbHelperSucursal;
    private ArrayList<Sucursal> sucursales = new ArrayList<>();
    private ApiOraSucursal apiOraSucursal;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sucursales, container,false);
        try{
            casoUsoSucursal = new CasoUsoSucursal();
            dbHelperSucursal = new DBHelperSucursal(getContext());
            Cursor cursor = dbHelperSucursal.getSucursales();
            sucursales = casoUsoSucursal.llenarListaSucursales(cursor);
            gridView = (GridView) root.findViewById(R.id.gridSucursales);
            SucursalAdapter sucursalAdapter = new SucursalAdapter(root.getContext(), sucursales);
            gridView.setAdapter(sucursalAdapter);
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
            case R.id.action_add_sucursal:
                Intent intent = new Intent(getContext(), SucursalForm.class);
                intent.putExtra("name","Sucursales");
                getActivity().startActivity(intent);
                //Toast.makeText(getContext(), "Sucursales", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}