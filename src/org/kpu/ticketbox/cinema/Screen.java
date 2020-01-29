package org.kpu.ticketbox.cinema;
import org.kpu.ticketbox.payment.*;

import java.util.*;

public abstract class Screen {
	protected int nTicketPrice; // 티켓 가격
	protected int nRowMax; // 좌석 행 최대 값
	protected int nColMax; // 좌석 열 최대 값
	protected String strMovieName; // 상영중인 영화제목
	protected String strMovieStory; // 상영중인 영화 줄거리
	protected MovieTicket [][] seatArray; // 좌석 2차원 배열
	public abstract void showMovieInfo( ); // 영화 정보 보여주기
	private int nCurrentReservedId = 100; // 예약 번호 100번부터 시작 
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>(); // 영수증 해시맵
	private int countPay = 0; // 티켓 판매 완료 회수
	private Scanner scan; // 스캐너 객체
	
	Screen(String name, String story, int price, int row, int col) { // 생성자
		this.strMovieName = name;
		this.strMovieStory = story;
		this.nTicketPrice = price;
		this.nRowMax = row;
		this.nColMax = col;
		
		this.seatArray = new MovieTicket[row][col]; // 좌석 객체 배열 생성
		initScreenSeat(); // 상영관 좌석 초기화
		
		scan = new Scanner(System.in); // 스캐너 객체 생성
	}
	
	public void initScreenSeat() { // 상영관 좌석 초기화하기
		for(int i=0; i<nRowMax; i++)
			for(int j=0; j<nColMax; j++)
				this.seatArray[i][j] = new MovieTicket(MovieTicket.SEAT_EMPTY_MARK);
	}
	
	public void showScreenMenu() { // 상영관 메뉴 보여주기
		System.out.println("-------------------");
		System.out.println(" 영화 메뉴 - " + this.strMovieName);
		System.out.println("-------------------");
		System.out.println("1. 상영 영화 정보");
		System.out.println("2. 좌석 예약 현황");
		System.out.println("3. 좌석 예약 하기");
		System.out.println("4. 좌석 결제 하기");
		System.out.println("5. 메인 메뉴 이동");
		System.out.println();
		
	}
	
