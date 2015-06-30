/*
 * 作成日: 2015/06/30
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp_result;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TSReadResult {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TSReadResult ts = new TSReadResult();

	}
	
	public TSReadResult(){
		
	}
	
	private ResultAllYear readFile(File dataFile){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(dataFile));
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			return  (ResultAllYear)ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		try {
			ois.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

}
