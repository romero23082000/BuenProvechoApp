package usa.retos.mireto.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import usa.retos.mireto.R;

import usa.retos.mireto.modelos.Sucursal;

public class SucursalAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sucursal> sucursals;
    LayoutInflater inflater;

    public SucursalAdapter(Context context, ArrayList<Sucursal> sucursals) {
        this.context = context;
        this.sucursals = sucursals;
    }

    @Override
    public int getCount() {
        return sucursals.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.sucursal_item,null);
        }
        ImageView imageView = convertView.findViewById(R.id.imgItem);
        TextView campo1 = convertView.findViewById(R.id.tvNameItem);
        TextView campo2 = convertView.findViewById(R.id.tvLocationItem);
        TextView campoId = convertView.findViewById(R.id.tvIdItem);

        Sucursal sucursal = sucursals.get(position);
        byte[] image = sucursal.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);


        campoId.setText("id :" + sucursal.getId());
        campo1.setText( sucursal.getName());
        campo2.setText( sucursal.getLocalization());
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
