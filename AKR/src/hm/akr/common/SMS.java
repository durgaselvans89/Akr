package hm.akr.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SMS {

	public void sendSMS(String phone, String message) {
		try {

			String makeURL = "http://enterprise.smsgupshup.com/GatewayAPI/rest?method=sendMessage&send_to="
					+ phone
					+ "&msg="
					+ message
					+ "&userid=2000032501&password=TextTrackGoods&v=1.0&mask=trackAKR"
					+"&send_to_partial_option=true";
			makeURL = makeURL.replaceAll(" ", "%20");
			URL url = new URL(makeURL);

			//URLConnection conn = url.openConnection();

			//BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;
			//while ((line = in.readLine()) != null) {
				//System.out.println("Result:" + line);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
