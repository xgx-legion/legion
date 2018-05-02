package com.xgx.legion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {
	public static void main(String[] args) {
		String sourcePath = "D:/test/test1.txt";
		String targetPath = "D:/test/proxy2.sql";
		
		
		try {
			InputStreamReader inReader = new InputStreamReader(new FileInputStream(sourcePath), "UTF-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			OutputStreamWriter outWriter = new OutputStreamWriter(new FileOutputStream(targetPath), "UTF-8");
			BufferedWriter bufWrite = new BufferedWriter(outWriter);
			String line = "";
			StringBuffer outLine = new StringBuffer();
			int count = 0;
			
			outLine.append("select * from t_proxy where proxy_id in (");
			
			while((line = bufReader.readLine()) != null){
				if(count < 900){
					count++;
					continue;
				}
				if(count == 900 * 2){
					break;
				}
				
				if(line.length() > 8){
					continue;
				}else{
					outLine.append("\'"+line+"\'").append(",");
				}
				
				count ++;
				System.out.println(count);
				continue;
			}
			outLine.append(");");
			
			// 写入到目标文件
			bufWrite.write(outLine.toString());
//			bufWrite.newLine();
//			bufWrite.write("commit;");
			bufReader.close();
			inReader.close();
			bufWrite.close();
			outWriter.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
