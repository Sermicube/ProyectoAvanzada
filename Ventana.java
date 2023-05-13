package ProyectoAvanzada;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class Ventana {
  // DECLARACIÓN DE VARIABLES
  // FRAME
  static JFrame frame;
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
  static JPanel gamepanel;
  private JLabel background;
  private ImageIcon gameimage;
  static int mat[][];
  static JLabel matriz[][];
  private int px;
  private int py;
  private int up;
  private int down;
  private int left;
  private int right;
  private String jugador;
  private JLabel nombre;
  private int points;
  private JLabel records;
  private Timer timer;
  Fantasma fan1;
  static int maTA[][];

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
    iniciar.setBackground(Color.ORANGE);
    iniciar.setForeground(Color.WHITE);
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
        System.out.println("Iniciar");
        Menu();
        menuEvent();
      }
    });
    //GAME
    mat=new int[15][15];
    matriz=new JLabel[15][15];
    maTA=new int[15][15];
    for (int i=0;i<mat.length;i++){
      for (int j=0;j<mat.length;j++){
        matriz[i][j]=new JLabel();
      }
    }

    mat=table(1);
    px=1;
    py=1;
    mat[px][py]=3;


    up=0;
    left=0;
    right=0;
    down=0;

    frame.add(panelppt);


  }
  private void cambiarColoresBotones() {
    Color temp = botones[0].getBackground();
    for (int i = 0; i < botones.length - 1; i++) {
      botones[i].setBackground(botones[i + 1].getBackground());
    }
    botones[botones.length - 1].setBackground(temp);
  }
  public void Menu() {
    frame.getContentPane().removeAll();
    // CONFIGURACIÓN PANEL
    panelmenu = new JPanel();
    panelmenu.setLayout(null);
    panelmenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    panelmenu.setVisible(true);
    // CONFIGURACION FONDO PRESENTACION
    fondomenu = new JLabel();
    fondomenu.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    imagemenu = new ImageIcon("Imagenes/ImagenCarga.png");
    imagemenu = new ImageIcon(
            imagemenu.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
    fondomenu.setIcon(imagemenu);
    fondomenu.setVisible(true);
    // ADICIONES
    panelmenu.add(fondomenu, 0);
    botones[0].setText("Jugar");
    botones[1].setText("Crear Tablero");
    botones[2].setText("Records");
    botones[3].setText("Cargar Tablero");
    botones[4].setText("Salir");
    Timer timer = new Timer(250, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        cambiarColoresBotones();
      }
    });
    timer.start();
    Color[] colores={Color.YELLOW,Color.RED,Color.BLUE,Color.GREEN,Color.ORANGE};
    for (int i = 0; i < botones.length; i++) {
      botones[i].setVisible(true);
      botones[i].setBounds(frame.getWidth() - (430), 300 + ((i + 1) * 50), 150, 30);
      botones[i].setBackground(colores[i]);
      botones[i].setForeground(Color.WHITE);
      //botones[i].setBackground(Color.WHITE);
      panelmenu.add(botones[i], 0);
    }
    frame.add(panelmenu);
    frame.revalidate();
    frame.repaint();
  }

  public void menuEvent(){
    botones[0].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("Jugar");
        jugador = JOptionPane.showInputDialog(frame, "Nombre del Jugador", "Escribe aquí");
        while(jugador == null || jugador.compareTo("Escribe aquí" ) == 0|| jugador.compareTo("")==0) {
          jugador = JOptionPane.showInputDialog(frame, "Debes ingresar un usuario", "Escribe aquí");
        }
        Jugar();
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
    botones[4].addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        System.out.println("Salir");
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
    gameimage = new ImageIcon("Imagenes/FondoMenu.png");
    gameimage = new ImageIcon(
            gameimage.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT));
    background.setIcon(gameimage);
    background.setVisible(true);
    // ADICIONES
    gamepanel.add(background, 0);
    for(int i=0;i<mat.length;i++){
      for (int j=0;j<mat.length;j++){
        matriz[i][j].setIcon(new ImageIcon("Imagenes/"+mat[i][j]+".png"));
        matriz[i][j].setBounds(100+(i*30),200+(j*30),30,30);
        matriz[i][j].setVisible(true);
        gamepanel.add(matriz[i][j],0);
      }
    }


    nombre=new JLabel("PLAYER: "+jugador);
    nombre.setBounds(20,20,150,30);
    nombre.setForeground(Color.WHITE);
    nombre.setVisible(true);
    gamepanel.add(nombre,0);


    points=0;
    records=new JLabel("POINTS: "+ points);
    records.setBounds(frame.getWidth()-130,20,150,30);
    records.setForeground(Color.WHITE);
    records.setVisible(true);
    gamepanel.add(records,0);


    move();
    fan1= new Fantasma(13,13);
    frame.add(gamepanel);
  }

  public static void drawMatrix(){
    for(int i=0;i<mat.length;i++){
      for (int j=0;j<mat.length;j++){
        matriz[i][j].setIcon(new ImageIcon("Imagenes/"+mat[i][j]+".png"));
        matriz[i][j].setBounds(200+(i*30),200+(j*30),30,30);
        matriz[i][j].setVisible(true);
        gamepanel.add(matriz[i][j],0);
      }
    }
  }
  public void move(){
    timer =new Timer(200,new ActionListener(){

      public void actionPerformed(ActionEvent e) {
        if(right==1 && (mat[px+1][py]==1||mat[px+1][py]==0)){
          if (mat[px+1][py]==1){
            points+=5;
            records.setText("POINTS: "+ points);
          }
          mat[px][py]=0;
          px=px+1;
          mat[px][py]=0;
          drawMatrix();
        }
        if(left==1 && (mat[px-1][py]==1||mat[px-1][py]==0)){
          if (mat[px-1][py]==1){
            points+=5;
            records.setText("POINTS: "+ points);
          }
          mat[px][py]=0;
          px=px-1;
          mat[px][py]=0;
          drawMatrix();
        }
        if(up==1 && (mat[px][py-1]==1||mat[px][py-1]==0)){
          if (mat[px][py-1]==1){
            points+=5;
            records.setText("POINTS: "+ points);
          }
          mat[px][py]=0;
          py=py-1;
          mat[px][py]=0;
          drawMatrix();
        }
        if(down==1 && (mat[px][py+1]==1||mat[px][py+1]==0)){
          if (mat[px][py+1]==1){
            points+=5;
            records.setText("POINTS: "+ points);
          }
          mat[px][py]=0;
          py=py+1;
          mat[px][py]=0;
          drawMatrix();
        }
        int found=0;
        for(int i=0;i<mat.length&&found==0;i++){
          for(int j=0;j<mat.length&&found==0;j++){
            if(mat[i][j]==1)
              found=1;
          }
        }
        if(found==0){
          JOptionPane.showMessageDialog(frame,"FELICITACIONES, COMPLETÓ EL NIVEL ");
          gamepanel.setVisible(false);
          panelmenu.setVisible(true);
          timer.stop();
        }
      }
    });
    timer.start();
    frame.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
          System.out.println("Tecla hacia arriba");
          if(mat[px][py-1]==1||mat[px][py-1]==0){
            up=1;
            down=0;
            left=0;
            right=0;
          }

        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
          System.out.println("Tecla hacia derecha");
          if(mat[px+1][py]==1||mat[px+1][py]==0){
            up=0;
            down=0;
            left=0;
            right=1;
          }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
          System.out.println("Tecla hacia izquierda");
          if(mat[px-1][py]==1||mat[px-1][py]==0){
            up=0;
            down=0;
            left=1;
            right=0;
          }
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
          System.out.println("Tecla hacia abajo");
          if(mat[px][py+1]==1||mat[px][py+1]==0){
            up=0;
            down=1;
            left=0;
            right=0;
          }
        }
      }
      @Override
      public void keyReleased(KeyEvent e) {

      }

    });


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
      return aux;
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
      return aux;
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
      return aux;
    }
    return aux1;
  }
  public void showFrame() {
    frame.setVisible(true);
  }

}
