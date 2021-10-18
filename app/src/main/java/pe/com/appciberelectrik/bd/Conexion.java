package pe.com.appciberelectrik.bd;
import android.content.Context;
import android.database.sqlite.*;
import androidx.annotation.Nullable;
public class Conexion extends SQLiteOpenHelper {

    //declaramos atributos para la conexion
    private SQLiteDatabase xcon;
    //creamos variables para cada tabla
    String t_perfil="", t_distrito="", t_empleado="",t_cliente="";

    public Conexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //creamos la conexion
        //getWritableDatabase()-->permite crear y escribir en la base de datos
        xcon=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creamos las tablas
        t_perfil="create table t_perfil(" +
                "codper integer primary key autoincrement," +
                "nomper text not null," +
                "estper interger not null)";
        t_distrito="create table t_distrito(" +
                "coddis integer primary key autoincrement," +
                "nomdis text not null," +
                "estdis integer not null)";

        t_empleado="create table t_empleado(" +
                "codemp integer primary key autoincrement," +
                "nomemp text not null," +
                "apepemp text not null," +
                "apememp text not null," +
                "dniemp text not null," +
                "diremp text not null," +
                "coddis integer not null," +
                "telemp text," +
                "celemp text not null," +
                "coremp text not null," +
                "sexemp text not null," +
                "usuemp text not null," +
                "claemp text not null," +
                "codper integer not null," +
                "estemp integer not null," +
                "foreign key (codper) references t_perfil(codper)," +
                "foreign key (coddis) references t_distrito(coddis))";

        t_cliente="create table t_cliente(" +
                "codcli integer primary key autoincrement," +
                "nomcli text not null," +
                "apepcli text not null," +
                "apemcli text not null," +
                "dnicli text not null," +
                "dircli text not null," +
                "coddis integer not null," +
                "telcli text," +
                "celcli text not null," +
                "corcli text not null," +
                "sexcli text not null," +
                "estcli integer not null," +
                "foreign key (coddis) references t_distrito(coddis))";

        //ejecutamos los comandos para crear la tabla
        db.execSQL(t_perfil);
        db.execSQL(t_distrito);
        db.execSQL(t_cliente);
        db.execSQL(t_empleado);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists t_perfil");
        db.execSQL("drop table if exists t_distrito");
        db.execSQL("drop table if exists t_empleado");
        db.execSQL("drop table if exists t_cliente");
    }
}
