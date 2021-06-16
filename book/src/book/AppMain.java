package book;

import java.util.Scanner;

import book.view.BookGUIApp;

public class AppMain {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
//		System.out.println("==초기화면==");
//		String str1 = ScannerUtil.readStr("회원가입/로그인");
//		if(str1.equals("회원가입")) {
//			new BookApp().signUp();
//		}
//		else if(str1.equals("로그인")) {
//			String str2 = ScannerUtil.readStr("user/manager");
//			if(str2.equals("user")) {				
//				new BookApp().userLogin();
//			}
//			else if(str2.equals("manager")) {
//				new BookApp().managerLogin();
//			}
//			else {
//				System.out.println("정확하게 입력해주세요.");
//				main(args);
//			}
//		}
//		else {
//			main(args);
//		}
		
//		new BookApp().user();
		
		new BookGUIApp();
		
	}
	
}
