package org.kpu.ticketbox.main;
import java.util.Scanner;
import org.kpu.ticketbox.cinema.*;
import org.kpu.ticketbox.util.BackupWriter;
import org.kpu.ticketbox.util.Statistics;

public class TicketBox {
	
	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	public static final String ADMIN_PASSWORD = "admin";
	
	Scanner scan = new Scanner(System.in);
	
	public void initScreen() {	
		// Screen(영화제목, 영화 줄거리, 티켓가격, 좌석(rowMax), 좌석(colMax))
		famillyScreen = new FamillyScreen("굿 월 헌팅", "천재적 두뇌를 가진 불우한 반항아", 8000, 10, 10);
		animationScreen = new AnimationScreen("아담스 패밀리", "세상에서 가장 무섭고 사랑스러운 가족 어드벤처", 10000, 10, 10);
		premiumScreen = new PremiumScreen("매트릭스", "인공 두뇌를 가진 컴퓨터가 지배하는 가상현실 공간 매트릭스 ", 30000, 5, 5);
	}
	
	void managerMode() { // 관리자 기능
		BackupWriter backupwriter = new BackupWriter();
		Screen screen1 = famillyScreen; Screen screen2 = animationScreen; Screen screen3 = premiumScreen;
		System.out.print("암호 입력: ");
		String password = scan.next();
		if((password.equals(ADMIN_PASSWORD))==true) {
			System.out.println("-------------------");
			System.out.println("----  관리자 기능  ----");
			System.out.println("-------------------");
			System.out.println("가족 영화관 결제 총액 : " + Statistics.sum(screen1.getHasMap()) + "원");
			System.out.println("애니메이션 영화관 결제 총액 : " + Statistics.sum(screen2.getHasMap()) + "원");
			System.out.println("프리미엄 영화관 결제 총액 : " + Statistics.sum(screen3.getHasMap()) + "원");
			System.out.println("영화관 총 티켓 판매량 : " + (screen1.getCountPay()+screen2.getCountPay()+screen3.getCountPay()));
			System.out.println(" c:\\\\temp\\\\receipt.txt 백업시작합니다.");
			backupwriter.backupFile("c:\\\\temp\\\\receipt.txt", screen1.getHasMap()); System.out.println("가족 영화관 매출 백업 완료");
			backupwriter.backupFile("c:\\\\temp\\\\receipt.txt", screen2.getHasMap()); System.out.println("애니메이션 영화관 매출 백업 완료");
			backupwriter.backupFile("c:\\\\temp\\\\receipt.txt", screen3.getHasMap()); System.out.println("프리미엄 영화관 매출 백업 완료");
			System.out.println();
		} else {System.out.print("[ERROR] 암호가 맞지 않습니다.");}
	}
	
	public Screen selectScreen() {
		System.out.println("-------------------");
		System.out.println("-  상영관 선택 메인메뉴    -");
		System.out.println("-------------------");
		System.out.println("1. 가족영화 1관");
		System.out.println("2. 애니메이션 영화 2관");
		System.out.println("3. 프리미엄 영화 3관 (식사, 커피 제공)");
		System.out.println("9. 관리자 메뉴");
		System.out.println("  선택(1~3, 9) 외 종료");
		System.out.println();
		System.out.print("상영관 선택 : ");
		int choice = scan.nextInt();
		switch(choice) {
		case 1: 
			return famillyScreen;
		case 2:
			return animationScreen;
		case 3:
			return premiumScreen;
		case 9:
			managerMode(); return null;
		default:
			return null;
		}
	}
}
