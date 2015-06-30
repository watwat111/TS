/*
 * 作成日: 2015/06/02
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp;

public class DataInMachine {
	private int startTime;
	private int endTime;
	private int jobNumber;
	
	public DataInMachine(int startTime,int endTime,int jobNumnber){
		this.startTime = startTime;
		this.endTime = endTime;
		this.jobNumber = jobNumnber;
	}
	
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString(){
		return "仕事No:" + jobNumber + " 開始:" + startTime +" 終了:" + endTime + " ";
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

}
