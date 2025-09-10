package src.main.java.dev.michael.sudoku;

import java.awt.*;
import javax.swing.*;

public class App {
    private static final JTextField[][] cells = new JTextField[9][9];
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        JPanel panel = new JPanel(new GridLayout(9,9,2,2));        

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                JTextField field = cells[row][col];
                field.setHorizontalAlignment(JTextField.CENTER);

                final int currentRow = row;
                final int currentCol = col;

                field.setInputVerifier(new InputVerifier() {
                    @Override
                    public boolean verify(JComponent input) {
                        String text = field.getText();

                        if (text.isEmpty()) return true;

                        try {
                            int value = Integer.parseInt(text);

                            // Aceita somente numeros entre 1 e 9
                            if (value < 1 || value > 9) {
                                JOptionPane.showMessageDialog(frame, "Digite apenas números de 1 a 9!");
                                field.setText("");
                                return false;
                            }

                            // Verificar duplicado na mesma linha
                            for (int c = 0; c < 9; c++) {
                                if (c != currentCol && text.equals(cells[currentRow][c].getText())) {
                                    JOptionPane.showMessageDialog(frame, "Número já existe nesta linha!");
                                    field.setText("");
                                    return false;
                                }
                            }

                            // Verificar duplicado na mesma coluna
                            for (int r = 0; r < 9; r++) {
                                if (r != currentRow && text.equals(cells[r][currentCol].getText())) {
                                    JOptionPane.showMessageDialog(frame, "Número já existe nesta coluna!");
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

                panel.add(field);
            }
        }

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
