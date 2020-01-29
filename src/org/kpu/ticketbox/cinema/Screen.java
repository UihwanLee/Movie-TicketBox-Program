package org.kpu.ticketbox.cinema;
import org.kpu.ticketbox.payment.*;

import java.util.*;

public abstract class Screen {
	protected int nTicketPrice; // Ƽ�� ����
	protected int nRowMax; // �¼� �� �ִ� ��
	protected int nColMax; // �¼� �� �ִ� ��
	protected String strMovieName; // ������ ��ȭ����
	protected String strMovieStory; // ������ ��ȭ �ٰŸ�
	protected MovieTicket [][] seatArray; // �¼� 2���� �迭
	public abstract void showMovieInfo( ); // ��ȭ ���� �����ֱ�
	private int nCurrentReservedId = 100; // ���� ��ȣ 100������ ���� 
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>(); // ������ �ؽø�
	private int countPay = 0; // Ƽ�� �Ǹ� �Ϸ� ȸ��
	private Scanner scan; // ��ĳ�� ��ü
	
	Screen(String name, String story, int price, int row, int col) { // ������
		this.strMovieName = name;
		this.strMovieStory = story;
		this.nTicketPrice = price;
		this.nRowMax = row;
		this.nColMax = col;
		
		this.seatArray = new MovieTicket[row][col]; // �¼� ��ü �迭 ����
		initScreenSeat(); // �󿵰� �¼� �ʱ�ȭ
		
		scan = new Scanner(System.in); // ��ĳ�� ��ü ����
	}
	
	public void initScreenSeat() { // �󿵰� �¼� �ʱ�ȭ�ϱ�
		for(int i=0; i<nRowMax; i++)
			for(int j=0; j<nColMax; j++)
				this.seatArray[i][j] = new MovieTicket(MovieTicket.SEAT_EMPTY_MARK);
	}
	
	public void showScreenMenu() { // �󿵰� �޴� �����ֱ�
		System.out.println("-------------------");
		System.out.println(" ��ȭ �޴� - " + this.strMovieName);
		System.out.println("-------------------");
		System.out.println("1. �� ��ȭ ����");
		System.out.println("2. �¼� ���� ��Ȳ");
		System.out.println("3. �¼� ���� �ϱ�");
		System.out.println("4. �¼� ���� �ϱ�");
		System.out.println("5. ���� �޴� �̵�");
		System.out.println();
		
	}
	
