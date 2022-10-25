package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

  public Bishop(int position, Colours colour) {
    super(position, colour);
    name = colour == Colours.WHITE ? "\u2657" : "\u265D";
    value = colour == Colours.WHITE ? 30 : -30;
    if (colour == Colours.BLACK) {
      evalValue = new double[]{2, 1, 1, 1, 1, 1, 1, 2,
          1, -0.5, 0, 0, 0, 0, -0.5, 1,
          1, -1, -1, -1, -1, -1, -1, 1,
          1, 0, -1, -1, -1, -1, 0, 1,
          1, -0.5, -0.5, -1, -1, -0.5, -0.5, 1,
          1, 0, -0.5, -1, -1, -0.5, 0, 1,
          1, 0, 0, 0, 0, 0, 0, 1,
          2, 1, 1, 1, 1, 1, 1, 2};
    } else {
      evalValue = new double[]{-2, -1, -1, -1, -1, -1, -1, -2,
          -1, 0, 0, 0, 0, 0, 0, -1,
          -1, 0, 0.5, 1, 1, 0.5, 0, -1,
          -1, 0.5, 0.5, 1, 1, 0.5, 0.5, -1,
          -1, 0, 1, 1, 1, 1, 0, -1,
          -1, 1, 1, 1, 1, 1, 1, -1,
          -1, 0.5, 0, 0, 0, 0, 0.5, -1,
          -2, -1, -1, -1, -1, -1, -1, -2};
    }
  }

  @Override
  public Piece clonePiece() {
    Bishop newBishop = new Bishop(position, colour);
    if (hasMoved) {
      newBishop.setHasMoved();
    }
    return newBishop;
  }


  @Override
  public List<Integer> seeMoves(Board board) {
    return super.movesDiag(board);
  }
}


