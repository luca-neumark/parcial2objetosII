package ar.edu.unahur.obj2.catering.contratos;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class Contrato {

    private String cliente;
    private Double importe;
    private String descripcion;

    public Contrato(String cliente, Propuesta propuesta) {
        this.cliente = cliente;
        this.importe = propuesta.getPrecio();
        this.descripcion = propuesta.getDescripcion();
    }

    public String getCliente() {
        return this.cliente;
    }

    public Double getImporte() {
        return this.importe;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
