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
 * Servlet implementation class InServlet
 */
@WebServlet("/InServlet")
public class InoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InoutServlet() {
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
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String name = request.getParameter("username");
	String pass = request.getParameter("password");
	HttpSession session = request.getSession();
	User user = (User)session.getAttribute("user");
	boolean status1 = false, status2 = false, status0 = false, statusin = false;
	AuthDAO c = new AuthDAO();
	int id = c.getIdbyName(name, pass);
	
	ResultSet rs1 = c.getExpensesIn(id);
	ResultSet rs2 = c.getExpensesOut(id);
	ResultSet rs0 = c.getAllExpenses(id);
	ResultSet rs = c.getAllIncome(id);
	try {
		status0 = rs0.next();
		status1 = rs1.next();
		status2 = rs2.next();
		statusin = rs.next();
		rs.previous();
		rs0.previous();
		rs1.previous();
		rs2.previous();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<Map> ls = new ArrayList<Map>();
	List<Map> it = new ArrayList<Map>();
	List<Map> it1 = new ArrayList<Map>();
	List<Map> in = new ArrayList<Map>();
	//Adding items in list
	
	try{
	while(rs1.next()){
		Map map = new HashMap();
		map.put("inid", rs1.getInt(2));
		map.put("title", rs1.getString(3));
		map.put("amount", rs1.getString(4));
		map.put("comment", rs1.getString(5));
		map.put("date", rs1.getString(6));
		it.add(map);
	}
	while(rs2.next()){
		Map map = new HashMap();
		map.put("outid", rs2.getInt(2));
		map.put("title", rs2.getString(3));
		map.put("amount", rs2.getString(4));
		map.put("comment", rs2.getString(5));
		map.put("date", rs2.getString(6));
		it1.add(map);
	}
	while(rs0.next()){
		Map map = new HashMap();
		map.put("outid", rs0.getInt(2));
		map.put("title", rs0.getString(3));
		map.put("amount", rs0.getString(4));
		map.put("comment", rs0.getString(5));
		map.put("date", rs0.getString(6));
		ls.add(map);
	}
		while(rs.next()){
			Map map = new HashMap();
			map.put("inid", rs.getInt(2));
			map.put("title", rs.getString(3));
			map.put("amount", rs.getString(4));
			map.put("comment", rs.getString(5));
			map.put("date", rs.getString(6));
			in.add(map);
	}
	}catch(Exception e){e.printStackTrace();}
	if(!(status1 == false) || !(status2 == false)){
		session.setAttribute("status1", status1);
		session.setAttribute("status2", status2);
		session.setAttribute("status", status0);
		session.setAttribute("statusin", statusin);
		session.setAttribute("in", in);
		session.setAttribute("ls", ls);
		session.setAttribute("it", it);
		session.setAttribute("it1", it1);
		request.getRequestDispatcher("User.jsp").forward(request, response);	
	}
	else{
		if(status1 == false || status2 == false){
			session.setAttribute("status2",status2);
			session.setAttribute("status", status0);
			session.setAttribute("status1", status1);
			session.setAttribute("statusin", statusin);
			session.setAttribute("in", in);
			session.setAttribute("ls", ls);
			session.setAttribute("it", it);
			session.setAttribute("it1", it1);
			request.getRequestDispatcher("User.jsp").forward(request, response);
			
		}else if(status2 == false){
			session.setAttribute("status1",status1);
			session.setAttribute("status2", status2);
			session.setAttribute("status", status0);
			session.setAttribute("statusin", statusin);
			session.setAttribute("in", in);
			session.setAttribute("ls", ls);
			session.setAttribute("it", it);
			session.setAttribute("it1", it1);
			request.getRequestDispatcher("User.jsp").forward(request, response);
		}else{
			session.setAttribute("in", in);
			session.setAttribute("ls", ls);
			session.setAttribute("it", it);
			session.setAttribute("it1", it1);
			session.setAttribute("status2", status2);
			session.setAttribute("status1",status1);
			session.setAttribute("status", status0);
			session.setAttribute("statusin", statusin);
			request.getRequestDispatcher("User.jsp").forward(request, response);
		}
	}
}
}
