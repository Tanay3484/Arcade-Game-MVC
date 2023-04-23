import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import com.zetcode.*;

public class GameMenu {
    private JFrame frame;
    private JPanel panel;
    private JButton ticTacToeBtn;
    private JButton tetrisBtn;
    private BufferedImage backgroundImage;

    public GameMenu() {
        frame = new JFrame("Game Menu");
        panel = new JPanel();
        ticTacToeBtn = new JButton("Play Tic-Tac-Toe");
        tetrisBtn = new JButton("Play Tetris");
    }

    private void loadGif() {
        try {
            URL url = new URL("https://thumbs.dreamstime.com/b/game-font-arcade-video-bit-arcade-retro-machine-88469923.jpg");
            backgroundImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void build() {
        panel.setLayout(new GridBagLayout());
        loadGif();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(ticTacToeBtn, c);
        c.gridy = 1;
        panel.add(tetrisBtn, c);

        ticTacToeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicTacToe.main(new String[0]);
            }
        });

        tetrisBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    var game = new Tetris();
                    game.setVisible(true);
                });
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(),
                            getHeight(), this);
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GameMenu menu = new GameMenu();
        menu.build();
    }
}