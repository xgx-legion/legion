package com.xgx.legion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {
	public static void main(String[] args) {
		String sourcePath = "D:/需求/s_ms/20180126/SQL/2001111844.txt";
		String targetPath = "D:/需求/s_ms/20180126/SQL/2001111844.sql";
		
		
		try {
			InputStreamReader inReader = new InputStreamReader(new FileInputStream(sourcePath), "UTF-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			OutputStreamWriter outWriter = new OutputStreamWriter(new FileOutputStream(targetPath), "UTF-8");
			BufferedWriter bufWrite = new BufferedWriter(outWriter);
			String line = "";
			StringBuffer outLine = new StringBuffer();
			int count = 0;
			while((line = bufReader.readLine()) != null){
				count ++;
				outLine = new StringBuffer(); 
				String[] lines = line.split(",");
				outLine.append("INSERT INTO T_ACCOUNT_LINE_V1 (ACCOUNT_ID,ACCOUNT_TYPE_ID,ACCOUNT_SEQID,ACCOUNT_TRADETYPE_ID,ACCOUNT_BELONGTIME,ACCOUNT_BILLNUMBER,ACCOUNT_AMOUNT_CHANGED,ACCOUNT_FROZENAMOUNT_CHANGED,AVAILABLEAMOUNT_CHANGED,ACCOUNT_AMOUNT, ACCOUNT_FROZENAMOUNT,ACCOUNT_AVAILBLEAMOUNT) VALUES(")
				.append("\'").append(lines[0]).append("\'").append(",")
				.append("\'").append(lines[1]).append("\'").append(",")
				.append("\'").append(lines[2]).append("\'").append(",")
				.append("\'").append(lines[3]).append("\'").append(",")
				.append("TO_DATE(\'").append(lines[4]).append("\',\'yyyy-MM-dd HH24:mi:ss\')").append(",")
				.append("\'").append(lines[5]).append("\'").append(",")
				.append(lines[6]).append(",")
				.append(lines[7]).append(",")
				.append(lines[8]).append(",")
				.append(lines[9]).append(",")
				.append(lines[10]).append(",")
				.append(lines[11]).append(");");
				// 写入到目标文件
				bufWrite.write(outLine.toString());
				bufWrite.newLine();
				
				System.out.println(count);
				continue;
			}
			bufWrite.write("commit;");
			bufReader.close();
			inReader.close();
			bufWrite.close();
			outWriter.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
