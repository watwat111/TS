/*
 * 作成日: 2015/06/01
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp;

public class Jobs {
	private DataInJobs[][] jobDatas;
	private Data data;
	public static final int MACHINE_COUNT = 0;
	public static final int JOB_COUNT = 1;
	private Job[] jobs;

	public Jobs(Data data) {
		this.data = data;
		jobDatas = new DataInJobs[data.getMachineCount()][data.getJobCount()];
		for (int i = 0; i < jobDatas.length; i++) {
			for (int j = 0; j < jobDatas[i].length; j++) {
				jobDatas[i][j] = new DataInJobs();
			}
		}
		// System.out.println(data.getJobCount());
		jobs = new Job[data.getJobCount()];
		for (int i = 0; i < jobs.length; i++) {
			jobs[i] = new Job(data);
		}
		
	}

	public void setDatafromJobNumber(int jobNumber) {
		Job selectJob = jobs[jobNumber];
		selectJob.getJobInfo();
		jobDatas[data.getMachineNumber()][data.getJobNumber()]
				.setBeforeMachineNumber(data.getBeforeMachineNumber());
		int beforeMachineNumner = data.getBeforeMachineNumber();
		if(beforeMachineNumner >= 0){
			data.setBeforeEndTime(jobDatas[data.getBeforeMachineNumber()][data.getJobNumber()].getEndTime());
		}
		else{
			data.setBeforeEndTime(0);
		}
		
	}

	public void setJobLength(int jobNumber, int machineNumber, int jobLength) {
		Job selectJob = jobs[jobNumber];
		selectJob.setJobLength(machineNumber, jobLength);
	}
	
	public void setEndTime(int endTime){
		jobDatas[data.getMachineNumber()][data.getJobNumber()]
				.setEndTime(endTime+1);
		//data.setBeforeEndTime(endTime);
	}

	public void testJobSLength(){
		for(int i = 0; i < jobs.length; i++){
			System.out.println(i + "番目の仕事：" );
			jobs[i].testJobLength();
			System.out.println("");
		}
		
	}
	
	public void init(){
		for(Job j:jobs){
			j.init();
		}
	}

}
