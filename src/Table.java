import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class Table extends JFrame {

  private int turn;
  public JPanel match;
  public JPanel information;
  public JLabel text;
  public JMenuBar menuBar;
  public JMenu menu;
  public JMenuItem menuJogoNovo, menuJogoPontos, menuJogoSair;

  private String[] state = { "", "", "", "", "", "", "", "", "" };

  private final int[][] possibilities = {
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
    turn = 0;
    initializeUI();
  }

  private void initializeUI() {
    setSize(500, 500);
    setTitle("Tic Tac Toe");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.BLACK);
    match = new JPanel();

    menuBar = new JMenuBar();
    menu = new JMenu("Options");
    menuJogoNovo = new JMenuItem("New Game");
    menuJogoPontos = new JMenuItem("Credits");
    menuJogoSair = new JMenuItem("Exit");

    menu.add(menuJogoNovo);
    menu.add(menuJogoPontos);
    menu.add(menuJogoSair);
    menuBar.add(menu);
    setJMenuBar(menuBar);

    menuJogoSair.addActionListener(e -> System.exit(0));

    menuJogoNovo.addActionListener(e -> {
      if (match != null) {
        match.removeAll();
        match.revalidate();
        match.repaint();
      }
      startGame();
    });

    menuJogoPontos.addActionListener(e -> {
      // Implement credits action here
    });

    startGame();
  }

  public int getTurn() {
    return turn;
  }

  public void addTurn() {
    turn++;
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
    if(this.turn == 9) {
      return true;
    }
    return false;
  }

  public void setText(String e) {
    this.text.setText(e);
  }

  public String getTableState() {
    return Arrays.toString(state);
  }

  public JPanel getJPanel() {
    return this.match;
  }

  public void startGame() {
    this.turn = 0;
    this.state = new String[9];
    for (int i = 0; i < state.length; i++) {
      state[i] = "";
    }

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

    revalidate();
    repaint();
  }
}
