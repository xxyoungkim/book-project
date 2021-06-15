package book.access;

import java.util.ArrayList;

import book.model.Book;
import book.model.User;

public interface BookAccess {
	
	
	//사용자
	//전체 책 조회
	public ArrayList<Book> searchAll();
	
	//조회 -> isbn
	public Book searchISBN(String isbn);
	
	//조회 -> 책 제목
	public ArrayList<Book> searchTitle(String title);
	
	//조회 -> 작가 이름
	public ArrayList<Book> searchAuthor(String author);
	
	//조회 -> 출판사
	public ArrayList<Book> searchPublisher(String publisher);
	
	//조회 -> 책 분류
	public ArrayList<Book> searchSubject(String subject);
	
	
	//대출
	public void rentalBook(String isbn);
	
	//반납
	public void returnBook(String isbn);
	
	//대출가능여부확인
	public int checkRental(String isbn);
	
	//대출가능한책 목록
	public ArrayList<Book> ableRental();
	
	
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
	
	
	//관리자
	//등록
	public void insertBook(Book book);
	
	//수정 -> isbn으로 검색해서 내용 수정
	public void updateBook(Book book);
	
	//삭제 -> isbn으로 검색해서 삭제
	public void deleteBook(String isbn);
	
}
