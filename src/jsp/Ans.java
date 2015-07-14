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
	private int[] machineAtinsert;
	private Data data;
	private int makeSpan;
	private int fitness;
	
	
	private int[][] jobInMachine;
/*	public static final int MACHINE = 0;
	public static final int JOB = 1;*/
	
	public Ans(Data data) {
		this.data = data;
		ans = new int[data.getMachineCount() * data.getJobCount()];
		setMachineAtinsert(new int[ans.length]);
		setJobInMachine(new int[data.getMachineCount()][data.getJobCount()]);
		init();
		//testShowAns();
	}
	
	@Override
	public Ans clone()  {
		Ans copy = new Ans(data);
		copy.setAns(getAns());
		copy.setMakeSpan(makeSpan);
		return copy;
		
	};
	
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
		setMakeSpan(Integer.MAX_VALUE);
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

	public int getMakeSpan() {
		return makeSpan;
	}

	public void setMakeSpan(int makeSpan) {
		this.makeSpan = makeSpan;
	}
	
	public void showMakeSpan(){
		System.out.println(makeSpan);
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public int[][] getJobInMachine() {
		return jobInMachine;
	}

	public void setJobInMachine(int[][] jobInMachine) {
		this.jobInMachine = jobInMachine;
	}

	public int[] getMachineAtinsert() {
		return machineAtinsert;
	}

	public void setMachineAtinsert(int[] machineAtinsert) {
		this.machineAtinsert = machineAtinsert;
	}
	
	public int getMachineCount(){
		return data.getMachineCount();
	}
	
	public void setGeneticByJobInMachine(){
		 int[] machine  =new int[data.getMachineCount()];
		 for(int i = 0; i < machineAtinsert.length; i++){
			 int tmp = machineAtinsert[i];
			 int tmp2 = machine[tmp]++;
			 ans[i] = jobInMachine[tmp][tmp2]; 
		 }
		 
	}

}
