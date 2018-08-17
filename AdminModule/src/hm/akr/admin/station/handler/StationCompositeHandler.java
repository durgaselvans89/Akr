package hm.akr.admin.station.handler;

import javax.naming.NamingException;

import hm.akr.common.BeanUtil;

public class StationCompositeHandler {

	BeanUtil beanutil = null;
	
	public StationCompositeHandler() throws NamingException, Exception {
		beanutil = new BeanUtil();
	}
	
	public void setStationLanguage(String[] stationcodes, byte tamil) throws Exception {
		if (null != beanutil) {
			beanutil.setStationLanguage(stationcodes, tamil);
		}
	}

}
