package ProyectoAvanzada;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;

public class Ventana {
  // DECLARACIÓN DE VARIABLES
  // FRAME
  private JFrame frame;
  // VISTA INICIAL
  private JPanel panelppt;
  private JButton iniciar;
  private JLabel fondoppt;
  private ImageIcon imageback;
  // MENÚ PRINCIPAL
  private JPanel panelmenu;
  private JButton botones[];
  private JLabel fondomenu;
  private ImageIcon imagemenu;
  //JUEGO
  private JPanel gamepanel;
  private JLabel background;
  private ImageIcon gameimage;
  private int mat[][];
  private JLabel matriz[][];
  private String jugador;
  private JLabel nombre;
  private String points;
  private JLabel records;

  public Ventana() {
    // CONFIGURACIÓN VENTANA
    frame = new JFrame("Pacman");
    frame.setSize(700, 700);
    frame.setLayout(null);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // CONFIGURACIÓN PANEL
    panelppt = new JPanel();
    panelppt.setLayout(null);
    panelppt.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    panelppt.setVisible(true);
    // CONFIGURACIÓN BOTÓN
    iniciar = new JButton("Iniciar");
    iniciar.setBounds((frame.getWidth()) - 400, 550, 100, 30);
    iniciar.setVisible(true);
    iniciar.setBackground(Color.WHITE);
    panelppt.add(iniciar, 0);// Se añade el botón al panel (Funcióna por capas como la edición de videos o
    // fotos, cada Panel es una capa)
    // CONFIGURACION FONDO PRESENTACION
    fondoppt = new JLabel();
    fondoppt.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    imageback = new ImageIcon("Imagenes/ImagenCarga.png");
    imageback = new ImageIcon(
            imageback.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
    fondoppt.setIcon(imageback);
    fondoppt.setVisible(true);
    // ADICIONES
    panelppt.add(fondoppt, 0);
    botones = new JButton[5];
    for (int i = 0; i < botones.length; i++) {
      botones[i] = new JButton();
    }
    iniciar.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        Menu();
        menuEvent();
      }
    });
    frame.add(panelppt);

  }

  public void Menu() {
    // CONFIGURACIÓN PANEL
    panelppt.setVisible(false);
    panelmenu = new JPanel();
    panelmenu.setLayout(null);
    panelmenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    panelmenu.setVisible(true);
    // CONFIGURACION FONDO PRESENTACION
    fondomenu = new JLabel();
    fondomenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    imagemenu = new ImageIcon("Imagenes/Menu.png");
    imagemenu = new ImageIcon(
            imagemenu.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
    fondomenu.setIcon(imagemenu);
    fondomenu.setVisible(true);
    // ADICIONES
    panelppt.add(fondomenu, 0);
    botones[0].setText("Jugar");
    botones[1].setText("Crear Tablero");
    botones[2].setText("Records");
    botones[3].setText("Cargar Tablero");
    botones[4].setText("Salir");
    for (int i = 0; i < botones.length; i++) {
      botones[i].setVisible(true);
      botones[i].setBounds(frame.getWidth() - (430), 300 + ((i + 1) * 50), 150, 30);
      botones[i].setBackground(Color.WHITE);
      panelmenu.add(botones[i], 0);
    }
    frame.add(panelmenu);
  }

  public void menuEvent(){
    botones[0].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("Jugar");
        jugador = JOptionPane.showInputDialog(frame, "Nombre del Jugador", "Escribe aquí");
        while(jugador == null || jugador.compareTo("Escribe aquí" ) == 0|| jugador.compareTo("")==0) {
          jugador = JOptionPane.showInputDialog(frame, "Debes ingresar un usuario", "Escribe aquí");
        }
        Menu();
      }
    });
    botones[1].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("Crear Tablero");
        Jugar();
      }
    });
    botones[2].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("Records");
        Menu();
      }
    });
    botones[3].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("Cargar Tablero");
        Menu();
      }
    });
    botones[0].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.exit(0);
      }
    });
  }
  public void Jugar(){
    panelmenu.setVisible(false);
    gamepanel = new JPanel();
    gamepanel.setLayout(null);
    gamepanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    gamepanel.setVisible(true);
    // CONFIGURACION FONDO PRESENTACION
    background = new JLabel();
    background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    gameimage = new ImageIcon("Imagenes/Menu.png");
    gameimage = new ImageIcon(
            gameimage.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
    background.setIcon(imagemenu);
    background.setVisible(true);
    // ADICIONES
    gamepanel.add(background, 0);
    frame.add(gamepanel);
  }

  public int[][] table(int opc){
    int[][] aux1 = new int[15][15];
    if(opc == 1){

      int aux[][]={
              {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,2,2,2,1,1,1,2,1,1,2,1,2},
              {2,1,2,1,1,1,1,1,1,2,1,1,1,1,2},
              {2,1,2,1,2,1,1,1,1,2,1,1,1,1,2},
              {2,1,2,1,2,1,1,1,1,2,1,1,1,1,2},
              {2,1,2,1,1,1,1,1,1,2,1,1,1,1,2},
              {2,1,2,2,2,2,1,1,1,2,1,1,1,1,2},
              {2,1,1,1,1,1,1,1,1,2,1,1,1,1,2},
              {2,1,2,2,2,2,2,2,1,2,2,2,2,1,2},
              {2,1,1,1,1,1,1,2,1,2,2,2,2,1,2},
              {2,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
              {2,1,1,1,1,1,1,2,2,2,2,2,2,1,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
      };
      return aux1;
    }
    else if(opc == 2){
      int aux[][]={
              {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,1,2,2,2,2,2,2,2,2,2,1,2},
              {2,1,2,1,2,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,1,2,2,2,2,2,2,2,2,2,1,2},
              {2,1,2,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,2,2,2,2,2,2,1,1,1,1,1,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,1,2,2,2,2,2,2,2,1,2,1,2},
              {2,1,2,1,1,1,1,1,1,1,1,1,2,1,2},
              {2,1,2,2,2,2,2,2,2,2,2,2,2,1,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
      };
      return aux1;
    }
    else if(opc == 3){
      int aux[][]={
              {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,1,2,1,2,1,2,1,2,1,2,1,2},
              {2,1,2,1,2,1,2,1,2,1,2,1,1,1,2},
              {2,1,2,1,2,1,2,1,2,1,1,1,1,1,2},
              {2,1,2,1,2,1,2,1,2,2,2,2,2,1,2},
              {2,1,2,1,2,1,2,1,1,1,1,1,1,1,2},
              {2,1,2,1,2,1,2,2,2,2,2,2,2,1,2},
              {2,1,2,1,2,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,1,2,2,2,2,2,2,2,2,2,1,2},
              {2,1,2,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,1,2,2,2,2,2,2,2,2,2,2,2,1,2},
              {2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
              {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
      };
      return aux1;
    }
    return aux1;
  }
  public void showFrame() {
    frame.setVisible(true);
  }

}
