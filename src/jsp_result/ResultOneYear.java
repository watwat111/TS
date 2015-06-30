/*
 * 作成日: 2015/06/30
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp_result;

import java.io.Serializable;

public class ResultOneYear implements Serializable{
	/**
	 * <code>serialVersionUID</code> のコメント
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
