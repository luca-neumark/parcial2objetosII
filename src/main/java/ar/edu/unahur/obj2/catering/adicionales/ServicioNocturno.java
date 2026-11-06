package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class ServicioNocturno extends Adicional {

    public ServicioNocturno(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected Double aplicarSobre(Double precioAcumulado) {
        return precioAcumulado * 120.0 / 100.0;
    }

    @Override
    protected String nombreDelAdicional() {
        return "Servicio nocturno";
    }
}
