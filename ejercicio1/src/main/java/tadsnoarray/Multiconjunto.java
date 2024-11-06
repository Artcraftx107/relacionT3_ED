package tadsnoarray;

public class Multiconjunto<E> {
    //Clase interna que funciona como Nodo
    private static class Nodo<E>{
        E dato;
        int cont;
        Nodo<E> siguiente;

        Nodo(E dato){
            this.dato=dato;
            this.cont=1;
            this.siguiente=null;
        }
    }

    private Nodo<E> head;
    private int size;

    public Multiconjunto(){
        head=null;
        size=0;
    }

    public void agregar(E item){
        if(contiene(item)){
            //Incrementa cont si el elemento ya estaba
            Nodo<E> actual = head;
            boolean found = false;
            while(actual!=null && !found){
                if(actual.dato.equals(item)){
                    actual.cont++;
                    found=true;
                }
                actual=actual.siguiente;
            }
        }else{
            //Agrega nuevo nodo si elemento inexistente
            Nodo<E> nuevoNodo = new Nodo<>(item);
            nuevoNodo.siguiente = head;
            head=nuevoNodo;
            size++;
        }
    }

    public void quitar(E item){
        if(isEmpty()){
            throw new IllegalStateException("El multiconjunto esta vacio");
        }

        if(head.dato.equals(item)){
            if(head.cont>1){
                head.cont--;
            }else{
                head=head.siguiente;
                size--;
            }
        }else{
            Nodo<E> actual = head;
            boolean found = false;
            while(actual!=null && !found){
                if(actual.dato.equals(item)){
                    if(actual.cont>1){
                        actual.cont--;
                    }else{
                        actual=actual.siguiente;
                        size--;
                    }
                    found=true;
                }
                actual=actual.siguiente;
            }
        }
    }

    public boolean contiene(E item){
        Nodo<E> actual = head;
        boolean found = false;
        while(actual!=null && !found){
            if(actual.dato.equals(item)){
                found=true;
            }
            actual=actual.siguiente;
        }
        return found;
    }

    public int contador(E item){
        Nodo<E> actual = head;
        boolean found = false;
        int contAux = 0;
        while(actual!=null && !found){
            if(actual.dato.equals(item)){
                contAux=actual.cont;
            }
            actual=actual.siguiente;
        }
        return contAux;
    }

    private boolean isEmpty(){
        return size==0;
    }
}
