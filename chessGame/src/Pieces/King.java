package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

  public King(int position, Colours colour) {
    super(position, colour);
    name = colour == Colours.WHITE ? "\u2654" : "\u265A";
    value = colour == Colours.WHITE ? 900 : -900;

    if (colour == Colours.BLACK) {
      evalValue = new double[]{-2, -3, -1, 0, 0, -1, -3, -2,
          -2, -2, 0, 0, 0, 0, -2, -2,
          1, 2, 2, 2, 2, 2, 2, 1,
          2, 3, 3, 4, 4, 3, 3, 2,
          2, 3, 3, 4, 4, 3, 3, 2,
          2, 3, 3, 4, 4, 3, 3, 2,
          2, 3, 3, 4, 4, 3, 3, 2,
          2, 3, 3, 4, 4, 3, 3, 2};
    } else {
      evalValue = new double[]{-3, -4, -4, -5, -5, -4, -4, -3,
          -3, -4, -4, -5, -5, -4, -4, -3,
          -3, -4, -4, -5, -5, -4, -4, -3,
          -3, -4, -4, -5, -5, -4, -4, -3,
          -2, -3, -3, -4, -4, -3, -3, -2,
          -1, -2, -2, -2, -2, -2, -2, -1,
          2, 2, 0, 0, 0, 0, 2, 2,
          2, 3, 1, 0, 0, 1, 3, 2};
    }
  }

  @Override
  public Piece clonePiece() {
    King newKing = new King(position, colour);
    if (hasMoved) {
      newKing.setHasMoved();
    }
    return newKing;
  }

  @Override
  public List<Integer> seeMoves(Board board) {
    List<Integer> moves = new ArrayList<>();
    List<Integer> possibleMoves = new ArrayList<>();
    moves.add(position + 1);
    moves.add(position - 1);
    moves.add(position + 8);
    moves.add(position - 8);
    moves.add(position + 9);
    moves.add(position - 9);
    moves.add(position + 7);
    moves.add(position - 7);

    for (int move : moves) {
      if (!(move < 0 || move >= 8 * 8 || board.getPieceAtPos(move).getColour() == colour
          || Math.abs(position % 8 - move % 8) > 1)) {
        possibleMoves.add(move);
      }
    }

    return possibleMoves;
  }


}
