package org.example;

public class Semaforo
{
    public synchronized void esperar() throws InterruptedException {
        wait();
    }

    public synchronized void notificar() throws InterruptedException {
        notifyAll();
    }
}
