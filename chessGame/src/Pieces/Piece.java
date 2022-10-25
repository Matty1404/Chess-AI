package Pieces;

import Game.Board;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

  protected String name;
  protected int position;
  protected final Colours colour;
  protected boolean hasMoved;
  protected int value;
  protected double[] evalValue;

  public Piece(int position, Colours colour) {
    this.colour = colour;
    this.position = position;
    hasMoved = false;
  }

  public double getEvalValue(int pos) {
    return evalValue[pos];
  }

  public abstract Piece clonePiece();

  public String getName() {
    return name;
  }

  public void setHasMoved() {
    hasMoved = true;
  }

  public int getSquare() {
    return position;
  }

  public int getValue() {
    return value;
  }

  public void setSquare(int position) {
    this.position = position;
  }

  public Colours getColour() {
    return colour;
  }

  public abstract List<Integer> seeMoves(Board board);

  public List<Integer> getMoves(Board board) {
    List<Integer> possibleMoves = new ArrayList<>();
    for (int move : seeMoves(board)) {
      if (notInCheckAfter(board, move, getSquare(), getColour())) {
        possibleMoves.add(move);
      }
    }
    return possibleMoves;
  }

  //checks to see if still in check after a move
  public boolean notInCheckAfter(Board board, int newPos, int oldPos, Colours colour) {
    Board clone = Board.boardClone(board);
    clone.tempMove(newPos, oldPos);
    boolean incheck = clone.inCheck(colour);
    board.getPieceAtPos(oldPos).setSquare(oldPos);
    return !incheck;
  }

  public List<Integer> enemyCanSee(Board board, Colours colour) {
    List<Integer> canSee = new ArrayList<>();
    for (int i = 0; i < 64; i++) {
      Piece currPiece = board.getPieceAtPos(i);
      if (currPiece.getColour() != colour
          && currPiece.getColour() != Colours.BLANK) {
        if (!(currPiece instanceof Pawn)) {
          canSee.addAll(currPiece.getMoves(board));
        } else {
          canSee.addAll(((Pawn) currPiece).takeMoves(board));
        }
      }
    }
    return canSee;
  }

  protected List<Integer> movesDiag(Board board) {
    List<Integer> moves = new ArrayList<>();
    int newPos = position;
    int row = position / 8;
    int column = position % 8;
    while (row < 7 && column < 7) {
      row++;
      column++;
      newPos += 9;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    row = position / 8;
    column = position % 8;
    newPos = position;
    while (row > 0 && column < 7) {
      row--;
      column++;
      newPos -= 7;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    row = position / 8;
    column = position % 8;
    newPos = position;
    while (row < 7 && column > 0) {
      row++;
      column--;
      newPos += 7;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    newPos = position;
    row = position / 8;
    column = position % 8;
    while (row > 0 && column > 0) {
      column--;
      row--;
      newPos -= 9;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    return moves;
  }

  protected List<Integer> movesSide(Board board) {
    List<Integer> moves = new ArrayList<>();
    int newPos = position;
    int row = position / 8;
    int column = position % 8;
    while (row < 7) {
      row++;
      newPos += 8;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    row = position / 8;
    newPos = position;
    while (row > 0) {
      row--;
      newPos -= 8;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    newPos = position;
    while (column > 0) {
      column--;
      newPos--;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    newPos = position;
    column = position % 8;
    while (column < 7) {
      column++;
      newPos++;
      if (board.getPieceAtPos(newPos).getColour() == Colours.BLANK) {
        moves.add(newPos);
      } else if (board.getPieceAtPos(newPos).getColour() == colour) {
        break;
      } else {
        moves.add(newPos);
        break;
      }
    }
    return moves;
  }
}
