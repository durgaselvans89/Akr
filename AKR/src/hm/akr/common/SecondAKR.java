package hm.akr.common;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SecondAKR {
	int count = 0;

	public int getSecondAKR(String proName) {
		String line;
		try {
			Process proc = Runtime.getRuntime().exec("wmic.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			OutputStreamWriter oStream = new OutputStreamWriter(proc
					.getOutputStream());
			oStream.write("process where name='"+proName+"'");
			oStream.flush();
			oStream.close();

			while ((line = input.readLine()) != null) {

				if (line.contains("AKR.jar"))
					count++;

			}
			input.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return count;
	}
}
