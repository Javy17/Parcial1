package sv.unab.edu.Infraestructura;

import sv.unab.edu.Dominio.mascota;
import sv.unab.edu.Negocios.MascotaDAL;

import java.sql.SQLException;
import java.util.ArrayList;

public class MascotaBL
{
    public int GuardarMascota(mascota pMascota) throws SQLException
    {
        return new MascotaDAL().GuardarMascota(pMascota);
    }
    public ArrayList<mascota> BuscarMascota(String pValor) throws SQLException
    {
        return  new MascotaDAL().BuscarMascota(pValor);
    }
    public ArrayList<mascota> BuscarM() throws SQLException
    {
        return  new MascotaDAL().BuscarM();
    }
    public int EditarMascota(mascota pMascota) throws SQLException
    {
        return new MascotaDAL().EditarMascota(pMascota);
    }
    public ArrayList<mascota> cargarMascota(Long cod) throws SQLException
    {
        return new MascotaDAL().cargarMascota(cod);
    }
    public int Eliminar(Long pId) throws SQLException
    {
        return new MascotaDAL().Eliminar(pId);
    }
}
