package doblementeE;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDobleE<T> implements Iterable<T> {
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamanio;

    public ListaDobleE() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void agregarInicio(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void agregarFinal(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
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
            NodoDoble<T> aux = obtenerNodo(indice);
            NodoDoble<T> nuevo = new NodoDoble<>(valor);

            nuevo.setAnterior(aux.getAnterior());
            nuevo.setSiguiente(aux);
            aux.getAnterior().setSiguiente(nuevo);
            aux.setAnterior(nuevo);
            tamanio++;
        }
    }

    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).getValor();
    }

    private NodoDoble<T> obtenerNodo(int indice) {
        if (!indiceValido(indice, false)) throw new IndexOutOfBoundsException("Índice inválido");
        NodoDoble<T> aux;
        if (indice < tamanio / 2) {
            aux = cabeza;
            for (int i = 0; i < indice; i++) aux = aux.getSiguiente();
        } else {
            aux = cola;
            for (int i = tamanio - 1; i > indice; i--) aux = aux.getAnterior();
        }
        return aux;
    }

    public int obtenerPosicionNodo(T valor) {
        NodoDoble<T> aux = cabeza;
        int pos = 0;
        while (aux != null) {
            if (aux.getValor().equals(valor)) return pos;
            aux = aux.getSiguiente();
            pos++;
        }
        return -1;
    }

    private boolean indiceValido(int indice, boolean insercion) {
        return insercion ? (indice >= 0 && indice <= tamanio) : (indice >= 0 && indice < tamanio);
    }

    public void eliminarPrimero() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        if (tamanio == 1) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
        }
        tamanio--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        if (tamanio == 1) {
            cabeza = cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        tamanio--;
    }

    public boolean eliminar(T valor) {
        if (estaVacia()) return false;

        if (cabeza.getValor().equals(valor)) {
            eliminarPrimero();
            return true;
        }
        if (cola.getValor().equals(valor)) {
            eliminarUltimo();
            return true;
        }

        NodoDoble<T> aux = cabeza.getSiguiente();
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().setAnterior(aux.getAnterior());
                }
                tamanio--;
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public void modificarNodo(int indice, T nuevoValor) {
        obtenerNodo(indice).setValor(nuevoValor);
    }

    @SuppressWarnings("unchecked")
    public void ordenarLista() {
        if (tamanio <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoDoble<T> aux = cabeza;
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
        NodoDoble<T> aux = cabeza;
        while (aux != null) {
            System.out.print(aux.getValor() + " <-> ");
            aux = aux.getSiguiente();
        }
        System.out.println("null");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;
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
        cabeza = cola = null;
        tamanio = 0;
    }

    public void invertirLista() {
        if (cabeza == null || cabeza.getSiguiente() == null) return;
        cola = cabeza;
        cabeza = invertirRecursivo(cabeza);
    }

    private NodoDoble<T> invertirRecursivo(NodoDoble<T> nodo) {
        if (nodo == null) return null;
        NodoDoble<T> temp = nodo.getSiguiente();
        nodo.setSiguiente(nodo.getAnterior());
        nodo.setAnterior(temp);
        if (temp == null) return nodo; // llegó al último → nueva cabeza
        return invertirRecursivo(temp);
    }


}