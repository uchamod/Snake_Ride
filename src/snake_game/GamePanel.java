package snake_game;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    static final int swidth = 600;
    static final int shight = 600;
    static final int unitsize = 25;
    static final int gameunit = (swidth * shight / unitsize);
    static final int delay = 150;
    final int x[] = new int[gameunit];
    final int y[] = new int[gameunit];
    Image apple;
    int bodypart = 6;
    int eat;
    int eatx;
    int eaty;
    char derection = 'R';
    boolean run = false;
    Timer time;
    Random random;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(swidth, shight));
        this.setFocusable(true);
        this.setBackground(Color.black);
        this.addKeyListener(new Mykey());
        apple=new ImageIcon("D:/apple").getImage();
        start();
    }

    public void start() {
        neweat();
        run = true;
        time = new Timer(delay, this);
        time.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public void draw(Graphics g) {
        
       
       if(run){
          
        // for (int i = 0; i < shight / unitsize; i++) {
           // g.drawLine(i * unitsize, 0, i * unitsize, shight);
           // g.drawLine(0, i * unitsize, swidth, i * unitsize);
       // }
            g.setColor(Color.red);
            g.fillOval(eatx, eaty, unitsize, unitsize);
           // g2d.drawImage(apple, eatx, eaty, unitsize, unitsize, null);
           // g2d.drawImage(apple, eatx, eaty, null);
           
         for (int i = 0; i < bodypart; i++) {
            if (i == 0) {
                g.setColor(new Color(0,255,51));
                g.fillRect(x[i], y[i], unitsize, unitsize);
            } else {
                g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                g.fillRect(x[i], y[i], unitsize, unitsize);
             

            }
             
        
        }
             g.setColor(Color.red);
             g.setFont(new Font("Ink Free",Font.BOLD,25));
             FontMetrics met=getFontMetrics(g.getFont());
             g.drawString("Score "+eat, (swidth-met.stringWidth("score "+eat))/2,g.getFont().getSize());
    }else{
           over(g);
       }
    }

    public void neweat() {
        eatx = random.nextInt((int) swidth / unitsize) * unitsize;
        eaty = random.nextInt((int) shight / unitsize) * unitsize;
    }

    public void move() {
        for (int i = bodypart; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (derection) {
            case 'U':
                y[0] = y[0] - unitsize;
                break;
            case 'D':
                y[0] = y[0] + unitsize;
                break;
            case 'L':
                x[0] = x[0] - unitsize;
                break;
            case 'R':
                x[0] = x[0] + unitsize;
                break;
        }

    }

    public void checkeat() {
            if(x[0]==eatx && y[0]==eaty){
                bodypart++;
                eat++;
                neweat();
            }
    }

    public void checkcollisions() {
        for (int i = bodypart; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                run = false;
            }

        }
        if (x[0] < 0) {
            run = false;
        }
        if (x[0] > swidth) {
            run = false;
        }
        if (y[0] < 0) {
            run = false;
        }
        if (y[0] > shight) {
            run = false;
        }
        if (!run) {
            time.stop();
        }

    }

    public void over(Graphics g) {
         g.setColor(Color.red);
         g.setFont(new Font("Ink Free",Font.BOLD,80));
         FontMetrics met=getFontMetrics(g.getFont());
         g.drawString("Game Over!", (swidth-met.stringWidth("Game Over"))/2,shight/2);
         
             g.setColor(Color.red);
             g.setFont(new Font("Ink Free",Font.BOLD,25));
             FontMetrics met2=getFontMetrics(g.getFont());
             g.drawString("Score "+eat, (swidth-met2.stringWidth("score "+eat))/2,g.getFont().getSize());
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (run) {
            move();
            checkeat();
            checkcollisions();
        }
        repaint();
    }

    /**
     *
     */
    public class Mykey extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    if (derection != 'L') {
                        derection = 'R';
                    }
                    break;

                case KeyEvent.VK_LEFT:
                    if (derection != 'R') {
                        derection = 'L';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (derection != 'D') {
                        derection = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (derection != 'U') {
                        derection = 'D';
                    }
                    break;
            }

        }
    }

}
