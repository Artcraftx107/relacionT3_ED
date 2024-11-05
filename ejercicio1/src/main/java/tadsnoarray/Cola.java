package tadsnoarray;

public class Cola<E> {
    // Clase estática Nodo para representar los elementos en la cola enlazada
    private static class Nodo<E> {
        E dato;             // Dato almacenado en el nodo
        Nodo<E> siguiente;  // Puntero al siguiente nodo

        //Inicializacion de un nuevo nodo
        Nodo(E data) {
            this.dato = data;
            this.siguiente = null;
        }
    }

    private Nodo<E> head;  // Puntero al primer nodo de la cola
    private Nodo<E> tail;  // Puntero al último nodo de la cola
    private int size;      // Número de elementos en la cola

    // Constructor: inicializa una cola vacía
    public Cola() {
        head = null;
        tail = null;
        size = 0;
    }

    // Metodo para agregar un elemento al final de la cola
    public void meterEnCola(E item) {
        Nodo<E> nuevoNodo = new Nodo<>(item);  // Crea un nuevo nodo con el elemento

        if (isEmpty()) {  // Si la cola está vacía, el nuevo nodo es la cabeza
            head = nuevoNodo;
        } else {          // Si no, enlazamos el último nodo con el nuevo nodo
            tail.siguiente = nuevoNodo;
        }
        tail = nuevoNodo; // Actualizamos el puntero de la cola
        size++;           // Incrementamos el tamaño
    }

    // Metodo para quitar el primer elemento de la cola
    public E quitarDeCola() {
        if (isEmpty()) {  // Lanza una excepción si la cola está vacía
            throw new IllegalStateException("La cola está vacía");
        }

        E item = head.dato;    // Guardamos el dato a devolver
        head = head.siguiente; // Movemos el puntero de la cabeza al siguiente nodo
        size--;                // Decrementamos el tamaño

        if (isEmpty()) {  // Si la cola queda vacía, actualizamos `tail` a null
            tail = null;
        }
        return item;  // Devolvemos el dato eliminado
    }

    // Metodo para obtener el primer elemento sin eliminarlo
    public E mirarPrimero() {
        if (isEmpty()) {  // Lanza una excepción si la cola está vacía
            throw new IllegalStateException("La cola está vacía");
        }
        return head.dato; // Devuelve el dato en la cabeza
    }

    // Metodo que verifica si la cola está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    // Metodo que devuelve el tamaño de la cola
    public int getSize() {
        return size;
    }
}
