import java.awt.*;
import java.util.Vector;
import javax.swing.*;

public class Table extends JFrame {

  private int turn;
  public JPanel match;
  public JPanel information;
  public JLabel text;

  private String[] state = { "", "", "", "", "", "", "", "", "" };

  private int[][] possibilities = {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 9 },
    { 1, 4, 7 },
    { 2, 5, 8 },
    { 3, 6, 9 },
    { 1, 5, 9 },
    { 3, 5, 7 },
  };

  public static void main(String args[]) {
    new Table().setVisible(true);
  }

  public void setOX(int pos, String name) {
    state[pos - 1] = name;
  }

  public Table() {
    setSize(500, 500);
    setTitle("Tic Tae Toe");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.black);
    startGame();
  }

  public int getTurn() {
    return turn;
  }

  public int addTurn() {
    if (winner()) {
      return turn;
    } else {
      this.turn++;
    }
    return turn;
  }

  public boolean winner() {
    for (int[] triplet : possibilities) {
      if (
        (
          state[triplet[0] - 1].equals("X") &&
          state[triplet[1] - 1].equals("X") &&
          state[triplet[2] - 1].equals("X")
        ) ||
        (
          state[triplet[0] - 1].equals("O") &&
          state[triplet[1] - 1].equals("O") &&
          state[triplet[2] - 1].equals("O")
        )
      ) {
        return true;
      }
    }
    return false;
  }

  public void setText(String e) {
    this.text.setText(e);
  }

  public String getTableState() {
    String tableState = "";
    for (String ox : state) {
      tableState += ox + " ";
    }
    return tableState;
  }

  public JPanel getJPanel() {
    return this.match;
  }

  public void startGame() {
    this.match = new JPanel();
    match.setLayout(new GridLayout(3, 3));
    add(match, BorderLayout.CENTER);

    this.information = new JPanel();
    this.text = new JLabel("TIC TAC TOE");
    text.setFont(new Font("sans", Font.PLAIN, 30));
    add(information, BorderLayout.NORTH);
    information.add(text);

    for (int i = 1; i <= 9; i++) {
      match.add(new OX(i, this));
    }
  }
}
