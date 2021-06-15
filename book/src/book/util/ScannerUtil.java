package book.util;

import java.util.Scanner;


public class ScannerUtil {
	static Scanner scanner = new Scanner(System.in);
	
	
	public static int readInt(String prompt) {
		System.out.print(prompt + "> ");
		return readInt();
	}
	
	public static int readInt() {
		int result = 0;
		while(true) {
			try {
				String temp = scanner.nextLine();
				result = Integer.parseInt(temp);//scanner.nextInt();
				break;
			} catch(Exception e) {
				System.out.println("숫자 형식이 아닙니다.");
			}
		}
		return result;
	}
	
	
	
	public static String readStr(String prompt) {
		System.out.print(prompt + "> ");
		return readStr();
	}
	
	public static String readStr() {
		String result = "";
		try {
			result = scanner.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
