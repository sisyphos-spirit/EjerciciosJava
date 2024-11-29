package org.example;

import java.util.concurrent.Semaphore;

public class VIP extends Thread
{
    private Semaphore semaforo;
    public VIP(String nombre, Semaphore s)
    {
        super(nombre);
        this.semaforo = s;
    }

    public void run()
    {

    }
}
