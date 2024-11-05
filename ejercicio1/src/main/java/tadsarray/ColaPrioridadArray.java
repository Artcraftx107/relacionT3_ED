package tadsarray;

public class ColaPrioridadArray<E> {
    private static final int INITIAL_CAPACITY = 10;
    private ElementoConPrioridades<E>[] elementoConPrioridades;
    private int size;

    private static class ElementoConPrioridades<E>{
        E dato;
        int prioridad;

        ElementoConPrioridades(E dato, int prioridad){
            this.dato=dato;
            this.prioridad=prioridad;
        }
    }

    public ColaPrioridadArray(){
        elementoConPrioridades= (ElementoConPrioridades<E>[]) new ElementoConPrioridades[INITIAL_CAPACITY];
        size=0;
    }

    //Metodo que inserta un nuevo elemento en una posicion dependiente de la prioridad de este
    public void insertar(E item, int prioridad){
        if(size== elementoConPrioridades.length){
            resize(elementoConPrioridades.length*2);
        }

        ElementoConPrioridades<E> nuevoElem = new ElementoConPrioridades<>(item, prioridad);

        int i = size-1;
        while(i>=0 && elementoConPrioridades[i].prioridad>prioridad){
            elementoConPrioridades[i+1]=elementoConPrioridades[i];
            i--;
        }

        elementoConPrioridades[i+1]=nuevoElem;
        size++;
    }

    //Metodo que elimina el elemento con mayor prioridad
    public E quitarMayorPrioridad(){
        //Comprobamos que la cola no este vacia, si lo esta, lanza una excepcion
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        //Cogemos el primer elemento (es el de mayor prioridad)
        E item = elementoConPrioridades[0].dato;

        //Desplazamos los elementos a la izquierda
        for(int i = 1; i<size; i++){
            //Aqui ya nos hemos cargado el de mayor prioridad con la primera iteracion
            elementoConPrioridades[i-1] = elementoConPrioridades[i];
        }
        //Limpiamos el ultimo elemento
        elementoConPrioridades[--size]=null;
        return item;
    }

    //Metodo que devuelve el elemento con mayor prioridad del array
    public E verMayorPrioridad(){
        //Comprobamos que la cola no este vacia, si lo esta, lanza una excepcion
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }
        //Devuelve el primer elemento del array ya que es el de mayor prioridad
        return elementoConPrioridades[0].dato;
    }

    //Metodo para redimensionar array
    private void resize(int nuevaCapacidad){
        //Creamos un nuevo array con la nueva capacidad
        ElementoConPrioridades<E>[] nuevoArray = (ElementoConPrioridades<E>[]) new ElementoConPrioridades[nuevaCapacidad];

        //Copiamos los elementos del array antiguo al nuevo
        System.arraycopy(elementoConPrioridades, 0, nuevoArray, 0, size);

        //Cambiamos el array antiguo por el nuevo de mayor capacidad
        elementoConPrioridades=nuevoArray;
    }

    //Metodo para ver si el array esta vacio o no
    private boolean isEmpty(){
        return size==0;
    }
}
