package Scripts.BodyScripts;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My game");

        GamePanel gamePanel = new GamePanel(window);
        window.add(gamePanel);
        if (!gamePanel.isFullscreen) {
            window.pack();
        }

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
