package doblementeE;

public class NodoDoble<T> {
    private T valor;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    public NodoDoble(T valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public T getValor() { return valor; }
    public void setValor(T valor) { this.valor = valor; }

    public NodoDoble<T> getSiguiente() { return siguiente; }
    public void setSiguiente(NodoDoble<T> siguiente) { this.siguiente = siguiente; }

    public NodoDoble<T> getAnterior() { return anterior; }
    public void setAnterior(NodoDoble<T> anterior) { this.anterior = anterior; }
}
