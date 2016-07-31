package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
 * Servlet implementation class Stats
 */
@WebServlet("/Stats")
public class Stats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stats() {
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
		
		String date1 = request.getParameter("dd1");
		String date2 = request.getParameter("dd2");
		boolean s = false;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		AuthDAO stat = new AuthDAO();
		ResultSet rs = stat.stat(date1, date2, user.getUserId());
		try {
			s = rs.next();
			rs.previous();
		}catch(Exception e){e.printStackTrace();}
		List item1 = new ArrayList();
		//Adding items in list
		int sum = 0;
		try{
		while(rs.next()){
			Map map = new HashMap();
			map.put("title", rs.getString(1));
			map.put("Expense", rs.getString(2));
			item1.add(map);
			sum+= Float.parseFloat(rs.getString(2));
		}
		}catch(Exception e){e.printStackTrace();}
		
		if(s == false){
			session.setAttribute("s",s);
			request.getRequestDispatcher("DisplayStats.jsp").forward(request, response);
		}
		else{
			session.setAttribute("s", s);
			session.setAttribute("item1", item1);
			session.setAttribute("sum", sum);
			request.getRequestDispatcher("DisplayStats.jsp").forward(request, response);
		}
	}
}
