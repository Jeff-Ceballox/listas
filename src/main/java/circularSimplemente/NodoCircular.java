package circularSimplemente;

public class NodoCircular<T> {
    private T valor;
    private NodoCircular<T> siguiente;

    public NodoCircular(T valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public T getValor() { return valor; }
    public void setValor(T valor) { this.valor = valor; }

    public NodoCircular<T> getSiguiente() { return siguiente; }
    public void setSiguiente(NodoCircular<T> siguiente) { this.siguiente = siguiente; }
}
