package tadsnoarray;

import java.util.NoSuchElementException;

public class Diccionario<K, V> {
    //Clase interna para representar nodo en la lista
    private static class Nodo<K, V>{
        K clave;
        V valor;
        Nodo<K, V> siguiente;

        Nodo(K clave, V valor){
            this.clave=clave;
            this.valor=valor;
            this.siguiente=null;
        }
    }

    private Nodo<K, V> head;
    private int size;

    public Diccionario(){
        head=null;
        size=0;
    }

    //Metodo para agregar clave-valor
    public void agregar(K clave, V valor) {
        Nodo<K, V> actual = head;
        boolean encontrada = false; // Variable de control

        // Busca la clave en la lista
        while (actual != null && !encontrada) {
            if (actual.clave.equals(clave)) {
                actual.valor = valor; // Actualiza el valor si la clave ya existe
                encontrada = true;    // Marca que se ha encontrado
            } else {
                actual = actual.siguiente;
            }
        }

        // Si la clave no se encontró, agrega un nuevo nodo al principio
        if (!encontrada) {
            Nodo<K, V> nuevoNodo = new Nodo<>(clave, valor);
            nuevoNodo.siguiente = head;
            head = nuevoNodo;
            size++;
        }
    }

    public V obtener(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }
        Nodo<K, V> actual = head;
        boolean found = false;
        while(actual!=null && !found){
            if(actual.clave.equals(clave)){
                found=true;
            }else{
                actual=actual.siguiente;
            }
        }

        if(!found){
            throw new NoSuchElementException("La clave "+clave+" no esta en el diccionario");
        }

        return actual.valor;
    }

    public void eliminar(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }

        if(head.clave.equals(clave)){
            head=head.siguiente;
            size--;
        }else{
            Nodo<K, V> actual = head;
            boolean found = false;
            while(actual!=null && !found){
                if(actual.clave.equals(clave)){
                    found=true;
                    actual=actual.siguiente;
                    size--;
                }else {
                    actual=actual.siguiente;
                }
            }

            if(!found){
                throw new NoSuchElementException("La clave "+clave+" no esta en el diccionario");
            }
        }
    }

    public boolean contieneClave(K clave){
        boolean found = false;
        Nodo<K, V> actual = head;
        while(actual!=null && !found){
            if(actual.clave.equals(clave)){
                found=true;
            }else{
                actual=actual.siguiente;
            }
        }
        return found;
    }

    private boolean isEmpty(){
        return size==0;
    }
}
