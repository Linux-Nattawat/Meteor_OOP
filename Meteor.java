
import java.awt.*;
import java.io.*;

import javax.swing.ImageIcon;

public class Meteor extends Thread {

    private int metX;
    private int metY;
    private int vx;
    private int vy;
    private boolean isAlive = true;
    private boolean isExploding = false;

    public Meteor(int metX, int metY, int vx, int vy) {
        setX(metX);
        setY(metY);
        setVx(vx);
        setVy(vy);
    }
    int rand = (int)(Math.random() * 5) + 1;
    String format = "meteor" + rand + ".png";
    Image mt = new ImageIcon("img/" + format).getImage();
    Image boom = new ImageIcon("img/boom.png").getImage();

    void draw(Graphics g) {
        if(isExploding)
            g.drawImage(boom, getX(), getY(), null);
        else
            g.drawImage(mt, getX(), getY(),null );
    }

    public void explode(){
                isExploding = true;
                setVx(0);
                setVy(0);
    }

    public boolean isExploding(){
        return isExploding;
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    int getX() {
        return metX;
    }
    
    int getY(){
        return metY;
    }
    
    int getVx() {
        return vx;
    }

    int getVy() {
        return vy;
    }
    
    void setX(int metX) {
        this.metX = metX;
    }

    void setY(int metY) {
        this.metY = metY;
    }

    void setVx(int vx) {
        this.vx = vx;
    }

    void setVy(int vy) {
        this.vy = vy;
    }

    @Override
    public void run() {
        super.run();
        while(isAlive){

            if(isExploding == true){
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                }
                isAlive = false;
            }

            setX(metX += getVx());
            setY(metY += getVy());

            if(metX <= 0 || metX >= (1440-100)){ // 1440 - 100 << 100 = ขนาดของรูป
                vx = (int)(vx * -1.125);
            }
            else if (metY <= 0 || metY >= (810-120)){
                vy = (int)(vy * -1.125);
            }

            try {
                Thread.sleep(16);    
            } catch (InterruptedException e) {
            } 
        }
    }

}
