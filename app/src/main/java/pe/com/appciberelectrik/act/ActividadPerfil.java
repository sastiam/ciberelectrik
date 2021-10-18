package pe.com.appciberelectrik.act;
//importamos el tinpo fragmento

import android.app.Fragment;
import android.app.FragmentTransaction;
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

import java.util.ArrayList;

import pe.com.appciberelectrik.R;
import pe.com.appciberelectrik.adaptadores.AdaptadorPerfil;
import pe.com.appciberelectrik.bean.Perfil;
import pe.com.appciberelectrik.dao.PerfilDAO;
import pe.com.appciberelectrik.imp.PerfilImp;
import pe.com.appciberelectrik.util.General;


public class ActividadPerfil extends Fragment {
    //creamos una variable estatica
    public static final String TAG="Fragmento Perfil";
    //declarando controles
    private TextView lblCod;
    private EditText txtNom;
    private CheckBox chkEst;
    private ListView lstPerfil;
    private Button btnRegistrar, btnActualizar, btnEliminar;
    //declaramos variables
    private int cod=0, fila=1, est=0;
    private  String nom="";
    private boolean res=false;
    //implementamos la interfaz
    PerfilDAO daoperfil=new PerfilImp();
    //creamos un objeto de la clase general
    General objgeneral=new General();
    //creamos un adaptador de tipo AdaptadorPerfil
    AdaptadorPerfil adaptador;
    //declaramos un array
    ArrayList<Perfil> registroperfil;
    //creamos un objerto de la clase Perfil
    Perfil objperfil=new Perfil();
    //declaramos una variable para las transiciones del fragmento
    FragmentTransaction ft;

    public ActividadPerfil() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // creamos una constante para la vista

        final View raiz=inflater.inflate(R.layout.actividad_perfil, container, false);
        //creamos los controles
        lblCod=raiz.findViewById(R.id.lblCod);
        txtNom=raiz.findViewById(R.id.txtNom);
        chkEst=raiz.findViewById(R.id.chkEst);
        lstPerfil=raiz.findViewById(R.id.lstDistrito);
        btnRegistrar=raiz.findViewById(R.id.btnRegistrar);
        btnActualizar=raiz.findViewById(R.id.btnActualizar);
        btnEliminar=raiz.findViewById(R.id.btnEliminar);
        //creamos un ArrayList de tipo perfil
        registroperfil=new ArrayList<Perfil>();
        //asignamos al arrayList los valores que se van a mostrar
        registroperfil=daoperfil.MostrarPerfil(raiz.getContext());
        if(registroperfil!=null){
            //creamos el adaptador perfil
            adaptador=new AdaptadorPerfil(raiz.getContext(),registroperfil);
            //asignamos los valores a la lista
            lstPerfil.setAdapter(adaptador);

        }

        //agregamos eventos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNom.getText().toString().equals("")){
                    objgeneral.Mensaje(raiz.getContext(),"Ingrese el nombre de perfil","Registran Perfil");
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
                    objperfil.setNombre(nom);
                    objperfil.setEstado(est);
                    //agregamos los valores
                    res=daoperfil.RegistrarPerfil(objperfil, raiz.getContext());
                    Log.e("Error",""+res);
                    if(res){
                        objgeneral.Mensaje(raiz.getContext(),"Se registro el perfil correctamente","Registrar Perfil");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstPerfil.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();

                    }else {
                        objgeneral.Mensaje(raiz.getContext(),"No se registro el perfil correctamente","Registrar Perfil");
                        CargarFragmento();

                    }
                }


            }
        });
        lstPerfil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtenemos el valor de la fila seleccionada
                fila=i;
                //asignando  los valores a los controles
                lblCod.setText(""+registroperfil.get(fila).getCodigo());
                txtNom.setText(registroperfil.get(fila).getNombre());
                if(registroperfil.get(fila).getEstado()==1){
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
                    objperfil.setCodigo(cod);
                    objperfil.setNombre(nom);
                    objperfil.setEstado(est);
                    //agregamos los valores para actualizar
                    res = daoperfil.ActualizarPerfil(objperfil);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el perfil correctamente", "Actualizar Perfil");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstPerfil.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();


                    } else {
                        objgeneral.Mensaje(raiz.getContext(), "No se actualizo el perfil correctamente", "Actualizar Perfil");
                        CargarFragmento();
                    }

                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Actualizar Perfil");
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
                    objperfil.setCodigo(cod);
                    //eliminamos
                    res=daoperfil.EliminarPerfil(objperfil);
                    if (res) {
                        objgeneral.Mensaje(raiz.getContext(), "Se alimino el perfil correctamente", "Eliminar Perfil");
                        CargarFragmento();
                        //asignamos el adaptador a la lista
                        lstPerfil.setAdapter(adaptador);
                        //limpiamos los controles
                        objgeneral.Limpiar((ViewGroup) raiz.findViewById(R.id.frmDistrito));
                        txtNom.requestFocus();


                    }else{
                        objgeneral.Mensaje(raiz.getContext(), "No se elimino el perfil correctamente", "Eliminar Perfil");
                        CargarFragmento();
                    }


                }else{
                    objgeneral.Mensaje(raiz.getContext(), "Seleccione un elemento de la lista", "Eliminar Perfil");
                    CargarFragmento();

                }

            }
        });

        return raiz;
    }
    public void CargarFragmento(){
        ActividadPerfil fperfil=new ActividadPerfil();
        ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, fperfil,ActividadPerfil.TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}