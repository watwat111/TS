/*
 * �쐬��: 2015/06/30
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
package jsp_result.ts_result;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jsp_result.JspResult;

public class ResultAllYear extends JspResult implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ResultOneYear> result;
	private int tabuLength;
	private int foundYear;
	
	
	public ResultAllYear(int tabuLngth,String name){
		super(name);
		result = new ArrayList<ResultOneYear>();
		this.tabuLength = tabuLngth;
		
	}

	public List<ResultOneYear> getResult() {
		return result;
	}

	public void setResult(List<ResultOneYear> result) {
		this.result = result;
	}
	
	public void setOneYear(int makeSpan){
		result.add(new ResultOneYear(makeSpan));
	}

	public int getTabuLength() {
		return tabuLength;
	}

	public void setTabuLength(int tabuLength) {
		this.tabuLength = tabuLength;
	}
	
	

	public int getFoundYear() {
		return foundYear;
	}

	public void setFoundYear(int foundYear) {
		this.foundYear = foundYear;
	}


	@Override
	public String getFileName(){
		return name+"_"+tabuLength;
	}
	
	

}
