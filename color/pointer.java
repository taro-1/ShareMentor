import javax.swing.*;
import java.awt.Point;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;

public class pointer extends JFrame implements MouseListener{

  JLabel label;

  public static void main(String[] args){
    pointer frame = new pointer();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 300, 200);
    frame.setTitle("タイトル");
    frame.setVisible(true);
  }

  JPointTest3(){
    JPanel panel = new JPanel();
    panel.addMouseListener(this);
    panel.setBackground(Color.BLUE);

    label = new JLabel("座標を表示");

    getContentPane().add(panel, BorderLayout.CENTER);
    getContentPane().add(label, BorderLayout.PAGE_END);
  }

  public void mouseClicked(MouseEvent e){
    Point point = e.getPoint();
    label.setText("x:" + point.x + ",y:" + point.y);
  }

  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}

}