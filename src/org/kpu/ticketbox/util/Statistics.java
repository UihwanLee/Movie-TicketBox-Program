package org.kpu.ticketbox.util;
import java.util.*;
import org.kpu.ticketbox.payment.Receipt;

public class Statistics {
	// 스크린 별 결제 금액 총액 계산 
	public static double sum( HashMap<Integer, Receipt> map) {
		double receipt_sum = 0.0;
		Set<Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator(); 
		
		while(it.hasNext()) {
			int number = it.next();
			Receipt receipt = map.get(number);
			receipt_sum += receipt.gettotalAmount();
		}
		return receipt_sum;
	}
}
