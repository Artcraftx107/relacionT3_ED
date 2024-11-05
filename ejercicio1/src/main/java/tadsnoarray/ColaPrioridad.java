package tadsnoarray;

public class ColaPrioridad<E> {
    //Clase interna que actua como Nodo para almacenar dato y prioridad
    private static class Nodo<E>{
        E dato;
        int prioridad;
        Nodo<E> siguiente;

        Nodo(E dato, int prioridad){
            this.dato=dato;
            this.prioridad=prioridad;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;

    //Inicializa una cola vacia
    public ColaPrioridad(){
        head=null;
    }

    //Inserta un elemento en una posicion dependiente de la propiedad
    public void insertar(E item, int prioridad){
        Nodo<E> nuevoNodo = new Nodo<>(item, prioridad);

        if(isEmpty() || head.prioridad>prioridad){
            //Inserta al inicio si esta vacia o si tiene la mayor prioridad
            nuevoNodo.siguiente=head;
            head=nuevoNodo;
        }else{
            //Busca posicion adecuada dependiendo de la prioridad 
            Nodo<E> actual = head;
            while(actual.siguiente!=null && actual.siguiente.prioridad<=prioridad){
                actual=actual.siguiente;
            }
            nuevoNodo.siguiente=actual.siguiente;
            actual.siguiente=nuevoNodo;
        }
    }

    //Borra el elemento con mayor prioridad
    public E quitarMayorPrioridad(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        E item = head.dato;
        head=head.siguiente;
        return item;
    }

    //Devuelve el elemento con mayor prioridad sin borrarlo
    public E verMayorPrioridad(){
        if(isEmpty()){
            throw new IllegalStateException("La cola esta vacia");
        }

        return head.dato;
    }

    //Metodo que comprueba si la cola esta vacia
    private boolean isEmpty(){
        return head==null;
    }
}
