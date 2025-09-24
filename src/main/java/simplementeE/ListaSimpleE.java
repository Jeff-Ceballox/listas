package simplementeE;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimpleE<T> implements Iterable<T> {
    private Nodo<T> cabeza;
    private int tamanio;

    public ListaSimpleE() {
        cabeza = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
        tamanio++;
    }

    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        tamanio++;
    }

    public void agregar(int indice, T valor) {
        if (!indiceValido(indice, true)) throw new IndexOutOfBoundsException("Índice inválido");
        if (indice == 0) {
            agregarInicio(valor);
        } else if (indice == tamanio) {
            agregarFinal(valor);
        } else {
            Nodo<T> nuevo = new Nodo<>(valor);
            Nodo<T> aux = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                aux = aux.getSiguiente();
            }
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            tamanio++;
        }
    }

    private Nodo<T> obtenerNodo(int indice) {
        if (!indiceValido(indice, false)) throw new IndexOutOfBoundsException("Índice inválido");
        Nodo<T> aux = cabeza;
        for (int i = 0; i < indice; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }


    public T obtenerValorNodo(int indice) {
        Nodo<T> nodo = obtenerNodo(indice);
        return nodo.getValor();
    }

    public int obtenerPosicionNodo(T valor) {
        Nodo<T> aux = cabeza;
        int pos = 0;
        while (aux != null) {
            if (aux.getValor().equals(valor)) return pos;
            aux = aux.getSiguiente();
            pos++;
        }
        return -1; // No encontrado
    }

    private boolean indiceValido(int indice, boolean insercion) {
        if (insercion) {
            return indice >= 0 && indice <= tamanio;
        } else {
            return indice >= 0 && indice < tamanio;
        }
    }

    public void eliminarPrimero() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        cabeza = cabeza.getSiguiente();
        tamanio--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        if (tamanio == 1) {
            cabeza = null;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente().getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
        }
        tamanio--;
    }

    public boolean eliminar(T valor) {
        if (estaVacia()) return false;
        if (cabeza.getValor().equals(valor)) {
            eliminarPrimero();
            return true;
        }
        Nodo<T> aux = cabeza;
        while (aux.getSiguiente() != null && !aux.getSiguiente().getValor().equals(valor)) {
            aux = aux.getSiguiente();
        }
        if (aux.getSiguiente() == null) return false;
        aux.setSiguiente(aux.getSiguiente().getSiguiente());
        tamanio--;
        return true;
    }

    public void modificarNodo(int indice, T nuevoValor) {
        Nodo<T> nodo = obtenerNodo(indice);
        nodo.setValor(nuevoValor);
    }

    @SuppressWarnings("unchecked")
    public void ordenarLista() {
        if (estaVacia() || tamanio == 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> aux = cabeza;
            while (aux.getSiguiente() != null) {
                Comparable<T> actual = (Comparable<T>) aux.getValor();
                if (actual.compareTo(aux.getSiguiente().getValor()) > 0) {
                    T temp = aux.getValor();
                    aux.setValor(aux.getSiguiente().getValor());
                    aux.getSiguiente().setValor(temp);
                    cambiado = true;
                }
                aux = aux.getSiguiente();
            }
        } while (cambiado);
    }

    public void imprimirLista() {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getValor() + " -> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            @Override
            public boolean hasNext() { return actual != null; }
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = actual.getValor();
                actual = actual.getSiguiente();
                return val;
            }
        };
    }

    public void borrarLista() {
        cabeza = null;
        tamanio = 0;
    }

    public void invertirLista() {
        if (cabeza == null || cabeza.getSiguiente() == null) return;
        cabeza = invertirRecursivo(cabeza, null);
    }

    private Nodo<T> invertirRecursivo(Nodo<T> actual, Nodo<T> anterior) {
        if (actual == null) return anterior;
        Nodo<T> siguiente = actual.getSiguiente();
        actual.setSiguiente(anterior);
        return invertirRecursivo(siguiente, actual);
    }

}