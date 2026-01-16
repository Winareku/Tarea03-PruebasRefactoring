package facade;

import main.Huesped;
import main.Reserva;
import main.Unidad;
import java.util.Date;

public class PoliticasService {
    private static final double CALIFICACION_MINIMA = 3.0;
    private static final int DIAS_PLAZO_CORTO = 7;
    private static final int DIAS_PLAZO_LARGO = 30;
    private static final double PENALIZACION_ALTA = 0.5;
    private static final double PENALIZACION_BAJA = 0.25;
    private static final int MILISEGUNDOS_POR_DIA = 1000 * 60 * 60 * 24;

    public boolean verificarPoliticas(Unidad unidad, Huesped huesped) {
        return huesped.getCalificacion() >= CALIFICACION_MINIMA;
    }

    public double calcularPenalizacion(Reserva reserva, Date fechaCancelacion) {
        long diasAntes = (reserva.getFechaInicio().getTime() - fechaCancelacion.getTime()) / MILISEGUNDOS_POR_DIA;
        if (diasAntes < DIAS_PLAZO_CORTO) {
            return reserva.getPrecioTotal() * PENALIZACION_ALTA;
        } else if (diasAntes < DIAS_PLAZO_LARGO) {
            return reserva.getPrecioTotal() * PENALIZACION_BAJA;
        }
        return 0.0;
    }
}