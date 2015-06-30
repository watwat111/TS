/*
 * 作成日: 2015/06/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp;

public class Machines {
	private Machine[] machines;
	private int[] setMachineIndex;
	private int lastSetIndex;
	private Data data;
	private int[][][] table;
	private int[][] table1;
	private int tabuLength = 25;

	public Machines(Data data) {
		this.data = data;
		machines = new Machine[data.getMachineCount()];
		setMachineIndex = new int[data.getMachineCount() * data.getJobCount()];
		lastSetIndex = 0;
		for (int i = 0; i < machines.length; i++) {
			machines[i] = new Machine(data);
		}
		table = new int[data.getMachineCount()][data.getJobCount()][data
				.getJobCount()];
		table1 = new int[data.getMachineCount()][data.getJobCount()];
				

	}
	
	public Machines(Data data,int tabuLength){
		this(data);
		this.tabuLength = tabuLength;
	}

	public int setMachineFromJobNumber() {
		Machine selecetMachine = machines[data.getMachineNumber()];
		setMachineIndex[lastSetIndex++] = data.getMachineNumber();
		return selecetMachine.setJobInMachine();

	}

	public int getEndJobInAllMachine() {
		int last = 0;
		for (Machine m : machines) {
			if (last < m.getJobEnd()) {
				last = m.getJobEnd();
			}
		}
		return last+1;
	}

	public void testShowMachinesLength() {
		for (int i = 0; i < machines.length; i++) {
			System.out.println(i + "番目の機械");
			machines[i].testShowJobInMachine();
		}
		System.out.println("最長の仕事" + getEndJobInAllMachine());

		for (int i = 0; i < setMachineIndex.length; i++) {
			System.out.print(" MNo：" + setMachineIndex[i]);
		}
		System.out.println();

	}

	public boolean isTabu(int machineNumber, int indexX, int indexY, int year) {
		return table[machineNumber][indexX][indexY] > year;
	}
	
	public boolean isTabu(int machineNumber, int insertPoint,  int year) {
		return table1[machineNumber][insertPoint] > year;
	}

	public void changeJobXandYOnMachine(int machineNumber, int indexX,
			int indexY) {
		machines[machineNumber].changeJobXAndB(indexX, indexY);
		/*table[machineNumber][indexX][indexY] = year + tabuLength;
		table[machineNumber][indexY][indexX] = year + tabuLength;*/
	}
	
	public boolean insertJobXByZOnMachine(int machinNumber,int indexX,int insertZ){
		return machines[machinNumber].insertJobXYByZ(indexX, insertZ);
	}

	public void AnsReconstruct(Ans ans) {
		int[] tmp = ans.getAns();
		for (int i = 0; i < setMachineIndex.length; i++) {
			tmp[i] = machines[setMachineIndex[i]].getJobNumber();
		}
		ans.setAns(tmp);
	}
	
	public int[] getReconstruct(int[] ans){
		for (int i = 0; i < setMachineIndex.length; i++) {
			ans[i] = machines[setMachineIndex[i]].getJobNumber();
			//System.out.print(" " + ans[i]);
		}
		//System.out.println();
		return ans;
	}

	public void reset() {
		lastSetIndex = 0;
		for (Machine m : machines) {
			m.reset();
		}
	}
	public void clear() {
		lastSetIndex = 0;
		for (Machine m : machines) {
			m.clear();
		}
	}

	public void changeJobXandYOnMachineWithTabu(int bestMachineNumber,
			int bestJobIndexX, int bestJobIndexY, int year) {
		changeJobXandYOnMachine(bestMachineNumber, bestJobIndexX, bestJobIndexY);
		
		table[bestMachineNumber][bestJobIndexX][bestJobIndexY] = year + tabuLength;
		table[bestMachineNumber][bestJobIndexY][bestJobIndexX] = year + tabuLength;
		
	}
	
	public void insertJobOnMachineWithTabu(int bestMachineNumber,
			int bestJobIndexX, int bestinsert, int year) {
		insertJobXByZOnMachine(bestMachineNumber, bestJobIndexX, bestinsert);
		
		table1[bestMachineNumber][bestJobIndexX-bestinsert]= year + tabuLength;
		
		
	}

}
