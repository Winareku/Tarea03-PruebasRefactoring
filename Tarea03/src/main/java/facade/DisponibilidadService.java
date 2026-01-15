package facade;

import main.*;
import main.enums.*;
import java.util.Date;

public class DisponibilidadService {
    public boolean verificarDisponibilidad(Unidad unidad, Date fechaInicio, Date fechaFin) {
        return unidad.getEstado() == EstadoUnidad.DISPONIBLE;
    }

    public void bloquearUnidad(Unidad unidad, Date fechaInicio, Date fechaFin) {
        unidad.setEstado(EstadoUnidad.RESERVADA);
    }
}
