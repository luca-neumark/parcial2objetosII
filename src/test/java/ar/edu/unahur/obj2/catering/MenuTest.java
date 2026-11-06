package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ar.edu.unahur.obj2.catering.excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class MenuTest {

    private Servicio mesaDeEntradas;
    private Servicio parrilladaCompleta;
    private Servicio mesaDulce;
    private Servicio barraDeTragos;
    private Servicio djYSonido;
    private Menu menuParrillero;
    private Menu packFiesta;

    @BeforeEach
    public void setUp() {
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrilladaCompleta = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        barraDeTragos = new Servicio("Barra de tragos", 150000.0);
        djYSonido = new Servicio("DJ y sonido", 120000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrilladaCompleta);
        menuParrillero.agregar(mesaDulce);

        packFiesta = new Menu("Pack Fiesta", 5.0);
        packFiesta.agregar(barraDeTragos);
        packFiesta.agregar(djYSonido);
    }

    @Test
    public void dadoUnMenuParrillero_alCalcularPrecio_aplicaElDescuentoSobreLaSuma() {
        Double precio = menuParrillero.getPrecio();

        assertEquals(342000.0, precio);
    }

    @Test
    public void dadoUnPackFiesta_alCalcularPrecio_aplicaElDescuentoSobreLaSuma() {
        Double precio = packFiesta.getPrecio();

        assertEquals(256500.0, precio);
    }

    @Test
    public void dadoUnMenuConDescuentoCero_alCalcularPrecio_devuelveLaSumaSinDescuento() {
        Menu sinDescuento = new Menu("Sin descuento", 0.0);
        sinDescuento.agregar(mesaDulce);

        Double precio = sinDescuento.getPrecio();

        assertEquals(60000.0, precio);
    }

    @Test
    public void dadoUnMenuConDescuentoNegativo_alCrearlo_lanzaDescuentoInvalidoException() {
        Double descuentoNegativo = -5.0;

        Executable creacion = () -> new Menu("Menú inválido", descuentoNegativo);

        assertThrows(DescuentoInvalidoException.class, creacion);
    }

    @Test
    public void dadoUnMenuConDescuentoMayorACien_alCrearlo_lanzaDescuentoInvalidoException() {
        Double descuentoExcesivo = 101.0;

        Executable creacion = () -> new Menu("Menú inválido", descuentoExcesivo);

        assertThrows(DescuentoInvalidoException.class, creacion);
    }

    @Test
    public void dadoUnMenuParrillero_alConsultarSuDescripcion_devuelveSuNombre() {
        String descripcion = menuParrillero.getDescripcion();

        assertEquals("Menú Parrillero", descripcion);
    }

    @Test
    public void dadoUnMenuParrillero_alContarServicios_devuelveTres() {
        Integer cantidad = menuParrillero.cantidadDeServicios();

        assertEquals(3, cantidad);
    }

    @Test
    public void dadoUnMenuParrillero_alConsultarSiIncluyeParrilladaCompleta_devuelveVerdadero() {
        Boolean incluye = menuParrillero.incluye("Parrillada completa");

        assertTrue(incluye);
    }

    @Test
    public void dadoUnMenuParrillero_alConsultarSiIncluyeDjYSonido_devuelveFalso() {
        Boolean incluye = menuParrillero.incluye("DJ y sonido");

        assertFalse(incluye);
    }
}
