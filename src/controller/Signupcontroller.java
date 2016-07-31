package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDAO;

/**
 * Servlet implementation class HomePagecontroller
 */
@WebServlet("/Signupcontroller")
public class Signupcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signupcontroller() {
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
		
		String uname = request.getParameter("username");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String email = request.getParameter("email");
		String usersel = request.getParameter("usersel");
		String answer = request.getParameter("answer");
		String balance = request.getParameter("bal");
		boolean regular = false;
		String msg = "";
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";

		if(!(uname.equals(null)) && pass1.equals("") && pass2.equals("") && email.equals(null)){
			AuthDAO check = new AuthDAO();
			boolean result = check.checkUserNameAvailable(uname);
			if(result){
				msg = "Username already exists...Please try again";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("signup.jsp").include(request, response);
			}else{
				msg = "Username ready to take...";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("signup.jsp").include(request, response);
			}
		}
		else if(uname.equals("")){
			msg = "Enter User name";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		else if(pass1.equals("")){
			msg = "Enter password";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		else if(pass2.equals("")){
			msg = "Please Enter 'Repeat password'";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		else if(!(pass1.equals(pass2))){
			msg = "password Mismatch";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		else if(!(pass1.matches(pattern))){
			msg = "Weak Password";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("HomePage.jsp").forward(request, response);
		}
		else if(email.equals("")){
			msg = "Please Enter Email";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		else if(usersel.equals("Nothing Selected")){
			msg = "Please Enter Recovery Question";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("signup.jsp").include(request, response);
		}
		else{
			AuthDAO enter = new AuthDAO();
			int status1 = enter.input(uname, pass1, email, balance, usersel, answer);
			
			if (status1 > 0){
				 msg = "Account created successfully!!!";
				request.setAttribute("msg", msg);
				request.setAttribute("reg", regular);
				request.getRequestDispatcher("HomePage.jsp").forward(request, response);
			}
			else{
				msg = "Invalid credentials...Please try again!!!";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("signup.jsp").include(request, response);
			}
				
		}
		
	
	}

}
