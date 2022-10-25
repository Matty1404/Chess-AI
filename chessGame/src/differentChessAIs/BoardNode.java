package differentChessAIs;

import Game.Board;
import Pieces.Colours;
import Pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class BoardNode {

  // <<Piece, moveTo>, score>   <Pair<Pair<Piece, Integer>, Integer>>
  private Pair<Piece, Integer> moveMade;
  private double score;
  private final Board board;
  private final List<BoardNode> children;
  private final Colours currTurn;

  public BoardNode(Board board, Piece piece, int moveTo, Colours currTurn) {
    this.board = board;
    this.children = new ArrayList<>();
    this.score = board.totalScore();
    this.currTurn = currTurn;
    moveMade = new Pair<>(piece, moveTo);
  }

  public Pair<Piece, Integer> getMoveMade() {
    return moveMade;
  }

  public Board getBoard() {
    return board;
  }

  public Colours getCurrTurn() {
    return currTurn;
  }

  public void setScore(double score) {
    this.score = score;
  }


  public void generateTree(boolean max, int depth) {
    for (List<Integer> moves : board.getAllMoves(currTurn)) {
      for (int i = 1; i < moves.size(); i++) {
        Board clone = Board.boardClone(board);
        Piece piece = clone.getPieceAtPos(moves.get(0));
        clone.makeMove(moves.get(i), piece);
        //clone.displayBoard();
        Colours newTurn = currTurn == Colours.WHITE ? Colours.BLACK : Colours.WHITE;

        BoardNode newNode = new BoardNode(clone, board.getPieceAtPos(moves.get(0)), moves.get(i),
            newTurn);
        //gen rest of it
        if (depth > 0) {
          if (depth > 1) {
            int setScore = !max ? 1000000 : -1000000;
            newNode.setScore(setScore);
          }
          newNode.generateTree(!max, depth - 1);
        }

        if (max) {
          if (newNode.getScore() < score) {
            setScore(newNode.getScore());
          }
        } else {
          if (newNode.getScore() > score) {
            setScore(newNode.getScore());
          }
        }
        children.add(newNode);
      }
    }

    //check if checkmated, if no moves then -1000000 points

  }

  public double getScore() {
    return score;
  }

  public List<BoardNode> getChildren() {
    return children;
  }





}
