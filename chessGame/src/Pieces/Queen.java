package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

  public Queen(int position, Colours colour) {
    super(position, colour);
    this.name = colour == Colours.WHITE ? "\u2655" : "\u265B";
    value = colour == Colours.WHITE ? 90 : -90;
    if (colour == Colours.BLACK) {
      evalValue = new double[]{2, 1, 1, 0.5, 0.5, 1, 1, 2,
          1, 0, -0.5, 0, 0, 0, 0, 1,
          1, 0, -0.5, -0.5, -0.5, -0.5, -0.5, 1,
          0.5, 0, -0.5, -0.5, -0.5, -0.5, 0, 0,
          0.5, 0, -0.5, -0.5, -0.5, -0.5, 0, 0.5,
          1, 0, -0.5, -0.5, -0.5, -0.5, 0, 1,
          1, 0, 0, 0, 0, 0, 0, 1,
          2, 1, 1, 0.5, 0.5, 1, 1, 2};
    } else {
      evalValue = new double[]{-2, -1, -1, -0.5, -0.5, -1, -1, -2,
          -1, 0, 0, 0, 0, 0, 0, -1,
          -1, 0, 0.5, 0.5, 0.5, 0.5, 0, -1,
          -0.5, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5,
          0, 0, 0.5, 0.5, 0.5, 0.5, 0, -0.5,
          -1, 0.5, 0.5, 0.5, 0.5, 0.5, 0, -1,
          -1, 0, 0.5, 0, 0, 0, 0, -1,
          -2, -1, -1, -0.5, -0.5, -1, -1, -2};
    }
  }

  @Override
  public Piece clonePiece() {
    Queen newQueen = new Queen(position, colour);
    if (hasMoved) {
      newQueen.setHasMoved();
    }
    return newQueen;
  }

  @Override
  public List<Integer> seeMoves(Board board) {
    List<Integer> moves = movesSide(board);
    moves.addAll(movesDiag(board));
    return moves;
  }
}
