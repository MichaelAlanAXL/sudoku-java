package src.main.java.dev.michael.sudoku.model;

import java.util.Random;

public class Board {
    private int[][] board = new int[9][9];
    private Random random = new Random();

    public Board() {
        gerarNovoJogo();
    }

    public void gerarNovoJogo() {
        int[][] base = {
            {5,3,0, 0,7,0, 0,0,0},
            {6,0,0, 1,9,5, 0,0,0},
            {0,9,8, 0,0,0, 0,6,0},
            {8,0,0, 0,6,0, 0,0,3},
            {4,0,0, 8,0,3, 0,0,1},
            {7,0,0, 0,2,0, 0,0,6},
            {0,6,0, 0,0,0, 2,8,0},
            {0,0,0, 4,1,9, 0,0,5},
            {0,0,0, 0,8,0, 0,7,9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = base[i][j];
            }            
        }

        embaralharLinhas();
    }

    private void embaralharLinhas() {
        for (int i = 0; i < 9; i += 3) {
            int l1 = i + random.nextInt(3);
            int l2 = i + random.nextInt(3);
            int[] temp = board[l1];
            board[l1] = board[l2];
            board[l2] = temp;
        }
    }

    public int[][] getBoard() {
        return board;
    }
    
}
