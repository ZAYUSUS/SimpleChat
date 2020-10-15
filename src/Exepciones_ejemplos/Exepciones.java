package Exepciones_ejemplos;

import java.lang.invoke.StringConcatException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exepciones {

    public static void main(String[] args) {
        ErroresMatematicos problema = new ErroresMatematicos();
        problema.Calculo();
    }
}
class ErroresMatematicos{//primero ejemplo, manejo por try catch
    static Logger control_errores = Log.IniciadorLog("Exepciones_ejemplos","ejemplosexepciones.txt", Level.FINE);
    int num=1;
    int num2=0;
    public void Calculo(){
        try{
            int operacion = num/num2;
            System.out.println("Su operación da: "+ operacion);
        }catch (ArithmeticException e){
            control_errores.log(Level.SEVERE,"Surgió un error aritmetico ", e );
        }catch (RuntimeException e){
            control_errores.info("Error, por favor revisar el código");
        }finally {
            System.out.println("Se completó la operación");
        }

    }
}
