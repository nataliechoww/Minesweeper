public class Board {
  private static String[][] board;
  private static String[][] displayBoard;
  private String hasBomb = "💣";
  private int x;
  private int y;
  private int numBombs;
  private int numUncovered = 0;
  public boolean firstPlay = true;
//Make variable boolean so that if the player clicks on a bomb on the first click, boolean turns true and we reinitialize the board.
  //like, I was thinking about either this be a public or private variable, yeah, I am wondering whether or not we should make it public so we can change it in the main file oh let's go to the main file
  //what does the boolean do ohhhh ok; what is the problem rn?
  //we should do all of this in the main class, not board class, cus then we can re call the function
  //we should just make the variable in the main class
  //also i need to shower but i take quick showers so ill be back in 10 mins - but i think we can make this within the while loop - like an if statement - we call the 

  //wait i have another idea
  //can i call u and u just put on headphones and dont speak
  public Board() {
    displayBoard = new String[8][10];
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 10; j++) {
        displayBoard[i][j] = "⬜";
      }
    }
  }
  
  public Board(int numBombs) {
    board = new String[8][10];
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
    if(numUncovered == 80-numBombs || (displayBoard[xcord][ycord] != ("F") && board[xcord][ycord].equals("💣"))) {
      gameOver = true;
    }
    return gameOver;
  }

  //prints hidden board
  public void display() {
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 10; j++) {
          System.out.print(board[i][j] + " ");
        }
      System.out.println();
    }
  }
  
  //prints displayed board
  public void display1() {
    System.out.print("   ");
    for (int col = 0; col < 9; col++) {
      System.out.print(col + "  ");
    }
    System.out.println(9);
    System.out.println("   —————————————————————————————");
      for (int i = 0; i < 8; i++) {
        System.out.print(i + "| ");
        for (int j = 0; j < 10; j++) {
          System.out.print(displayBoard[i][j] + " ");
        }
      System.out.println();
    }
  }
  }