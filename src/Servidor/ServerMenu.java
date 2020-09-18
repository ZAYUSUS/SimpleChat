package Servidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMenu {
    public static void main(String[] args ){
        myWindow2 marco1= new myWindow2(); //crea la ventana
        marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//hace que la ventana se cierre

    }
}
/*
 *Se crea la clase de la ventana que hereda
 * de la calse JFrame
 */
class myWindow2 extends JFrame implements Runnable{
    //--------------------COLORES---------------------------
    public Color azulito = new Color(28,232,175);//color de fondo
    public Color amarillo = new Color(219,190,22);
    public  Color rosado = new Color(219,22,154);
    public  Color verde = new Color(14,139,143);
    //---------------------------------------------------------
    public JPanel paper;
    public JTextArea caja;
    public myWindow2(){
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

        Thread Hilo = new Thread(this);
        Hilo.start();

        Inicializador();

    }
    private void Inicializador(){
        PaperCreator();
        ComponentesEtiquetas();
        Botones();
        EntradasTexto();
    }
    private void PaperCreator(){//Clase que dibuja en la ventana funciona para introducir imagenes,texto, botones...
        paper = new JPanel();
        paper.setLayout(null);//desactiva el diseño de el layout
        paper.setBackground(azulito);
        this.getContentPane().add(paper);
    }
    private void ComponentesEtiquetas(){

        //-----------------------------------------------------------------------
        JLabel etiqueta = new JLabel("Servidor",SwingConstants.LEFT);//etiqueta del titulo
        etiqueta.setBounds(0,0,400,50);
        etiqueta.setOpaque(true);//activa la opcion de editar el color de la etiqueta
        etiqueta.setBackground(amarillo);
        etiqueta.setFont(new Font("Century Gothic",Font.PLAIN,45));
        paper.add(etiqueta);//etiqueta se agrega al panel
    }
    private void Botones(){
        JButton enviar = new JButton("Enviar");
        enviar.setBounds(20,350,100,30);
        enviar.setBackground(rosado);//Agrega color al boton
        enviar.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));
        paper.add(enviar);//Añade el boton a la ventana

        ActionListener AccionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //codigo que se ejecuta al presionar el boton
                System.out.println(caja.getText());
            }
        };
        enviar.addActionListener(AccionBoton);
    }
    private void EntradasTexto(){//metodo que crea las entradas de texto
        caja = new JTextArea();
        //setText(String)
        //.append
        caja.setBounds(0,60,500,200);
        caja.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));
        caja.setBackground(verde);
        paper.add(caja);

    }

    @Override
    public void run() {
        try {
            ServerSocket Escuchar = new ServerSocket(9933);

            while (true) {
                Socket conector = Escuchar.accept();

                DataInputStream Entradas = new DataInputStream(conector.getInputStream());

                String mensaje = Entradas.readUTF();

                caja.append(mensaje + '\n');

                conector.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
