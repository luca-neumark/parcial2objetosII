package ar.edu.unahur.obj2.catering.propuestas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.catering.excepciones.DescuentoInvalidoException;
import ar.edu.unahur.obj2.catering.excepciones.PropuestaNoIncluidaException;

public class Menu implements Propuesta {

   private String nombre;
    private Double descuento;
    private List<Propuesta> componentes;

    public Menu(String nombre, Double descuento) {
        if (descuento < 0 || descuento > 100) {
            throw new DescuentoInvalidoException("El descuento debe estar entre 0 y 100: " + descuento);
        }
        this.nombre = nombre;
        this.descuento = descuento;
        this.componentes = new ArrayList<>();
    }

    public void agregar(Propuesta propuesta) {
        this.componentes.add(propuesta);
    }

    public void quitar(Propuesta propuesta) {
        if (!this.componentes.contains(propuesta)) {
            throw new PropuestaNoIncluidaException(
                    "La propuesta " + propuesta.getDescripcion() + " no es componente directo de " + this.nombre);
        }
        this.componentes.remove(propuesta);
    }

    @Override 
    public Double getPrecio() {
        Double suma = this.componentes.stream()
                .map(componente -> componente.getPrecio())
                .reduce(0.0, (acumulado, precio) -> acumulado + precio);
        return suma * (100.0 - this.descuento) / 100.0;
    }

    @Override 
    public String getDescripcion() {
        return this.nombre;
    }

    @Override 
    public Integer cantidadDeServicios() {
        return this.componentes.stream()
                .map(componente -> componente.cantidadDeServicios())
                .reduce(0, (acumulado, cantidad) -> acumulado + cantidad);
    }

    @Override 
    public Boolean incluye(String nombreDeServicio) {
        return this.componentes.stream()
                .anyMatch(componente -> componente.incluye(nombreDeServicio));
    }
}
