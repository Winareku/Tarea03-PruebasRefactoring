import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import factorymethod.CasaFactory;
import factorymethod.DepartamentoFactory;
import factorymethod.HabitacionFactory;
import factorymethod.IUnidad;

class FactoryMethodTest {
    
    private CasaFactory casaFactory;
    private DepartamentoFactory departamentoFactory;
    private HabitacionFactory habitacionFactory;
    
    @BeforeEach
    void setUp() {
        casaFactory = new CasaFactory();
        departamentoFactory = new DepartamentoFactory();
        habitacionFactory = new HabitacionFactory();
    }
    
    // TESTS DE CREACIÓN DE INSTANCIAS
    
    @Test
    @DisplayName("FM-01: CasaFactory crea instancia correcta")
    void testCasaFactory_CreaInstancia() {
        IUnidad casa = casaFactory.crearUnidad();
        
        assertNotNull(casa, "La factory debe crear una instancia");
        assertEquals("Casa", casa.getTipo());
        assertEquals(200.0, casa.getPrecioBase(), 0.01);
        assertEquals(6, casa.getCapacidad());
    }
    
    @Test
    @DisplayName("FM-02: DepartamentoFactory crea instancia correcta")
    void testDepartamentoFactory_CreaInstancia() {
        IUnidad depto = departamentoFactory.crearUnidad();
        
        assertNotNull(depto, "La factory debe crear una instancia");
        assertEquals("Departamento", depto.getTipo());
        assertEquals(120.0, depto.getPrecioBase(), 0.01);
        assertEquals(4, depto.getCapacidad());
    }
    
    @Test
    @DisplayName("FM-03: HabitacionFactory crea instancia correcta")
    void testHabitacionFactory_CreaInstancia() {
        IUnidad habitacion = habitacionFactory.crearUnidad();
        
        assertNotNull(habitacion, "La factory debe crear una instancia");
        assertEquals("Habitación Privada", habitacion.getTipo());
        assertEquals(50.0, habitacion.getPrecioBase(), 0.01);
        assertEquals(2, habitacion.getCapacidad());
    }
    
    // TESTS DE VALIDACIÓN
    
    @Test
    @DisplayName("FM-04: Todas las unidades tienen descripción válida")
    void testDescripcionesValidas() {
        IUnidad casa = casaFactory.crearUnidad();
        IUnidad depto = departamentoFactory.crearUnidad();
        IUnidad habitacion = habitacionFactory.crearUnidad();
        
        assertNotNull(casa.getDescripcion());
        assertNotNull(depto.getDescripcion());
        assertNotNull(habitacion.getDescripcion());
        
        assertFalse(casa.getDescripcion().isEmpty());
        assertFalse(depto.getDescripcion().isEmpty());
        assertFalse(habitacion.getDescripcion().isEmpty());
    }
    
    @Test
    @DisplayName("FM-05: Factory Pattern - instancias independientes")
    void testInstanciasIndependientes() {
        IUnidad casa1 = casaFactory.crearUnidad();
        IUnidad casa2 = casaFactory.crearUnidad();
        
        assertNotSame(casa1, casa2, "Cada llamada debe crear una nueva instancia");
    }
    
    @Test
    @DisplayName("FM-06: Validar que precios y capacidades son positivos")
    void testValoresPositivos() {
        IUnidad casa = casaFactory.crearUnidad();
        IUnidad depto = departamentoFactory.crearUnidad();
        IUnidad habitacion = habitacionFactory.crearUnidad();
        
        assertTrue(casa.getPrecioBase() > 0);
        assertTrue(casa.getCapacidad() > 0);
        
        assertTrue(depto.getPrecioBase() > 0);
        assertTrue(depto.getCapacidad() > 0);
        
        assertTrue(habitacion.getPrecioBase() > 0);
        assertTrue(habitacion.getCapacidad() > 0);
    }
}