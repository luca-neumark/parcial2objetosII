package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.gestion.ActualizarPrecio;
import ar.edu.unahur.obj2.catering.gestion.AgregarPropuesta;
import ar.edu.unahur.obj2.catering.gestion.Modificacion;
import ar.edu.unahur.obj2.catering.gestion.QuitarPropuesta;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class ModificacionesTest {
    private Servicio mesaDeEntradas;
    private Servicio parrilladaCompleta;
    private Servicio mesaDulce;
    private Servicio fotografo;
    private Menu menuParrillero;

    @BeforeEach
    public void setUp() {
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrilladaCompleta = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        new Servicio("Barra de tragos", 150000.0);
        fotografo = new Servicio("Fotógrafo", 100000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrilladaCompleta);
        menuParrillero.agregar(mesaDulce);
    }

    @Test
    public void dadaUnaModificacionDeAgregado_alAplicarla_laPropuestaPasaAFormarParteDelMenu() {
        Modificacion agregado = new AgregarPropuesta(menuParrillero, fotografo);

        agregado.aplicar();

        assertTrue(menuParrillero.incluye("Fotógrafo"));
    }

    @Test
    public void dadaUnaModificacionDeAgregado_alAplicarla_elPrecioDelMenuSube() {
        Modificacion agregado = new AgregarPropuesta(menuParrillero, fotografo);

        agregado.aplicar();

        assertEquals(432000.0, menuParrillero.getPrecio());
    }

    @Test
    public void dadaUnaModificacionDeAgregadoAplicada_alRevertirla_quitaLoAgregado() {
        Modificacion agregado = new AgregarPropuesta(menuParrillero, fotografo);
        agregado.aplicar();

        agregado.revertir();

        assertEquals(342000.0, menuParrillero.getPrecio());
    }

    @Test
    public void dadaUnaModificacionDeQuitado_alAplicarla_laPropuestaDejaDeSerComponente() {
        Modificacion quitado = new QuitarPropuesta(menuParrillero, mesaDulce);

        quitado.aplicar();

        assertEquals(288000.0, menuParrillero.getPrecio());
    }

    @Test
    public void dadaUnaModificacionDeQuitadoAplicada_alRevertirla_vuelveAAgregarLaPropuesta() {
        Modificacion quitado = new QuitarPropuesta(menuParrillero, mesaDulce);
        quitado.aplicar();

        quitado.revertir();

        assertEquals(342000.0, menuParrillero.getPrecio());
    }

    @Test
    public void dadaUnaActualizacionDePrecio_alAplicarla_elServicioTieneElPrecioNuevo() {
        Modificacion actualizacion = new ActualizarPrecio(parrilladaCompleta, 260000.0);

        actualizacion.aplicar();

        assertEquals(260000.0, parrilladaCompleta.getPrecio());
    }

    @Test
    public void dadaUnaActualizacionDePrecio_alRevertirla_devuelveElPrecioAnterior() {
        Modificacion actualizacion = new ActualizarPrecio(parrilladaCompleta, 260000.0);
        actualizacion.aplicar();

        actualizacion.revertir();

        assertEquals(240000.0, parrilladaCompleta.getPrecio());
    }
}
