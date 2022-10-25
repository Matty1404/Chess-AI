package Pieces;

import Game.Board;
import java.util.Collections;
import java.util.List;

public class Empty extends Piece {

  public Empty(int position, Colours colour) {
    super(position, colour);
    name = " ";
    evalValue = new double[]{0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0};
    value = 0;
  }

  @Override
  public List<Integer> seeMoves(Board board) {
    return Collections.emptyList();
  }

  @Override
  public Piece clonePiece() {
    return new Empty(position, colour);
  }

  @Override
  public List<Integer> getMoves(Board board) {
    return Collections.emptyList();
  }
}
