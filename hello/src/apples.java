import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.*;
import java.util.*;
  
public class apples 
{
   public static void main(String[] args)
   {
      // establish main frame in which program will run
      TranspoFrame myTranspoFrame = new TranspoFrame("Transporter Room");
      myTranspoFrame.setSize(300, 200);
      myTranspoFrame.setLocationRelativeTo(null);
      myTranspoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // show everything to the user
      myTranspoFrame.setVisible(true);
   }
}

// TranspoFrame class is derived from JFrame class
class TranspoFrame  extends JFrame
{
   private JButton btnMyButton;
   private JTextField txtMyTextArea;
   private JLabel lblMyLabel;
   
   // TranspoFrame constructor
   public TranspoFrame(String title)
   {
      // pass the title up to the JFrame constructor
      super(title);
      
      // set up layout which will control placement of buttons, etc.
      FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 20);          
      setLayout(layout);
      
      // Frame will have 3 controls: a label, a text field and a button
      lblMyLabel = new JLabel("Friend's Name: ");
      txtMyTextArea = new JTextField(10);
      btnMyButton = new JButton("Press Here to See Friend"); 
      add(lblMyLabel);
      add(txtMyTextArea);
      add(btnMyButton);

      SeeFriendListener myListener = new SeeFriendListener();
      btnMyButton.addActionListener( myListener );
      txtMyTextArea.addActionListener( myListener );
   }
   
   // inner class for JButton Listener
   class SeeFriendListener implements ActionListener
   {
      SecondFrame frmSecond;
      
      // event handler for JButton
      public void actionPerformed(ActionEvent e)
      {
         String strFriendName;
         String[] sub_names;
         boolean error_flag;
         
         strFriendName = txtMyTextArea.getText();
         if (strFriendName == null || strFriendName.length() < 3)
            error_flag = true;
         else if (strFriendName.indexOf(",") >= 0)
            error_flag = true;
         else if (strFriendName.equalsIgnoreCase("Michael Loceff"))
         {
            JOptionPane.showMessageDialog(null, 
                  "I'm sorry, but no one can "
                  + "see Michael Loceff, please choose someone else.");  
            return;
         }
         else
         {
            // break into first and last using space
            sub_names = strFriendName.split(" ", 0);
            if (sub_names.length != 2)
               error_flag = true;
            else if (sub_names[0].length() < 2)
               error_flag = true;
            else if (sub_names[1].length() < 2)
               error_flag = true;
            else
               error_flag = false;
         }
         
         if (error_flag)
         {
            JOptionPane.showMessageDialog(null, 
                  "Name must be '[first][last]', and  [first]\n"
                  + "and [last} must each contain at least 2 letters.");
            return;
         }

         frmSecond = new SecondFrame(strFriendName);
         frmSecond.setSize(500, 250);
         frmSecond.setLocationRelativeTo(null);
         
         // show second frame to the user
         frmSecond.setVisible(true);

      }
   } // end inner class SeeFriendListener
} // end class TranspoFrame

//SecondFrame class is derived from JFrame class
class SecondFrame  extends JFrame
{
   JLabel lblName, lblTaxes, lblPrice;
   JPanel panel_main, panel_lft, panel_ctr, panel_rt;
   JComboBox cmboRealityLevels;
   JRadioButton btnLstn, btnTalk, btnFull;
   ButtonGroup btngp;
   JButton btn_ok;
   
   // state variables
   private int reality_state;  // 1-4
   private int comm_state;     // 1-3
   
   // constructor
   public SecondFrame(String strFriend)
   {
      // chain to base class constructor
      super("Transporter Processing Information");
      reality_state = comm_state = 0;

      // set up three main components
      lblName = new JLabel("Before you can see " + strFriend 
            + " I need some information.");
      lblTaxes = new JLabel("(Prices are subject to local taxes)");
      panel_main = new JPanel( new GridLayout(1, 3, 10, 10));

      // use border layout and put three above components in frame
      setLayout (new BorderLayout(20, 10));
      add(lblName, BorderLayout.NORTH );
      add(panel_main, BorderLayout.CENTER);
      add(lblTaxes, BorderLayout.SOUTH);

      // create three sub-panels to to into panel_main
      panel_lft = new JPanel( new FlowLayout(FlowLayout.CENTER, 5, 10));
      panel_ctr = new JPanel( new FlowLayout(FlowLayout.LEFT, 5, 5));
      panel_rt = new JPanel( new FlowLayout(FlowLayout.CENTER, 5, 10));
      panel_main.add(panel_lft);
      panel_main.add(panel_ctr);
      panel_main.add(panel_rt);

      // place borders around three sub-panels
      panel_lft.setBorder(new TitledBorder("Reality Level"));
      panel_ctr.setBorder(new TitledBorder("Communication Level"));
      panel_rt.setBorder(new TitledBorder("Price"));
 
      // put a combo box in panel_lft
      String[] strRealityLevels = {"1: See Lo-Res Pic", "2: Text Chat", 
            "3: Color Pic + Audio", "4: 3-D Real Human"};
      cmboRealityLevels = new JComboBox(strRealityLevels);
      panel_lft.add(cmboRealityLevels);

      // put a button group in panel_ctr
      btnLstn = new JRadioButton("Listen Only");
      btnLstn.setSelected(true);    // default
      btnTalk = new JRadioButton("Talk Only");
      btnFull = new JRadioButton("Full Interaction");     
      btngp = new ButtonGroup();
      btngp.add(btnLstn);
      btngp.add(btnTalk);
      btngp.add(btnFull);
          
      // place buttons in panel, top to bottom
      panel_ctr.add(btnLstn);
      panel_ctr.add(btnTalk);
      panel_ctr.add(btnFull);
      
      // put the price and ok button in panel_rt     
      lblPrice = new JLabel("-- temporary --");
      btn_ok = new JButton("Submit Order");
      panel_rt.add(lblPrice);
      panel_rt.add(btn_ok); 
      
      // register the listeners
      cmboRealityLevels.addItemListener(new RealityLevelListener());
      btnLstn.addItemListener(new CommLevelListener());
      btnTalk.addItemListener(new CommLevelListener());
      btnFull.addItemListener(new CommLevelListener());
      
      // adjust price to default state
      UpdatePrice();
   }
   
   private void UpdatePrice()
   {
      int intPrice;
      NumberFormat bucks = NumberFormat.getCurrencyInstance(Locale.US);
          
      intPrice = 1000*(reality_state+1) + 500*(comm_state+1);
      
      // beaming people across spacetime is expensive
      if (reality_state == cmboRealityLevels.getItemCount() - 1)
         intPrice = 500000; 
 
      System.out.println(cmboRealityLevels.getItemCount());
      // update the pricetag
      lblPrice.setText(bucks.format(intPrice)); 
   }
   
   // inner class for JButton Listener
   class RealityLevelListener implements ItemListener
   {
      public void itemStateChanged(ItemEvent e)
      {
         reality_state = cmboRealityLevels.getSelectedIndex();
         UpdatePrice();
      }
   } // end RealityLevelListener
 
   // inner class for JButton Listener
   class CommLevelListener implements ItemListener
   {
      public void itemStateChanged(ItemEvent e)
      {
         if (btnLstn.isSelected())
               comm_state = 0;
         else if (btnTalk.isSelected())
               comm_state = 1;
         if (btnFull.isSelected())
               comm_state = 2;
         UpdatePrice();
      }   
   } // end CommLevelListener
}