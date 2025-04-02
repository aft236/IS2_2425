package es.unican.is2.src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class ListaOrdenadaTest {

	ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();

	@Test
	public void testGet() {
		// Casos Validos
		// Caso 1: Get(0, [2]) = 2
		lista.add(2);
		Integer i = 2;
		assertEquals(lista.get(0), i);

		// Caso 2: Get(2, [5, 7, 2]) = 2
		lista.add(5);
		lista.add(7);
		i = 7;
		assertEquals(i, lista.get(2));

		// Caso 3: Get(1, [2, 5, 7]) = 5
		i = 5;
		assertEquals(i, lista.get(1));
		lista.clear();

		// Casos No Validos
		// Caso 4: Get(-1, [2]): IndexOutOfBoundsException
		lista.add(2);
		assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.get(-5);
			}
		});
		lista.clear();

		// Caso 5: Get(3, [5, 2, 7]): IndexOutOfBoundsException
		lista.add(5);
		lista.add(2);
		lista.add(7);
		assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.get(3);
			}
		});
		lista.clear();

		// Caso 6: Get(1, []): IndexOutOfBoundsException
		assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.get(1);
			}
		});
		lista.clear();
	}

	@Test
	public void testAdd() {
		// Casos Validos
		// Caso 1: Add(5, [])= [5]
		lista.add(5);
		Integer i = 5;
		assertEquals(1, lista.size());
		assertEquals(i, lista.get(0));
		lista.clear();

		// Caso 2: Add(2, [5]) = [2, 5]
		lista.add(5);
		lista.add(2);
		i = 2;
		assertEquals(2, lista.size());
		assertEquals(i, lista.get(0));
		lista.clear();

		// Caso 3: Add(9, [2, 5, 7]) = [2, 5, 7, 9]
		lista.add(5);
		lista.add(7);
		lista.add(2);
		lista.add(9);
		assertEquals(4, lista.size());

		i = 2;
		assertEquals(i, lista.get(0));

		i = 5;
		assertEquals(i, lista.get(1));

		i = 7;
		assertEquals(i, lista.get(2));

		i = 9;
		assertEquals(i, lista.get(3));
		lista.clear();

		// Casos No Validos
		// Casos 4: Add(null, [2,5]): NullPointerException
		assertThrows(NullPointerException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.add(null);
			}
		});
		lista.clear();
	}

	@Test
	public void testRemove() {
		// Casos Validos
		// Caso 1: Remove(0, [2]) : []
		lista.add(2);
		lista.remove(0);
		assertEquals(0, lista.size());
		lista.clear();

		// Caso 2: Remove(2, [2, 5, 7]) = [2, 5]
		lista.add(2);
		lista.add(5);
		lista.add(7);
		lista.remove(2);
		assertEquals(2, lista.size());

		Integer i = 2;
		assertEquals(i, lista.get(0));

		i = 5;
		assertEquals(i, lista.get(1));
		lista.clear();

		// Caso 3: Remove(1, [2, 5, 7]) = [2, 7]
		lista.add(2);
		lista.add(5);
		lista.add(7);
		lista.remove(1);
		assertEquals(2, lista.size());

		i = 2;
		assertEquals(i, lista.get(0));

		i = 7;
		assertEquals(i, lista.get(1));
		lista.clear();

		// Casos No Validos
		// Caso 4: Remove(-1, [7]) : IndexOutOfBoundsException
		lista.add(2);
		assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.remove(-5);
			}
		});
		lista.clear();

		// Caso 5: Remove(3, [2, 5, 7]): IndexOutOfBoundsException
		lista.add(2);
		lista.add(5);
		lista.add(7);
		assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.remove(3);
			}
		});
		lista.clear();

		// Caso 6: Remove(1, [2, 5, 7]) = [2, 7]
		assertThrows(IndexOutOfBoundsException.class, new ThrowingRunnable() {
			public void run() throws Throwable {
				lista.remove(1);
			}
		});
		lista.clear();
	}

	@Test
	public void testSize() {
		// Casos válidos.
		// Caso 1: Size([]) : 0
		ListaOrdenada<Integer> lista1 = new ListaOrdenada<Integer>();
		assertEquals(0, lista1.size());

		// Caso 2: Size([2]): 1
		ListaOrdenada<Integer> lista2 = new ListaOrdenada<Integer>();
		lista2.add(2);
		assertEquals(1, lista2.size());

		// Caso 3: Size([2, 5, 7]): 3
		ListaOrdenada<Integer> lista3 = new ListaOrdenada<Integer>();
		lista3.add(2);
		lista3.add(5);
		lista3.add(7);
		assertEquals(3, lista3.size());

	}

	@Test
	public void testClear() {
		// Caso válido.
		// Caso 1: Clear([2, 5, 7]) = []
		ListaOrdenada<Integer> lista1 = new ListaOrdenada<Integer>();
		lista1.add(2);
		lista1.add(5);
		lista1.add(7);
		lista1.clear();
		assertEquals(0, lista1.size());
	}
}
