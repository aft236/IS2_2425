package es.unican.is2.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.unican.is2.adt.IListaOrdenada;

/**
 * Clase que implementa el ADT ListaOrdenada
 */
public class ListaOrdenada<E extends Comparable<E>> implements IListaOrdenada<E> {

	private List<E> lista = new ArrayList<E>();

	public E get(int indice) throws IndexOutOfBoundsException {
		if (indice < 0 || indice >= lista.size()) {
			throw new IndexOutOfBoundsException();
		}

		return lista.get(indice);
	}

	public void add(E elemento) throws NullPointerException {

		if (elemento == null) {
			throw new NullPointerException();
		}
		int indice = 0;
		if (lista.size() != 0) {
			while (indice < lista.size() && elemento.compareTo(lista.get(indice)) > 0) {
				indice++;
			}
		}
		lista.add(indice, elemento);
	}

	public E remove(int indice) throws IndexOutOfBoundsException {
		if (indice < 0 || indice >= lista.size()) {
			throw new IndexOutOfBoundsException();
		}

		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		Iterator<E> iter = lista.iterator();
		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}
	}
}
