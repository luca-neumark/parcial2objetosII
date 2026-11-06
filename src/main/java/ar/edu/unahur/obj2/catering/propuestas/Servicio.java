package ar.edu.unahur.obj2.catering.propuestas;

import ar.edu.unahur.obj2.catering.excepciones.PrecioNegativoException;

public class Servicio implements Propuesta {

    private String nombre;
    private Double precio;

    public Servicio(String nombre, Double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo: " + precio);
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override 
    public Double getPrecio() {
        return this.precio;
    }

    @Override 
    public String getDescripcion() {
        return this.nombre;
    }

    @Override 
    public Integer cantidadDeServicios() {
        return 1;
    }

    @Override 
    public Boolean incluye(String nombreDeServicio) {
        return this.nombre.equals(nombreDeServicio);
    }
}
