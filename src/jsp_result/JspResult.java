package jsp_result;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import result.IResult;

public abstract class JspResult implements IResult,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String name;

	protected long time;
	protected int bestMakeSpan;
	
	
	public JspResult(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getBestMakeSpan() {
		return bestMakeSpan;
	}

	public void setBestMakeSpan(int bestMakeSpan) {
		this.bestMakeSpan = bestMakeSpan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract String getFileName();
}
