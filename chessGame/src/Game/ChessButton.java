package Game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ChessButton extends JButton {

  private int position = -1;
  private final Font font = new Font("Ariel", Font.PLAIN, 34);
  private final Color bgLight = new Color(222, 184, 135);
  private final Color getBgSelectLight = new Color(255, 255, 255);
  private final Color bgDark = new Color(139, 69, 19);
  private final Color bgSelectDark = new Color(0, 0, 0);
  private final Color moved = new Color(201, 169, 169);

  ChessButton(String s) {
    super(s);
  }

  public void setPosition(int pos) {
    position = pos;
  }

  public int getPosition() {
    assert (position != -1);
    return position;
  }

  public void set(int row, int col) {
    setFont(font);
    setOpaque(true);
    setBackground((col + row) % 2 != 0 ? bgDark : bgLight);
    setHorizontalAlignment(SwingConstants.CENTER);
  }

  public void highlight(int row, int col) {
    setBackground((row + col) % 2 != 0 ? bgSelectDark : getBgSelectLight);
  }

  public void highlightMoved() {
    setBackground(moved);
  }

  public void removeMoved(int pos) {
    setBackground((pos % 8 + pos / 8) % 2 != 0 ? bgDark : bgLight);
  }

}