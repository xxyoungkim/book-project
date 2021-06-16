package book.model;

public class Manager {
	
	private String managerId;
	private String managerPass;
	
	public Manager() {}

	public Manager(String managerId, String managerPass) {
		super();
		this.managerId = managerId;
		this.managerPass = managerPass;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPass() {
		return managerPass;
	}

	public void setManagerPass(String managerPass) {
		this.managerPass = managerPass;
	}

	@Override
	public String toString() {
		return "Manager [관리자아이디=" + managerId + ", 관리자비밀번호=" + managerPass + "]";
	}
	

}
