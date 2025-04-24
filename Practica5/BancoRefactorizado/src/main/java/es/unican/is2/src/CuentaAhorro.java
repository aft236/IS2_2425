package es.unican.is2.src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta) throws datoErroneoException {
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}
	
	// REFACTORIZADO
	private void validarCantidad(double cantidad) throws datoErroneoException {
	    if (cantidad <= 0) {
	        throw new datoErroneoException("No se puede ingresar/retirar una cantidad negativa");
	    }
	}
	
	// REFACTORIZADO
	public void nuevoMovimiento(String concepto, double cantidad) {
		Movimiento m = new Movimiento();
		m.setF(LocalDateTime.now());
		m.setC(concepto);
		m.setI(cantidad);
		this.Movimientos.add(m);
	}
	
	// REFACTORIZADO
	public void nuevoRetiro(String concepto, double cantidad) throws saldoInsuficienteException {
		validarCantidad(cantidad);
	    if (getSaldo() < cantidad)
	        throw new saldoInsuficienteException("Saldo insuficiente");

	    Movimiento m = new Movimiento();
	    m.setF(LocalDateTime.now());
	    m.setC(concepto);
	    m.setI(-cantidad);
	    Movimientos.add(m);
	}

	public void ingresar(double x) throws datoErroneoException {
		validarCantidad(x);
		
		nuevoMovimiento("Ingreso en efectivo", x);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		nuevoRetiro("Retirada de efectivo", x);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException {
		validarCantidad(x);
		
	    nuevoMovimiento(concepto, x);;
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		nuevoRetiro(concepto, x);
	}

	@Override
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) {
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		Movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return Movimientos;
	}

	public LocalDate getCaducidadDebito() {
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) {
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() {
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) {
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() {
		return limiteDebito;
	}

}