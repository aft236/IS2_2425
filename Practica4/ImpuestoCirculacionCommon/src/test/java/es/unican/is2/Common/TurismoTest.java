package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurismoTest {

	private Turismo turismo;

	@BeforeEach
	public void setUp() throws Exception {
		turismo = new Turismo(0, "7227BBB", LocalDate.now(), TipoMotor.GAS, 1);
	}

	@Test
	public void testConstructor() {
		assertEquals(0, turismo.getId());
		assertEquals("7227BBB", turismo.getMatricula());
		assertEquals(LocalDate.now(), turismo.getFechaMatriculacion());
		assertEquals(TipoMotor.GAS, turismo.getMotor());
		assertEquals(1, turismo.getPotencia());
	}

	@Test
	public void precioImpuesto() throws DataAccessException {
		// Casos válidos
		turismo = new Turismo(0, "7227BBB", LocalDate.now(), TipoMotor.GAS, 1);
		assertEquals(turismo.precioImpuesto(), 12.5);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(1), TipoMotor.GAS, 8);
		assertEquals(turismo.precioImpuesto(), 33.5);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusMonths(6), TipoMotor.GAS, 12);
		assertEquals(turismo.precioImpuesto(), 71.5);

		turismo = new Turismo(0, "7227BBB", LocalDate.now(), TipoMotor.HIBRIDO, 16);
		assertEquals(turismo.precioImpuesto(), 44.5);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(4), TipoMotor.HIBRIDO, 20);
		assertEquals(turismo.precioImpuesto(), 55.75);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(2), TipoMotor.HIBRIDO, 7);
		assertEquals(turismo.precioImpuesto(), 6.25);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(25).minusDays(1), TipoMotor.GASOLINA, 1);
		assertEquals(turismo.precioImpuesto(), 0);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(25).plusDays(1), TipoMotor.GAS, 15);
		assertEquals(turismo.precioImpuesto(), 143);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(15), TipoMotor.HIBRIDO, 19);
		assertEquals(turismo.precioImpuesto(), 178);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(25).minusDays(1), TipoMotor.ELECTRICO, 25);
		assertEquals(turismo.precioImpuesto(), 0);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(30), TipoMotor.GAS, 4);
		assertEquals(turismo.precioImpuesto(), 0);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusYears(1), TipoMotor.GASOLINA, 10);
		assertEquals(turismo.precioImpuesto(), 67);

		turismo = new Turismo(0, "7227BBB", LocalDate.now().minusMonths(6), TipoMotor.ELECTRICO, 14);
		assertEquals(turismo.precioImpuesto(), 35.75);

		turismo = new Turismo(0, "7227BBB", LocalDate.now(), TipoMotor.DIESEL, 17);
		assertEquals(turismo.precioImpuesto(), 178);
		
		// Casos no válidos
		turismo = new Turismo(0, "1234ABC", LocalDate.now().plusDays(1), TipoMotor.GAS, 7);
        assertThrows(OperacionNoValidaException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				turismo.precioImpuesto();
			}
		}); 
        
        turismo = new Turismo(0, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, 0);
        assertThrows(OperacionNoValidaException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				turismo.precioImpuesto();
			}
		});
        
        turismo = new Turismo(0, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, -1);
        assertThrows(OperacionNoValidaException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				turismo.precioImpuesto();
			}
		});
	}
}
