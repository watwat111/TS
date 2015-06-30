/*
 * 作成日: 2015/06/30
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jsp_result;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultAllYear implements Serializable{

	/**
	 * <code>serialVersionUID</code> のコメント
	 */
	private static final long serialVersionUID = 1L;
	private List<ResultOneYear> result;
	private int tabuLength;
	private String name;
	private int foundYear;
	private long time;
	private int bestMakeSpan;
	
	public ResultAllYear(int tabuLngth,String name){
		result = new ArrayList<ResultOneYear>();
		this.tabuLength = tabuLngth;
		this.name = name;
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
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	public int getFoundYear() {
		return foundYear;
	}

	public void setFoundYear(int foundYear) {
		this.foundYear = foundYear;
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
	
	

}
