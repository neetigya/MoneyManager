package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDAO;

/**
 * Servlet implementation class RecoveryController
 */
@WebServlet("/RecoveryController")
public class RecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecoveryController() {
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
		
		String email = request.getParameter("email");
		AuthDAO val = new AuthDAO();
		boolean status = val.checkemailAvailable(email);
		
		if(status){
			ResultSet rs = val.getqabyemail(email);
			HttpSession session = request.getSession();
			request.setAttribute("rs1", rs);
			session.setAttribute("email", email);
			request.getRequestDispatcher("recoques.jsp").forward(request, response);
		}else{
			String msg = "Email id is not registered!!!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("RecoveryPassword.jsp").forward(request, response);
		}
	}

}
