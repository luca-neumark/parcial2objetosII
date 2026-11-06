package ar.edu.unahur.obj2.catering.gestion;

import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class ActualizarPrecio implements Modificacion {

    private Servicio servicio;
    private Double precioNuevo;
    private Double precioAnterior;

    public ActualizarPrecio(Servicio servicio, Double precioNuevo) {
        this.servicio = servicio;
        this.precioNuevo = precioNuevo;
    }

    @Override
    public void aplicar() {
        this.precioAnterior = this.servicio.getPrecio();
        this.servicio.setPrecio(this.precioNuevo);
    }

    @Override
    public void revertir() {
        this.servicio.setPrecio(this.precioAnterior);
    }
}