	public void showSeatMap() { // �󿵰� �¼� ���� ��Ȳ �����ֱ�
		System.out.println("\t[ �¼� ���� ��Ȳ ]");
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
	
	public void reserveTicket() { // �¼� ����
		System.out.println(" [ �¼� ���� ]");
		System.out.print("�¼� �� ��ȣ �Է�(1 - " + nRowMax + ") : ");
		int row = scan.nextInt();
		System.out.print("�¼� �� ��ȣ �Է�(1 - " + nColMax + ") : ");
		int col = scan.nextInt();
		/* �¼� ������ 2���� �˻縦 ��ġ�� �ȴ�.
		 * 1. �ش� �¼� ��ȣ�� �����ϴ°�? -> �ش� �¼� ��ȣ�� �ε����� �Ѿ�� �ʴ°��� üũ
		 * 2. �ش� �¼� ��ȣ�� ���� or �����Ǿ� �ִ� �����ΰ�? -> �̹� ���� or �����Ǿ� �ִ� ��� �߰��� �������� �ʴ´�. 
		 */
		if(((row>=1 && row<=nRowMax) && (col>=1 && col<=nColMax))) {
			if(seatArray[row-1][col-1].getcStatus()== MovieTicket.SEAT_EMPTY_MARK) {
				seatArray[row-1][col-1].setSeat(row, col);
				seatArray[row-1][col-1].setnReservedId(nCurrentReservedId);
				seatArray[row-1][col-1].setcStatus(MovieTicket.SEAT_RESERVED_MARK); // ���� �Ϸ� ���� ��ũ�� ��ȯ
				System.out.println("��[" + row + "] ��[" + col + "] " + nCurrentReservedId++ + " ���� ��ȣ�� �����Ǿ����ϴ�.");
			} else {System.out.println("[ERROR] �Է��Ͻ� �¼��� �̹� ���� or �����Ǿ� �ִ� �����Դϴ�.");}
		} else {System.out.println("[ERROR] �Է��Ͻ� �¼� ��ȣ�� �������� �ʽ��ϴ�.");}
		System.out.println();
	}
	
	public void showPayMethod() { // ���� ��� ���� â
		System.out.println("-------------------");
		System.out.println("    ���� ��� ����          ");
		System.out.println("-------------------");
		System.out.println("1. ������ü");
		System.out.println("2. ī�����");
		System.out.println("3. ����ϰ���");
	}
	
	public void payment() { // ���� �ϱ�
		System.out.println(" [ �¼� ���� ���� ]");
		System.out.print("���� ��ȣ �Է� : ");
		int id = scan.nextInt();
		int row=-1, col=-1;
		
		// �Է��� ���� ��ȣ�� �����ϴ��� üũ
		for(int i=0; i<nRowMax; i++) {
			for(int j=0; j<nColMax; j++) {
				if(seatArray[i][j].getnReservedId()==id) {
					row = i; col=j;
				}
			}
		}
		/* �¼� ������ 2���� �˻縦 ��ġ�� �ȴ�.
		 * 1. �ش� �¼� ��ȣ�� ���� ��ȣ�� �����ϴ°�? -> �¼� �迭�� ���� ��ȣ�� �����ϴ���(���Ÿ� �Ͽ�����) üũ.
		 * 2. �ش� �¼��� ���� �Ϸ�� �������� ������? -> �̹� �����Ǿ� �ִ� ��� �߰��� �������� �ʴ´�. 
		 */
		if(row!=-1 && col!=-1) {
			if(seatArray[row][col].getcStatus()== MovieTicket.SEAT_RESERVED_MARK) {
				showPayMethod();
				System.out.print("���� ��� �Է�(1~3) : "); int choice = scan.nextInt();
				System.out.println();
				Pay pay = null; String name = null; String number = null;
				switch(choice) {
				case 1: 
					System.out.println(" [ ���� ��ü ] ");
					System.out.print("�̸� �Է� : "); name = scan.next();
					System.out.print("���� ��ȣ �Է�(12�ڸ���) : "); number = scan.next();
					BankTransfer banktransfer = new BankTransfer(); pay = banktransfer;
					break;
				case 2:
					System.out.println(" [ ī�� ���� ] ");
					System.out.print("�̸� �Է� : "); name = scan.next();
					System.out.print("ī�� ��ȣ �Է�(12�ڸ���) : "); number = scan.next();
					CardPay cardPay = new CardPay(); pay = cardPay;
					break;
				case 3:
					System.out.println(" [ ����� ���� ] ");
					System.out.print("�̸� �Է� : "); name = scan.next();
					System.out.print("�ڵ��� ��ȣ �Է�(11�ڸ���) : "); number = scan.next();
					MobilePay mobilePay = new MobilePay(); pay = mobilePay;
					break;
				default:
					System.out.println("[ERROR] �Է��Ͻ� ���� ����� �������� �ʽ��ϴ�.");
					break;
				}
				if(pay!=null) {
					Receipt receipt = pay.charge(strMovieName, nTicketPrice, name, number);
					if(receipt != null) {
						System.out.println(pay.getMethod() + " ������ �Ϸ�Ǿ����ϴ�. : " + receipt.gettotalAmount() + "��");
						seatArray[row][col].setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // ���� �Ϸ� ���� ��ũ�� ��ȯ
						receiptMap.put(id, receipt); // Ű(���� ��ȣ) + Receipt ��ü
						countPay++; // Ƽ�� �Ǹ� Ƚ�� ����
					}
				} 
			} else {System.out.println("[ERROR] �Է��Ͻ� ���� ��ȣ�� �̹� ���� �Ϸ�� �����Դϴ�.");}
		} else {System.out.println("[ERROR] �Է��Ͻ� ���� ��ȣ�� �������� �ʽ��ϴ�.");}
		System.out.println();
	}
	
	public HashMap<Integer, Receipt> getHasMap() { return receiptMap; }
	public int getCountPay() { return countPay; }

}
