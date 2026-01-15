package facade;

import main.*;
import main.enums.*;

public class DisponibilidadService {
    public boolean verificarDisponibilidad(Unidad unidad) {
        return unidad.getEstado() == EstadoUnidad.DISPONIBLE;
    }

    public void bloquearUnidad(Unidad unidad) {
        unidad.setEstado(EstadoUnidad.RESERVADA);
    }
}
