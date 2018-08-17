package com.hm.dao;

/**
 * 
 * @author Hakuna Matata
 */
public interface DAOConstants {

	public static final int BOOKEDSTATUS = 0;
	public static final int CANCELLEDSTATUS = 1;
	public static final String EMPTY_STRING = "";
	public static final String DUPLICATE_ENTRY = "Duplicate entry";

	/** **************Fields********************************* */

	public static final String MAC_ID = "mac_id";
	public static final String PASSWORD = "password";
	public static final String DELIVERED_STATUS = "Delivered";
	public static final String DISPATCH_STATUS = "Dispatched";
	public static final String STATION_CODE = "station_code";
	public static final String STATION_NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CONTACTS = "contacts";
	public static final String AMOUNT = "amount";
	public static final String RR_DATE = "rr_date";
	public static final String RR_CREATED_DATE = "created_on";
	public static final String INSTALLMENT = "installment";
	public static final String RR_ID = "id";
	public static final String RECOVERY_FLAG = "recovery_flag";
	public static final String PROFILE_ID = "profile_id";
	public static final String PROFILE_NAME = "profile_name";
	public static final String CC_COMMISSION = "cc_commission";
	public static final String CCCOMMISSIONS = "cccommission";
	public static final String DC_COMMISSION = "dc_commission";
	public static final String LR_NO = "lr_no";
	public static final String LR_TYPE = "lr_type";
	public static final String MODE = "lr_type";
	public static final String BFT = "bft";
	public static final String CCCHARGES = "ccc";
	public static final String ABOVE_CARD_RATE = "above_card_rate";
	public static final String EQUALS_CARD_RATE = "equals_card_rate";
	public static final String UPTO_20 = "upto_20";
	public static final String ABOVE_20 = "above_20";
	public static final String FROM_STATION = "from_station";
	public static final String TO_STATION = "to_station";
	public static final String TOTAL_AMOUNT = "total_amount";
	public static final String ACTUAL_WEIGHT = "actual_weight";
	public static final String CHARGED_WEIGHT = "charged_weight";
	public static final String TOTAL_WEIGHT = "total_weight";
	public static final String DELIVERED_DATE = "outward_time";
	public static final String BOOKING_COMMISSIONS = "bkgcommission";
	public static final String BOOKING_COMM_PERCENT = "bkgcommission_percent";
	public static final String BOOKING_COMMISSION = "booking_commission";
	public static final String DELIVERY_COMMISSION = "DCC";
	public static final String BOOKED_DATE = "booked_date";
	public static final String LR_DATE = "lr_date";
	public static final String RATE_TYPE = "rate_type";
	public static final String ACTUAL_BFT = "actual_bft";
	public static final String STATUS = "status";
	public static final String OUTWARD_TIME = "outward_time";
	public static final String INWARD_TIME = "inward_time";
	public static final String BKGCOMMISSION = "Bkgcommission";
	public static final String CCCOMMISSION = "Cccommission";
	public static final String DLYCOMMISSION = "Dlycommission";
	public static final String RECOVERY = "Recovery";
	public static final String REFUND = "Refund";
	public static final String TOTAL = "Total";
	public static final String BOOKING = "bkg_commission";
	public static final String DELIVERY = "dlv_commission";
	public static final String DELV_DATE = "delivered_date";
	public static final String DDC = "ddc";
	public static final String DDEXTRA = "ddextra";
	public static final String OTHER = "other_charges";
	public static final String DEMURRAGE = "demurrage";
	public static final String UNDERCHARGE = "undercharge";
	public static final String POSTAGE = "postage";
	public static final String DDCFREE = "ddcfree";
	public static final String DC = "delivery_commission";

	/** *************************************************** */

	/** Query ********************************************* */

	public static final String UPDATE_PASSWORD = "UPDATE station SET password = ? where station_code = ?";
	public static final String GET_PASSWORD = "SELECT password from station where station_code = ? ";

