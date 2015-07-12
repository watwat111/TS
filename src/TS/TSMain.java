/*
 * 作成日: 2015/06/02
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package TS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import createFile.CreateFile;
import jsp.Ans;
import jsp.Data;
import jsp.Jobs;
import jsp.Machines;
import jsp_result.ResultAllYear;
import jspload.LoadJSP;

public class TSMain {
	Jobs jobs;
	Ans ans;
	private int foundBestMakeSpan;
	Machines machines;
	Data data;
	private int cycleNumber = 10;
	private int year;
	private ResultAllYear result;
	private int tabuLength = 250;
	private int maxYear = 10;
	private String name = "job3.txt";
	private String MasterPath;
	private String jspPath;
	private String saveFilePath;
	private int nowCycleNumber;
	//private String SaveName = "C:\\Users\\watwat\\Desktop\\2015\\ゼミ\\2015_07_01\\" + name + "\\" + tabuLength;
	private int foundBestMakeSpanYear = Integer.MAX_VALUE;
	private UseTime useTime;

	public TSMain() {
		MasterPath = new File(System.getProperty("java.class.path")).getPath();
		int index = MasterPath.indexOf(";");
		MasterPath = new File(MasterPath.substring(0,index)).getParent();		
		System.out.println(MasterPath);
		for(nowCycleNumber = 0;nowCycleNumber  < cycleNumber; nowCycleNumber ++){
			useTime = new UseTime();
			init();
			useTime.setStartTime(System.nanoTime());
			ts();
			useTime.setEndTime(System.nanoTime());
			result.setTime(useTime.getTime());

			result.setBestMakeSpan(foundBestMakeSpan);
			outResult();
		}
		
		//outTxt();
		// jobs.testJobSLength();
		// load.test();
	}

	public void init() {
		
		saveFilePath = CreateFile.createFileByData(MasterPath, "\\" + name + "\\" + tabuLength);
		/*Calendar now = Calendar.getInstance();
		saveFilePath = MasterPath + "\\" + now.get(Calendar.YEAR) + "_" + (now.get(Calendar.MONTH) + 1) + "_"
				+ now.get(Calendar.DAY_OF_MONTH) + "\\" + name + "\\" + tabuLength;

		File newdir = new File(saveFilePath);
		if (newdir.exists()) {

		} else {
			newdir.mkdirs();
		}*/

		foundBestMakeSpan = Integer.MAX_VALUE;
		data = new Data();
		year = 0;
		jspPath = MasterPath + "\\src\\jsp_example\\";
		File file = new File(jspPath + name);
		LoadJSP load = new LoadJSP(file, data);
		load.loadBasicData();
		jobs = new Jobs(data);
		load.setJob(jobs);
		ans = new Ans(data);
		machines = new Machines(data, tabuLength);
		result = new ResultAllYear(tabuLength, name);

		// data.testJobLength();

	}

	/*public void outTxt() {
		try {
			Calendar now = Calendar.getInstance();
			File file = new File(
					SaveName + "\\" + "Result" + +now.get(Calendar.DAY_OF_MONTH) + now.get(Calendar.HOUR_OF_DAY)
							+ now.get(Calendar.MINUTE) + now.get(Calendar.MILLISECOND) + ".txt");

			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

			pw.println("タブー期間" + result.getTabuLength());
			pw.println("発見年代" + result.getFoundYear());
			pw.println("発見メイクスパン" + result.getBestMakeSpan());
			pw.println("計測時間" + result.getTime());
			pw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}*/

	private static boolean checkBeforeWritefile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canWrite()) {
				return true;
			}
		}

		return false;
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
		// istabu change~
		// machines.AnsReconstruct(ans);

	}

	private int setGnt(int[] tmp) {
		machines.clear();
		jobs.init();
		machines.reset();
		for (int i = 0; i < tmp.length; i++) {
			// System.out.println(tmp[i]);
			data.setJobNumber(tmp[i]);
			jobs.setDatafromJobNumber(tmp[i]);
			int endTime = machines.setMachineFromJobNumber();
			jobs.setEndTime(endTime);
		}

		return machines.getEndJobInAllMachine();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TSMain main = new TSMain();

	}

	public void outResult() {
		ObjectOutputStream oos = null;
		try {
			Calendar now = Calendar.getInstance();

			oos = new ObjectOutputStream(
					new FileOutputStream(saveFilePath + "\\" + "Result" + +now.get(Calendar.DAY_OF_MONTH)
							+"_"+ now.get(Calendar.HOUR_OF_DAY) + "_"+now.get(Calendar.MINUTE) + "_"+nowCycleNumber + ".ts"));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			oos.writeObject(this.result);
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
