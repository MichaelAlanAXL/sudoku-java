package src.main.java.dev.michael.sudoku;

import java.awt.*;
import javax.swing.*;
import src.main.java.dev.michael.sudoku.model.Board;

public class App {
    private static final JTextField[][] cells = new JTextField[9][9];

    private static boolean checkWin() {
        // Verifica linhas
        for (int row = 0; row < 9; row++) {
            boolean[] seen = new boolean[9];
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                if (text.isEmpty()) return false; 

                int num = Integer.parseInt(text) - 1;
                if (seen[num]) return false;
                seen[num] = true;
            }
        }
        // Verifica colunas
        for (int col = 0; col < 9; col++) {
            boolean[] seen = new boolean[9];
            for (int row = 0; row < 9; row++) {
                int num = Integer.parseInt(cells[row][col].getText()) - 1;
                if (seen[num]) return false;
                seen[num] = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Board board = new Board();
        int[][] initialBoard = board.getBoard();
        
        JFrame frame = new JFrame("Sudoku");
        JPanel panel = new JPanel(new GridLayout(9,9,2,2)); 
        
        JButton newGameButton = new JButton("Novo Jogo");
        newGameButton.addActionListener(e -> {
            board.gerarNovoJogo();
            int[][] novoTabuleiro = board.getBoard();

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    JTextField field = cells[row][col];
                    int value = novoTabuleiro[row][col];

                    if (value != 0) {
                        field.setText(String.valueOf(value));
                        field.setEditable(false);
                        field.setBackground(Color.LIGHT_GRAY);
                    } else {
                        field.setText("");
                        field.setEditable(true);
                        field.setBackground(Color.WHITE);
                    }
                }
            }
        });
        JButton hintButton = new JButton("Dica (3x)");
        JButton clearButton = new JButton("Limpar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameButton);
        buttonPanel.add(hintButton);
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                JTextField field = cells[row][col];
                field.setHorizontalAlignment(JTextField.CENTER);

                int value = initialBoard[row][col];

                // campos preenchidos inicialmente bloquear edição
                if (value != 0) {
                    field.setText(String.valueOf(value));
                    field.setEditable(false);
                    field.setBackground(Color.LIGHT_GRAY);
                } else {
                    // somente os campos vazios podem ser preenchido/editado
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

                                if (checkWin()) {
                                    JOptionPane.showMessageDialog(frame, "Parabéns, você ganhou!");
                                }
    
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(frame, "Digite apenas números!");
                                field.setText("");
                                return false;
                            }
    
                            return true;
                        }
                    });
                }

                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0)? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;
                field.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
    
                panel.add(field);

            }
        }

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
