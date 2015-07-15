package analysis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.swing.JButton;

import jsp_result.ga_result.ResultAllGeneration;
import panel.MasterPanel;
import createFile.CreateFile;

public class GAAnalysisPanel extends  MasterPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	private ResultAllGeneration[] allResults;
	private JButton outText;
	private String saveFilePath;
	private String masterPath;
	private String subPath = "\\result";
	private static final int OUT_TIME = 0;

	public GAAnalysisPanel(String masterPath) {
		this.masterPath = masterPath;
		// TODO Auto-generated constructor stub
		outText = new JButton("	書き出し");

		add(outText);
		outText.addActionListener(this);
		outText.setEnabled(false);
	}

	private void setAllResults() {
		allResults = new ResultAllGeneration[results.length];
		for (int i = 0; i < results.length; i++) {
			allResults[i] = (ResultAllGeneration) results[i];
		}
	}

	@Override
	public void endSetFiles() {
		outText.setEnabled(true);
		setAllResults();

	}

	@Override
	public void actionPerformed(ActionEvent button) {
		// TODO Auto-generated method stub
		if (button.getSource() == outText) {
			saveFilePath = CreateFile.createFileByData(masterPath, subPath);
			outTxt(OUT_TIME);
		}

	}

	public void outTxt(int index) {
		try {
			String type = "";
			switch (index) {
			case OUT_TIME:
				type = "time";
				break;

			default:
				break;
			}
			Calendar now = Calendar.getInstance();
			File file = new File(saveFilePath + "\\" + allResults[0].getFileName() + "_" + type + "_"
					+ now.get(Calendar.DAY_OF_MONTH) + "_" + now.get(Calendar.HOUR_OF_DAY) + "_"
					+ now.get(Calendar.MINUTE) + "_" + now.get(Calendar.MILLISECOND) + ".txt");

			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			switch (index) {
			case OUT_TIME:
				for (int i = 0; i < allResults.length; i++) {
					pw.print(allResults[i].getTime() + ",");
				}
				break;

			default:
				break;
			}

			pw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
