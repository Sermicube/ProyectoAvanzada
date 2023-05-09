package ProyectoAvanzada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static ProyectoAvanzada.Ventana.mat;

public class Fantasma {
 private int fanx;
 private int fany;
 Timer timer;
 Random rnd;
 int dir;
 int mx;
 int my;

    public Fantasma(int fanx, int fany) {
        this.fanx = fanx;
        this.fany = fany;
        mat[fanx][fany]=7;
        dir=rnd.nextInt(4);
    }

    public void movement(){
        timer =new Timer(200,new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (dir==0){

                }
                if (dir==1){

                }
                if (dir==2){

                }
                if (dir==3){

                }
                /*if (right == 1 && (mat[px + 1][py] == 1 || mat[px + 1][py] == 0)) {
                    if (mat[px + 1][py] == 1) {
                        points += 5;
                        records.setText("POINTS: " + points);
                    }
                    mat[px][py] = 0;
                    px = px + 1;
                    mat[px][py] = 0;
                    drawMatrix();
                }*/
            }
        });
    }
}

