
import java.awt.*;
import java.io.*;

public class Meteor extends Thread {

    private int metX;
    private int metY;
    private int vx;
    private int vy;
    private boolean isAlive = true;

    public Meteor(int metX, int metY, int vx, int vy) {
        setX(metX);
        setY(metY);
        setVx(vx);
        setVy(vy);
    }
    int rand = (int)(Math.random() * 3) + 1;
    String format = "meteor" + rand + ".png";
    Image mt = Toolkit.getDefaultToolkit().createImage(
            System.getProperty("user.dir") + File.separator + "img"+ File.separator + format
    );

    void draw(Graphics g) {
        g.drawImage(mt, getX(), getY(),null );
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
            setX(metX += getVx());
            setY(metY += getVy());

            if(metX <= 0 || metX >= (1440-100)){ // 1440 - 100 << 100 = ขนาดของรูป
                vx = (int)(vx * -1.115);
            }
            else if (metY <= 0 || metY >= (810-100)){
                vy = (int)(vy * -1.115);
            }

            try {
                Thread.sleep(16);    
            } catch (InterruptedException e) {
            } 
        }
    }

}
