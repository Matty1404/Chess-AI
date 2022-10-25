package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

  public Pawn(int position, Colours colour) {
    super(position, colour);
    this.name = colour == Colours.WHITE ? "\u2659" : "\u265F";
    value = colour == Colours.WHITE ? 10 : -10;
    if (colour == Colours.BLACK) {
      evalValue = new double[]{0, 0, 0, 0, 0, 0, 0, 0,
          -0.5, -1, -1, 2, 2, -1, -1, -0.5,
          -0.5, 0.5, 1, 0, 0, 1, 0.5, -0.5,
          0, 0, 0, -2, -2, 0, 0, 0,
          -0.5, -0.5, -1, -2.5, -2.5, -1, -0.5, -0.5,
          -1, -1, -2, -3, -3, -2, -1, -1,
          -5, -5, -5, -5, -5, -5, -5, -5,
          0, 0, 0, 0, 0, 0, 0, 0};
    } else {
      evalValue = new double[]{0, 0, 0, 0, 0, 0, 0, 0,
          5, 5, 5, 5, 5, 5, 5, 5,
          1, 1, 2, 3, 3, 2, 1, 1,
          0.5, 0.5, 1, 2.5, 2.5, 1, 0.5, 0.5,
          0, 0, 0, 2, 2, 0, 0, 0,
          0.5, -0.5, -1, 0, 0, -1, -0.5, 0.5,
          0.5, 1, 1, -2, -2, 1, 1, 0.5,
          0, 0, 0, 0, 0, 0, 0, 0};
    }
  }

  @Override
  public Piece clonePiece() {
    Pawn newPawn = new Pawn(position, colour);
    if (hasMoved) {
      newPawn.setHasMoved();
    }
    return newPawn;
  }

  @Override
  public void setSquare(int pos) {
    super.setSquare(pos);
    if (pos / 8 == 7 || pos / 8 == 0) {
      //changePieceTo(piece);
    }
  }

  @Override
  public List<Integer> seeMoves(Board board) {
    List<Integer> moves = new ArrayList<>(takeMoves(board));
    if (colour == Colours.WHITE) {
      if (board.getPieceAtPos(position - 8).getColour() == Colours.BLANK) {
        moves.add(position - 8);
        if (!hasMoved && board.getPieceAtPos(position - 16).getColour() == Colours.BLANK) {
          moves.add(position - 16);
        }
      }
    } else {
      if (board.getPieceAtPos(position + 8).getColour() == Colours.BLANK) {
        moves.add(position + 8);
        if (!hasMoved && board.getPieceAtPos(position + 16).getColour() == Colours.BLANK) {
          moves.add(position + 16);
        }
      }
    }
    List<Integer> finalMoves = new ArrayList<>();
    for (int move : moves) {
      if (notInCheckAfter(board, move, position, colour)) {
        finalMoves.add(move);
      }
    }
    return finalMoves;
  }


  public List<Integer> takeMoves(Board board) {
    List<Integer> moves = new ArrayList<>();
    if (colour == Colours.WHITE) {
      if ((position - 9) >= 0 && (position - 9) < 64
          && board.getPieceAtPos(position - 9).getColour() != Colours.BLANK
          && board.getPieceAtPos(position - 9).getColour() != colour
          && Math.abs(position % 8 - (position - 9) % 8) == 1) {
        moves.add(position - 9);
      }
      if ((position - 7) >= 0 && (position - 7) < 64
          && board.getPieceAtPos(position - 7).getColour() != Colours.BLANK
          && board.getPieceAtPos(position - 7).getColour() != colour
          && Math.abs(position % 8 - (position - 7) % 8) == 1) {
        moves.add(position - 7);
      }
    } else {
      if ((position + 9) >= 0 && (position + 9) < 64
          && board.getPieceAtPos(position + 9).getColour() != Colours.BLANK
          && board.getPieceAtPos(position + 9).getColour() != colour
          && Math.abs(position % 8 - (position + 9) % 8) == 1) {
        moves.add(position + 9);
      }
      if ((position + 7) >= 0 && (position + 7) < 64
          && board.getPieceAtPos(position + 7).getColour() != Colours.BLANK
          && board.getPieceAtPos(position + 7).getColour() != colour
          && Math.abs(position % 8 - (position + 7) % 8) == 1) {
        moves.add(position + 7);
      }
    }
    List<Integer> finalMoves = new ArrayList<>();
    for (int move : moves) {
      if (notInCheckAfter(board, move, position, colour)) {
        finalMoves.add(move);
      }
    }
    return finalMoves;
  }
}
