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
 * Servlet implementation class changeQues
 */
@WebServlet("/changeQues")
public class changeQues extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeQues() {
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
		
		String Question = request.getParameter("usersel");
			
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = user.getUserId();

		AuthDAO authen = new AuthDAO();
		User profile = authen.getUserById(id);
		String pass = profile.getPassword();
		if(!(pass).equals(password)){
			
			RequestDispatcher r1 = getServletContext().getRequestDispatcher(
					"/changeQues.jsp");
			PrintWriter out1 = response.getWriter();
			out1.println("<font color=red>Enter Correct Password</font>");
			r1.include(request, response);
			
			
		}
		
			else if(Question.equals("Nothing Selected")){
				RequestDispatcher r1 = getServletContext().getRequestDispatcher(
						"/changeQues.jsp");
				PrintWriter out1 = response.getWriter();
				out1.println("<font color=red>Please select the question</font>");
				r1.include(request, response);
			}
			
			else{
				
				int r = authen.changeQuestion(id,Question);
				
				if(r>0){
					RequestDispatcher r1 = getServletContext().getRequestDispatcher(
							"/changeQues.jsp");
					PrintWriter out1 = response.getWriter();
					out1.println("<font color=red>update done, Time to change your Answer</font>");
					r1.include(request, response);
					
				}
			}
			
			
		}
		
		
	}

