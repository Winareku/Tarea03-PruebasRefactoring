import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import facade.DisponibilidadService;
import facade.PoliticasService;
import facade.PreciosService;
import facade.ReservaFacade;
import factorymethod.Departamento;
import factorymethod.IUnidad;
import main.Anfitrion;
import main.DatosReserva;
import main.Huesped;
import main.Propiedad;
import main.Reserva;
import main.Unidad;
import main.enums.EstadoUnidad;

class FacadeTest {
    
    private DisponibilidadService disponibilidadService;
    private PoliticasService politicasService;
    private PreciosService preciosService;
    private ReservaFacade reservaFacade;
    
    private Unidad unidad;
    private Huesped huespedValido;
    private Huesped huespedInvalido;
    private Date fechaInicio;
    private Date fechaFin;
    
    @BeforeEach
    void setUp() {
        disponibilidadService = new DisponibilidadService();
        politicasService = new PoliticasService();
        preciosService = new PreciosService();
        reservaFacade = new ReservaFacade();
        
        Anfitrion anfitrion = new Anfitrion("A001", "Carlos Dueño", "carlos@email.com", "0999999999");
        Propiedad propiedad = new Propiedad("P001", "Av. Principal 123", anfitrion);
        IUnidad tipoUnidad = new Departamento();
        
        unidad = new Unidad("U001", tipoUnidad, propiedad);
        unidad.setEstado(EstadoUnidad.DISPONIBLE);
        
        huespedValido = new Huesped("H001", "Juan Pérez", "juan@email.com", "0987654321");
        huespedValido.setCalificacion(4.5);
        
        huespedInvalido = new Huesped("H002", "María López", "maria@email.com", "0987654322");
        huespedInvalido.setCalificacion(2.0);
        
        fechaInicio = new Date();
        fechaFin = new Date(fechaInicio.getTime() + (5 * 24 * 60 * 60 * 1000L));
    }
    
    // DisponibilidadService
    
    @Test
    @DisplayName("DS-01: Verificar disponibilidad según estado")
    void testVerificarDisponibilidad() {
        // Unidad disponible
        assertTrue(disponibilidadService.verificarDisponibilidad(unidad));
        
        // Unidad reservada
        unidad.setEstado(EstadoUnidad.RESERVADA);
        assertFalse(disponibilidadService.verificarDisponibilidad(unidad));
    }
    
    @Test
    @DisplayName("DS-02: Bloquear unidad cambia estado")
    void testBloquearUnidad() {
        disponibilidadService.bloquearUnidad(unidad);
        assertEquals(EstadoUnidad.RESERVADA, unidad.getEstado());
    }
    
    // PoliticasService
    
    @Test
    @DisplayName("POL-01: Verificar políticas según calificación")
    void testVerificarPoliticas() {
        // Huésped con calificación >= 3.0 cumple políticas
        assertTrue(politicasService.verificarPoliticas(unidad, huespedValido));
        
        // Huésped con calificación < 3.0 no cumple políticas
        assertFalse(politicasService.verificarPoliticas(unidad, huespedInvalido));
        
        // Huésped con calificación exactamente 3.0 cumple políticas
        Huesped limite = new Huesped("H003", "Pedro", "pedro@email.com", "0987654323");
        limite.setCalificacion(3.0);
        assertTrue(politicasService.verificarPoliticas(unidad, limite));
    }
    
