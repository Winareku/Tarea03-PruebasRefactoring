package main;

import factorymethod.IUnidad;
import main.enums.*;

public class Unidad {
    private String id;
    private EstadoUnidad estado;
    private IUnidad unidadTipo;
    private Propiedad propiedad;

    public Unidad(String id, IUnidad unidadTipo, Propiedad propiedad) {
        this.id = id;
        this.unidadTipo = unidadTipo;
        this.propiedad = propiedad;
        this.estado = EstadoUnidad.DISPONIBLE;
    }

    public String getId() {
        return id;
    }

    public EstadoUnidad getEstado() {
        return estado;
    }

    public void setEstado(EstadoUnidad estado) {
        this.estado = estado;
    }

    public IUnidad getUnidadTipo() {
        return unidadTipo;
    }

    public double getPrecioBase() {
        return unidadTipo.getPrecioBase();
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }
}
