package com.hm.dao;

/**
 * 
 * @author
 */
public interface DAOConstants {

	/** **************Fields********************************* */

	public static final String STATION_CODE = "station_code";

	public static final String STATION_NAME = "name";

	public static final String TYPE = "type";

	public static final String ADDRESS = "address";

	public static final String CITY = "city";

	public static final String STATE = "state";

	public static final String PIN = "pin";

	public static final String MOBILE = "mobile";

	public static final String PHONE1 = "phone1";

	public static final String PHONE2 = "phone2";

	public static final String OWNER = "owner";

	public static final String DISCOUNT = "discount";

	public static final String BRANCH_CODE = "branch_code";

	public static final String TOPAY = "topay";

	public static final String PAID = "paid";

	public static final String BILLING = "billing";

	public static final String CR = "cr";

	public static final String FROM_STATION = "from_station";

	public static final String TO_STATION = "to_station";

	public static final String DISTANCE = "distance";

	public static final String INSERT_DISTANCE = "insert into distance (from_station,to_station,distance)values(?,?,?)";

	public static final String UPDATE_DISTANCE = "update distance set distance = ? ,  card_rate = ? where (to_station = ? and from_station = ?)or(to_station = ? and from_station = ?)";

	public static final String BFINCREMENT = "bf_increment";

	public static final String BPI = "card_rate";

	public static final String LRCHARGE = "lr_charge";

	public static final String GSC = "gsc";

	public static final String MIN_FREIGHT = "min_freight_value";

	public static final String MIN_WEIGHT = "min_weight_value";

	public static final String CC_LIMIT = "cc_limit";

	public static final String CC_SPECIAL = "ccforspecial";

	public static final String CC_COMMODITY = "ccforcommodity";

	public static final String CC_HLC = "ccperarticle";

	public static final String CARD_RATE = "card_rate";

	public static final String PROFILE_NAME = "profile_name";

	public static final String CC_COMMISSION = "cc_commission";

	public static final String CC_CONSIDER = "cc_consider";

	public static final String CC_REFUND = "cc_refund";

	public static final String REFUND_ARTICLE = "refundperarticle";

	public static final String DC_COMMISSION = "dc_commission";

	public static final String Delivery_COMMISSION = "delivery_commission";

	public static final String LANGUAGE = "language";

	public static final String EQUALS_CARD_RATE = "equals_card_rate";

	public static final String ABOVE_CARD_RATE = "above_card_rate";

	public static final String UPTO_20__CARD_RATE = "upto_20";

	public static final String ABOVE_20_CARD_RATE = "above_20";

	public static final String LR_TYPE = "lr_type";

	public static final String DHC = "dhc";

	/** *************************************************** */

	/** Query ********************************************* */

	public static final String ADD_STATION = "insert into station (station_code,name,type,address,city,state,pin,mobile,phone1,phone2,owner,branch,discount) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String DEL_STATION = "delete from station where station_code = ? ";

	public static final String ADD_BRANCH = "insert into branch (branch_code,name,address,city,state,pin,mobile,phone1,phone2,owner,region,discount) values (?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String DEL_BRANCH = "delete from branch where branch_code = ? ";

	public static final String ADD_REGION = "insert into region (region_code,name,address,city,state,pin,mobile,phone1,phone2,owner) values (?,?,?,?,?,?,?,?,?,?)";

	public static final String DEL_REGION = "delete from region where region_code = ? ";

	/*
	 * public static final String GET_STATIONS = "select
	 * a.station_code,a.name,a.type,a.address,a.city,a.state," +
	 * "a.pin,a.mobile,a.phone1,a.phone2,a.owner,a.discount,a.bf_increment,a.branch_code,b.card_rate," +
	 * "b.lr_Charge,b.gsc,b.min_freight_value,b.min_weight_value, a.cc_limit,
	 * a.ccforspecial, a.ccforcommodity,a.ccperarticle from station " + "a left
	 * outer join bft_quotation b on a.station_code = b.station_id group by
	 * station_code " + "order by a.name";
	 */

	public static final String GET_STATIONS = "select a.station_code,a.name,a.type,a.mobile,a.phone1,a.owner,"
			+ "a.discount,a.bf_increment,a.branch_code,b.card_rate,b.lr_Charge,b.dhc,b.gsc,b.min_freight_value,"
			+ "b.min_weight_value, a.cc_limit, a.ccforspecial,a.ccforcommodity,a.ccperarticle,a.language,a.cc_consider,"
			+ "a.cc_refund,a.refundperarticle,a.dc_commission, a.state, a.city, a.address from station a,"
			+ "bft_quotation b where  a.station_code = b.station_id group by station_code order by a.name";

	public static final String GET_BRANCHES = " select * from branch ";

	public static final String GET_SELECTED_STATIONS = " select s.station_code,s.name from branch b,station s where "
			+ " b.name=(SELECT a.name FROM branch a WHERE a.branch_code = ?) "
			+ " and b.branch_code = s.station_code";

	public static final String GET_TR = "select region_code, name from region";

	public static final String INSERT_LR_RANGE = "insert into lr_stock_allocation (station_code, allocation_date, start_number, end_number, batch_id, type) values (?, NOW(), ?, ?, ?, ?)";

	public static final String GET_BATCH_ID = "select count(*) from lr_stock_allocation where station_code = ? and type = ? ";

	// public static final String GET_LR_RANGE = " select station_code, type,
	// start_number, end_number, batch_id from lr_stock_allocation ";

