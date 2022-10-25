package Game;

import Pieces.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

  private final Piece[][] board;

  public Board() {
    board = new Piece[8][8];
  }

  public static int posToRow(int position) {
    return position / 8;
  }

  public int posToColumn(int position) {
    return position % 8;
  }

  public Piece[][] getBoard() {
    return board;
  }

  public int toPos(int row, int column) {
    return row * 8 + column;
  }

  public Piece getPieceAtPos(int position) {
    return board[posToRow(position)][posToColumn(position)];
  }

  public void makeMove(int position, Piece piece) {
    int oldPos = piece.getSquare();
    board[posToRow(oldPos)][posToColumn(oldPos)] = new Empty(oldPos, Colours.BLANK);
    board[posToRow(position)][posToColumn(position)] = piece;
    piece.setSquare(position);
    piece.setHasMoved();

    if (piece instanceof Pawn) {
      if (piece.getColour() == Colours.BLACK && piece.getSquare() >= 56) {
        board[posToRow(position)][posToColumn(position)] = new Queen(position, Colours.BLACK);
      } else if (piece.getColour() == Colours.WHITE && piece.getSquare() <= 7) {
        board[posToRow(position)][posToColumn(position)] = new Queen(position, Colours.WHITE);
      }
    }

  }

  public static Board boardClone(Board board) {
    Board clone = new Board();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        clone.board[i][j] = board.getPieceAtPos(i * 8 + j).clonePiece();
      }
    }
    return clone;
  }

  public List<List<Integer>> getAllEnemyMoves(Colours colour) {
    List<List<Integer>> allMoves = new ArrayList<>();
    for (int i = 0; i < 64; i++) {
      Piece currPiece = getPieceAtPos(i);
      if (currPiece.getColour() != colour
          && currPiece.getColour() != Colours.BLANK) {
        List<Integer> moves = currPiece.getMoves(this);
        if (moves.size() > 0) {
          moves.add(0, i);
          allMoves.add(moves);
        }
      }
    }
    return allMoves;
  }

  public List<List<Integer>> getAllMoves(Colours colour) {
    List<List<Integer>> allMoves = new ArrayList<>();
    for (int i = 0; i < 64; i++) {
      Piece currPiece = getPieceAtPos(i);
      if (currPiece.getColour() == colour) {
        List<Integer> moves = currPiece.getMoves(this);
        if (moves.size() > 0) {
          moves.add(0, i);
          allMoves.add(moves);
        }
      }
    }
    return allMoves;
  }

  public void tempMove(int newPos, int oldPos) {
    board[posToRow(newPos)][posToColumn(newPos)] = getPieceAtPos(oldPos);
    getPieceAtPos(oldPos).setSquare(newPos);
    board[posToRow(oldPos)][posToColumn(oldPos)] = new Empty(oldPos, Colours.BLANK);
  }

  public void displayMoves(List<Integer> moves) {
    for (int move : moves) {
      System.out.println(
          "(" + (char) ('A' + posToColumn(move)) + ", " + (posToRow(move) + 1) + ")");
    }
  }

  public boolean inCheck(Colours colour) {
    //TODO
    //check which pieces can see the king
    //return list of spaces which can stop the king from being in check
    int kingpos = -1;
    for (int i = 0; i < 64; i++) {
      if (getPieceAtPos(i) instanceof King && getPieceAtPos(i).getColour() == colour) {
        kingpos = i;
        break;
      }
    }
    assert (kingpos != -1);

    for (int i = 0; i < 64; i++) {
      Piece currPiece = getPieceAtPos(i);
      if (currPiece.getColour() != colour
          && currPiece.getColour() != Colours.BLANK) {
        if (!(currPiece instanceof Pawn)) {
          if (currPiece.seeMoves(this).contains(kingpos)) {
            return true;
          }
        } else {
          if (((Pawn) currPiece).takeMoves(this).contains(kingpos)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean canMove(Colours colour) {
    for (int i = 0; i < 64; i++) {
      Piece piece = getPieceAtPos(i);
      if (piece.getColour() == colour  && !piece.getMoves(this).isEmpty()) {
        return true;
      }
    }
    return false;
  }

  public WinState hasWon(Colours colour) {
    //colour whose turn it is
    if (!canMove(colour)) {
      if (inCheck(colour)) {
        if (colour == Colours.BLACK) {
          return WinState.WHITE_WINS;
        } else {
          return WinState.BLACK_WINS;
        }
      }
      return WinState.STALEMATE;
    }
    return WinState.CONTINUE;
  }

  public void displayBoard() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      sb.append(i + 1).append(" ");
      for (int j = 0; j < 8; j++) {
        sb.append(board[i][j].getName()).append(" ");
      }
      sb.append("\n");
    }
    sb.append("  A  B  C  D  E  F  G  H");
    System.out.println(sb.toString());
  }

  public void initiateBoard() {
    board[0][0] = new Rook(0, Colours.BLACK);
    board[0][1] = new Knight(1, Colours.BLACK);
    board[0][2] = new Bishop(2, Colours.BLACK);
    board[0][3] = new Queen(3, Colours.BLACK);
    board[0][4] = new King(4, Colours.BLACK);
    board[0][5] = new Bishop(5, Colours.BLACK);
    board[0][6] = new Knight(6, Colours.BLACK);
    board[0][7] = new Rook(7, Colours.BLACK);
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(8 + i, Colours.BLACK);
    }
    for (int i = 2; i < 6; i++) {
      for (int j = 0; j < 8; j++) {
        board[i][j] = new Empty(i * 8 + j, Colours.BLANK);
      }
    }
    board[7][0] = new Rook(56, Colours.WHITE);
    board[7][1] = new Knight(57, Colours.WHITE);
    board[7][2] = new Bishop(58, Colours.WHITE);
    board[7][3] = new Queen(59, Colours.WHITE);
    board[7][4] = new King(60, Colours.WHITE);
    board[7][5] = new Bishop(61, Colours.WHITE);
    board[7][6] = new Knight(62, Colours.WHITE);
    board[7][7] = new Rook(63, Colours.WHITE);
    for (int i = 0; i < 8; i++) {
      board[6][i] = new Pawn(48 + i, Colours.WHITE);
    }
  }

  public double totalScore() {
    double count = 0;
    for (int i = 0; i < 64; i++) {
      count += getPieceAtPos(i).getValue();


      count += getPieceAtPos(i).getEvalValue(i);


    }
    return count;
  }
}
