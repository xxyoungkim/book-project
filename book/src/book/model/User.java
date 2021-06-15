package book.model;

public class User {
	
	private String user_id;
	private String user_pass;
	private String user_name;
	private String user_birth;
	private String user_phone;
	
	
	public User() {}
	
	

	public User(String user_id, String user_pass, String user_name, String user_birth, String user_phone) {
		super();
		this.user_id = user_id;
		this.user_pass = user_pass;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_phone = user_phone;
		
	}



	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_pass=" + user_pass + ", user_name=" + user_name + ", user_birth="
				+ user_birth + ", user_phone=" + user_phone + "]";
	}
	
}
