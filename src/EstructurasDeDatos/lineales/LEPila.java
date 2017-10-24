
package EstructurasDeDatos.lineales;

import EstructurasDeDatos.modelos.TADPila;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public class LEPila<E> implements TADPila<E> {
    
    private Nodo cima;
    private int talla=0;
    
    public class Nodo<E>{
        public E dato;
        public Nodo siguiente;
    }
    
    @Override
    public void apilar(E elemento) {
        Nodo<E> nuevo = new Nodo<E>();
        nuevo.dato=elemento;
        talla++;
        if (cima==null)
        {
            cima=nuevo;
        }
        else{
            nuevo.siguiente=cima;
            cima=nuevo;
        }
    }

    @Override
    public E desapilar() {
        if(cima==null) return null;
        else{
            E dato=(E) cima.dato;
            cima=cima.siguiente;
            return dato;
        }
    }

    @Override
    public E tope() {
        E dato=(E) cima.dato;
            return dato;
    }

    @Override
    public boolean esVacia() {
        if(cima==null) return true;
        else return false;
    }
    
    @Override
    public String toString()
    {
        String res="\t\t";
        Nodo<E> aux= new Nodo<E>();
        for(aux=cima; aux!=null; aux=aux.siguiente)
        {
            res+=aux.dato.toString()+"\n\t\t";
        }
        return res;    
    }
    
    /* En el metodo de replicar, introduciremos un elemento entero. A continuacion iremos desapilando la pila de forma recursiva
       es decir, desapilamos la pila, guardamos el valor en una variable y llamamos el metodo recursivo otra vez para que desapile otro
       elemento de la pila. Esto ocurrira hasta que la pila sea vacia siendo este evento el caso base del algoritmo recursivo.
       Una vez que hemos llegado al caso base, ahora nos encontramos en la fase de plegado. En esta fase de plegado, lo que haremos
       es ir apilando los elementos pero no de cualquier forma, sino que si el elemento que hemos introducido por parametro
        es igual al almacenado en la variable local del Stack, le repetiremos tantas veces como el numero que indica. Es decir
        Si el numero introducido es un 5, y encuentra un 5, apilara este 5 veces mediante un bucle for.
        Si el numero introducido por parametro no es igual al que se encuentra en la variable "dato" del Stack, entonces este "dato"
        se apilara una unica vez siguiendo el flujo normal de un algoritmo recursivo.
    */
    public void replicarElemento(int elemento)
    {
        if(!this.esVacia())
        {
            E dato;
            dato=this.desapilar();
            replicarElemento(elemento);
            if(dato.equals(elemento))
            {
                for(int i=1; i<elemento;i++)
                {
                    this.apilar(dato);
                }
            }
            this.apilar(dato);
        }
    }
    
}
