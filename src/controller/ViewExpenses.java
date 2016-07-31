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

import model.User;
import dao.AuthDAO;

/**
 * Servlet implementation class ViewExpenses
 */
@WebServlet("/ViewExpenses")
public class ViewExpenses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewExpenses() {
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String date = request.getParameter("datepick");
		boolean status = false;
		HttpSession session = request.getSession();
		
		AuthDAO c = new AuthDAO();
		User user = (User)session.getAttribute("user");
		ResultSet rs = c.getAllTranOfDate(user.getUserId(), date);
		List<Map> ls1 = new ArrayList<Map>();
		System.out.println(date);
		try {
			while(rs.next()){
					status = true;
					Map map = new HashMap();
					map.put("title", rs.getString(3));
					map.put("amount", rs.getString(4));
					map.put("comment", rs.getString(5));
					map.put("date", rs.getString(6));
					ls1.add(map);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(status != false){
			session.setAttribute("ls1", ls1);
			session.setAttribute("stat", true);
			session.setAttribute("print", false);
			request.getRequestDispatcher("Expense.jsp").forward(request, response);
		}else{
			session.setAttribute("stat",false);
			session.setAttribute("print", false);
			request.getRequestDispatcher("Expense.jsp").forward(request, response);
		}	
	}
}
