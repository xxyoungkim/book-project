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
	public void rentalBook();
	
	//반납
	public void returnBook();
	
	//대출가능여부확인
	public void checkRental();
	
	
	
	//관리자
	//등록
	public void insertBook(Book book);
	
	//수정 -> isbn으로 검색해서 내용 수정
	public void updateBook(Book book);
	
	//삭제 -> isbn으로 검색해서 삭제
	public void deleteBook(String isbn);
	
}
