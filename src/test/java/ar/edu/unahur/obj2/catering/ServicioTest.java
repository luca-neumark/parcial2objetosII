package ar.edu.unahur.obj2.catering;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ar.edu.unahur.obj2.catering.excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.propuestas.Servicio;

public class ServicioTest {

    @Test
    public void dadoUnServicioConPrecioNegativo_alCrearlo_lanzaPrecioNegativoException() {
        Double precioNegativo = -1.0;

        Executable creacion = () -> new Servicio("Mesa dulce", precioNegativo);

        assertThrows(PrecioNegativoException.class, creacion);
    }

    @Test
    public void dadoUnServicioConPrecioCero_alCrearlo_conservaElPrecio() {
        Servicio cortesia = new Servicio("Cortesía", 0.0);

        Double precio = cortesia.getPrecio();

        assertEquals(0.0, precio);
    }

    @Test
    public void dadoUnServicio_alConsultarSuDescripcion_devuelveSuNombre() {
        Servicio mesaDulce = new Servicio("Mesa dulce", 60000.0);

        String descripcion = mesaDulce.getDescripcion();

        assertEquals("Mesa dulce", descripcion);
    }

    @Test
    public void dadoUnServicioSuelto_alContarServicios_devuelveUno() {
        Servicio djYSonido = new Servicio("DJ y sonido", 120000.0);

        Integer cantidad = djYSonido.cantidadDeServicios();

        assertEquals(1, cantidad);
    }

    @Test
    public void dadoUnServicio_alConsultarSiIncluyeSuPropioNombre_devuelveVerdadero() {
        Servicio barraDeTragos = new Servicio("Barra de tragos", 150000.0);

        Boolean incluye = barraDeTragos.incluye("Barra de tragos");

        assertTrue(incluye);
    }

    @Test
    public void dadoUnServicio_alConsultarSiIncluyeOtroNombre_devuelveFalso() {
        Servicio barraDeTragos = new Servicio("Barra de tragos", 150000.0);

        Boolean incluye = barraDeTragos.incluye("DJ y sonido");

        assertFalse(incluye);
    }
}
