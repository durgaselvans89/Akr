package com.hm.dao;

/**
 * 
 * @author
 */
public interface DAOConstants {

	public static final int BOOKEDSTATUS = 0;

	public static final int CANCELLEDSTATUS = 1;

	public static final String ARRIVED_STATUS = "Arrived";

	public static final String DELIVERED_STATUS = "Delivered";

	public static final String DISPATCH_STATUS = "Dispatched";

	public static final String TOARRIVE_STATUS = "ToArrive";

	public static final String EMPTY_STRING = "";

	public static final String DUPLICATE_ENTRY = "Duplicate entry";

	/** **************Fields********************************* */

	public static final String LR_NO = "lr_no";
	public static final String LR_DATE = "lr_date";
	public static final String FROM_STATION = "from_station";
	public static final String TO_STATION = "to_station";
	public static final String NO_OF_ARTICLES = "no_of_articles";
	public static final String ACTUAL_WEIGHT = "actual_weight";
	public static final String CONSIGNOR_NAME = "consignor_name";
	public static final String CONSIGNEE_NAME = "consignee_name";
	public static final String LR_TYPE = "lr_type";
	public static final String TYPE = "type";
	public static final String CONSIGNOR_ADDRESS = "consignor_address";
	public static final String CONSIGNEE_ADDRESS = "consignee_address";
	public static final String CONSIGNOR_CST = "consignor_CST";
	public static final String CONSIGNEE_GST = "consignee_GST";
	public static final String ARTICLE_ID = "article_id";
	public static final String ARTICLE_VALUE = "article_value";
	public static final String ARTICLE_DESC = "article_description";
	public static final String CHARGED_WEIGHT = "charged_weight";
	public static final String BASIC_FRIEGHT = "bft";
	public static final String LRCHARGES = "lrc";
	public static final String CCC = "ccc";
	public static final String DOOR_DELIVERY_CHARGE = "ddc";
	public static final String DCC = "dcc";
	public static final String DDC = "ddc";
	public static final String IEC = "iec";
	public static final String LC = "lc";
	public static final String GSC = "gsc";
	public static final String SALES_TAX = "stax";
	public static final String OTHER_CHARGES = "other_charges";
	public static final String TOTAL = "total";
	public static final String LR_STATUS = "lr_status";
	public static final String DESC = "desc";
	public static final String RATE_TYPE = "rate_type";
	public static final String CNOR_PHONE = "cnorphone";
	public static final String CNEE_PHONE = "cneephone";
	public static final String CNOR_LANDLINE = "cnorlandline";
	public static final String CNEE_LANDLINE = "cneelandline";
	public static final String SSTAX = "sstax";
	public static final String SMS_NOTIFY = "sms_notification";
	public static final String TOTAL_CHARGED_WT = "total_charged_wt";
	public static final String DHC = "dhc";
	public static final String ISUPD = "isupd";
	public static final String OLDLRNO = "oldLrno";
	public static final String OLDLRTOTAL = "oldLrFrt";
	public static final String DEMU = "demu";
	public static final String POST = "post";
	
	

	public static final String STATION_CODE = "station_code";
	public static final String DISPATCH_TO = "dispatch_to";
	public static final String OUTWARD_TIME = "outward_time";
	public static final String STATION_NAME = "name";
	public static final String CR_NO = "cr_no";
	public static final String MOBILE = "mobile";
	public static final String PHONE1 = "phone1";
	public static final String PHONE2 = "phone2";
	public static final String CR_STATUS = "status";
	public static final String VALUE_PER_KM = "value_per_km";
	public static final String TODAY = "TODAY_DATE";
	public static final String CR_BOOKED_ACT = "CR BOOKED";
	public static final String CR_CANCELLED_ACT = "CR Cancelled";
	public static final String LR_DELIVERY_DATE = "lr_delivery_date";
	public static final String CR_CANCEL_DATE = "cr_cancel_date";

	public static final String BOOKING_TYPE = "booking_type";
	public static final String CREATEDBY = "createdby";
	public static final String CREATEDON = "createdon";
	public static final String INVOICE_NO = "invoice_no";
	public static final String DELIVERED_DATE = "delivered_date";

	public static final String TRACKING_TABLE = "lr_tracking";
	public static final String TRACKING_TABLE_ARCHIVE = "lr_tracking_archive";
	public static final String TRACKING_TABLE_ARCHIVE_HISTORY = "lr_tracking_archive_history";
	
	/** *************************************************** */

