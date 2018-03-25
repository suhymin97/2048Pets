package dsa.hcmiu.a2048pets.entities.handle;

import java.util.ArrayList;
import dsa.hcmiu.a2048pets.entities.model.Board;


/**
 * Created by Admin on 3/25/2018.
 */

public class HandleGame{
    ArrayList<Integer> List1,List2,List3,ListUndo;
    public static int highScore = 0;
    public static int best = 0;
    int row;
    int col;

    public HandleGame (){
        Board.getInstance();
        getBest();
    }

    public void Undo(){
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();
        int countChange = 0;

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List1.add(Board.getInstance().getElement(row,col));
            }
        }
        if(Board.countUndo < 3 && Board.score > 100)
        {
            for (int row = 0 ; row < 4; row++)
            {
                for (int col = 0; col < 4; col ++)
                {
                    Board.getInstance().setElement(row,col,ListUndo.get(col*4+row));
                }
            }
            Board.score = Board.scoreUndo;
        }

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List2.add(Board.getInstance().getElement(row,col));
            }
        }

        for (int i = 0; i < List1.size(); i++)
        {
            if(List1.get(i).equals(List2.get(i)))
            {
                countChange++;
            }
        }

        if(countChange == List1.size())
        {
            countChange=0;
        }
        else
        {
            Board.countUndo++;
        }

    }

    public void pushUp(){
        for (col =0; col <4; col++)
        {
            for (int loop = 0; loop <3; loop++)
            {
                for( row = 3; row > 0; row--)
                {
                    if(Board.getInstance().getElement(row-1,col) == 0)
                    {
                        int t = Board.getInstance().getElement(row, col);
                        Board.getInstance().setElement(row-1,col,t);
                        Board.getInstance().setElement(row,col,0);
                    }
                }
            }
        }
    }

    public void moveUp(){
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List1.add(Board.getInstance().getElement(row,col));
            }
        }

        pushUp();

        for(row=0; row<4; row++)
        {
            for(col= 0; col <3;col++)
            {
                if(Board.getInstance().getElement(row,col) == Board.getInstance().getElement(row+1,col))
                {
                    int t = Board.getInstance().getElement(row+1,col) + Board.getInstance().getElement(row,col);
                    Board.getInstance().setElement(row,col,t) ;
                    Board.getInstance().setElement(row+1,col,0);
                    Board.score = Board.score + Board.getInstance().getElement(row,col);
                }
            }
        }

        pushUp();

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List2.add(Board.getInstance().getElement(row,col));
            }
        }

        for (int i = 0; i < List1.size(); i++)
        {
            if(List1.get(i).equals(List2.get(i)))
            {
                Board.countMove = Board.countMove +1;
            }
        }

        if(Board.countMove == List1.size()){Board.countMove = 0;}
        else
        {
            Board.getInstance().addRandomNumber();
            Board.countMove = 0;
            ListUndo = List1;
            Board.scoreUndo = Board.score - 100;
        }
    }

    public void pushDown(){
        for (col =0; col <4; col++)
        {
            for (int loop = 0; loop <3; loop++)
            {
                for(row = 0; row < 3; row++)
                {
                    if(Board.getInstance().getElement(row+1,col) == 0)
                    {
                        int t = Board.getInstance().getElement(row,col);
                        Board.getInstance().setElement(row+1,col,t);
                        Board.getInstance().setElement(row,col,0);
                    }
                }
            }
        }
    }

    public void moveDown(){
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();

        for(col= 0; col <4;col++)
        {
            for(row=0; row<4; row++)
            {
                List1.add(Board.getInstance().getElement(row,col));
            }
        }

        pushDown();

        for(row=3; row>=0; row--)
        {
            for(col= 0; col <4;col++)
            {
                if(Board.getInstance().getElement(row,col) == Board.getInstance().getElement(row-1,col))
                {
                    int t = Board.getInstance().getElement(row-1,col) + Board.getInstance().getElement(row,col);
                    Board.getInstance().setElement(row,col,t);
                    Board.getInstance().setElement(row-1,col,0);
                    Board.score = Board.score + Board.getInstance().getElement(row,col);
                }
            }
        }

        pushDown();

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List2.add(Board.getInstance().getElement(row,col));
            }
        }

        for (int i = 0; i < List1.size(); i++)
        {
            if(List1.get(i).equals(List2.get(i)))
            {
                Board.countMove = Board.countMove +1;
            }
        }

        if(Board.countMove == List1.size()){Board.countMove = 0;}
        else
        {
            Board.getInstance().addRandomNumber();
            Board.countMove = 0;
            ListUndo = List1;
            Board.scoreUndo = Board.score - 100;
        }
    }

    public void pushRight(){
        for (int loop = 0; loop <2; loop++)
        {
            for (row =0; row <4; row++)
            {
                for(col = 0; col < 3; col++)
                {
                    if(Board.getInstance().getElement(row,col+1) == 0)
                    {
                        int t =Board.getInstance().getElement(row,col);
                        Board.getInstance().setElement(row,col+1,t);
                        Board.getInstance().setElement(row,col,0);
                    }
                }
            }
        }
    }

    public void moveRight(){
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List1.add(Board.getInstance().getElement(row,col));
            }
        }

        pushRight();

        for (row =0; row <4; row ++)
        {
            for(col = 3; col > 0 ; col--)
            {
                if(Board.getInstance().getElement(row,col) == Board.getInstance().getElement(row,col-1))
                {
                    int t = Board.getInstance().getElement(row,col-1) + Board.getInstance().getElement(row,col);
                    Board.getInstance().setElement(row,col,t);
                    Board.getInstance().setElement(row,col-1,0);
                    Board.score = Board.score + Board.getInstance().getElement(row,col);
                }
            }
        }

        pushRight();

        for(row=0; row<4; row++)
        {
            for(col= 0; col <4;col++)
            {
                List2.add(Board.getInstance().getElement(row,col));
            }
        }

        for (int i = 0; i < List1.size(); i++)
        {
            if(List1.get(i).equals(List2.get(i)))
            {
                Board.countMove++;
            }
        }

        if(Board.countMove == List1.size()){Board.countMove = 0;}
        else
        {
            Board.getInstance().addRandomNumber();
            Board.countMove = 0;
            ListUndo = List1;
            Board.scoreUndo = Board.score - 100;
        }
    }

    public void pushLeft(){
        for (int loop = 0; loop <2; loop++)
        {
            for (row =0; row <4; row++)
            {
                for(col = 3; col > 0; col--)
                {
                    if(Board.getInstance().getElement(row,col-1) == 0)
                    {
                        Board.getInstance().setElement(row,col-1, Board.getInstance().getElement(row,col));
                        Board.getInstance().setElement(row,col,0);
                    }
                }
            }
        }
    }

    public void moveLeft(){
        List1 = new ArrayList<Integer>();
        List2 = new ArrayList<Integer>();

        for(col= 0; col <4;col++)
        {
            for(row=0; row<4; row++)
            {
                List1.add(Board.getInstance().getElement(row,col));
            }
        }

        pushLeft();

        for (row =0; row <4;row++)
        {
            for(col = 0; col <3 ; col++)
            {
                if(Board.getInstance().getElement(row,col) == Board.getInstance().getElement(row,col+1))
                {
                    int t = Board.getInstance().getElement(row,col+1) + Board.getInstance().getElement(row,col);
                    Board.getInstance().setElement(row,col, t);
                    Board.getInstance().setElement(row,col+1,0);
                    Board.score = Board.score + Board.getInstance().getElement(row,col);
                }

            }
        }
        pushLeft();
        for(col= 0; col <4;col++)
        {
            for(row=0; row<4; row++)
            {
                List2.add(Board.getInstance().getElement(row,col));
            }
        }
        for (int i = 0; i < List1.size(); i++)
        {
            if(List1.get(i).equals(List2.get(i)))
            {
                Board.countMove ++;
            }
        }
        if(Board.countMove == List1.size()){Board.countMove = 0;}
        else
        {
            Board.getInstance().addRandomNumber();
            Board.countMove = 0;
            ListUndo = List1;
            Board.scoreUndo = Board.score - 100;
        }
    }

    public boolean gameOver(){
        return Board.getInstance().fullCol() && Board.getInstance().fullRow() && Board.getInstance().fullSpecial();
    }

    public void saveBest(){
        List3 = new ArrayList<Integer>();
        if(Board.score > best)
        {
            highScore = Board.score;
            best = Board.score;
        }
        else
        {
            highScore = best;
        }
        List3.add(highScore);
        HandleFile.writeFile(List3);

    }

    public int getBest(){
        List3 = HandleFile.readFile();
        for (int i = 0; i < List3.size(); i++)
        {
            if(List3.get(i) > best)
            {
                best = List3.get(i);
            }
        }
        return best;
    }

}
