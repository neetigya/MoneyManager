package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.LocalDate;

import model.User;

public class AuthDAO {
	ResultSet rs = null; 
	int userid;
	public ResultSet validate(String name, String pass) {
		 
		try{  
		Class.forName("com.mysql.jdbc.Driver");  	
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3307/usertable","root","root");  
		      
		PreparedStatement ps=con.prepareStatement(  
		"select * from usertable where name=? and password=?");  
		ps.setString(1,name);  
		ps.setString(2,pass);  
		      
		rs=ps.executeQuery();
		          
		}catch(Exception e){System.out.println(e);}  
		return rs;  

	}
	
	public int getIdbyName(String name, String pass){
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps=con.prepareStatement(  
			"select * from usertable where name = ? and password = ?");  
			ps.setString(1,name);  
			ps.setString(2,pass);  
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				userid = rs.getInt(1);
			}
				
			}catch(Exception e){e.printStackTrace();}
		return userid;
	}
	public ResultSet getqabyemail(String email){
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps=con.prepareStatement(  
			"select user_ques, answer from usertable where email = ?");  
			ps.setString(1, email);  
			
			rs = ps.executeQuery();
				
			}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	public User getUserById(int userId){
		User user = null;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			      
			PreparedStatement ps=con.prepareStatement(
			"select id, password, name, email, balance, user_ques, answer from usertable");  
			
			rs = ps.executeQuery();
			while(rs.next()){
				if(userId == rs.getInt(1)){
					user = new User(userId, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6),rs.getString(7), true);				
				}
			}
			}catch(Exception e){System.out.println(e);}
		return user;
	}
	public boolean checkUserNameAvailable(String userName){
		 boolean result = false;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps = con.prepareStatement("select name from usertable");
			
			rs = ps.executeQuery();
			while(rs.next()){
				if(userName.equals(rs.getString(1))){
					result = true;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
	}
	public boolean checkemailAvailable(String email){
		 boolean result = false;
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps = con.prepareStatement("select email from usertable");
			
			rs = ps.executeQuery();
			while(rs.next()){
				if(email.equals(rs.getString(1))){
					result = true;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
	}
	
	
	int i;
	public int input(String name, String pass, String email,String bal, String usersel, String answer) {
		// TODO Auto-generated method stub
		int balance = Integer.parseInt(bal);
		try {	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
		
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into usertable values(?,?,?,?,?,?,?)");  
	   
	 ps.setString(1,null);
	 ps.setString(2,name);
	 ps.setString(3,pass);  
	 ps.setString(4,email);
	 ps.setInt(5, balance);
	 ps.setString(6,usersel);
	 ps.setString(7,answer);
		i = ps.executeUpdate();
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		}
	
	public ResultSet getExpensesIn(int id){
		ResultSet rs1 = null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM user_in where user_id = ? ORDER BY date_time desc LIMIT 3");  
		   
			ps.setInt(1, id);
			rs1 = ps.executeQuery();
			
		}catch(Exception e){e.printStackTrace();}
		return rs1;
	}

	public ResultSet getExpensesOut(int id) {
		// TODO Auto-generated method stub
		ResultSet rs1 = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT * FROM user_out where user_id = ? ORDER BY date_time desc LIMIT 3");  
		   
			ps.setInt(1, id);
			rs1 = ps.executeQuery();
			
		}catch(Exception e){e.printStackTrace();}
		return rs1;
	}

	public boolean checkAnswer(String ans, String email) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			      
			PreparedStatement ps=con.prepareStatement(  
			"select answer from usertable where email = ?");  
			ps.setString(1, email);    
			      
			rs=ps.executeQuery();
			 while(rs.next()){
				 if(rs.getString(1).equals(ans)){
					 result = true;
				 }
			 }
			}catch(Exception e){System.out.println(e);}  
			return result;  

		
	}

	public int updatepass(String pass1, String email) {
		// TODO Auto-generated method stub
		int status = 0;
		try{
		Class.forName("com.mysql.jdbc.Driver");  	
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3307/usertable","root","root");  
		      
		PreparedStatement ps=con.prepareStatement(  
		"update usertable set password = ? where email = ?");  
		ps.setString(1,pass1);
		ps.setString(2,email);
		status = ps.executeUpdate();
		
		}catch(Exception e){e.printStackTrace();}
		return status;
	}
	int p,w;
	public int addExpense(String user_id, String title, String amount, String note, String date) {
		// TODO Auto-generated method stub
		try {	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
		
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into user_out values(?,?,?,?,?,?)");  
	   
	 ps.setString(1,user_id);
	 ps.setString(2,null);
	 ps.setString(3,title);  
	 ps.setString(4,amount);
	 ps.setString(5,note);
	 ps.setString(6,date);
		 p = ps.executeUpdate();
		 PreparedStatement ps1=(PreparedStatement) con.prepareStatement("update usertable set balance=(balance- ?)where id=?");
			ps1.setString(1,amount);
			ps1.setString(2,user_id);
			w=ps1.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w;
		}
	int q,c;
	public int addIncome(String user_id, String title, String amount, String note, String date) {
		// TODO Auto-generated method stub
		try {	
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
		
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into user_in values(?,?,?,?,?,?)");  
	   
	 ps.setString(1,user_id);
	 ps.setString(2,null);
	 ps.setString(3,title);  
	 ps.setString(4,amount);
	 ps.setString(5,note);
	 ps.setString(6,date);
		 q = ps.executeUpdate();
		
		 PreparedStatement ps1=(PreparedStatement) con.prepareStatement("update usertable set balance=(balance+ ?)where id=?");
		ps1.setString(1,amount);
		ps1.setString(2,user_id);
		c=ps1.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		}
	
	public int addnot(int userid, String title, String note, String dd, String flag, String email){
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into notification values(?,?,?,?,?,?,?)");  
		 ps.setInt(1, userid);
		 ps.setString(2,null);
		 ps.setString(3, title);  
		 ps.setString(4, note);
		 ps.setString(5, dd);
		 ps.setString(6, flag);
		 ps.setString(7, email);
			i = ps.executeUpdate();
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return i;
	}
	
	public ResultSet jobImp(String date){
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from notification where date = ?");  
		
			ps.setString(1, date);
			
			rs = ps.executeQuery();
			
		}catch(Exception e){e.printStackTrace();}
		return rs;
	}
	
	public ResultSet stat(String date1, String date2, int id){
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
		PreparedStatement ps=(PreparedStatement) con.prepareStatement("select title,sum(user_out) as sum from user_out where user_id = ? and date_time between ? AND ? group by title");  
		ps.setInt(1, id);
		ps.setString(2, date1);
		ps.setString(3, date2);
		rs = ps.executeQuery();
	}catch(Exception e){e.printStackTrace();}	
		return rs;
	}
	public int addExpense(int user_id, String title, String amount,
			String note, String date) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("insert into usertable.user_out values(?,?,?,?,?,?)");

			ps.setInt(1, user_id);
			ps.setString(2, null);
			ps.setString(3, title);
			ps.setString(4, amount);
			ps.setString(5, note);
			ps.setString(6, date);
			p = ps.executeUpdate();
			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement("update usertable.usertable set balance=(balance- ?)where id=?");
			ps1.setString(1, amount);
			ps1.setInt(2, user_id);
			w = ps1.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w;
	}

	int d;

	public int addIncome(int user_id, String title, String amount, String note,
			String date) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("insert into usertable.user_in values(?,?,?,?,?,?)");

			ps.setInt(1, user_id);
			ps.setString(2, null);
			ps.setString(3, title);
			ps.setString(4, amount);
			ps.setString(5, note);
			ps.setString(6, date);
			q = ps.executeUpdate();

			PreparedStatement ps1 = (PreparedStatement) con
					.prepareStatement("update usertable.usertable set balance=(balance+ ?)where id=?");
			ps1.setString(1, amount);
			ps1.setInt(2, user_id);
			c = ps1.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public ResultSet getAllTranOfDate(int user_id, String date) {

		ResultSet rs4 = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("select * from usertable.user_out where user_id = ? and date_Time= ?");

			ps.setInt(1, user_id);
			ps.setString(2, date);
			rs4 = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs4;

	}
	
	public ResultSet getAllINOfDate(int Id, String date) {
		ResultSet rs4 = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("select * from user_in where user_id = ? and date_Time= ?");

			ps.setInt(1, Id);
			ps.setString(2, date);
			rs4 = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs4;
	}
	
	public int deleteTran(String out_id ){
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("delete from usertable.user_out where out_id = ? limit 1");
			ps.setString(1, out_id);
			i = ps.executeUpdate();
			
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return i;
	}

	public float getoutamount(String id) {
		// TODO Auto-generated method stub
		float amt = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = (PreparedStatement) con
					.prepareStatement("select user_out from usertable.user_out where out_id = ?");

			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();
			while(rs.next()){
				amt = Float.parseFloat(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return amt;
	}

	public int updatebalance(float bal, int id) {
		// TODO Auto-generated method stub
	
		int status = 0;
		try{
		Class.forName("com.mysql.jdbc.Driver");  	
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3307/usertable","root","root");  
		      
		PreparedStatement ps=con.prepareStatement(  
		"update usertable set balance = ? where id = ?");  
		ps.setFloat(1, bal);
		ps.setInt(2, id);
		status = ps.executeUpdate();
		
		}catch(Exception e){e.printStackTrace();}
		return status;
	
	}
	public int feedBack(String username, String feedback) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");

			String sql = "INSERT INTO `feedback` (`username`, `comments`) VALUES ('" + username + "', '" + feedback
					+ "');";
			PreparedStatement s = con.prepareStatement(sql);
			int temp = s.executeUpdate(sql);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public ResultSet getAllExpenses(int id) {
		// TODO Auto-generated method stub
		ResultSet rs1 = null;
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT * FROM user_out where user_id = ? ORDER BY date_time desc");  
		   
			ps.setInt(1, id);
			rs1 = ps.executeQuery();
			
		}catch(Exception e){e.printStackTrace();}
		return rs1;
	}

	public ResultSet getAllIncome(int id) {
		// TODO Auto-generated method stub
		ResultSet rs1 = null;
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("SELECT * FROM user_in where user_id = ? ORDER BY date_time desc");  
		   
			ps.setInt(1, id);
			rs1 = ps.executeQuery();
			
		}catch(Exception e){e.printStackTrace();}
		return rs1;
	}

	public ResultSet getFlag(int userId) {
		// TODO Auto-generated method stub
		try{
			
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps=con.prepareStatement(  
			"select * from notification where user_id = ? and flag <> '0' ORDER BY date desc");  
			ps.setInt(1, userId);  
			
			rs = ps.executeQuery();
				
			}catch(Exception e){e.printStackTrace();}
		return rs;
	
	}

	public int setNotifDelete(int n_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		int status = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			String sql = "Delete from notification where n_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, n_id);
			
			status = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	public int getidbyemail(String email) {
		// TODO Auto-generated method stub
		int id = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps=con.prepareStatement(  
			"select id from usertable where email = ?");  
			ps.setString(1, email);  
			
			rs = ps.executeQuery();
				while(rs.next()){
					id = rs.getInt(1);
				}
			}catch(Exception e){e.printStackTrace();}
		return id;
	}

	public int changeMonth(LocalDate date, int n_id) {
		// TODO Auto-generated method stub
		int change = 0;
		String D = date.toString();
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			      
			PreparedStatement ps=con.prepareStatement(  
			"update notification set date = ? where n_id = ?");  
			ps.setString(1,D);
			ps.setInt(2,n_id);
			change = ps.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return change;
	}


	public int updateNotif(int int1) {
		// TODO Auto-generated method stub
		int update = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			      
			PreparedStatement ps=con.prepareStatement(  
			"update notification set flag = '4' where n_id = ?");  
			ps.setInt(1,int1);
			update = ps.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
		
	}
	//Getting n_id for bills table
	public int getn_idbyvalues(int id, String title, String note,
			String date, String flag, String email) {
		int n_id = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps=con.prepareStatement(  
			"select n_id from notification where user_id = ? AND title = ? AND note = ? AND date = ? AND flag = ? AND email = ?");  
			ps.setInt(1, id);  
			ps.setString(2, title);  
			ps.setString(3, note);
			ps.setString(4, date);
			ps.setString(5, flag);
			ps.setString(6, email);
			
			rs = ps.executeQuery();
				while(rs.next()){
					n_id = rs.getInt(1);
					
				}
			}catch(Exception e){e.printStackTrace();}
		return n_id;
	}

	public int addbill(int n_id, String Payeeemail, double sharedamt, String email) {
		// TODO Auto-generated method stub
		int status = 0;
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/usertable","root","root");
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("insert into bills values(?,?,?,?,?,?)");
			
		    ps.setString(1, null);
		    ps.setInt(2,n_id);
		    ps.setString(3, Payeeemail);  
		    ps.setDouble(4, sharedamt);
		    ps.setDouble(5, 0.0);
		    ps.setString(6, email);
			status = ps.executeUpdate();
			
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return status;	

	}

	public double getAmount(String n_id, String email) {
		// TODO Auto-generated method stub
		double amt = 0.0;
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps=con.prepareStatement(  
			"select New_Amount from bills where Payto = ? AND n_id = ?");  
			ps.setString(1, email);  
			ps.setString(2, n_id);
			
			rs = ps.executeQuery();
				while(rs.next()){
					amt = rs.getDouble(1);
					System.out.println(amt);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return amt;
	}

	public void paidAmount(String n_id, String email, double amt) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			      
			PreparedStatement ps=con.prepareStatement(  
			"update bills set Paid_Amount = ? where Payto = ? AND n_id = ?");  
			ps.setDouble(1, amt);
			ps.setString(2, email);
			ps.setString(3, n_id);
			ps.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void NewAmount(String n_id, String email, double e) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			      
			PreparedStatement ps=con.prepareStatement(  
			"update bills set New_Amount = ? where Payto = ? AND n_id = ?");  
			ps.setDouble(1, e);
			ps.setString(2, email);
			ps.setString(3, n_id);
			ps.executeUpdate();
		
		}catch(Exception exp){
			exp.printStackTrace();
		}
	}

	public ResultSet GetBills(String email) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");  	
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3307/usertable","root","root");  
			    
			PreparedStatement ps = con.prepareStatement(  
			"select * from bills where Payto = ? OR Payee = ? ORDER BY b_id desc");  
			ps.setString(1, email);  
			ps.setString(2, email);
		
			rs = ps.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rs;
	
	}
	
	public int updateProfilePass(int id, String newPassword) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = con
					.prepareStatement("update usertable set password = ? where id = ?");
			ps.setString(1, newPassword);
			ps.setInt(2, id);
			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int changeQuestion(int id, String question) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = con
					.prepareStatement("update moneymanager.usertable set user_ques = ? where id = ?");
			ps.setString(1, question);
			ps.setInt(2, id);
			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}

	public int changeAnswer(int id, String answer) {
		// TODO Auto-generated method stub
		int status = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/usertable", "root", "root");

			PreparedStatement ps = con
					.prepareStatement("update usertable set answer = ? where id = ?");
			ps.setString(1, answer);
			ps.setInt(2, id);
			status = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
