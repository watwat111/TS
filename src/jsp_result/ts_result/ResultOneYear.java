/*
 * �쐬��: 2015/06/30
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
package jsp_result.ts_result;

import java.io.Serializable;

public class ResultOneYear implements Serializable{
	/**
	 * <code>serialVersionUID</code> �̃R�����g
	 */
	private static final long serialVersionUID = 1L;
	private int makeSpan;

	public ResultOneYear(int makeSpan){
		this.makeSpan = makeSpan;
	}
	public int getMakeSpan() {
		return makeSpan;
	}

	public void setMakeSpan(int makeSpan) {
		this.makeSpan = makeSpan;
	}




}
