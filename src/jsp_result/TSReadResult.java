/*
 * �쐬��: 2015/06/30
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
package jsp_result;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TSReadResult {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TSReadResult ts = new TSReadResult();

	}
	
	public TSReadResult(){
		
	}
	
	private ResultAllYear readFile(File dataFile){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(dataFile));
		} catch (FileNotFoundException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		try {
			return  (ResultAllYear)ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		try {
			ois.close();
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		return null;
	}

}
