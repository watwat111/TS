/*
 * 作成日: 2015/06/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp;

public class Data {
	private int machineNumber;
	private int jobLength;
	private int beforeMachineNumber;
	private int jobNumber;
	private int machineCount;
	private int jobCount;
	private int minMakeSpan;
	private int beforeEndTime;
	private int EndTime;
	
	public int getBeforeMachineNumber() {
		return beforeMachineNumber;
	}
	public void setBeforeMachineNumber(int beforeMachineNumber) {
		this.beforeMachineNumber = beforeMachineNumber;
	}
	public int getJobLength() {
		return jobLength;
	}
	public void setJobLength(int jobLength) {
		this.jobLength = jobLength;
	}
	public int getMachineNumber() {
		return machineNumber;
	}
	public void setMachineNumber(int machineNumber) {
		this.machineNumber = machineNumber;
	}
	public int getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}
	public int getMachineCount() {
		return machineCount;
	}
	public void setMachineCount(int machineCount) {
		this.machineCount = machineCount;
	}
	public int getJobCount() {
		return jobCount;
	}
	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}
	public int getMinMakeSpan() {
		return minMakeSpan;
	}
	public void setMinMakeSpan(int minMakeSpan) {
		this.minMakeSpan = minMakeSpan;
	}
	public int getBeforeEndTime() {
		return beforeEndTime;
	}
	public void setBeforeEndTime(int beforeEndTime) {
		this.beforeEndTime = beforeEndTime;
	}
	public int getEndTime() {
		return EndTime;
	}
	public void setEndTime(int endTime) {
		EndTime = endTime;
	}
	
	public void testJobLength(){
		System.out.println("InData:jobLength" + this.jobLength);
	}

}
