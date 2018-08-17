/**
 * 
 */
package hm.akr.container.history;

import hm.akr.common.BeanUtil;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.interfaces.Reporting;

/**
 * @author user
 *
 */
public class HistoryHandler {

	BeanUtil beanutil = null;
	private Reporting remote;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public HistoryHandler() throws Exception {
		try {
			beanutil = BeanUtil.getInstance();
			remote = beanutil.getReportingBean();			
		} catch (Exception exception) {
			throw exception;
		}
	}
	
		
	public VersionDTO[] getHistoryYears() throws Exception {
		if(remote != null){
			return remote.getHistoryYears();
		}		
		return null;
	}
	

}
