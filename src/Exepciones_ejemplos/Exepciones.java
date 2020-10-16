package Exepciones_ejemplos;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;




/*
 *Esta clase se agrego ya que los ejemplos del codigo de la tarea 1 son muy parecidos
 * por eso opte por crear algunos ejemplos extra
 */







public class Exepciones {

    public static void main(String[] args) throws nuevaExepcion{
        Errores problema = new Errores();
        problema.Calculo();
        problema.Personalizadas();
        problema.ErrorArchivo();
    }
}

class Errores{
    static Logger control_errores = Log.IniciadorLog("Exepciones_ejemplos","ejemplosexepciones.txt", Level.FINE);//log del programa
    int num=1;
    int num2=0;
    public void Calculo(){//primero ejemplo, manejo por try catch y ejemplo de exepción creada
        try{
            int operacion = num/num2;
            System.out.println("Su operación da: "+ operacion);
        }catch (ArithmeticException e){
            control_errores.log(Level.SEVERE,"Surgió un error aritmetico ", e );
        }catch (Exception e){
            control_errores.info("Error, por favor revisar el código");
        }finally {
            System.out.println("Se completó la operación");
        }
    }
    public void Personalizadas(){//ejemplo de manejo por errores personalizados
        String Nombre = "Bryan";
        if(Nombre.length()>6) {
            System.out.println("Esto no puede pasar");
        }else{
            throw new StringIndexOutOfBoundsException("No se pudo encontrar un string de ese tamaño");
        }
    }
    public void ErrorArchivo() throws nuevaExepcion{//exepcion para archivos ejemplo con la exepcion creada
        try {
            FileWriter archivo = new FileWriter("arhivoquenoexiste.txt");

        }catch (FileSystemNotFoundException e){
            throw new nuevaExepcion("Esta execpcion ha sido creada",e);
        }catch (IOException e){
            control_errores.log(Level.SEVERE,"No se encontró el archivo",e);
        }
    }
}
class nuevaExepcion extends Exception{
    public nuevaExepcion(String mensaje,Throwable raiz){
        super("Esta exepcion ha sido creada",raiz);
    }
}
