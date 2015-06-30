/*
 * 作成日: 2015/06/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jspload;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jsp.Data;
import jsp.Job;
import jsp.Jobs;

public class LoadJSP {
	private Data data;
	private File file;
	private static final int MACHINE = 2;
	private static final int JOB = 1;
	private static final int MAKESPAN = 3;
	
	public LoadJSP(File file,Data data){
		this.file = file;
		this.data = data;
		
	}
	
public void setJob(Jobs jobs){
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			int lineNumber = 0;
			
			while(((line = br.readLine()) != null)){
				
				String[] strArray = line.split(" ");
				if(lineNumber == 0){
				}
				else {
					int[][] tmp  = new int[strArray.length][];;
					for(int i = 0; i < strArray.length / 2; i++){
						
						//tmp[i] = new int[]{Integer.parseInt(strArray[2 * i + 1]),Integer.parseInt(strArray[2 * i + 2])};
						jobs.setJobLength(lineNumber - 1, Integer.parseInt(strArray[2 * i + 1]), Integer.parseInt(strArray[2 * i + 2]));
						//System.out.println((strArray[2 * i + 1]) + "@" + (strArray[2 * i + 2]));
					}
					//jobs[lineNumber - 1] = new Job(tmp,machine);
					
				}
				lineNumber++;
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("not file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	
	public void loadBasicData(){
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			String[] array = line.split(" ");
			data.setMachineCount(Integer.parseInt(array[MACHINE]));
			data.setJobCount(Integer.parseInt(array[JOB]));
			data.setMinMakeSpan(Integer.parseInt(array[MAKESPAN]));
			
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("not file");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	
	
	public void test(){
		System.out.println(data.getMinMakeSpan());
		System.out.println(data.getJobCount());
		System.out.println(data.getMachineCount());
	}

}
