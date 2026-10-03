
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class start {

    public static void main(String[] args) {

        new GameFrame();
    }
}

public class GameFrame extends JFrame {

    public int width = 1440;
    public int height = 810;

    public GameFrame() {
        setSize(1440, 810);
        setTitle("Meteor Strike!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Panel(Integer.parseInt(JOptionPane.showInputDialog("Input Meteor :"))));
        setVisible(true);
    }

}

class Panel extends JPanel implements Runnable {

    private ArrayList<Meteor> meteor = new ArrayList<>();
    Image bg = Toolkit.getDefaultToolkit().createImage(
            System.getProperty("user.dir") + File.separator + "img" + File.separator + "background.jpg"
    );

    public Panel(int met) {
        setSize(1440, 810);
        int x, y, vx, vy;
        for (int i = 0; i < met; i++) {
            x = (int) (Math.random() * 1440);
            y = (int) (Math.random() * 810);
            vx = (int) (Math.random() * 20) - 10;
            vy = (int) (Math.random() * 20) - 10;
            Meteor m = new Meteor(x, y, vx, vy);
            m.start();
            meteor.add(m);
        }
    }

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
