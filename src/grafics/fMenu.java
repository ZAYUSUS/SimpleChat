package grafics;

import javax.naming.ldap.UnsolicitedNotification;
import  javax.swing.*;//importa la biblioteca grafica
import java.awt.*;

public class fMenu {
    public static void main(String[] args ){
        myWindow marco1= new myWindow(); //crea la ventana
        marco1.setVisible(true);// vuelve la ventana visible
        marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//hace que la ventana se cierre

    }
}
/*
*Se crea la clase de la ventana que hereda
* de la calse JFrame
 */
class myWindow extends JFrame{
    public myWindow(){
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenHeight/2,screenWidth/4);
        setTitle("Mensager");
        setLocation(screenHeight/4,screenWidth/6);
        setResizable(false);
    }
}

class box extends JPanel{

}