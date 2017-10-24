
package EstructurasDeDatos.lineales;

import EstructurasDeDatos.modelos.TADPila;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public class ArrayPila<E> implements TADPila<E> {

    
    protected E Array[];
    protected int tope;
    protected static final int TAMANIO_ARRAY=200;

    public ArrayPila()
    {
        Array=(E[]) new Object[TAMANIO_ARRAY];
        tope=-1;
    }
    
    private void duplicarArray()
    {
       E nuevoArray[]=(E[]) new Object[Array.length*2];
       for (int i=0; i<=tope; i++)
       {
           nuevoArray[i]=Array[i];
           Array=nuevoArray;
       }
    }
    
    @Override
    public void apilar(E elemento) {
        if(tope+1 == Array.length) duplicarArray();
        tope++;
        Array[tope]=elemento;
    }

    @Override
    public E desapilar() {
        E ultimo = Array[tope];
        tope--;
        return ultimo;
    }

    @Override
    public E tope() {
        return Array[tope];
    }

    @Override
    public boolean esVacia() {
        if(tope==-1) return true;
        else return false;
    }
    
    public String toString(){
        String res="\t\t";
        for(int i=tope; i>=0;i--){
            res+=Array[i]+"\n\t\t";
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
    @Override
    public void replicarElemento(int elemento) {
         if(!this.esVacia())
        {
            E dato;
            dato=this.desapilar();
            replicarElemento(elemento);
            if(dato.equals(elemento))
            {

                for(int i=1; i<elemento;i++)//Tenemos que tener en cuenta que se va a apilar el dato posteriormente, por lo que hay que ponerle una menos en el for.
                {
                    this.apilar(dato);
                }
            }
            this.apilar(dato);
        }
    }
    
    
    
    
}
