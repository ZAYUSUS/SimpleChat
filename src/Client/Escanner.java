package Client;

import Exepciones_ejemplos.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Escanner {
    static Logger bitacora = Log.IniciadorLog("Client.Escanner","Bitacora.txt",Level.FINE);
    public static void main(String[] args) {
        Escanner buscador = new Escanner();
        buscador.EscannerPuertos();
    }
    public int EscannerPuertos(){
        int ports=1;
        for (int port = 1; port < 1024; port++){
            try {
                ServerSocket prueba = new ServerSocket(port);
                prueba.close();
                ports=port;
                break;
            } catch (UnknownHostException e) {
                bitacora.info(">>>No se conoce host "+e);
            }catch (IOException e) {
                bitacora.info(">>>Error en un puerto "+ e);
            }finally {
                System.out.println("Se escaneo correctamente el puerto "+ ports);
            }
        }
        return ports;
    }
}