	/** ********Fields for table ARTICLE_TYPE ************* */
	public static final String ARTICLE_TYPE = "type";
	public static final String INWARD_TIME = "inward_time";
	public static final String LAST_INWARD_DATE = "last_inward_date";

	/** *************************************************** */

	/** *********Fields for table GMR_IN_TIME************** */
	public static final String VEHICLE_NO = "vehicle_no";
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
	
	public static final String  DISCOUNT="discount";
	public static final String  VEHICLE_MODEL="vehicle_model";

	/** Query ********************************************* */
	
	public static final String CHECK_OLDLR_STATUS = "SELECT a.lr_status,b.from_station,a.inward_time, b.consignor_name,b.consignee_name,b.consignor_address,b.consignee_address,"+
			"b.consignor_cst,b.consignee_gst,cnorphone,cneephone,cnorlandline,cneelandline FROM lr_tracking a,lr b where a.station_code=?"+
			" and a.lr_no = b.lr_no and a.lr_no = ?" ;
	

	public static final String INSERT_QUERY_REBOOK = "insert into lr (lr_no,lr_date,lr_type,from_station,to_station,consignor_name,consignor_address,"
			+ "consignee_name,consignee_address,consignor_CST,consignee_GST,article_id,article_value,no_of_articles,article_description,"
			+ "actual_weight,charged_weight,bft,lrc,ccc,ddc,dcc,iec,lc,gsc,stax,other_charges,total,lr_status,createdby,createdon,art_unit,rate_type,actual_bft,ddcfree,cnorphone,cneephone,total_charged_wt,sms_notification,cnorlandline,cneelandline,bkgcommission,cccommission,sstax,bkgcommission_percent,dhc,`desc`,isupd,oldLrno,oldLrFrt,post,demu)"
			+ " values(?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String INSERT_QUERY = "insert into lr (lr_no,lr_date,lr_type,from_station,to_station,consignor_name,consignor_address,"
		+ "consignee_name,consignee_address,consignor_CST,consignee_GST,article_id,article_value,no_of_articles,article_description,"
		+ "actual_weight,charged_weight,bft,lrc,ccc,ddc,dcc,iec,lc,gsc,stax,other_charges,total,lr_status,createdby,createdon,art_unit,rate_type,actual_bft,ddcfree,cnorphone,cneephone,total_charged_wt,sms_notification,cnorlandline,cneelandline,bkgcommission,cccommission,sstax,bkgcommission_percent,dhc,`desc`)"
		+ " values(?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	
	public static final String CANCEL_LR_QUERY = "update lr l set lr_status = ?,l.desc=?,createdon=now()  where from_station = ? AND lr_no = ? AND lr_status < 1 AND lr_no NOT IN (select A.lr_no from lr_tracking A where A.lr_no = ? and A.lr_status != '"
			+ ARRIVED_STATUS + "' )";

	public static final String CHECK_ISUPD = "select isupd,oldLrno,from_station from lr where lr_no = ? " ;
	
	
	public static final String INSERT_LRTRACK = "insert into lr_tracking (lr_no,station_code,lr_status,vehicle_no,inward_time) values(?,?,?,?,?)";

	public static final String INSERT_MARKET_VEHICLE = "insert into market_vehicle(name,phone,model_no,rate,vehicle_date,from_station,to_station) values(?,?,?,?,?,?,?)";

	public static final String UPDATE_LRTRACK = "UPDATE LR_TRACKING SET LR_STATUS = ?, VEHICLE_NO = ?, driver_name = ?, dispatch_To = ?, outward_time = ? where station_code = ? and lr_no = ? and lr_status = '"
			+ ARRIVED_STATUS + "'";

	public static final String JOIN_INTIMEWITHLR = "SELECT A.STATION_CODE,B.LR_NO,B.LR_DATE,A.INWARD_TIME,"
			+ "CURDATE() AS TODAY_DATE,B.TOTAL,B.DDC,b.article_id,article_value,B.LR_TYPE,B.FROM_STATION,B.TO_STATION,B.ACTUAL_WEIGHT,B.CONSIGNOR_NAME, B.no_of_articles, "
			+ "B.CONSIGNEE_NAME,A.LR_NO,A.VEHICLE_NO,b.cnorphone,b.cneephone,b.rate_type,b.sms_notification FROM LR_TRACKING A,LR B WHERE "
			+ " A.STATION_CODE = ? AND B.LR_STATUS != 1 AND A.LR_NO=B.LR_NO AND A.LR_STATUS = '"
			+ TOARRIVE_STATUS + "'";

	public static final String JOIN_OUTTIMEWITHLR = "SELECT b.ddc,b.total,b.lr_type,b.article_value,b.article_id,A.STATION_CODE,"
			+ "A.INWARD_TIME,B.LR_NO,B.LR_DATE,B.FROM_STATION,B.TO_STATION,B.ACTUAL_WEIGHT,B.NO_OF_ARTICLES,"
			+ "B.CONSIGNOR_NAME,B.CONSIGNEE_NAME,A.LR_NO FROM LR_TRACKING A,LR B WHERE A.STATION_CODE = ? AND B.LR_STATUS != 1 AND"
			+ " A.LR_NO=B.LR_NO AND A.LR_STATUS = '" + ARRIVED_STATUS + "'";

	public static final String JOIN_CRWITHLR = "SELECT b.ddc,b.total,b.lr_type,b.article_value,b.article_id,"
			+ "b.delivered_station,b.last_inward_date,B.LR_NO,B.LR_DATE,B.FROM_STATION,B.TO_STATION,"
			+ "B.ACTUAL_WEIGHT,B.NO_OF_ARTICLES,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME,b.LR_NO FROM LR B "
			+ "WHERE b.delivered_station = ? AND b.LR_STATUS = 2 and B.lr_type='topay' union all "
			+ "SELECT b.ddc,b.total,b.lr_type,b.article_value,b.article_id,b.delivered_station,"
			+ "b.last_inward_date,B.LR_NO,B.LR_DATE,B.FROM_STATION,B.TO_STATION,B.ACTUAL_WEIGHT,"
			+ "B.NO_OF_ARTICLES,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME,b.LR_NO FROM LR B "
			+ "WHERE b.delivered_station = ? AND b.LR_STATUS = 2 and B.lr_type!='topay' and"
			+ " date(B.delivered_date)=curdate()";

	public static final String GET_BOOKINGDETAIL = "select lr_no, lr_date, lr_type, from_station, to_station, "
			+ " consignor_name, consignor_address, consignee_name, consignee_address, consignor_cst, consignee_gst, "
			+ "article_id, article_value, article_description, no_of_articles, actual_weight, charged_weight, bft, lrc, ccc, ddc, dcc, iec, lc,"
			+ "gsc, stax, other_charges, total, lr_status, createdby, createdon,art_unit,a.desc,a.rate_type,a.cnorphone,a.cneephone,a.total_charged_wt,a.cnorlandline,a.cneelandline,a.sstax,a.dhc,a.isupd,a.oldLrno,a.oldLrFrt,a.post,a.demu  from lr a where lr_no = ? ";

	public static final String GET_BOOKINGDETAIL_HISTORY = "select lr_no, lr_date, lr_type, from_station, to_station, "
		+ " consignor_name, consignor_address, consignee_name, consignee_address, consignor_cst, consignee_gst, "
		+ "article_id, article_value, article_description, no_of_articles, actual_weight, charged_weight, bft, lrc, ccc, ddc, dcc, iec, lc,"
		+ "gsc, stax, other_charges, total, lr_status, createdby, createdon,art_unit,a.desc,a.rate_type,a.cnorphone,a.cneephone,a.total_charged_wt,a.cnorlandline,a.cneelandline,a.sstax,a.dhc,a.isupd,a.oldLrno,a.oldLrFrt,a.post,a.demu  from lr_history a where lr_no = ? ";

	
	/*
	 * public static final String GET_ARTICLEDETAIL = "select
	 * lr_no,article_name,no_of_article,article_value," +
	 * "actual_weight,volume_weight,charged_weight,article_desc from
	 * article_details where lr_no=?";
	 */

	public static final String GET_ARTICLEDETAIL = "select lr_no,article_name,no_of_article,article_value,"
			+ "	actual_weight,art_length,art_breath,art_height,volume_weight,charged_weight,article_desc"
			+ "	from article_details where lr_no=?";

	public static final String GET_ARTICLEDETAIL_HISTORY = "select lr_no,article_name,no_of_article,article_value,"
		+ "	actual_weight,art_length,art_breath,art_height,volume_weight,charged_weight,article_desc"
		+ "	from article_details_history where lr_no=?";

	
	public static final String GET_ARTICLE_TYPES = "SELECT article_id,type,value_per_km FROM article_master order by type";

	public static final String DELETE_LRTRACKING = "DELETE FROM LR_TRACKING WHERE LR_NO = ?";

	public static final String ASSIGN_GOODS = "UPDATE LR_TRACKING SET LR_STATUS = '"
			+ ARRIVED_STATUS
			+ "', INWARD_TIME = NOW() WHERE STATION_CODE = ? AND LR_NO = ? AND LR_STATUS = '"
			+ TOARRIVE_STATUS + "'";

	public static final String INSERT_ARTICLES = "insert into article_details values(?,?,?,?,?,?,?,?,?,?,?)";

	public static final String CHECK_CR = "SELECT station_code,status, cr_no, cr_cancel_date, lr_delivery_date from cash_receipt where lr_no = ? order by lr_delivery_date";

	public static final String CHECK_CR_HISTORY = "SELECT station_code,status, cr_no, cr_cancel_date, lr_delivery_date from cash_receipt_history where lr_no = ? order by lr_delivery_date";
	
	public static final String CHECK_DUPLICATE = " SELECT count(*) from lr where lr_no = ? ";

	public static final String GET_USED_LR_NO = "select lr_no,lr_type from lr where from_station = ? " +
			"and lr_type = ? and date(lr_date) >= ? " +
			"union all " +
			"select lr_no,lr_type from lr_history where from_station = ?  and " +
			" lr_type = ? and date(lr_date) >= ? order by 1 ,2 ";

	public static final String GET_USED_CR_NO = "SELECT cr_no FROM cash_receipt where station_code=? ";

	/** **************************************************** */

	public static final String INSERT_COMMODITIES = "INSERT INTO article_master (type,value_per_km)VALUES(?,?)";

	public static final String UPDATE_COMMODITIES = "UPDATE article_master SET value_per_km = ? WHERE type = ?";

	public static final String DELETE_COMMODITIES = "DELETE FROM article_master WHERE article_id = ?";

	public static final String INSERT_SUNDRY_ARTICLE = "INSERT INTO ARTICLE_MASTER (type) VALUE (?)";

	public static final String GET_SUNDRY_ARTICLES = "SELECT ARTICLE_ID, TYPE FROM ARTICLE_MASTER order by type";

	public static final String DELETE_SUNDRY_ARTICLE = "DELETE FROM ARTICLE_MASTER WHERE type = ?";

	public static final String COMMODITIES_REINITIAL = "update article_master set value_per_km = 0 WHERE type = ?";

	public static final String VIEW_BILL_OUTWARD = "Select lr_no, delivered_date, lr_date, from_station, to_station,  "
			+ " no_of_articles, charged_weight, bft, lrc, ccc, ddc, dcc, iec, lc, gsc, stax, other_charges,"
			+ " total, invoice_no from lr where month(delivered_date) = ? and year(delivered_date) = ? "
			+ " and consignor_name = ? and from_station = ?";

	public static final String VIEW_BILL_INWARD = "Select lr_no, delivered_date, lr_date, to_station, from_station,   "
			+ " no_of_articles, charged_weight, bft, lrc, ccc, ddc, dcc, iec, lc, gsc, stax, other_charges,"
			+ " total, invoice_no from lr where month(delivered_date) = ? and year(delivered_date) = ? "
			+ " and consignee_name = ? and to_station = ?";

	public static final String INSERT_SMS = "insert into pending_sms values(?,?,now())";

	/** ***********Error Message *************************** */
	public static final String CANCELLATION_ERROR = "LR can't be cancelled as it is already dispatched";
	public static final String RECORD_NOT_AVAILABLE = "LR Number does not exist";
	public static final String DUPLICATE_ERROR = "LR Number already exists. Please Use a different LR Number";
	/** **************************************************** */

	public static final String GET_SPECIAL_DISCOUNTER = "SELECT d.discount FROM discounter d where "
			+ "from_station=? and to_station=?";
	
	//Un used LR list
	
	public static final String  GET_VEHICLES="SELECT vehicle_model FROM vehicle";
	
	public static final String GET_UN_USED_LR_LIST = "Select lr_no,type from stationary_allocated " +
			" where station_code = ? ";
	
	public static final String GET_STATIONARY_LIMIT = "SELECT (substring(end_number,2) - " +
			" (substring(start_number,2)))/2 FROM lr_stock_allocation where station_code = ? and type = ? " +
			" order by allocation_date desc limit 1 ";

	public static final String GET_AVAIL_STATIONARY_COUNT = "SELECT count(*) FROM stationary_allocated " +
			"where station_code = ? and type = ? ";
	
	public static final String GET_OLDLR_GMRO_DETAILS = " select a.from_station as from_station,b.inward_time as inward_time ,a.consignee_name as consignee_name from lr a,lr_tracking b where" +
	" a.lr_no = b.lr_no and a.lr_no =? and b.station_code = ? ";

}
