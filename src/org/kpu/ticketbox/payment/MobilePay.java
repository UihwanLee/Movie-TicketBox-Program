package org.kpu.ticketbox.payment;

public class MobilePay implements Pay  {
	public static final double MOBILE_COMMISION = 0.2;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		// TODO Auto-generated method stub
		String client = name + "#" + number;
		double totalAmount = amount + amount*MOBILE_COMMISION;
		Receipt receipt = new Receipt(client, product, "MobilePay", amount, totalAmount);
		return receipt;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return "¸ð¹ÙÀÏ";
	}

}
