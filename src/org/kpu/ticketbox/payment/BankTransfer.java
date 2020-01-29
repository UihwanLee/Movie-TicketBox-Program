package org.kpu.ticketbox.payment;

public class BankTransfer implements Pay {
	public static final double BANK_TRANSFER_COMMISION = 0.1;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		// TODO Auto-generated method stub
		String client = name + "#" + number;
		double totalAmount = amount + amount*BANK_TRANSFER_COMMISION;
		Receipt receipt = new Receipt(client, product, "BankTransfer", amount, totalAmount);
		return receipt;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return "АєЗа";
	}

}
