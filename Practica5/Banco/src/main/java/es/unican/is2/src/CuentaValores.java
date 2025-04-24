package es.unican.is2.src;

import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	/*
	 * WMC = 1
	 * */
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	/*
	 * WMC = 1
	 * */
	public List<Valor> getValores() {
		return valores;
	}
	
	/*
	 * WMC = 3
	 * CCog = 3
	 * */
	public boolean anhadeValor(Valor valor) {	// CBO = 1
		for (Valor v:valores) {	// WMC = 1;		CCog = 1
			if (v.getEntidad().equals(valor.getEntidad()))	// WMC = 1;		CCog = 1+1
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
