package com.hm.dao;

/**
 * 
 * 
 */
public interface DAOConstants {

	public static final int CANCELLED_STATUS = 1;

	public static final String DATE_FORMAT = "dd-MM-yyyy";

	public static final String SQLDATE_FORMAT = "yyyy-MM-dd";

	/** **************Fields********************************* */

	public static final String LR_NO = "lr_no";

	public static final String CR_NO = "cr_no";

	public static final String STATION_CODE = "station_code";

	public static final String DATE = "date";

	public static final String CONSIGNEE_NAME = "consignee_name";

	public static final String OUTWARD_TIME = "outward_time";

	public static final String OTHER = "other";

	public static final String RECIV_FROM = "receivedfrom";

	public static final String INWARD_TIME = "inward_time";

	public static final String STATUS = "status";

	public static final String DEMURAGE = "demurrage";

	public static final String UNDER_CHARGE = "undercharge";

	public static final String POSTAGE = "postage";

	public static final String DD_EXTRA = "ddextra";

	public static final String TOTAL_AMOUNT = "total_amount";

	public static final String LR_DATE = "lr_date";

	public static final String FROM_STATION = "from_station";

	public static final String TO_STATION = "to_station";

	public static final String NO_OF_ARTICLES = "no_of_articles";

	public static final String ACTUAL_WEIGHT = "actual_weight";

	public static final String BASIC_FRIEGHT = "bft";

	public static final String DOOR_DELIVERY_CHARGE = "ddc";

	public static final String CR_PRINT = "cr_print";

	public static final String DUPLICATE_ENTRY = "Duplicate entry";

	public static final String LAST_INWARD_DATE = "last_inward_date";

	public static final String LR_TYPE = "lr_type";
	public static final String CONSIGNOR_ADDRESS = "consignor_address";
	public static final String CONSIGNEE_ADDRESS = "consignee_address";
	public static final String CONSIGNOR_CST = "consignor_CST";
	public static final String CONSIGNEE_GST = "consignee_GST";

	public static final String CCC = "ccc";
	public static final String DCC = "dcc";
	public static final String DHC = "dhc";
	public static final String DDC = "ddc";
	public static final String IEC = "iec";
	public static final String LC = "lc";
	public static final String GSC = "gsc";
	public static final String SALES_TAX = "stax";

	public static final String CONSIGNOR_NAME = "consignor_name";

	public static final String ARTICLE_ID = "article_id";
	public static final String ARTICLE_VALUE = "article_value";
	public static final String ARTICLE_DESC = "article_description";
	public static final String CHARGED_WEIGHT = "charged_weight";
	public static final String LRCHARGES = "lrc";

	public static final String OTHER_CHARGES = "other_charges";
	public static final String TOTAL = "total";
	public static final String LR_STATUS = "lr_status";
	public static final String RECORD_NOT_AVAILABLE = "LR Number does not exist";

	/** *************************************************** */

	public static final String ART_NAME = "article_name";
	public static final String ART_NOA = "no_of_article";
	public static final String ART_VALUE = "article_value";
	public static final String ART_ACTUALWT = "actual_weight";
	public static final String ART_VOLUMEWT = "volume_weight";
	public static final String ART_CHGDWT = "charged_weight";
	public static final String ART_DESC = "article_desc";
	public static final String ART_LEN = "art_length";
	public static final String ART_BRTH = "art_breath";
	public static final String ART_HT = "art_height";
	public static final String ART_UNIT = "art_unit";

	public static final String CNOR_PHONE = "cnorphone";

	public static final String CNEE_PHONE = "cneephone";

	public static final String RATE_TYPE = "rate_type";

	public static final String SMS_ALERT = "sms_notification";

	/** Query ********************************************* */

	public String generateCR = "insert into cash_receipt(cr_no,date,station_code,lr_no,demurrage,undercharge,"
			+ "postage,ddextra,other,total_amount,lr_delivery_date,receivedfrom) "
			+ "values (?,NOW(),?,?,?,?,?,?,?,?,?,?)";

	public String generateCR1 = "insert into cash_receipt(cr_no,date,station_code,lr_no,demurrage,undercharge,"
			+ "postage,ddextra,other,total_amount,lr_delivery_date,receivedfrom) "
			+ "values (?,date_add(NOW(),interval 1 second),?,?,?,?,?,?,?,?,?,?)";
	
