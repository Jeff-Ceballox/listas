package circularSimplemente;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularSimple<T> implements Iterable<T> {
    private NodoCircular<T> cabeza;
    private NodoCircular<T> cola;
    private int tamanio;

    public ListaCircularSimple() {
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
        NodoCircular<T> nuevo = new NodoCircular<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
            cola.setSiguiente(cabeza);
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            cola.setSiguiente(cabeza); // circularidad
        }
        tamanio++;
    }

    public void agregarFinal(T valor) {
        NodoCircular<T> nuevo = new NodoCircular<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
            cola.setSiguiente(cabeza);
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setSiguiente(cabeza);
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
            NodoCircular<T> aux = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                aux = aux.getSiguiente();
            }
            NodoCircular<T> nuevo = new NodoCircular<>(valor);
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            tamanio++;
        }
    }

    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).getValor();
    }

    private NodoCircular<T> obtenerNodo(int indice) {
        if (!indiceValido(indice, false)) throw new IndexOutOfBoundsException("Índice inválido");
        NodoCircular<T> aux = cabeza;
        for (int i = 0; i < indice; i++) {
            aux = aux.getSiguiente();
        }
        return aux;
    }

    public int obtenerPosicionNodo(T valor) {
        if (estaVacia()) return -1;
        NodoCircular<T> aux = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if (aux.getValor().equals(valor)) return i;
            aux = aux.getSiguiente();
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
            cola.setSiguiente(cabeza);
        }
        tamanio--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) throw new NoSuchElementException("Lista vacía");
        if (tamanio == 1) {
            cabeza = cola = null;
        } else {
            NodoCircular<T> aux = cabeza;
            while (aux.getSiguiente() != cola) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(cabeza);
            cola = aux;
        }
        tamanio--;
    }

    public boolean eliminar(T valor) {
        if (estaVacia()) return false;

        if (cabeza.getValor().equals(valor)) {
            eliminarPrimero();
            return true;
        }
        NodoCircular<T> aux = cabeza;
        while (aux.getSiguiente() != cabeza) {
            if (aux.getSiguiente().getValor().equals(valor)) {
                if (aux.getSiguiente() == cola) {
                    eliminarUltimo();
                } else {
                    aux.setSiguiente(aux.getSiguiente().getSiguiente());
                    tamanio--;
                }
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
            NodoCircular<T> aux = cabeza;
            for (int i = 0; i < tamanio - 1; i++) {
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
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        NodoCircular<T> aux = cabeza;
        for (int i = 0; i < tamanio; i++) {
            System.out.print(aux.getValor() + " -> ");
            aux = aux.getSiguiente();
        }
        System.out.println("(cabeza)");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoCircular<T> actual = cabeza;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < tamanio;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = actual.getValor();
                actual = actual.getSiguiente();
                count++;
                return val;
            }
        };
    }


}
