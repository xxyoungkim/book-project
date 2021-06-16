package book.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.model.Book;
import book.model.User;

public class BookDAO implements BookAccess {

	
	static Connection conn;
	static PreparedStatement psmt;
	static ResultSet rs;
	String sql;
	ArrayList<Book> bookList;
	Book book;
	ArrayList<User> userList;
	User user;
	
	
	
	//사용자
	//전체조회
	@Override
	public ArrayList<Book> searchAll() {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book");
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return bookList;
	}

	
	//isbn으로 조회
	@Override
	public Book searchISBN(String isbn) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where isbn=?");
			psmt.setString(1, isbn);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return book;
	}

	
	//제목으로 조회
	@Override
	public ArrayList<Book> searchTitle(String title) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where title like ?");
			psmt.setString(1, "%" + title + "%");
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return bookList;
	}

	
	//작가이름으로 조회
	@Override
	public ArrayList<Book> searchAuthor(String author) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where author=?");
			psmt.setString(1, author);
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return bookList;
	}

	
	//출판사로 조회
	@Override
	public ArrayList<Book> searchPublisher(String publisher) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where publisher like ?");
			psmt.setString(1, "%" + publisher + "%");
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return bookList;
	}

	
	//책 분류로 조회
	@Override
	public ArrayList<Book> searchSubject(String subject) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where subject like ?");
			psmt.setString(1, "%" + subject + "%");
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return bookList;
	}

	
	//대여 -> 책을 대여해가면 count(책 권수)가 0으로 변경된다
	@Override
	public void rentalBook(String isbn) {
		connect();
		try {
			psmt = conn.prepareStatement("update book set count=0 where isbn=?");
			psmt.setString(1, isbn);
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}


	//반납 -> 책을 반납하면 count(책 권수)가 1으로 변경된다
	@Override
	public void returnBook(String isbn) {
		connect();
		try {
			psmt = conn.prepareStatement("update book set count=1 where isbn=?");
			psmt.setString(1, isbn);
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}


	//대출가능여부확인 -> count를 조회해서 count가 1이면 대여가능, 0이면 대여불가능
	@Override
	public int checkRental(String isbn) {
		connect();
		int count = 2;
		try {
			psmt = conn.prepareStatement("select count from book where isbn=?");
			psmt.setString(1, isbn);
			rs = psmt.executeQuery();
			if(rs.next()) {
//				book.setCount(rs.getInt("count"));
				count = rs.getInt("count");
//				System.out.println(count);
//				return count;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return count;
	}

	
	
	//대출 가능한 책 목록
	@Override
	public ArrayList<Book> ableRental() {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where count=1");
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return bookList;
	}
	
	
	@Override
	public ArrayList<Book> unableRental() {
		connect();
		try {
			psmt = conn.prepareStatement("select * from book where count=0");
			rs = psmt.executeQuery();
			bookList = new ArrayList<Book>();
			
			while(rs.next()) {
				book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setSubject(rs.getString("subject"));
				book.setTranslator(rs.getString("translator"));
				
				bookList.add(book);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		return bookList;
	}

	
	
	
	//회원가입
	public void signUp(String userId, String userPass, String userName, String userBirth, String userPhone) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into user values(?, ?, ?, ?, ?)");
			psmt.setString(1, userId);
			psmt.setString(2, userPass);
			psmt.setString(3, userName);
			psmt.setString(4, userBirth);
			psmt.setString(5, userPhone);
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	
	
	//로그인: idEqual && passEqual == 로그인 성공
	@Override
	public boolean logIn(String userId, String userPass) {
		boolean result;
		
		if(idEqual(userId)) {
			if(passEqual(userPass)) {
//				System.out.println("로그인 성공!");
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
	public boolean idEqual(String userId) {
		connect();
		boolean result = false;
		try {
			//입력한 u_id와 동일한 u_id가 있으면 조회
			psmt = conn.prepareStatement("select user_id from user where user_id=?");
			psmt.setString(1, userId);
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
	public boolean passEqual(String userPass) {
		connect();
		boolean result = false;
		try {
			psmt = conn.prepareStatement("select user_pass from user where user_pass=?");
			psmt.setString(1, userPass);
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
	
		
	//관리자로그인: idEqual && passEqual == 로그인 성공
	public boolean managerLogIn(String managerId, String managerPass) {
		boolean result;
		
		if(managerIdEqual(managerId)) {
			if(managerPassEqual(managerPass)) {
//				System.out.println("로그인 성공!");
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
	
	
	//관리자id확인 메소드
	public boolean managerIdEqual(String managerId) {
		connect();
		boolean result = false;
		try {
			//입력한 u_id와 동일한 u_id가 있으면 조회
			psmt = conn.prepareStatement("select manager_id from manager where manager_id=?");
			psmt.setString(1, managerId);
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
	
	
	
	//관리자password확인 메소드
	public boolean managerPassEqual(String managerPass) {
		connect();
		boolean result = false;
		try {
			psmt = conn.prepareStatement("select manager_pass from manager where manager_pass=?");
			psmt.setString(1, managerPass);
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
			psmt.setString(1, user.getUserPass());
			psmt.setString(2, user.getUserPhone());
			psmt.setString(3, user.getUserId());
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
			psmt = conn.prepareStatement("delete from user where user_pass=?");
			psmt.setString(1, user.getUserPass());
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}
	



	//회원정보 단건조회
	public User findOneUser(String userId) {
		connect();
		try {
			psmt = conn.prepareStatement("select * from user where user_id=?");
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserPass(rs.getString("user_pass"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
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
				user.setUserId(rs.getString("user_id"));
				user.setUserPass(rs.getString("user_pass"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
				
				userList.add(user);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
		return userList;
	}
	
		
	
	
	//관리자
	//등록
	@Override
	public void insertBook(Book book) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into book(isbn, title, author, publisher, subject, translator) values(?, ?, ?, ?, ?, ?)");
			psmt.setString(1, book.getIsbn());
			psmt.setString(2, book.getTitle());
			psmt.setString(3, book.getAuthor());
			psmt.setString(4, book.getPublisher());
			psmt.setString(5, book.getSubject());
			psmt.setString(6, book.getTranslator());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 등록하였습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}

	//변경 -> isbn으로 검색해서 변경
	@Override
	public void updateBook(Book book) {
		connect();
		try {
			psmt = conn.prepareStatement("update book set title=?, author=?, publisher=?, subject=?, translator=? where isbn=?");
			psmt.setString(1, book.getTitle());
			psmt.setString(2, book.getAuthor());
			psmt.setString(3, book.getPublisher());
			psmt.setString(4, book.getSubject());
			psmt.setString(5, book.getTranslator());
			psmt.setString(6, book.getIsbn());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경하였습니다.");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}

	//삭제 -> 제목으로 검색해서 삭제
	@Override
	public void deleteBook(String isbn) {
		connect();
		try {
			psmt = conn.prepareStatement("delete from book where isbn=?");
			psmt.setString(1, isbn);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 삭제하였습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {close();}
		
	}

	
	
	
	//연결
	public static void connect() {
		String url = "jdbc:sqlite:C:/sqlite/db/book.db";
		try {
			conn = DriverManager.getConnection(url);
//			System.out.println("연결성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//connect close
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
