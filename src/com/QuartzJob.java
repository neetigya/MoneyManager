package com;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.joda.time.LocalDate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.AuthDAO;
public class QuartzJob implements Job {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
    
		AuthDAO jobimpl = new AuthDAO();
		LocalDate localDate = new LocalDate();
        ResultSet rs = jobimpl.jobImp(localDate.toString());
        try {
			while(rs.next()){
				System.out.println("hi");
				int id = jobimpl.getidbyemail(rs.getString(7));
				if(rs.getString(5).equals("1")||rs.getString(5).equals("2")){ //Send an email(reminder) only when flag is 1 or 2
					if(id == rs.getInt(1)){
						String to = rs.getString(7);   
				        // Sender's email (change accordingly)
				        System.out.println(to);
				        String from = "managermoney8@gmail.com"; //change accordingly	
				        final String username = "managermoney8@gmail.com"; 
				        final String password = "Sharda@47";        //change accordingly
				        
				        Properties props = new Properties(); 
				        props.put("mail.smtp.auth", "true"); 
				        props.put("mail.smtp.host", "smtp.gmail.com");
				        props.put("mail.smtp.port", 587);
				        props.put("mail.smtp.starttls.enable", "true");
				        Session session = Session.getInstance(props, 
				        		new javax.mail.Authenticator() { protected PasswordAuthentication 
				        	getPasswordAuthentication() { return new PasswordAuthentication(username, password); } });
				        
				        try { Message message = new MimeMessage(session); 
				        message.setFrom(new InternetAddress(from)); 
				        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); 
				        message.setSubject(rs.getString(3)); 
				        message.setText(rs.getString(4));   
				        if(rs.getString(5).equals("1")){
				        	jobimpl.setNotifDelete(rs.getInt(2));
				        }else if(rs.getString(5).equals("2")){
				        	//Change the date
				        	int change = jobimpl.changeMonth(localDate.plusMonths(1),rs.getInt(2));
				        	if(change > 0){
				        		System.out.println("Date couldn't be changed!!!");
				        	}
				        }
				        
				        //send the message 
				        Transport.send(message);   
				         } 
				        catch (MessagingException e) 
				        { 
				        	throw new RuntimeException(e); 
				        }

					}else{
						//Do nothing
					}
				}
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    }
}
