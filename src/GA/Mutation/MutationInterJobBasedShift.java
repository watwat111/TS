package GA.Mutation;

import java.util.List;

import create_random_index.CreateRandomIndex;
import jsp.Ans;

public class MutationInterJobBasedShift extends MutationBase{
	private int shift = 1;

	@Override
	public List<Ans> mutation(double pm, List<Ans> list) {
		// TODO Auto-generated method stub
		int machineNumber = CreateRandomIndex.getRandomIndex(list.get(0).getMachineCount());
		for(int  i = 0; i < list.size(); i++){
			if(Math.random() < pm){
				doInterJobBasedShift(list.get(i), machineNumber);
				//System.out.println(1);
			}
		}
		return list;
	}
	
	private Ans doInterJobBasedShift(Ans ans,int machineNumber){
		int[][] work = ans.getJobInMachine();
		for(int i = 0; i < work.length; i++){
			for(int j = 1; j < work[i].length; j++){
				if(machineNumber == work[i][j]){
					work[i][j] = work[i][j-shift];
					work[i][j-shift] = machineNumber;
					break;
				}
			}
		}
		ans.setJobInMachine(work);
		ans.setGeneticByJobInMachine();
		return ans;
	}

}
