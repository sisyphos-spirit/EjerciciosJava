package org.example;

public class Productor extends Thread
{
    // Primero creo el buffer y un semaforo con el que se comunicarán los hilos
    int[] buffer = new int[3];
    private Semaforo semaforo;
    public Productor(String nombre, int[] buffer, Semaforo semaforo)
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
            // Utilizo el bucle for para recorrer el buffer en busca de un espacio para insertar un número
            for (int i = 0; i < 3; i++)
            {
                if (buffer[i] == 0)
                {
                    // Cuando inserta un número, lo escribe por la consola, se va a dormir, notifica al resto de hilos y finalmente se espera a que le vuelvan a llamar
                    buffer[i] = (int) ((Math.random()) * 100 + 1);
                    System.out.println(Thread.currentThread().getName() + ": Número " + buffer[i] + " insertado en el espacio de buffer " + (i + 1));
                    try
                    {
                        Thread.sleep(500);
                        semaforo.notificar();
                        semaforo.esperar();
                    } catch (InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
            // Si el buffer está completamente lleno, no entrará al if anterior, por lo que aquí me aseguro de evitar interbloqueos
            try
            {
                Thread.sleep(500);
                semaforo.notificar();
                semaforo.esperar();
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
