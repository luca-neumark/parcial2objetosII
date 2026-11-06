package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.catering.adicionales.MozosAdicionales;
import ar.edu.unahur.obj2.catering.contratos.Contrato;
import ar.edu.unahur.obj2.catering.propuestas.Menu;
import ar.edu.unahur.obj2.catering.propuestas.Propuesta;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class ContratoTest {
    
    private Servicio mesaDeEntradas;
    private Servicio parrilladaCompleta;
    private Servicio mesaDulce;
    private Menu menuParrillero;

    @BeforeEach
    public void setUp() {
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrilladaCompleta = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregar(mesaDeEntradas);
        menuParrillero.agregar(parrilladaCompleta);
        menuParrillero.agregar(mesaDulce);
    }

    @Test
    public void dadoUnContratoSobreMenuConMozos_alConsultarSuImporte_devuelveElPrecioConAdicional() {
        Propuesta menuConMozos = new MozosAdicionales(menuParrillero);
        Contrato contrato = new Contrato("Lucía", menuConMozos);

        Double importe = contrato.getImporte();

        assertEquals(360000.0, importe);
    }

    @Test
    public void dadoUnContratoSobreMenuConMozos_alConsultarSuDescripcion_devuelveLaDelMomentoDeContratar() {
        Propuesta menuConMozos = new MozosAdicionales(menuParrillero);
        Contrato contrato = new Contrato("Lucía", menuConMozos);

        String descripcion = contrato.getDescripcion();

        assertEquals("Menú Parrillero + Mozos adicionales", descripcion);
    }
}
