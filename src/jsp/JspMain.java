package jsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import TS.UseTime;
import jspload.LoadJSP;
import result.IResult;

public class JspMain {
	protected Jobs jobs;
	protected Ans ans;
	protected Machines machines;
	protected Data data;
	protected String name = "job3.txt";
	protected String masterPath;
	protected String jspPath;
	protected String saveFilePath;
	protected UseTime useTime;
	protected int cycleNumber = 10;
	protected int nowCycleNumber;
	protected int foundBestMakeSpan;
	protected int foundBestMakeSpanYear = Integer.MAX_VALUE;
	
	
	public JspMain() {
		// TODO Auto-generated constructor stub
		masterPath = new File(System.getProperty("java.class.path")).getPath();
		int index = masterPath.indexOf(";");
		masterPath = new File(masterPath.substring(0,index)).getParent();
		
		init(0);
	}
	
	public JspMain(int tabuLength) {
		// TODO Auto-generated constructor stub
		this();
		
		init(tabuLength);
	}
	
	
	
	public void init(int tabuLength){
		data = new Data();
		
		jspPath = masterPath + "\\src\\jsp_example\\";
		File file = new File(jspPath + name);
		LoadJSP load = new LoadJSP(file, data);
		load.loadBasicData();
		jobs = new Jobs(data);
		load.setJob(jobs);
		ans = new Ans(data);
		machines = new Machines(data, tabuLength);
	}
	
	protected int setGnt(int[] tmp) {
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
	
	public void outResult(IResult result,String extension) {
		ObjectOutputStream oos = null;
		try {
			Calendar now = Calendar.getInstance();

			oos = new ObjectOutputStream(
					new FileOutputStream(saveFilePath + "\\" + "Result" + +now.get(Calendar.DAY_OF_MONTH)
							+"_"+ now.get(Calendar.HOUR_OF_DAY) + "_"+now.get(Calendar.MINUTE) + "_"+extension));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			oos.writeObject(result);
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
