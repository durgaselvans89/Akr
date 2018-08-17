package hm.akr.admin.sundry.handler;

import java.rmi.RemoteException;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.DiscounterDTO;
import hm.akr.dto.InsuranceDTO;
import hm.akr.dto.RegularSundryDTO;
import hm.akr.dto.SpecialSundryDTO;
import hm.akr.interfaces.Station;

public class SundryHandler {

	BeanUtil beanutil = null;

	/**
	 * Constructor Method for Admin composite handler
	 * 
	 * @throws Exception
	 */
	public SundryHandler() throws Exception {
		beanutil = new BeanUtil();

	}

	/**
	 * Method to set BPI for given Station
	 * 
	 * @param stationcodes
	 * @param bpivalue
	 * @throws Exception
	 */
	public void setBPIValues(String[] stationcodes, float bpivalue)
			throws Exception {
		
		if (null != beanutil) {
			beanutil.setBPI(stationcodes, bpivalue);
		}
	}

	/**
	 * Method to set LR charges for given station
	 * 
	 * @param stationcodes
	 * @param lrc
	 * @throws Exception
	 */
	public void setLRC(String[] stationcodes, float lrc) throws Exception {
		if (null != beanutil) {
			beanutil.setLRCharges(stationcodes, lrc);
		}
	}

	/**
	 * Method to set GSC value for given stations
	 * 
	 * @param stationcodes
	 * @param gsc
	 * @throws Exception
	 */
	public void setGSC(String[] stationcodes, float gsc) throws Exception {
		if (null != beanutil) {
			beanutil.setGSC(stationcodes, gsc);
		}
	}

	/**
	 * Method to set BPI decrement values for given station
	 * 
	 * @param stationcodes
	 * @param bpiincrement
	 * @throws Exception
	 */
	public void setBFIIncrement(String[] stationcodes, int bpiincrement)
			throws Exception {
		if (null != beanutil) {
			beanutil.setBFIncrement(stationcodes, bpiincrement);
		}
	}

	/**
	 * Method to set BPI increment values for given station
	 * 
	 * @param stationcodes
	 * @param bpidecrement
	 * @throws Exception
	 */
	public void setBFIDecrement(String[] stationcodes, int bpidecrement)
			throws Exception {
		if (null != beanutil) {
			beanutil.setBFDecrement(stationcodes, bpidecrement);
		}
	}

