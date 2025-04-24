package es.unican.is2.src;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>(); // CBO = 1
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>(); // CBO = 1
    
    /*
     * WMC = 1
     * */
 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
 	/*
     * WMC = 1
     * */
	public void cambiaDireccion(String calle, String zip, String localidad) {
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	/*
     * WMC = 1
     * */
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	/*
     * WMC = 3
     * CCog	= 2
     * */
	public void anhadeTarjeta(Tarjeta t) {
		tarjetas.add(t);
		if (t instanceof Debito) {	// WMC = +1;	CCog = 1;	CBO = 1
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {	// WMC = +1;	CCog = 1
			Credito tc = (Credito) t;	// CBO = 1
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	/*
     * WMC = 5
     * CCog	= 8
     * */
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {	// WMC = +1;	CCog = 1
			if (c instanceof CuentaAhorro) {	// WMC = +1;	CCog = 1+1;		CBO = 1
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {	// WMC = +1;	CCog = 1+1;		CBO = 1
				for (Valor v: ((CuentaValores) c).getValores()) {	// WMC = +1;	CCog = 1+1+1
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	/*
     * WMC = 1
     * */
	public String getNombre() {
		return nombre;
	}
	
	/*
     * WMC = 1
     * */
	public String getCalle() {
		return calle;
	}
	
	/*
     * WMC = 1
     * */
	public String getZip() {
		return zip;
	}
	
	/*
     * WMC = 1
     * */
	public String getLocalidad() {
		return localidad;
	}
	
	/*
     * WMC = 1
     * */
	public String getTelefono() {
		return telefono;
	}
	
	/*
     * WMC = 1
     * */
	public String getDni() {
		return dni;
	}
}