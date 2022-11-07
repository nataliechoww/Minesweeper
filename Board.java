public class Board {
  private static String[][] board;
  private static String[][] displayBoard;
  private String hasBomb = "💣";
  private int x;
  private int y;
  private int numBombs;
  private int countOfBombs = 0;
  private int numUncovered = 0;

  public Board() {
    this.displayBoard = new String[8][10];
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 10; j++) {
        displayBoard[i][j] = "X";
      }
    }
  }
  
  public Board(int numBombs) {
    this.board = new String[8][10];
    x = 8;
    y = 10;
    this.numBombs = numBombs;
    board = new String[8][10];
  
    // fill board with bombs
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
  
  //checks cell that player chose
  public void uncover(int xcord, int ycord) {
    displayBoard[xcord][ycord] = board[xcord][ycord];
      numUncovered++;
    
  }

  //flags selected cell
  public void flag(int xcord, int ycord) {
    displayBoard[xcord][ycord] = "F";
  }

  //checks to see if all flags are in place
  public boolean getStatus(int numBombs, int xcord, int ycord) {
    this.numBombs = numBombs;
    boolean gameOver = false;
    if(numUncovered == 80-numBombs || board[xcord][ycord].equals("💣")) {
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
