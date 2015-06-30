/*
 * �쐬��: 2015/06/01
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
package jsp;

import java.util.ArrayList;
import java.util.List;

public class Machine {
	private Data data;
	private List<DataInMachine> setJob;
	private int lastIndex;

	public Machine(Data data) {
		this.data = data;
		lastIndex = 0;
		setJob = new ArrayList<DataInMachine>(data.getJobCount());
	}
	
	public int getJobNumber(){
		return setJob.get(lastIndex++).getJobNumber();
	}
	
	public void changeJobXAndB(int indexX,int indexY){
		int tmpNumber = setJob.get(indexX).getJobNumber();
		setJob.get(indexX).setJobNumber(setJob.get(indexY).getJobNumber());
		setJob.get(indexY).setJobNumber(tmpNumber);
		//System.out.println(indexX + "** " + indexY + "@@"+setJob.get(indexX).getJobNumber() + "::" + setJob.get(indexY).getJobNumber());
	}
	
	public boolean insertJobXYByZ(int indexX,int insertLengthZ){
		int newNumber = indexX - insertLengthZ;
		/*if(newNumber <= 0){
			return false;
		}*/
		DataInMachine tmpData = setJob.get(indexX);
	
		setJob.remove(indexX);
		
		setJob.add(newNumber, tmpData);
		
		return true;
	}

	public int setJobInMachine() {
		int startIndex = data.getBeforeEndTime();
		int endIndex = 0;
		for (int i = 0; i < setJob.size(); i++) {
			DataInMachine dataInMachine = setJob.get(i);
			int endTime = dataInMachine.getEndTime();
			if (startIndex > endTime) {
				continue;
			}
			int jobLength = data.getJobLength();
			if (jobLength <= getSpaceLength(i)) {
				endIndex  =endTime
						+ data.getJobLength();
				setJob.add(
						i + 1,
						new DataInMachine(endTime + 1, endIndex ,data.getJobNumber()));
				return endIndex ;
			}
		}
		endIndex = startIndex
				+ data.getJobLength() - 1;
		setJob.add(new DataInMachine(startIndex,endIndex,data.getJobNumber()));
		return endIndex;
	}

	private int getSpaceLength(int index) {
		
		if (index  +2> setJob.size()) {
			return 999999999;
		} else {
			return setJob.get(index + 1).getStartTime()
					- setJob.get(index).getEndTime() - 1;
		}
	}
	
	public void testShowJobInMachine(){
		for(int i = 0; i < setJob.size(); i++){
			System.out.print(i + "�Ԗڂ̎d��:" + setJob.get(i));
		}
		System.out.println("");
	}
	
	public int getJobEnd(){
		return setJob.get(setJob.size()-1).getEndTime();
	}
	
	public void reset(){
		lastIndex = 0;
	}

	public void clear() {
		setJob.clear();
	}

}
