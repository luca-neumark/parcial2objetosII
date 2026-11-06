package ar.edu.unahur.obj2.catering.gestion;

import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;

public class QuitarPropuesta implements Modificacion {

    private Menu menu;
    private Propuesta propuesta;

    public QuitarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void aplicar() {
        this.menu.quitar(this.propuesta);
    }

    @Override
    public void revertir() {
        this.menu.agregar(this.propuesta);
    }
}
