/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class InwardAnalysisDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fromStation = null;
	
	private String inwardStation = null;
	
	private int count = 0;
	
	private float basic_freight = 0;
	
	private float total_freight = 0;
	
	private float actual_weight = 0;
	
	private float charged_weight = 0;
	
	private int noa = 0;
	
	/**
	 * 
	 */
	public InwardAnalysisDTO() {
		super();		
	}

	/**
	 * @return the fromStation
	 */
	public String getFromStation() {
		return fromStation;
	}

	/**
	 * @param fromStation the fromStation to set
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/**
	 * @return the inwardStation
	 */
	public String getInwardStation() {
		return inwardStation;
	}

	/**
	 * @param inwardStation the inwardStation to set
	 */
	public void setInwardStation(String inwardStation) {
		this.inwardStation = inwardStation;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the basic_freight
	 */
	public float getBasic_freight() {
		return basic_freight;
	}

	/**
	 * @param basic_freight the basic_freight to set
	 */
	public void setBasic_freight(float basic_freight) {
		this.basic_freight = basic_freight;
	}

	/**
	 * @return the total_freight
	 */
	public float getTotal_freight() {
		return total_freight;
	}

	/**
	 * @param total_freight the total_freight to set
	 */
	public void setTotal_freight(float total_freight) {
		this.total_freight = total_freight;
	}

	/**
	 * @return the actual_weight
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight the actual_weight to set
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return the charged_weight
	 */
	public float getCharged_weight() {
		return charged_weight;
	}

	/**
	 * @param charged_weight the charged_weight to set
	 */
	public void setCharged_weight(float charged_weight) {
		this.charged_weight = charged_weight;
	}

	/**
	 * @return the noa
	 */
	public int getNoa() {
		return noa;
	}

	/**
	 * @param noa the noa to set
	 */
	public void setNoa(int noa) {
		this.noa = noa;
	}

}
