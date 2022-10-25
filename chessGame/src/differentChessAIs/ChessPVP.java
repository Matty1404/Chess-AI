package differentChessAIs;

import Game.Board;
import Game.BoardT;
import Game.ChessButton;
import Game.GameState;
import Game.WinState;
import Pieces.Colours;
import Pieces.Piece;
import java.awt.event.ActionEvent;
import java.util.List;

public class ChessPVP extends BoardT {


  public ChessPVP(Board board, Colours turn) {
    super(board, turn);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ChessButton button = (ChessButton) e.getSource();
    Piece piece = board.getPieceAtPos(button.getPosition());
    if (state == GameState.CHOOSE_PIECE) {
      if (piece.getColour() == turn) {
        List<Integer> moves = piece.getMoves(board);
        button.highlight(
            button.getPosition() / 8, button.getPosition() % 8);
        for (int move : moves) {
          int row = move / 8;
          int col = move % 8;
          labels[row][col].highlight(row, col);
        }
        prevPos = button.getPosition();
        prevMoves = moves;
        state = GameState.CHOOSE_MOVE;
      }
    } else {
      //check if same piece chosen then must reset prev stuff
      //otherwise rest prev stuff and update board
      labels[prevPos / 8][prevPos % 8].set(prevPos / 8, prevPos % 8);
      for (int move : prevMoves) {
        int row = move / 8;
        int col = move % 8;
        labels[row][col].set(row, col);
      }
      state = GameState.CHOOSE_PIECE;
      if (piece.getColour() == turn && button.getPosition() != prevPos) {
        List<Integer> moves = piece.getMoves(board);
        button.highlight(
            button.getPosition() / 8, button.getPosition() % 8);
        for (int move : moves) {
          int row = move / 8;
          int col = move % 8;
          labels[row][col].highlight(row, col);
        }
        prevMoves = moves;
        state = GameState.CHOOSE_MOVE;
      } else if (prevMoves.contains(button.getPosition())) {
        piece = board.getPieceAtPos(prevPos);
        board.makeMove(button.getPosition(), piece);
        labels[prevPos / 8][prevPos % 8].setText(" ");
        button.setText(piece.getName());
        turn = turn == Colours.WHITE ? Colours.BLACK : Colours.WHITE;
        if (board.hasWon(turn) != WinState.CONTINUE) {
          System.out.println("THERE IS A WINNER");
        }
      }
      prevPos = button.getPosition();
    }
  }
}
