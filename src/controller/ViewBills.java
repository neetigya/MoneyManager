package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;

import model.User;
import dao.AuthDAO;

/**
 * Servlet implementation class ViewBills
 */
@WebServlet("/ViewBills")
public class ViewBills extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBills() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String email = request.getParameter("email");
		String n_id = (request.getParameter("n_id"));
		String payee = (String)request.getParameter("p_ee");
		String payto = (String)request.getParameter("p_to");
		AuthDAO setbill = new AuthDAO();
		double amt = setbill.getAmount(n_id, payto);
		setbill.paidAmount(n_id, payto, amt);
		setbill.NewAmount(n_id, payto, 0.0);
		LocalDate date = new LocalDate();
		System.out.println(payee);
		if(payee.equals(email)){
			//Payee has settled the bill, send payto notification
			int id = setbill.getidbyemail(payto);
			setbill.addnot(id, "Amount " +amt+ " has been settled by " +email,email+ " has settled the bill of " +amt, date.toString(), "5", email);
		}else if(payto.equals(email)){
			//Payto has settled the email, send payee notification
			int id = setbill.getidbyemail(payee);
			setbill.addnot(id, "Amount " +amt+ " has been settled by " +email,email+ " has settled the bill of " +amt, date.toString(), "5", email);
		}
		//Update the attributes
		session.removeAttribute("settledBill");
		session.removeAttribute("UnsettledBill");
		List<Map> settledBill = new ArrayList<Map>(); //For settled bill, take them in one list
		List<Map> UnsettledBill = new ArrayList<Map>(); //For unsettled bill, take them in another list
		boolean settleStatus = false;
		boolean unsettleStatus = false;
		
		ResultSet rs = setbill.GetBills(email);
		String AmtMsg ="";
		try {
			while(rs.next()){
				if(rs.getDouble(4) == 0.0){
					Map<String , Comparable> map = new HashMap<String, Comparable>(); //Bill is settled
					if(email.equals(rs.getString(3))){
						//User is Payee
						AmtMsg = "You have paid " + rs.getDouble(5)+ " to " +rs.getString(6);
					}else if(email.equals(rs.getString(6))){
						//User is payto(the one who receives)
						AmtMsg = "You have been paid " +rs.getDouble(5)+ " by " +rs.getString(3) ;
					}
					settleStatus = true;
					map.put("AmtMsg", AmtMsg);
					map.put("Amount",rs.getDouble(5));
					map.put("Payee", rs.getString(3));
					map.put("Payto", rs.getString(6));
					map.put("n_id", rs.getInt(2));
					map.put("bid", rs.getInt(1));
					settledBill.add(map);
				}else if(rs.getDouble(5) == 0.0){
					Map<String , Comparable> map = new HashMap<String, Comparable>(); //Bill is unsettled
					if(email.equals(rs.getString(3))){
						//User is Payee
						AmtMsg = "You have to pay " + rs.getDouble(4)+ " to " +rs.getString(6);
					}else if(email.equals(rs.getString(6))){
						//User is payto(the one who receives)
						AmtMsg = rs.getString(3)+ " have to pay " +rs.getDouble(4)+ " to you" ;
					}
					unsettleStatus = true;
					map.put("AmtMsg", AmtMsg);
					map.put("Amount",rs.getDouble(5));
					map.put("Payee", rs.getString(3));
					map.put("Payto", rs.getString(6));
					map.put("n_id", rs.getInt(2));
					map.put("bid", rs.getInt(1));
					UnsettledBill.add(map);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("mail", email);
		session.setAttribute("settleStatus", settleStatus);
		session.setAttribute("unsettleStatus", unsettleStatus);
		session.setAttribute("settledBill", settledBill);
		session.setAttribute("UnsettledBill", UnsettledBill);
		
		request.getRequestDispatcher("ViewBills.jsp").forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		
		User user = (User)request.getAttribute("user");
		AuthDAO N = new AuthDAO();
		HttpSession session = request.getSession();
		List<Map> settledBill = new ArrayList<Map>(); //For settled bill, take them in one list
		List<Map> UnsettledBill = new ArrayList<Map>(); //For unsettled bill, take them in another list
		boolean settleStatus = false;
		boolean unsettleStatus = false;
		
		String email = request.getParameter("email");
		ResultSet rs = N.GetBills(email);
		String AmtMsg ="";
		try {
			while(rs.next()){
				if(rs.getDouble(4) == 0.0){
					Map<String , Comparable> map = new HashMap<String, Comparable>(); //Bill is settled
					if(email.equals(rs.getString(3))){
						//User is Payee
						AmtMsg = "You have paid " + rs.getDouble(5)+ " to " +rs.getString(6);
					}else if(email.equals(rs.getString(6))){
						//User is payto(the one who receives)
						AmtMsg = "You have been paid " +rs.getDouble(5)+ " by " +rs.getString(3) ;
					}
					settleStatus = true;
					map.put("AmtMsg", AmtMsg);
					map.put("Amount",rs.getDouble(5));
					map.put("Payee", rs.getString(3));
					map.put("Payto", rs.getString(6));
					map.put("n_id", rs.getInt(2));
					map.put("bid", rs.getInt(1));
					settledBill.add(map);
				}else if(rs.getDouble(5) == 0.0){
					Map<String , Comparable> map = new HashMap<String, Comparable>(); //Bill is unsettled
					if(email.equals(rs.getString(3))){
						//User is Payee
						AmtMsg = "You have to pay " + rs.getDouble(4)+ " to " +rs.getString(6);
					}else if(email.equals(rs.getString(6))){
						//User is payto(the one who receives)
						AmtMsg = rs.getString(3)+ " have to pay " +rs.getDouble(4)+ " to you" ;
					}
					unsettleStatus = true;
					map.put("AmtMsg", AmtMsg);
					map.put("Amount",rs.getDouble(5));
					map.put("Payee", rs.getString(3));
					map.put("Payto", rs.getString(6));
					map.put("n_id", rs.getInt(2));
					map.put("bid", rs.getInt(1));
					UnsettledBill.add(map);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("mail", email);
		session.setAttribute("settleStatus", settleStatus);
		session.setAttribute("unsettleStatus", unsettleStatus);
		session.setAttribute("settledBill", settledBill);
		session.setAttribute("UnsettledBill", UnsettledBill);
		request.getRequestDispatcher("ViewBills.jsp").forward(request, response);

	}

}
