package ar.edu.unahur.obj2.catering.gestion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.catering.excepciones.NadaParaRevertirException;

public class GestorDeCarta {

    private List<Modificacion> historial;

    public GestorDeCarta() {
        this.historial = new ArrayList<>();
    }

    public void aplicar(Modificacion modificacion) {
        this.aplicarLote(List.of(modificacion));
    }

    public void aplicarLote(List<Modificacion> lote) {
        List<Modificacion> aplicadas = new ArrayList<>();
        try {
            for (Modificacion modificacion : lote) {
                modificacion.aplicar();
                aplicadas.add(modificacion);
            }
        } catch (RuntimeException falla) {
            for (Integer i = aplicadas.size() - 1; i >= 0; i--) {
                aplicadas.get(i).revertir();
            }
            throw falla;
        }
        this.historial.addAll(aplicadas);
    }

    public void revertir() {
        if (this.historial.isEmpty()) {
            throw new NadaParaRevertirException("No hay modificaciones para revertir");
        }
        Integer indiceDeLaUltima = this.historial.size() - 1;
        Modificacion ultima = this.historial.get(indiceDeLaUltima);
        ultima.revertir();
        this.historial.remove(ultima);
    }
}
