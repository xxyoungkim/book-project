package book.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import book.AppMain;
import book.access.BookAccess;
import book.access.BookDAO;
import book.model.Book;
import book.model.User;
import book.util.ScannerUtil;

public class BookApp {

	BookAccess bookList = new BookDAO();
	Scanner scanner = new Scanner(System.in);
	Book book;
//	User user;
	static String connectingID;
	static String connectingPass;
	static ArrayList<String> rentalingBook;

	// 사용자

	public void user() {
		int usernum;

		do {
			userTitle();
			usernum = ScannerUtil.readInt("선택");

			switch (usernum) {
			case 1: searchAll(); break;
			case 2: searchISBN(); break;
			case 3: searchTitle(); break;
			case 4: searchAuthor(); break;
			case 5: searchPublisher(); break;
			case 6: searchSubject(); break;
			case 7: rentalBook(); break;
			case 8: returnBook(); break;
			case 9: checkRental(); break;
			case 10: updateUser(); break;
			case 11: deleteUser(); break;
			case 0: userLogout(); break;
			}

		} while (usernum != 0);
	}

	public void userTitle() {
		System.out.println("1.전체조회");
		System.out.println("2.ISBN조회");
		System.out.println("3.제목조회");
		System.out.println("4.작가조회");
		System.out.println("5.출판사조회");
		System.out.println("6.분류조회");
		System.out.println("7.책대여");
		System.out.println("8.책반납");
		System.out.println("9.대여가능한지조회");
		System.out.println("---------");
		System.out.println("10.회원정보수정");
		System.out.println("11.회원탈퇴");
		System.out.println("0.종료");

	}

	// 관리자

	public void manager() {
		int managernum;

		do {
			managerTitle();
			managernum = ScannerUtil.readInt("선택");

			switch (managernum) {
			case 1: searchAll(); break;
			case 2: insertBook(); break;
			case 3: updateBook(); break;
			case 4: deleteBook(); break;
			case 5: searchAllUser(); break;
			case 6: findOneUser(); break;
			case 0: userLogout(); break;
			}

		} while (managernum != 0);
	}

	public void managerTitle() {
		System.out.println("1.책전체조회");
		System.out.println("2.책등록");
		System.out.println("3.책수정");
		System.out.println("4.책삭제");
		System.out.println("5.회원전체조회");
		System.out.println("6.회원선택조회");
		System.out.println("0.종료");
	}

	// 1.전체조회@@@ ////////////////기능조회
	public void searchAll() {
		System.out.print("전체조회(1)/대여가능책조회(2)> ");
		int selectNum = ScannerUtil.readInt();
		if (selectNum == 1) {
			ArrayList<Book> list = bookList.searchAll();
			for (Book bAll : list) {
				System.out.println(bAll);
			}
		} else if (selectNum == 2) {
			ArrayList<Book> list = bookList.ableRental();
			for (Book bAble : list) {
				System.out.println(bAble);
			}
		}
	}

	//대여가능 책 조회(count = 1인 * select)
	public void ableRental() {
		ArrayList<Book> list = bookList.ableRental();
		for(Book b : list) {
			System.out.println(b);
		}
	}
	
	
	//대여불가능 책 조회(count = 0)
	public void unableRental() {
		ArrayList<Book> list = bookList.unableRental();
		for(Book b : list) {
			System.out.println(b);
		}
	}
	
	

	// 2.조회 -> isbn@@@
	public void searchISBN() {
		String isbn = ScannerUtil.readStr("isbn 입력");
		Book book = bookList.searchISBN(isbn);
		System.out.println(book);
	}

	// 3.조회 -> 책 제목@@@
	public void searchTitle() {
		String title = ScannerUtil.readStr("책제목 입력");
		ArrayList<Book> list = bookList.searchTitle(title);
		for (Book b : list) {
			System.out.println(b);
		}
	}

	// 4.조회 -> 작가 이름@@@
	public void searchAuthor() {
		String author = ScannerUtil.readStr("작가이름 입력");
		ArrayList<Book> list = bookList.searchAuthor(author);
		for (Book b : list) {
			System.out.println(b);
		}
	}

