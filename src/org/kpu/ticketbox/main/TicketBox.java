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
		// Screen(��ȭ����, ��ȭ �ٰŸ�, Ƽ�ϰ���, �¼�(rowMax), �¼�(colMax))
		famillyScreen = new FamillyScreen("�� �� ����", "õ���� �γ��� ���� �ҿ��� ���׾�", 8000, 10, 10);
		animationScreen = new AnimationScreen("�ƴ㽺 �йи�", "���󿡼� ���� ������ ��������� ���� ��庥ó", 10000, 10, 10);
		premiumScreen = new PremiumScreen("��Ʈ����", "�ΰ� �γ��� ���� ��ǻ�Ͱ� �����ϴ� �������� ���� ��Ʈ���� ", 30000, 5, 5);
	}
	
	void managerMode() { // ������ ���
		BackupWriter backupwriter = new BackupWriter();
		Screen screen1 = famillyScreen; Screen screen2 = animationScreen; Screen screen3 = premiumScreen;
		System.out.print("��ȣ �Է�: ");
		String password = scan.next();
		if((password.equals(ADMIN_PASSWORD))==true) {
			System.out.println("-------------------");
			System.out.println("----  ������ ���  ----");
			System.out.println("-------------------");
			System.out.println("���� ��ȭ�� ���� �Ѿ� : " + Statistics.sum(screen1.getHasMap()) + "��");
			System.out.println("�ִϸ��̼� ��ȭ�� ���� �Ѿ� : " + Statistics.sum(screen2.getHasMap()) + "��");
			System.out.println("�����̾� ��ȭ�� ���� �Ѿ� : " + Statistics.sum(screen3.getHasMap()) + "��");
			System.out.println("��ȭ�� �� Ƽ�� �Ǹŷ� : " + (screen1.getCountPay()+screen2.getCountPay()+screen3.getCountPay()));
			System.out.println(" c:\\\\temp\\\\receipt.txt ��������մϴ�.");
			backupwriter.backupFile("c:\\\\temp\\\\receipt.txt", screen1.getHasMap()); System.out.println("���� ��ȭ�� ���� ��� �Ϸ�");
			backupwriter.backupFile("c:\\\\temp\\\\receipt.txt", screen2.getHasMap()); System.out.println("�ִϸ��̼� ��ȭ�� ���� ��� �Ϸ�");
			backupwriter.backupFile("c:\\\\temp\\\\receipt.txt", screen3.getHasMap()); System.out.println("�����̾� ��ȭ�� ���� ��� �Ϸ�");
			System.out.println();
		} else {System.out.print("[ERROR] ��ȣ�� ���� �ʽ��ϴ�.");}
	}
	
	public Screen selectScreen() {
		System.out.println("-------------------");
		System.out.println("-  �󿵰� ���� ���θ޴�    -");
		System.out.println("-------------------");
		System.out.println("1. ������ȭ 1��");
		System.out.println("2. �ִϸ��̼� ��ȭ 2��");
		System.out.println("3. �����̾� ��ȭ 3�� (�Ļ�, Ŀ�� ����)");
		System.out.println("9. ������ �޴�");
		System.out.println("  ����(1~3, 9) �� ����");
		System.out.println();
		System.out.print("�󿵰� ���� : ");
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
