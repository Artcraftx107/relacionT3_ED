package tadsarray;

public class ColaArray<E> {
    private static final int INITIAL_CAPACITY = 10; // Capacidad inicial del array
    private E[] elements; // Array para almacenar los elementos de la cola
    private int head;     // Índice del primer elemento de la cola
    private int tail;     // Índice del último elemento +1 (para manejo circular)
    private int size;     // Número de elementos en la cola

    public ColaArray() {
        // Inicializa el array con la capacidad inicial
        elements = (E[]) new Object[INITIAL_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    // Metodo para agregar un elemento al final de la cola
    public void meterEnCola(E item) {
        if (size == elements.length) { // Si el array está lleno, lo redimensionamos al doble
            resize(elements.length * 2);
        }

        elements[tail] = item;         // Añadimos el elemento en el índice de `tail`
        tail = (tail + 1) % elements.length; // Incremento circular de `tail`
        size++;                        // Incrementamos el tamaño de la cola
    }

    // Metodo para eliminar y devolver el primer elemento de la cola
    public E quitarDeCola() {
        if (isEmpty()) { // Lanza una excepción si la cola está vacía
            throw new IllegalStateException("La cola está vacía");
        }

        E item = elements[head];   // Guardamos el elemento en `head` para devolverlo
        elements[head] = null;     // Eliminamos la referencia para la recolección de basura
        head = (head + 1) % elements.length; // Incremento circular de `head`
        size--;                    // Decrementamos el tamaño de la cola

        // Si el tamaño es menor que un cuarto de la capacidad, reducimos el array
        if (size > 0 && size <= elements.length / 4 && elements.length / 2 >= INITIAL_CAPACITY) {
            resize(elements.length / 2);
        }

        return item;  // Devolvemos el elemento eliminado
    }

    // Metodo para obtener el primer elemento sin eliminarlo
    public E mirarPrimero() {
        if (isEmpty()) {  // Lanza una excepción si la cola está vacía
            throw new IllegalStateException("La cola está vacía");
        }
        return elements[head];  // Devuelve el elemento en `head`
    }

    // Metodo que verifica si la cola está vacía
    public boolean isEmpty() {
        return size == 0;
    }

    // Metodo para redimensionar el array a una nueva capacidad
    private void resize(int nuevaCapacidad) {
        E[] nuevoArray = (E[]) new Object[nuevaCapacidad]; // Crea un nuevo array

        // Copiamos los elementos desde `head` hasta `tail` en orden
        for (int i = 0; i < size; i++) {
            nuevoArray[i] = elements[(head + i) % elements.length];
        }

        elements = nuevoArray; // Asignamos el nuevo array a `elements`
        head = 0;              // Reiniciamos `head` a 0
        tail = size;           // `tail` ahora apunta después del último elemento
    }
}
