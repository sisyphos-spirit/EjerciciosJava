package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class VIP extends Thread
{
    // Primero creo el semáforo, el CountDownLatch y la barrera
    private Semaphore semaforo;
    CountDownLatch cdl;
    CyclicBarrier barrera;

    // En el constructor, aprovecho para asignar la prioridad de los hilos
    public VIP(String nombre, Semaphore s, CountDownLatch cdl, CyclicBarrier barrera)
    {
        super(nombre);
        this.semaforo = s;
        this.cdl = cdl;
        this.barrera = barrera;
        this.setPriority(Thread.MAX_PRIORITY);
    }

    public void run()
    {
        // Los clientes avisan en cuanto llegan a la tienda y se esperan a la barrera para poder entrar
        System.out.println(Thread.currentThread().getName() + ": He llegado a la tienda");

        // Los clientes VIP utilizan el semáforo para entrar a la tienda y ocupan los 3 espacios disponibles
        try
        {
            barrera.await();
            semaforo.acquire(3);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("--> " + Thread.currentThread().getName() + ": Entrando a la tienda");

        try
        {
            sleep(3000);
        } catch (InterruptedException e)
        {
            System.out.println(e);
        }

        // Cuando terminan, los clientes usan el semáforo para liberar el espacio en la tienda
        System.out.println("<-- " + Thread.currentThread().getName() + ": Saliendo de la tienda");
        semaforo.release(3);
        cdl.countDown();
    }
}
