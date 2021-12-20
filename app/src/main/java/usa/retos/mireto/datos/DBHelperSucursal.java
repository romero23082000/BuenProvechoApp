package usa.retos.mireto.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelperSucursal extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    public DBHelperSucursal( Context context) {
        super(context, "Reto4.db", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SUCURSALES("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name VARCAHR,"+
                "location VARCHAR,"+
                "image BLOB"+
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS SUCURSALES");
    }

    public void insertSucursal(String name, String localization, byte[] image){
        String sql = "INSERT INTO SUCURSALES  VALUES(null,?,?,?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);

        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindString(2, localization);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }
    public Cursor getSucursales(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SUCURSALES",null);
        return cursor;
    }
}
