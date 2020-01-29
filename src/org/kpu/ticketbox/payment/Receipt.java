package org.kpu.ticketbox.payment;

public class Receipt {
	String client; // ����� �̸� + ���� ����
	String productName; // ��ȭ ��ǰ �̸�
	String payMethod; // ���� ����
	double subTotalAmount; // Ŀ�̼� ������ �ݾ�
	double totalAmount; // Ŀ�̼� ������ �ݾ�
	
	public Receipt(String client, String productName, String payMethod, double subTotalAmount, double totalAmount) {
		this.client = client; this.productName = productName; this.payMethod = payMethod;
		this.subTotalAmount = subTotalAmount; this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "client=" + client + ", productName=" + productName + ", "
				+ "payMethod=" + payMethod + ", subTotalAmount=" + subTotalAmount + ", "
						+ "totalAmount=" + totalAmount;
	}
	
	public double gettotalAmount() { return totalAmount; }
}
