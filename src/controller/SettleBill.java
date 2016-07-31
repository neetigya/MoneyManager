package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.joda.time.LocalDate;

import dao.AuthDAO;

/**
 * Servlet implementation class SettleBill
 */
@WebServlet("/SettleBill")
public class SettleBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettleBill() {
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
		String n_id = request.getParameter("n_id");
		String email = (String)request.getParameter("email");
		String msg = "true";
		AuthDAO setbill = new AuthDAO();
		double amt = setbill.getAmount(n_id, email);
		setbill.paidAmount(n_id, email, amt);
		setbill.NewAmount(n_id, email, 0.0);
		
		//Set the notification for the user 1
		int id = setbill.getidbyemail(email);
		LocalDate date = new LocalDate();
		setbill.addnot(id, "Amount " +amt+ " has been settled by " +user.getUsername(),user.getUsername()+ " has settled the bill of " +amt, date.toString(), "5", user.getEmail());
		//Remove the notification from the Viewnot.jsp because user has already viewed it.
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("Viewnot.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	
	}

}
