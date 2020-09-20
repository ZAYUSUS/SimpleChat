package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
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
class myWindow extends JFrame implements Runnable{
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
    public JLabel indicaPuerto;
    public JLabel actualPuerto;
    String Usuario = JOptionPane.showInputDialog("Nombre: ");
    public myWindow() {
        setSize(700,800);
        setTitle("Mensager");
        setLocation(800,300);
        setResizable(false);

        setVisible(true);// vuelve la ventana visible
        Inicializador();
    }
    private void Inicializador(){
        PaperCreator();
        ComponentesEtiquetas();
        EntradasTexto();
        Botones();

        Thread Hilo1 = new Thread(this);
        Hilo1.start();
    }
    private void PaperCreator(){//Clase que dibuja en la ventana funciona para introducir imagenes,texto, botones...
        paper = new JPanel();
        paper.setLayout(null);//desactiva el diseño de el layout
        //paper.setBackground(azulito);
        this.getContentPane().add(paper);
    }
    private void ComponentesEtiquetas(){
        //-----------------------------------------------------------------------
        JLabel etiqueta = new JLabel("CHAT",SwingConstants.LEFT);//etiqueta del titulo
        etiqueta.setBounds(0,0,400,50);
        etiqueta.setOpaque(true);//activa la opcion de editar el color de la etiqueta
        etiqueta.setBackground(amarillo);
        etiqueta.setFont(new Font("Century Gothic",Font.PLAIN,45));
        paper.add(etiqueta);//etiqueta se agrega al panel

        JLabel indicaPuerto = new JLabel("Puerto",SwingConstants.CENTER);
        indicaPuerto.setBounds(510,550,70,30);
        paper.add(indicaPuerto);

        JLabel indicaIP = new JLabel("IP",SwingConstants.CENTER);
        indicaIP.setBounds(510,600,70,30);
        paper.add(indicaIP);

        JLabel indicaNombre = new JLabel("Nombre: ");
        indicaNombre.setBounds(420,20,90,30);
        indicaNombre.setFont(daytona);
        paper.add(indicaNombre);

        nombre = new JLabel();
        nombre.setBounds(510,20,80,30);
        nombre.setFont(daytona);
        nombre.setOpaque(true);
        nombre.setBackground(rosado);
        nombre.setText(Usuario);
        paper.add(nombre);

        indicaPuerto = new JLabel(" Puerto de Escucha");
        indicaPuerto.setBounds(510,630,150,30);
        paper.add(indicaPuerto);

        actualPuerto = new JLabel();
        actualPuerto.setBounds(460,630,150,30);
        paper.add(actualPuerto);



    }
    private void Botones(){
        JButton enviar = new JButton("Enviar");
        enviar.setBounds(20,550,100,30);
        enviar.setBackground(rosado);//Agrega color al boton
        enviar.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));
        paper.add(enviar);//Añade el boton a la ventana

        ActionListener AccionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //codigo que se ejecuta al presionar el boton
                try {
                    Socket conector = new Socket(direccion.getText(),Integer.parseInt(puerto.getText()));/*conecta el socket al servidor de escucha con el texto
                    *de las entradas de texto
                    */

                    InfoEnvio datos =  new InfoEnvio();//crea un objeto de envio de datos

                    datos.setNombre(nombre.getText());
                    datos.setMensaje(texto.getText());
                    datos.setPuerto(puerto.getText());
                    datos.setIp(direccion.getText());

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
        //caja.setBounds(20,60,600,350);
        caja.setFont(daytona);
        caja.setEnabled(true);
        caja.setEditable(false);
        JScrollPane scroll = new JScrollPane(caja);
        scroll.setBounds(20,60,600,350);
        paper.add(scroll);

        puerto = new JTextField();
        puerto.setBounds(300,550,200,30);
        puerto.setFont(daytona);
        puerto.setBackground(azulito);
        paper.add(puerto);

        direccion = new JTextField();
        direccion.setBounds(300,600,200,30);
        direccion.setFont(daytona);
        direccion.setBackground(azulito);
        direccion.setText("127.0.0.1");
        paper.add(direccion);

        texto = new JTextField();
        texto.setBounds(30,450,500,30);
        texto.setFont(daytona);
        texto.setBackground(amarillo);
        paper.add(texto);


    }
    private JTextField puerto,texto,direccion;//crea las entradas de texto del nombre ,el puerto,Ip
    private JLabel nombre;

    @Override
    public void run() {//bloque donde estan los sockets
        try{
            Escanner entrada = new Escanner();

            int conexion = entrada.EscannerPuertos();
            actualPuerto.setText(Integer.toString(conexion));
            ServerSocket Servercliente = new ServerSocket(conexion);//crea el puerto donde se escuchara
            Socket cliente;//variable para el socket de envio recogida de datos

            String nombre,puerto,mensaje,direccion;

            InfoEnvio paqueteRecibido;

            while (true){
                cliente = Servercliente.accept();

                ObjectInputStream datosEntrada = new ObjectInputStream(cliente.getInputStream());

                paqueteRecibido = (InfoEnvio) datosEntrada.readObject();

                nombre = paqueteRecibido.getNombre();//recuperamos la informacion del objeto
                puerto =  paqueteRecibido.getPuerto();
                mensaje = paqueteRecibido.getMensaje();
                direccion = paqueteRecibido.getIp();


                caja.append(puerto+"///"+nombre+">>"+mensaje+"\n");

                //Socket reconector =  new Socket(direccion,Integer.parseInt(puerto));

                //ObjectOutputStream paqueteReenvio = new ObjectOutputStream(reconector.getOutputStream());

                //paqueteReenvio.writeObject(paqueteRecibido);

                //paqueteReenvio.close();

                //reconector.close();

                cliente.close();

            }

        }catch (IOException | ClassNotFoundException e){
            System.out.println("Errorzaso");
            e.printStackTrace();
        }catch (Exception a){
            System.out.println(a.getMessage());
        }
    }
}

class InfoEnvio implements Serializable {//clase para almacenar los datos del cliente
    private String nombre,puerto,mensaje,Ip;

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

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

