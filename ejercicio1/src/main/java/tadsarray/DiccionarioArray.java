package tadsarray;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class DiccionarioArray<K, V> {
    private static final int INITIAL_CAPACITY=10;
    private K[] claves;
    private V[] valores;
    private int size;

    public DiccionarioArray(){
        claves= (K[]) new Object[INITIAL_CAPACITY];
        valores= (V[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void agregar(K clave, V valor){
        int pos = buscar(clave);
        if(pos!=-1){
            valores[pos] = valor;
        }else{
            if(size== claves.length){
                resize(claves.length*2);
            }
            claves[size] = clave;
            valores[size] = valor;
            size++;
        }
    }

    public V obtenerValue(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }

        int pos = buscar(clave);
        if(pos==-1){
            throw new NoSuchElementException("La clave "+clave+" no esta añadida al diccionario");
        }

        return valores[pos];
    }

    public void eliminar(K clave){
        if(isEmpty()){
            throw new IllegalStateException("El diccionario esta vacio");
        }
        int pos = buscar(clave);
        if(pos==-1){
            throw new NoSuchElementException("La clave "+clave+" no esta añadida al diccionario");
        }

        System.arraycopy(claves, pos+1, claves, pos, size-pos-1);
        System.arraycopy(valores, pos+1, valores, pos, size-pos-1);
        claves[--size]=null;
        valores[size]=null;
    }

    private int buscar(K clave){
        int pos = 0;
        boolean found = false;
        while(pos<size && !found){
            if(claves[pos].equals(clave)){
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

    public boolean contieneClave(K clave){
        return buscar(clave)!=-1;
    }

    private void resize(int nuevaCapacidad){
        claves= Arrays.copyOf(claves, nuevaCapacidad);
        valores=Arrays.copyOf(valores, nuevaCapacidad);
    }

    private boolean isEmpty(){
        return size==0;
    }
}
