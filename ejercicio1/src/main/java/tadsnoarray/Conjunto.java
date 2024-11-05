package tadsnoarray;

public class Conjunto<E> {
    // Clase interna que funciona como Nodo
    private static class Nodo<E> {
        E dato;
        Nodo<E> siguiente;

        Nodo(E dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<E> head; // Cabeza de la lista
    private int size; // Tamaño del conjunto

    public Conjunto() {
        head = null; // Inicializa la cabeza como nula
        size = 0; // Inicializa el tamaño en cero
    }

    public void agregar(E item) {
        // No se añaden duplicados
        if (!contiene(item)) {
            Nodo<E> nuevoNodo = new Nodo<>(item);
            nuevoNodo.siguiente = head; // El nuevo nodo apunta al anterior head
            head = nuevoNodo; // Actualiza la cabeza
            size++; // Incrementa el tamaño
        }
    }

    public void quitar(E item) {
        if (isEmpty()) {
            throw new IllegalStateException("El conjunto está vacío");
        }

        if (head.dato.equals(item)) {
            head = head.siguiente; // Elimina el primer nodo
            size--; // Decrementa el tamaño
        } else {
            Nodo<E> actual = head;
            boolean found = false;

            while (actual.siguiente != null && !found) {
                if (actual.siguiente.dato.equals(item)) {
                    found = true; // Se encontró el nodo a eliminar
                } else {
                    actual = actual.siguiente; // Avanza al siguiente nodo
                }
            }

            if (found) {
                actual.siguiente = actual.siguiente.siguiente; // Elimina el nodo encontrado
                size--; // Decrementa el tamaño
            }
        }
    }

    public boolean contiene(E item) {
        Nodo<E> actual = head;
        boolean found = false;
        while (actual != null && !found) {
            if (actual.dato.equals(item)) {
                found = true;  // Elemento encontrado
            } else {
                actual = actual.siguiente; // Avanza al siguiente nodo
            }
        }

        return found; //Devuelve como valor si el elemento se ha encontrado o no
    }
    private boolean isEmpty() {
        return size == 0; // Devuelve true si está vacío, false si no
    }
}
