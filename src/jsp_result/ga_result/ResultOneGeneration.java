package jsp_result.ga_result;

import java.io.Serializable;

public class ResultOneGeneration implements Serializable{
	/**
	 * <code>serialVersionUID</code> ‚ÌƒRƒƒ“ƒg
	 */
	private static final long serialVersionUID = 1L;
	private int makeSpan;

	public ResultOneGeneration(int makeSpan){
		this.makeSpan = makeSpan;
	}
	public int getMakeSpan() {
		return makeSpan;
	}

	public void setMakeSpan(int makeSpan) {
		this.makeSpan = makeSpan;
	}

}