	public static final String GET_LR_RANGE = "(select station_code, type,allocation_date, start_number, "
			+ "end_number, batch_id	from lr_stock_allocation where station_code=? and type='billing' "
			+ "order by batch_id desc limit 3)"
			+ "union all"
			+ "	(select station_code, type,allocation_date, start_number, end_number, batch_id "
			+ "from lr_stock_allocation where station_code=? and  type='paid'	order by batch_id desc limit 3)"
			+ "union all "
			+ "(select station_code, type,allocation_date, start_number, end_number, batch_id	from "
			+ "lr_stock_allocation where station_code=? and  type='topay'	order by batch_id desc limit 3)"
			+ " order by type desc,allocation_date";

	public static final String INSERT_STATIONARY = "insert into stationary_settings (station_code, topay, paid, billing, cr, branch_code) values (?, ?, ?, ?, ?, ?)";

	public static final String DELETE_STATIONARY = "delete from stationary_settings where branch_code = ? ";

	public static final String GET_STATIONARY = "select branch_code, station_code, topay, paid, billing, cr from stationary_settings ";

	public static final String CHECK_STATIONARY = "select count(*) from stationary_settings where branch_code = ? ";
	public static final String UPDATE_BF_DECREMENT = "UPDATE station SET discount = ? WHERE station_code = ?";

	public static final String UPDATE_BF_INCREMENT = "UPDATE station SET bf_increment = ? WHERE station_code = ?";

	public static final String GET_DISTANCE_LIST = "SELECT from_station,to_station,distance,card_rate FROM distance WHERE from_station = ? OR to_station = ?";

	public static final String GET_MANAGE_REGULAR_SUNDRY = "SELECT station_code,bf_increment,discount FROM station GROUP BY station_code";

	public static final String UPDATE_MANAGE_REGULAR_SUNDRY = "UPDATE station SET bf_increment = ?,discount = ? WHERE station_code = ? ";

	public static final String UPDATE_ALARM_SETTINGS = "UPDATE stationary_alarm_settings SET topay = ? , paid = ?, billing = ?, cr = ? WHERE station_code = ? ";

	public static final String UPDATE_DRSFINE = "UPDATE station SET drs_duration = ?,drs_fine = ? WHERE station_code = ?";

	public static final String UPDATE_CC_LIMIT = "UPDATE station SET cc_limit = ? WHERE station_code = ?";

	public static final String UPDATE_MIN_WEIGHT = "UPDATE bft_quotation set min_weight_value=? where quotation_id = 'SUNDRY' and station_id = ?";

	public static final String UPDATE_MIN_FREIGHT = "UPDATE bft_quotation set min_freight_value=? where quotation_id = 'SUNDRY' and station_id = ?";

	public static final String GET_MANAGE_COMMISSION = "SELECT s.station_code,c.cc_limit,c.cc_consider,c.cc_refund,d.delivery_commission," +
														"s.profile_name FROM station s , cc_profile c, " +
														"dc_profile d where c.profile_name=s.cc_profile_name " +
														"and d.profile_name=s.dc_profile_name " +
														"order by s.station_code";

	public static final String UPDATE_MANAGE_COMMISSION = "UPDATE station set profile_name = ?, dc_commission = ?, cc_limit = ? , cc_consider = ?, cc_refund = ?  where station_code = ?";

	public static final String UPDATE_CC_SPECIAL = "UPDATE station SET ccforspecial = ? WHERE station_code = ?";

	public static final String UPDATE_CC_COMMODITY = "UPDATE station SET ccforcommodity = ? WHERE station_code = ?";

	public static final String UPDATE_CC_HLC_CUSTOMER = "UPDATE customer SET ccperarticle = ?, refundperarticle = ?  WHERE name = ?";

	public static final String UPDATE_ALL_CC_HLC_CUSTOMER = "UPDATE customer SET ccperarticle = ?, refundperarticle = ?  WHERE station_code = ? and type_of_customer = 'consignor'";

	public static final String UPDATE_ALL_CC_HLC_STATION = "UPDATE station SET ccperarticle = ?, refundperarticle = ?  WHERE station_code = ?";

	public static final String GET_STATIONARY_REPORT = "Select station_code 'station_code', topay 'topay' , paid 'paid', billing 'billing', cr 'cr'"
			+ "  from stationary_alarm_report";

	public static final String UPDATE_LANGUAGE = "UPDATE station SET language = ? WHERE station_code = ?";

	public static final String DEL_STATIONARY_REPORT = "delete from stationary_alarm_report where station_code=?";

	public static final String GET_CARD_RATE_PROFILE = "Select a.equals_card_rate,a.above_card_rate,a.upto_20,"
			+ "a.above_20,a.lr_type,b.station_code,a.profile_name  from booking_commission a,station b where "
			+ "a.profile_name = b.profile_name";

	public static final String GET_CC_PROFILE_DETAILS = "Select a.station_code,c.cc_consider,c.cc_refund,c.cc_limit,c.ccforspecial,"
			+ "c.ccforcommodity from station a,cc_profile c where a.cc_profile_name=c.profile_name";

	public static final String GET_DC_PROFILE_DETAILS = "select a.station_code,b.delivery_commission from "
			+ "station a,dc_profile b where a.dc_profile_name=b.profile_name";

	public static final String GET_HLC_REFUND = " select refundperarticle from customer c where c.name=? and "
			+ " c.station_code=?";

	
	//incrementer
	public static final String GET_INCREMENTER = "select increment from incrementer where from_station = ? and to_station = ? ";
	
	public static final String HOLIDAY_ENTRY = "insert into holiday_entry(holiday_date,holiday_desc,created_date)values(?,?,CURDATE())";
	/** **************************************************** */

}
