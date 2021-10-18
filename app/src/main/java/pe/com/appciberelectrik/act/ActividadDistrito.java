package pe.com.appciberelectrik.act;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorDistrito;
import pe.com.appciberelectrik.adaptadores.AdaptadorPerfil;
import pe.com.appciberelectrik.bean.Distrito;
import pe.com.appciberelectrik.bean.Perfil;
import pe.com.appciberelectrik.dao.DistritoDAO;
import pe.com.appciberelectrik.dao.PerfilDAO;
import pe.com.appciberelectrik.imp.DistritoImp;
import pe.com.appciberelectrik.imp.PerfilImp;
import pe.com.appciberelectrik.util.General;


public class ActividadDistrito extends Fragment {
    //creamos una variable estatica
    public static final String TAG="Fragmento Distrito";
    //declarando controles
    private TextView lblCod;
    private EditText txtNom;
    private CheckBox chkEst;
    private ListView lstDistrito;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    //declaramos variables
    private int cod=0, fila=1, est=0;
    private  String nom="";
    private boolean res=false;
    //implementamos la interfaz
    DistritoDAO daodistrito=new DistritoImp();
    //creamos un objeto de la clase general
    General objgeneral=new General();
    //creamos un adaptador de tipo AdaptadorDistrito
    AdaptadorDistrito adaptador;
    //declaramos un array
    ArrayList<Distrito> registrodistrito;
    //creamos un objerto de la clase Perfil
    Distrito objdistrito=new Distrito();
    //declaramos una variable para las transiciones del fragmento
    FragmentTransaction ft;

    public ActividadDistrito() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // creamos una constante para la vista

        final View raiz=inflater.inflate(R.layout.actividad_distrito, container, false);
        //creamos los controles
        lblCod=raiz.findViewById(R.id.lblCod);
        txtNom=raiz.findViewById(R.id.txtNom);
        chkEst=raiz.findViewById(R.id.chkEst);
        lstDistrito=raiz.findViewById(R.id.lstDistrito);
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);
        //creamos un ArrayList de tipo distrito
        registrodistrito=new ArrayList<Distrito>();
        //asignamos al arrayList los valores que se van a mostrar
        registrodistrito=daodistrito.MostrarDistrito(raiz.getContext());
        if(registrodistrito!=null){
            //creamos el adaptador distrito
            adaptador=new AdaptadorDistrito(raiz.getContext(),registrodistrito);
            //asignamos los valores a la lista
            lstDistrito.setAdapter(adaptador);

        }

        //agregamos eventos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre de Distrito","Registran Distrito");
                    txtNom.requestFocus();
                    raiz.findViewById(R.id.frmDistrito);
                }else {
                    //capturando valores
                    nom=txtNom.getText().toString();
                    if (chkEst.isChecked()){
                        est=1;
                    }else{
                        est=0;

                    }
                    //enviamos los valores a la clase
                    objdistrito.setNombre(nom);
                    objdistrito.setEstado(est);
                    //agregamos los valores
                    res=daodistrito.RegistrarDistrito(objdistrito, raiz.getContext());
                    Log.e("Error",""+res);
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(),"Se registro el distrito correctamente","Registrar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();

                    }else {
                        objgeneral.Mensaje(raiz.getContext(),"No se registro el distrito correctamente","Registrar Distrito");
                        CargarFragmento();

                    }
                }


            }
        });
        lstDistrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtenemos el valor de la fila seleccionada
                fila=i;
                //asignando  los valores a los controles
                lblCod.setText(""+registrodistrito.get(fila).getCodigo());
                txtNom.setText(registrodistrito.get(fila).getNombre());
                if(registrodistrito.get(fila).getEstado()==1){
                    chkEst.setChecked(true);
                }else {
                    chkEst.setChecked(false);
                }

            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar para que se selecciona un elemento de la lista
                if(fila>=0) {
                    cod = Integer.parseInt(lblCod.getText().toString());
                    nom = txtNom.getText().toString();
                    if (chkEst.isChecked()) {
                        est = 1;
                    } else {
                        est = 0;

                    }
                    //enviamos los datos al objeto
                    objdistrito.setCodigo(cod);
                    objdistrito.setNombre(nom);
                    objdistrito.setEstado(est);
                    //agregamos los valores para actualizar
                    res = daodistrito.ActualizarDistrito(objdistrito);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el distrito correctamente", "Actualizar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();


                    } else {
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el distrito correctamente", "Actualizar Distrito");
                        CargarFragmento();
                    }

                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Actualizar Distrito");
                    CargarFragmento();
                }

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validando que seleccione un elemento de la lista
                if(fila>=0){
                    cod=Integer.parseInt(lblCod.getText().toString());
                    //enviamos el codigo
                    objdistrito.setCodigo(cod);
                    //eliminamos
                    res=daodistrito.EliminarDistrito(objdistrito);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "Se alimino el Distrito correctamente", "Eliminar Distrito");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstDistrito.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();


                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el distrito correctamente", "Eliminar Distrito");
                        CargarFragmento();
                    }


                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Eliminar Distrito");
                    CargarFragmento();

                }

            }
        });

        return raiz;

    }

    public void CargarFragmento(){
        ActividadDistrito fdistrito=new ActividadDistrito();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, fdistrito,ActividadDistrito.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

}