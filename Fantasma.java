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
 int dir=0;
 int mx;
 int my;

    public Fantasma(int fanx, int fany) {
        rnd= new Random();
        this.fanx = fanx;
        this.fany = fany;
        Ventana.mat[fanx][fany]=7;
        dir=rnd.nextInt(4);
        this.movement();
    }

    public void movement(){
        timer =new Timer(200,new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (dir==0){
                    if(Ventana.mat[fanx-1][fany]==0||Ventana.mat[fanx-1][fany]==1){
                        fanx-=1;
                        Ventana.mat[fanx][fany]=7;
                    }
                    if(fanx>0&&Ventana.mat[fanx-1][fany]==2){
                        dir=rnd.nextInt(4);
                    }
                    if(Ventana.mat[fanx-1][fany]==7){
                        dir=rnd.nextInt(4);
                    }
                }
                if (dir==1){
                    if(Ventana.mat[fanx+1][fany]==0||Ventana.mat[fanx+1][fany]==1){
                        fanx+=1;
                        Ventana.mat[fanx][fany]=7;
                    }
                    if(fanx<13&&Ventana.mat[fanx+1][fany]==2){
                        dir=rnd.nextInt(4);
                    }
                    if(Ventana.mat[fanx+1][fany]==7){
                        dir=rnd.nextInt(4);
                    }
                }
                if (dir==2){
                    if(Ventana.mat[fanx][fany-1]==0||Ventana.mat[fanx][fany-1]==1){
                        fany-=1;
                        Ventana.mat[fanx][fany]=7;
                    }
                    if(fanx>0&&Ventana.mat[fanx][fany-1]==2){
                        dir=rnd.nextInt(4);
                    }
                    if(Ventana.mat[fanx][fany-1]==7){
                        dir=rnd.nextInt(4);
                    }
                }
                if (dir==3){
                    if(Ventana.mat[fanx][fany+1]==0||Ventana.mat[fanx][fany+1]==1){
                        fany+=1;
                        Ventana.mat[fanx][fany]=7;
                    }
                    if(fanx<13&&Ventana.mat[fanx-1][fany+1]==2){
                        dir=rnd.nextInt(4);
                    }
                    if(Ventana.mat[fanx][fany+1]==7){
                        dir=rnd.nextInt(4);
                    }
                }
            }
        });
        timer.start();
    }
}

