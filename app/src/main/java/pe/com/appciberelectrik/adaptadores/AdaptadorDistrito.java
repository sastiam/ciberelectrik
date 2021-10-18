package pe.com.appciberelectrik.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.bean.Distrito;


public class AdaptadorDistrito extends BaseAdapter {
    //creamos un ArrayList de la clase Perfil
    private ArrayList<Distrito> listadistrito;
    //creamos un LayoutInflater --> para poder inflar o
    // cargar un layout dentro de otro
    LayoutInflater layoutInflater;

    public AdaptadorDistrito(Context contexto, ArrayList<Distrito> adistrito){
        this.listadistrito=adistrito;
        layoutInflater=LayoutInflater.from(contexto);
    }
    @Override
    public int getCount() {
        return listadistrito.size();
    }

    @Override
    public Object getItem(int i) {
        return listadistrito.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //evaluamos que la vista nno sea nula
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista_distrito,viewGroup, false);
            //creamos un objeto de la clase distrito
            Distrito objdistrito=(Distrito) getItem(i);
            //creamos los controles
            TextView lblCodDis=view.findViewById(R.id.lblCodDis);
            TextView lblNomDis=view.findViewById(R.id.lblNomDis);
            TextView lblEstDis=view.findViewById(R.id.lblEstDis);
            //agregamos  los valores a los controles
            lblCodDis.setText(""+objdistrito.getCodigo());
            lblNomDis.setText(""+objdistrito.getNombre());
            if(objdistrito.getEstado()==1){
                lblEstDis.setText("Habilitado");
            }else {
                lblEstDis.setText("Deshabilitado");
            }
        }


        return view;
    }
}