    @Test
    @DisplayName("POL-02: Calcular penalización según días")
    void testCalcularPenalizacion() {
        // Cancelación con menos de 7 días de anticipación: 50% de penalización
        Date fecha5 = new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000L));
        Date fechaFin5 = new Date(fecha5.getTime() + (3 * 24 * 60 * 60 * 1000L));
        DatosReserva datos1 = new DatosReserva(unidad, huespedValido, fecha5, fechaFin5, 100.0);
        Reserva res1 = new Reserva(datos1);
        assertEquals(50.0, politicasService.calcularPenalizacion(res1, new Date()), 0.01);
        
        // Cancelación entre 7 y 30 días de anticipación: 25% de penalización
        Date fecha20 = new Date(System.currentTimeMillis() + (20 * 24 * 60 * 60 * 1000L));
        Date fechaFin20 = new Date(fecha20.getTime() + (3 * 24 * 60 * 60 * 1000L));
        DatosReserva datos2 = new DatosReserva(unidad, huespedValido, fecha20, fechaFin20, 100.0);
        Reserva res2 = new Reserva(datos2);
        assertEquals(25.0, politicasService.calcularPenalizacion(res2, new Date()), 0.01);
        
        // Cancelación con más de 30 días de anticipación: sin penalización
        Date fecha35 = new Date(System.currentTimeMillis() + (35 * 24 * 60 * 60 * 1000L));
        Date fechaFin35 = new Date(fecha35.getTime() + (3 * 24 * 60 * 60 * 1000L));
        DatosReserva datos3 = new DatosReserva(unidad, huespedValido, fecha35, fechaFin35, 100.0);
        Reserva res3 = new Reserva(datos3);
        assertEquals(0.0, politicasService.calcularPenalizacion(res3, new Date()), 0.01);
    }
    
    // PreciosService
    
    @Test
    @DisplayName("PR-01: Calcular precios correctamente")
    void testPreciosService() {
        // 5 días * 120.0 (precio base Departamento) = 600.0
        assertEquals(600.0, preciosService.calcularPrecioTotal(unidad, fechaInicio, fechaFin), 0.01);
        
        // 20% del precio base = 24.0
        assertEquals(24.0, preciosService.calcularDeposito(unidad), 0.01);
        
        // 10% del precio base = 12.0
        assertEquals(12.0, preciosService.calcularTarifasAdicionales(unidad), 0.01);
    }
    
    // ReservaFacade - Integración
    
    @Test
    @DisplayName("RF-01: Realizar reserva exitosa")
    void testRealizarReserva_Exitosa() {
        Reserva reserva = reservaFacade.realizarReserva(unidad, huespedValido, fechaInicio, fechaFin);
        
        assertNotNull(reserva);
        assertEquals(EstadoUnidad.RESERVADA, unidad.getEstado());
        assertEquals(600.0, reserva.getPrecioTotal(), 0.01);
        assertTrue(huespedValido.getReservas().contains(reserva));
    }
    
    @Test
    @DisplayName("RF-02: Rechazar reserva cuando no cumple requisitos")
    void testRealizarReserva_Rechazada() {
        // Rechazar por políticas (calificación baja)
        assertNull(reservaFacade.realizarReserva(unidad, huespedInvalido, fechaInicio, fechaFin));
        
        // Rechazar por disponibilidad (unidad ya reservada)
        unidad.setEstado(EstadoUnidad.RESERVADA);
        assertNull(reservaFacade.realizarReserva(unidad, huespedValido, fechaInicio, fechaFin));
    }
    
    @Test
    @DisplayName("RF-03: Cancelar reserva")
    void testCancelarReserva() {
        Reserva reserva = reservaFacade.realizarReserva(unidad, huespedValido, fechaInicio, fechaFin);
        
        assertTrue(reservaFacade.cancelarReserva(reserva));
        assertEquals("CANCELADA", reserva.getEstado());
        assertEquals(EstadoUnidad.DISPONIBLE, unidad.getEstado());
    }
    
    @Test
    @DisplayName("RF-04: Modificar reserva")
    void testModificarReserva() {
        Reserva reserva = reservaFacade.realizarReserva(unidad, huespedValido, fechaInicio, fechaFin);
        Date nuevaFecha = new Date(fechaInicio.getTime() + (7 * 24 * 60 * 60 * 1000L));
        
        // No se puede modificar porque la unidad está RESERVADA
        assertFalse(reservaFacade.modificarReserva(reserva, nuevaFecha));
        
        // Liberar la unidad y entonces sí se puede modificar
        unidad.setEstado(EstadoUnidad.DISPONIBLE);
        assertTrue(reservaFacade.modificarReserva(reserva, nuevaFecha));
        assertEquals(nuevaFecha, reserva.getFechaInicio());
    }
}