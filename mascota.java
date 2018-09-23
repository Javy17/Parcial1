package sv.unab.edu.Dominio;

import java.util.Objects;
import java.util.StringJoiner;

public class mascota
{
    private Long id;
    private String nombreM;
    private int edad;
    private String peso;
    private String rasa;
    private String fechaNacimientoM;
    private String nombreD;
    private  String apellidoPaterno;
    private String apellidoMaterno;
    private String dui;
    private String telefono;
    private String direccion;
    private String fechaNacimientoD;

    public mascota()
    {

    }

    public mascota(Long id)
    {
        this.id = id;
    }

    public mascota(Long id, String nombreM, int edad, String peso,String rasa, String fechaNacimientoM, String nombreD, String apellidoPaterno, String apellidoMaterno, String dui, String telefono, String direccion, String fechaNacimientoD) {
        this.id = id;
        this.nombreM = nombreM;
        this.edad = edad;
        this.peso = peso;
        this.rasa = rasa;
        this.fechaNacimientoM = fechaNacimientoM;
        this.nombreD = nombreD;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.dui = dui;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimientoD = fechaNacimientoD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getFechaNacimientoM() {
        return fechaNacimientoM;
    }

    public void setFechaNacimientoM(String fechaNacimientoM) {
        this.fechaNacimientoM = fechaNacimientoM;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimientoD() {
        return fechaNacimientoD;
    }

    public void setFechaNacimientoD(String fechaNacimientoD) {
        this.fechaNacimientoD = fechaNacimientoD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof mascota)) return false;
        mascota mascota = (mascota) o;
        return Objects.equals(id, mascota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", mascota.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .toString();
    }
}
