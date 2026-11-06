package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.function.Executable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.excepciones.NadaParaRevertirException;
import ar.edu.unahur.obj2.catering.gestion.ActualizarPrecio;
import ar.edu.unahur.obj2.catering.gestion.AgregarPropuesta;
import ar.edu.unahur.obj2.catering.gestion.GestorDeCarta;
import ar.edu.unahur.obj2.catering.gestion.Modificacion;
import ar.edu.unahur.obj2.catering.gestion.QuitarPropuesta;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class GestorDeCartaTest {
    
    private GestorDeCarta gestor;
    private Menu menuParrillero;
    private Servicio fotografo;
    private Servicio parrilladaCompleta;
    private Servicio barraDeTragos;
    private Servicio mesaDeEntradas;
    private Servicio mesaDulce;

    @BeforeEach
    public void setUp() {
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrilladaCompleta = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        barraDeTragos = new Servicio("Barra de tragos", 150000.0);
        fotografo = new Servicio("Fotógrafo", 100000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrilladaCompleta);
        menuParrillero.agregar(mesaDulce);

        gestor = new GestorDeCarta();
    }

    private void aplicarLoteIgnorandoLaFalla(List<Modificacion> lote) {
        try {
            gestor.aplicarLote(lote);
        } catch (RuntimeException falla) {
        }
    }

    private void aplicarSecuenciaInmediata() {
        gestor.aplicar(new AgregarPropuesta(menuParrillero, fotografo));
        gestor.aplicar(new ActualizarPrecio(parrilladaCompleta, 260000.0));
        gestor.aplicar(new QuitarPropuesta(menuParrillero, mesaDulce));
    }

    @Test
    public void dadoUnLoteConUnaModificacionInvalida_alAplicarlo_dejaElCatalogoComoEstaba() {
        
        gestor.aplicar(new AgregarPropuesta(menuParrillero, fotografo));
        List<Modificacion> lote = List.of(
                new ActualizarPrecio(parrilladaCompleta, 260000.0),
                new QuitarPropuesta(menuParrillero, barraDeTragos));

        aplicarLoteIgnorandoLaFalla(lote);

        assertEquals(240000.0, parrilladaCompleta.getPrecio());
    }

    @Test
    public void dadaUnaSecuenciaInmediata_alAplicarLasTresModificaciones_elMenuQuedaEn396000() {
        aplicarSecuenciaInmediata();

        Double precio = menuParrillero.getPrecio();

        assertEquals(396000.0, precio);
    }

    @Test
    public void dadaUnaSecuenciaInmediata_alRevertirUnaVez_elMenuVuelveAl450000() {
        aplicarSecuenciaInmediata();

        gestor.revertir();

        assertEquals(450000.0, menuParrillero.getPrecio());
    }

    @Test
    public void dadaUnaSecuenciaInmediata_alRevertirDosVeces_elMenuVuelveAl432000() {
        aplicarSecuenciaInmediata();

        gestor.revertir();
        gestor.revertir();

        assertEquals(432000.0, menuParrillero.getPrecio());
    }

    @Test
    public void dadoUnHistorialVacio_alRevertir_lanzaNadaParaRevertirException() {
        Executable reversion = () -> gestor.revertir();

        assertThrows(NadaParaRevertirException.class, reversion);
    }

}
