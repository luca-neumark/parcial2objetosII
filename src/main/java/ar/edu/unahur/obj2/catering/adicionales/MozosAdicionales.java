package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class MozosAdicionales extends Adicional {

    public MozosAdicionales(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected Double aplicarSobre(Double precioAcumulado) {
        return precioAcumulado + 18000.0;
    }

    @Override
    protected String nombreDelAdicional() {
        return "Mozos adicionales";
    }
}
