package tadsnoarray;

import java.util.NoSuchElementException;

public class Lista<E> {
    //Clase interna que actua como Nodo
    private static class Nodo<E>{
        E dato;     //Dato del nodo
        Nodo<E> siguiente; //Referencia al siguiente Nodo

        Nodo(E dato){
            this.dato=dato;
            this.siguiente=null; //Inicialmente no apunta a ningun nodo
        }
    }

    private Nodo<E> head;
    private int size;

    //Inicializamos lista vacia
    public Lista(){
        head=null;
        size=0;
    }

    //Metodo para agregar elemento al final de la lista
    public void agregarAlFinal(E item){
        Nodo<E> nuevoNodo = new Nodo<>(item);

        if(head==null){
            //Lista vacia
            head=nuevoNodo;
        }else{
            Nodo<E> actual = head;
            //Recorre hasta ultimo nodo
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nuevoNodo;
        }
        size++;
    }

    //Metodo para quitar elemento por valor
    public boolean quitar(E item){
        boolean found = false;
        if(head == null){
            throw new IllegalStateException("La lista esta vacia");
        }

        if(head.dato.equals(item)){
            head=head.siguiente;
            size--;
        }else{
            Nodo<E> actual = head;
            while(actual.siguiente!=null && !found){
                if(actual.dato.equals(item)){
                    actual = actual.siguiente;
                    size--;
                    found=true;
                }
                actual=actual.siguiente;
            }
        }

        //Si no lo encuentra, tira excepcion
        if(!found){
            throw new NoSuchElementException("El elemento "+item+" no esta en la lista");
        }

        //Si lo ecuentra, devuelve true para comunicar el elemento ha sido eliminado
        return found;
    }
}
