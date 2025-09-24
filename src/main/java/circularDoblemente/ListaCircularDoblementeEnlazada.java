package circularDoblemente;

public class ListaCircularDoblementeEnlazada<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int size;

    public ListaCircularDoblementeEnlazada() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            nuevo.siguiente = cabeza;
            nuevo.anterior = cola;
            cabeza.anterior = nuevo;
            cola.siguiente = nuevo;
            cabeza = nuevo;
        }
        size++;
    }


    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            nuevo.siguiente = cabeza;
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cabeza.anterior = nuevo;
            cola = nuevo;
        }
        size++;
    }


    public void insertarEnPosicion(T dato, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        if (posicion == 0) {
            insertarInicio(dato);
        } else if (posicion == size) {
            insertarFinal(dato);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato);
            Nodo<T> actual = cabeza;
            for (int i = 0; i < posicion; i++) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual;
            nuevo.anterior = actual.anterior;
            actual.anterior.siguiente = nuevo;
            actual.anterior = nuevo;
            size++;
        }
    }


    public void eliminarInicio() {
        if (estaVacia()) return;

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = cola;
            cola.siguiente = cabeza;
        }
        size--;
    }


    public void eliminarFinal() {
        if (estaVacia()) return;

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = cabeza;
            cabeza.anterior = cola;
        }
        size--;
    }


    public void eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }

        if (posicion == 0) {
            eliminarInicio();
        } else if (posicion == size - 1) {
            eliminarFinal();
        } else {
            Nodo<T> actual = cabeza;
            for (int i = 0; i < posicion; i++) {
                actual = actual.siguiente;
            }
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
            size--;
        }
    }


    public void eliminar(T dato) {
        if (estaVacia()) return;

        Nodo<T> actual = cabeza;
        do {
            if (actual.dato.equals(dato)) {
                if (actual == cabeza) {
                    eliminarInicio();
                } else if (actual == cola) {
                    eliminarFinal();
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                    actual.siguiente.anterior = actual.anterior;
                    size--;
                }
                return;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
    }


    public boolean buscar(T dato) {
        if (estaVacia()) return false;

        Nodo<T> actual = cabeza;
        do {
            if (actual.dato.equals(dato)) return true;
            actual = actual.siguiente;
        } while (actual != cabeza);

        return false;
    }


    public T obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }


    public T obtenerPrimero() {
        if (estaVacia()) return null;
        return cabeza.dato;
    }


    public T obtenerUltimo() {
        if (estaVacia()) return null;
        return cola.dato;
    }


    public void mostrarAdelante() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo<T> actual = cabeza;
        do {
            System.out.print(actual.dato + " <-> ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("(circular)");
    }


    public void mostrarAtras() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }
        Nodo<T> actual = cola;
        do {
            System.out.print(actual.dato + " <-> ");
            actual = actual.anterior;
        } while (actual != cola);
        System.out.println("(circular)");
    }


    public int obtenerTamaño() {
        return size;
    }

    // 16. Vaciar lista
    public void vaciar() {
        cabeza = null;
        cola = null;
        size = 0;
    }


    @Override
    public String toString() {
        if (estaVacia()) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = cabeza;
        do {
            sb.append(actual.dato).append(", ");
            actual = actual.siguiente;
        } while (actual != cabeza);
        sb.setLength(sb.length() - 2); // eliminar la última coma
        sb.append("]");
        return sb.toString();
    }
}
