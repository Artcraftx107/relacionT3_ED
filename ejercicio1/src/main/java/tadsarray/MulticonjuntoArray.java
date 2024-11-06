package tadsarray;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MulticonjuntoArray<E> {
    private static final int INITIAL_CAPACITY = 10;
    private E[] elements;
    private int[] counts;
    private int size;

    public MulticonjuntoArray(){
        elements= (E[]) new Object[INITIAL_CAPACITY];
        counts = new int[INITIAL_CAPACITY];
        size=0;
    }

    public void agregar(E item){
        int pos = buscar(item);
        if(pos!=-1){
            counts[pos]++;
        }else{
            if(size== elements.length){
                resize(elements.length*2);
            }
            elements[size] = item;
            counts[size] = 1;
            size++;
        }
    }

    public void quitar(E item){
        int pos = buscar(item);
        if(pos==-1){
            throw new NoSuchElementException("El elemento "+item+" no esta en el multiconjunto");
        }
        if(isEmpty()){
            throw new IllegalStateException("El multiconjunto esta vacio");
        }

        if(counts[pos]>1){
            counts[pos]--;
        }else{
            //Elimina elemento y reordena array
            System.arraycopy(elements, pos+1, elements, pos, size-pos-1);
            System.arraycopy(counts, pos+1, counts, pos, size-pos-1);
            elements[--size]=null;
            counts[size]=0;
        }
    }

    public boolean contiene(E item){
        return buscar(item)!=-1;
    }

    public int cantidad(E item){
        int pos = buscar(item);
        int aux = 0;
        if(pos!=-1){
            aux=counts[pos];
        }
        return aux;
    }

    private int buscar(E item){
        int pos = 0;
        boolean found = false;
        while(pos<size && !found){
            if(elements[pos].equals(item)){
                found=true;
            }else{
                pos++;
            }
        }
        if(!found){
            pos=-1;
        }
        return pos;
    }

    private boolean isEmpty(){
        return size==0;
    }

    private void resize(int nuevaCapacidad){
        elements= Arrays.copyOf(elements, nuevaCapacidad);
        counts = Arrays.copyOf(counts, nuevaCapacidad);
    }
}
