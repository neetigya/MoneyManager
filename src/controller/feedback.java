package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AuthDAO;

/**
 * Servlet implementation class feedback
 */
@WebServlet("/feedback")
public class feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public feedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fb = request.getParameter("feedback");
		String em = request.getParameter("email");
		
		AuthDAO ad = new AuthDAO();
		int status = ad.feedBack(em, fb);
		
		if(status > 0){
			String msg = "feedback successfully submitted";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("HomePage.jsp").include(request, response);
		}
		else{

			String msg = "Error in submitting feedback...Try Again!!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("feedback.jsp").include(request, response);
		}
		
		doGet(request, response);
	}

}
