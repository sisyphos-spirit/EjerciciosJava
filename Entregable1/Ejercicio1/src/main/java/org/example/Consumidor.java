package org.example;

public class Consumidor extends Thread
{
    // Primero creo el buffer y un semaforo con el que se comunicarán los hilos
    int[] buffer = new int[3];
    private Semaforo semaforo;
    public Consumidor(String nombre, int[] buffer, Semaforo semaforo)
    {
        super(nombre);
        this.buffer = buffer;
        this.semaforo = semaforo;
    }

    public void run()
    {
        // Empiezo con un while(true) para que el hilo siga funcionando permanentemente
        while (true)
        {
            // Utilizo el bucle for para recorrer el buffer en busca de un número que poder consumir
            for (int i = 0; i < 3; i++)
            {
                if (buffer[i] != 0)
                {
                    // Cuando consume un número, lo escribe por la consola, se va a dormir, notifica al resto de hilos y finalmente se espera a que le vuelvan a llamar
                    System.out.println(Thread.currentThread().getName() + ": Número " + buffer[i] + " eliminado del espacio de buffer " + (i + 1));
                    buffer[i] = 0;
                    try
                    {
                        Thread.sleep(2000);
                        semaforo.notificar();
                        semaforo.esperar();
                    } catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
            // Si el buffer está completamente vacío, no entrará al if anterior, por lo que aquí me aseguro de evitar interbloqueos
            try
            {
                semaforo.notificar();
                semaforo.esperar();
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
