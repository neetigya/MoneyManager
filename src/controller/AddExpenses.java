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
 * Servlet implementation class AddExpenses
 */
@WebServlet("/AddExpenses")
public class AddExpenses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpenses() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	
		String msg;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		//List it = (List)session.getAttribute("it");
		List it1 = (List)session.getAttribute("it1");
		List ls = (List)session.getAttribute("ls");
		AuthDAO authen = new AuthDAO();
		int user_id = user.getUserId();

		String title = request.getParameter("title");
		String amount = request.getParameter("amount");
		String note = request.getParameter("note");
		String date = request.getParameter("date");
		String message;
		
		float bal;
		if (title.equals("") || amount.equals("") || date.equals("")) {

			msg = "Please enter all the details ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("addExpense.jsp").forward(request,
					response);

			// out.println("please enter all the details");
			// request.getRequestDispatcher("addExpense.jsp").include(request,
			// response);
		} else {
			Map map = new HashMap();
			map.put("title", title);
			map.put("amount", amount);
			map.put("comment", note);
			map.put("date", date);
			it1.add(map);
			ls.add(map);
			AuthDAO addUserExpense = new AuthDAO();
			int status = addUserExpense.addExpense(user_id, title, amount,
					note, date);
			bal  = user.getBalance()-Float.parseFloat(amount);
			user.setBalance(bal);
			if (status > 0) {
				
				message = "Amount added successfully!!!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("addExpense.jsp").include(request,
						response);
				
			} else {
				message =" Error in adding amount, please try again!!!";
				request.setAttribute("message", message);
				request.getRequestDispatcher("addExpense.jsp").include(request,
						response);
			}
		}

	
	}

}