	/**
	 * 
	 * @param stationcodes
	 * @param minFT
	 * @throws Exception
	 */
	public void setMinimumFreight(String[] stationcodes, int minFT)
			throws Exception {

		try {
			Station stationRemote = beanutil.getStationBean();
			stationRemote.setMinimumFreight(stationcodes, minFT);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcodes
	 * @param minFT
	 * @throws Exception
	 */
	public void setMinimumWeight(String[] stationcodes, int minFT)
			throws Exception {

		try {
			Station stationRemote = beanutil.getStationBean();
			stationRemote.setMinimumWeight(stationcodes, minFT);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * Method to get BPI for LR
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getBPI() throws Exception {
		Float[] bpi = null;
		if (null != beanutil) {
			bpi = beanutil.getBPI();
		}
		return bpi;
	}
	
	/**
	 * Method to get pending BPI
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getPendingBPI() throws Exception {
		Float[] bpi = null;
		if (null != beanutil) {
			bpi = beanutil.getPendingBPI();
		}
		return bpi;
	}

	/**
	 * Method to get LRC for LR
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getLRCharges() throws Exception {
		Float[] lrc = null;
		if (null != beanutil) {
			lrc = beanutil.getLRCharges();
		}
		return lrc;
	}
	
	/**
	 * Method to get Pending LRC for LR
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getPendingLRC() throws Exception {
		Float[] lrc = null;
		if (null != beanutil) {
			lrc = beanutil.getPendingLRC();
		}
		return lrc;
	}

	/**
	 * Method to get GSC for LR
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getGSC() throws Exception {
		Float[] gsc = null;
		if (null != beanutil) {
			gsc = beanutil.getGSC();
		}
		return gsc;
	}

	/**
	 * Method to get Regular Sundry details for LR
	 * 
	 * @return
	 * @throws Exception
	 */
	public RegularSundryDTO[] getRegularSundryDetails() throws Exception {
		RegularSundryDTO[] dto = null;

		if (null != beanutil) {
			dto = beanutil.getRegularSundryDetails();
		}
		return dto;
	}

	/**
	 * Method to update old BPI values using new values,it will get effective
	 * from given effective date
	 * 
	 * @param oldbpi
	 * @param newbpi
	 * @param effectivedate
	 * @throws Exception
	 */
	public void setBPIUpdate(Float[] oldbpi, Float[] newbpi, Date effectivedate)
			throws Exception {
		if (null != beanutil) {
			beanutil.setBPIUpdate(oldbpi, newbpi, effectivedate);
		}
	}

	/**
	 * Method to update old LRC values using new values,it will get effective
	 * from given effective date
	 * 
	 * @param oldlrc
	 * @param newlrc
	 * @param effectivedate
	 * @throws Exception
	 */
	public void setLRCUpdate(Float[] oldlrc, Float[] newlrc, Date effectivedate)
			throws Exception {
		if (null != beanutil) {
			beanutil.setLRCUpdate(oldlrc, newlrc, effectivedate);
		}
	}

	/**
	 * Method to update old GSC values using new values,it will get effective
	 * from given effective date
	 * 
	 * @param oldgsc
	 * @param newgsc
	 * @param effectivedate
	 * @throws Exception
	 */
	public void setGSCUpdate(Float[] oldgsc, Float[] newgsc, Date effectivedate)
			throws Exception {
		if (null != beanutil) {
			beanutil.setGSCUpdate(oldgsc, newgsc, effectivedate);
		}
	}

	/**
	 * Method to set General settings
	 * 
	 * @param rsDTO
	 * @throws Exception
	 */
	public void setGeneralSettings(RegularSundryDTO rsDTO) throws Exception {
		if (null != beanutil) {
			beanutil.setGeneralSettings(rsDTO);
		}
	}

	/**
	 * Method to get LR charges for special sundry LS's
	 * 
	 * @return
	 * @throws Exception
	 */
	public SpecialSundryDTO[] getSpecialSundrySettings() throws Exception {
		SpecialSundryDTO[] ssDTO = null;
		if (null != beanutil) {
			ssDTO = beanutil.getSpecialSundrySettings();
		}
		return ssDTO;
	}

	/**
	 * Method to get available insurance charges
	 * 
	 * @return
	 * @throws Exception
	 */
	public InsuranceDTO[] getInsuranceChargeList() throws Exception {
		InsuranceDTO[] icDTO = null;
		if (null != beanutil) {
			icDTO = beanutil.getInsuranceChargeList();
		}
		return icDTO;
	}

	/**
	 * Method to set insurance details for LR
	 * 
	 * @param icDTO
	 * @throws Exception
	 */
	public void setInsuranceChargeList(InsuranceDTO[] icDTO) throws Exception {
		if (null != beanutil) {
			beanutil.setInsuranceChargeList(icDTO);
		}
	}

	/**
	 * Method to set LR charges for special sundry
	 * 
	 * @param ssDTO
	 * @throws Exception
	 */
	public void setSpecialSundrySettings(SpecialSundryDTO[] ssDTO)
			throws Exception {
		if (null != beanutil) {
			beanutil.setSpecialSundrySettings(ssDTO);
		}
	}

	/**
	 * Method to get all commodities
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getAricleTypes() throws Exception {
		ArticleDTO[] artDTO = null;
		if (null != beanutil) {
			artDTO = beanutil.getAricleTypes();
		}
		return artDTO;
	}

	/**
	 * Method to insert no of commodities
	 * 
	 * @param artDTO
	 * @throws Exception
	 */
	public void insertCommodities(ArticleDTO[] artDTO) throws Exception {
		if (null != beanutil) {
			beanutil.insertCommodities(artDTO);
		}
	}

	/**
	 * Method to update commodities for all changed attributes *
	 * 
	 * @param artDTO
	 * @throws Exception
	 */
	public void updateCommodities(ArticleDTO[] artDTO) throws Exception {
		if (null != beanutil) {
			beanutil.updateCommodities(artDTO);
		}
	}

	/**
	 * Method to Remove a commodity using given id
	 * 
	 * @param articleId
	 * @throws Exception
	 */
	public void deleteCommodity(String articleName) throws Exception {
		if (null != beanutil) {
			beanutil.deleteCommodity(articleName);
		}
	}
	
	public String getServerDate() throws Exception {
		String date = null;
		if (null != beanutil) {
			date = BeanUtil.getServerDate();
		}		
		return date;
	}

	/**
	 * 
	 * @param articleType
	 * @throws Exception
	 */
	public void insertSundryArticle(String articleType) throws Exception {
		if (null != beanutil) {
			beanutil.insertSundryArticle(articleType);
		}
	}

	public ArticleDTO[] getSundryAricleTypes() throws Exception {
		ArticleDTO[] artDTO = null;
		if (null != beanutil) {
			artDTO = beanutil.getSundryAricleTypes();
		}
		return artDTO;
	}
	
	/**
	 * 
	 * @param articleType
	 * @throws Exception
	 */
	public void deleteSundryArticle(String articleType) throws Exception {
		if (null != beanutil) {
			beanutil.deleteSundryArticle(articleType);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public RegularSundryDTO getGeneralSettings() throws Exception {
		RegularSundryDTO regularDTO = null;
		if (null != beanutil) {
			regularDTO = beanutil.getGeneralSettings();
		}
		return regularDTO;
	}
	
	/**
	 * 
	 * @param stationcodes
	 * @param bpidecrement
	 * @throws Exception
	 */
	public void setCCSundryLimit(String[] stationcodes, int cc)
			throws Exception {
		if (null != beanutil) {
			beanutil.setCCSundryLimit(stationcodes, cc);
		}
	}	
	
	public void setDiscounter(DiscounterDTO[] discount)
	throws Exception {
		if (null != beanutil) {
			beanutil.setDiscounter(discount);
		}
	}	
	
	public DiscounterDTO[] getDiscounter() throws Exception {
		DiscounterDTO[] discountDTO = null;
		if (null != beanutil) {
			discountDTO = beanutil.getDiscounter();
		}
		return discountDTO;
	}
	
	public void deleteDiscounter(String desc)
	throws Exception {
		if (null != beanutil) {
			beanutil.deleteDiscounter(desc);
		}
	}	
	
	public void setDHC(String[] stationcodes, float dhc)
	throws Exception {
		if (null != beanutil) {
			beanutil.setDHC(stationcodes, dhc);
		}
	}	
	
	public void setIncrementer(DiscounterDTO[] incr)
	throws Exception {
		if (null != beanutil) {
			beanutil.setIncrementer(incr);
		}
	}	
	
	public DiscounterDTO[] getIncrementer() throws Exception {
		DiscounterDTO[] incrDTO = null;
		if (null != beanutil) {
			incrDTO = beanutil.getIncrementer();
		}
		return incrDTO;
	}
	
	public void deleteIncrementer(String from, String to)
	throws Exception {
		if (null != beanutil) {
			beanutil.deleteIncrementer(from, to);
		}
	}	
	
	public DiscounterDTO[] searchIncrementer(String desc)
	throws Exception {
		if (null != beanutil) {
			return beanutil.searchIncrementer(desc);
		}
		return null;
	}	
	
	public void updateIncrementer(int incr, String desc)
	throws Exception {
		if (null != beanutil) {
			beanutil.updateIncrementer(incr, desc);
		}
	}
	public void deleteAllIncrementer(String desc)
	throws Exception {
		if (null != beanutil) {
			beanutil.deleteAllIncrementer(desc);
		}		
	}	
			
}
