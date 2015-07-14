/*
 * 作成日: 2015/06/02
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package TS;

import createFile.CreateFile;
import jsp.JspMain;
import jsp_result.ts_result.ResultAllYear;

public class TSMain extends JspMain{
	
	private int year;
	private int tabuLength = 250;
	private int maxYear = 10;
	private ResultAllYear result;

	public TSMain() {
		super("job3.txt",10);
		
		//System.out.println(MasterPath);
		
		for(nowCycleNumber = 0;nowCycleNumber  < cycleNumber; nowCycleNumber ++){
			useTime = new UseTime();
			init();
			useTime.setStartTime(System.nanoTime());
			ts();
			useTime.setEndTime(System.nanoTime());
			result.setTime(useTime.getTime());

			result.setBestMakeSpan(foundBestMakeSpan);
			outResult(result,nowCycleNumber+".ts");
		}
		
		//outTxt();
		// jobs.testJobSLength();
		// load.test();
	}

	public void init() {
		
		saveFilePath = CreateFile.createFileByData(masterPath, "\\TS\\" + name + "\\" + tabuLength);
	
		foundBestMakeSpan = Integer.MAX_VALUE;
		year = 0;
		

		result = new ResultAllYear(tabuLength, name);

		// data.testJobLength();

	}

	


	public void ts() {
		for (year = 0; year < maxYear; year++) {

			evaluateBySwap();
			if (foundBestMakeSpan == data.getMinMakeSpan()) {
				result.setFoundYear(year);
				break;
			}
		}

	}

	private void evaluateBySwap() {
		int[] tmp = ans.getAns();
		setGnt(tmp);
		int[] tmp2 = tmp.clone();
		int bestMakeSpan = Integer.MAX_VALUE;
		machines.reset();
		jobs.init();
		int bestMachineNumber = 0;
		int bestJobIndexX = 0;
		int bestJobIndexY = 0;
		// machines.testShowMachinesLength();
		for (int machineIndex = 0; machineIndex < data.getMachineCount(); machineIndex++) {
			for (int jobIndexX = 0; jobIndexX < data.getJobCount() - 1; jobIndexX++) {
				for (int jobIndexY = jobIndexX + 1; jobIndexY < data.getJobCount(); jobIndexY++) {
					if (!machines.isTabu(machineIndex, jobIndexX, jobIndexY, year)) {
						setGnt(tmp);
						// ここ
						machines.changeJobXandYOnMachine(machineIndex, jobIndexX, jobIndexY);
						tmp2 = machines.getReconstruct(tmp2);
						machines.changeJobXandYOnMachine(machineIndex, jobIndexX, jobIndexY);

						// System.out.println(machineIndex + " :" + jobIndexX +
						// ":" + jobIndexY);
						int tmpMakeSpan = setGnt(tmp2);
						if (tmpMakeSpan < bestMakeSpan) {
							// System.out.println(tmpMakeSpan);
							bestMakeSpan = tmpMakeSpan;
							bestMachineNumber = machineIndex;
							bestJobIndexX = jobIndexX;
							bestJobIndexY = jobIndexY;

						}
						// machines.testShowMachinesLength();

					}
				}
			}
		}
		System.out.println("year:" + year + " BestMakeSpan:" + bestMakeSpan + "foundBestMakeSpan" + foundBestMakeSpan);
		if (bestMakeSpan < foundBestMakeSpan) {
			foundBestMakeSpan = bestMakeSpan;
		}
		result.setOneYear(bestMakeSpan);
		machines.changeJobXandYOnMachineWithTabu(bestMachineNumber, bestJobIndexX, bestJobIndexY, year);
		machines.AnsReconstruct(ans);
		// istabu change~
		// machines.AnsReconstruct(ans);

	}

	private void evaluateByInsert() {
		int[] tmp = ans.getAns();
		setGnt(tmp);
		int[] tmp2 = tmp.clone();
		int bestMakeSpan = Integer.MAX_VALUE;
		machines.reset();
		jobs.init();
		int bestMachineNumber = 0;
		int bestJobIndexX = 0;
		int bestJobIndexY = 0;
		machines.testShowMachinesLength();
		for (int machineIndex = 0; machineIndex < data.getMachineCount(); machineIndex++) {
			for (int jobIndexX = 0; jobIndexX < data.getJobCount(); jobIndexX++) {
				for (int insert = 1; insert < jobIndexX; insert++) {
					if (!machines.isTabu(machineIndex, jobIndexX - insert, year)) {
						setGnt(tmp);

						machines.insertJobXByZOnMachine(machineIndex, jobIndexX, insert);
						tmp2 = machines.getReconstruct(tmp2);

						machines.insertJobXByZOnMachine(machineIndex, jobIndexX - insert, -insert);

						// System.out.println(machineIndex + " :" + jobIndexX +
						// ":" + jobIndexY);
						int tmpMakeSpan = setGnt(tmp2);
						if (tmpMakeSpan < bestMakeSpan) {
							// System.out.println(tmpMakeSpan);
							bestMakeSpan = tmpMakeSpan;
							bestMachineNumber = machineIndex;
							bestJobIndexX = jobIndexX;
							bestJobIndexY = insert;

						}
						// machines.testShowMachinesLength();

					}
				}
			}
		}
		System.out.println(bestJobIndexX + ";" + bestJobIndexY + ":" + bestMachineNumber);
		machines.insertJobOnMachineWithTabu(bestMachineNumber, bestJobIndexX, bestJobIndexY, year);
		machines.AnsReconstruct(ans);
		

	}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TSMain main = new TSMain();

	}

	

}
