import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.TimerTask;
import javax.swing.*;
public class Ghost extends JFrame {
    panel pan = new panel();
        Ghost(){
            setTitle("Ghost Hunter");
            setSize(1000,563);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(pan);
        }

    public static void main(String[] args) {
        new Ghost().setVisible(true);;
    }
}
class panel extends JPanel {
    Image bg = Toolkit.getDefaultToolkit().createImage(
        System.getProperty("user.dir") + File.separator + "background.jpg"
    );
    Image gh = Toolkit.getDefaultToolkit().createImage(
        System.getProperty("user.dir") + File.separator + "ghost.png"
    );
    Image si = Toolkit.getDefaultToolkit().createImage(
        System.getProperty("user.dir") + File.separator + "sight.gif"
    );
    int[] gx = new int[10];
    int[] gy = new int[10];
    int sx = 500;
    int sy = 250;
    panel(){
        setSize(1000,563);
        for (int i = 0; i < gx.length; i++) {
            gx[i] = (int)(Math.random()*900);
            gy[i] = (int)(Math.random()*460);
        }
        addMouseMotionListener(new MouseMotionListener(){

            public void mouseDragged(MouseEvent e){

            }
            public void mouseMoved(MouseEvent e){
                sx=e.getX()-50;
                sy=e.getY()-50;
                repaint();
            }
        });
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < gx.length; i++) {
                    gx[i] += (int)(Math.random() * 10) - 5;
                    gy[i] += (int)(Math.random() * 10) - 5;
                }
                repaint();
            }
        }, 0, 100);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg,0, 0, this);
        g.setFont(new Font("Tamoha", Font.BOLD, 40));
        g.setColor(Color.CYAN);
        g.drawString("Ghost Hunter", 700, 50);
        g.setColor(Color.white);
        g.drawLine(700, 60, 950, 60);
        for (int i = 0; i< gx.length; i++) {
            g.drawImage(gh,gx[i],gy[i], this);
        }
        g.drawImage(si,sx,sy, this);
    }
}
