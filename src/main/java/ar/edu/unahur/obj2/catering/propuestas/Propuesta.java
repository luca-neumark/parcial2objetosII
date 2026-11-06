package ar.edu.unahur.obj2.catering.propuestas;

public interface Propuesta {

    public Double getPrecio();

    public String getDescripcion();

    public Integer cantidadDeServicios();

    public Boolean incluye(String nombreDeServicio);
}
