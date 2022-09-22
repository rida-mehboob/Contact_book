import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;



public class ViewContactUI extends JFrame
{

	public JPanel contentPane;
	public JTextField entername;
	public JTextField entermobilenumber;
	public JTable table_1;
	public JTextField searchtextField;

	
	
	public ViewContactUI() 
	{
		initialize();
		loadContactTable();
		
	}
	
	
	
	public void loadContactTable() {
		Db_Connection db = new Db_Connection();
		ResultSet rs = db.fetchAllContacts();
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
	}
	
	
	public void initialize() 
	{
		this.setBounds(100, 100, 469, 319);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
	
	//---------------------------------------------------LABELS---------------------------------------------------------//
		
		JLabel mobilenumber_1 = new JLabel("MOBILE #");
		mobilenumber_1.setHorizontalAlignment(SwingConstants.TRAILING);
		mobilenumber_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		mobilenumber_1.setBounds(21, 107, 65, 14);
		this.getContentPane().add(mobilenumber_1);
		
		JLabel name_1 = new JLabel(" NAME");
		name_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		name_1.setBounds(36, 76, 65, 20);
		this.getContentPane().add(name_1);
		

		JLabel searchname = new JLabel("ENTER NAME TO SEARCH");
		searchname.setFont(new Font("Times New Roman", Font.BOLD, 12));
		searchname.setBounds(26, 45, 158, 20);
		this.getContentPane().add(searchname);
		
		JLabel lblNewLabel = new JLabel("CONTACT BOOK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(71, 11, 294, 23);
		this.getContentPane().add(lblNewLabel);
		
		

	// --------------------------------------TEXT FIELDS----------------------------------------------------------------//	
		entername = new JTextField();
		entername.setBackground(new Color(240, 248, 255));
		entername.setEditable(false);
		entername.setBounds(96, 74, 158, 20);
		this.getContentPane().add(entername);
		entername.setColumns(10);
		
		entermobilenumber =  new JTextField();
		entermobilenumber.setBackground(new Color(240, 248, 255));
		entermobilenumber.setEditable(false);
		entermobilenumber.setBounds(96, 104, 158, 20);
		this.getContentPane().add(entermobilenumber);
		entermobilenumber.setColumns(10);
		
		searchtextField = new JTextField();
		searchtextField .addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
			
				char c = e.getKeyChar();
				if(Character.isLetter(c) || Character.isWhitespace(c)||Character.isISOControl(c))
				{
					searchtextField .setEditable(true);
				}
				else
				{
					searchtextField .setEditable(false);
				}
			}
		});
		searchtextField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String name1 = searchtextField.getText();
				
				Db_Connection db = new Db_Connection();
				ResultSet rs = db.SearchContacts(name1);
				
				try {
					if(rs.next ())
						{
							String name = rs.getString(1);
							String mobilenumber = rs.getString(2);
							entername.setText(name);
							entermobilenumber.setText(mobilenumber);
						}
						
						else
						{
							//searchtextField.setText("");
							 //JOptionPane.showMessageDialog(null, "Name not Found"); 
						}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}); 
		
		searchtextField.setColumns(10);
		searchtextField.setBounds(209, 45, 158, 20);
		this.getContentPane().add(searchtextField);
		

	///----------------------------------------TABLE SCROLL PANE ----------------------------------------------------------//
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 130, 372, 105);
		this.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		loadContactTable();
		

	//------------------------------------BUTTOMS ADD AND SEARCH--------------------------------------------------------------//	
		
		JButton btnNewButton = new JButton("CLICK TO ADD NEW CONTACT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 new AddingInContactbook();
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnNewButton.setBounds(209, 246, 234, 23);
		this.getContentPane().add(btnNewButton);
		
		this.setVisible(true);
	}
	}



