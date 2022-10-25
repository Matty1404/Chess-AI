package differentChessAIs;

import Game.Board;
import Pieces.Colours;
import Pieces.Empty;
import Pieces.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MinimaxTree {

  private final BoardNode root;
  private final Colours currTurn;


  public MinimaxTree(Board board, Colours turn, int depth) {
    currTurn = turn == Colours.WHITE ? Colours.BLACK : Colours.WHITE;
    this.root = new BoardNode(board, new Empty(-1, Colours.BLANK), -1, currTurn);
    boolean blackAI = currTurn == Colours.BLACK;
    int setScore = blackAI ? 100000 : -100000;
    root.setScore(setScore);


    //root.generateTree(blackAI, depth);

    root.setScore(generateTree2(root, 3, -100000, 100000, blackAI));
  }

  public BoardNode getRoot() {
    return root;
  }

  //move the min value to the top of each node, then find which node has the min of the tree then use that
  //for white use max, for black use min
  public Pair<Piece, Integer> blackAI() {
    //want the maximum then take the minimum of them
//    System.out.println("we want this score on this move:  " + root.getScore());


    for (BoardNode node : root.getChildren()) {
      if (node.getScore() == root.getScore()) {
        return node.getMoveMade();
      }
    }
    return null;
  }

  public double generateTree2(BoardNode node, int depth, double alpha, double beta,
      boolean isBlack) {
    List<List<Integer>> allMoves = node.getBoard().getAllMoves(node.getCurrTurn());
    if (depth == 0) {
      return node.getScore();
    }
    if (allMoves.isEmpty()) {
      node.setScore(isBlack ? 10000 : -10000);
      return node.getScore();
    }
    double value = isBlack ? 100000 : -100000;

    for (List<Integer> moves : allMoves) {
      for (int i = 1; i < moves.size(); i++) {
        Board clone = Board.boardClone(node.getBoard());
        Piece piece = clone.getPieceAtPos(moves.get(0));
        clone.makeMove(moves.get(i), piece);
        //clone.displayBoard();
        Colours newTurn = node.getCurrTurn() == Colours.WHITE ? Colours.BLACK : Colours.WHITE;
        BoardNode newNode = new BoardNode(clone, node.getBoard().getPieceAtPos(moves.get(0)), moves.get(i),
            newTurn);
        //gen rest of it
        double toSetNode;
        if (!isBlack) {
          //whites move
          toSetNode = generateTree2(newNode, depth - 1, alpha, beta, true);
          //new value will be the larger of the two since white want to maximise value
          value = Math.max(value, toSetNode);
          //if value is larger than the previous smallest then dont explore any more since bad
          // for prev move we want
          if (value >= beta) {
            break;
          }
          alpha = Math.max(alpha, value);
        } else {
          //blacks move
          toSetNode = generateTree2(newNode, depth - 1, alpha, beta, false);
          value = Math.min(value, toSetNode);
          // if smaller than the smaller
          if (value <= alpha) {

            break;
          }
          beta = Math.min(beta, value);
        }
        //here we want to set node WHEN the score is larger/smaller than the already existing score
        node.setScore(value);
        newNode.setScore(toSetNode);
        node.getChildren().add(newNode);
      }
      if (!isBlack && value >= beta) {
        break;
      } else if (isBlack && value <= alpha) {
        break;
      }
    }

    //check if checkmated, if no moves then -1000000 points
    return value;
  }


}
