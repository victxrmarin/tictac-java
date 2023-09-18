import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class OX extends JPanel {

  private String name;
  private BufferedImage img;
  private JLabel label;
  private Controller ctrl;
  private int position;

  public OX(int pos, Table table) {
    this.position = pos;
    this.ctrl = new Controller(this, table);
    try {
      this.img = ImageIO.read(new File("./img/blank.png"));
      this.label = new JLabel(new ImageIcon(img));
      this.add(label);

      label.addMouseListener(this.ctrl);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  public void removeListener() {
    label.removeMouseListener(ctrl);
  }
  public void setName(String ox) {
    this.name = ox;
  }

  public String getName() {
    return this.name;
  }

  public int getPosition() {
    return position;
  }

  public void setImg(String path) {
    try {
      this.img = ImageIO.read(new File(path));
      this.label.setIcon(new ImageIcon(img));
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }
}
