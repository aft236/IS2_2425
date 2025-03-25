package es.unican.is2.Common;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MotocicletaTest {

	private Motocicleta motocicleta;

	@BeforeEach
	public void setUp() throws Exception {
		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusDays(5), TipoMotor.GAS, 5);
	}

	@Test
	public void testConstructor() {
		assertEquals(0, motocicleta.getId());
		assertEquals("7227BBB", motocicleta.getMatricula());
		assertEquals(LocalDate.now().minusDays(5), motocicleta.getFechaMatriculacion());
		assertEquals(TipoMotor.GAS, motocicleta.getMotor());
		assertEquals(5, motocicleta.getCilindrada());
	}

	@Test
	public void precioImpuesto() throws DataAccessException {
		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(25).minusDays(1), TipoMotor.GASOLINA, 1);
		assertEquals(motocicleta.precioImpuesto(), 0);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now(), TipoMotor.HIBRIDO, 70);
		assertEquals(motocicleta.precioImpuesto(), 2);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(1), TipoMotor.GAS, 125);
		assertEquals(motocicleta.precioImpuesto(), 4);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(50), TipoMotor.DIESEL, 126);
		assertEquals(motocicleta.precioImpuesto(), 0);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(12), TipoMotor.ELECTRICO, 180);
		assertEquals(motocicleta.precioImpuesto(), 3.75);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now(), TipoMotor.GASOLINA, 250);
		assertEquals(motocicleta.precioImpuesto(), 15);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(4), TipoMotor.HIBRIDO, 251);
		assertEquals(motocicleta.precioImpuesto(), 7.5);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusMonths(6), TipoMotor.GAS, 375);
		assertEquals(motocicleta.precioImpuesto(), 15);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now(), TipoMotor.ELECTRICO, 500);
		assertEquals(motocicleta.precioImpuesto(), 7.5);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(2), TipoMotor.HIBRIDO, 501);
		assertEquals(motocicleta.precioImpuesto(), 15);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(1), TipoMotor.GAS, 750);
		assertEquals(motocicleta.precioImpuesto(), 30);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(25), TipoMotor.GASOLINA, 1000);
		assertEquals(motocicleta.precioImpuesto(), 0);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now().minusYears(12), TipoMotor.HIBRIDO, 1001);
		assertEquals(motocicleta.precioImpuesto(), 120);

		motocicleta = new Motocicleta(0, "7227BBB", LocalDate.now(), TipoMotor.ELECTRICO, 1500);
		assertEquals(motocicleta.precioImpuesto(), 30);

		// Casos no válidos
		motocicleta = new Motocicleta(0, "1234ABC", LocalDate.now().plusDays(1), TipoMotor.GAS, 7);
		assertThrows(OperacionNoValidaException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				motocicleta.precioImpuesto();
			}
		});

		motocicleta = new Motocicleta(0, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, 0);
		assertThrows(OperacionNoValidaException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				motocicleta.precioImpuesto();
			}
		});

		motocicleta = new Motocicleta(0, "1234ABC", LocalDate.now().minusYears(5), TipoMotor.GASOLINA, -1);
		assertThrows(OperacionNoValidaException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				motocicleta.precioImpuesto();
			}
		});
	}
}
