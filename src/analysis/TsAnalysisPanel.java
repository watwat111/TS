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

import jsp_example.FileName;
import jsp_result.ts_result.ResultAllYear;
import panel.MasterPanel;
import createFile.CreateFile;

public class TsAnalysisPanel extends MasterPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ResultAllYear[] allResults;
	private JButton outText;
	private JButton allOutCsv;
	private String saveFilePath;
	private String masterPath;
	private String subPath = "\\result\\ts";

	private static final int OUT_TIME = 0;
	private static final int OUT_MAKESPAN = 1;
	private static final int OUT_MOVE = 2;

	public TsAnalysisPanel(String masterPath) {
		this.masterPath = masterPath;
		// TODO Auto-generated constructor stub
		outText = new JButton("�����o��");
		allOutCsv = new JButton("allOutCsv");
		add(allOutCsv);
		add(outText);
		outText.addActionListener(this);
		allOutCsv.addActionListener(this);
		outText.setEnabled(false);
	}



	private void setAllResults() {
		allResults = new ResultAllYear[results.length];
		for (int i = 0; i < results.length; i++) {
			allResults[i] = (ResultAllYear) results[i];
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
			outCsv(OUT_TIME);
			outCsv(OUT_MOVE);
			outCsv(OUT_MAKESPAN);
		}
		else if(button.getSource() == allOutCsv){
			saveFilePath = CreateFile.createFileByData(masterPath, subPath);
			String loadPath =masterPath +"\\"+   CreateFile.createNowDataPath() + "\\" + "TS" + "\\";
			//System.out.println(CreateFile.createFileByData(masterPath, subPath));
			for(FileName f: FileName.values()){
				for(int tabuLength = 0; tabuLength < 21; tabuLength ++){


					File file = new File(loadPath +  f.toString()+".txt\\" + (tabuLength * 50) + "\\");
					if(file.exists()){
						File[] result = file.listFiles();
						setFiles(result);
						setAllResults();
						outCsv(OUT_TIME);
						outCsv(OUT_MOVE);
						outCsv(OUT_MAKESPAN);
						//System.out.println(loadPath +  f.toString()+".txt\\" + (tabuLength * 50) + "\\");
					}
				}
			}
		//	System.out.println(loadPath );

		}

	}

	public void outCsv(int index) {
		try {
			String type = "";
			switch (index) {
			case OUT_TIME:
				type = "time";
				break;
			case OUT_MAKESPAN:
				type = "makespan";
				break;
			case OUT_MOVE:
				type = "move";
				break;

			default:
				break;
			}
			Calendar now = Calendar.getInstance();
			File file = new File(saveFilePath + "\\" + allResults[0].getFileName() + "_" + type + "_"
					+ now.get(Calendar.DAY_OF_MONTH) + "_" + now.get(Calendar.HOUR_OF_DAY) + "_"
					+ now.get(Calendar.MINUTE) + "_" + now.get(Calendar.MILLISECOND) + ".csv");

			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			switch (index) {
			case OUT_TIME:
				for (int i = 0; i < allResults.length; i++) {
					pw.print(allResults[i].getTime() + ",");
				}
				break;
			case OUT_MAKESPAN:
				for (int i = 0; i < allResults.length; i++) {
					pw.print(allResults[i].getBestMakeSpan() + ",");
				}
				break;
			case OUT_MOVE :
				for (int i = 0; i < allResults.length; i++) {
					pw.print(allResults[i].getFoundYear() + ",");
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
