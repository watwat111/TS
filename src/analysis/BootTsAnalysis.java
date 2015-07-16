package analysis;

import java.io.File;

import javax.swing.JFrame;

import panel.ResultFrame;


public class BootTsAnalysis {

	public static void main(String[] args) {
		String MasterPath = new File(System.getProperty("java.class.path")).getPath();
		int index = MasterPath.indexOf(";");
		MasterPath = new File(MasterPath.substring(0,index)).getParent();	
		JFrame test = new ResultFrame(MasterPath,"ts",new TsAnalysisPanel(MasterPath));
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.pack();
		test.setVisible(true);	
	}

}
