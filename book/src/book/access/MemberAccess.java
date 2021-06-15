package book.access;

import java.util.ArrayList;

import book.model.User;

public interface MemberAccess {

	//회원가입
	public void signUp(String user_id, String user_pass, String user_name, String user_birth, String user_phone);
	
	//로그인: idEqual && passEqual == 로그인 성공
	public boolean logIn(String user_id, String user_pass);
	
	

	
	//로그아웃
	public void logOut();
	
	//회원정보수정
	public void updateUser(User user);

	//회원정보삭제
	public void deleteUser(User user);
	
	//회원정보 단건조회
	public User findOneUser(String user_id);

	
	//회원 전체조회
	public ArrayList<User> searchAllUser();
}
