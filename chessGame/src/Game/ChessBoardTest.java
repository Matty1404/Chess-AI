package Game;

import Pieces.Colours;
import differentChessAIs.ChessPVP;
import differentChessAIs.MoveAI;

public class ChessBoardTest {

  private static void printWon(WinState winState) {
    switch (winState) {
      case WHITE_WINS -> System.out.println("white won");
      case BLACK_WINS -> System.out.println("black won");
      case STALEMATE -> System.out.println("stalemate");
      default -> System.out.println("next Move:");
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Board board = new Board();
    board.initiateBoard();
    Colours turn = Colours.WHITE;
    //BoardT boardT = new ChessPVP(board, turn);
    BoardT boardT = new MoveAI(board, turn);
    boardT.display();

  }
}