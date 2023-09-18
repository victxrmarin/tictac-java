import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.lang.*;
import javax.imageio.*;
import javax.swing.*;

public class Controller implements MouseListener, ActionListener {

  private OX ox;
  private Table table;
  private JPanel match;

  public Controller(OX ox, Table table) {
    this.ox = ox;
    this.table = table;
  }

  public void whoPlays() {
    System.out.printf("Clicked - ");
    if (table.getTurn() % 2 == 0 || table.getTurn() == 0) {
      System.out.println(this.ox.getPosition());
      this.ox.setImg("./img/icons8-x-96.png");
      this.ox.setName("X");
      this.table.setOX(this.ox.getPosition(), this.ox.getName());
      System.out.println(table.getTableState());
      this.table.setText("O TURN");
    } else {
      System.out.println(this.ox.getPosition());
      this.ox.setImg("./img/icons8-o-96.png");
      this.ox.setName("O");
      this.table.setOX(this.ox.getPosition(), this.ox.getName());
      System.out.println(table.getTableState());
      this.table.setText("X TURN");
    }

    whoWins();
  }

  public void whoWins() {
    if (table.winner()) {
      if (table.getTurn() % 2 == 0) {
        this.table.setText("X WINS!");
      } else {
        this.table.setText("O WINS!");
      }
    }
  }

  public void afterMatch() {
    this.match = table.getJPanel();
    match.setVisible(false);
    match.removeAll();

    match.setLayout(new BorderLayout());
    JPanel menu = new JPanel();
    menu.setLayout(new FlowLayout());
    JButton restart = new JButton("Restart");
    JLabel restartext = new JLabel("Play Again");
    menu.add(restartext);
    menu.add(restart);
    match.add(menu, BorderLayout.CENTER);
    match.setVisible(true);

    restart.addActionListener(e -> {
      this.table.setVisible(false);
      match.removeAll();
      startGame();
      this.table.repaint();
      this.table.setVisible(true);
    });
  }

  public void startGame() {
    this.match = new JPanel();
    match.setLayout(new GridLayout(3, 3));
    table.add(match, BorderLayout.CENTER);

    this.table.information = new JPanel();
    this.table.text = new JLabel("TIC TAC TOE");
    this.table.text.setFont(new Font("sans", Font.PLAIN, 30));
    this.table.add(this.table.information, BorderLayout.NORTH);
    this.table.information.add(this.table.text);

    for (int i = 1; i <= 9; i++) {
      match.add(new OX(i, this.table));
    }
  }

  public void mousePressed(MouseEvent e) {
    table.addTurn();
    ox.removeListener();
    try {
      Thread.sleep(500);
      whoPlays();
      if (table.winner()) {
        whoPlays();
        this.afterMatch();
      }
    } catch (InterruptedException ie) {}
  }

  public void mouseExited(MouseEvent e) {}

  public void mouseEntered(MouseEvent e) {}

  public void mouseReleased(MouseEvent e) {}

  public void mouseClicked(MouseEvent e) {}

  public void actionPerformed(ActionEvent e) {}
}
