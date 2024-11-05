package tadsarray;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ConjuntoArray<E> {
    private static final int INITIAL_CAPACITY = 10; // Capacidad inicial del array
    private E[] elements; // Array para almacenar los elementos
    private int size; // Tamaño del conjunto

    public ConjuntoArray() {
        elements = (E[]) new Object[INITIAL_CAPACITY]; // Inicializa el array
        size = 0; // Tamaño inicial
    }

    public void agregar(E item) {
        if (!contiene(item)) {
            if (size == elements.length) {
                resize(elements.length * 2); // Duplicar el tamaño si está lleno
            }
            elements[size++] = item; // Agregar el nuevo elemento
        }
    }

    public void quitar(E item) {
        if (isEmpty()) {
            throw new IllegalStateException("El conjunto está vacío");
        }

        int pos = buscar(item);
        if (pos == -1) {
            throw new NoSuchElementException("El elemento " + item + " no está en el conjunto");
        }

        // Desplaza los elementos a la izquierda
        System.arraycopy(elements, pos + 1, elements, pos, size - pos - 1);
        elements[--size] = null; // Limpiar la referencia
    }

    private boolean contiene(E item) {
        return buscar(item) != -1; // Retorna true si se encuentra el elemento
    }

    public int buscar(E item) {
        int pos = 0;
        boolean found = false;
        while (pos < size && !found) {
            if (elements[pos].equals(item)) {
                found = true; //Sale del bucle si el elemento se ha encontrado en el array
            } else {
                pos++;
            }
        }

        if(!found){
            pos=-1; //Devolvera -1 si el elemento no se ha encontrado en el array
        }
        return pos;
    }

    private void resize(int nuevaCapacidad) {
        elements = Arrays.copyOf(elements, nuevaCapacidad); // Redimensiona el array
    }

    private boolean isEmpty() {
        return size == 0; // Retorna true si el tamaño es cero
    }
}
