package Aula6_16_10_2023;


import java.util.Iterator;

public class MinhaLista<T> implements Iterable<T> {
    class Nodo<D>{
        public D dado;
        public Nodo<D> prox;

        public Nodo(D dado){
            this.dado = dado;
            this.prox = null;
        }
    }

    class Iterador<I> implements Iterator<I>{
        private Nodo<I> cursor;


        /* 
        public Iterador(){
            cursor = (MinhaLista<T>.Nodo<I>) prim;
        }
        */
        
        @Override
        public boolean hasNext() {
            return cursor != null;
            /* 
            if (cursor != null) return true;
            else return false;
            */
        }

        @Override
        public I next() {
            I dado = cursor.dado;
            cursor = cursor.prox;
            return dado;
        }
    }
    
    private Nodo<T> prim;
    private Nodo<T> ult;

    public MinhaLista(){
        prim = null;
        ult = null;
    }

    public void add(T dado){
        Nodo<T> novo = new Nodo<T>(dado);
        if (prim == null){
            prim = novo;
            ult = novo;
        }else{
            ult.prox = novo;
            ult = novo;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterador<T>();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        Nodo<T> aux = prim;
        while(aux != null){
            str.append("["+aux.dado+"]");
            aux = aux.prox;
        }
        return str.toString();
    }
}
