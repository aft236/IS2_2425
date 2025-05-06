package es.unican.is2.src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos; // CBO = 1
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;
	
	/*
     * WMC = 1
     * CBO = 1
     * */
	public CuentaAhorro(String numCuenta) throws datoErroneoException { // CBO = 1
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}
	
	/*
     * WMC = 2
     * CCog = 1
     * */
	private void validarCantidad(double cantidad) throws datoErroneoException { // REFACTORIZADO
	    if (cantidad <= 0) { // WMC = 1;  CCog = 1
	        throw new datoErroneoException("No se puede ingresar/retirar una cantidad negativa");
	    }
	}
	
	/*
     * WMC = 1
     * */
	public void nuevoMovimiento(String concepto, double cantidad) { // REFACTORIZADO
		Movimiento m = new Movimiento();
		m.setF(LocalDateTime.now());
		m.setC(concepto);
		m.setI(cantidad);
		this.Movimientos.add(m);
	}
	
	/*
     * WMC = 2
     * CCog = 1
     * CBO = 1
     * */
	public void nuevoRetiro(String concepto, double cantidad) throws saldoInsuficienteException { // REFACTORIZADO
		validarCantidad(cantidad);
	    if (getSaldo() < cantidad) // WMC = 1;	CCog = 1
	        throw new saldoInsuficienteException("Saldo insuficiente"); // CBO = 1

	    Movimiento m = new Movimiento();
	    m.setF(LocalDateTime.now());
	    m.setC(concepto);
	    m.setI(-cantidad);
	    Movimientos.add(m);
	}

	/*
     * WMC = 1
     * */
	public void ingresar(double x) throws datoErroneoException {
		validarCantidad(x);
		
		nuevoMovimiento("Ingreso en efectivo", x);
	}

	/*
     * WMC = 1
     * */
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		nuevoRetiro("Retirada de efectivo", x);
	}

	/*
     * WMC = 1
     * */
	public void ingresar(String concepto, double x) throws datoErroneoException {
		validarCantidad(x);
		
	    nuevoMovimiento(concepto, x);;
	}
	
	/*
     * WMC = 1
     * */
	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException {
		nuevoRetiro(concepto, x);
	}
	
	/*
     * WMC = 2
     * CCog = 1
     * */
	@Override
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { // WMC = 1;		CCog = 1
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}
	
	/*
     * WMC = 1
     * */
	public void addMovimiento(Movimiento m) {
		Movimientos.add(m);
	}

	/*
     * WMC = 1
     * */
	public List<Movimiento> getMovimientos() {
		return Movimientos;
	}

	/*
     * WMC = 1
     * */
	public LocalDate getCaducidadDebito() {
		return caducidadDebito;
	}

	/*
     * WMC = 1
     * */
	public void setCaducidadDebito(LocalDate caducidadDebito) {
		this.caducidadDebito = caducidadDebito;
	}

	/*
     * WMC = 1
     * */
	public LocalDate getCaducidadCredito() {
		return caducidadCredito;
	}

	/*
     * WMC = 1
     * */
	public void setCaducidadCredito(LocalDate caducidadCredito) {
		this.caducidadCredito = caducidadCredito;
	}

	/*
     * WMC = 1
     * */
	public double getLimiteDebito() {
		return limiteDebito;
	}

}