package book.model;

public class User {
	
	private String userId;
	private String userPass;
	private String userName;
	private String userBirth;
	private String userPhone;
	
	
	public User() {}


	public User(String userId, String userPass, String userName, String userBirth, String userPhone) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userPhone = userPhone;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPass() {
		return userPass;
	}


	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserBirth() {
		return userBirth;
	}


	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	@Override
	public String toString() {
		return "User [아이디=" + userId + ", 비밀번호=" + userPass + ", 이름=" + userName + ", 생년월일="
				+ userBirth + ", 연락처=" + userPhone + "]";
	}
	

}
