package sv.unab.edu.Negocios;

import sv.unab.edu.Dominio.mascota;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MascotaDAL
{
    Connection connection;

    {
        try
        {

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/veterinaria", "postgres", "trance4ever");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agencia_vuelo","postgres", "trance4ever");
    PreparedStatement preparedStatement = null;
    //PreparedStatement MaxId = null;
    //PreparedStatement insertCliente = null;
    public int GuardarMascota(mascota pMascota) throws SQLException {
        //Connection connection = null;
        //PreparedStatement preparedStatement = null;

        Integer resultado = 0;
        try {

            Class.forName("org.postgresql.Driver");
            //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agencia_vuelo", "postgres", "trance4ever");
            preparedStatement = connection.prepareStatement("insert into avr.mascota(nombreM,edad,peso,rasa,fechaNacimientoM,nombreD,apellidoPaterno,apellidoMaterno,dui,telefono,direccion,fechaNacimientoD)values (?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, pMascota.getNombreM());
            preparedStatement.setInt(2, pMascota.getEdad());
            preparedStatement.setString(3, pMascota.getPeso());
            preparedStatement.setString(4, pMascota.getRasa());
            preparedStatement.setString(5, pMascota.getFechaNacimientoM());
            preparedStatement.setString(6, pMascota.getNombreD());
            preparedStatement.setString(7, pMascota.getApellidoPaterno());
            preparedStatement.setString(8, pMascota.getApellidoMaterno());
            preparedStatement.setString(9, pMascota.getDui());
            preparedStatement.setString(10, pMascota.getTelefono());
            preparedStatement.setString(11, pMascota.getDireccion());
            preparedStatement.setString(12, pMascota.getFechaNacimientoD());

            resultado = preparedStatement.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error " + e);

        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return resultado;
    }
    public ArrayList<mascota> BuscarMascota(String pValor) throws SQLException {
        // Connection con = null;
        //PreparedStatement ps = null;

        ArrayList<mascota> listaP = new ArrayList<>();
        //preparedStatement = connection.prepareStatement("select * from avr.persona where nombre Ilike '%"+ pValor +"%' or apellidopaterno Ilike '%"+ pValor +"%' or apellidomaterno Ilike '%"+ pValor +"%' or dui Ilike '%"+ pValor +"%' or telefono Ilike '%"+ pValor +"%' or email Ilike '%"+ pValor +"%' or direccion Ilike '%"+ pValor +"%' or fechanacimiento '%"+ pValor +"%'");
        preparedStatement = connection.prepareStatement("select * from avr.mascota where nombreM Ilike '%" + pValor + "%' or peso Ilike '%" + pValor + "%' or rasa Ilike '%" + pValor + "%' or fechaNacimientoM Ilike '%" + pValor + "%' or nombreD Ilike '%" + pValor + "%'  or apellidoPaterno Ilike '%" + pValor + "%' or apellidoMaterno Ilike '%" + pValor + "%' or dui Ilike '%" + pValor + "%' or telefono Ilike '%" + pValor + "%' or direccion Ilike '%" + pValor + "%' or fechaNacimientoD Ilike '%" + pValor + "%'");

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            mascota m = new mascota();
            m.setId(rs.getLong("Id"));
            m.setNombreM(rs.getString("nombreM"));
            m.setEdad(rs.getInt("edad"));
            m.setPeso(rs.getString("peso"));
            m.setRasa(rs.getString("rasa"));
            m.setFechaNacimientoM(rs.getString("fechaNacimientoM"));
            m.setNombreD(rs.getString("nombreD"));
            m.setApellidoPaterno(rs.getString("apellidoPaterno"));
            m.setApellidoMaterno(rs.getString("apellidoMaterno"));
            m.setDui(rs.getString("dui"));
            m.setTelefono(rs.getString("telefono"));
            m.setDireccion(rs.getString("direccion"));
            m.setFechaNacimientoD(rs.getString("fechaNacimientoD"));
            listaP.add(m);
        }
        connection.close();
        preparedStatement.close();

        //JOptionPane.showMessageDialog(null, listaP);
        return listaP;
    }
    public ArrayList<mascota> BuscarM() throws SQLException {
        // Connection con = null;
        //PreparedStatement ps = null;

        ArrayList<mascota> listaP = new ArrayList<>();
        //preparedStatement = connection.prepareStatement("select * from avr.persona where nombre Ilike '%"+ pValor +"%' or apellidopaterno Ilike '%"+ pValor +"%' or apellidomaterno Ilike '%"+ pValor +"%' or dui Ilike '%"+ pValor +"%' or telefono Ilike '%"+ pValor +"%' or email Ilike '%"+ pValor +"%' or direccion Ilike '%"+ pValor +"%' or fechanacimiento '%"+ pValor +"%'");
        preparedStatement = connection.prepareStatement("select * from avr.mascota");

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            mascota ma = new mascota();
            ma.setId(rs.getLong("Id"));
            ma.setNombreM(rs.getString("nombreM"));
            ma.setEdad(rs.getInt("edad"));
            ma.setPeso(rs.getString("peso"));
            ma.setRasa(rs.getString("rasa"));
            ma.setFechaNacimientoM(rs.getString("fechaNacimientoM"));
            ma.setNombreD(rs.getString("nombreD"));
            ma.setApellidoPaterno(rs.getString("apellidoPaterno"));
            ma.setApellidoMaterno(rs.getString("apellidoMaterno"));
            ma.setDui(rs.getString("dui"));
            ma.setTelefono(rs.getString("telefono"));
            ma.setDireccion(rs.getString("direccion"));
            ma.setFechaNacimientoD(rs.getString("fechaNacimientoD"));
            listaP.add(ma);
        }
        connection.close();
        preparedStatement.close();

        //JOptionPane.showMessageDialog(null, listaP);
        return listaP;
    }
    public int EditarMascota(mascota pMascota) throws SQLException
    {
        Integer resultado = 0;
        try
        {
            Class.forName("org.postgresql.Driver");
            preparedStatement = connection.prepareStatement("UPDATE  avr.mascota set nombreM=?,edad=?,peso=?,rasa=?,fechaNacimientoM=?, nombreD=?, apellidoPaterno=?, apellidoMaterno=?, dui=?,telefono=?,direccion=?,fechaNacimientoD=? where Id=?");
            preparedStatement.setString(1, pMascota.getNombreM());
            preparedStatement.setInt(2, pMascota.getEdad());
            preparedStatement.setString(3, pMascota.getPeso());
            preparedStatement.setString(4, pMascota.getRasa());
            preparedStatement.setString(5, pMascota.getFechaNacimientoM());
            preparedStatement.setString(6, pMascota.getNombreD());
            preparedStatement.setString(7, pMascota.getApellidoPaterno());
            preparedStatement.setString(8, pMascota.getApellidoMaterno());
            preparedStatement.setString(9, pMascota.getDui());
            preparedStatement.setString(10, pMascota.getTelefono());
            preparedStatement.setString(11, pMascota.getDireccion());
            preparedStatement.setString(12, pMascota.getFechaNacimientoD());
            preparedStatement.setLong(13, pMascota.getId());

            resultado = preparedStatement.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error " + e);

        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return resultado;
    }
    public ArrayList<mascota> cargarMascota(Long cod) throws SQLException {
        //Connection Conex = connexion.cone();
        PreparedStatement Ps;
        ArrayList<mascota> ListP = new ArrayList<>();
        Ps =connection.prepareStatement(
                "select * from avr.mascota where id=" + cod);
        ResultSet rs = Ps.executeQuery();
        while (rs.next()) {
            mascota m = new mascota();
            m.setNombreM(rs.getString("NombreM"));
            m.setEdad(rs.getInt("Edad"));
            m.setPeso(rs.getString("Peso"));
            m.setRasa(rs.getString("Rasa"));
            m.setFechaNacimientoM(rs.getString("FechaNacimientoM"));
            m.setNombreD(rs.getString("NombreD"));
            m.setApellidoPaterno(rs.getString("ApellidoPaterno"));
            m.setApellidoMaterno(rs.getString("ApellidoMaterno"));
            m.setDui(rs.getString("Dui"));
            m.setTelefono(rs.getString("Telefono"));

            m.setDireccion(rs.getString("Direccion"));
            m.setFechaNacimientoD(rs.getString("FechaNacimientoD"));
            ListP.add(m);
        }
        connection.close();
        Ps.close();
        return ListP;
    }
    public int Eliminar(Long pId) throws SQLException
    {
        Integer resultado = 0;
        try
        {
            Class.forName("org.postgresql.Driver");
            preparedStatement = connection.prepareStatement("delete FROM avr.mascota where id = ?");
            preparedStatement.setLong(1, pId);
            resultado = preparedStatement.executeUpdate();
            /*preparedStatement = connection.prepareStatement("delete FROM avr.persona where Id = ?");
            preparedStatement.setLong(1, pId);*/
            //resultado = preparedStatement.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "A ocurrido un error " + e);

        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return resultado;
    }
}
