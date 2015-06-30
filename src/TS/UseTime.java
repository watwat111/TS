/*
 * 作成日: 2015/06/30
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package TS;

public class UseTime {
	private long startTime;
	private long endTime;
	private long time;
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long  endTime) {
		
		this.endTime = endTime;
		calcTime();
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	private void calcTime(){
		time = endTime - startTime;
	}
	
	

}
