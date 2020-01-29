package org.kpu.ticketbox.main;
import java.util.*;
import org.kpu.ticketbox.cinema.*;

public class TicketBoxMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		boolean bMainMenu = true; // �󿵰� ���� �޴� ���
		ticketBox.initScreen(); // �ʱ�ȭ ( 3 ��ũ�� ��ü�� ���� )
		
		while(true) {
			if(bMainMenu) {
				screen = ticketBox.selectScreen();
				if( screen == null ) {
					System.out.print(" �ȳ��� ������ !");
					scan.close();
					System.exit(0);
				}
				bMainMenu = false;
			}
			screen.showScreenMenu();
			System.out.print(" �޴��� �����ϼ��� >> ");
			try {
				int select = scan.nextInt();
				switch(select) {
				case 1: // ��ũ�� �� ��ȭ ���� ����
					screen.showMovieInfo();
					break;
				case 2:
					screen.showSeatMap();
					break;
				case 3:
					screen.reserveTicket();
					break;
				case 4:
					screen.payment();
					break;
				case 5: // ���θ޴��� ���ư���
					bMainMenu = true;
					break;
				default:
					break;
				}
			} catch(InputMismatchException e) {
				
			}
		}
	}
}

