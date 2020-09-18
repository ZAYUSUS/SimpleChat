package grafics;

import java.awt.event.*;

import java.awt.*;
import javax.naming.ldap.UnsolicitedNotification;

import  javax.swing.*;//importa la biblioteca grafica

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
    //--------------------COLORES---------------------------
    public Color azulito = new Color(28,232,175);//color de fondo
    public Color amarillo = new Color(219,190,22);
    public  Color rosado = new Color(219,22,154);
    //---------------------------------------------------------
    public JPanel paper;
    public myWindow(){
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize=screen.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenHeight/2,screenWidth/4);
        setTitle("Mensager");
        setLocation(screenHeight/4,screenWidth/6);
        setBackground(Color.PINK);
        setResizable(false);

        setVisible(true);// vuelve la ventana visible

        Inicializador();

    }
    private void Inicializador(){
        PaperCreator();
        ComponentesEtiquetas();
        Botones();
    }
    private void PaperCreator(){//Clase que dibuja en la ventana funciona para introducir imagenes,texto, botones...
        paper = new JPanel();
        paper.setLayout(null);//desactiva el diseño de el layout
        paper.setBackground(azulito);
        this.getContentPane().add(paper);
    }
    private void ComponentesEtiquetas(){

        //-----------------------------------------------------------------------
        JLabel etiqueta = new JLabel("Mensajería",SwingConstants.LEFT);//etiqueta del titulo
        etiqueta.setBounds(0,0,400,50);
        etiqueta.setOpaque(true);//activa la opcion de editar el color de la etiqueta
        etiqueta.setBackground(amarillo);
        etiqueta.setFont(new Font("Century Gothic",Font.PLAIN,45));
        paper.add(etiqueta);//etiqueta se agrega al panel
    }
    private void Botones(){
        JButton enviar = new JButton("Enviar");
        enviar.setBounds(20,400,100,30);
        enviar.setBackground(rosado);//Agrega color al boton
        enviar.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));
        paper.add(enviar);//Añade el boton a la ventana
    }
}

