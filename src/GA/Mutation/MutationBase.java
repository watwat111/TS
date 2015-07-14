package GA.Mutation;

import java.util.List;

import jsp.Ans;

public abstract class MutationBase implements IMutation{

	public abstract List<Ans> mutation(double pm, List<Ans> list);
}
