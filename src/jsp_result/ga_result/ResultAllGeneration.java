package jsp_result.ga_result;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import jsp_result.JspResult;

public class ResultAllGeneration extends JspResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxGeneration;
	private double pm;
	private double pc;
	private int foundGeneration;
	private List<ResultOneGeneration> list;
	
	
	public ResultAllGeneration(String name,int maxGeneration,double pm,double pc) {
		super(name);
		// TODO Auto-generated constructor stub
		list = new ArrayList<ResultOneGeneration>();
		this.maxGeneration = maxGeneration;
		this.pm = pm;
		this.pc = pc;
	}
	
	public void setOneGeneration(int makeSpan){
		list.add(new ResultOneGeneration(makeSpan));
	}
	
	public double getPc() {
		return pc;
	}
	public void setPc(double pc) {
		this.pc = pc;
	}
	public double getPm() {
		return pm;
	}
	public void setPm(double pm) {
		this.pm = pm;
	}
	public int getMaxGeneration() {
		return maxGeneration;
	}
	public void setMaxGeneration(int maxGeneration) {
		this.maxGeneration = maxGeneration;
	}
	public int getFoundGeneration() {
		return foundGeneration;
	}
	public void setFoundGeneration(int foundGeneration) {
		this.foundGeneration = foundGeneration;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return name + "_"+ pm+"_"+pc+"_"+maxGeneration;
	}
	

}