	public static final String CREATE_PROFILE = "INSERT INTO booking_commission(profile_id,profile_name,lr_type,above_card_rate,equals_card_rate,upto_20,above_20)VALUES(?,?,?,?,?,?,?)";
	public static final String CREATE_CC_PROFILE = "INSERT INTO cc_profile(profile_name,cc_limit,cc_consider,cc_refund,ccforspecial,ccforcommodity)VALUES(?,?,?,?,?,?)";
	public static final String UPDATE_PROFILE = "UPDATE booking_commission SET "
			+ "above_card_rate = ?,equals_card_rate = ?,upto_20 = ?,above_20 = ?"
			+ " WHERE  profile_name = ? and lr_type = ?";

	public static final String UPDATE_CC_PROFILE = "UPDATE cc_profile SET "
		+ "cc_limit = ?,cc_consider = ?,cc_refund = ?,ccforspecial = ?, ccforcommodity = ? "
		+ " WHERE  profile_name = ? ";
	
	public static final String IS_PROFILE_EXIST = "SELECT 2 FROM STATION WHERE profile_name = ?";
	public static final String DELETE_PROFILE = "DELETE FROM booking_commission WHERE profile_id = ?";
	public static final String GET_PROFILE_LIST = "SELECT profile_id,profile_name FROM booking_commission";
	public static final String GET_CARD_PROFILE = "SELECT lr_type,above_card_rate,equals_card_rate,upto_20,above_20 FROM booking_commission";
	public static final String GET_PROFILE = "SELECT profile_id,profile_name FROM booking_commission WHERE profile_name = ?";
	public static final String GET_CARD = "SELECT lr_type,above_card_rate,equals_card_rate,upto_20,above_20 FROM booking_commission WHERE profile_name = ?";
	public static final String GET_DISTINCT_PROFILE = "SELECT distinct(profile_name) FROM booking_commission";

	public static final String IS_CC_PROFILE_EXIST = "SELECT 2 FROM STATION WHERE cc_profile_name = ?";
	public static final String GET_DISTINCT_CC_PROFILE = "SELECT distinct(profile_name) FROM cc_profile";
	public static final String GET_CC_PROFILE = "SELECT * FROM cc_profile WHERE profile_name = ?";
	public static final String GET_CC_PROFILE_LIST = "SELECT * FROM cc_profile";
	public static final String DELETE_CC_PROFILE = "DELETE FROM cc_profile WHERE profile_name = ?";
	public static final String UPDATE_CC_PROFILE_COMMISSION = "UPDATE station SET cc_profile_name = ? where station_code = ?";
	
	//DC
	public static final String GET_DC_PROFILE = "SELECT * FROM dc_profile WHERE profile_name = ?";
	public static final String UPDATE_DC_PROFILE = "UPDATE dc_profile SET "
											+ " delivery_commission = ? WHERE  profile_name = ? ";
	public static final String CREATE_DC_PROFILE = "INSERT INTO dc_profile(profile_name,delivery_commission)VALUES(?,?)";
	public static final String GET_DC_PROFILE_LIST = "SELECT * FROM dc_profile";
	public static final String GET_DISTINCT_DC_PROFILE = "SELECT distinct(profile_name) FROM dc_profile";	
	public static final String UPDATE_DC_PROFILE_COMMISSION = "UPDATE station SET dc_profile_name = ? where station_code = ?";
	public static final String DELETE_DC_PROFILE = "DELETE FROM dc_profile WHERE profile_name = ?";
	public static final String IS_DC_PROFILE_EXIST = "SELECT 2 FROM STATION WHERE dc_profile_name = ?";
	// Admin module booking commission
	public static final String UPDATE_BOOKING_COMMISSION = "UPDATE station SET profile_name = ? where station_code = ?";
	public static final String GET_BOOKING_COMMISSION = "SELECT profile_name from station where station_code = ? ";
	public static final String GET_DISTINCT_BOOKING_COMMISSION = "SELECT distinct(profile_name) from station";
	public static final String GET_BOOKING_COMMISSION_LIST = "SELECT station_code,name,profile_name FROM station";

