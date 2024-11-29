package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {
        for (int i = 10; i <= 100; i+=10)
            {
                try
                {
                    Process process = Runtime.getRuntime().exec("java -jar Proceso_jmmr-1.0-SNAPSHOT.jar " + i);
                    System.out.println("Proceso " + i + " creado");
                }catch (IOException e)
                {
                    System.out.println("Se ha producido un error, cerrando el programa");
                    System.exit(-1);
                }
            }
    }
}