	// 5.조회 -> 출판사@@@
	public void searchPublisher() {
		String publisher = ScannerUtil.readStr("출판사 입력");
		ArrayList<Book> list = bookList.searchPublisher(publisher);
		for (Book b : list) {
			System.out.println(b);
		}
	}

	// 6.조회 -> 책 분류@@@
	public void searchSubject() {
		String subject = ScannerUtil.readStr("책분류 입력");
		ArrayList<Book> list = bookList.searchSubject(subject);
		for (Book b : list) {
			System.out.println(b);
		}
	}

	// 7.대여@@@
	public void rentalBook() {
		int count = 0;
		rentalingBook = new ArrayList<String>();
		while (count < 3) {
			searchTitle();
			System.out.print("대여할 책의 ISBN 입력> ");
			String isbn = ScannerUtil.readStr();
			bookList.rentalBook(isbn);
			rentalingBook.add(isbn);
			System.out.println("대여하였습니다.");
			count++;
			if (count < 3) {
				System.out.print("추가로 대여하시겠습니까?(y/n) ");
				String str = scanner.nextLine();
				if (str.equals("y") || str.equals("Y")) {
					continue;
				} else if (str.equals("n") || str.equals("N")) {
					break;
				}
			}
		}
		System.out.println("최대 3권까지 대여 가능합니다.");
		System.out.println("==대여하신 책의 ISBN 목록==");
		ArrayList<Book> list = bookList.unableRental();
		for(Book b : list) {
			System.out.println(b);
		}
//		for (String r_isbn : rentalingBook) {
//			System.out.println(r_isbn);
//			if(r_isbn == null) {
//				System.out.println("대여중인 책이 없습니다.");
//			}
//		}
	}

	// 8.반납@@@
	public void returnBook() {
		int count = 0;
		System.out.println("==대여하신 책의 ISBN 목록==");
		unableRental();
//		if(rentalingBook.size() == 0) {
//			System.out.println("대여중인 책이 없습니다.");
//		}
//		else {
//			for (String r_isbn : rentalingBook) {
//				System.out.println(r_isbn);
//			}
//		}
		


		while (count < rentalingBook.size()) {
			System.out.print("반납할 책의 ISBN 입력> ");
			String isbn = ScannerUtil.readStr();
			bookList.returnBook(isbn);
			// 여기까지 수정
			Iterator it = rentalingBook.iterator();
			while (it.hasNext()) {
				String str2 = (String) it.next();
				if (str2 == isbn) {
					it.remove();
				}
			}
			System.out.println("반납하였습니다.");
			if (count < rentalingBook.size()) {
				System.out.print("추가로 반납하시겠습니까?(y/n) ");
				String str = scanner.nextLine();
				if (str.equals("y") || str.equals("Y")) {
					continue;
				} else if (str.equals("n") || str.equals("N")) {
					break;
				}
			}
		}

	}

	// 9.대여가능여부 확인 -> count가 0이면 불가능, 1이면 가능@@@
	public void checkRental() {
		System.out.print("대여가능책조회(1)/ISBN으로 조회(2)> ");
		int selectNum = ScannerUtil.readInt();
		if(selectNum == 1) {
			ArrayList<Book> list = bookList.ableRental();
			for (Book bAble : list) {
				System.out.println(bAble);
			}
		}
		else if(selectNum == 2) {
			
			System.out.print("조회할 ISBN 입력> ");
			String isbn = scanner.nextLine();
			int count = bookList.checkRental(isbn);
			
			if (count == 0) {
				System.out.println("현재 대여중인 책입니다.");
			} else if (count == 1) {
				System.out.println("대여가 가능한 책입니다.");
			}
		}

	}

	// 회원가입
	static String[] uList = { "아이디: ", "패스워드(문자+숫자): ", "이름: ", "생년월일(6자리): ", "연락처: " };

