package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.adicionales.MozosAdicionales;
import ar.edu.unahur.obj2.catering.adicionales.ServicioNocturno;
import ar.edu.unahur.obj2.catering.adicionales.VajillaPremium;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class AdicionalesTest {

    private Servicio mesaDeEntradas;
    private Servicio parrilladaCompleta;
    private Servicio mesaDulce;
    private Servicio barraDeTragos;
    private Servicio djYSonido;
    private Servicio brindis;
    private Menu menuParrillero;
    private Menu packFiesta;
    private Menu eventoCompleto;

    @BeforeEach
    public void setUp() {
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrilladaCompleta = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        barraDeTragos = new Servicio("Barra de tragos", 150000.0);
        djYSonido = new Servicio("DJ y sonido", 120000.0);
        brindis = new Servicio("Brindis de bienvenida", 50000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrilladaCompleta);
        menuParrillero.agregar(mesaDulce);

        packFiesta = new Menu("Pack Fiesta", 5.0);
        packFiesta.agregar(barraDeTragos);
        packFiesta.agregar(djYSonido);

        eventoCompleto = new Menu("Evento Completo", 20.0);
        eventoCompleto.agregar(menuParrillero);
        eventoCompleto.agregar(packFiesta);
        eventoCompleto.agregar(brindis);
    }

    @Test
    public void dadoUnServicioConMozosAdicionales_alCalcularPrecio_sumaDieciochoMil() {
        MozosAdicionales conMozos = new MozosAdicionales(brindis);

        Double precio = conMozos.getPrecio();

        assertEquals(68000.0, precio);
    }

    @Test
    public void dadoUnServicioConServicioNocturno_alCalcularPrecio_sumaElVeintePorCiento() {
        ServicioNocturno conNocturno = new ServicioNocturno(mesaDulce);

        Double precio = conNocturno.getPrecio();

        assertEquals(72000.0, precio);
    }

    @Test
    public void dadoUnMenuConMozosYLuegoNocturno_alCalcularPrecio_elNocturnoIncluyeLosMozos() {
        Propuesta propuesta = new ServicioNocturno(new MozosAdicionales(menuParrillero));

        Double precio = propuesta.getPrecio();

        assertEquals(432000.0, precio);
    }

    @Test
    public void dadoUnServicioNocturnoAplicadoAntesQueMozos_alCalcularPrecio_elOrdenAlteraElPrecioFinal() {
        Propuesta propuesta = new MozosAdicionales(new ServicioNocturno(menuParrillero));

        Double precio = propuesta.getPrecio();

        assertEquals(428400.0, precio);
    }

    @Test
    public void dadoUnServicioConVajillaRepetida_alCalcularPrecio_sumaDosVeces() {
        Propuesta propuesta = new VajillaPremium(new VajillaPremium(brindis));

        Double precio = propuesta.getPrecio();

        assertEquals(74000.0, precio);
    }

    @Test
    public void dadoUnServicioConNocturnoRepetido_alCalcularPrecio_acumulaElRecargo() {
        Propuesta propuesta = new ServicioNocturno(new ServicioNocturno(mesaDulce));

        Double precio = propuesta.getPrecio();

        assertEquals(86400.0, precio);
    }

    @Test
    public void dadoUnEventoCompletoConVajilla_alCalcularPrecio_sumaDoceMilAlPrecioDelMenu() {
        Propuesta propuesta = new VajillaPremium(eventoCompleto);

        Double precio = propuesta.getPrecio();

        assertEquals(530800.0, precio);
    }

    @Test
    public void dadoUnMenuConMozosYNocturno_alConsultarDescripcion_listaLosAdicionalesEnOrden() {
        Propuesta propuesta = new ServicioNocturno(new MozosAdicionales(menuParrillero));

        String descripcion = propuesta.getDescripcion();

        assertEquals("Menú Parrillero + Mozos adicionales + Servicio nocturno", descripcion);
    }

    @Test
    public void dadoUnMenuConAdicional_alConsultarElMenuOriginal_mantieneSuPrecio() {
        Propuesta conMozos = new MozosAdicionales(menuParrillero);

        Double precioOriginal = menuParrillero.getPrecio();

        assertEquals(342000.0, precioOriginal);
    }
}
