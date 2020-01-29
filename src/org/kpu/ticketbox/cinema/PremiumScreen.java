package org.kpu.ticketbox.cinema;

import org.kpu.ticketbox.payment.MovieTicket;

public class PremiumScreen extends Screen {
	public PremiumScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	@Override
	public void showMovieInfo() {
		// TODO Auto-generated method stub
		System.out.println("-------------------");
		System.out.println(" " + this.strMovieName + " 소개");
		System.out.println("-------------------");
		System.out.println("영화관 : 프리미엄 영화 3관");
		System.out.println("줄거리 : " + this.strMovieStory);
		System.out.println("가 격 : " + this.nTicketPrice);
	}
} 
