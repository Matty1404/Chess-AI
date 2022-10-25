package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

  public Knight(int position, Colours colour) {
    super(position, colour);
    this.name = colour == Colours.WHITE ? "\u2658" : "\u265E";
    value = colour == Colours.WHITE ? 30 : -30;
    if (colour == Colours.BLACK) {
      evalValue = new double[]{5, 4, 3, 3, 3, 3, 4, 5,
          4, 2, 0, -0.5, -0.5, 0, 2, 3,
          3, -0.5, -1, -1.5, -1.5, -1, -0.5, 3,
          3, 0, -1.5, -2, -2, -1.5, 0, 3,
          3, -0.5, -1.5, -2, -2, -1.5, 0.5, 3,
          3, 0, -1, -1.5, -1.5, -1, 0, 3,
          4, 2, 0, 0, 0, 0, 2, 4,
          5, 4, 3, 3, 3, 3, 4, 5};
    } else {
      evalValue = new double[]{-5, -4, -3, -3, -3, -3, -4, -5,
          -4, -2, 0, 0, 0, 0, -2, -4,
          -3, 0, 1, 1.5, 1.5, 1, 0, -3,
          -3, 0.5, 1.5, 2, 2, 1.5, -0.5, -3,
          -3, 0, 1.5, 2, 2, 1.5, 0, -3,
          -3, 0.5, 1, 1.5, 1.5, 1, 0.5, -3,
          -4, -2, 0, 0.5, 0.5, 0, -2, -3,
          -5, -4, -3, -3, -3, -3, -4, -5};
    }
  }

  @Override
  public Piece clonePiece() {
    Knight newKnight = new Knight(position, colour);
    if (hasMoved) {
      newKnight.setHasMoved();
    }
    return newKnight;
  }

  public List<Integer> seeMoves(Board board) {
    List<Integer> moves = new ArrayList<>();
    List<Integer> possibleMoves = new ArrayList<>();
    moves.add(position + 15);
    moves.add(position - 15);
    moves.add(position + 17);
    moves.add(position - 17);
    moves.add(position + 6);
    moves.add(position - 6);
    moves.add(position + 10);
    moves.add(position - 10);

    for (int move : moves) {
      if (!(move < 0 || move >= 8 * 8 || board.getPieceAtPos(move).getColour() == colour
          || Math.abs(position % 8 - move % 8) > 2)) {
        possibleMoves.add(move);
      }
    }
    return possibleMoves;
  }
}