	public String GET_LASTUSED_CR_AGAIN = "select max(cr_no) as LAST_CR FROM cash_receipt where " +
			"station_code = ? and substring(cr_no,1,1) = ? ";

	
	public String updateCR = "update cash_receipt set demurrage = ?, undercharge = ?, postage = ?," +
			" ddextra = ?, other = ?, total_amount = ?, receivedfrom = ?, status = 2 where cr_no = ?";


	public static final String TOPAY_LR_NOS = "Select A.lr_no, A.last_inward_date "
			+ "from lr A  where  A.delivered_station  = ? and  A.lr_status = 2  AND A.lr_type =  'TOPAY'"
			+ " order by cast(SUBSTR(lr_no,2) as decimal)";

	public static final String TOPAY_LR_NOS1 = "select c.cr_no,a.lr_no,last_inward_date, lr_date, from_station, "
			+ "to_station,consignor_name, consignee_name, no_of_articles, charged_weight, ddc, total,cnorphone,cneephone,rate_type,sms_notification, a.actual_weight from lr a, cash_receipt c where"
			+ " a.lr_no = c.lr_no and c.status = 0 and delivered_station  = ? and  lr_status = 3  AND " 
			+ " lr_type =  'TOPAY' and date(a.delivered_date) > '2011-02-26' order by delivered_date desc limit 750";

	public static final String PAID_LR_NOS1 = "select c.cr_no,a.lr_no,last_inward_date, lr_date,from_station, "
			+ "to_station,consignor_name,consignee_name,no_of_articles,charged_weight,ddc,total,cnorphone,cneephone,rate_type,sms_notification, a.actual_weight from lr a, cash_receipt c "
			+ "where a.lr_no = c.lr_no and c.status = 0 and delivered_station = ? and lr_status = 3 and " 
			+ " lr_type = 'PAID' and date(delivered_date) > '2011-02-26' order by delivered_date desc limit 750";

	public static final String BILLING_LR_NOS1 = "select c.cr_no,a.lr_no,last_inward_date, lr_date, from_station, "
			+ "to_station,consignor_name,consignee_name,no_of_articles,charged_weight,ddc,total,cnorphone,cneephone,rate_type,sms_notification, a.actual_weight from lr a, cash_receipt c where "
			+ " a.lr_no = c.lr_no and c.status = 0 and delivered_station = ?  and lr_status = 3 and  lr_type = 'BILLING'  "
			+ " and date(delivered_date) > '2011-02-26' order by delivered_date desc limit 750";

	public static final String UPDATE_CR_STATUS = "update cash_receipt set status = ? , cr_cancel_date = now() where cr_no = ? AND station_code = ?";

	public static final String GET_CR_INFO = "select a.cr_no, a.date, a.station_code, a.lr_no, a.demurrage,"
			+ " a.undercharge, a.postage, a.ddextra,a.other,a.total_amount, b.lr_date, b.from_station,b.consignor_name, b.consignee_name,"
			+ " b.to_station, b.no_of_articles, b.actual_weight, b.bft, b.ddc,b.cnorphone,b.cneephone, a.status from cash_receipt a, lr"
			+ " b where b.lr_no = a.lr_no and a.cr_no = ?";

	public static final String GET_CR_INFO_HISTORY = "select a.cr_no, a.date, a.station_code, a.lr_no, a.demurrage,"
		+ " a.undercharge, a.postage, a.ddextra,a.other,a.total_amount, b.lr_date, b.from_station,b.consignor_name, b.consignee_name,"
		+ " b.to_station, b.no_of_articles, b.actual_weight, b.bft, b.ddc,b.cnorphone,b.cneephone, a.status from cash_receipt_history a, lr_history"
		+ " b where b.lr_no = a.lr_no and a.cr_no = ?";

	public static final String IS_DRS_CONFIRMED = "SELECT status FROM Daily_remittance WHERE station_code = ? " +
	" and date(date_sub(dr_date, interval 1 day)) = ?";

	
	public static final String GET_CR_PRINT_INFO = "select a.cr_no, a.date, a.station_code, a.lr_no, "
			+ "a.demurrage, a.undercharge,a.postage, a.ddextra,a.other,a.total_amount, b.lr_date, "
			+ "b.from_station,a.receivedfrom,b.consignee_name, b.to_station, b.no_of_articles, b.actual_weight, b.bft,"
			+ " b.ddc, b.total, a.status,a.cr_print,b.lr_type,b.consignor_name, "
			+ "b.consignor_address,b.consignee_address, b.consignor_cst, b.consignee_gst, b.article_id,"
			+ "b.article_value, b.article_description, b.charged_weight, b.bft, b.lrc,b.dhc, b.ccc, b.dcc, b.iec, b.lc,"
			+ "b.gsc, b.stax, b.other_charges,b.lr_status, b.createdby, b.createdon  from cash_receipt a, lr b where  a.station_code=? and"
			+ " b.lr_no = a.lr_no and date(a.date)=? and a.status != 1 and (a.status = 2 or a.cr_print = 1) " ;

