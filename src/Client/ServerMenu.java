package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ServerMenu {
    public static void main(String[] args ){
        myWindow2 marco1= new myWindow2(); //crea la ventana
        marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//hace que la ventana se cierre\
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
    public JLabel localizacion;

    public myWindow2(){

        setSize(700,600);
        setTitle("Mensager");
        setLocation(800,300);
        setBackground(Color.PINK);
        setResizable(false);

        setVisible(true);// vuelve la ventana visible
        Inicializador();

    }
    public void Inicializador(){
        PaperCreator();
        ComponentesEtiquetas();
        Botones();
        EntradasTexto();
        Thread Hilo = new Thread(this);
        Hilo.start();
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

        localizacion = new JLabel();
        localizacion.setBounds(470,20,60,50);
        paper.add(localizacion);

        JLabel indicadorLocat = new JLabel("Puerto");
        indicadorLocat.setBounds(400,20,60,50);
        indicadorLocat.setText("Puerto");
        paper.add(indicadorLocat);
    }
    private void Botones(){
        JButton enviar = new JButton("Enviar");
        enviar.setBounds(20,450,100,30);
        enviar.setBackground(rosado);//Agrega color al boton
        enviar.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));
        paper.add(enviar);//Añade el boton a la ventana

        ActionListener AccionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //codigo que se ejecuta al presionar el boton
            }
        };
        enviar.addActionListener(AccionBoton);
    }
    private void EntradasTexto(){//metodo que crea las entradas de texto
        caja = new JTextArea();
        caja.setBounds(0,60,550,350);
        caja.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));
        caja.setBackground(verde);
        paper.add(caja);

    }
    @Override
    public void run() {
        try {
            Escanner puertos = new Escanner();

            localizacion.setText(Integer.toString(puertos.EscannerPuertos()));

            ServerSocket Escuchar = new ServerSocket(puertos.EscannerPuertos());

            String nombre,puerto,mensaje,direccion;

            InfoEnvio paqueteEntregado;

            while (true) {
                Socket conector = Escuchar.accept();

                ObjectInputStream paquete = new ObjectInputStream(conector.getInputStream());//se recibe el objeto con la info

                paqueteEntregado = (InfoEnvio) paquete.readObject();//lee el objeto serializado

                nombre = paqueteEntregado.getNombre();//recuperamos la informacion del objeto
                puerto =  paqueteEntregado.getPuerto();
                mensaje = paqueteEntregado.getMensaje();
                direccion = paqueteEntregado.getIp();

                caja.append(nombre+">> "+ mensaje + " >Ip "+direccion+">>Puerto "+ puerto +"\n");

                Socket reconector =  new Socket(direccion,9933);
                ObjectOutputStream paqueteReenvio = new ObjectOutputStream(reconector.getOutputStream());

                paqueteReenvio.writeObject(paqueteEntregado);
                paqueteReenvio.close();

                reconector.close();

                conector.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

