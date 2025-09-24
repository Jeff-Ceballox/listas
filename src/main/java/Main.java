import simplementeE.ListaSimpleE;
import doblementeE.*;
import circularDoblemente.*;
import circularSimplemente.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("===== Lista Simplemente Enlazada =====");
        ListaSimpleE<Integer> listaSimple = new ListaSimpleE<>();
        listaSimple.agregarFinal(1);
        listaSimple.agregarFinal(2);
        listaSimple.agregarFinal(3);
        listaSimple.imprimirLista();
        listaSimple.eliminarPrimero();
        listaSimple.imprimirLista();

        System.out.println("\n===== Lista Doblemente Enlazada =====");
        ListaSimpleE<String> listaDoble = new ListaSimpleE<>();
        listaDoble.agregarInicio("A");
        listaDoble.agregarFinal("B");
        listaDoble.agregarFinal("C");
        listaDoble.imprimirLista();

        System.out.println("\n===== Lista Circular Simplemente Enlazada =====");
        ListaCircularSimple<Integer> listaCircularSimple = new ListaCircularSimple<>();
        listaCircularSimple.agregarFinal(10);
        listaCircularSimple.agregarFinal(20);
        listaCircularSimple.agregarFinal(30);
        listaCircularSimple.imprimirLista();
        listaCircularSimple.eliminarPrimero();
        listaCircularSimple.imprimirLista();

        System.out.println("\n===== Lista Circular Doblemente Enlazada =====");
        ListaCircularDoblementeEnlazada<String> listaCircularDoble = new ListaCircularDoblementeEnlazada<>();
        listaCircularDoble.insertarInicio("X");
        listaCircularDoble.insertarFinal("Y");
        listaCircularDoble.insertarFinal("Z");
        listaCircularDoble.mostrarAdelante();
        listaCircularDoble.mostrarAtras();
    }
}
