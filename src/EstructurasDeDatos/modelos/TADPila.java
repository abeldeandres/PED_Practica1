
package EstructurasDeDatos.modelos;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public interface TADPila<E> {
   public void apilar(E elemento);
   public E desapilar();
   public E tope();
   public boolean esVacia();
   public String toString();
   public void replicarElemento(int elemento);
}
