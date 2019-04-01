package minesweeper;

/***
 * OzU CS 222 Note:
 * This code is taken from
 * http://www.planet-source-code.com/vb/scripts/ShowCode.asp?txtCodeId=4853&lngWId=2
 *
 ***/

// The "Minesweeper" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Minesweeper extends Applet implements MouseListener
{
    Button table[] [];      //Array of Buttons
    boolean bomb[] [];      //array true if bomb is present on button [x][y]
    boolean flag[] [];      //array true if flag is present at button [x][y]
    boolean exposed[] [];   //used for exposing 0's. If true then a 0 has been exposed
    boolean checkWinBool[] [];  // if [x][y] = true then the button has a number on it or it is a bomb (used for checking if game is over)
    int count = 0, bombsRemaining;          //counting the number of bombs placed
    String surbombs;        //number of bombs surrounding button [x][y] (is a string so that we can setLabel for the button)
    int randx, randy;       //random ints for bombs
    int row = 16, col = 30, numbombs = 99, Setup = 3; //number of rows columns, and bombs
    int sizex = 780, sizey = 492;
    Font font;
    Panel gamePanel, menuPanel, difficultyPanel, mainPanel;
    Label bombs, timeRemaning, newGame;
    Button RestartE, RestartM, RestartH;
    GridLayout gameLayout;
    public void init ()
    {
        setLayout (new BorderLayout ());
        gameLayout = new GridLayout (row, col);
        gamePanel = new Panel (gameLayout);
        font = new Font ("ComicSans", Font.BOLD, 17);
        setFont (font);
        menuPanel = new Panel (new BorderLayout ());
        mainPanel = new Panel ();
        timeRemaning = new Label ("");
        newGame = new Label ("New Game");
        RestartE = new Button ("Easy");
        RestartE.addMouseListener (this);
        RestartM = new Button ("Medium");
        RestartM.addMouseListener (this);
        RestartH = new Button ("Hard");
        RestartH.addMouseListener (this);
        difficultyPanel = new Panel ();
        bombsRemaining = numbombs;
        bombs = new Label (Integer.toString (bombsRemaining));
        table = new Button [row] [col];
        bomb = new boolean [row] [col];
        flag = new boolean [row] [col];
        exposed = new boolean [row] [col];
        checkWinBool = new boolean [row] [col];
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                table [x] [y] = new Button ();
                table [x] [y].addMouseListener (this);
                gamePanel.add (table [x] [y]);
            }
        }
        //these for loops set up the buttons and sets all the boolean arrays to = false
        add (menuPanel, "North");
        add (gamePanel, "Center");
        menuPanel.add (bombs, "West");
        menuPanel.add (mainPanel, "North");
        mainPanel.add (newGame);
        menuPanel.add (difficultyPanel, "Center");
        difficultyPanel.add (RestartE);
        difficultyPanel.add (RestartM);
        difficultyPanel.add (RestartH);
        menuPanel.setBackground(Color.lightGray);
        newGame.setBackground(Color.lightGray);
        newGame.setForeground(Color.black);
        bombs.setBackground(Color.lightGray);
        bombs.setForeground(Color.white);
        restartGame(row, col, numbombs, row, col, sizex, sizey);
    }


    public void restartGame(int row, int col, int numbombs, int prow, int pcol, int sizex, int sizey)
    {
        //mBar.Restart (table, row, col);
        //mBar.SetupMenu ();
        setSize (sizex, sizey);
        invalidate();
        validate();
        gameLayout.setRows (row);
        gameLayout.setColumns (col);
        int count = 0;
        bombsRemaining = numbombs;
        bombs.setText (Integer.toString (bombsRemaining));
        for (int x = 0 ; x < prow ; x++)
        {
            for (int y = 0 ; y < pcol ; y++)
            {
                gamePanel.remove (table [x] [y]);
            }
        }
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {

                table [x] [y].setEnabled (true);
                table [x] [y].setLabel ("");
                table [x] [y].setBackground (Color.gray);
                table [x] [y].setForeground (Color.white);
                gamePanel.add (table [x] [y]);
                bomb [x] [y] = false;
                flag [x] [y] = false;
                exposed [x] [y] = false;
                checkWinBool[x] [y] = false;
            }
        }
        setSize (sizex, sizey);
        invalidate();
        validate();
        //adds the bombs to random places on the grid
        while (count < numbombs)
        {
            randx = (int) (Math.random () * (row));
            randy = (int) (Math.random () * (col));
            if (bomb [randx] [randy] == false)
            {
                bomb [randx] [randy] = true;
                checkWinBool[randx] [randy] = true;
                count++;
            }
        }
    }


    public void mouseClicked (MouseEvent e)
    {
        int prow = 0, pcol = 0;
        if (e.getSource () == RestartE)
        {
            row = 10;
            col = 10;
            numbombs = 10;
            sizex = 300;
            sizey = 346;
            if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 1;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 1;
            }
        }
        if (e.getSource () == RestartM)
        {
            row = 16;
            col = 16;
            numbombs = 40;
            sizex = 496;
            sizey = 540;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 2;
            }
            else if (Setup == 3)
            {
                prow = 16;
                pcol = 30;
                Setup = 2;
            }
        }
        if (e.getSource () == RestartH)
        {
            row = 16;
            col = 30;
            numbombs = 99;
            sizex = 780;
            sizey = 492;
            if (Setup == 1)
            {
                prow = 10;
                pcol = 10;
                Setup = 3;
            }
            else if (Setup == 2)
            {
                prow = 16;
                pcol = 16;
                Setup = 3;
            }
        }
        if (e.getSource () == RestartE || e.getSource () == RestartM || e.getSource () == RestartH)
            restartGame(row, col, numbombs, prow, pcol, sizex, sizey);
        boolean gameover = false;
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (e.getSource () == table [x] [y])
                {
                    if (e.getButton () == e.BUTTON1 && flag [x] [y] == false) //if left click, and there is no flag on the button
                    {
                        if (bomb [x] [y] == true)  // if you you click on a bomb, results in game over
                        {
                            table [x] [y].setLabel ("*");
                            gameover ();
                            table [x] [y].setBackground (Color.black);
                            gameover = true;
                            break;

                        }
                        exposed [x] [y] = true;
                        checkWinBool[x] [y] = true; // these set to true mean that the button has been clicked
                        surbombs = Integer.toString (surroundingBombs (x, y)); //gets the number of surrounding buttons with bombs and sets it to a string so that it is possible to setLabel
                        table [x] [y].setLabel (surbombs); // sets the label to be the number of bombs in the 8 surrounding buttons
                        if (surroundingBombs (x, y) == 0)
                        {
                            check (x, y);          //calls a recursive method so that if a "0" is there the surrounding 8 buttins must be exposed and if one of those is "0" it too must be exposed and so on
                        }
                    }
                    else if (e.getButton () == e.BUTTON3)  // if it is a right click
                    {
                        if (flag [x] [y] == true) //if there is a flag already present set it so that there is no flag
                        {
                            table [x] [y].setLabel ("");
                            table [x] [y].setForeground (Color.white);
                            flag [x] [y] = false;
                            checkWinBool[x] [y] = false;
                            bombsRemaining++;
                        }
                        else if (checkWinBool[x] [y] == false || bomb [x] [y] == true) //if there is no flag, set it so there is a flag
                        {
                            table [x] [y].setLabel ("|>");
                            table [x] [y].setForeground (Color.red);
                            flag [x] [y] = true;
                            checkWinBool[x] [y] = true;
                            bombsRemaining--;
                        }
                        bombs.setText (Integer.toString (bombsRemaining));

                    }
                    else if (e.getButton () == e.BUTTON2 && flag [x] [y] == false && checkWinBool[x] [y] == true && bomb [x] [y] == false) //if both left and right click at the same time
                    { //only executes if there is no flag, it has been exposed, and no bomb
                        if (getFlags(x, y) == surroundingBombs (x, y)) //checks that the number of getFlags around [x][y] = the number of bombs around [x][y]
                        {
                            for (int q = x - 1 ; q <= x + 1 ; q++)
                            {
                                for (int w = y - 1 ; w <= y + 1 ; w++)
                                {
                                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                                        break;
                                    if (bomb [q] [w] == false && flag [q] [w] == true) //if there is no bomb, but there is a flag its game over
                                    {
                                        gameover ();
                                        gameover = true;
                                        break;
                                    }
                                }
                            }
                            if (gameover == false)
                            {
                                expose (x, y);     //eposes the surrounding 8 buttons
                                check (x, y);      //checks if any of those are "0" and calls the recursive method
                            }
                        }
                    }
                    if (gameover == false) //this just calls the method for changing the colours of the buttons if they have been clicked
                        clicked ();
                }

            }
        }
        checkWin();
    }


    public void clicked ()     //changes the color of the buttons and if [x][y] is "0" set the label to nothing
    {
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (checkWinBool[x] [y] == true && flag [x] [y] == false && bomb [x] [y] == false)
                    table [x] [y].setBackground (Color.darkGray);
                if (flag [x] [y] == false && surroundingBombs (x, y) == 0)
                    table [x] [y].setLabel ("");
            }
        }
    }


    public int getFlags(int x, int y)  // get the number of surrounding getFlags
    {
        int surFlags = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;

                    if (flag [q] [w] == true)
                    {
                        surFlags++;
                    }
                    break;
                }
            }
        }
        return surFlags;
    }


    public int surroundingBombs (int x, int y)  // checks surrounding 8 squares for number of bombs (it does include itself, but has already been checked for a bomb so it won't matter)
    {
        int surBombs = 0;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (bomb [q] [w] == true)
                        surBombs++;
                    break;
                }
            }
        }
        return surBombs;
    }


    public void expose (int x, int y)  // exposes the surrounding 8 buttons
    {
        String surrbombs;
        exposed [x] [y] = true;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (flag [q] [w] == true)
                        break;

                    checkWinBool[q] [w] = true;
                    surrbombs = Integer.toString (surroundingBombs (q, w));
                    table [q] [w].setLabel (surrbombs);
                    break;

                }
            }
        }
    }


    public void surrender(int x, int y)  //this is what checks if a surrounding button has "0" is so expose it and check around the exposed buttons until there is no more "0"'s
    {
        String surrbombs;
        for (int q = x - 1 ; q <= x + 1 ; q++)
        {
            for (int w = y - 1 ; w <= y + 1 ; w++)
            {
                while (true)
                {
                    if (q < 0 || w < 0 || q >= row || w >= col) // makes sure that it wont have an error for buttons next to the wall
                        break;
                    if (flag [q] [w] == true)
                        break;
                    if (exposed [q] [w] == false && surroundingBombs (q, w) == 0)
                    {
                        expose (q, w);
                        check (q, w);
                    }
                    break;
                }
            }
        }
    }


    public void check (int x, int y)  //calls the surrender method but is neccesary because of the expose first
    {
        expose (x, y);
        surrender(x, y);
    }


    public void checkWin()    //checks if all the button without bombs have been pressed
    {
        boolean allexposed = true;
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (flag [x] [y] == true && bomb [x] [y] == false)
                    allexposed = false;
                if (checkWinBool[x] [y] == false)
                {
                    allexposed = false;
                    break;
                }
            }
        }
        if (allexposed != false)
        {
            gameover ();
            int x2 = (int) col / 2;
            int y2 = (int) row / 2;
            table [y2] [x2 - 4].setLabel ("Y");
            table [y2] [x2 - 3].setLabel ("O");
            table [y2] [x2 - 2].setLabel ("U");
            table [y2] [x2 - 1].setLabel ("");
            table [y2] [x2].setLabel ("W");
            table [y2] [x2 + 1].setLabel ("I");
            table [y2] [x2 + 2].setLabel ("N");
            table [y2] [x2 + 3].setLabel ("!");
            table [y2] [x2 + 4].setLabel ("!");
            for (int i = -4 ; i < 5 ; i++)
            {
                table [y2] [x2 + i].setBackground (Color.black);
                table [y2] [x2 + i].setForeground (Color.white);
            }
        }
    }


    public void gameover ()  // is called if bomb is clicked or on the double click if flag is not on a bomb
    {
        for (int x = 0 ; x < row ; x++)
        {
            for (int y = 0 ; y < col ; y++)
            {
                if (bomb [x] [y] == true)
                {
                    table [x] [y].setLabel ("*"); //exposes all bombs
                    table [x] [y].setBackground (Color.red);
                }
                table [x] [y].setEnabled (false); //disable all buttons
            }
        }
        int x2 = (int) col / 2;
        int y2 = (int) row / 2;
        table [y2] [x2 - 4].setLabel ("Y");
        table [y2] [x2 - 3].setLabel ("O");
        table [y2] [x2 - 2].setLabel ("U");
        table [y2] [x2 - 1].setLabel ("");
        table [y2] [x2].setLabel ("L");
        table [y2] [x2 + 1].setLabel ("O");
        table [y2] [x2 + 2].setLabel ("S");
        table [y2] [x2 + 3].setLabel ("E");
        table [y2] [x2 + 4].setLabel ("!");
        for (int i = -4 ; i < 5 ; i++)
        {
            table [y2] [x2 + i].setBackground (Color.black);
            table [y2] [x2 + i].setForeground (Color.white);
        }
    }


    /////////////////////////////////////////////////////////////////
    //These are not used but are necessary for mouseListener
    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }
}


