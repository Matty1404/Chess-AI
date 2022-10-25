package Game;

import Pieces.Colours;
import java.util.Scanner;

public class playGame {

  private static void printWon(WinState winState) {
    switch (winState) {
      case WHITE_WINS -> System.out.println("white won");
      case BLACK_WINS -> System.out.println("black won");
      case STALEMATE -> System.out.println("stalemate");
      default -> System.out.println("next Move:");
    }
  }

  public static void main(String[] args) {
    Scanner moveInput = new Scanner(System.in);
    Board board = new Board();
    board.initiateBoard();
    board.displayBoard();
    System.out.println("\n");
    Colours turn = Colours.WHITE;
    while (true) {
      if (board.hasWon(turn) != WinState.CONTINUE) {
        printWon(board.hasWon(turn));
        break;
      }
      //choose square/piece to move
      //if empty list ie no moves/not the right colour, do not highlight/ask to enter new piece
      //enter a place on the board to move to (highlighted/listed)
      //change turn
      String select;
      System.out.println("select which piece to move");
      select = moveInput.nextLine();
      while (select.charAt(0) < 'A' || select.charAt(0) > 'H' || select.charAt(1) < '1'
          || select.charAt(1) > '8' ||
          board.getPieceAtPos(board.toPos(select.charAt(1) - '1', select.charAt(0) - 'A'))
              .getColour() != turn) {
        System.out.println("not a correct square to choose, please select right one");
        select = moveInput.nextLine();
      }

      int selectedPiece = board.toPos(select.charAt(1) - '1', select.charAt(0) - 'A');
      board.displayMoves(board.getPieceAtPos(selectedPiece).getMoves(board));
      System.out.println("select where to move or type back to choose a piece to move");
      select = moveInput.nextLine();
      if (select.equals("Back")) {
        continue;
      }
      while (select.charAt(0) < 'A' || select.charAt(0) > 'H' || select.charAt(1) < '1'
          || select.charAt(1) > '8' || !board.getPieceAtPos(selectedPiece).getMoves(board)
          .contains(board.toPos(select.charAt(1) - '1', select.charAt(0) - 'A'))) {
        System.out.println("not a valid move or type back to choose a piece to move");
        select = moveInput.nextLine();
        if (select.equals("Back")) {
          break;
        }
      }
      if (select.equals("Back")) {
        continue;
      }
      int moveTo = board.toPos(select.charAt(1) - '1', select.charAt(0) - 'A');

      board.makeMove(moveTo, board.getPieceAtPos(selectedPiece));
      board.displayBoard();
      turn = turn == Colours.WHITE ? Colours.BLACK : Colours.WHITE;
    }
  }
}
