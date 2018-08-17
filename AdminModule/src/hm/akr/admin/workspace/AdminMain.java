
package hm.akr.admin.workspace;

import hm.akr.common.BeanUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author
 * 
 */
public class AdminMain {

	public static String SERVER_DATE = null;
	
	private static boolean prop;

	/**
	 * 
	 * @param args
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			String macId = getMacAddressForAnyOS();		
			System.out.println("Error Code 151");
			prop = new StationSettings().loadComposite();

			if (!prop)
				System.out.println("Error Code 10" + macId);
			
			if (null != macId) {				
					MainForm form = new MainForm(macId);
					form.loadForm();
			} 

		}  catch (Exception exception) {
			try {
				String macId = getMacAddressForAnyOS();	
				BeanUtil.isSecondServer=1;
				MainForm form = new MainForm(macId);
				form.loadForm();
			} catch (Exception e) {				
				e.printStackTrace();
			}
			//exception.printStackTrace();
		}
	}

	/**
	 * 
	 * @return macAddress
	 * @throws IOException
	 */

	public static String getMacAddress() throws IOException, Exception {
		String macAddress = null;
		String command = "ipconfig /all";

		try {

			Process pid = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(pid
					.getInputStream()));

			while (true) {
				String line = in.readLine();

				if (line == null)
					break;
				Pattern p = Pattern.compile(".*Physical Address.*: (.*)");
				Matcher m = p.matcher(line);

				if (m.matches()) {
					macAddress = m.group(1);
					if (macAddress.length() > 0)
						break;
				}
			}
			in.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		macAddress = macAddress.replace("-", "");

		return macAddress;
	}

	/**
	 * 
	 * @return macAddressForAllOS
	 */
	public static String getMacAddressForAnyOS() {
		String macAddress = "";
		try {

			InetAddress address = InetAddress.getLocalHost();
			/*
			 * Get NetworkInterface for the current host and then read the
			 * hardware address.
			 */
			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			byte[] mac = ni.getHardwareAddress();

			if (null != mac) {
				/*
				 * Extract each array of mac address and convert it to hexa with
				 * the following format 08-00-27-DC-4A-9E.
				 */
				for (int i = 0; i < mac.length; i++) {
					macAddress += String.format("%02X%s", mac[i],
							(i < mac.length - 1) ? "" : "");
				}
			}

		} catch (UnknownHostException exception) {
			exception.printStackTrace();
		} catch (SocketException exception) {
			exception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return macAddress;
	}

}
