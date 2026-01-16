package facade;

import main.Unidad;
import java.util.Date;

public class PreciosService {
    private static final double PORCENTAJE_DEPOSITO = 0.2;
    private static final double PORCENTAJE_TARIFAS_ADICIONALES = 0.1;
    private static final int MILISEGUNDOS_POR_DIA = 1000 * 60 * 60 * 24;

    public double calcularPrecioTotal(Unidad unidad, Date fechaInicio, Date fechaFin) {
        long dias = (fechaFin.getTime() - fechaInicio.getTime()) / MILISEGUNDOS_POR_DIA;
        return unidad.getPrecioBase() * dias;
    }

    public double calcularDeposito(Unidad unidad) {
        return unidad.getPrecioBase() * PORCENTAJE_DEPOSITO;
    }

    public double calcularTarifasAdicionales(Unidad unidad) {
        return unidad.getPrecioBase() * PORCENTAJE_TARIFAS_ADICIONALES;
    }
}