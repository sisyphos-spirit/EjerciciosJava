package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        // Primero creo el buffer y un semaforo con el que se comunicarán los hilos
        int[] buffer = new int[3];
        Semaforo semaforo = new Semaforo();
        Scanner sc = new Scanner(System.in);
        int i;
        int j;

        // Si el valor introducido por el usuario no es numérico, asigno manualmente el número a 1
        System.out.println("Indique la cantidad de productores que desea crear");
        try
        {
            i = sc.nextInt();
        } catch (InputMismatchException e)
        {
            i = 1;
            sc.nextLine();
        }

        System.out.println("Indique la cantidad de consumidores que desea crear");
        try
        {
            j = sc.nextInt();
        } catch (InputMismatchException e)
        {
            j = 1;
        }

        // Sincronizo el buffer y ejecuto todos los hilos
        synchronized (buffer)
        {
            for (int k = 0; k < i; k++)
            {
                new Productor("Productor" + (k+1), buffer, semaforo).start();
            }

            for (int k = 0; k < j; k++)
            {
                new Consumidor("Consumidor" + (k+1), buffer, semaforo).start();
            }
        }
    }
}