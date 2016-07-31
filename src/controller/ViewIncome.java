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
 * Servlet implementation class ViewIncome
 */
@WebServlet("/ViewIncome")
public class ViewIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewIncome() {
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

		String date = request.getParameter("dp1");
		boolean status = false;
		HttpSession session = request.getSession();
		System.out.println(date);
		AuthDAO c = new AuthDAO();
		User user = (User)session.getAttribute("user");
		ResultSet rs = c.getAllINOfDate(user.getUserId(), date);
		List<Map> in1 = new ArrayList<Map>();
		try {
			while(rs.next()){
				
					status = true;
					Map map = new HashMap();
					map.put("title", rs.getString(3));
					map.put("amount", rs.getString(4));
					map.put("comment", rs.getString(5));
					map.put("date", rs.getString(6));
					in1.add(map);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(status != false){
			session.setAttribute("in1", in1);
			session.setAttribute("stat2", true);
			session.setAttribute("print", false);
			request.getRequestDispatcher("Income.jsp").forward(request, response);
		}else{
			session.setAttribute("stat2",false);
			session.setAttribute("print", false);
			request.getRequestDispatcher("Income.jsp").forward(request, response);
		}	

	}

}
