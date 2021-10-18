package pe.com.appciberelectrik.dao;

import android.content.Context;

import java.util.ArrayList;

import pe.com.appciberelectrik.bean.Cliente;

public interface ClienteDAO {
    public ArrayList<Cliente> MostrarCliente(Context contexto);
    public boolean RegistrarCliente(Cliente c, Context contexto);
    public boolean ActualizarCliente(Cliente c, Context contexto);
    public boolean EliminarCliente(Cliente c);
}
