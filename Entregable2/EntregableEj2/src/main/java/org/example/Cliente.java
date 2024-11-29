package org.example;

import java.util.concurrent.Semaphore;

public class Cliente extends Thread
{
    private Semaphore semaforo;
    public Cliente(String nombre, Semaphore s)
    {
        super(nombre);
        this.semaforo = s;
    }

    public void run()
    {

    }
}
