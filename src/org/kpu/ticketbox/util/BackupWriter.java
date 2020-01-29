package org.kpu.ticketbox.util;
import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.kpu.ticketbox.payment.Receipt;

public class BackupWriter {
	// c:\\temp\\receipt.txt ���Ͽ� ��ü ��ũ�� ���� ������ ����ϱ�
	public void backupFile(String filename, HashMap<Integer, Receipt> map) {
		File file = new File(filename);
		FileWriter fw = null;
		
		Set<Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator(); 
		
		try {
			fw = new FileWriter(file, true);
			while(it.hasNext()) {
				int number = it.next();
				Receipt receipt = map.get(number);
				String line = receipt.toString();
				fw.write(line);
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