	public void signUp() {
		Scanner scanner = new Scanner(System.in);

		boolean[] result = null;
		String[] input = new String[5];

		System.out.println("----------------------------");
		System.out.println("        회원가입 페이지");
		System.out.println("----------------------------");

		while (true) {
			result = new boolean[] { false, false, false, false, false };

			// id
			System.out.print(uList[0]);
			input[0] = scanner.nextLine();
			char[] user_id = input[0].toCharArray();
			for (int i = 0; i < user_id.length; i++) {
				char temp_id = user_id[i];
				if (Character.isLetter(temp_id) || Character.isDigit(temp_id)) {
					result[0] = true;
				} else {
					result[0] = false;
					break;
				}
			}

			// password
			System.out.print(uList[1]);
			input[1] = scanner.nextLine();
			if (input[1].length() > 5) { // && Character.isLetter(input[1].charAt(0))) {
				char[] user_pass = input[1].toCharArray();
				for (int i = 0; i < user_pass.length; i++) {
					char temp_pass = user_pass[i];
					if (Character.isLetter(temp_pass) || Character.isDigit(temp_pass)) {
						result[1] = true;
					} else {
						result[1] = false;
						break;
					}
				}
			}

			// name
			System.out.print(uList[2]);
			input[2] = scanner.nextLine();
			char[] user_name = input[2].toCharArray();
			for (int i = 0; i < user_name.length; i++) {
				char temp_name = user_name[i];
				if (Character.isLetter(temp_name)) {
					result[2] = true;
				} else {
					result[2] = false;
					break;
				}
			}

			// birth
			System.out.print(uList[3]);
			input[3] = scanner.nextLine();
			char[] user_birth = input[3].toCharArray();
			if (user_birth.length == 6) {
				result[3] = true;
			} // 길이가 6이면 true, 숫자가 아니면 false
			for (int i = 0; i < user_birth.length; i++) {
				if (!(Character.isDigit(user_birth[i]))) {
					result[3] = false;
					break;
				}
			}

			// phone
			System.out.print(uList[4]);
			input[4] = scanner.nextLine();
			char[] user_phone = input[4].toCharArray();
			if (user_phone.length == 11 || user_phone.length == 13) {
				result[4] = true;
			}
			for (int i = 0; i < user_phone.length; i++) {
				if ( (Character.isLetter(user_phone[i])) ) {
					result[4] = false;
					break;
				}
			}

			if (result[0] && result[1] && result[2] && result[3] && result[4]) {
				break;
			}
			if (!result[0]) {
				System.out.println("아이디 입력을 확인하세요.");
			}
			if (!result[1]) {
				System.out.println("패스워드 입력을 확인하세요.");
			}
			if (!result[2]) {
				System.out.println("이름 입력을 확인하세요.");
			}
			if (!result[3]) {
				System.out.println("생년월일 입력을 확인하세요.");
			}
			if (!result[4]) {
				System.out.println("연락처 입력을 확인하세요.");
			}
		}

//		User user = new User(input[0], input[1], input[2], input[3], input[4]);
		bookList.signUp(input[0], input[1], input[2], input[3], input[4]);

		System.out.println("회원가입이 완료되었습니다.");
		userLogin();

	} // end of signuUp


	
	
	// 회원정보 수정
	public void updateUser() {
		String user_id = ScannerUtil.readStr("id 입력");
		User user = bookList.findOneUser(user_id);
		if(! user_id.equals(connectingID)) {	
			System.out.println("자신의 회원정보만 수정할 수 있습니다.");
			user();
		}

		// 비밀번호, 휴대전화번호 수정 가능
		System.out.print("수정할 비밀번호> ");
		String user_pass = scanner.nextLine();
		if (!user_pass.equals("")) {
			user.setUser_pass(user_pass);
		}

		System.out.print("수정할 연락처> ");
		String user_phone = scanner.nextLine();
		if (!user_phone.equals("")) {
			user.setUser_phone(user_phone);
		}
		
		bookList.updateUser(user);

	}

	// 회원탈퇴
	public void deleteUser() {
		String user_pass = ScannerUtil.readStr("비밀번호 입력");
		User user = bookList.findOneUser(user_pass);
		if(! user_pass.equals(connectingPass)) {	
			System.out.println("자신만 탈퇴할 수 있습니다.");
			user();
		}
//		System.out.print("비밀번호를 입력하세요> ");
//		String user_pass = scanner.nextLine();
		if (user_pass != null) {
			System.out.print("정말로 탈퇴하시겠습니까?(y/n) ");
			String str = scanner.nextLine();
			if (str.equals("y") || str.equals("Y")) {
				bookList.deleteUser(user);
				System.out.println("탈퇴하였습니다.");
				new AppMain().main(null);
			}
		}
	}