	public static final String BOOKING_COMMISSION_EFFECTIVE_DATE = "insert into pending_profile (station_code,parameter,new_value,effective_date) values (?,'BOOKINGPROFILE',?,?)";

	// Admin module consider refund
	public static final String UPDATE_CC_CONSIDER_REFUND = "UPDATE station SET cc_consider = ?, cc_refund = ?, cc_limit = ?  where station_code = ?";

	// client side booking commission
	public static final String GET_BOOKING_COMMISSIONS_LIST = "SELECT "
			+ " a.lr_no,a.lr_date,a.lr_type, round(a.bft,2) as bft,round(a.bkgcommission,2) as bkgcommission, (a.bft/a.actual_bft) as cardRate, a.rate_type, a.bkgcommission_percent FROM lr a,station b"
			+ " WHERE a.lr_status != 1 and "
			+ " a.from_station = b.station_code AND a.from_station = ? AND MONTH(a.lr_date) = ? AND YEAR(a.lr_date) = ? and bkgcommission is not null order by lr_date";

	public static final String GET_BOOKING_COMMISSIONS_LIST_HISTORY = "SELECT "
		+ " a.lr_no,a.lr_date,a.lr_type, round(a.bft,2) as bft,round(a.bkgcommission,2) as bkgcommission, (a.bft/a.actual_bft) as cardRate, a.rate_type, a.bkgcommission_percent FROM lr_history a,station b"
		+ " WHERE a.lr_status != 1 and "
		+ " a.from_station = b.station_code AND a.from_station = ? AND MONTH(a.lr_date) = ? AND YEAR(a.lr_date) = ? and bkgcommission is not null order by lr_date";

	
	public static final String CREATE_REFUND = "INSERT INTO rr_register(station_code,description,contacts,recovery_flag,rr_date,amount,installment,created_on)VALUES(?,?,?,0,now()+interval ? month,?,?,now())";
	public static final String UPDATE_REFUND = "UPDATE rr_register SET station_code,description,amount,contacts,recovery_flag,rr_date WHERE id = ? and recovery_flag = 0";
	public static final String DELETE_REFUND = "DELETE FROM rr_register WHERE id = ? and recovery_flag = 0";
	public static final String GET_REFUND_COLLECTION = "SELECT id,station_code,description,amount,contacts,recovery_flag,created_on FROM rr_register WHERE station_code = ? and rr_date = ? and rcovery_flag = 0";
	public static final String GET_REFUND_COLLECTION_LIST = "SELECT id,station_code,description,round(amount,2) as amount,contacts,recovery_flag,created_on,installment FROM "
			+ "rr_register WHERE station_code = ? and date(rr_date) between ? and ? and recovery_flag = 0";

	public static final String CREATE_RECOVERY = "INSERT INTO rr_register(station_code,description,contacts,recovery_flag,rr_date,amount,installment,created_on)VALUES(?,?,?,1,now()+interval ? month,?,?,now())";
	public static final String UPDATE_RECOVERY = "UPDATE rr_register SET station_code,description,contacts,amount,recovery_flag,rr_date WHERE id = ? and recovery_flag = 1";
	public static final String DELETE_RECOVERY = "DELETE FROM rr_register WHERE id =? and recovery_flag = 1";
	public static final String GET_RECOVERY_COLLECTION = "SELECT id,station_code,description,amount,contacts,recovery_flag,rr_date FROM rr_register WHERE station_code = ? and rr_date = ? and recovery_flag = 1";
	public static final String GET_RECOVERY_COLLECTION_LIST = "SELECT id,station_code,description,round(amount,2) as amount,contacts,recovery_flag,created_on,installment FROM rr_register WHERE station_code = ? and date(rr_date) between ? and ? and recovery_flag = 1";

