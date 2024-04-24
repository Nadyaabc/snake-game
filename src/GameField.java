import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 352;
    private final int DOT_SIZE = 16;
    private final int DOTS = 400;
    private Image dot;
    private Image apple;
    private Image snakeHead;
    private int appleX;
    private int appleY;
    private int[] x = new int[DOTS];
    private int[] y = new int[DOTS];
    private int dots;
    private Timer timer;
    //private Timer timerStart;
    private boolean right = true;
    private boolean left;
    private boolean up;
    private boolean down;
    private boolean inGame = true;
    public int count = 0;

    public GameField() {
        // setBackground(new Color(245, 202, 195));
        setBackground(Color.BLACK);
        loadImages();
        initGame();
        addKeyListener(new FieldkeyListener());
        setFocusable(true);
    }

    public void initGame() {
        //  timerStart = new Timer(250,this);
        // timerStart.start();
        dots = 3; // начальное количество точек
        for (int i = 0; i < dots; i++) {
            //начальные значения позиций
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }

        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(23) * DOT_SIZE;
        appleY = new Random().nextInt(23) * DOT_SIZE;

    }

    public void loadImages() {
        ImageIcon iid = new ImageIcon("src/images/img-1.png");
        dot = iid.getImage();
        ImageIcon iia = new ImageIcon("src/images/img.png");
        apple = iia.getImage();
        ImageIcon iish = new ImageIcon("src/images/snakehead.png");
        snakeHead = iish.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String cnt = "COUNT: " + count;
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            g.drawImage(snakeHead, x[0], y[0], this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
            cnt = "COUNT: " + count;
            g.setColor(Color.white);
            g.drawString(cnt, 10, 15);
        } else {
            String str = "GAME OVER";
            g.setColor(Color.white);
            g.drawString(str, 170, 175);
            g.drawString(cnt, 180, 155);

        }
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            count++;
            createApple();
        }
    }

    public void checkCollissions() {
        for (int i = dots; i > 0; i--)
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
                break;
            }
        if (x[0] > SIZE) inGame = false;
        if (x[0] < 0) inGame = false;
        if (y[0] > SIZE) inGame = false;
        if (y[0] < 0) inGame = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollissions();
            move();
        }
        repaint();
    }

    class FieldkeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = down = false;
            }
            if (key == KeyEvent.VK_UP && !down) {
                up = true;
                left = right = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = right = false;
            }
        }
    }
}
