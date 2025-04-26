package es.unican.is2.src;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;
	private LocalDate caducidad;
	
	/*
     * WMC = 1
     * CBO = 1
     * */
	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) { // CBO = 1
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	/*
     * WMC = 2
     * CCog = 1
     * CBO = 2
     * */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { // CBO = 2
		if (saldoDiarioDisponible<x) { // WMC = 1;	CCog = 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	
	/*
     * WMC = 2
     * CCog = 1
     * */
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		if (saldoDiarioDisponible<x) { // WMC = 1;	CCog = 1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	/*
     * WMC = 1
     * */
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/*
     * WMC = 1
     * */
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	/*
     * WMC = 1
     * */
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

	/*
     * WMC = 1
     * */
	@Override
	public void actualizarCaducidad() {
		cuentaAsociada.setCaducidadDebito(caducidad);
	}

}