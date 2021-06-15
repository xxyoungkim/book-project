package book.view;

import java.util.Scanner;

import book.access.MemberAccess;
import book.access.UserDAO;
import book.model.User;
import book.util.ScannerUtil;

public class UserApp {
	
	static MemberAccess userList = new UserDAO();
	Scanner scanner = new Scanner(System.in);
	User user;
	
	
	//회원가입
	static String[] uList = { "아이디: ", "패스워드(문자+숫자): ", "이름: ", "생년월일: ", "연락처(숫자만 입력): " };
	public void signUp() {
		Scanner scanner = new Scanner(System.in);

		boolean[] result = null;
		String[] input = new String[5];

		System.out.println("----------------------------");
		System.out.println("        회원가입 페이지");
		System.out.println("----------------------------");

		while (true) {
			result = new boolean[] { false, false, false, false, false };

			//id
			System.out.print(uList[0]);
			input[0] = scanner.nextLine();
			char[] user_id = input[0].toCharArray();
			for(int i = 0; i < user_id.length; i++) {
				char temp_id = user_id[i];
				if(Character.isLetter(temp_id) || Character.isDigit(temp_id)) {				
					result[0] = true;
				}
				else {
					result[0] = false;
					break;
				}
			}

			//password
			System.out.print(uList[1]);
			input[1] = scanner.nextLine();
			if(input[1].length() > 5) { // && Character.isLetter(input[1].charAt(0))) {
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

			
			//name
			System.out.print(uList[2]);
			input[2] = scanner.nextLine();
			char[] user_name = input[2].toCharArray();
			for(int i = 0; i < user_name.length; i++) {
				char temp_name = user_name[i];
				if (Character.isLetter(temp_name)) {
					result[2] = true;
				}
				else {
					result[2] = false;
					break;
				}
			}
			
			
			//birth
			System.out.print(uList[3]);
			input[3] = scanner.nextLine();
			char[] user_birth = input[3].toCharArray();
			if(user_birth.length == 6) {
				result[3] = true;
			}	//길이가 6이면 true, 숫자가 아니면 false
			for(int i = 0; i < user_birth.length; i++) {
				if (!(Character.isDigit(user_birth[i]))) {
					result[3] = false;
					break;
				}
			}
			
			
			//phone
			System.out.print(uList[4]);
			input[4] = scanner.nextLine();
			char[] user_phone = input[4].toCharArray();
			if(user_phone.length == 11) {
				result[4] = true;
			}
			for(int i = 0; i < user_phone.length; i++) {
				if (!(Character.isDigit(user_phone[i]))) {
					result[4] = false;
					break;
				}
			}
			
			
			
			if(result[0] && result[1] && result[2] && result[3] && result[4]) {
				break;
			}
			if(!result[0]) {
				System.out.println("아이디 입력을 확인하세요.");
			}
			if(!result[1]) {
				System.out.println("패스워드 입력을 확인하세요.");
			}
			if(!result[2]) {
				System.out.println("이름 입력을 확인하세요.");
			}
			if(!result[3]) {
				System.out.println("생년월일 입력을 확인하세요.");
			}
			if(!result[4]) {
				System.out.println("연락처 입력을 확인하세요.");
			}
		}
		
		
//		User user = new User(input[0], input[1], input[2], input[3], input[4]);
		userList.signUp(input[0], input[1], input[2], input[3], input[4]);
		
		System.out.println("회원가입이 완료되었습니다.");

	} //end of signuUp
	

	
	
	//회원정보 수정
	public void updateUser() {
		new BookApp().findOneUser();
		
		//비밀번호, 휴대전화번호 수정 가능
		System.out.print("수정할 비밀번호> ");
		String user_pass = scanner.nextLine();
		if(! user_pass.equals("")) {
			user.setUser_pass(user_pass);
		}
		
		System.out.print("수정할 연락처> ");
		String user_phone = scanner.nextLine();
		if(! user_phone.equals("")) {
			user.setUser_phone(user_phone);
		}
		
		userList.updateUser(user);
		
	}
	
	
	//회원탈퇴
	public void deleteUser() {
		System.out.print("아이디를 입력하세요> ");
		String user_id = scanner.nextLine();
		System.out.print("비밀번호를 입력하세요> ");
		String user_pass = scanner.nextLine();
		if(user_id != null || user_pass != null) {
			System.out.print("정말로 탈퇴하시겠습니까?(y/n) ");
			String str = scanner.nextLine();
			if(str.equals("y") || str.equals("Y")) {
				userList.deleteUser(user);
			}
		}
	}
	

	
	//로그인체크
	public boolean idPassCheck() {
		boolean result = false;
		String user_id = ScannerUtil.readStr("아이디 입력");
		String user_pass = ScannerUtil.readStr("비밀번호 입력");
		boolean idPassCheck = userList.logIn(user_id, user_pass);
		
		if(idPassCheck == true) {
			result = true;
		}
		
		return result;
	}
	
	//로그인
	public void userLogin() {
		boolean result = false;
		do {
		if(idPassCheck()) {
			result = true;
			new BookApp().user();
		}
		else {
			result = false;
			System.out.println("로그인 실패!");
		}
		} while(result = true);
	}
	
	
	//로그아웃
	public void userLogout() {
		
	}
	
	
}