	// Admin side Cartage commission
	public static final String UPDATE_CC_COMMISSION = "UPDATE station SET CC_COMMISSION = ? where station_code = ?";
	public static final String GET_CC_COMMISSION = "SELECT round(CC_COMMISSION,2) as CC_COMMISSION from station where station_code = ? ";
	public static final String GET_DISTINCT_CC_COMMISSION = "SELECT distinct(CC_COMMISSION) from station";
	public static final String GET_CC_COMMISSION_LIST = "SELECT station_code,name,round(cc_commission,2) as CC_COMMISSION FROM station";
	// Client side Cartage Commission
	public static final String GET_CARTAGE_COMMISSION_LIST = "SELECT "
			+ " a.lr_no,round(a.bft,2) as bft,a.lr_date,round(a.ccc,2) as ccc,a.lr_type,round(a.cccommission,2) as cccommission,round(b.cc_commission,2) as ccPercent FROM lr a,station b"
			+ " WHERE a.lr_status != 1 and "
			+ " a.from_station = b.station_code AND a.from_station = ? AND MONTH(a.lr_date) = ? AND YEAR(a.lr_date) = ? and cccommission is not null order by lr_date";

	public static final String GET_CARTAGE_COMMISSION_LIST_HISTORY = "SELECT "
		+ " a.lr_no,round(a.bft,2) as bft,a.lr_date,round(a.ccc,2) as ccc,a.lr_type,round(a.cccommission,2) as cccommission,round(b.cc_commission,2) as ccPercent FROM lr_history a,station b"
		+ " WHERE a.lr_status != 1 and "
		+ " a.from_station = b.station_code AND a.from_station = ? AND MONTH(a.lr_date) = ? AND YEAR(a.lr_date) = ? and cccommission is not null order by lr_date";

	public static final String GET_DDDETAILS = "SELECT "
			+ " a.lr_no , a.lr_date , a.lr_type, "
			+ " round(a.ddc,2) as ddc,round(a.ddcfree,2) as ddcfree,round(a.total,2) as total,"
			+ " round(b.ddextra,2) as ddextra,round(b.other,2) as other_charges,round(b.demurrage,2) as demurrage,round(b.undercharge,2) as undercharge,round(b.postage,2) as postage "
			+ " FROM "
			+ " lr a, cash_receipt b "
			+ " WHERE a.delivered_station = ? and  a.lr_type = 'TOPAY' and a.lr_status!=1 and rate_type!=6 "
			+ "and b.station_code = a.delivered_station"
			+ " and b.status != 1  and MONTH(b.date) = ? and YEAR(b.date) = ? and a.lr_no = b.lr_no "
			+ " UNION "
			+ " SELECT "
			+ " a.lr_no , a.lr_date , a.lr_type, "
			+ " round(a.ddc,2) as ddc,round(a.ddcfree,2) as ddcfree,round(a.total,2) as total,"
			+ " round(b.ddextra,2) as ddextra,round(b.other,2) as other_charges,round(b.demurrage,2) as demurrage,round(b.undercharge,2) as undercharge,round(b.postage,2) as postage "
			+ " FROM "
			+ " lr a left join cash_receipt b on a.lr_no = b.lr_no "
			+ " WHERE "
			+ " a.delivered_station = ? and a.lr_status = 2 and a.lr_type != 'TOPAY' and a.lr_status!=1 and rate_type!=6 and "
			+ " MONTH(b.date) = ? and YEAR(b.date) = ?";
	
