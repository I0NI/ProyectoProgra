package newmaquinaarcade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Totito extends JFrame {
    private char currentPlayer = 'X';
    private JButton[][] buttons = new JButton[3][3];
    private JLabel turnLabel;

    public Totito() {
        setTitle("Totito - Juego");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        turnLabel = new JLabel("Turno de: " + currentPlayer);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        add(turnLabel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        gamePanel.setPreferredSize(new Dimension(300, 300));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 50));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setContentAreaFilled(false); // Elimina el fondo del botón
                buttons[row][col].setBorderPainted(false); // Elimina el borde del botón
                buttons[row][col].setOpaque(false); // Hace que el botón sea transparente

                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton buttonClicked = (JButton) e.getSource();
                        if (buttonClicked.getText().equals("")) {
                            buttonClicked.setText(String.valueOf(currentPlayer));
                            buttonClicked.setForeground(currentPlayer == 'X' ? Color.RED : Color.GREEN);
                            buttonClicked.setEnabled(false);
                            if (checkForWin()) {
                                JOptionPane.showMessageDialog(null, "¡" + currentPlayer + " ha ganado!");
                                resetGame();
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(null, "Empate");
                                resetGame();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                turnLabel.setText("Turno de: " + currentPlayer);
                            }
                        }
                    }
                });
                gamePanel.add(buttons[row][col]);
            }
        }

        add(gamePanel, BorderLayout.CENTER);
    }

    private boolean checkForWin() {
        // Verificar filas, columnas y diagonales para determinar si hay un ganador
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(buttons[i][0].getText(), buttons[i][1].getText(), buttons[i][2].getText())
                    || checkRowCol(buttons[0][i].getText(), buttons[1][i].getText(), buttons[2][i].getText())) {
                return true;
            }
        }
        return checkRowCol(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText())
                || checkRowCol(buttons[0][2].getText(), buttons[1][1].getText(), buttons[2][0].getText());
    }

    private boolean checkRowCol(String c1, String c2, String c3) {
        return !c1.equals("") && c1.equals(c2) && c2.equals(c3);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
        turnLabel.setText("Turno de: " + currentPlayer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Totito totito = new Totito();
            totito.setVisible(true);
        });
    }
}

