import javax.swing.JFrame;

public class Main1 {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        window.setTitle("My game");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);

        MyPanel panel=new MyPanel();
        window.add(panel);

        //window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
