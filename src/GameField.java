import javax.swing.*;
import java.awt.*;
public class GameField extends JPanel{
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int DOTS = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int [] x = new int[DOTS];
    private int [] y = new int[DOTS];
    private int dots;
    private Timer timer;
    private boolean right = true;
    private boolean left;
    private boolean up;
    private boolean down;
    private boolean inGame = true;

    public GameField()
    {
        setBackground(Color.PINK);
        loadImages();
    }

    public void loadImages()
    {
        ImageIcon
    }

}
