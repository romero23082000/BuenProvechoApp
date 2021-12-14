package usa.retos.mireto.casos_uso;

import android.database.Cursor;

import java.util.ArrayList;


import usa.retos.mireto.modelos.Servicio;

public class CasoUsoServicios {
    public ArrayList<Servicio> llenarListaServicios(Cursor cursor){
        ArrayList<Servicio> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else {
            while (cursor.moveToNext()) {
                Servicio servicio = new Servicio(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)
                );
                list.add(servicio);
            }
            return list;
        }
    };
}
