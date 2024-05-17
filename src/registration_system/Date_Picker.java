package registration_system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Date_Picker {
	
	private JPanel datePanel;
	private JButton btnChoose;
	private JFrame frame; 
	
  public Date_Picker() {
    JLabel J_Label = new JLabel("Date Selected:");
    final JTextField J_Text_Field = new JTextField(20);
    btnChoose = new JButton("Choose the Date");
    JPanel datePanel = new JPanel();
    datePanel.add(J_Label);
    datePanel.add(J_Text_Field);
    datePanel.add(btnChoose);
    frame = new JFrame();
    frame.getContentPane().add(datePanel);
    frame.pack();
    frame.setVisible(true);
    
    btnChoose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        J_Text_Field.setText(new DatePick(frame).Set_Picked_Date());
      }
    });
  }
}