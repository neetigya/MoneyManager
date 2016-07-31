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
 * Servlet implementation class finalrecovery
 */
@WebServlet("/finalrecovery")
public class finalrecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public finalrecovery() {
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
		HttpSession session = request.getSession();
		
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String email = (String)session.getAttribute("email");
		
		if(pass1.equals(pass2)){
			AuthDAO a = new AuthDAO();
			int status = a.updatepass(pass1, email);
			if(status < 0){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}else{
				String msg = "Password successfully changed!!";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("HomePage.jsp").forward(request, response);
				
			}
		}else{
			String msg = "Password don't match!!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("updatepass.jsp").forward(request, response);
		}
	}

}