	//로그인체크
	public boolean idPassCheck() {
		boolean result = false;
		String user_id = ScannerUtil.readStr("아이디 입력");
		String user_pass = ScannerUtil.readStr("비밀번호 입력");
		boolean idPassCheck = bookList.logIn(user_id, user_pass);
		connectingID = user_id;
		connectingPass = user_pass;

		if(idPassCheck == true) {
			result = true;
		}

		return result;
	}

	//로그인
	public void userLogin() {
		boolean result = false;
		do {
			if (idPassCheck()) {
				result = true;
				System.out.println("로그인 성공!");
				new BookApp().user();
			} else {
				result = false;
				System.out.println("로그인 실패!");
			}
		} while (result = true);
	}
	
	//관리자로그인체크
	public boolean managerIdPassCheck() {
		boolean result = false;
		String manager_id = ScannerUtil.readStr("아이디 입력");
		String manager_pass = ScannerUtil.readStr("비밀번호 입력");
		boolean managerIdPassCheck = bookList.managerLogIn(manager_id, manager_pass);

		if(managerIdPassCheck == true) {
			result = true;
		}

		return result;
	}
		
	//관리자 로그인
	public void managerLogin() {
		boolean result = false;
		do {
			if (managerIdPassCheck()) {
				result = true;
				System.out.println("관리자 로그인 성공!");
				new BookApp().manager();
			} else {
				result = false;
				System.out.println("관리자 로그인 실패!");
			}
		} while (result = true);
	}
	

	// 로그아웃
	public void userLogout() {
		System.out.println("로그아웃");
		new AppMain().main(null);
	}

	// 관리자
	// 책 등록////////////////////////////////등록할 때 이미 있는 isbn이면 "이미 있는 isbn" 출력
	public void insertBook() {
		Book book = new Book();
		book.setIsbn(ScannerUtil.readStr("isbn 입력"));
		book.setTitle(ScannerUtil.readStr("책제목 입력"));
		book.setAuthor(ScannerUtil.readStr("작가 입력"));
		book.setPublisher(ScannerUtil.readStr("출판사 입력"));
		book.setSubject(ScannerUtil.readStr("책분류 입력"));
		book.setTranslator(ScannerUtil.readStr("옮긴이 입력(없으면 null 입력)"));
		bookList.insertBook(book);
	}

	// 책 수정 -> isbn으로 검색해서 내용 수정
	public void updateBook() {
		searchISBN();
//		System.out.print("조회할 isbn> ");
//		String isbn = scanner.nextLine();

		System.out.print("수정할 제목> ");
		String title = scanner.nextLine();
		if (!title.equals("")) {
			book.setTitle(title);
		}

		System.out.print("수정할 작가이름> ");
		String author = scanner.nextLine();
		if (!author.equals("")) {
			book.setAuthor(author);
		}

		System.out.print("수정할 출판사> ");
		String publisher = scanner.nextLine();
		if (!publisher.equals("")) {
			book.setPublisher(publisher);
		}

		System.out.print("수정할 책분류> ");
		String subject = scanner.nextLine();
		if (!subject.equals("")) {
			book.setSubject(subject);
		}

		System.out.print("수정할 옮긴이> ");
		String translator = scanner.nextLine();
		if (!translator.equals("")) {
			book.setTranslator(translator);
		}

		bookList.updateBook(book);
	}

	// 책 삭제 -> 책제목 조회 ->하나를 선택해서 삭제
	public void deleteBook() {
		searchTitle();
		System.out.print("삭제할 책> ");
		String isbn = scanner.nextLine();
		if (isbn != null) {
			System.out.print("정말로 삭제하시겠습니까?(y/n) ");
			String str = scanner.nextLine();
			if (str.equals("y") || str.equals("Y")) {
				bookList.deleteBook(isbn);
			}
		}
	}

	// 회원조회
	public void searchAllUser() {
		ArrayList<User> list = bookList.searchAllUser();
		for (User u : list) {
			System.out.println(u);
		}
	}

	// 회원 단건 조회
	public void findOneUser() {
		String user_id = ScannerUtil.readStr("id 입력");
		User user = bookList.findOneUser(user_id);
		System.out.println(user);
	}

}