	public void showSeatMap() { // 상영관 좌석 예약 현황 보여주기
		System.out.println("\t[ 좌석 예약 현황 ]");
		for(int i=0; i<nRowMax+1; i++) {
			if(i>0) System.out.print("["+i+"]");
			for(int j=0; j<nColMax; j++) {
				if(j==0) System.out.print("\t");
				if(i==0) System.out.print("["+(j+1)+"] ");
				else System.out.print("["+seatArray[i-1][j].getcStatus()+"] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void reserveTicket() { // 좌석 예약
		System.out.println(" [ 좌석 예약 ]");
		System.out.print("좌석 행 번호 입력(1 - " + nRowMax + ") : ");
		int row = scan.nextInt();
		System.out.print("좌석 열 번호 입력(1 - " + nColMax + ") : ");
		int col = scan.nextInt();
		/* 좌석 예약은 2가지 검사를 거치게 된다.
		 * 1. 해당 좌석 번호가 존재하는가? -> 해당 좌석 번호가 인덱스를 넘어가지 않는가를 체크
		 * 2. 해당 좌석 번호가 에약 or 결제되어 있는 상태인가? -> 이미 예약 or 결제되어 있는 경우 추가로 예약하지 않는다. 
		 */
		if(((row>=1 && row<=nRowMax) && (col>=1 && col<=nColMax))) {
			if(seatArray[row-1][col-1].getcStatus()== MovieTicket.SEAT_EMPTY_MARK) {
				seatArray[row-1][col-1].setSeat(row, col);
				seatArray[row-1][col-1].setnReservedId(nCurrentReservedId);
				seatArray[row-1][col-1].setcStatus(MovieTicket.SEAT_RESERVED_MARK); // 예매 완료 상태 마크로 변환
				System.out.println("행[" + row + "] 열[" + col + "] " + nCurrentReservedId++ + " 예약 번호로 접수되었습니다.");
			} else {System.out.println("[ERROR] 입력하신 좌석은 이미 예약 or 결제되어 있는 상태입니다.");}
		} else {System.out.println("[ERROR] 입력하신 좌석 번호는 존재하지 않습니다.");}
		System.out.println();
	}
	
	public void showPayMethod() { // 결제 방식 선택 창
		System.out.println("-------------------");
		System.out.println("    결제 방식 선택          ");
		System.out.println("-------------------");
		System.out.println("1. 은행이체");
		System.out.println("2. 카드결제");
		System.out.println("3. 모바일결제");
	}
	
	public void payment() { // 결제 하기
		System.out.println(" [ 좌석 예약 결제 ]");
		System.out.print("예약 번호 입력 : ");
		int id = scan.nextInt();
		int row=-1, col=-1;
		
		// 입력한 예약 번호가 존재하는지 체크
		for(int i=0; i<nRowMax; i++) {
			for(int j=0; j<nColMax; j++) {
				if(seatArray[i][j].getnReservedId()==id) {
					row = i; col=j;
				}
			}
		}
		/* 좌석 예약은 2가지 검사를 거치게 된다.
		 * 1. 해당 좌석 번호의 예매 번호가 존자하는가? -> 좌석 배열에 예매 번호가 존재하는지(예매를 하였는지) 체크.
		 * 2. 해당 좌석이 결제 완료된 상태이진 않은가? -> 이미 결제되어 있는 경우 추가로 결제하지 않는다. 
		 */
		if(row!=-1 && col!=-1) {
			if(seatArray[row][col].getcStatus()== MovieTicket.SEAT_RESERVED_MARK) {
				showPayMethod();
				System.out.print("결제 방식 입력(1~3) : "); int choice = scan.nextInt();
				System.out.println();
				Pay pay = null; String name = null; String number = null;
				switch(choice) {
				case 1: 
					System.out.println(" [ 은행 이체 ] ");
					System.out.print("이름 입력 : "); name = scan.next();
					System.out.print("은행 번호 입력(12자리수) : "); number = scan.next();
					BankTransfer banktransfer = new BankTransfer(); pay = banktransfer;
					break;
				case 2:
					System.out.println(" [ 카드 결제 ] ");
					System.out.print("이름 입력 : "); name = scan.next();
					System.out.print("카드 번호 입력(12자리수) : "); number = scan.next();
					CardPay cardPay = new CardPay(); pay = cardPay;
					break;
				case 3:
					System.out.println(" [ 모바일 결제 ] ");
					System.out.print("이름 입력 : "); name = scan.next();
					System.out.print("핸드폰 번호 입력(11자리수) : "); number = scan.next();
					MobilePay mobilePay = new MobilePay(); pay = mobilePay;
					break;
				default:
					System.out.println("[ERROR] 입력하신 결제 방식이 존재하지 않습니다.");
					break;
				}
				if(pay!=null) {
					Receipt receipt = pay.charge(strMovieName, nTicketPrice, name, number);
					if(receipt != null) {
						System.out.println(pay.getMethod() + " 결제가 완료되었습니다. : " + receipt.gettotalAmount() + "원");
						seatArray[row][col].setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // 결제 완료 상태 마크로 변환
						receiptMap.put(id, receipt); // 키(예약 번호) + Receipt 객체
						countPay++; // 티켓 판매 횟수 증가
					}
				} 
			} else {System.out.println("[ERROR] 입력하신 예매 번호는 이미 결제 완료된 상태입니다.");}
		} else {System.out.println("[ERROR] 입력하신 예약 번호가 존재하지 않습니다.");}
		System.out.println();
	}
	
	public HashMap<Integer, Receipt> getHasMap() { return receiptMap; }
	public int getCountPay() { return countPay; }

}
