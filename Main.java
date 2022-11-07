import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to my Minesweeper Replica! The aim of the game is to uncover every 'X' without a bomb in the 8 x 10 block. Watch out! There are 10 hidden bombs under the array of X's. You can either flag the X's if you think there is a bomb underneath, or uncover the X. When you uncover an X, there might be 1's, 2's, and so on. These numbers tell you how many bombs are next to that cell. Good luck!");
    
    int[] arr = new int[2];
    String choice;
    Board board = new Board(10);
    Board displayBoard = new Board();
    board.display();
    displayBoard.display1();
    int counts = 0;

    while(!board.getStatus(10, arr[0], arr[1])) {
    
    System.out.println("Type F to flag cell or type U to uncover cell:");
    Scanner input = new Scanner(System.in);
      choice = input.nextLine();

    System.out.println("Now type your two coordinates, pressing enter after typing each one:");
    
    for(int i = 0; i < 2; i++) {
      Scanner input1 = new Scanner(System.in);
      arr[i] = input1.nextInt();
    }
      
    if(choice.equals("F")) {
      displayBoard.flag(arr[0],arr[1]);
      board.display();
      displayBoard.display1();
    }

    if(choice.equals("U")) {
      if(counts==0) {
        displayBoard.setFirstClick(arr[0],arr[1]);
        counts++;
      }
      else {
        board.uncover(arr[0],arr[1]);
      }
      board.display();
      displayBoard.display1();
    }
      
    }
    System.out.println("You have lost:(");
  }
}

