package main;

import java.util.Date;

public class DatosReserva {
    private Unidad unidad;
    private Huesped huesped;
    private Date fechaInicio;
    private Date fechaFin;
    private double precioTotal;

    public DatosReserva(Unidad unidad, Huesped huesped, Date fechaInicio, Date fechaFin, double precioTotal) {
        this.unidad = unidad;
        this.huesped = huesped;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }
}
