package grafics;

import  javax.swing.*;

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
        setSize(500,300);
    }
}