package es.unican.is2.Common;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

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
	
	public void precioImpuesto() {
		// Casos válidos
		assertEquals(turismo.precioImpuesto(), 12,5);
	}
}

