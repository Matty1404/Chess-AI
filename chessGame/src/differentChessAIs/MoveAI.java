package differentChessAIs;

import Game.Board;
import Game.BoardT;
import Game.ChessButton;
import Game.GameState;
import Game.WinState;
import Pieces.Colours;
import Pieces.Piece;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveAI extends BoardT {

  public MoveAI(Board board, Colours turn) {
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

        //added stuff here
        if (madeMove != null) {
          int pos1 = madeMove.valueA;
          int pos2 = madeMove.valueB;
          labels[pos1 / 8][pos1 % 8].removeMoved(pos1);
          labels[pos2 / 8][pos2 % 8].removeMoved(pos2);
        }
        // stopped

        piece = board.getPieceAtPos(prevPos);
        board.makeMove(button.getPosition(), piece);
        labels[prevPos / 8][prevPos % 8].setText(" ");
        button.setText(board.getPieceAtPos(button.getPosition()).getName());

        //added stuff here
        labels[prevPos / 8][prevPos % 8].highlightMoved();
        button.highlightMoved();
        madeMove = new Pair<>(prevPos, button.getPosition());
        // stopped

        if (board.hasWon(Colours.BLACK) != WinState.CONTINUE) {
          System.out.println("THERE IS A WINNER");
        } else {


          minimaxAI();




        }
        if (board.hasWon(Colours.WHITE) != WinState.CONTINUE) {
          System.out.println("THERE IS A WINNER");
          //lock all buttons
        }
      }
      prevPos = button.getPosition();
    }
  }

  private void randomMoveAI() {
    List<List<Integer>> allMoves = board.getAllEnemyMoves(turn);
    Random generator = new Random();
    //get the piece that the ai needs to move and then move that piece
    List<Integer> move = allMoves.get(generator.nextInt(0, allMoves.size()));
    Piece piece = board.getPieceAtPos(move.get(0));
    int movePos = move.get(generator.nextInt(1, move.size()));
    labels[piece.getSquare() / 8][piece.getSquare() % 8].setText(" ");
    board.makeMove(movePos, piece);
    labels[movePos / 8][movePos % 8].setText(board.getPieceAtPos(movePos).getName());
  }

  private void evalAI() {
    List<List<Integer>> allMoves = board.getAllEnemyMoves(turn);
    //get the piece that the ai needs to move and then move that piece
    double lowestScore = 1000;
    Piece chosenPiece = null;
    Integer chosenMove = null;
    for (List<Integer> moves : allMoves) {
      Piece piece = board.getPieceAtPos(moves.get(0));
      for (int i = 1; i < moves.size(); i++) {
        Board clone = Board.boardClone(board);
        clone.makeMove(moves.get(i), clone.getPieceAtPos(moves.get(0)));
        double score = clone.totalScore();
        if (score < lowestScore) {
          lowestScore = score;
          chosenPiece = piece;
          chosenMove = moves.get(i);
        }
      }
    }
    labels[chosenPiece.getSquare() / 8][chosenPiece.getSquare() % 8].setText(" ");
    board.makeMove(chosenMove, chosenPiece);
    labels[chosenMove / 8][chosenMove % 8].setText(board.getPieceAtPos(chosenMove).getName());
  }

  private void minimaxAI() {
    //depth = 3 to start
    int pos1;
    int pos2;
    if (madeMove != null) {
      pos1 = madeMove.valueA;
      pos2 = madeMove.valueB;
      labels[pos1 / 8][pos1 % 8].removeMoved(pos1);
      labels[pos2 / 8][pos2 % 8].removeMoved(pos2);
    }
    int depth = 2;
    MinimaxTree generateTree = new MinimaxTree(board, turn, depth);
    Pair<Piece, Integer> move = generateTree.blackAI();

    pos1 = move.valueA.getSquare();
    pos2 = move.valueB;

    labels[move.valueA.getSquare() / 8][move.valueA.getSquare() % 8].setText(" ");
    board.makeMove(move.valueB, move.valueA);
    labels[move.valueB / 8][move.valueB % 8].setText(board.getPieceAtPos(move.valueB).getName());
    madeMove = new Pair<>(pos1, pos2);
    labels[pos1 / 8][pos1 % 8].highlightMoved();
    labels[pos2 / 8][pos2 % 8].highlightMoved();

    if (turn == Colours.WHITE) { //AI is black so return smallest value
      //smallest value
    } else {
      //return largest value
    }
  }

}
