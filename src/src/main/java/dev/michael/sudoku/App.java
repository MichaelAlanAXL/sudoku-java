package src.main.java.dev.michael.sudoku;

import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Mini Sudoku 3x3");
        JPanel panel = new JPanel(new GridLayout(3,3,5,5));
        
        JTextField[] cells = new JTextField[9];

        for (int i = 0; i < 9; i++) {
            cells[i] = new JTextField();

            // Validação
            cells[i].setInputVerifier(new InputVerifier() {
                @Override
                public boolean verify(JComponent input) {
                    JTextField field = (JTextField) input;
                    String text = field.getText();

                    try {
                        int value = Integer.parseInt(text);

                        // validar números
                        if (value < 1 || value > 9) {
                            JOptionPane.showMessageDialog(frame, "Digite apenas números de 1 a 9!");
                            field.setText("");
                            return false;
                        }
                        // validar números repetidos
                        for (JTextField other : cells) {
                            if (other != field && text.equals(other.getText())) {
                                JOptionPane.showMessageDialog(frame, "Número já usado!");
                                field.setText("");
                                return false;
                            }
                        }


                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame, "Digite apenas números!");
                        field.setText("");
                        return false;
                    }
                    return true;
                }
            });

            panel.add(cells[i]);            
        }

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
