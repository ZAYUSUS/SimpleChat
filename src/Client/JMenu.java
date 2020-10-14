package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import  java.net.*;
import java.awt.event.*;

import java.awt.*;//parte base de la biblioteca grafica

import  javax.swing.*;//importa la biblioteca grafica
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

public class JMenu {
    public static void main(String[] args ){
        myWindow marco1= new myWindow(); //crea la ventana
        marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//hace que la ventana se cierra
    }
}

class myWindow extends JFrame implements Runnable{//Se crea la clase de la ventana que hereda

    //--------------------COLORES---------------------------
    Color azulito = new Color(25,37,158);//color de fondo
    Color amarillo = new Color(219,190,22);
    Color rosado = new Color(219,22,154);
    Color verdeOscuro = new Color(57,158,143);
    Color verdeClaro = new Color(73,235,210);
    Color marron = new Color(235,163,61);


    //------------------Fuentes-------------------------------------
    Font daytona = new Font("Daytona Pro Light",Font.PLAIN,20);

    //---------------------------------------------------------
    JPanel paper;// panel para insertar los componentes en pantalla

    JTextArea caja;// Componente donde se imprimirá los mensajes
    JTextArea avisos;//componente que imprime avisos

    JLabel actualPuerto;//etiqueta para indicar a cual puerto se esta escuchando

    String Usuario = JOptionPane.showInputDialog("Nombre: ","Anónimo");//Ventana emergente para saber el nombre del usuario

