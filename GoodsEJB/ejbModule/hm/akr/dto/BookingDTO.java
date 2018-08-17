package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0 DTO class for Booking
 */
public class BookingDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lrno = null;

	private String type = null;

	private String from = null;

	private String to = null;

	private String consignorName = null;

	private String consigneeName = null;

	private String consignor_address = null;

	private String consignee_address = null;

	private float article_value = 0;

	private String article_description = null;

	private boolean status = false;

	private Date date = null;

	private Date cancel_Date = null;

	private String consignor_CST = null;

	private String consignee_GST = null;

	private int no_of_articles = 0;

	private float actual_weight = 0;

	private float charged_weight = 0;

	// basic frieght charges
	private float bft = 0;

	// Cottage carry cost
	private float ccc = 0;

	// LR Charges
	private float lrc = 0;

	private float dcc = 0;

	private float ddc = 0;

	private float ddcFree = 0;

	private float iec = 0;

	private float lc = 0;

	private float gsc = 0;

	private float insuranceCharge = 0;

	private float stax = 0;

	private int article_id = 0;

	private float other_charges = 0;

	private float total = 0;

	private String createdby = null;

	private String fromMobile = null;

	private String fromPhone1 = null;

	private String fromPhone2 = null;

	private String toMobile = null;

	private String toPhone1 = null;

	private String toPhone2 = null;

	private String duplicate = null;

	private Date deliveredDate = null;

	private String invoiceNo = null;

	private int art_unit;

	private ArticleDTO[] articledto = null;

	private int rate_type;

	private float actual_bft = 0;

	private String cancelledOption = null;

	private String cnorPhone = null;

	private String cneePhone = null;

	private float total_charged_wt = 0;

	private int smsNotify = 0;

	private String cnorLandLine = null;

	private String cneeLandLine = null;

	private float booking_commission = 0;

	private float cc_commission = 0;
	
	private boolean isStaxNo=false;
	
	private float booking_percent = 0;
	
	private float dhc = 0;
	
	private float oldLrTotal = 0;
	
	private float post = 0;
	
	private float demu = 0;
	
	private String oldLrno = null;
	
	private String isUpd = null;

	/**
	 * @return the booking_percent
	 */
	public float getBooking_percent() {
		return booking_percent;
	}
	
	public String getIsUPd()	{
		return isUpd;
	}
	
	public void setIsUpd(String isUpd){
		this.isUpd = isUpd;
	}
	
	
	
	
	public float getOldLrTotal() {
		return oldLrTotal;
	}
	
	public void setOldLrTotal(Float oldLrTotal){
		this.oldLrTotal = oldLrTotal;
	}
	
	
	public float getPost() {
		return post;
	}
	
	public void setPost(Float post){
		this.post = post;
	}
	
	public float getDemu() {
		return demu;
	}
	
	public void setDemu(Float demu){
		this.demu = demu;
	}
	
	public String getOldLrno() {
		return oldLrno;
	}
	
	public void setOldLrno(String oldLrno){
		this.oldLrno = oldLrno;
	}
	
	
	/**
	 * @param booking_percent the booking_percent to set
	 */
	public void setBooking_percent(float booking_percent) {
		this.booking_percent = booking_percent;
	}

	public String getCnorPhone() {
		return cnorPhone;
	}

	public void setCnorPhone(String cnorPhone) {
		this.cnorPhone = cnorPhone;
	}

	public String getCneePhone() {
		return cneePhone;
	}

	public void setCneePhone(String cneePhone) {
		this.cneePhone = cneePhone;
	}

	public String getCancelledOption() {
		return cancelledOption;
	}

	public void setCancelledOption(String cancelledOption) {
		this.cancelledOption = cancelledOption;
	}

	/**
	 * @return the rate_type
	 */
	public int getRate_type() {
		return rate_type;
	}

	/**
	 * @param rate_type
	 *            the rate_type to set
	 */
	public void setRate_type(int rate_type) {
		this.rate_type = rate_type;
	}

	/**
	 * @return the actual_bft
	 */
	public float getActual_bft() {
		return actual_bft;
	}

	/**
	 * @param actual_bft
	 *            the actual_bft to set
	 */
	public void setActual_bft(float actual_bft) {
		this.actual_bft = actual_bft;
	}

	/**
	 * @return the articledto
	 */
	public ArticleDTO[] getArticledto() {
		return articledto;
	}

	/**
	 * @param articledto
	 *            the articledto to set
	 */
	public void setArticledto(ArticleDTO[] articledto) {
		this.articledto = articledto;
	}

	/**
	 * Constructor method
	 */
	public BookingDTO() {
		super();

	}

	/**
	 * @return Returns the actual_weight.
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight
	 *            The actual_weight to set.
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return Returns the article_description.
	 */
	public String getArticle_description() {
		return article_description;
	}

	/**
	 * @param article_description
	 *            The article_description to set.
	 */
	public void setArticle_description(String article_description) {
		this.article_description = article_description;
	}

	/**
	 * @return Returns the article_value.
	 */
	public float getArticle_value() {
		return article_value;
	}

	/**
	 * @param article_value
	 *            The article_value to set.
	 */
	public void setArticle_value(float article_value) {
		this.article_value = article_value;
	}

	/**
	 * @return Returns the charged_weight.
	 */
	public float getCharged_weight() {
		return charged_weight;
	}

	/**
	 * @param charged_weight
	 *            The charged_weight to set.
	 */
	public void setCharged_weight(float charged_weight) {
		this.charged_weight = charged_weight;
	}

	/**
	 * @return Returns the consignee_address.
	 */
	public String getConsignee_address() {
		return consignee_address;
	}

	/**
	 * @param consignee_address
	 *            The consignee_address to set.
	 */
	public void setConsignee_address(String consignee_address) {
		this.consignee_address = consignee_address;
	}

	/**
	 * @return Returns the consignor_address.
	 */
	public String getConsignor_address() {
		return consignor_address;
	}

	/**
	 * @param consignor_address
	 *            The consignor_address to set.
	 */
	public void setConsignor_address(String consignor_address) {
		this.consignor_address = consignor_address;
	}

	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return Returns the consignee_GST.
	 */
	public String getConsignee_GST() {
		return consignee_GST;
	}

	/**
	 * @param consignee_GST
	 *            The consignee_GST to set.
	 */
	public void setConsignee_GST(String consignee_GST) {
		this.consignee_GST = consignee_GST;
	}

	/**
	 * @return Returns the consignor_CST.
	 */
	public String getConsignor_CST() {
		return consignor_CST;
	}

	/**
	 * @param consignor_CST
	 *            The consignor_CST to set.
	 */
	public void setConsignor_CST(String consignor_CST) {
		this.consignor_CST = consignor_CST;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Returns the lrno.
	 */
	public String getLrno() {
		return lrno;
	}

	/**
	 * @param lrno
	 *            The lrno to set.
	 */
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	/**
	 * @return Returns the no_of_articles.
	 */
	public int getNo_of_articles() {
		return no_of_articles;
	}

	/**
	 * @param no_of_articles
	 *            The no_of_articles to set.
	 */
	public void setNo_of_articles(int no_of_articles) {
		this.no_of_articles = no_of_articles;
	}

	/**
	 * @return Returns the other_charges.
	 */
	public float getOther_charges() {
		return other_charges;
	}

	/**
	 * @param other_charges
	 *            The other_charges to set.
	 */
	public void setOther_charges(float other_charges) {
		this.other_charges = other_charges;
	}

	/**
	 * @return Returns the status.
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Returns the total.
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            The total to set.
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the article_id.
	 */
	public int getArticle_id() {
		return article_id;
	}

	/**
	 * @param article_id
	 *            The article_id to set.
	 */
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	/**
	 * @return Returns the bft.
	 */
	public float getBft() {
		return bft;
	}

	/**
	 * @param bft
	 *            The bft to set.
	 */
	public void setBft(float bft) {
		this.bft = bft;
	}

	/**
	 * @return Returns the ccc.
	 */
	public float getCcc() {
		return ccc;
	}

	/**
	 * @param ccc
	 *            The ccc to set.
	 */
	public void setCcc(float ccc) {
		this.ccc = ccc;
	}

	/**
	 * @return Returns the dcc.
	 */
	public float getDcc() {
		return dcc;
	}

	/**
	 * @param dcc
	 *            The dcc to set.
	 */
	public void setDcc(float dcc) {
		this.dcc = dcc;
	}

	/**
	 * @return Returns the ddc.
	 */
	public float getDdc() {
		return ddc;
	}

	/**
	 * @param ddc
	 *            The ddc to set.
	 */
	public void setDdc(float ddc) {
		this.ddc = ddc;
	}

	/**
	 * @return Returns the gsc.
	 */
	public float getGsc() {
		return gsc;
	}

	/**
	 * @param gsc
	 *            The gsc to set.
	 */
	public void setGsc(float gsc) {
		this.gsc = gsc;
	}

	/**
	 * @return Returns the iec.
	 */
	public float getIec() {
		return iec;
	}

	/**
	 * @param iec
	 *            The iec to set.
	 */
	public void setIec(float iec) {
		this.iec = iec;
	}

	/**
	 * @return Returns the lc.
	 */
	public float getLc() {
		return lc;
	}

	/**
	 * @param lc
	 *            The lc to set.
	 */
	public void setLc(float lc) {
		this.lc = lc;
	}

	/**
	 * @return Returns the lrc.
	 */
	public float getLrc() {
		return lrc;
	}

	/**
	 * @param lrc
	 *            The lrc to set.
	 */
	public void setLrc(float lrc) {
		this.lrc = lrc;
	}

	/**
	 * @return Returns the stax.
	 */
	public float getStax() {
		return stax;
	}

	/**
	 * @param stax
	 *            The stax to set.
	 */
	public void setStax(float stax) {
		this.stax = stax;
	}

	/**
	 * @return Returns the consigneeName.
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * @param consigneeName
	 *            The consigneeName to set.
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	/**
	 * @return Returns the consignorName.
	 */
	public String getConsignorName() {
		return consignorName;
	}

	/**
	 * @param consignorName
	 *            The consignorName to set.
	 */
	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby
	 *            the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the fromMobile
	 */
	public String getFromMobile() {
		return fromMobile;
	}

	/**
	 * @param fromMobile
	 *            the fromMobile to set
	 */
	public void setFromMobile(String fromMobile) {
		this.fromMobile = fromMobile;
	}

	/**
	 * @return the fromPhone1
	 */
	public String getFromPhone1() {
		return fromPhone1;
	}

	/**
	 * @param fromPhone1
	 *            the fromPhone1 to set
	 */
	public void setFromPhone1(String fromPhone1) {
		this.fromPhone1 = fromPhone1;
	}

	/**
	 * @return the fromPhone2
	 */
	public String getFromPhone2() {
		return fromPhone2;
	}

	/**
	 * @param fromPhone2
	 *            the fromPhone2 to set
	 */
	public void setFromPhone2(String fromPhone2) {
		this.fromPhone2 = fromPhone2;
	}

	/**
	 * @return the toMobile
	 */
	public String getToMobile() {
		return toMobile;
	}

	/**
	 * @param toMobile
	 *            the toMobile to set
	 */
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}

	/**
	 * @return the toPhone1
	 */
	public String getToPhone1() {
		return toPhone1;
	}

	/**
	 * @param toPhone1
	 *            the toPhone1 to set
	 */
	public void setToPhone1(String toPhone1) {
		this.toPhone1 = toPhone1;
	}

	/**
	 * @return the toPhone2
	 */
	public String getToPhone2() {
		return toPhone2;
	}

	/**
	 * @param toPhone2
	 *            the toPhone2 to set
	 */
	public void setToPhone2(String toPhone2) {
		this.toPhone2 = toPhone2;
	}

	/**
	 * @return the duplicate
	 */
	public String getDuplicate() {
		return duplicate;
	}

	/**
	 * @param duplicate
	 *            the duplicate to set
	 */
	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}

	/**
	 * @return the insuranceCharge
	 */
	public float getInsuranceCharge() {
		return insuranceCharge;
	}

	/**
	 * @param insuranceCharge
	 *            the insuranceCharge to set
	 */
	public void setInsuranceCharge(float insuranceCharge) {
		this.insuranceCharge = insuranceCharge;
	}

	/**
	 * @return the deliveredDate
	 */
	public Date getDeliveredDate() {
		return deliveredDate;
	}

	/**
	 * @param deliveredDate
	 *            the deliveredDate to set
	 */
	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo
	 *            the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the art_unit
	 */
	public int getArt_unit() {
		return art_unit;
	}

	/**
	 * @param art_unit
	 *            the art_unit to set
	 */
	public void setArt_unit(int art_unit) {
		this.art_unit = art_unit;
	}

	public Date getCancel_Date() {
		return cancel_Date;
	}

	public void setCancel_Date(Date cancel_Date) {
		this.cancel_Date = cancel_Date;
	}

	public float getDdcFree() {
		return ddcFree;
	}

	public void setDdcFree(float ddcFree) {
		this.ddcFree = ddcFree;
	}

	/**
	 * @return the total_charged_wt
	 */
	public float getTotal_charged_wt() {
		return total_charged_wt;
	}

	/**
	 * @param total_charged_wt
	 *            the total_charged_wt to set
	 */
	public void setTotal_charged_wt(float total_charged_wt) {
		this.total_charged_wt = total_charged_wt;
	}

	/**
	 * @return the smsNotify
	 */
	public int getSmsNotify() {
		return smsNotify;
	}

	/**
	 * @param smsNotify
	 *            the smsNotify to set
	 */
	public void setSmsNotify(int smsNotify) {
		this.smsNotify = smsNotify;
	}

	/**
	 * @return the cnorLandLine
	 */
	public String getCnorLandLine() {
		return cnorLandLine;
	}

	/**
	 * @param cnorLandLine
	 *            the cnorLandLine to set
	 */
	public void setCnorLandLine(String cnorLandLine) {
		this.cnorLandLine = cnorLandLine;
	}

	/**
	 * @return the cneeLandLine
	 */
	public String getCneeLandLine() {
		return cneeLandLine;
	}

	/**
	 * @param cneeLandLine
	 *            the cneeLandLine to set
	 */
	public void setCneeLandLine(String cneeLandLine) {
		this.cneeLandLine = cneeLandLine;
	}

	/**
	 * @return the booking_commission
	 */
	public float getBooking_commission() {
		return booking_commission;
	}

	/**
	 * @param booking_commission
	 *            the booking_commission to set
	 */
	public void setBooking_commission(float booking_commission) {
		this.booking_commission = booking_commission;
	}

	/**
	 * @return the cc_commission
	 */
	public float getCc_commission() {
		return cc_commission;
	}

	/**
	 * @param cc_commission
	 *            the cc_commission to set
	 */
	public void setCc_commission(float cc_commission) {
		this.cc_commission = cc_commission;
	}

	/**
	 * @return the isStaxNo
	 */
	public boolean isStaxNo() {
		return isStaxNo;
	}

	/**
	 * @param isStaxNo the isStaxNo to set
	 */
	public void setStaxNo(boolean isStaxNo) {
		this.isStaxNo = isStaxNo;
	}

	/**
	 * @return the dhc
	 */
	public float getDhc() {
		return dhc;
	}

	/**
	 * @param dhc the dhc to set
	 */
	public void setDhc(float dhc) {
		this.dhc = dhc;
	}

}
