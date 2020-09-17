package grafics;

import java.awt.event.*;

import java.awt.*;
import javax.naming.ldap.UnsolicitedNotification;

import  javax.swing.*;//importa la biblioteca grafica
import java.awt.*;

public class fMenu {
    public static void main(String[] args ){
        myWindow marco1= new myWindow(); //crea la ventana

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

        Paper plank = new Paper();
        add(plank);

        setVisible(true);// vuelve la ventana visible

    }
}

class Paper extends JPanel{

    public Paper (){
        setLayout(new BorderLayout());

        JPanel cuadroBlanco = new JPanel();

        JMenuBar menu = new JMenuBar();

        JMenu enviar = new JMenu("Enviar");

        menu.add(enviar);

        cuadroBlanco.add(menu);

        add(cuadroBlanco,BorderLayout.SOUTH);
        //-------------------------------------------

        JTextPane body = new JTextPane();
        add(body,BorderLayout.CENTER);

    }
}