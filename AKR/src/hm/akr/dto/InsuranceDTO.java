/**
 * 
 */
package hm.akr.dto;
import java.io.Serializable;

/**
 * @author user
 *
 */
public class InsuranceDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String quotationId = null;
	
	private String articleName = null;
	
	private float fromValue = 0;
	
	private float toValue = 0;
	
	private float insuranceChargeValue = 0;
	
	
	/**
	 * 
	 */
	public InsuranceDTO() {
		
	}


	public String getQuotationId() {
		return quotationId;
	}


	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}


	public String getArticleName() {
		return articleName;
	}


	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}


	public float getFromValue() {
		return fromValue;
	}


	public void setFromValue(float fromValue) {
		this.fromValue = fromValue;
	}


	public float getToValue() {
		return toValue;
	}


	public void setToValue(float toValue) {
		this.toValue = toValue;
	}


	public float getInsuranceChargeValue() {
		return insuranceChargeValue;
	}


	public void setInsuranceChargeValue(float insuranceChargeValue) {
		this.insuranceChargeValue = insuranceChargeValue;
	}

}
