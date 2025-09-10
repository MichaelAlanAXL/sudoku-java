package src.main.java.dev.michael.sudoku;

import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Mini Sudoku 2x2");
        
        // grid 2 x 2
        JPanel panel = new JPanel(new GridLayout(2,2,5,5));

        JTextField cell1 = new JTextField();
        JTextField cell2 = new JTextField();
        JTextField cell3 = new JTextField();
        JTextField cell4 = new JTextField();

        panel.add(cell1);
        panel.add(cell2);
        panel.add(cell3);
        panel.add(cell4);

        frame.add(panel);

        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
