package org.kpu.ticketbox.payment;

public class CardPay implements Pay  {
	public static final double CARD_COMMISION = 0.15;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		// TODO Auto-generated method stub
		String client = name + "#" + number;
		double totalAmount = amount + amount*CARD_COMMISION;
		Receipt receipt = new Receipt(client, product, "CardPay", amount, totalAmount);
		return receipt;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return "Ä«µå";
	}

}
