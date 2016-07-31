package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
 * Servlet implementation class DeleteTran
 */
@WebServlet("/DeleteTran")
public class DeleteTran extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTran() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		AuthDAO authen = new AuthDAO();
		
		String out_id = request.getParameter("hidden");
		float amount = authen.getoutamount(out_id);
		float bal = user.getBalance() + amount;
		
		int check = authen.deleteTran(out_id);
		if(check > 0){
			user.setBalance(bal);
			int check1 = authen.updatebalance(bal, user.getUserId());
			if(check1>0){
				
				List<Map> it1 = (List<Map>) session.getAttribute("it1");
				List<Map> ls = (List<Map>) session.getAttribute("ls");
				Map m = new HashMap();
				Iterator<Map> it1iterator = it1.iterator();
				while(it1iterator.hasNext()){
					Map map = it1iterator.next();
					int key = (Integer) map.get("outid");
					if(Integer.parseInt(out_id) == (key)){
						m = map;
						//it1.remove(map);
				}
			}
				it1.remove(m);
					Iterator<Map> lsiterator = ls.iterator();
					while(lsiterator.hasNext()){
						Map map = lsiterator.next();
						int key = (Integer) map.get("outid");
						if(Integer.parseInt(out_id) == (key)){
							m = map;
							//ls.remove(map);
					}
				}
					ls.remove(m);
					String msg7 = "Entry Deleted ";
				request.setAttribute("msg", msg7);
				request.getRequestDispatcher("deleteTran.jsp").forward(request,
						response);
			}
			
			else{
				String msg7 = "Balance can't be updated";
				request.setAttribute("msg", msg7);
				request.getRequestDispatcher("deleteTran.jsp").forward(request,
						response);
			}
				
		}
		else{
			String msg7 = "Error in Deleting Entry ";
			request.setAttribute("msg", msg7);
			request.getRequestDispatcher("deleteTran.jsp").forward(request,
					response);
			
		}

	}

}
