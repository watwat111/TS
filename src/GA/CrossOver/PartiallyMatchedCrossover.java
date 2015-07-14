package GA.CrossOver;

import java.util.List;

import create_random_index.CreateRandomIndex;
import jsp.Ans;

public class PartiallyMatchedCrossover extends BasicCrossOver {

	public PartiallyMatchedCrossover(Ans ans) {
		// TODO Auto-generated constructor stub
		super(ans);
	}

	@Override
	public List<Ans> crossOver(List<Ans> anss, double pc) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < anss.size(); i++){
			if(Math.random() < pc){
				int targetIndex = CreateRandomIndex.getRandomIndex(anss.size());
				int index1 =  CreateRandomIndex.getRandomIndex(geneticLength);
				int index2 = getIndex2(index1, geneticLength);
				copyAns(anss.get(targetIndex).getAns());
				anss.get(i).setAns(doPartiallyMatchedCrossover(anss.get(targetIndex).getAns(),anss.get(i).getAns() , index1, index2));
				anss.get(i).setAns(doPartiallyMatchedCrossover(anss.get(i).getAns(),tmp, index1, index2));
				
			}
		}
		
		return anss;
	}

	private int[] doPartiallyMatchedCrossover(int[] ans, int[] target, int index1, int index2) {
		for (int changeIndex1 = index1; changeIndex1 <= index2; changeIndex1++) {
			int targetNumber = target[changeIndex1];
			int changeIndex2 = getChangeIndex2(ans, index1, index2, targetNumber);
			ans = changeAns(ans, changeIndex1, changeIndex2, targetNumber);
		}
		return ans;
	}

	private int[] changeAns(int[] ans, int changeIndex1, int changeIndex2, int targetNumber) {
		int tmp = ans[changeIndex1];
		ans[changeIndex1] = targetNumber;
		ans[changeIndex2] = tmp;
		return ans;
	}

	private int getChangeIndex2(int[] ans, int index1, int index2, int targetNumber) {
		for (int i = 0; i < index1; i++) {
			if (ans[i] == targetNumber) {
				return i;
			}
		}
		for (int i = index2 + 1; i < ans.length; i++) {
			if (ans[i] == targetNumber) {
				return i;
			}
		}
		for (int i = index1; i <= index2; i++) {
			if (ans[i] == targetNumber) {
				return i;
			}
		}

		return -1;

	}

	private int getIndex2(int index1, int geneticLength) {
		return index1 + CreateRandomIndex.getRandomIndex(geneticLength - index1);
	}

}