	public static final String GET_DDDETAILS_HISTORY = "SELECT "
		+ " a.lr_no , a.lr_date , a.lr_type, "
		+ " round(a.ddc,2) as ddc,round(a.ddcfree,2) as ddcfree,round(a.total,2) as total,"
		+ " round(b.ddextra,2) as ddextra,round(b.other,2) as other_charges,round(b.demurrage,2) as demurrage,round(b.undercharge,2) as undercharge,round(b.postage,2) as postage "
		+ " FROM "
		+ " lr_history a, cash_receipt_history b "
		+ " WHERE a.delivered_station = ? and  a.lr_type = 'TOPAY' and a.lr_status!=1 and rate_type!=6 "
		+ "and b.station_code = a.delivered_station"
		+ " and b.status != 1  and MONTH(b.date) = ? and YEAR(b.date) = ? and a.lr_no = b.lr_no "
		+ " UNION "
		+ " SELECT "
		+ " a.lr_no , a.lr_date , a.lr_type, "
		+ " round(a.ddc,2) as ddc,round(a.ddcfree,2) as ddcfree,round(a.total,2) as total,"
		+ " round(b.ddextra,2) as ddextra,round(b.other,2) as other_charges,round(b.demurrage,2) as demurrage,round(b.undercharge,2) as undercharge,round(b.postage,2) as postage "
		+ " FROM "
		+ " lr_history a left join cash_receipt_history b on a.lr_no = b.lr_no "
		+ " WHERE "
		+ " a.delivered_station = ? and a.lr_status = 2 and a.lr_type != 'TOPAY' and a.lr_status!=1 and rate_type!=6 and "
		+ " MONTH(b.date) = ? and YEAR(b.date) = ?";

	// Admin side Delivery Commission
	public static final String UPDATE_DC_COMMISSION = "UPDATE station SET DC_COMMISSION = ? where station_code = ?";
	public static final String GET_DC_COMMISSION = "SELECT round(DC_COMMISSION,2) as DC_COMMISSION from station where station_code = ? ";
	public static final String GET_DISTINCT_DC_COMMISSION = "SELECT distinct(DC_COMMISSION) FROM station";
	public static final String GET_DC_COMMISSION_LIST = "SELECT station_code,name,dc_commission FROM station";
	// client side Delivery Commission
	public static final String GET_DELIVERY_COMMISSION_LIST = "SELECT a.lr_no, a.delivered_date,a.lr_date,"
		   + "a.rate_type,a.lr_type, a.actual_weight, a.dlycommission as"
		   + " DCC,a.ddcfree FROM lr a  WHERE a.delivered_station = ? AND MONTH(a.delivered_date) = ? "
		   + " AND YEAR(a.delivered_date) = ? and a.lr_status = 3 and rate_type != 6 and rate_type is not null "
		   + "order by a.delivered_date";
	public static final String GET_DELIVERY_COMMISSION_LIST_HISTORY = "SELECT a.lr_no, a.delivered_date,a.lr_date,"
		   + "a.rate_type,a.lr_type, a.actual_weight, a.dlycommission as"
		   + " DCC,a.ddcfree FROM lr_history a  WHERE a.delivered_station = ? AND MONTH(a.delivered_date) = ? "
		   + " AND YEAR(a.delivered_date) = ? and a.lr_status = 3 and rate_type != 6 and rate_type is not null "
		   + "order by a.delivered_date";
	/** **************************************************** */
	public static final String GET_ADMIN_VIEW = "select b.station_code,b.name,round(a.bkg_commission,2) as bkg_commission,round(a.dlv_commission,2) as dlv_commission ,"
			+ "round(a.refund,2) as refund,round(a.recovery,2) as recovery,round(a.cc_commission,2) as cc_commission from station_commission_view a,station b "
			+ "where b.branch_code like ? and a.smonth = ? and a.syear=?  and a.Station_code=b.station_code";

	/** ***********Error Message *************************** */
	public static final String CANCELLATION_ERROR = "StationCode can't be cancelled";
	public static final String RECORD_NOT_AVAILABLE = "StationCode does not exist";
	public static final String DUPLICATE_ERROR = "StationCode already exists. Please Use a different StationCode";
	/** **************************************************** */

}
