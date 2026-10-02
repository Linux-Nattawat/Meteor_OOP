
import java.awt.*;
import java.io.*;
import javax.swing.*;

class start {

    public static void main(String[] args) {
        Panel pan = new Panel();
        pan.getMet(Integer.parseInt(JOptionPane.showInputDialog("Input Meteor :")));

        new GameFrame();
    }
}

public class GameFrame extends JFrame {

    Panel pan = new Panel();
    public int width = 1440;
    public int height = 810;

    public GameFrame() {
        setSize(1440, 810);
        setTitle("Meteor Strike!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(pan);
    }

}

class Panel extends JPanel implements Runnable {

    private int met;

    public Panel() {
        setSize(1440, 810);
        int x,y,vx,vy;
        for (int i = 0;; i++){
            x = (int) (Math.random() * 1440);
            y = (int) (Math.random() * 810);
            vx = (int) (Math.random() * 10) + 1;
            vy = (int)(Math.random()*10)+1;
            new Meteor(x,y,vx,vy);
        }
    }
    
    public void getMet(int met) {
        this.met = met;
    }

    Image bg = Toolkit.getDefaultToolkit().createImage(
            System.getProperty("user.dir") + File.separator + "img" + File.separator + "background.jpg"
    );

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                System.err.println("The thread was interrupted!");
            }
        }
    }

}
