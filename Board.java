public class Board {
  private static String[][] board;
  private static String[][] displayBoard;
  private String hasBomb = "💣";
  private int x;
  private int y;
  private int numBombs;
  private int numUncovered = 0;

  public Board() {
    displayBoard = new String[8][10];
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 10; j++) {
        displayBoard[i][j] = "X";
      }
    }
  }
  
  //constructor for hidden board
  public Board(int numBombs) {
    board = new String[8][10];
    x = 8;
    y = 10;
    this.numBombs = numBombs;
    board = new String[8][10];
  
    // fills board with bombs in random locations
    for (int i = 0; i < numBombs; i++) {
      x = (int) (Math.random() * 8);
      y = (int) (Math.random() * 10);
      board[x][y] = hasBomb;
    }

    // fill the rest of the board with 0's
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 10; j++) {
        if (board[i][j] != hasBomb) {
          board[i][j] = "0";
        }
      }
    }
    
    //fills board with bomb hints
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 10; j++) {
        if (board[i][j] != hasBomb) {
          int count = 0;
          if((i+1<8) && board[i+1][j].equals("💣")) {
            count++;
          }
          if((i-1>-1) && board[i-1][j].equals("💣")) {
            count++;
          }
          if((j+1<10) && board[i][j+1].equals("💣")) {
            count++;
          }
          if((j-1>-1) && board[i][j-1].equals("💣")) {
            count++;
          }
          if((i+1<8) && (j+1<10) && board[i+1][j+1].equals("💣")) {
            count++;
          }
          if((i+1<8) && (j-1>-1) && board[i+1][j-1].equals("💣")) {
            count++;
          }
          if((i-1>-1) && (j+1<10) && board[i-1][j+1].equals("💣")) {
            count++;
          }
          if((i-1>-1) && (j-1>-1) && board[i-1][j-1].equals("💣")) {
            count++;
          }
          board[i][j] = String.valueOf(count);
        }
      }
    }
  }
  //sets the board on the player's first entry so that cells around the first entry are revealed
  public void setFirstClick(int xcord, int ycord) {
    displayBoard[xcord][ycord] = board[xcord][ycord];
    numUncovered++;
    if((xcord+1<8) && board[xcord+1][ycord]!=("💣")) {
      displayBoard[xcord+1][ycord] = board[xcord+1][ycord];
      numUncovered++;
    }
    if((xcord-1>-1) && board[xcord-1][ycord]!=("💣")) {
      displayBoard[xcord-1][ycord] = board[xcord-1][ycord];
      numUncovered++;
    }
    if((ycord+1<10) && board[xcord][ycord+1]!=("💣")) {
      displayBoard[xcord][ycord+1] = board[xcord][ycord+1];
      numUncovered++;
    }
    if((ycord-1>-1) && board[xcord][ycord-1]!=("💣")) {
      displayBoard[xcord][ycord-1] = board[xcord][ycord-1];
      numUncovered++;
    }
    if((xcord+1<8) && (ycord+1<10) && board[xcord+1][ycord+1]!=("💣")) {
      displayBoard[xcord+1][ycord+1] = board[xcord+1][ycord+1];
      numUncovered++;
    }
    if((xcord+1<8) && (ycord-1>-1) && board[xcord+1][ycord-1]!=("💣")) {
      displayBoard[xcord+1][ycord-1] = board[xcord+1][ycord-1];
      numUncovered++;
    }
    if((xcord-1>-1) && (ycord+1<10) && board[xcord-1][ycord+1]!=("💣")) {
     displayBoard[xcord-1][ycord+1] = board[xcord-1][ycord+1];
     numUncovered++;
    }
    if((xcord-1>-1) && (ycord-1>-1) && board[xcord-1][ycord-1]!=("💣")) {
      displayBoard[xcord-1][ycord-1] = board[xcord-1][ycord-1];
      numUncovered++;
    }
  }
  
  //uncovers the cell the player chose
  public void uncover(int xcord, int ycord) {
    displayBoard[xcord][ycord] = board[xcord][ycord];
      numUncovered++;
    
  }

  //flags selected cell
  public void flag(int xcord, int ycord) {
    displayBoard[xcord][ycord] = "F";
  }

  //checks to see if player lost the game from uncovering a bomb OR if player won the game by uncovering all non-bomb cells
  public boolean getStatus(int numBombs, int xcord, int ycord) {
    this.numBombs = numBombs;
    boolean gameOver = false;
    if(numUncovered == 80-numBombs || (displayBoard[xcord][ycord] != "F" && board[xcord][ycord].equals("💣"))) {
      gameOver = true;
    }
    return gameOver;
  }

  
  //prints displayed board
  public void display1() {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 10; j++) {
          System.out.print(displayBoard[i][j] + " ");
        }
      System.out.println();
    }
  }
  }
