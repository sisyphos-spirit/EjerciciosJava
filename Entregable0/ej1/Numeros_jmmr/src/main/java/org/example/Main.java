package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        // Primero reocojo el valor de los argumentos.
        int n;
        try
        {
            n = Integer.parseInt(args[0]);
        }catch (NumberFormatException e) // Me aseguro de que el argumento sea un valor entero.
        {
            n = 0;
            System.out.println("El valor introducido es incorrecto, se esperaba un entero de valor positivo. Cerrando el programa...");
            System.exit(-1);
        }catch (IndexOutOfBoundsException e) // Evito que salte una excepción si el usuario no introduce ningún argumento.
        {
            n = 0;
            System.out.println("No se ha introducido ningún argumento, se esperaba un entero de valor positivo. Cerrando el programa...");
            System.exit(-1);
        }
        // También me aseguro de que el valor sea positivo.
        if (n < 0)
        {
            n = 0;
            System.out.println("El valor introducido es incorrecto, se esperaba un entero de valor positivo. Cerrando el programa...");
            System.exit(-1);
        }

        // Creo un ArrayList que contendrá todos los primos.
        ArrayList<Integer> primos = new ArrayList<>();
        for (int i = 2; i < 998; i++) // Como solo busco los primos menores a mil, recorro del menor al mayor (2 y 997).
        {
            for (int j = 2; j <= i; j++) // Para cada número compruebo el resto de sus divisiones con todos los menores.
            {
                if (i % j == 0) // Si el número es divisible entre otro distinto, este no será primo.
                {
                    if (i != j)
                    {
                        break;
                    }else // Si i y j son iguales, es que el número no es divisible entre ninguno que no sea él mismo (además del 1), por lo que es primo.
                    {
                        primos.add(i);
                    }
                }
            }
        }

        //Para tantas veces como el usuario haya pedido, se imprime un número aleatorio de la lista de primos.
        for (int i = 0; i < n; i++)
        {
            System.out.println(primos.get((int)(Math.random()*primos.indexOf(primos.getLast()))));
        }
    }
}