    public myWindow() {//constructor de la ventana
        setSize(1000,600);//tamaño de la ventana
        setTitle("Mensager");//titulo
        setLocation(800,300);// ubicacion en pantalla
        setResizable(false);

        setVisible(true);// vuelve la ventana visible
        Inicializador();//inicia los metodos para colocar componentes
    }
    private void Inicializador(){
        PaperCreator();//Inicia el panel de para almacenar los componentes
        Thread Hilo1 = new Thread(this);//crea el hilo para ejecutar el ServerSocket en segundo plano
        Hilo1.start();//inicializa el hilo
    }
    private void PaperCreator(){//Clase que dibuja en la ventana funciona para introducir imagenes,texto, botones...
        paper = new JPanel();
        paper.setLayout(null);//desactiva el diseño de el layout
        paper.setBackground(verdeClaro);
        this.getContentPane().add(paper);
        ComponentesEtiquetas();
        Botones();
        EntradasTexto();//Inicia las entradas de texto
    }
    private void ComponentesEtiquetas(){
        //-----------------------------------------------------------------------
        JLabel etiqueta = new JLabel("CHAT",SwingConstants.LEFT);//etiqueta del titulo
        etiqueta.setBounds(0,0,400,50);
        etiqueta.setOpaque(true);//activa la opcion de editar el color de la etiqueta
        etiqueta.setBackground(amarillo);
        etiqueta.setFont(new Font("Century Gothic",Font.PLAIN,45));
        paper.add(etiqueta);//etiqueta se agrega al panel


        JLabel indicaPuerto = new JLabel("Puerto de Salida",SwingConstants.LEFT);
        indicaPuerto.setBounds(880,170,120,30);
        paper.add(indicaPuerto);

        JLabel indicaIP = new JLabel("IP",SwingConstants.CENTER);//etiqueta que indica la IP
        indicaIP.setBounds(880,120,70,30);
        paper.add(indicaIP);

        JLabel indicaNombre = new JLabel("Nombre: ");
        indicaNombre.setBounds(420,20,90,30);
        indicaNombre.setFont(daytona);
        paper.add(indicaNombre);

        JLabel indicaEscritor = new JLabel("Escriba Aquí");
        indicaEscritor.setBounds(670,120,500,30);
        indicaEscritor.setFont(new Font("Daytona Pro Light",Font.PLAIN,10));
        paper.add(indicaEscritor);

        JLabel indicaEspacio2 = new JLabel("Escriba Aquí");
        indicaEspacio2.setBounds(670,170,200,30);
        indicaEspacio2.setFont(new Font("Daytona Pro Light",Font.PLAIN,10));
        paper.add(indicaEspacio2);

        JLabel indicaEspacio = new JLabel("Escriba Aquí");
        indicaEspacio.setBounds(80,450,200,30);
        indicaEspacio.setFont(new Font("Daytona Pro Light",Font.PLAIN,10));
        paper.add(indicaEspacio);

        nombre = new JLabel(Usuario,SwingConstants.CENTER);//etiqueta que muestra el nombre elegido  por el usuario
        nombre.setBounds(510,20,80,30);
        nombre.setFont(daytona);
        nombre.setForeground(rosado);
        paper.add(nombre);

        JLabel indicaActualPuerto = new JLabel("Puerto Entrada",SwingConstants.CENTER);
        indicaActualPuerto.setBounds(650,0,150,30);
        paper.add(indicaActualPuerto);

        actualPuerto = new JLabel("",SwingConstants.CENTER);//muestra el puerto de escucha actual
        actualPuerto.setBounds(790,0,100,30);
        actualPuerto.setOpaque(true);
        actualPuerto.setBackground(marron);
        paper.add(actualPuerto);



    }
    private void Botones(){
        JButton enviar = new JButton("Enviar");// boton para enviar la información
        enviar.setBounds(30,510,100,30);//posicion, alto y ancho del boton

        enviar.setBackground(amarillo);//Agrega color al boton

        enviar.setFont(new Font("Daytona Pro Light",Font.PLAIN,20));//tipo de letra del boton

        paper.add(enviar);//Añade el boton a la ventana

        ActionListener AccionBoton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //codigo que se ejecuta al presionar el boton
                String verificacion = puerto.getText();
                String Ipverificacion = direccion.getText();
                    if (verificacion.length() > 0 && Ipverificacion.length() > 0) {
                        try {
                            Socket conector = new Socket(direccion.getText(), Integer.parseInt(puerto.getText()));/*conecta el socket al servidor de escucha con el texto
                             *de las entradas de texto
                             */

                            InfoEnvio datos = new InfoEnvio();//crea un objeto de envio de datos

                            datos.setNombre(nombre.getText());//Agrega lo que este escrito en el JTextField al objeto
                            datos.setMensaje(texto.getText());//Agrega lo que este en el JTextField del mensaje al objeto
                            datos.setPuerto(actualPuerto.getText());
                            datos.setIp(direccion.getText());

                            ObjectOutputStream paquete = new ObjectOutputStream(conector.getOutputStream());
                            paquete.writeObject(datos);// construye el objeto de envio de datos

                            conector.close();//cierra la conexion
                            caja.append("Tu: " + texto.getText() + "\n");
                            texto.setText("");
                        } catch (UnknownHostException e1) {//detecta si el Host es desconocido
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            System.out.println(e1.getMessage());
                            avisos.setForeground(Color.green);
                            avisos.append("Error :"+"No se puede conectar al puerto indicado");
                        }finally {
                            System.out.println("Se acabo el proceso de envió de datos");
                        }
                    } else{
                        JOptionPane.showMessageDialog(paper,
                                "Faltan los datos de la IP o el Puerto");
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
        caja.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(caja);
        scroll.setBounds(20,60,600,350);
        paper.add(scroll);

        avisos = new JTextArea();
        avisos.setEditable(false);
        avisos.setLineWrap(true);
        avisos.setBackground(Color.black);
        avisos.setFont(new Font("Daytona Pro Light",Font.PLAIN,12));
        JScrollPane scrollAvisos = new JScrollPane(avisos);
        scrollAvisos.setBounds(670,310,290,100);
        paper.add(scrollAvisos);

        puerto = new JTextField();//cuadro para insertar el numero de puerto
        puerto.setBounds(670,170,200,30);
        puerto.setFont(daytona);
        puerto.setBackground(verdeOscuro);
        paper.add(puerto);

        direccion = new JTextField();//cuadro para insertar la IP
        direccion.setBounds(670,120,200,30);
        direccion.setFont(daytona);
        direccion.setBackground(verdeOscuro);
        direccion.setText("127.0.0.1");
        paper.add(direccion);

        texto = new JTextField();//cuadro para insertar el mensaje
        texto.setBounds(30,450,500,30);
        texto.setFont(daytona);
        texto.setForeground(azulito);
        paper.add(texto);

    }
    private JTextField puerto,texto,direccion;//crea las entradas de texto del nombre ,el puerto,Ip
    private JLabel nombre;

    @Override
    public void run() {//bloque donde estan los sockets, corre en segundo plano
        try{
            Escanner entrada = new Escanner();//crea la instancia del escaner de puertos

            int conexion = entrada.EscannerPuertos();//guarda el numero de puerto disponible

            actualPuerto.setText(Integer.toString(conexion));//agrega el numero a la etiqueta de la pantalla

            ServerSocket Servercliente = new ServerSocket(conexion);//crea el puerto donde se escuchara
            Socket cliente;//variable para el socket de envio recogida de datos

            String nombre,puerto,mensaje,direccion;

            InfoEnvio paqueteRecibido;//crea la variable del objeto para enviar informacion

            while (true){
                cliente = Servercliente.accept();//acepta la conexion con server

                ObjectInputStream datosEntrada = new ObjectInputStream(cliente.getInputStream());//carga los datos del objeto InfoEnvio

                paqueteRecibido = (InfoEnvio) datosEntrada.readObject();

                nombre = paqueteRecibido.getNombre();//recuperamos la informacion del objeto
                mensaje = paqueteRecibido.getMensaje();
                puerto = paqueteRecibido.getPuerto();


                caja.append("{"+puerto+"}"+nombre+">>"+mensaje+"\n");// crea el mensaje que aparece en el Jtextarea

                cliente.close();// cierra la conexion

            }

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }catch (Exception a){
            System.out.println(a.getMessage());//se emprime el error generado
        }finally {
            System.out.println("Se terminó el proceso de recibido de datos");
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






/*Ayuda con el lenguaje, Sockets y la Interfaz gráfica.
*https://www.youtube.com/channel/UCdulIs-x_xrRd1ezwJZR9ww
*https://www.w3schools.com/java/java_class_methods.asp
*https://www.youtube.com/channel/UC7QoKU6bj1QbXQuNIjan82Q
 */
/*En la ventana se puede escribir en tres cuadros diferentes
 *el amarillo se usa para escribir el mensaje que se desea enviar
 *el de puerto se usa para indicar el puerto al cual se dirige el mensaje
 * el de la IP para escribir la dirección IP
 */