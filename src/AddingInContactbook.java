import java.awt.Component;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AddingInContactbook  {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField entername;
	private JTextField entermobilenumber;
	
	public AddingInContactbook()
	{
		initialize();
		//getInput(null, null);
		
	}

	public void initialize() {
	
			
		frame = new JFrame("ADDING CONTACT");
		frame.setBounds(100, 100, 469, 319);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

			
			entername = new JTextField();
			
			entername.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) 
				{
					char c = e.getKeyChar();
					if(Character.isLetter(c) || Character.isWhitespace(c)||Character.isISOControl(c))
					{
						entername.setEditable(true);
					}
					else
					{
						entername.setEditable(false);
					}
				}
			});
			entername.setBounds(224, 33, 158, 20);
			frame.getContentPane().add(entername);
			entername.setColumns(10);
			
			entermobilenumber =  new JTextField();
			entermobilenumber.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) 
				{
					String phoneNumber = entermobilenumber.getText();
					int length = phoneNumber.length();
				
					Character c = e.getKeyChar();
					if( e.getKeyChar() >= '0' &&  e.getKeyChar()<='9')
					{
						
						if(length < 12) 
						{
						entermobilenumber.setEditable(true);
						}
				    
						else
						{
							entermobilenumber.setEditable(false);
						}
					}
					else
					{ 
						if(e.getExtendedKeyCode( ) == KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode( ) == KeyEvent.VK_DELETE) 
						{
							entermobilenumber.setEditable(true);
						}
						else 
						{
							entermobilenumber.setEditable(false);
						}
					}
					}
			});
			entermobilenumber.setBounds(224, 79, 158, 20);
			frame.getContentPane().add(entermobilenumber);
			entermobilenumber.setColumns(10);
			
			JLabel mobilenumber_1 = new JLabel("ENTER MOBILE #");
			mobilenumber_1.setHorizontalAlignment(SwingConstants.TRAILING);
			mobilenumber_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			mobilenumber_1.setBounds(46, 82, 108, 14);
			frame.getContentPane().add(mobilenumber_1);
			
			JLabel name_1 = new JLabel("ENTER NAME");
			name_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
			name_1.setBounds(59, 33, 94, 20);
			frame.getContentPane().add(name_1);
	

			
			JButton add = new JButton("ADD CONTACT");
			add.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{ 
					if(entername.getText().isEmpty() || entermobilenumber.getText().isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "The Fields are empty");
					}
					
					else
					{
					
					 Db_Connection db = new Db_Connection();
					  db.addInTable(entername.getText(),  entermobilenumber.getText());
					  entername.setText("");					
					  entermobilenumber.setText("");
					  entername.requestFocus();	   
				}
				}
				});
				add.setBackground(SystemColor.inactiveCaption);
				add.setVisible(true);
				add.setFont(new Font("Times New Roman", Font.BOLD, 12));
				add.setBounds(142, 159, 152, 23);
				frame.getContentPane().add(add);
				
	}

	

	


	

	

			
	
}

