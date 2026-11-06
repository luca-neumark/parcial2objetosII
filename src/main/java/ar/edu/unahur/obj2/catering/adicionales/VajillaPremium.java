package ar.edu.unahur.obj2.catering.adicionales;

import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class VajillaPremium extends Adicional {

    public VajillaPremium(Propuesta propuesta) {
        super(propuesta);
    }

    @Override
    protected Double aplicarSobre(Double precioAcumulado) {
        return precioAcumulado + 12000.0;
    }

    @Override
    protected String nombreDelAdicional() {
        return "Vajilla premium";
    }
}
