/*
 * 作成日: 2015/06/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ans {

	private int[] ans;
	private Data data;

	public Ans(Data data) {
		this.data = data;
		ans = new int[data.getMachineCount() * data.getJobCount()];
		init();
		//testShowAns();
	}
	
	public int[] getAns(){
		return ans.clone();
	}

	public void init() {
		List<Integer> tmp = new ArrayList<Integer>(ans.length);
		for (int i = 0; i < data.getJobCount(); i++) {
			for (int j = 0; j < data.getMachineCount(); j++) {
				tmp.add(i);
			}
		}
		Collections.shuffle(tmp);
		for(int i = 0; i < ans.length; i++){
			ans[i] = tmp.get(i);
		}
		//testShowAns();
	}
	
	public void setAns(int[] ans){
		this.ans = ans;
	}
	


	public void testShowAns() {
		System.out.println("ANS： ");
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println("");

	}

}
