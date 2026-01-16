package facade;

import main.enums.*;
import main.*;
import strategy.EmailNotification;
import strategy.INotification;
import java.util.Date;

public class ReservaFacade {
    private DisponibilidadService disponibilidadService;
    private PreciosService preciosService;
    private PoliticasService politicasService;
    private INotification notificationService;

    public ReservaFacade() {
        this.disponibilidadService = new DisponibilidadService();
        this.preciosService = new PreciosService();
        this.politicasService = new PoliticasService();
        this.notificationService = new EmailNotification();
    }

    public Reserva realizarReserva(Unidad unidad, Huesped huesped, Date fechaInicio, Date fechaFin) {
        if (!cumpleRequisitos(unidad, huesped, fechaInicio, fechaFin)) {
            return null;
        }

        double precioTotal = preciosService.calcularPrecioTotal(unidad, fechaInicio, fechaFin);

        if (!procesarPago(precioTotal)) {
            return null;
        }

        return finalizarReserva(unidad, huesped, fechaInicio, fechaFin, precioTotal);
    }

    private boolean cumpleRequisitos(Unidad unidad, Huesped huesped, Date fechaInicio, Date fechaFin) {
        if (!politicasService.verificarPoliticas(unidad, huesped)) {
            System.out.println("El huésped no cumple con las políticas");
            return false;
        }

        if (!disponibilidadService.verificarDisponibilidad(unidad)) {
            System.out.println("La unidad no está disponible");
            return false;
        }
        return true;
    }

    private boolean procesarPago(double precioTotal) {
        // Logica inlined de PagoService.procesarPago
        System.out.println("Procesando pago de $" + precioTotal + " con tarjeta");
        boolean pagoExitoso = true; // Simulación

        if (!pagoExitoso) {
            System.out.println("Error al procesar el pago");
            return false;
        }
        return true;
    }

    private Reserva finalizarReserva(Unidad unidad, Huesped huesped, Date fechaInicio, Date fechaFin,
            double precioTotal) {
        disponibilidadService.bloquearUnidad(unidad);

        DatosReserva datosReserva = new DatosReserva(unidad, huesped, fechaInicio, fechaFin, precioTotal);
        Reserva reserva = new Reserva(datosReserva);
        huesped.realizarReserva(reserva);

        notificationService.send("Reserva confirmada para " + unidad.getId(), huesped.getEmail());

        return reserva;
    }

    public boolean cancelarReserva(Reserva reserva) {
        Date ahora = new Date();
        double penalizacion = politicasService.calcularPenalizacion(reserva, ahora);
        double reembolso = reserva.getPrecioTotal() - penalizacion;

        // Logica inlined de PagoService.procesarReembolso
        System.out.println("Procesando reembolso de $" + reembolso);
        boolean reembolsoExitoso = true; // Simulación

        if (reembolsoExitoso) {
            reserva.setEstado(EstadoReserva.CANCELADA);
            reserva.getUnidad().setEstado(EstadoUnidad.DISPONIBLE);
            return true;
        }
        return false;
    }

    public boolean modificarReserva(Reserva reserva, Date nuevaFecha) {
        if (disponibilidadService.verificarDisponibilidad(reserva.getUnidad())) {
            reserva.setFechaInicio(nuevaFecha);
            return true;
        }
        return false;
    }
}