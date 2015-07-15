package GA;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import jsp.Ans;
import jsp.JspMain;
import jsp_result.ga_result.ResultAllGeneration;
import GA.CrossOver.BasicCrossOver;
import GA.CrossOver.PartiallyMatchedCrossover;
import GA.Mutation.MutationBase;
import GA.Mutation.MutationInterJobBasedShift;
import GA.Survive.SurviveBase;
import GA.Survive.SurviveRulette;
import GA.calc_fitness.CalcFitnessAtLinerNormarize;
import GA.calc_fitness.CalcFitnessBase;
import TS.UseTime;
import createFile.CreateFile;

public class GAMain1 extends JspMain {
	private int maxGeneration = 5000;
	private int nowGeneration;
	private ResultAllGeneration result;
	private double pm = 0.25;
	private double pc = 0.7;
	private int population = 200;
	private BasicCrossOver crossOver;
	private List<Ans> anss;
	private int worthMakeSpan;
	private int bestMakeSpan;
	private CalcFitnessBase calcFitness;
	private MutationBase mutation;
	private SurviveBase survive;
	private boolean isEnd;

	public GAMain1() {
		super("job3.txt", 5);
		// TODO Auto-generated constructor stub

		// System.out.println(111);

		// System.out.println(MasterPath);

		for (nowCycleNumber = 0; nowCycleNumber < cycleNumber; nowCycleNumber++) {
			try {
				timer_delay();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			init();
			isEnd = false;
			useTime = new UseTime();
			// init();
			useTime.setStartTime(System.nanoTime());
			GA();
			useTime.setEndTime(System.nanoTime());
			result.setTime(useTime.getTime());
			// System.out.println("Cyc" + cycleNumber );
			result.setBestMakeSpan(foundBestMakeSpan);
			outResult(result, nowCycleNumber + ".ga");
		}
	}

	public void GA() {
		for (nowGeneration = 0; nowGeneration < maxGeneration; nowGeneration++) {
			setMakeSpanAllGenetic();
			setBestAns();
			result.setOneGeneration(bestMakeSpan);
			if (bestMakeSpan == data.getMinMakeSpan()) {
				result.setFoundGeneration(nowGeneration);
				break;
			}
			calcFitness.calcFitness(anss);
			anss = crossOver.crossOver(anss, pc);
			anss = mutation.mutation(pm, anss);
			anss = survive.survive(anss);
			insertBestAns();
			System.out.println(nowGeneration + ":" + bestMakeSpan);
			if(isEnd){
				break;
			}
		}
		// testShowAllMakeSpan();
	}

	private void setBestAns() {
		if (anss.get(anss.size() - 1).getMakeSpan() < bestMakeSpan) {
			ans = anss.get(anss.size() - 1).clone();
			bestMakeSpan = ans.getMakeSpan();
		}
	}

	private void insertBestAns() {
		anss.set(0, ans.clone());
	}

	private void testShowAllMakeSpan() {
		for (Ans ans : anss) {
			// System.out.println(12222);
			ans.showMakeSpan();
		}
	}

	private void setMakeSpanAllGenetic() {
		worthMakeSpan = 0;
		for (Ans ans : anss) {
			int makespan = setGnt(ans);
			if (makespan > worthMakeSpan) {
				worthMakeSpan = makespan;
			}
			if (makespan < bestMakeSpan) {
				bestMakeSpan = makespan;
			}
			ans.setMakeSpan(makespan);
		}
	}

	public void init() {
		saveFilePath = CreateFile.createFileByData(masterPath, "\\GA\\" + name
				+ "\\" + getSaveFileName());
		anss = new ArrayList<Ans>(population);
		for (int i = 0; i < population; i++) {
			anss.add(new Ans(data));
			// System.out.println(111);
		}
		bestMakeSpan = Integer.MAX_VALUE;

		// System.out.println(anss.size());
		result = new ResultAllGeneration(name, maxGeneration, pm, pc);
		crossOver = new PartiallyMatchedCrossover(ans);
		calcFitness = new CalcFitnessAtLinerNormarize();
		mutation = new MutationInterJobBasedShift();
		survive = new SurviveRulette();
		isEnd = false;
	}

	private String getSaveFileName() {
		return maxGeneration + "_" + pc + "_" + pm;
	}

	public static void main(String[] args) {
		GAMain1 main = new GAMain1();

	}

	public void timer_delay() throws InterruptedException {
		TimerTask task = new SampleTask();
		Timer timer = new Timer("遅延タイマー");

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
