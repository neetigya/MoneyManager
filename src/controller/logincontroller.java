package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import dao.AuthDAO;

/**
 * Servlet implementation class logincontroller
 */
@WebServlet("/logincontroller")
public class logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logincontroller() {
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
		String msg = null;
		
		AuthDAO val = new AuthDAO();
		int id;
		id = val.getIdbyName(name, pass);
		ResultSet rs = val.validate(name, pass);
		boolean status = false;
		try {
			status = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(name != null){
			User user = new User();
			user = val.getUserById(id);
			
		if(status){
			boolean regular = true;
			request.setAttribute("user", user);
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("InServlet");
			rd.forward(request, response);
		}
		else{
				msg = "Username or password Error";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("HomePage.jsp").forward(request, response);
			}
		
		}
		else{
			session.invalidate();
    		//out.println("session timed out");
    		msg = "session timed out";
    		User user = new User();
    		request.setAttribute("user", user);
    		request.setAttribute("msg", msg);
    		request.getRequestDispatcher("HomePage.jsp").forward(request,response);
		}
	}

}
