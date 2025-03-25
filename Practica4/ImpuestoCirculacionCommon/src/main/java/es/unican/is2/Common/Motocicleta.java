package es.unican.is2.Common;

import java.time.LocalDate;
import java.time.Year;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		double precio = 0;
		if (cilindrada <= 125) {
			precio = 8;
		} else if (125 < cilindrada && cilindrada <= 250) {
			precio = 15;
		} else if (250 < cilindrada && cilindrada <= 500) {
			precio = 30;
		} else if (500 < cilindrada && cilindrada <= 1000) {
			precio = 60;
		} else if (1000 < cilindrada) {
			precio = 120;
		}

		int añosVehiculo = Year.now().getValue() - super.getFechaMatriculacion().getYear();
		
		// Si la fecha de matriculación aún no ha pasado en este año, restamos 1
		if (LocalDate.now().isBefore(super.getFechaMatriculacion().plusYears(añosVehiculo))) {
			añosVehiculo--;
		}
		
		if (cilindrada <= 0) {
			throw new OperacionNoValidaException("La cilindrada tiene que ser mayor que 0");
		}
		
		if (super.getFechaMatriculacion().isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("La fecha de matriculacion tiene que ser mayor que hoy.");
		}
		
		if (añosVehiculo >= 25) {
			precio = 0;
		} else if (super.getMotor() == TipoMotor.ELECTRICO) {
			precio = precio * 0.25; // 75% de descuento
		} else if (super.getMotor() == TipoMotor.HIBRIDO && añosVehiculo <= 4) {
			precio = precio * 0.25; // 75% de descuento
		} else if (super.getMotor() == TipoMotor.GAS && añosVehiculo <= 1) {
			precio = precio * 0.5;
		}

		return precio;
	}

}
