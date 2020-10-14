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
                prueba.close();
                ports=port;
                break;
            } catch (UnknownHostException e) {
                System.err.println("No se conoce host");
            }catch (IOException e) {
                System.out.println(e);
            }finally {
                System.out.println("Se escaneo correctamente el puerto "+ ports);
            }
        }
        return ports;
    }
}
