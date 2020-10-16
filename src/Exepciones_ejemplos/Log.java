package Exepciones_ejemplos;
import java.io.IOException;
import java.util.logging.*;

public class Log {
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);//obtiene el logger global

    public static Logger IniciadorLog(String paquete,String archivo, Level nivel){

        try {
            FileHandler FH = new FileHandler(archivo, true);//archivo que muestra los errores
            logger = Logger.getLogger(paquete);
            logger.addHandler(FH);

            SimpleFormatter formatter = new SimpleFormatter();
            FH.setFormatter(formatter);
        }catch (IOException e) {
            logger = Logger.getGlobal();
            logger.log(Level.SEVERE,"Error con el inicio de el log",e);
            return logger;
        }
        logger.setLevel(nivel);
        return logger;//devuelve un objeto para guardar los logs en un archivo
    }
}
/*
   Niveles para el logger
   SEVERE
   WARNING
   INFO
   CONFIG
   FINE
   FINER
   FINEST
*/