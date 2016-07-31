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
 * Servlet implementation class Notification
 */
@WebServlet("/Notification")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Short polling implementation
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		AuthDAO N = new AuthDAO();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if((user == null)){

				//Do nothing
		}else{
			ResultSet rs = N.getFlag(user.getUserId());
			boolean status = false;
			int count = 0;
			List<Map> list1 = new ArrayList<Map>();
			try {
				while(rs.next()){
					if(rs.getString(6).equals("3") || rs.getString(6).equals("5")){	//For share expense and settled bill
						status = true;
						Map<String, Comparable> map = new HashMap<String, Comparable>();
						map.put("n_id", rs.getInt(2));
						map.put("Subject", rs.getString(3));
						map.put("Message", rs.getString(4));
						map.put("Date", rs.getDate(5));
						map.put("email", rs.getString(7));						
						list1.add(map);	
						N.updateNotif(rs.getInt(2));//To see the old notifications
						count++;
					}
					
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Get all the notifications whose flag is set to 4
			
			
			if(status == false){
				//Server won't respond
			}else{
				//server respond
				session.setAttribute("list1", list1);
				out.print(count);
				count = 0;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String title = request.getParameter("title");
		String note = request.getParameter("note");
		String date = (String)request.getParameter("dd");
		String rep = request.getParameter("usersel");
		String flag = "0";
		String msg;
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(rep.equals("Repeat")){
			flag = "2";
		}else{
			flag = "1";
		}
		AuthDAO notify = new AuthDAO();
		int status = notify.addnot(user.getUserId(), title, note, date, flag, user.getEmail());
		//AuthDAO notify = new AuthDAO();
		//notify.addnot(user.getUserId(), title, note, date);
		
		
		if(status > 0){
			msg = "Event Added Successfully";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("User.jsp").forward(request, response);
		}
		else{
			msg = "Some Error occurred...try again";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("User.jsp").forward(request, response);
		}
	}

}
