package Exepciones_ejemplos;

public class Exepciones {
    public static void main(String[] args) {
        ErroresMatematicos problema = new ErroresMatematicos();
        problema.Calculo();
    }

}
class ErroresMatematicos{//primero ejemplo, manejo por try catch
    int num=1;
    int num2=0;
    public void Calculo(){
        try{
            int operacion = num/num2;
            System.out.println("Su operación da: "+ operacion);
        }catch (ArithmeticException e){
            System.out.println("Surgió un error aritmetico "+ e );
        }catch (RuntimeException e){
            System.out.println("Error, por favor revisar el código");
        }finally {
            System.out.println("Se completó la operación");
        }

    }
}