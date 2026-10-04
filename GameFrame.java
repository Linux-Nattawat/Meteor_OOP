
import java.awt.*;
import java.util.concurrent.*;
import javax.swing.*;

class start {

    public static void main(String[] args) {

        new GameFrame();
    }
}

public class GameFrame extends JFrame {

    public GameFrame() {
        setSize(1440, 810);
        setTitle("Meteor Strike!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Panel(Integer.parseInt(JOptionPane.showInputDialog("Input Meteor :"))));
        setVisible(true);
    }

}

class Panel extends JPanel implements Runnable {

    private CopyOnWriteArrayList<Meteor> metArr = new CopyOnWriteArrayList<>();
    Image bg = new ImageIcon("img/background.jpg").getImage();

    public Panel(int met) {
        setSize(1440, 810);
        int x, y, vx, vy;
        for (int i = 0; i < met; i++) {
            x = (int) (Math.random() * (1440 - 130) + 10);
            y = (int) (Math.random() * (810 - 130) + 10);
            while (true) {
                vx = (int) (Math.random() * 6) - 3;
                vy = (int) (Math.random() * 6) - 3;
                if (vx != 0 && vy != 0)
                    break;
            }
            // System.out.print(vx+","+vy+"/ ");

            Meteor m = new Meteor(x, y, vx, vy);
            m.start();
            metArr.add(m);
        }

        new Thread(this).start();
    }

    private void checkPos() {
        for (int i = 0; i < metArr.size(); i++) {
            for (int j = i + 1; j < metArr.size(); j++) {
                Meteor m1 = metArr.get(i);
                Meteor m2 = metArr.get(j);

                int left1 = m1.getX();
                int right1 = m1.getX() + 72;
                int top1 = m1.getY();
                int bottom1 = m1.getY() + 72;

                int left2 = m2.getX();
                int right2 = m2.getX() + 72;
                int top2 = m2.getY();
                int bottom2 = m2.getY() + 72;

                if (right1 >= left2 && left1 <= right2 && bottom1 >= top2 && top1 <= bottom2 && !m1.isExploding() && !m2.isExploding()) {
                    if (Math.random() < 0.5) {
                        m1.explode();
                    } else {
                        m2.explode();
                    }
                }
                
            }
        }
        metArr.removeIf(m -> !m.getIsAlive());

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, this);

        for (int i = 0; i < metArr.size(); i++) {
                metArr.get(i).draw(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            checkPos();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                System.err.println("The thread was interrupted!");
            }

        }

    }

}
