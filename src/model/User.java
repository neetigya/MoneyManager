package model;

import java.sql.ResultSet;

public class User {
	int userId;
	String username;
	String password;
	String email;
	float balance;
	String question;
	String answer;
	boolean loggedin;
	
	public User(){	
		userId = -1;
		username = null;
		password = null;
		question = null;
		answer = null;
		email = null;
		balance = 0;
		loggedin = false;
	}

	public User(int userId, String password, String username, String email,
			int balance, String question, String answer, boolean loggedin) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.password = password;
		this.balance = balance;
		this.email = email;
		this.question = question;
		this.answer = answer;
		this.username = username;
		this.loggedin = loggedin;
	}
	
		public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float bal) {
		this.balance = bal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}
}

