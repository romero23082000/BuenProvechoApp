package usa.retos.mireto.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import usa.retos.mireto.FormActivity;
import usa.retos.mireto.R;
import usa.retos.mireto.datos.DBHelperSucursal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IProjection;
import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class SucursalForm extends AppCompatActivity {

    private final int REQUEST_CODE_GALLERY = 999;
    private Button btnChoose, btnInsertar;
    private EditText edtName;
    private TextView tvLocalization;
    private ImageView imgSelected;
    private DBHelperSucursal dbHelperSucursal;

    private String name;
    private String localization;
    private byte[] image;
    private MapView myOpenMapView;
    private MapController myMapController;
    ArrayList<OverlayItem> puntos = new ArrayList<OverlayItem>();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionType = ev.getAction();
        switch(actionType){
            case MotionEvent.ACTION_UP:
                Projection pro =  myOpenMapView.getProjection();
                GeoPoint loc = (GeoPoint) pro.fromPixels((int)ev.getX(),(int)ev.getY());
                String latitude = Double.toString((double)loc.getLatitudeE6()/1000000);
                String longitude = Double.toString((double)loc.getLongitudeE6()/1000000);

                tvLocalization.setText(latitude+","+longitude);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal_form);

        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        GeoPoint Bogota = new GeoPoint(4.6351, -74.0703);
        GeoPoint Bogota1 = new GeoPoint(4.6351, -74.1703);
        GeoPoint Bogota2 = new GeoPoint(4.6740, -74.1790);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        edtName = (EditText) findViewById(R.id.editName);
        tvLocalization = (TextView) findViewById(R.id.tvLocalization);
        dbHelperSucursal = new DBHelperSucursal(getApplicationContext());
        imgSelected =(ImageView) findViewById(R.id.imgSelected);


        myOpenMapView = (MapView) findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);

        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(Bogota);
        myMapController.setZoom(6);

        myOpenMapView.setMultiTouchControls(true);
        puntos.add(new OverlayItem("Bogota", "Ciudad de Bogota", Bogota));
        puntos.add(new OverlayItem("Bogota", "Ciudad de Bogota", Bogota1));
        puntos.add(new OverlayItem("Bogota", "Ciudad de Bogota", Bogota2));
        refrescaPuntos();




        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        SucursalForm.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY

                );
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    llenarCampos();
                    dbHelperSucursal.insertSucursal( name,localization,image);
                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "Insert Success", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public  byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }
    public void llenarCampos(){
        name = edtName.getText().toString().trim();
        localization = tvLocalization.getText().toString().trim();
        image = imageViewToByte(imgSelected);

    }
    public void limpiarCampos(){
        edtName.setText("");
        tvLocalization.setText("");
        imgSelected.setImageResource(R.mipmap.ic_launcher);
    }
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            return false;
        }
        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            return true;
        }
    };
    private void refrescaPuntos() {
        myOpenMapView.getOverlays().clear();
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "Sin Permisos", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgSelected.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}