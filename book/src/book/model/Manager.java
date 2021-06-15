package book.model;

public class Manager {
	
	private String manager_id;
	private String manager_pass;
	
	public Manager() {}
	
	
	public Manager(String manager_id, String manager_pass) {
		super();
		this.manager_id = manager_id;
		this.manager_pass = manager_pass;
	}


	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_pass() {
		return manager_pass;
	}
	public void setManager_pass(String manager_pass) {
		this.manager_pass = manager_pass;
	}
	
	
	@Override
	public String toString() {
		return "Manager [manager_id=" + manager_id + ", manager_pass=" + manager_pass + "]";
	}
	
}
