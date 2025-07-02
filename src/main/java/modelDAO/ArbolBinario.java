package modelDAO;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {

    class Nodo {

        int info;
        Nodo izq, der;

        public Nodo(int valor) {
            this.info = valor;
            this.izq = null;
            this.der = null;
        }
    }

    Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }
    //Metodo para insertar nodos al arbol
    public void insertar(int valor) {
        this.raiz = insertarNodo(this.raiz, valor);
    }
    //Metodo que valida en que posicion se debe insertar el nodo en el arbol
    private Nodo insertarNodo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }
        //Validamos si el valor es mayor o menor para saber en que posicion va
        if (valor < nodo.info) {
            nodo.izq = insertarNodo(nodo.izq, valor);
        } else if (valor > nodo.info) {
            nodo.der = insertarNodo(nodo.der, valor);
        }

        return nodo;
    }
    //Metodo para poder recorrer el arbol en inorden
    public List<Integer> imprimirIn() {
        List<Integer> resultado = new ArrayList<>();
        ordenarIn(this.raiz, resultado);
        return resultado;
    }
    //Metodo para poder recorrer el arbol en preorden
    public List<Integer> imprimirPre() {
        List<Integer> resultado = new ArrayList<>();
        ordenarPre(this.raiz, resultado);
        return resultado;
    }
    //Metodo para recorrer el arbol en postorden
    public List<Integer> imprimirPos() {
        List<Integer> resultado = new ArrayList<>();
        ordenarPos(this.raiz, resultado);
        return resultado;
    }
    //Metodo que sirve para ir organizando el arbol en inorden para su posterior impresion
    private void ordenarIn(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            ordenarIn(nodo.izq, resultado);
            resultado.add(nodo.info);
            ordenarIn(nodo.der, resultado);
        }
    }
    //Metodo que sirve para ir organizando el arbol en preorden para su posterior impresion
    private void ordenarPre(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            resultado.add(nodo.info);
            ordenarPre(nodo.izq, resultado);
            ordenarPre(nodo.der, resultado);
        }
    }
    //Metodo que sirve para ir organizando el arbol en postorden para su posterior impresion
    private void ordenarPos(Nodo nodo, List<Integer> resultado) {
        if (nodo != null) {
            ordenarPos(nodo.izq, resultado);
            ordenarPos(nodo.der, resultado);
            resultado.add(nodo.info);
        }
    }
    //Metodo para calcular cuantos niveles tiene el arbol creado
    public int niveles(Nodo nodo) {
        if (nodo == null) {
            return -1; // Si el nodo es null, entonces no tiene nivel.
        } else {
            int nivelIzquierdo = niveles(nodo.izq);
            int nivelDerecho = niveles(nodo.der);
            if (nivelIzquierdo > nivelDerecho) {
                return nivelIzquierdo + 1;
            } else {
                return nivelDerecho + 1;
            }
        }
    }
    //Metodo que se encarga de devolver el valor del nivel del arbol
    public int nivelArbol() {
        return niveles(this.raiz);
    }

}
