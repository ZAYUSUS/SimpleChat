package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import  java.net.*;
import java.awt.event.*;

import java.awt.*;

import  javax.swing.*;//importa la biblioteca grafica

public class JMenu {
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
    public  Color verde = new Color(14,139,143);
    //------------------Fuentes-------------------------------------
    public Font daytona = new Font("Daytona Pro Light",Font.PLAIN,20);
    //---------------------------------------------------------
    public JPanel paper;
    public JTextArea caja;
    public myWindow(){

        setSize(700,600);
        setTitle("Mensager");
        setLocation(800,300);
        setBackground(Color.PINK);
        setResizable(false);

        setVisible(true);// vuelve la ventana visible

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
        JLabel etiqueta = new JLabel("Cliente",SwingConstants.LEFT);//etiqueta del titulo
        etiqueta.setBounds(0,0,400,50);
        etiqueta.setOpaque(true);//activa la opcion de editar el color de la etiqueta
        etiqueta.setBackground(amarillo);
        etiqueta.setFont(new Font("Century Gothic",Font.PLAIN,45));
        paper.add(etiqueta);//etiqueta se agrega al panel
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
                //System.out.println(caja.getText());
                try {
                    Socket conector = new Socket("192.168.0.14", 9933);

                    InfoEnvio datos =  new InfoEnvio();

                    datos.setNombre(nombre.getText());
                    datos.setMensaje(caja.getText());
                    datos.setPuerto(puerto.getText());

                    ObjectOutputStream paquete = new ObjectOutputStream(conector.getOutputStream());
                    paquete.writeObject(datos);
                    conector.close();//cierra la conexion

                    } catch (UnknownHostException e1){
                        e1.printStackTrace();
                    }catch (IOException e1){
                        System.out.println(e1.getMessage());
                }
            }
        };
        enviar.addActionListener(AccionBoton);
    }
    private void EntradasTexto(){//metodo que crea las entradas de texto
        caja = new JTextArea();
        //setText(String)
        //.append
        caja.setBounds(0,60,600,350);
        caja.setFont(daytona);
        caja.setBackground(verde);
        paper.add(caja);

        nombre = new JTextField();
        nombre.setBounds(200,20,200,30);
        nombre.setFont(daytona);
        nombre.setBackground(rosado);
        paper.add(nombre);

        puerto = new JTextField();
        puerto.setBounds(300,450,200,30);
        puerto.setFont(daytona);
        puerto.setBackground(azulito);
        paper.add(puerto);

    }
    private JTextField nombre,puerto;//crea las entradas de texto del nombre y el puerto

}

class InfoEnvio implements Serializable {//clase para almacenar los datos del cliente
    private String nombre,puerto,mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

}
// informacion para el codigo por parte de pildorasinformaticas en youtube

