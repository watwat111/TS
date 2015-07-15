/*
 * �쐬��: 2015/06/02
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */
package TS;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import jsp.JspMain;
import jsp_result.ts_result.ResultAllYear;
import createFile.CreateFile;

public class TSMain extends JspMain{

	private int year;
	private   int tabuLength;
	private int maxYear = 5000;
	private ResultAllYear result;
	private boolean isEnd;
	private Timer timer;



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int tabuLength = 250;
		TSMain main = new TSMain(tabuLength);

	}

	public TSMain(int tabu) {
		super("job3.txt",5,tabu);
		tabuLength = tabu;
		//System.out.println(MasterPath);

		for(nowCycleNumber = 0;nowCycleNumber  < cycleNumber; nowCycleNumber ++){
			try {
				timer_delay();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			useTime = new UseTime();
			super.init(tabuLength);
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
		isEnd  =false;
		result = new ResultAllYear(tabuLength, name);
		result.setFoundYear(Integer.MAX_VALUE);

		// data.testJobLength();

	}




	public void ts() {
		//foundBestMakeSpan = Integer.MAX_VALUE;
		for (year = 0; year < maxYear; year++) {

			evaluateBySwap();
			if (foundBestMakeSpan == data.getMinMakeSpan()) {
				result.setFoundYear(year);
				timer.cancel();
				break;
			}
			if(isEnd){
				timer.cancel();
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
						// ����
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

	public void timer_delay() throws InterruptedException {
		TimerTask task = new SampleTask();
		timer = new Timer("遅延タイマー");

		////System.out.println("main start：" + new Date());
		timer.schedule(task, TimeUnit.SECONDS.toMillis(5)); // 10秒後に実行

		/*TimeUnit.SECONDS.sleep(30); // 30秒間待つ
		timer.cancel();*/
		//System.out.println("main end  ：" + new Date());
	}

	class SampleTask extends TimerTask {



		/** このメソッドがTimerから呼ばれる */
		@Override
		public void run() {
			System.out.println("5s end");
			isEnd  = true;
			//System.out.println("タスク実行：" + new Date());
		}
	}







}
