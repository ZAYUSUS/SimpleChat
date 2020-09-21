package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Escanner {
    public static void main(String[] args) {
        Escanner buscador = new Escanner();
        buscador.EscannerPuertos();
    }
    public int EscannerPuertos() {
        int ports=1;
        for (int port = 1; port < 1024; port++){
            try {
                ServerSocket prueba = new ServerSocket(port);
                System.out.println(port + "\n");
                prueba.close();
                ports=port;
                //System.out.println("Esto es ports"+ports);
            } catch (UnknownHostException e) {
                System.err.println("No se conoce host");
            }catch (IOException e) { }

        }
        return ports;
    }
}
