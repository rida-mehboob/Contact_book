import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;


import net.proteanit.sql.DbUtils;

public class Db_Connection {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	public Db_Connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			//System.out.println("connection successful");
		}

		catch (ClassNotFoundException ex) {
		}

		catch (SQLException ex) {
		}

	}

	public ResultSet fetchAllContacts() {
		try {
			pst = con.prepareStatement("select * from contacts");
			rs = pst.executeQuery();

		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return rs;

	}

	
		public ResultSet SearchContacts(String name1) 
		
		{
			try
			{
				pst = con.prepareStatement("select *from contacts where Name = ?");
				pst.setString(1, name1);
				rs = pst.executeQuery();
			} 				
			catch (SQLException e1) 
			{
			// TODO Auto-generated catch block				
				e1.printStackTrace();
			}
			return rs;
		}
		
	public void addInTable(String name, String mobilenumber) {
		try 
		{
			pst = con.prepareStatement("insert into contacts (Name, Mobile_Number) values(?,?)");
			pst.setString(1, name);
			pst.setString(2, mobilenumber);
			pst.executeUpdate();
		
			new ViewContactUI();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
