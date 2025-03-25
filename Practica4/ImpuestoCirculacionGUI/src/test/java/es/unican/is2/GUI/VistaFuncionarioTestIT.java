package es.unican.is2.GUI;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import es.unican.is2.Business.GestionImpuestoCirculacion;
import es.unican.is2.DAO.ContribuyentesDAO;
import es.unican.is2.DAO.VehiculosDAO;

public class VistaFuncionarioTestIT {

	private FrameFixture demo;

	VehiculosDAO vehiculosDAO = new VehiculosDAO();
	ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();

	// Componentes de la capa negocio
	GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);

	// Componentes de la casa presentacion
	VistaFuncionario vistafuncionario = new VistaFuncionario(negocio);

	@BeforeEach
	public void setUp() {
		demo = new FrameFixture(vistafuncionario);
		vistafuncionario.setVisible(true);

	}

	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	public void testConsultaContribuyente() {
		// Introducir un DNI en el campo de búsqueda
		demo.textBox("txtDniContribuyente").enterText("11111111A");

		// Pulsar el botón de consulta
		demo.button("btnBuscar").click();

		// Verificar que se muestran los datos correctos
		demo.textBox("txtNombreContribuyente").requireText("Juan Perez Lopez");

		demo.textBox("txtTotalContribuyente").requireText("206,75");

		// Comprobar que la lista de vehículos está actualizada
		demo.list("listMatriculasVehiculos").requireItemCount(3);

	}

	@Test
	public void testConsultaContribuyente2() {
		// Introducir un DNI en el campo de búsqueda
		demo.textBox("txtDniContribuyente").enterText("22222222A");

		// Pulsar el botón de consulta
		demo.button("btnBuscar").click();

		// Verificar que se muestran los datos correctos
		demo.textBox("txtNombreContribuyente").requireText("Ana Cuesta Ruiz");

		demo.textBox("txtTotalContribuyente").requireText("223,00");

		// Comprobar que la lista de vehículos está actualizada
		demo.list("listMatriculasVehiculos").requireItemCount(1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testConsultaContribuyenteDNIIncorrecto() {
		// Introducir un DNI en el campo de búsqueda
		demo.textBox("txtDniContribuyente").enterText("22222222B");

		// Pulsar el botón de consulta
		demo.button("btnBuscar").click();

		// Verificar que se muestran los datos correctos
		demo.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");

		demo.textBox("txtTotalContribuyente").requireText("0");

		// Comprobar que la lista de vehículos está actualizada
		demo.list("listMatriculasVehiculos").requireItemCount(0);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}