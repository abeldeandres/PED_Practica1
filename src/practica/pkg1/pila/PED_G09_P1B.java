
package practica.pkg1.pila;

import EstructurasDeDatos.lineales.LEPila;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public class PED_G09_P1B {

    /**
     * @param args the command line arguments
     */
    public static Scanner ent = null;

    public static int leerInt() {//Lectura de número por teclado, con la implementación de una excepción en caso de introducir un caracter que no se corresponda con un número.

        String strNumero = "";
        int numero = 0;
        boolean lecturaBien = false;
        do {
            ent = new Scanner(System.in);
            System.out.print("> ");
            strNumero = ent.nextLine();

            try {
                numero = Integer.parseInt(strNumero);
                lecturaBien = true;
            } catch (Exception e) {
            }

        } while (!lecturaBien);//Se repetirá la petición de strNumero mientras lecturaBien tenga el valor booleano false, es decir, no se corresponda con un entero.

        return numero;
    }
    //Creamos la lanzadera de nuestro metodo recursivo, metiendole unicamente una pila. La pila funcionara con cualquier
    //objeto cuya clase de referencia haya implementado o tenga implementado el metodo compareTo mediante la interfaz Comparable.
    public static <E extends Comparable<E>> void elementoMenor(LEPila<E> pila) {
        elementoMenorRec(pila, pila.tope());
    }
    //A continuacion llamamos al propio metodo recursivo cuyos parametros van a ser la pila y el elemento minimo de esta.
    //Al principio, el elemento minimo sera el tope del array, pero ira variando a lo largo del metodo recursivo.
    public static <E extends Comparable<E>> E elementoMenorRec(LEPila<E> pila, E minimo) {
        E minimoAbsoluto;
        //Si el array esta vacio, entonces apilaremos el minimo y le devolveremos mediante un return
        //Esto solamente ocurrira cuando la pila se encuentre vacia.
        //Nos encontramos por lo tanto en el caso base del metodo recursivo.
        if (pila.esVacia()) {
            pila.apilar(minimo);
            return minimo;
        //Mientras la pila no este vacia, iremos desapilando y almacenando el elemento en una variable local llamada "dato"
        } else {
            E dato = pila.desapilar();
            //Si el dato desapilado es mayor que minimo, entonces realizaremos la llamada recursiva utilizando minimo, por el contrario
            //el segundo parametro del metodo recursivo sera el dato.
            if (minimo.compareTo(dato) == 1) {
                minimo = dato;
            }
            //Ahora nos encontramos en la fase de despleagado.
            //Una vez que hayamos desapilado el elemento, llamamos de nuevo al metodo recursivo, y cuando la pila este vacia, 
            //nos devolvera el minimo, este minimo se almacenara en la variable "minimoAbsoluto". Este minimo sera el absoluto.
            minimoAbsoluto = elementoMenorRec(pila, minimo);
            //Una vez que tenemos el minimo absoluto, significa que ya hemos llegado al caso base, y por lo tanto, ahora nos encontraremos
            //en la fase de plegado.
            //En esta fase iremos apilando siempre y cuando el dato desapilado y guardado en el Stack sea diferente del minimo absoluto, si ambos 
            //son iguales, entonces no apilaremos.
            if (dato.compareTo(minimoAbsoluto) != 0) {
                pila.apilar(dato);
            }
            //Este return no tiene gran funcionalidad, pero es necesario ya que nuestro metodo es una funcion y no un procedimiento.
            //Por lo tanto podria devolver el minimo como hacemos, pero tambien podria devolver un 0, como nosotros no vamos a guardar el resultado
            //de esta funcion en otra variable, no es necesario que devuelva un valor concreto.
            return minimoAbsoluto;
        }
    }

    public static void menu() {
        System.out.println("\t\tMENÚ");
        System.out.println("1.-Crear una pila de enteros(vacía)");
        System.out.println("2.-Almacenar elementos en la pila");
        System.out.println("3.-Mostrar Pila en Pantalla");
        System.out.println("4.-El menor al fondo de la pila");
        System.out.println("5.-Replicar elemento");
        System.out.println("0.-Salir");
        System.out.println("Elija Opción:");
    }

    public static void main(String[] args) throws IOException {
        int opcion = 0;
        LEPila<Integer> LEpila = null;
        boolean salir = false;
        boolean pilaCreada = false;
        boolean pilaConContenido = false;
        do {
            menu();
            opcion = leerInt();

            switch (opcion) {

                case 1:

                    LEpila = new LEPila<Integer>();
                    System.out.println("Pila vacía creada.");
                    System.out.println("Pulsa <Intro> para continuar...");
                    System.in.read();
                    pilaCreada = true;
                    break;
                    
                    //En el case 2 lo que vamos a hacer es utilizar unas variables llamadas "flags" para saber si hemos
                    //usado la opcion 1 del menu, de ser asi, podremos utilizar la opcion 2.
                    //Ademas hemos utilizado un do... while en el que nos pedira que introduzcamos numeros hasta que el numero introducido
                    //es un -1, en ese caso, sale del bucle y termina la opcion (el case).
                    //Cuando introducimos un numero, si este no es un -1, lo apilaremos en la pila.
                    //Despues mostraremos la pila si esta no se encuentra vacia.
                case 2:

                    if (!pilaCreada) {
                        System.out.println("\nLo sentimos, ha de crear la pila previamente (Opción 1 del menú).\n");
                    } else {
                        boolean terminar = false;
                        do {
                            System.out.println("Introduzca números enteros: ");
                            int numero = leerInt();
                            if (numero == -1) {
                                terminar = true;
                            } else {
                                LEpila.apilar(new Integer(numero));
                            }

                        } while (!terminar);

                        if (LEpila.esVacia()) {
                            System.out.println("\nPila vacía\n");
                        } else {
                            System.out.println("\nLa pila contiene la siguiente información:\n");
                            System.out.println(LEpila.toString());
                            pilaConContenido = true;
                        }

                    }

                    break;
                    
                    //En el case 3, mostraremos la pila siempre y cuando hayamos elegido la opcion 1 del menu, sino podrian ocurrir
                    //errores, ya que si la pila no ha sido creada, no podria mostrarse. Si la pila ha sido creada y esta no esta vacia, la mostrara.
                    //En el caso contrario mostrara un mensaje.
                case 3:

                    if (!pilaCreada) {
                        System.out.println("\nLo sentimos, la Pila no está creada, ha de crearla previamente (Opción 1 del menú).\n");
                    } else {

                        if (!LEpila.esVacia()) {
                            System.out.println("\nLa pila contiene la siguiente información:\n");
                            System.out.println(LEpila.toString());
                        } else {
                            System.out.println("\nPila vacía.\n");
                            System.out.println("\nLo sentimos, la Pila está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;
                    
                     //En este metodo, usamos otra variable "flag" o bandera, en la que si hemos utilizado la opcion 2 se activara y permitira
                    //usar el metodo del menor al fondo. En caso contrario, mostrara un mensaje.
                    //Por otro lado, si la pila no esta vacia, mostrara los elementos en el estado inicial y en el estado posterior a utilizar
                    //el metodo del menor al fondo.
                case 4:

                    if (!pilaConContenido) {
                        System.out.println("\nLo sentimos, la Pila está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                    } else {

                        if (!LEpila.esVacia()) {
                            System.out.println("\nEstado inicial de la pila: \n");
                            System.out.println(LEpila.toString());
                            elementoMenor(LEpila);
                            System.out.println("Estado final de la pila: \n");
                            System.out.println(LEpila.toString());
                        } else {
                            System.out.println("\nLo sentimos, la Pila está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;
                    
                        //En este metodo, usamos otra variable "flag" o bandera, en la que si hemos utilizado la opcion 2 se activara y permitira
                        //usar el metodo de replicar elemento. En caso contrario, mostrara un mensaje.
                        //Por otro lado, si la pila no esta vacia, mostrara los elementos en el estado inicial y en el estado posterior a utilizar
                        //el metodo del menor al fondo.
                case 5:

                    if (!pilaConContenido) {
                        System.out.println("\nLo sentimos, la Pila está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                    } else {
                        if (!LEpila.esVacia()) {
                            System.out.println("\nPila Inicial: \n");
                            System.out.println(LEpila.toString());
                            System.out.println("\nIntroduzca un número entero: \t");
                            int entero = leerInt();
                            LEpila.replicarElemento(entero);
                            System.out.println("\nEstado final de la pila: \n");
                            System.out.println(LEpila.toString());
                        } else {
                            System.out.println("\nLo sentimos, la Pila está vacía, por favor, rellenela de elementos previamente (Opción 2 del menú).\n");
                        }
                    }

                    break;

                case 0:

                    System.out.println("\n\t\t\t\tGracias por utilizar nuestro TAD Pila\n");
                    System.out.println("\n\t\t\t\t\tIván Barbado & Abel de Andrés\n");
                    salir = true;
                    break;

                default:
                    System.out.println("\nPor favor, introduzca una opcion correcta, del 0 al 5, ambos inclusive y según corresponda.");

                    break;
            }

        } while (!salir);
    }
}
