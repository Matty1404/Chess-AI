package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

  public Rook(int position, Colours colour) {
    super(position, colour);
    this.name = colour == Colours.WHITE ? "\u2656" : "\u265C";
    value = colour == Colours.WHITE ? 50 : -50;
    if (colour == Colours.BLACK) {
      evalValue = new double[]{0, 0, 0, -0.5, -0.5, 0, 0, 0,
          0.5, 0, 0, 0, 0, 0, 0, 0.5,
          0.5, 0, 0, 0, 0, 0, 0, 0.5,
          0.5, 0, 0, 0, 0, 0, 0, 0.5,
          0.5, 0, 0, 0, 0, 0, 0, 0.5,
          0.5, 0, 0, 0, 0, 0, 0, 0.5,
          -0.5, -1, -1, -1, -1, -1, -1, -0.5,
          0, 0, 0, 0, 0, 0, 0, 0};
    } else {
      evalValue = new double[]{0, 0, 0, 0, 0, 0, 0, 0,
          0.5, 1, 1, 1, 1, 1, 1, 0.5,
          -0.5, 0, 0, 0, 0, 0, 0, -0.5,
          -0.5, 0, 0, 0, 0, 0, 0, -0.5,
          -0.5, 0, 0, 0, 0, 0, 0, -0.5,
          -0.5, 0, 0, 0, 0, 0, 0, -0.5,
          -0.5, 0, 0, 0, 0, 0, 0, -0.5,
          0, 0, 0, 0.5, 0.5, 0, 0, 0};
    }
  }

  @Override
  public Piece clonePiece() {
    Rook newRook = new Rook(position, colour);
    if (hasMoved) {
      newRook.setHasMoved();
    }
    return newRook;
  }

  @Override
  public List<Integer> seeMoves(Board board) {
    return super.movesSide(board);
  }

}
