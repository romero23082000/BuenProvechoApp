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
import usa.retos.mireto.modelos.Producto;

public class ProductoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Producto> productos;
    LayoutInflater inflater;

    public ProductoAdapter(Context context, ArrayList<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.grid_item,null);
        }
        ImageView imageView = convertView.findViewById(R.id.imgItem);
        ImageView imageFavorite = convertView.findViewById(R.id.imgFavorite);
        TextView campo1 = convertView.findViewById(R.id.tvCampo1);
        TextView campo2 = convertView.findViewById(R.id.tvCampo2);
        TextView campo3 = convertView.findViewById(R.id.tvCampo3);
        TextView campoId = convertView.findViewById(R.id.tvId);

        Producto producto = productos.get(position);
        byte[] image = producto.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);




        campoId.setText("ID :" + producto.getId());
        campo1.setText( producto.getName());
        campo2.setText( producto.getDescription());
        campo3.setText( producto.getPrice());
        if(Integer.parseInt(producto.getPrice()) == 0){
            imageFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
