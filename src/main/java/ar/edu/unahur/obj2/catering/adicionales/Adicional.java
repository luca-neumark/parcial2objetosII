package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public abstract class Adicional implements Propuesta {

    protected Propuesta propuesta;

    public Adicional(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

    @Override
    public Double getPrecio() {
        return this.aplicarSobre(this.propuesta.getPrecio());
    }

    @Override
    public String getDescripcion() {
        return this.propuesta.getDescripcion() + " + " + this.nombreDelAdicional();
    }

    @Override
    public Integer cantidadDeServicios() {
        return this.propuesta.cantidadDeServicios();
    }

    @Override
    public Boolean incluye(String nombreDeServicio) {
        return this.propuesta.incluye(nombreDeServicio);
    }

    protected abstract Double aplicarSobre(Double precioAcumulado);

    protected abstract String nombreDelAdicional();
}
