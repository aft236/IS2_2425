package es.unican.is2.Common;

import java.time.LocalDate;
import java.time.Year;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
		
		int añosVehiculo = Year.now().getValue() - super.getFechaMatriculacion().getYear();

		// Si la fecha de matriculación aún no ha pasado en este año, restamos 1
		if (LocalDate.now().isBefore(super.getFechaMatriculacion().plusYears(añosVehiculo))) {
		    añosVehiculo--;
		}
		
		if (potencia <= 0) {
			throw new OperacionNoValidaException("La potencia tiene que ser mayor que 0");
		}
		
		if (super.getFechaMatriculacion().isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("La fecha de matriculacion tiene que ser mayor que hoy.");
		}
		
			
		double precio = 0;
		if (potencia < 8) {
			precio = 25;
		} else if (8 <= potencia && potencia < 12) {
			precio = 67;
		} else if (12 <= potencia && potencia < 16) {
			precio = 143;
		} else if (16 <= potencia && potencia < 20) {
			precio = 178;
		} else if (20 <= potencia) {
			precio = 223;
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
