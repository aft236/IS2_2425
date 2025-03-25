package es.unican.is2.Business;

import es.unican.is2.DAO.ContribuyentesDAO;


import es.unican.is2.DAO.VehiculosDAO;
import es.unican.is2.Common.Contribuyente;
import es.unican.is2.Common.DataAccessException;
import es.unican.is2.Common.IContribuyentesDAO;
import es.unican.is2.Common.IGestionContribuyentes;
import es.unican.is2.Common.IGestionVehiculos;
import es.unican.is2.Common.IInfoImpuestoCirculacion;
import es.unican.is2.Common.IVehiculosDAO;
import es.unican.is2.Common.OperacionNoValidaException;
import es.unican.is2.Common.Vehiculo;

public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {
	private IContribuyentesDAO contribuyentesDAO;
	private IVehiculosDAO vehiculosDAO;
	
	public GestionImpuestoCirculacion(ContribuyentesDAO contribuyentesDAO, VehiculosDAO vehiculosDAO) {
		this.contribuyentesDAO = contribuyentesDAO;
		this.vehiculosDAO = vehiculosDAO;
	}

	// IGesrionContribuyentes
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		if (contribuyente(c.getDni()) != null) {
			return null;
		}
		
		return contribuyentesDAO.creaContribuyente(c);
	}

	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
		if (contribuyente(dni) == null) {
			return null;
		}
		
		if (!contribuyente(dni).getVehiculos().isEmpty()) {
			throw new OperacionNoValidaException("El contribuyente tiene vehículos a su nombre\n");
		}
		
		return contribuyentesDAO.eliminaContribuyente(dni);
	}
	

	// IGesrionVehiculos
	/**
	 * Registra un nuevo vehiculo y lo asocia al contribuyente con el dni indicado
	 * @param v Vehiculo que desea registrar
	 * @param dni DNI del contribuyente
	 * @return El vehiculo ya registrado
	 * 		   null si no se registra porque no se encuentra el contribuyente
	 * @throws OperacionNoValidaException si ya existe un vehiculo con la misma matricula
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
		if (contribuyente(dni) == null) {
			return null;
		}
		
		if (vehiculo(v.getMatricula()) != null) {
			throw new OperacionNoValidaException("Ya existe un vehículo con esa matrícula\n");
		}
		
		vehiculosDAO.creaVehiculo(v);
		contribuyente(dni).getVehiculos().add(v);
		
		return v;
	}
	
	/**
	 * Elimina el vehiculo cuya matricula se indica y 
	 * que pertenece al contribuyente cuyo dni se indica
	 * @param matricula Matricula del coche a eliminar
	 * @param dni DNI del propietario del vehiculo
 	 * @return El vehiculo eliminado
 	 *         null si el vehiculo o el propietario no existen
 	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
 	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
		if (contribuyente(dni) == null) {
			return null;
		} 
		
		if (vehiculo(matricula) == null) {
			return null; 
		}
		
		if (contribuyente(dni).buscaVehiculo(matricula) == null) {
			throw new OperacionNoValidaException("El vehículo no pertenece al dni indicado\n");
		}
		
		
		Vehiculo v = vehiculo(matricula);
		contribuyente(dni).getVehiculos().remove(v);
		vehiculosDAO.eliminaVehiculo(matricula);
		
		return v;
	}
	
	/**
	 * Cambia el propietario del vehiculo indicado
	 * @param matricula Matricula del vehiculo
	 * @param dniActual DNI del propietario actual del vehiculo
	 * @param dniNuevo DNI del nuevo propietario del vehiculo
	 * @return true si se realiza el cambio
	 *         false si no realiza el cambio porque el vehiculo o los contribuyentes no existen
	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo)
			throws OperacionNoValidaException, DataAccessException {
		
		if (contribuyente(dniActual) == null) {
			return false;
		}
		
		if (contribuyente(dniNuevo) == null) {
			return false;
		} 
		
		if (vehiculo(matricula) == null) {
			return false; 
		} 
		
		if (contribuyente(dniActual).buscaVehiculo(matricula) == null) {
			throw new OperacionNoValidaException("El vehiculo no pertenece al dni indicado\n");
		}
		
		Vehiculo v = vehiculo(matricula);
		contribuyente(dniActual).getVehiculos().remove(v);
		contribuyente(dniNuevo).getVehiculos().add(v);
		return true;
	}
	
	
	// IInfoImpuestoCirculacion
	/**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo buscado
	 * @return El vehiculo correspondiente a la matricula
	 * 	       null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		return vehiculosDAO.vehiculoPorMatricula(matricula);
	}
	
	/**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		return contribuyentesDAO.contribuyente(dni);
	}
	
}
