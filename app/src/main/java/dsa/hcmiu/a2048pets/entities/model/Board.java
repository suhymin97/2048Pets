package dsa.hcmiu.a2048pets.entities.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 3/25/2018.
 */

public class Board {
    private static Board instance;
    public static int row;
    public static int col;
    public static int score;
    public static int countUndo;
    public static int scoreUndo;
    public static int max = 4;
    private static int[][] board = new int[max][max];
    private Random random = new Random();
    private ArrayList<Integer> List1, List2;


    private Board() {
        init();
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public int getElement(int rowi, int col) {
        return board[rowi][col];
    }

    public void setElement(int rowi, int col, int value) {
        board[rowi][col]=value;
    }

/*	public static void printboard() {
		for (int i=0;i<20;i++) {
			for(int k=0;k<8;k++) {
				System.out.print(board[i][k]+"\t");
			}
			System.out.println("\n");
		}
	}*/

    private void init() {
        score = 0;
        countUndo = 0;
        scoreUndo = 0;
        int rCol1 = random.nextInt(max);
        int rRow1 = random.nextInt(max);
        int rCol2 = random.nextInt(max-rCol1);
        int rRow2 = random.nextInt(max-rRow1);
        for (row =0; row <max; row++)
        {
            for(col = 0; col <max; col++)
            {
                board[rCol1][rRow1] = 2;
                board[rCol2][rRow2] = (random.nextInt(2)+1)*2;
            }
        }
    }

    public void newGame() {
        for (col = 0; col < max; col++) {
            for (row = 0; row < max; row++) {
                board[col][row] = 0;
            }
        }
        init();
    }

    public void addRandomNumber(){
        int countEmpty = 0;
        for (row = 0; row < max; row++)
        {
            for (col = 0; col < max; col++)
            {
                if (board[col][row] == 0)
                {
                    countEmpty ++;
                }
            }
        }

        if(countEmpty != 0)
        {
            int addedPosition = random.nextInt(countEmpty);
            int countPosition = 0 ;
            for (row = 0 ; row < max; row++)
            {
                for (col = 0; col < max; col ++)
                {
                    if (board[col][row] == 0)
                    {
                        countPosition++;
                    }
                    if (countPosition == addedPosition + 1)
                    {
                        board[col][row] =(random.nextInt(2)+1)*2;
                        return;
                    }
                }
            }
        }
    }


    public boolean fullRow(){
        int count = 0;
        for(row= 0; row <max-1;row++) {
            for (col = 0; col < max; col++) {
                if(board[row][col] == 0 || board[row][col] == board[row+1][col] )
                {
                    count++;
                }
            }
        }
        if(count == 0) return true;
        return false;
    }

    public boolean fullCol(){
        int count=0;
        for(row= 0; row <max;row++) {
            for (col = 0; col < max-1; col++) {
                if (board[row][col] == 0 || board[row][col] == board[row][col + 1]) {
                    count++;
                }
            }
        }
        if(count == 0) return true;
        return false;
    }

    public boolean fullSpecial()
    {
        boolean check = false;
        if(board[3][3] == 0 || board[3][3] == board[3][2]|| board[3][3] == board[2][3] ) return false;
        else return true;
    }

}
