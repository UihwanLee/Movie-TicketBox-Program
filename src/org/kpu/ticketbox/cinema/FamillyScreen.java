package org.kpu.ticketbox.cinema;

public class FamillyScreen extends Screen {
	public FamillyScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	@Override
	public void showMovieInfo() {
		// TODO Auto-generated method stub
		System.out.println("-------------------");
		System.out.println(" " + this.strMovieName + " �Ұ�");
		System.out.println("-------------------");
		System.out.println("��ȭ�� : ���� ��ȭ 1��");
		System.out.println("�ٰŸ� : " + this.strMovieStory);
		System.out.println("�� �� : " + this.nTicketPrice);
	}
	
}