	public static final String UPDATE_CR_PRINT_STATUS = "update cash_receipt set cr_print=1 where cr_no=?";

	//public static final String GET_LAST_CREATED_CR = "SELECT max(cr_no) as LAST_CR FROM cash_receipt c where "
		//	+ "station_code=? and date(c.date) = date((select max(d.date) from cash_receipt d where station_code=?))";

	
	public static final String GET_LAST_CREATED_CR = "select cr_no as LAST_CR from cash_receipt where " +
			" station_code = ? order by date desc limit 1" ;
	
	//public static final String GET_LAST_CREATED_CR = "select max(cr_no) as LAST_CR " +
		//	"FROM cash_receipt where station_code = ? and  substring(cr_no,1,1) = substring(" +
			//"(SELECT cr_no FROM cash_receipt where station_code = ? order by date desc limit 1),1,1)";
	
	public static final String GET_LR_RANGE = "select station_code, type, start_number, end_number, batch_id "
			+ "FROM lr_stock_allocation l where station_code=? "
			+ "and l.type='cr' order by batch_id desc limit 3";

	public static final String getDeliveryDate = "select delivered_date from lr where lr_no=? ";

	public static final String STOR_SMS = "insert into pending_sms(mobile_no,message,stored_date) values(?,?,now())";

	/** **************************************************** */

	/** ***********Error Message *************************** */
	public static final String CANCELLATION_ERROR = "Cancellation Error.";

	public static final String DUPLICATE_ERROR = "CR Number already exists. Please use a different CR Number";

	public static final String INSERTION_ERROR = "Error while inserting record in GMR out time";

	public static final String UPDATION_ERROR = "The station which delivered the LR can only cancel the corresponding CR";

	public static final String STATUS_CHANGE_ERROR = "Error while chaging status";

	public static final String GET_CR_ERROR = "Error while getting cr details";

	public static final String GET_ARTICLEDETAIL = "select lr_no,article_name,no_of_article,article_value,"
			+ "	actual_weight,art_length,art_breath,art_height,volume_weight,charged_weight,article_desc"
			+ "	from article_details where lr_no=?";
	
	public static final String GET_ARTICLEDETAIL_HISTORY = "select lr_no,article_name,no_of_article,article_value,"
		+ "	actual_weight,art_length,art_breath,art_height,volume_weight,charged_weight,article_desc"
		+ "	from article_details_history where lr_no=?";

	public static final String GET_CR_PRINT_INFO_HISTORY = "select a.cr_no, a.date, a.station_code, a.lr_no, "
		+ "a.demurrage, a.undercharge,a.postage, a.ddextra,a.other,a.total_amount, b.lr_date, "
		+ "b.from_station,a.receivedfrom,b.consignee_name, b.to_station, b.no_of_articles, b.actual_weight, b.bft,"
		+ " b.ddc, b.total, a.status,a.cr_print,b.lr_type,b.consignor_name, "
		+ "b.consignor_address,b.consignee_address, b.consignor_cst, b.consignee_gst, b.article_id,"
		+ "b.article_value, b.article_description, b.charged_weight, b.bft, b.lrc, b.ccc, b.dcc, b.iec, b.lc,"
		+ "b.gsc, b.stax, b.other_charges,b.lr_status, b.createdby, b.createdon  from cash_receipt_history a, lr_history b where  a.station_code=? and"
		+ " b.lr_no = a.lr_no and date(a.date)=? and a.status != 1 ";

	
	/*public String UPDATE_DELIVERY_COMMISSION = " Update lr set dlycommission = ? " +
				" where delivered_station = ? and rate_type != 6 and lr_no = ? ";*/
	public String UPDATE_DELIVERY_COMMISSION = " Update lr set dlycommission = ? " +
		" where lr_no = ? and rate_type != 6 and delivered_station = " +
		" (select station_code from Cash_receipt where lr_no = ? limit 1) ";

	
	/** **************************************************** */

}
