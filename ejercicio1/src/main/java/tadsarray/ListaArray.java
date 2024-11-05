package tadsarray;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ListaArray<E> {
    private static final int INITIAL_CAPACITY = 10;
    private E[] elements;
    private int size;

    public ListaArray(){
        elements=(E[]) new Object[INITIAL_CAPACITY];
        size=0;
    }

    //Metodo para agregar elemento al final de la lista
    public void agregar(E item){
        if(size== elements.length){
            resize(elements.length*2);
        }

        elements[size++]=item;
    }

    //Metodo para quitar un elemento
    public void quitar(E item){
        if(isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        int pos = buscar(item);

        //Desplaza los elementos a la izquierda
        System.arraycopy(elements, pos+1, elements, pos, size - pos - 1);
        elements[--size]=null;
    }

    //Metodo para conseguir la posicion de un elemento en la lista
    private int buscar(E item) {
        if (isEmpty()){
            throw new IllegalStateException("La lista esta vacia");
        }
        boolean found = false;
        int currentPos = 0;
        while(currentPos<size && !found){
            if(elements[currentPos].equals(item)){
                found=true;
            }else{
                currentPos++;
            }
        }

        //Si el elemento no esta en la lista, lanza excepcion
        if(!found){
            throw new NoSuchElementException("El elemento "+item+" no esta en la lista");
        }
        return currentPos;
    }

    private boolean isEmpty(){
        return size==0;
    }

    private void resize(int nuevaCapacidad){
        elements = Arrays.copyOf(elements, nuevaCapacidad);
    }
}
