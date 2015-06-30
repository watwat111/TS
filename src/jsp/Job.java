/*
 * 作成日: 2015/06/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp;

public class Job {
	public static final int JOB_MACHINE = 0;
	public static final int JOB_LENGTH = 1;
	private int[][] job;
	private int jobIndex = 0;
	private Data data;

	public Job(Data data){
		this.data = data;
		job = new int[data.getMachineCount()][2];
	}
	
	public void setJobLength(int machineNumber,int jobLength){
		try{
			job[jobIndex][JOB_MACHINE] = machineNumber;
			job[jobIndex++][JOB_LENGTH] = jobLength;
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("仕事の機械数を超越しています");
		}
		if(jobIndex >= job.length){
			jobIndex = 0;
		}
	}
	
	public void getJobInfo(){
		data.setJobLength(job[jobIndex][JOB_LENGTH]);
		data.setMachineNumber(job[jobIndex][JOB_MACHINE]);
		if(jobIndex-1 >=0){
			data.setBeforeMachineNumber(job[jobIndex-1][JOB_MACHINE]);
		}
		else{
			data.setBeforeMachineNumber(-1);
		}
		jobIndex++;	
	}
	
	public void testJobLength(){
		for(int i = 0; i < job.length; i++){
			System.out.print("長さ:" + job[i][JOB_LENGTH] + "機械番号" + job[i][JOB_MACHINE] + " ");
			
		}
	}
	
	public void init(){
		jobIndex = 0;
	}
	
	
	
}
