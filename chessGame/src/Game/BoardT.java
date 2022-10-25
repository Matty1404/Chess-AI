package Game;

import Pieces.Colours;
import Pieces.Piece;
import differentChessAIs.Pair;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;


public abstract class BoardT extends JFrame implements ActionListener {

  //Initialise arrays to hold panels and images of the board
  protected Board board;
  protected Colours turn;
  protected GameState state = GameState.CHOOSE_PIECE;
  protected int prevPos;
  protected List<Integer> prevMoves;
  protected Pair<Integer, Integer> madeMove = null;

  protected ChessButton[][] labels;

  public BoardT(Board board, Colours turn) {
    this.board = board;
    this.turn = turn;
    labels = new ChessButton[][]{

        // black
        {new ChessButton(board.getPieceAtPos(0).getName()),
            new ChessButton(board.getPieceAtPos(1).getName()),
            new ChessButton(board.getPieceAtPos(2).getName()),
            new ChessButton(board.getPieceAtPos(3).getName()),
            new ChessButton(board.getPieceAtPos(4).getName()),
            new ChessButton(board.getPieceAtPos(5).getName()),
            new ChessButton(board.getPieceAtPos(6).getName()),
            new ChessButton(board.getPieceAtPos(7).getName())},
        {new ChessButton(board.getPieceAtPos(8).getName()),
            new ChessButton(board.getPieceAtPos(9).getName()),
            new ChessButton(board.getPieceAtPos(10).getName()),
            new ChessButton(board.getPieceAtPos(11).getName()),
            new ChessButton(board.getPieceAtPos(12).getName()),
            new ChessButton(board.getPieceAtPos(13).getName()),
            new ChessButton(board.getPieceAtPos(14).getName()),
            new ChessButton(board.getPieceAtPos(15).getName())},
        // empty
        {new ChessButton(board.getPieceAtPos(16).getName()),
            new ChessButton(board.getPieceAtPos(17).getName()),
            new ChessButton(board.getPieceAtPos(18).getName()),
            new ChessButton(board.getPieceAtPos(19).getName()),
            new ChessButton(board.getPieceAtPos(20).getName()),
            new ChessButton(board.getPieceAtPos(21).getName()),
            new ChessButton(board.getPieceAtPos(22).getName()),
            new ChessButton(board.getPieceAtPos(23).getName())},
        {new ChessButton(board.getPieceAtPos(24).getName()),
            new ChessButton(board.getPieceAtPos(25).getName()),
            new ChessButton(board.getPieceAtPos(26).getName()),
            new ChessButton(board.getPieceAtPos(27).getName()),
            new ChessButton(board.getPieceAtPos(28).getName()),
            new ChessButton(board.getPieceAtPos(29).getName()),
            new ChessButton(board.getPieceAtPos(30).getName()),
            new ChessButton(board.getPieceAtPos(31).getName())},
        {new ChessButton(board.getPieceAtPos(32).getName()),
            new ChessButton(board.getPieceAtPos(33).getName()),
            new ChessButton(board.getPieceAtPos(34).getName()),
            new ChessButton(board.getPieceAtPos(35).getName()),
            new ChessButton(board.getPieceAtPos(36).getName()),
            new ChessButton(board.getPieceAtPos(37).getName()),
            new ChessButton(board.getPieceAtPos(38).getName()),
            new ChessButton(board.getPieceAtPos(39).getName())},
        {new ChessButton(board.getPieceAtPos(40).getName()),
            new ChessButton(board.getPieceAtPos(41).getName()),
            new ChessButton(board.getPieceAtPos(42).getName()),
            new ChessButton(board.getPieceAtPos(43).getName()),
            new ChessButton(board.getPieceAtPos(44).getName()),
            new ChessButton(board.getPieceAtPos(45).getName()),
            new ChessButton(board.getPieceAtPos(46).getName()),
            new ChessButton(board.getPieceAtPos(47).getName())},
        // white
        {new ChessButton(board.getPieceAtPos(48).getName()),
            new ChessButton(board.getPieceAtPos(49).getName()),
            new ChessButton(board.getPieceAtPos(50).getName()),
            new ChessButton(board.getPieceAtPos(51).getName()),
            new ChessButton(board.getPieceAtPos(52).getName()),
            new ChessButton(board.getPieceAtPos(53).getName()),
            new ChessButton(board.getPieceAtPos(54).getName()),
            new ChessButton(board.getPieceAtPos(55).getName())},
        {new ChessButton(board.getPieceAtPos(56).getName()),
            new ChessButton(board.getPieceAtPos(57).getName()),
            new ChessButton(board.getPieceAtPos(58).getName()),
            new ChessButton(board.getPieceAtPos(59).getName()),
            new ChessButton(board.getPieceAtPos(60).getName()),
            new ChessButton(board.getPieceAtPos(61).getName()),
            new ChessButton(board.getPieceAtPos(62).getName()),
            new ChessButton(board.getPieceAtPos(63).getName())}
    };
  } // Board()

  void display() {
    setTitle("Chess");

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    Container contentPane = getContentPane();
    GridLayout gridLayout = new GridLayout(8, 8);

    contentPane.setLayout(gridLayout);

    int row = -1;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        ChessButton button = labels[i][j];
        button.set(i, j);
        button.setPosition(i * 8 + j);
        button.addActionListener(this);
        contentPane.add(button);
      }
    }

    setSize(600, 600);
    setLocationRelativeTo(null);
    setVisible(true);
  } // display()

} // class Board