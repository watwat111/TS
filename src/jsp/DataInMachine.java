/*
 * �쐬��: 2015/06/02
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
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
		return "�d��No:" + jobNumber + " �J�n:" + startTime +" �I��:" + endTime + " ";
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

}
