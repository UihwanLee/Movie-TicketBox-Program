package org.kpu.ticketbox.payment;

public class Receipt {
	String client; // 사용자 이름 + 결제 정보
	String productName; // 영화 상품 이름
	String payMethod; // 결제 수단
	double subTotalAmount; // 커미션 제외한 금액
	double totalAmount; // 커미션 포함한 금액
	
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
