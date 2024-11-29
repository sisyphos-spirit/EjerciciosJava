package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class Main {
    public static void main(String[] args)
    {
        // Primero reocojo el valor de los argumentos.
        int n;
        try
        {
            n = Integer.parseInt(args[0]);
        }catch(IndexOutOfBoundsException e) // Evito que salte una excepción si el usuario no introduce ningún argumento.
        {
            n = 0;
            System.out.println("No se ha introducido ningún argumento, se esperaba un entero de valor positivo. Cerrando el programa...");
            System.exit(-1);
        }catch(NumberFormatException e) // Me aseguro de que el argumento sea un valor entero.
        {
            n = 0;
            System.out.println("El valor introducido es incorrecto, se esperaba un entero de valor positivo. Cerrando el programa...");
            System.exit(-1);
        }
        // También me aseguro de que el valor sea positivo.
        if (n < 0)
        {
            n = 0;
            System.out.println("El valor introducido es incorrecto, se esperaba un entero de valor positivo. Cerrando el programa...");
            System.exit(-1);
        }

        // Guardo en un array las letras del abecedario.
        char[] letras = new char[26];
        for (int i = 0; i < letras.length; i++)
        {
            letras[i] = (char) ('a' + i);
        }

        // Genero tantas palabras como quiera el usuario, usando letras aleatorias y una longitud de entre 5 y 15.
        int longitud;
        String palabra;
        try
        {
            RandomAccessFile raf = new RandomAccessFile("Buffer.txt", "rwd");
            FileLock bloqueo = null;

            for (int i = 0; i < n; i++)
            {
                palabra = "";
                longitud = (int)(Math.random()*11)+5;
                for (int j = 0; j < longitud; j++)
                {
                    palabra += letras[(int)(Math.random()*26)];
                }

                bloqueo = raf.getChannel().lock();
                raf.seek(raf.length());
                raf.writeBytes("Proceso " + args[0] + ": " + palabra + "\n");
                bloqueo.release();
                Thread.sleep(100);

            }

        }catch (Exception e)
        {
            System.out.println("Se ha producido un error, cerrando el programa");
            System.exit(-1);
        }

    }
}