package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        // Creo un objeto scanner y un ArrayList para leer y almacenar los números introducidos por el usuario.
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numeros = new ArrayList<>();
        while (scanner.hasNext()) // Esta condición hace que el programa pueda funcionar correctamente en tuberia con el realizado anteriormente.
        {
            try
            {
                numeros.add(scanner.nextInt());
            }catch(InputMismatchException e) // Si el usuario escribe algo distinto a un número, recojo la excepción y comienzo a ordenar los números.
            {
                break;
            }
        }

        //Este algoritmo va comparando los números y fijando los menores al principio del ArrayList, hasta que quedan todos ordenados.
        int aux;
        for(int i = 1; i <= numeros.indexOf(numeros.getLast())+1; i++)
        {
            for(int j = 0; j < numeros.indexOf(numeros.getLast())+1 - i; j++)
            {
                if(numeros.get(j) > numeros.get(j+1))
                {
                    aux = numeros.get(j);
                    numeros.set(j, numeros.get(j+1));
                    numeros.set(j+1, aux);
                }
            }
        }

        // Finalmente imprimo por pantalla los números ordenados.
        for (int i = 0; i < numeros.indexOf(numeros.getLast())+1; i++)
        {
           System.out.println(numeros.get(i));
        }
    }
}