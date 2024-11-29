package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args)
    {
        // Primero creo el semáforo, el CountDownLatch y la barrera
        Semaphore semaforo = new Semaphore(3);
        CountDownLatch cdl = new CountDownLatch(9);
        CyclicBarrier barreraEntrada = new CyclicBarrier(9);

        // Creo los objetos Cliente y los voy lanzando en el orden indicado
        for(int i=1; i<=2;i++)
        {
            System.out.println("Cliente " + i + " lanzado");
            new Cliente("Cliente " + i, semaforo, cdl, barreraEntrada).start();
        }

        System.out.println("Victoria lanzada");
        new VIP("Victoria Beckham", semaforo, cdl, barreraEntrada).start();
        
        for(int i=3; i<=6;i++)
        {
            System.out.println("Cliente " + i + " lanzado");
            new Cliente("Cliente " + i, semaforo, cdl, barreraEntrada).start();
        }

        System.out.println("David lanzado");
        new VIP("David Beckham", semaforo, cdl, barreraEntrada).start();

        for(int i=7; i<=8;i++)
        {
            System.out.println("Cliente " + i + " lanzado");
            new Cliente("Cliente " + i, semaforo, cdl, barreraEntrada).start();
        }

        // Una vez creados y lanzados los clientes, utilizo el CountDownLatch para cerrar la tienda cuando todos hayan terminado
        try
        {
            cdl.await();
        }catch (Exception ex)
        {
        }
        System.out.println("TIENDA CERRADA");

    }
}