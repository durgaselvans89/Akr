/**
 * 
 */
package hm.akr.workspace;

import java.io.*;
import java.util.Properties;

/**
 * 
 */
public class makeDir {

	
	
	public static void main(String args[]) throws IOException
	{
		String dirLoc = "F:/Test1/";
		String stnCodeLoc = "F:/Test1/stnCode.txt";
		
		FileInputStream fis = null;		
		BufferedInputStream bis = null;
		DataInputStream dis = null;		
		
		fis = new FileInputStream(stnCodeLoc);    
		bis = new BufferedInputStream(fis);
		dis = new DataInputStream(bis);
		
		while (dis.available() != 0) {

        String folder = dis.readLine();
        String[] station = folder.split("\t");
        
         
        File dir = new File(dirLoc + station[0]);
        boolean succ = (dir).mkdir();

        if (!succ)
        {
        	System.out.println("Creation failed");
        }	
        else{ 
		
        	System.out.println("Folder " + station[0] + " Created");	
		
		try {
			Properties props = new Properties();
			props.setProperty("StationCode", station[0]);
			props.setProperty("CashBeanJNDI", "ejb/Cash");
			props.setProperty("ReportingBeanJNDI", "ejb/Reporting");
			props.setProperty("InitialContextFactory", "org.jnp.interfaces.NamingContextFactory");
			props.setProperty("GoodsBeanJNDI", "ejb/Goods");
			props.setProperty("SecurityBeanJNDI", "ejb/Security");
			props.setProperty("StationBeanJNDI", "ejb/Station");
			props.setProperty("UrlPrefix", "org.jboss.naming/:org.jnp.interfaces");
			props.setProperty("VehicleBeanJNDI", "ejb/Vehicle");
			props.setProperty("ProviderUrl", "87.248.113.14:1099");
			props.setProperty("StationName", station[1]);
			
			props.store(new FileOutputStream(new File(dirLoc + station[0] + "/akrsystem.properties")), "akr");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	
	}
	}
     
     fis.close();
     bis.close();
     dis.close();
     
    }
	
}
