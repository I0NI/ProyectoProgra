package newmaquinaarcade;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMaquinaArcade {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú de Juegos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new BackgroundPanel(); // Usamos el panel personalizado con fondo
        panel.setLayout(new BorderLayout()); // Usamos BorderLayout

        // Agregamos la imagen "nombre.png" en la parte superior
        ImageIcon titleImageIcon = new ImageIcon(NewMaquinaArcade.class.getResource("/resources/nombre.png"));
        JLabel titleLabel = new JLabel(titleImageIcon);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 0)); // Margen izquierdo y superior
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20)); // Layout centrado con espacio vertical
        buttonsPanel.setOpaque(false); // Hacemos que el panel no sea opaco para que el fondo se vea

        JButton botonTotito = createStyledButtonWithImage("Totito", "/resources/totito.jpg");
        botonTotito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Totito totitoGame = new Totito();
                    totitoGame.setVisible(true);
                });
            }
        });

        JButton botonSnake = createStyledButtonWithImage("Snake", "/resources/snake.png");
        JButton botonPong = createStyledButtonWithImage("Pong", "/resources/pong.png");
        JButton botonSecreto = createStyledButtonWithImage("Juego Secreto", "/resources/interrogacion.jpg");

        buttonsPanel.add(botonTotito);
        buttonsPanel.add(botonSnake);
        buttonsPanel.add(botonPong);
        buttonsPanel.add(botonSecreto);

        panel.add(buttonsPanel, BorderLayout.SOUTH); // Botones en la parte inferior y centrados
        frame.add(panel);
        frame.setVisible(true);
    }

    private static JButton createStyledButtonWithImage(String text, String imagePath) {
        JButton button = new JButton(text);
        Font buttonFont = button.getFont().deriveFont(Font.BOLD, 14); // Cambia el tamaño de la fuente

        try {
            BufferedImage image = ImageIO.read(NewMaquinaArcade.class.getResource(imagePath));
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            button.setIcon(new ImageIcon(scaledImage));
            button.setFont(buttonFont); // Aplica la nueva fuente al botón
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto debajo de la imagen
            button.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado

            // Crea un borde redondeado con un radio de 10
            Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            button.setBorder(BorderFactory.createCompoundBorder(roundedBorder, BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return button;
    }

    // Clase para crear un JPanel con fondo GIF
    static class BackgroundPanel extends JPanel {
        private ImageIcon background;

        BackgroundPanel() {
            try {
                background = new ImageIcon(NewMaquinaArcade.class.getResource("/resources/fondo2.gif"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
