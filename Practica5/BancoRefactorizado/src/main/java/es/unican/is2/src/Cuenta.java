package es.unican.is2.src;

public abstract class Cuenta {
	
	private String numCuenta;
	
	/*
     * WMC = 1
     * */
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	/*
     * WMC = 1
     * */
	public String getNumCuenta() {
		return numCuenta;
	}
	
	/*
     * WMC = 1
     * */
	public abstract double getSaldo();
}
