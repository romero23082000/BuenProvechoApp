package usa.retos.mireto.casos_uso;

import android.database.Cursor;

import java.util.ArrayList;

import usa.retos.mireto.modelos.Producto;
import usa.retos.mireto.modelos.Sucursal;

public class CasoUsoSucursal {
    public ArrayList<Sucursal> llenarListaSucursales(Cursor cursor){
        ArrayList<Sucursal> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else {
            while (cursor.moveToNext()) {
                Sucursal sucursal = new Sucursal(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)
                );
                list.add(sucursal);
            }
            return list;
        }
    };
}
