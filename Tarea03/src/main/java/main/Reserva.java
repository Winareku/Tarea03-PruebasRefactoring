package main;

import main.enums.EstadoReserva;

import java.util.Date;

public class Reserva {
    private String id;
    private Unidad unidad;
    private Huesped huesped;
    private Date fechaInicio;
    private Date fechaFin;
    private double precioTotal;
    private EstadoReserva estado;

    public Reserva(DatosReserva datos) {
        this.id = "RES-" + System.currentTimeMillis();
        this.unidad = datos.getUnidad();
        this.huesped = datos.getHuesped();
        this.fechaInicio = datos.getFechaInicio();
        this.fechaFin = datos.getFechaFin();
        this.precioTotal = datos.getPrecioTotal();
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public String getId() {
        return id;
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

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}
