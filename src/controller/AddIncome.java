package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import model.User;
import dao.AuthDAO;

/**
 * Servlet implementation class AddIncome
 */
@WebServlet("/AddIncome")
public class AddIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String msg= "";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session=request.getSession();  
        
        User user=(User)session.getAttribute("user");  
        List<Map> it = (ArrayList)session.getAttribute("it");
           
        AuthDAO authen = new AuthDAO();
		int user_id = authen
				.getIdbyName(user.getUsername(), user.getPassword());
		
		
		String title = request.getParameter("title");
		String amount = request.getParameter("amount");
		String note = request.getParameter("note");
		String date = request.getParameter("date");
		
		if(title.equals("") || amount.equals("") || date.equals(""))
		{
			msg = "please enter all the details";
			request.setAttribute("message1", msg);
			request.getRequestDispatcher("incomingAmount.jsp").forward(request,
					response);
		}
		else
		{
			Map map = new HashMap();
			map.put("title", title);
			map.put("amount", amount);
			map.put("comment", note);
			map.put("date", date);
			it.add(map);			
			AuthDAO addUserIncome = new AuthDAO();
			int status = addUserIncome.addIncome(user_id,title,amount,note,date);
			
			if (status > 0){
					float balance = user.getBalance()+Float.parseFloat(amount);
					user.setBalance(balance);
					msg = "Amount added successfully!!!";
					request.setAttribute("message1", msg);
				request.getRequestDispatcher("incomingAmount.jsp").include(request,response);
				//request.setAttribute("reg", regular);
				//request.getRequestDispatcher("incomingAmount.jsp").forward(request, response);
			}
			else{
				msg = "Error in adding amount, please try again!!!";
				request.setAttribute("message1", msg);
				request.getRequestDispatcher("incomingAmount.jsp").include(request, response);
			}
		}

	}

}
