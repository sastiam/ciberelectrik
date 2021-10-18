package pe.com.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Distrito;


public interface DistritoDAO {
    public ArrayList<Distrito> MostrarDistrito(Context contexto);
    public boolean RegistrarDistrito(Distrito d, Context contexto);
    public boolean ActualizarDistrito(Distrito d);
    public boolean EliminarDistrito(Distrito d);

}
