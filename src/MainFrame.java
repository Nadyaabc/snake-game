import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame
{
    public MainFrame() {
        setTitle("Змейка");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocation(450,100);
        add(new GameField());
        setVisible(true);
    }
    public static void main (String[] args)
    {
        MainFrame mw = new MainFrame();
    }

}

