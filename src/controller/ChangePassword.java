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

import dao.AuthDAO;
import model.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String oldPassword = request.getParameter("oPass");
		String newPassword = request.getParameter("nPass");
		String confirmPassword = request.getParameter("cPass");

		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = user.getUserId();

		AuthDAO authen = new AuthDAO();
		User profile = authen.getUserById(id);
		String pass = profile.getPassword();

		if (oldPassword.length() == 0) {

			RequestDispatcher r1 = getServletContext().getRequestDispatcher(
					"/changePass.jsp");
			PrintWriter out1 = response.getWriter();
			out1.println("<font color=red>Enter Password</font>");
			r1.include(request, response);
		}

		else if (!oldPassword.equals(pass)) {
			RequestDispatcher r1 = getServletContext().getRequestDispatcher(
					"/changePass.jsp");
			PrintWriter out1 = response.getWriter();
			out1.println("<font color=red>Old Password is not correct</font>");
			r1.include(request, response);
		}

		else if (!newPassword.equals(confirmPassword)) {

			RequestDispatcher r1 = getServletContext().getRequestDispatcher(
					"/changePass.jsp");
			PrintWriter out1 = response.getWriter();
			out1.println("<font color=red>Password Match Error</font>");
			r1.include(request, response);

		} 
		
		else if (newPassword.length()==0 || confirmPassword.length()==0) {

			RequestDispatcher r1 = getServletContext().getRequestDispatcher(
					"/changePass.jsp");
			PrintWriter out1 = response.getWriter();
			out1.println("<font color=red>Enter New Passwords</font>");
			r1.include(request, response);

		}
		
		else {
			int status = authen.updateProfilePass(id, newPassword);
			if (status > 0) {
				RequestDispatcher r1 = getServletContext()
						.getRequestDispatcher("/Profile.jsp");
				PrintWriter out1 = response.getWriter();
				out1.println("<font color=red>Password updated </font>");
				r1.include(request, response);

			}

		}

	}

}
