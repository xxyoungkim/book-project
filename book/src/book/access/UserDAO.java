package book.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.model.User;

public class UserDAO implements MemberAccess {
	
	static PreparedStatement psmt;
	static ResultSet rs;
	static Connection conn;
	String sql;
	ArrayList<User> userList;
	User user;
	
	
	//회원가입
	public void signUp(String user_id, String user_pass, String user_name, String user_birth, String user_phone) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into user values(?, ?, ?, ?, ?)");
			psmt.setString(1, user_id);
			psmt.setString(2, user_pass);
			psmt.setString(3, user_name);
			psmt.setString(4, user_birth);
			psmt.setString(5, user_phone);
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	
	//로그인: idEqual && passEqual == 로그인 성공
	@Override
	public boolean logIn(String user_id, String user_pass) {
		boolean result;
		
		if(idEqual(user_id)) {
			if(passEqual(user_pass)) {
				System.out.println("로그인 성공!");
				result = true;
			}
			else {
				System.out.println("비밀번호가 틀렸습니다.");
				result = false;
			}
		}
		else {
			System.out.println("존재하지 않는 아이디입니다.");
			result = false;
		}
		
		return result;
	}
	
	
	//id확인 메소드
	public boolean idEqual(String user_id) {
		connect();
		boolean result = false;
		try {
			//입력한 u_id와 동일한 u_id가 있으면 조회
			psmt = conn.prepareStatement("select user_id from user where user_id=?");
			psmt.setString(1, user_id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = true;
				return result;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return result;
	}
	
	
	
	//password확인 메소드
	public boolean passEqual(String user_pass) {
		connect();
		boolean result = false;
		try {
			psmt = conn.prepareStatement("select user_pass from user where user_pass=?");
			psmt.setString(1, user_pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = true;
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return result;
	}
	
		
	
	
	//로그아웃
	@Override
	public void logOut() {
		connect();
		
	}
	
	
	//회원정보수정 -> 아이디를 입력해서 비밀번호, 휴대전화번호 수정
	@Override
	public void updateUser(User user) {
		connect();
		try {
			psmt = conn.prepareStatement("update user set user_pass=?, user_phone=? where user_id=?");
			psmt.setString(1, user.getUser_pass());
			psmt.setString(2, user.getUser_phone());
			psmt.setString(3, user.getUser_id());
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	
	//회원정보삭제 -> 비밀번호 입력해서 삭제
	@Override
	public void deleteUser(User user) {
		connect();
		try {
			psmt = conn.prepareStatement("delete from user where user_id=?, user_pass=?");
			psmt.setString(1, user.getUser_id());
			psmt.setString(2, user.getUser_pass());
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	



	//회원정보 단건조회
	public User findOneUser(String user_id) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from user where user_id=?");
			psmt.setString(1, user_id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pass(rs.getString("user_pass"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_phone(rs.getString("user_phone"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return user;
	}
	

	@Override
	public ArrayList<User> searchAllUser() {
		connect();
		userList = new ArrayList<User>();
		try {
			psmt = conn.prepareStatement("select * from user");
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setUser_id(rs.getString("user_id"));
				user.setUser_pass(rs.getString("user_pass"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_phone(rs.getString("user_phone"));
				
				userList.add(user);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return userList;
	}
	
	
	
	//연결메소드
	public static void connect() {
		String url = "jdbc:sqlite:C:/sqlite/db/book.db";
		try {
			conn = DriverManager.getConnection(url);
//			System.out.println("연결성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//connect닫는메소드
	public static void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //end of if

		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} //end of if
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// end of if
	} //end of close


}
