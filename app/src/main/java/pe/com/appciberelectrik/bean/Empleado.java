package pe.com.appciberelectrik.bean;

public class Empleado {
    //atributos
    private int codigo;
    private String nombre;
    private String apellidop;
    private String apellidom;
    private String dni;
    private String direccion;
    private Distrito distrito;
    private String telefono;
    private String celular;
    private String correo;
    private String sexo;
    private String usuario;
    private String clave;
    private Perfil perfil;
    private int estado;

    public Empleado() {
    }

    public Empleado(int codigo, String nombre, String apellidop,
                    String apellidom, String dni, String direccion,
                    Distrito distrito, String telefono, String celular,
                    String correo, String sexo, String usuario,
                    String clave, Perfil perfil, int estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.dni = dni;
        this.direccion = direccion;
        this.distrito = distrito;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
        this.sexo = sexo;
        this.usuario = usuario;
        this.clave = clave;
        this.perfil = perfil;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
