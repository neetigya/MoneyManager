package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ChangeAnswer
 */
@WebServlet("/ChangeAnswer")
public class ChangeAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAnswer() {
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
		String password = request.getParameter("pass1");
		
		String Answer = request.getParameter("answer");
			
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = user.getUserId();

		AuthDAO authen = new AuthDAO();
		User profile = authen.getUserById(id);
		String pass = profile.getPassword();
		if(!(pass).equals(password)){
			
			RequestDispatcher r1 = getServletContext().getRequestDispatcher(
					"/changeAns.jsp");
			PrintWriter out1 = response.getWriter();
			out1.println("<font color=red>Enter Correct Password</font>");
			r1.include(request, response);
			
			
		}
		
			else if(Answer.equals("")){
				RequestDispatcher r1 = getServletContext().getRequestDispatcher(
						"/changeAns.jsp");
				PrintWriter out1 = response.getWriter();
				out1.println("<font color=red>Please Enter an Answer</font>");
				r1.include(request, response);
			}
			
			else{
				
				int r = authen.changeAnswer(id,Answer);
				
				if(r>0){
					RequestDispatcher r1 = getServletContext().getRequestDispatcher(
							"/changeAns.jsp");
					PrintWriter out1 = response.getWriter();
					out1.println("<font color=red>update done</font>");
					r1.include(request, response);
					
				}
			}
			
	}
}