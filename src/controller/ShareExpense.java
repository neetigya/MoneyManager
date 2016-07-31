package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.joda.time.LocalDate;

import com.Domain;
import com.EmailUtility;

import dao.AuthDAO;

/**
 * Servlet implementation class ShareExpense
 */
@WebServlet("/ShareExpense")
public class ShareExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

	
    public ShareExpense() {
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
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String amount = request.getParameter("amount");
		String emails = request.getParameter("email");
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String email = "", domain = "", Expensemsg = "";
		emails = emails + '\0';
		double amt = 0.0;
		int count = 0, j = 0;
		ArrayList<String> emailarray = new ArrayList<String>();
		AuthDAO a = new AuthDAO();
		try{
		amt = Float.parseFloat(amount);
		}catch(Exception e){
				Expensemsg = "Enterd amount is not in valid format";
				request.setAttribute("MSG", Expensemsg);
			request.getRequestDispatcher("User.jsp").forward(request, response);}
		
		for(int i = 0; i < emails.length();i++){
			if(emails.charAt(i) == ','){
				//Check Domain
				Domain d = new Domain();
				try {
					d.doLookup(domain);
				} catch (Exception e) {
					Expensemsg = "domain:" +domain+ "is incorrect";
					request.setAttribute("MSG", Expensemsg);
					request.getRequestDispatcher("User.jsp").forward(request, response);
					e.printStackTrace();
				}
					emailarray.add(email);
				email = "";
				domain = "";
				count++;
			}else if(emails.charAt(i) == ' '){
				
			}
			else if(emails.charAt(i) == '\0'){ //Because user may enter only 1 email and also there is no ',' after last email
				//Check Domain
				Domain d = new Domain();
				try {
					d.doLookup(domain);
				} catch(Exception e) {
					Expensemsg = "domain:" +domain+ " is incorrect";
					request.setAttribute("MSG", Expensemsg);
					request.getRequestDispatcher("User.jsp").forward(request, response);
					e.printStackTrace();
				}
					System.out.println(email);
					emailarray.add(email);
					count++;
			}
			else{
				email = email + emails.charAt(i);
				if(emails.charAt(i) == '@'){
					for(int k = i+1; k < emails.length()-1; k++){
						if(emails.charAt(k) == ','){
							break;
						}else if(emails.charAt(k) == '\0'){
							break;
						}
						domain = domain + emails.charAt(k);
						
					}
					
				}
			}
		}
		String resultMessage = "";
		String subject = u.getUsername()+ " wants to share an expense with you";
		String content = "";
		double sharedamt = amt/(count+1); 
		//Sending Emails/notifications for shared amount
		for(int i = 0; i < emailarray.size(); i++){
			content = u.getUsername()+ " have added you in splitting " +amt+ "\nYour Share: " +sharedamt+ "\n\nMessage sent by" +u.getEmail();
			boolean status = a.checkemailAvailable(emailarray.get(i));
			if(status == true){
				//Send notification on their account
				int id = a.getidbyemail(emailarray.get(i));
				LocalDate localdate = new LocalDate();
				a.addnot(id, u.getUsername()+ " wants to share an expense with you", u.getUsername()+ " have added you in splitting " +amt+ "\nYour Share: " +sharedamt+ "\n\nMessage sent by" +u.getEmail(), localdate.toString(), "3", u.getEmail());
				int n_id = a.getn_idbyvalues(id, u.getUsername()+ " wants to share an expense with you", u.getUsername()+ " have added you in splitting " +amt+ "\nYour Share: " +sharedamt+ "\n\nMessage sent by" +u.getEmail(), localdate.toString(), "3", u.getEmail());
				//Add bill in bills table for further communication
				a.addbill(n_id, emailarray.get(i),sharedamt, u.getEmail());
				 resultMessage = "The User(s) have been notified.";
			}else{
				//Send notification on their email
				try {
		            EmailUtility.sendEmail(host, port, user, pass, emailarray.get(i), subject,
		                    content);
		            resultMessage = "The User(s) have been notified.";
		            
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            resultMessage = "There were an error: " + ex.getMessage();
		        }
				
			}
		}
		  request.setAttribute("MSG", resultMessage);
          request.getRequestDispatcher("User.jsp").forward(
                  request, response);
	}

}
