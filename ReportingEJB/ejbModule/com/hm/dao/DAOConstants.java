package com.hm.dao;

/**
 * 
 * @author Hakuna Matata
 */
public interface DAOConstants {

	
	
	
	public static final String CHECK_OLDLR_TOTAL = "select total,lr_type from lr where lr_no=?" ;
	
	public static final String DELIVERED_STATUS = "Delivered";
	public static final String DISPATCH_STATUS = "Dispatched";

	public static final String INSERT_DRS = "insert into Daily_remittance ("
			+ "dr_no,station_code,paid_collection_amount,topay_collection_amount,"
			+ "add_remittance,less_remittance,dd_reimbursement,grand_total,status,dr_date) values(?,?,?,?,?,?,?,?,?,?)";

	public static final String UPDATE_DRS = "UPDATE "
			+ "Daily_remittance "
			+ "SET "
			+ "paid_collection_amount = ?, topay_collection_amount = ?, add_remittance = ?,"
			+ "less_remittance = ?, dd_reimbursement = ?, grand_total = ?, status = ? "
			+ "WHERE " + "dr_no = ?";

	public static final String UPDATE_MEASUREMENTS = "update measurements set inch=?,feet=?,cm=?";

	public static final String GET_DRS = "SELECT "
			+ "dr_date, station_code, paid_collection_amount, topay_collection_amount, add_remittance,"
			+ "less_remittance, dd_reimbursement, grand_total, status "
			+ "FROM Daily_remittance WHERE dr_no = ? and date(dr_date) = ? ";
	
	//public static final String IS_DRS_CONFIRMED = "SELECT status FROM Daily_remittance WHERE station_code = ? " +
		//	" and date(date_sub(dr_date, interval 1 day)) = ?";

	public static final String IS_DRS_CONFIRMED = "SELECT status FROM Daily_remittance WHERE station_code = ? " +
	" and date(dr_date) = ?";


	public static final String ST_BOOKING = "select lr_status,"
			+ "lr_no,lr_date,lr_type,to_station,no_of_articles,actual_weight,bft,ccc,other_charges,ddc,"
			+ "total, lrc, dcc, iec, lc, gsc, stax from lr "
			+ "where from_station = ? and date(lr_date) = ? order by "
			+ "SUBSTR(lr_no,2)";

	
	public static final String ST_BOOKING_UNION = "select lr_status,"
		+ "lr_no,lr_date,lr_type,to_station,no_of_articles,actual_weight,bft,ccc,other_charges,ddc,"
		+ "total, lrc, dcc, iec, lc, gsc, stax from lr "
		+ "where from_station = ? and date(lr_date) = ? order by "
		+ "SUBSTR(lr_no,2)" +
		" union " +
		"select lr_status,"
		+ "lr_no,lr_date,lr_type,to_station,no_of_articles,actual_weight,bft,ccc,other_charges,ddc,"
		+ "total, lrc, dcc, iec, lc, gsc, stax from lr_history "
		+ "where from_station = ? and date(lr_date) = ? order by "
		+ "SUBSTR(lr_no,2)";
		
	public static final String ST_BOOKING_HISTORY = "select lr_status,"
		+ "lr_no,lr_date,lr_type,to_station,no_of_articles,actual_weight,bft,ccc,other_charges,ddc,"
		+ "total, lrc, dcc, iec, lc, gsc, stax from lr_history "
		+ "where from_station = ? and date(lr_date) = ? order by "
		+ "SUBSTR(lr_no,2)";
	
	public static final String BRANCH_CODE = "branch_code";

	public static final String GET_VERSION = "select A.station_code,A.version_id,B.branch_code from mac_info A,station"
			+ " B where A.station_code=B.station_code order by B.branch_code";

	public static final String GET_DAILY_STATUS = "SELECT A.ds_no,b.branch_code,A.station_code,A.name,"
			+ "A.topay,A.paid,A.billing,A.gmr_intime,A.gmr_outtime,A.cr  FROM daily_status A,"
			+ "station B where a.station_code=b.station_code order by b.branch_code";

	/*
	 * public static final String GET_OUTSTANDING = "Select delivered_station as
	 * station,lr_type,ddc," + "total from lr where date(delivered_date)=? " +
	 * "and lr_status=2 and lr_type='topay'" + "union all select from_station as
	 * station ,lr_type,ddc,total from lr " + "where date(lr_date)=? and
	 * lr_type='paid'";
	 */

	public static final String GET_OUTSTANDING = "Select b.from_station as sourcestation, a.station_code as "
			+ "currentstation, b.lr_no as lrno, b.ddc as ddc, b.total as total, b.lr_type as lr_type, 1 as "
			+ "lrstatus    from lr_tracking a , lr b  where a.lr_status != 'Dispatched'"
			+ "  and b.lr_status =0  and date(inward_time)!=curdate() and b.lr_type = 'Topay' "
			+ "and a.lr_no=b.lr_no and b.from_station<>a.station_code "
			+ "union all "
			+ "Select a.from_station as sourcestation, a.delivered_Station as currentstation , a.lr_no as lrno, "
			+ "a.ddc as ddc, a.total as total,a.lr_type as lrtype,1 as lrstatus   from lr a "
			+ "where a.lr_status = 2  and a.lr_type = 'TOPAY' and "
			+ "date(last_inward_date)!=curdate() and a.from_station!=a.delivered_Station "
			+ "union all"
			+ " Select b.from_station as sourcestation, a.station_code as currentstation,"
			+ "b.lr_no as lrno, b.ddc as ddc, b.total as total, b.lr_type as lr_type, 1 as lrstatus"
			+ " from lr_tracking a , lr b where a.lr_status = 'Arrived'  and b.lr_status =0"
			+ " and b.lr_type = 'Paid' and a.lr_no=b.lr_no and b.from_station=a.station_code"
			+ " and date(lr_date)<curdate() union all Select b.from_station as sourcestation,"
			+ " a.station_code as currentstation,"
			+ "b.lr_no as lrno, b.ddc as ddc, b.total as total, b.lr_type as lr_type, 1 as lrstatus "
			+ "from lr_tracking a , lr b where a.lr_status = 'Dispatched'  and b.lr_status =0 "
			+ "and b.lr_type = 'Paid' and a.lr_no=b.lr_no and b.from_station=a.station_code"
			+ " and date(lr_date)<date(outward_time) and date(outward_time)=curdate()"
			+ "   order by 2,6";

	public static final String GET_BOOKEDLR_COUNT = "SELECT count(l.lr_no) as lrCount, l.from_station as " +
			"station_code,l.lr_type FROM lr l,station a where l.from_station = a.station_code and " +
			"date(lr_date) between ? and ? and branch_code = ? group by 2,3 " +
			"union all " +
			"SELECT count(l.lr_no) as lrCount, l.from_station as station_code,l.lr_type " +
			"FROM lr_history l,station a where l.from_station = a.station_code and " +
			"date(lr_date) between ? and ? and branch_code = ? group by 2,3";
	
	public static final String GET_BOOKEDLR_COUNT_UNION = "SELECT count(l.lr_no) as lrCount, l.from_station as " +
	"station_code,l.lr_type FROM lr l,station a where l.from_station = a.station_code and " +
	"date(lr_date) between ? and ? and branch_code = ? group by 2,3 " +
	"union all " +
	"SELECT count(l.lr_no) as lrCount, l.from_station as station_code,l.lr_type " +
	"FROM lr_history l,station a where l.from_station = a.station_code and " +
	"date(lr_date) between ? and ? and branch_code = ? group by 2,3";

	/*
	 * public static final String ST_DELIVERY = "SELECT " + "a.lr_no , a.lr_date ,
	 * a.from_station, a.no_of_articles, a.actual_weight, a.lr_type," + "a.ddc,
	 * a.total, " + "(a.bft + a.lrc + a.ccc + a.dcc + a.iec + a.lc + a.gsc +
	 * a.stax + a.other_charges) as others," + "b.demurrage, b.undercharge,
	 * b.postage, b.ddextra, b.cr_no, b.total_amount, b.status," + "
	 * a.delivered_date, a.last_inward_date " + "FROM lr a left join
	 * cash_receipt b on a.lr_no = b.lr_no " + "WHERE " + "a.delivered_station = ?
	 * and (a.lr_status=3 or a.lr_status=0 or a.lr_status=2) " + "and
	 * a.delivered_date like ? " + "union all " + "SELECT a.lr_no , a.lr_date ,
	 * a.from_station, a.no_of_articles, a.actual_weight," + "a.lr_type,a.ddc,
	 * a.total, (a.bft + a.lrc + a.ccc + a.dcc + a.iec + a.lc + a.gsc + a.stax" + "+
	 * a.other_charges) as others,b.demurrage, b.undercharge, b.postage,
	 * b.ddextra, b.cr_no," + " b.total_amount, b.status,a.delivered_date,
	 * a.last_inward_date FROM lr a " + "left join cash_receipt b on a.lr_no =
	 * b.lr_no where " + " a.delivered_station = ? and (a.lr_status = 2)" + "
	 * and a.delivered_date like ? ";
	 */
	/*
	 * public static final String ST_DELIVERY = " SELECT 1,a.lr_no , a.lr_date ,
	 * a.from_station, a.no_of_articles, " + "a.actual_weight,a.lr_type,a.ddc,
	 * a.total, (a.bft + a.lrc + a.ccc + a.dcc + a.iec + a.lc " + "+ a.gsc +
	 * a.stax + a.other_charges) as others,b.demurrage, b.undercharge, " +
	 * "b.postage, b.ddextra, b.cr_no, b.total_amount,
	 * b.status,a.delivered_date, a.last_inward_date " + "FROM lr a
	 * ,cash_receipt b WHERE a.lr_no = b.lr_no and a.delivered_station = ?" + "
	 * and (a.lr_status=3 or a.lr_status=0 or a.lr_status =2) and
	 * date(a.delivered_date) = ?" + "union all SELECT 2,a.lr_no , a.lr_date ,
	 * a.from_station, a.no_of_articles, a.actual_weight," + "a.lr_type,a.ddc,
	 * a.total, (a.bft + a.lrc + a.ccc + a.dcc + a.iec + a.lc + a.gsc +a.stax+ " +
	 * "a.other_charges) as others,null demurrage, null as undercharge," + "
	 * null postage, null as ddextra,null as cr_no,null as total_amount, null as
	 * status," + "a.delivered_date, a.last_inward_date FROM lr a where
	 * a.delivered_station = ? " + "and (a.lr_status = 2) and
	 * date(a.delivered_date) = ? order by 1,cast(SUBSTR(cr_no,2) as decimal)";
	 */

	public static final String ST_DELIVERY = " SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, "
			+ "a.actual_weight,a.lr_type,a.ddc,a.ddcfree, a.total, b.other,b.demurrage, b.undercharge, "
			+ "b.postage, b.ddextra, b.cr_no, b.total_amount, b.status,a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno "
			+ "FROM lr a ,cash_receipt b  WHERE (a.lr_status<>1) and a.lr_no = b.lr_no and b.station_code = ?"
			+ " and date(b.lr_delivery_date) = ?"
			+ "union all SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, a.actual_weight,"
			+ "a.lr_type,a.ddc,a.ddcfree, a.total, null other,null demurrage, null as undercharge,"
			+ "  null postage, null as ddextra,null as cr_no,null as total_amount, null as status,"
			+ "a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno FROM lr a  where a.delivered_station = ?   "
			+ "and (a.lr_status = 2) and date(a.delivered_date) = ? ";
	
	
	public static final String ST_DELIVERY_UNION = " SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, "
		+ "a.actual_weight,a.lr_type,a.ddc,a.ddcfree, a.total, b.other,b.demurrage, b.undercharge, "
		+ "b.postage, b.ddextra, b.cr_no, b.total_amount, b.status,a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno "
		+ "FROM lr a ,cash_receipt b  WHERE (a.lr_status<>1) and a.lr_no = b.lr_no and b.station_code = ?"
		+ " and date(b.lr_delivery_date) = ?"
		+ "union all SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, a.actual_weight,"
		+ "a.lr_type,a.ddc,a.ddcfree, a.total, null other,null demurrage, null as undercharge,"
		+ "  null postage, null as ddextra,null as cr_no,null as total_amount, null as status,"
		+ "a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno FROM lr a  where a.delivered_station = ?   "
		+ "and (a.lr_status = 2) and date(a.delivered_date) = ? " +
		" union " + 
		" SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, "
		+ "a.actual_weight,a.lr_type,a.ddc,a.ddcfree, a.total, b.other,b.demurrage, b.undercharge, "
		+ "b.postage, b.ddextra, b.cr_no, b.total_amount, b.status,a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno "
		+ "FROM lr_history a ,cash_receipt_history b  WHERE (a.lr_status<>1) and a.lr_no = b.lr_no and b.station_code = ?"
		+ " and date(b.lr_delivery_date) = ?"
		+ "union all SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, a.actual_weight,"
		+ "a.lr_type,a.ddc,a.ddcfree, a.total, null other,null demurrage, null as undercharge,"
		+ "  null postage, null as ddextra,null as cr_no,null as total_amount, null as status,"
		+ "a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno FROM lr_history a  where a.delivered_station = ?   "
		+ "and (a.lr_status = 2) and date(a.delivered_date) = ? ";
 	
	public static final String ST_DELIVERY_HISTORY = " SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, "
		+ "a.actual_weight,a.lr_type,a.ddc,a.ddcfree, a.total, b.other,b.demurrage, b.undercharge, "
		+ "b.postage, b.ddextra, b.cr_no, b.total_amount, b.status,a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno "
		+ "FROM lr_history a ,cash_receipt_history b  WHERE (a.lr_status<>1) and a.lr_no = b.lr_no and b.station_code = ?"
		+ " and date(b.lr_delivery_date) = ?"
		+ "union all SELECT a.lr_no , a.lr_date , a.from_station, a.no_of_articles, a.actual_weight,"
		+ "a.lr_type,a.ddc,a.ddcfree, a.total, null other,null demurrage, null as undercharge,"
		+ "  null postage, null as ddextra,null as cr_no,null as total_amount, null as status,"
		+ "a.delivered_date, a.last_inward_date,a.isupd,a.oldLrno FROM lr_history a  where a.delivered_station = ?   "
		+ "and (a.lr_status = 2) and date(a.delivered_date) = ? ";

	public static final String CALCULATE_DRS_DETAILS = "SELECT "
			+ "min(a.lr_no) as fromNo, max(a.lr_no) toNo, sum(a.total) + "
			+ "(SELECT ifnull(sum(c.total_amount),0) FROM cash_receipt c WHERE a.from_station = ? and "
			+ "a.lr_status != 1 and a.lr_type = 'PAID' and c.lr_no=a.lr_no  and c.status != 1  "
			+ "and date(c.date) = ? ) as total_collection,ifnull((a.lr_type),'') as lr_type FROM lr a WHERE a.from_station = ? and "
			+ "a.lr_status != 1 and a.lr_type = 'PAID' and date(a.lr_date) = ? group by lr_type "
			+ "UNION "
			+ "SELECT "
			+ "min(c.cr_no) as fromNo, max(c.cr_no) as toNo, sum(c.total_amount) as total_collection, "
			+ "ifnull((l.lr_type),'') as lr_type FROM cash_receipt c,lr l WHERE l.delivered_station= ? and l.lr_type='TOPAY' "
			+ "and c.station_code = l.delivered_station and c.status != 1 and c.lr_no=l.lr_no  "
			+ "and date(c.date) = ? group by lr_type ";

	/*
	 * public static final String GET_DDREIMBURSEMENT = "SELECT a.lr_no ,
	 * a.lr_date , a.from_station," + " a.no_of_articles,a.actual_weight,
	 * a.lr_type, a.bft,a.ddc, b.ddextra, b.cr_no, b.date,a.total," + "(b.other)
	 * as other_charges FROM lr a, cash_receipt b WHERE a.delivered_station= ? " +
	 * "and a.lr_status = 3 and a.lr_type = 'TOPAY' and b.station_code =
	 * a.delivered_station " + "and b.status != 1 and date(b.date) = ? and
	 * a.lr_no = b.lr_no " + " UNION SELECT a.lr_no , a.lr_date ,
	 * a.from_station, a.no_of_articles,a.actual_weight, " +
	 * "a.lr_type,a.bft,a.ddc, b.ddextra, b.cr_no, b.date,a.total,(b.other) as
	 * other_charges FROM" + " lr a left join cash_receipt b on a.lr_no =
	 * b.lr_no WHERE a.delivered_station = ? " + "and a.lr_status > 1 and
	 * a.lr_type != 'TOPAY' and date(a.delivered_date) = ? ";
	 */

	public static final String GET_DDREIMBURSEMENT = "SELECT a.lr_no, a.lr_date ,a.from_station,"
			+ "a.no_of_articles,a.actual_weight,a.lr_type,a.bft,a.ddc,a.ddcfree, b.ddextra, b.cr_no, b.date,a.total,"
			+ "(b.other) as other_charges, b.status FROM lr a,cash_receipt b WHERE a.delivered_station= ? and"
			+ " a.lr_status = 3 and b.station_code = a.delivered_station and b.status != 1  "
			+ "and date(b.date) = ? and a.lr_no = b.lr_no";
	
	public static final String GET_DDREIMBURSEMENT_UNION = "SELECT a.lr_no, a.lr_date ,a.from_station,"
		+ "a.no_of_articles,a.actual_weight,a.lr_type,a.bft,a.ddc,a.ddcfree, b.ddextra, b.cr_no, b.date,a.total,"
		+ "(b.other) as other_charges, b.status FROM lr a,cash_receipt b WHERE a.delivered_station= ? and"
		+ " a.lr_status = 3 and b.station_code = a.delivered_station and b.status != 1  "
		+ "and date(b.date) = ? and a.lr_no = b.lr_no" + 
		" union "+
		"SELECT a.lr_no, a.lr_date ,a.from_station,"
		+ "a.no_of_articles,a.actual_weight,a.lr_type,a.bft,a.ddc,a.ddcfree, b.ddextra, b.cr_no, b.date,a.total,"
		+ "(b.other) as other_charges,b.status FROM lr_history a,cash_receipt_history b WHERE a.delivered_station= ? and"
		+ " a.lr_status = 3 and b.station_code = a.delivered_station and b.status != 1  "
		+ "and date(b.date) = ? and a.lr_no = b.lr_no";
	
	public static final String GET_DDREIMBURSEMENT_HISTORY = "SELECT a.lr_no, a.lr_date ,a.from_station,"
		+ "a.no_of_articles,a.actual_weight,a.lr_type,a.bft,a.ddc,a.ddcfree, b.ddextra, b.cr_no, b.date,a.total,"
		+ "(b.other) as other_charges,b.status FROM lr_history a,cash_receipt_history b WHERE a.delivered_station= ? and"
		+ " a.lr_status = 3 and b.station_code = a.delivered_station and b.status != 1  "
		+ "and date(b.date) = ? and a.lr_no = b.lr_no";

	public static final String CALCULATE_DOOR_DELIVERY = "SELECT "
			+ "sum(a.ddc) + sum(b.ddextra) as DDCHARGES "
			+ "FROM "
			+ "lr a, cash_receipt b "
			+ "WHERE  b.station_code = ? and b.status != 1 and date(b.date) = ? and  "
			+ "a.delivered_station= b.station_code and  a.lr_type = 'TOPAY'   and a.lr_no = b.lr_no "
			+

			"UNION "
			+

			"SELECT "
			+ "sum(a.ddc) + sum(b.ddextra) as DDCHARGES "
			+ "FROM "
			+ "lr_tracking_archive c, lr a LEFT JOIN cash_receipt b on a.lr_no = b.lr_no "
			+ "WHERE " + "c.station_code = ? and c.lr_status ='"
			+ DELIVERED_STATUS + "' and date(c.outward_time) = ? "
			+ "and c.lr_no = a.lr_no and a.lr_type != 'TOPAY'";

	public static final String GET_GOODS_MOVEMENT = "SELECT "
			+ "a.lr_no, a.lr_date, a.from_station, a.to_station, a.consignor_name,"
			+ " a.consignee_name,a.actual_weight,a.no_of_articles, a.article_id, a.total, c.vehicle_no, "
			+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr a, lr_tracking c WHERE c.station_code = ? and "
			+ "(c.lr_status = '"
			+ DISPATCH_STATUS
			+ "') and "
			+ "a.lr_no = c.lr_no and "
			+ "date(c.outward_time) = ? "
			+ "UNION "
			+ "SELECT "
			+ "a.lr_no, a.lr_date, a.from_station, a.to_station, a.consignor_name,"
			+ " a.consignee_name,a.actual_weight,a.no_of_articles, a.article_id, a.total, c.vehicle_no, "
			+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr a, lr_tracking_archive c WHERE c.station_code = ? and "
			+ "(c.lr_status = '" + DELIVERED_STATUS + "' or c.lr_status = '"
			+ DISPATCH_STATUS + "' ) and " + "a.lr_no = c.lr_no and "
			+ "date(c.outward_time) = ? order by 11";
	
	public static final String GET_GOODS_MOVEMENT_HISTORY = "SELECT "
		+ "a.lr_no, a.lr_date, a.from_station, a.to_station, a.consignor_name,"
		+ " a.consignee_name,a.actual_weight,a.no_of_articles, a.article_id, a.total, c.vehicle_no, "
		+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr a, lr_tracking c WHERE c.station_code = ? and "
		+ "(c.lr_status = '"
		+ DISPATCH_STATUS
		+ "') and "
		+ "a.lr_no = c.lr_no and "
		+ "date(c.outward_time) = ? "
		+ "UNION "
		+ "SELECT "
		+ "a.lr_no, a.lr_date, a.from_station, a.to_station, a.consignor_name,"
		+ " a.consignee_name,a.actual_weight,a.no_of_articles, a.article_id, a.total, c.vehicle_no, "
		+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr_history a, lr_tracking_archive_history c WHERE c.station_code = ? and "
		+ "(c.lr_status = '" + DELIVERED_STATUS + "' or c.lr_status = '"
		+ DISPATCH_STATUS + "' ) and " + "a.lr_no = c.lr_no and "
		+ "date(c.outward_time) = ? order by 11";

	public static final String GET_GOODS_MOVEMENT_VEHICLE = "SELECT "
			+ "a.lr_no, a.from_station, a.to_station, a.consignor_name,"
			+ " a.consignee_name,a.actual_weight,a.no_of_articles, c.station_code, c.lr_status, c.vehicle_no, "
			+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr a, lr_tracking c WHERE c.station_code = ? and "
			+ "(c.lr_status = '"
			+ DISPATCH_STATUS
			+ "') and "
			+ "a.lr_no = c.lr_no and "
			+ "date(c.outward_time) = ? and c.vehicle_no = ? group by c.outward_time "
			+ "UNION "
			+ "SELECT "
			+ "a.lr_no, a.from_station, a.to_station, a.consignor_name,"
			+ " a.consignee_name,a.actual_weight,a.no_of_articles, c.station_code, c.lr_status, c.vehicle_no, "
			+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr a, lr_tracking c WHERE c.station_code = ? and "
			+ "(c.lr_status = '"
			+ DELIVERED_STATUS
			+ "') and "
			+ "a.lr_no = c.lr_no and "
			+ "date(c.outward_time) = ? and c.vehicle_no = ? group by c.outward_time ";

	public static final String GET_TRANSHIPMENT = "select d.station_code, d.month, d.year, d.inwardLrs, "
			+ "d.delivery1day, d.delivery2day, d.delivery3day, d.stock, d.intime1day, d.deliverym3, "
			+ "s.branch_code from delivery_report d, station s where d.station_code = s.station_code "
			+ "and s.branch_code = ? and d.month = ? and d.year = ? ORDER BY d.station_code";

	public static final String GET_CURRENT_STATUS = "SELECT b.ddc,b.total,b.article_value,A.STATION_CODE,"
			+ "A.INWARD_TIME,A.LR_STATUS,B.LR_NO,B.LR_DATE,B.FROM_STATION,B.TO_STATION,B.ACTUAL_WEIGHT,B.NO_OF_ARTICLES,"
			+ "B.CONSIGNOR_NAME,B.CONSIGNEE_NAME,A.LR_NO FROM LR_TRACKING A,LR B WHERE B.From_stATION = ? "
			+ "AND A.LR_NO=B.LR_NO AND (A.LR_STATUS = 'Arrived' OR A.LR_STATUS='TOARRIVE')";

	public static final String GET_MEASUREMENTS = "select feet,cm,inch from measurements limit 1";

	public static final String GET_LR_TRACK_COUNT = "SELECT sum(per_day) as count FROM lr_track_dailystatus "
			+ "where" + " date(date_cur) between ? and ?";

	public static final String GET_VERFICATION_REPORT = "select a.lr_no,a.lr_date,a.consignor_name,"
			+ "a.consignee_name,a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,"
			+ "a.ddc,a.lr_type,a.from_station,a.to_station from lr a,lr_tracking_archive b where a.lr_no=b.lr_no"
			+ " and b.lr_status='Delivered'";
	
	public static final String DAILY_DELIVERY_STATUS = " select * from daily_delivery_status where date = ? and a.station_code = b.station_code and b.branch_code like ? ";

	public static final String COUNT = "count";

	public static final String FEET = "feet";

	public static final String INCH = "inch";

	public static final String CM = "cm";

	public static final String CHECK_DATE = "SELECT count(maildate) FROM mac_info WHERE maildate=CURDATE()";

	public static final String UPDATE_DATE = "UPDATE  mac_info SET maildate=curdate();";

	public static final String GET_GMR_INTIME = "";

	public static final String GET_GMR_OUTTIME = "";

	public static final String GET_CR_COUNT = "";

	/** *************** */
	public static final String LR_NO = "lr_no";

	public static final String LR_DATE = "lr_date";

	public static final String FROM_STATION = "from_station";

	public static final String NO_OF_ARTICLES = "no_of_articles";

	public static final String ACTUAL_WEIGHT = "actual_weight";

	public static final String TO_STATION = "to_station";

	public static final String LR_TYPE = "lr_type";

	public static final String DDC = "ddc";

	public static final String BASIC_FRIEGHT = "bft";

	public static final String CCC = "ccc";

	public static final String LRC = "lrc";
	
	public static final String DHC = "dhc";
	
	public static final String DCC = "dcc";

	public static final String DELIVERED_STATION = "delivered_station";

	public static final String IEC = "iec";

	public static final String LC = "lc";

	public static final String GSC = "gsc";

	public static final String STAX = "stax";

	public static final String LR_STATUS = "lr_status";

	public static final String OTHER_CHARGES = "other_charges";

	public static final String DOOR_DELIVERY_CHARGE = "ddc";

	public static final String TOTAL = "total";
	
	public static final String ISUPD = "isupd";
	
	public static final String OLDLRNO = "oldLrno";
	
	
	
	

	// public static final String LR_TRACKING_DATE = "date";

	public static final String OUTWARD_TIME = "outward_time";

	public static final String INWARD_TIME = "inward_time";

	public static final String DELIVERED_DATE = "delivered_date";

	public static final String LAST_INWARD_DATE = "last_inward_date";

	/** *************** */

	/** ************* */
	public static final String DEMURRAGE = "demurrage";

	public static final String UNDER_CHARGE = "undercharge";

	public static final String POSTAGE = "postage";

	public static final String DD_EXTRA = "ddextra";

	public static final String TOTAL_AMOUNT = "total_amount";

	public static final String CR_NO = "cr_no";

	public static final String CR_DATE = "DATE";

	public static final String CR_STATUS = "status";

	public static final String DDC_FREE = "ddcfree";

	/** ************* */

	/** *********** */
	public static final String OTHERS = "others";

	public static final String FROM_NUMBER = "fromNo";

	public static final String TO_NUMBER = "toNo";

	public static final String TOTAL_COLLECTION = "total_collection";

	public static final String DDCHARGES = "DDCHARGES";

	/** ************ */

	/** *************** */

	public static final String SERVER_DATE = "server_date";

	public static final String DR_DATE = "dr_date";

	public static final String STATION_CODE = "station_code";

	public static final String STATION_NAME = "name";

	public static final String PAID_COLLECTION = "paid_collection_amount";

	public static final String TOPAY_COLLECTION = "topay_collection_amount";

	public static final String ADD_REMITTANCE = "add_remittance";

	public static final String LESS_REMITTANCE = "less_remittance";

	public static final String DD_REIMBURSEMENT = "dd_reimbursement";

	public static final String GRAND_TOTAL = "grand_total";

	public static final String STATUS = "status";

	public static final String VERSION_ID = "version_id";

	public static final String LR_COUNT = "lrCount";

	/** *************** */

	public static final String INWARD_LRS = "inwardLrs";

	public static final String DELIVERY_1DAY = "delivery1day";

	public static final String DELIVERY_2DAY = "delivery2day";

	public static final String DELIVERY_3DAY = "delivery3day";

	public static final String DELIVERY_M3 = "deliverym3";

	public static final String STOCK = "stock";

	public static final String INTIME_1DAY = "intime1day";

	/** *************** */
	public static final String TOPAY_TYPE = "TOPAY";

	public static final String PAID_TYPE = "PAID";

	public static final String BILLING_TYPE = "BILLING";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String CONSIGNOR_NAME = "consignor_name";

	public static final String CONSIGNEE_NAME = "consignee_name";
	
	public static final String CNORPHONE = "cnorphone";

	public static final String CNEEPHONE = "cneephone";

	public static final String VEHICLE_NO = "vehicle_no";

	public static final String DRIVER_NAME = "driver_name";

	public static final String MOVED_TO = "dispatch_to";

	public static final String GMR_INTIME = "gmr_intime";

	public static final String GMR_OUTTIME = "gmr_outtime";

	public static final String CR_COUNT = "cr";

	public static final String ARTICLE_ID = "article_id";

	public static final String STATION = "station";

	public static final String CURRENT_STATION = "currentstation";

	public static final String CONSIGNEE_ADDRESS = "consignee_address";
	
	public static final String TYPE_IN = "type_in";
	// History years
	public static final String YEAR = "year";

	public static final String DB_NAME = "db_name";

	public static final String GET_HISTORY_YEARS = "select year, db_name, three_month from year_history";
	
	public static final String LAST_MONTH = "last_month";

	public static final String LAST_YEAR = "last_year";

	//last month 

	public static final String HIGH_BASIC_FRIEGHT = "high_bft";

	public static final String HIGH_TOTAL = "high_total";

	public static final String HIGH_COUNT = "high_count";

	public static final String HIGH_NO_OF_ARTICLES = "high_no_of_articles";

	public static final String HIGH_ACTUAL_WEIGHT = "high_actual_weight";

	public static final String HIGH_CHARGED_WEIGHT = "high_charged_weight";

	public static final String SUNDRY_TYPE = "sundry_type";
	
	public static final String  ARTICLE_VALUE="article_value";
	
	public static final String  MODEL_NO="model_no";	
	
	public static final String  RATE="rate";
	
	public static final String DR_NO = "dr_no";
	
	public static final String NO_OF_LRS = "no_of_lrs";
	
	public static final String CUSTOMER_NAME = "customer_name";
	
	public static final String CHARGED_WEIGHT = "charged_weight";
	
	public static final String RATE_TYPE = "rate_type";
	
	public static final String DRS_DATE = "drs_date";
	
	public static final String DISCOUNT = "discount";
	
	public static final String NAME = "name";
	
	public static final String  ACTUAL_BFT = "actual_bft";
	
	public static final String  SNO = "sno";
	
	public static final String  HOLIDAY_DESC = "holiday_desc";
	
	public static final String  HOLIDAY_DATE = "holiday_date";
	
	
	public  static final String GET_HOLIDAY_VIEW = "select sno,holiday_desc,holiday_date from holiday_entry where year(holiday_date) = ?";
	// Reports
	public static final String GET_RS_REPORT = "SELECT l.from_station,l.to_station,l.lr_no,l.lr_date,l.lr_type,"
			+ "d.dr_no,d.date,c.cr_no,l.consignor_name, l.consignee_name, d.lr_amount,d.paid_date "
			+ "FROM daily_remittance_details d, lr l left join cash_receipt c on l.lr_no = c.lr_no "
			+ "where d.lr_no = l.lr_no and month(d.date) = ? and year(d.date) = ? and d.station_code in "
			+ "(select station_code from station where branch_code = ?)";
	
	
	public static final String GET_RS_REPORT_UNION = "SELECT l.from_station,l.to_station,l.lr_no,l.lr_date,l.lr_type,"
		+ "d.dr_no,d.date,c.cr_no,l.consignor_name, l.consignee_name, d.lr_amount,d.paid_date "
		+ "FROM daily_remittance_details d, lr l left join cash_receipt c on l.lr_no = c.lr_no "
		+ "where d.lr_no = l.lr_no and month(d.date) = ? and year(d.date) = ? and d.station_code in "
		+ "(select station_code from station where branch_code = ?)" +
		" union	" +
		" SELECT l.from_station,l.to_station,l.lr_no,l.lr_date,l.lr_type,"
		+ "d.dr_no,d.date,c.cr_no,l.consignor_name, l.consignee_name, d.lr_amount,d.paid_date "
		+ "FROM daily_remittance_details d, lr_history l left join cash_receipt_history c on l.lr_no = c.lr_no "
		+ "where d.lr_no = l.lr_no and month(d.date) = ? and year(d.date) = ? and d.station_code in "
		+ "(select station_code from station where branch_code = ?)	";
		
	
	public static final String GET_RS_REPORT_HISTORY = "SELECT l.from_station,l.to_station,l.lr_no,l.lr_date,l.lr_type,"
		+ "d.dr_no,d.date,c.cr_no,l.consignor_name, l.consignee_name, d.lr_amount,d.paid_date "
		+ "FROM daily_remittance_details d, lr_history l left join cash_receipt_history c on l.lr_no = c.lr_no "
		+ "where d.lr_no = l.lr_no and month(d.date) = ? and year(d.date) = ? and d.station_code in "
		+ "(select station_code from station where branch_code = ?)";
	

	public static final String GET_CANCELLED_LR = /*"select from_station, lr_no, lr_date,lr_type, "
			+ "to_station, no_of_articles,actual_weight,bft,ccc,iec,other_charges,ddc, total from lr "
			+ "where lr_status = 1 and date(lr_date) between ? and ?";*/
		" select a.from_station, a.lr_no, a.lr_date,a.lr_type, " +
		" a.to_station, a.no_of_articles,a.actual_weight,a.bft,a.ccc,a.iec,a.other_charges,a.ddc, a.total " +
		" from lr a,station b	where lr_status = 1 and date(lr_date) between ? and ? " +
		" and a.from_station = b.station_code and b.branch_code like ? and b.station_code like ? ";
	
	
	public static final String GET_CANCELLED_LR_UNION = /*"select from_station, lr_no, lr_date,lr_type, "
		+ "to_station, no_of_articles,actual_weight,bft,ccc,iec,other_charges,ddc, total from lr "
		+ "where lr_status = 1 and date(lr_date) between ? and ?";*/
	" select a.from_station, a.lr_no, a.lr_date,a.lr_type, " +
	" a.to_station, a.no_of_articles,a.actual_weight,a.bft,a.ccc,a.iec,a.other_charges,a.ddc, a.total " +
	" from lr a,station b	where lr_status = 1 and date(lr_date) between ? and ? " +
	" and a.from_station = b.station_code and b.branch_code like ? and b.station_code like ? " + 
	" union " +
	" select a.from_station, a.lr_no, a.lr_date,a.lr_type, " +
	" a.to_station, a.no_of_articles,a.actual_weight,a.bft,a.ccc,a.iec,a.other_charges,a.ddc, a.total " +
	" from lr_history a,station b	where lr_status = 1 and date(lr_date) between ? and ? " +
	" and a.from_station = b.station_code and b.branch_code like ? and b.station_code like ? ";
	
	public static final String GET_CANCELLED_LR_HISTORY = " select a.from_station, a.lr_no, a.lr_date,a.lr_type, " +
			" a.to_station, a.no_of_articles,a.actual_weight,a.bft,a.ccc,a.iec,a.other_charges,a.ddc, a.total " +
			" from lr_history a,station b	where lr_status = 1 and date(lr_date) between ? and ? " +
			" and a.from_station = b.station_code and b.branch_code like ? and b.station_code like ? ";
			

	/*public static final String GET_CANCELLED_LR1 = "select from_station,count(lr_no) as no_of_lrs,sum(total) as total"
			+ " from lr where lr_status = 1 and date(lr_date) between ? and ? "
			+ "group by from_station";*/
	public static final String GET_CANCELLED_LR1 = " select a.from_station,count(a.lr_no) as no_of_lrs,sum(a.total) as total " +
			" from lr a,station b  where a.lr_status = 1 and date(a.lr_date) between ? and ? " +
			" and a.from_station = b.station_code and b.branch_code like ? " +
			" group by from_station ";
	
	
	public static final String GET_CANCELLED_LR1_UNION = " select a.from_station,count(a.lr_no) as no_of_lrs,sum(a.total) as total " +
	" from lr a,station b  where a.lr_status = 1 and date(a.lr_date) between ? and ? " +
	" and a.from_station = b.station_code and b.branch_code like ? " +
	" group by from_station " +
	" union" + 
	" select a.from_station,count(a.lr_no) as no_of_lrs,sum(a.total) as total " +
	" from lr_history a,station b  where a.lr_status = 1 and date(a.lr_date) between ? and ? " +
	" and a.from_station = b.station_code and b.branch_code like ? " +
	" group by from_station ";
	
	
	public static final String GET_CANCELLED_LR1_HISTORY = " select a.from_station,count(a.lr_no) as no_of_lrs,sum(a.total) as total " +
			" from lr_history a,station b  where a.lr_status = 1 and date(a.lr_date) between ? and ? " +
			" and a.from_station = b.station_code and b.branch_code like ? " +
			" group by from_station ";
			
	
	public static final String GET_CNOR_BUSINESS_ANAYSIS = " select count(lr_no) as count,consignor_name as " +
			"customer_name,a.desc as type_in,from_station as station_code,lr_date as lr_date,sum(bft) as bft, " +
			"sum(total) as total,sum(actual_weight) as actual_weight,sum(charged_weight) as charged_weight, " +
			"sum(no_of_articles) as no_of_articles,rate_type from lr a,station b where a.from_station = " +
			"b.station_code AND a.lr_status != 1 and b.branch_code like ? AND date(lr_date) " +
			"between ? and ? group by month(lr_date),from_station,type_in,consignor_name " +
			 "UNION "
			+ " select count(lr_no) as count,consignor_name as customer_name,a.desc as type_in,from_station " +
    		  " as station_code,lr_date as lr_date,sum(bft) as bft, sum(total) as total,sum(actual_weight) as " +
    		  " actual_weight,sum(charged_weight) as charged_weight, sum(no_of_articles) as no_of_articles," +
    		  " rate_type from lr_history a,station b where a.from_station=b.station_code AND a.lr_status != 1 " +
    		  " and b.branch_code like ? AND date(lr_date) between ? and ? group by " +
    		  " month(lr_date),from_station,type_in,consignor_name ";
	
	/*public static final String GET_CNOR_BUSINESS_ANAYSIS = "select count(lr_no) as count,consignor_name as customer_name,from_station as station_code, "
	+ "lr_date as lr_date,sum(bft) as bft, "
	+ "sum(total) as total,sum(actual_weight) as actual_weight,sum(charged_weight) as charged_weight, "
	+ "sum(no_of_articles) as no_of_articles,rate_type from lr a,station b,customer c  where a.from_station=b.station_code AND "
	+ "a.consignor_name=c.name and c.type_of_customer = 'Consignor' and b.branch_code like ? AND "
	+ "date(lr_date) between ? and ? group by 	month(lr_date),consignor_name,from_station " 
	+ "UNION "
	+ "select count(lr_no) as count,consignor_name as customer_name,from_station as station_code, "
	+ "lr_date as lr_date,sum(bft) as bft,"
	+ "sum(total) as total,sum(actual_weight) as actual_weight,sum(charged_weight) as charged_weight, "
	+ "sum(no_of_articles) as no_of_articles,rate_type from lr_history a,station b,customer c  where a.from_station=b.station_code AND "
	+ "a.consignor_name=c.name and c.type_of_customer = 'Consignor' and b.branch_code like ? AND "
	+ "date(lr_date) between ? and ? group by 	month(lr_date),consignor_name,from_station ";*/

	
	public static final String GET_CNOR_BUSINESS_ANAYSIS_HISTORY ="select count(lr_no) as count,consignor_name as customer_name,from_station as station_code,"
	+ "lr_date as lr_date,sum(bft) as bft,"
	+ "sum(total) as total,sum(actual_weight) as actual_weight,sum(charged_weight) as charged_weight,"
	+ "sum(no_of_articles) as no_of_articles from lr_history a,station b  where a.from_station=b.station_code AND"
	+ "b.branch_code like ? AND"
	+ "date(lr_date) between ? and ? group by month(lr_date),consignor_name,from_station";

	
	public static final String GET_CNEE_BUSINESS_ANAYSIS = " select count(lr_no) as count,consignee_name as " +
			"customer_name,a.desc as type_in,to_station as station_code,lr_date as lr_date,sum(bft) as bft, " +
			"sum(total) as total,sum(actual_weight) as actual_weight,sum(charged_weight) as charged_weight, " +
			"sum(no_of_articles) as no_of_articles,rate_type from lr a,station b where a.to_station = " +
			"b.station_code AND a.lr_status != 1 and b.branch_code like ? AND date(lr_date) " +
			"between ? and ? group by month(lr_date),to_station,type_in,consignee_name " +
			 "UNION "
			+ " select count(lr_no) as count,consignee_name as customer_name,a.desc as type_in,to_station " +
    		  " as station_code,lr_date as lr_date,sum(bft) as bft, sum(total) as total,sum(actual_weight) as " +
    		  " actual_weight,sum(charged_weight) as charged_weight, sum(no_of_articles) as no_of_articles," +
    		  " rate_type from lr_history a,station b where a.to_station=b.station_code AND a.lr_status != 1 " +
    		  " and b.branch_code like ? AND date(lr_date) between ? and ? group by " +
    		  " month(lr_date),to_station,type_in,consignee_name ";

	
	public static final String GET_CNEE_BUSINESS_ANAYSIS_HISTORY = "select count(lr_no) as count,consignee_name as customer_name,to_station as station_code,"
		+ "lr_date as lr_date,sum(bft) as bft,"
		+ "sum(total) as total,sum(actual_weight) as actual_weight,sum(charged_weight) as charged_weight,"
		+ "sum(no_of_articles) as no_of_articles from lr_history a,station b  where a.from_station=b.station_code AND "
		+ "b.branch_code like ? AND  "
		+ "date(lr_date) between ? and ? group by 	month(lr_date),consignor_name,from_station";

	
	public static final String GET_CCCHARGE_SUMMARY_BFT = "Select b.branch_code, count(a.from_station) as CC_LRs, "
			+ "a.from_Station, sum(a.bft) as bft, sum(a.ccc) as ccc from lr a, station b where a.lr_status != 1 and a.ccc != 0 and date(lr_date) "
			+ "between ? and ? and a.from_station = b.station_code and "
			+ "b.branch_code like ? group by a.from_station ";
	
	
	public static final String GET_CCCHARGE_SUMMARY_BFT_UNION = "Select b.branch_code, count(a.from_station) as CC_LRs, "
		+ "a.from_Station, sum(a.bft) as bft, sum(a.ccc) as ccc from lr a, station b where a.lr_status != 1 and a.ccc != 0 and date(lr_date) "
		+ "between ? and ? and a.from_station = b.station_code and "
		+ "b.branch_code like ? group by a.from_station "+
		" union"+
		" Select b.branch_code, count(a.from_station) as CC_LRs, "
		+ "a.from_Station, sum(a.bft) as bft, sum(a.ccc) as ccc from lr_history a, station b where a.lr_status != 1 and a.ccc != 0 and date(lr_date) "
		+ "between ? and ? and a.from_station = b.station_code and "
		+ "b.branch_code like ? group by a.from_station ";
	
	public static final String GET_CCCHARGE_SUMMARY_BFT_HISTORY = "Select b.branch_code, count(a.from_station) as CC_LRs, "
		+ "a.from_Station, sum(a.bft) as bft, sum(a.ccc) as ccc from lr_history a, station b where a.lr_status != 1 and a.ccc != 0 and date(lr_date) "
		+ "between ? and ? and a.from_station = b.station_code and "
		+ "b.branch_code like ? group by a.from_station ";
	

		public static final String GET_CCCHARGE_SUMMARY_PDTP = "Select b.branch_code, a.from_Station, count(lr_no) as PD_TP_LRs, "
				+ "sum(a.bft) as bft, sum(a.ccc) as ccc "
				+ "from lr a, station b where a.lr_status != 1 and (a.lr_type = 'TOPAY' or a.lr_type = 'PAID') and date(lr_date) "
				+ "between ? and ? and a.from_station = b.station_code and b.branch_code like ? group by from_station;";

		public static final String GET_CCCHARGE_SUMMARY_PDTP_UNION = "Select b.branch_code, a.from_Station, count(lr_no) as PD_TP_LRs, "
			+ "sum(a.bft) as bft, sum(a.ccc) as ccc "
			+ "from lr a, station b where a.lr_status != 1 and (a.lr_type = 'TOPAY' or a.lr_type = 'PAID') and date(lr_date) "
			+ "between ? and ? and a.from_station = b.station_code and b.branch_code like ? group by from_station " +
			" union "+
			"Select b.branch_code, a.from_Station, count(lr_no) as PD_TP_LRs, "
			+ "sum(a.bft) as bft, sum(a.ccc) as ccc "
			+ "from lr_history a, station b where a.lr_status != 1 and (a.lr_type = 'TOPAY' or a.lr_type = 'PAID') and date(lr_date) "
			+ "between ? and ? and a.from_station = b.station_code and b.branch_code like ? group by from_station ";

		
		public static final String GET_CCCHARGE_SUMMARY_PDTP_HISTORY = "Select b.branch_code, a.from_Station, count(lr_no) as PD_TP_LRs, "
			+ "sum(a.bft) as bft, sum(a.ccc) as ccc "
			+ "from lr_history a, station b where a.lr_status != 1 and (a.lr_type = 'TOPAY' or a.lr_type = 'PAID') and date(lr_date) "
			+ "between ? and ? and a.from_station = b.station_code and b.branch_code like ? group by from_station;";

	public static final String GET_UNDELIVERED_TOPAY = "Select a.station_code, b.from_station, count(b.lr_no) as lr_no, " +
			"sum(b.total) as total,'Not Delivered' as sundry_type from LR_TRACKING a ,lr b where " +
			"b.lr_type = 'Topay' and (b.lr_status = 0) and (a.lr_status = 'Arrived') and a.station_code != b.from_station " +
			"AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? group by " +
			"a.station_code " +
			"UNION " +			
			/*"Select (b.delivered_station) as station_code , b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total," +
			" 'Delivered' as sundry_type from  lr b where b.lr_type = 'Topay' and " +
			" (b.lr_status = 3) and b.delivered_station != b.from_station and date(b.last_inward_date) " +
			"between ? and ? group by b.delivered_station";*/
			" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
			" 'Delivered' as sundry_type from LR_TRACKING_archive a, lr b where b.lr_type = 'Topay' and" +
			" a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
			" between ? and ? group by a.station_code ";
	
	public static final String GET_UNDELIVERED_TOPAY_UNION = "Select a.station_code, b.from_station, count(b.lr_no) as lr_no, " +
	"sum(b.total) as total,'Not Delivered' as sundry_type from LR_TRACKING a ,lr b where " +
	"b.lr_type = 'Topay' and (b.lr_status = 0) and (a.lr_status = 'Arrived') and a.station_code != b.from_station " +
	"AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? group by " +
	"a.station_code " +
	"UNION " +			
	/*"Select (b.delivered_station) as station_code , b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total," +
	" 'Delivered' as sundry_type from  lr b where b.lr_type = 'Topay' and " +
	" (b.lr_status = 3) and b.delivered_station != b.from_station and date(b.last_inward_date) " +
	"between ? and ? group by b.delivered_station";*/
	" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
	" 'Delivered' as sundry_type from LR_TRACKING_archive a, lr b where b.lr_type = 'Topay' and" +
	" a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
	" between ? and ? group by a.station_code " +
	" union "+
	" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
	" 'Delivered' as sundry_type from LR_TRACKING_archive_history a, lr_history b where b.lr_type = 'Topay' and" +
	" a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
	" between ? and ? group by a.station_code ";
	
	
	public static final String GET_UNDELIVERED_TOPAY_HISTORY = /*"Select a.station_code, b.from_station, count(b.lr_no) as lr_no, " +
			"sum(b.total) as total,'Not Delivered' as sundry_type from LR_TRACKING a ,lr_history b where " +
			"b.lr_type = 'Topay' and (b.lr_status = 0) and (a.lr_status = 'Arrived') and a.station_code != b.from_station " +
			"AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? group by " +
			"a.station_code " +
			"union " +			
			/*"Select (b.delivered_station) as station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total," +
			" 'Delivered' as sundry_type from lr_history b where b.lr_type = 'Topay' and " +
			"(b.lr_status = 3) and b.delivered_station != b.from_station and date(b.last_inward_date)  between ? and ? " +
			"group by b.delivered_station";*/
			" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
			" 'Delivered' as sundry_type from LR_TRACKING_archive_history a, lr_history b where b.lr_type = 'Topay' and" +
			" a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
			" between ? and ? group by a.station_code ";

	
	public static final String GET_UNDELIVERED_TOPAY_BRANCH = "Select a.station_code, b.from_station, count(b.lr_no) as lr_no, " +
			"sum(b.total) as total,'Not Delivered' as sundry_type from LR_TRACKING a ,lr b, station s " +
			"where b.lr_type = 'Topay' and (b.lr_status = 0) and (a.lr_status = 'Arrived') and a.station_code != " +
			"b.from_station AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? " +
			"and a.station_code = s.station_code and s.branch_code = ? group by a.station_code " +
			"union " +
			/*"Select (b.delivered_station) as station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, 'Delivered' as sundry_type " +
			"from lr b, station s where b.lr_type = 'Topay' and (b.lr_status = 3) and " +
			" b.delivered_station != b.from_station and date(b.last_inward_date)  between ? and ? " +
			"and a.station_code = s.station_code and s.branch_code = ? " +
			"group by b.delivered_station";*/
			" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
			" 'Delivered' as sundry_type from LR_TRACKING_archive a, lr b, station s  where b.lr_type = 'Topay' and" +
			" a.station_code = s.station_code and s.branch_code = ? and a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
			" between ? and ? group by a.station_code ";
	
	public static final String GET_UNDELIVERED_TOPAY_BRANCH_UNION = "Select a.station_code, b.from_station, count(b.lr_no) as lr_no, " +
		"sum(b.total) as total,'Not Delivered' as sundry_type from LR_TRACKING a ,lr b, station s " +
		"where b.lr_type = 'Topay' and (b.lr_status = 0) and (a.lr_status = 'Arrived') and a.station_code != " +
		"b.from_station AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? " +
		"and a.station_code = s.station_code and s.branch_code = ? group by a.station_code " +
		"union " +
		/*"Select (b.delivered_station) as station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, 'Delivered' as sundry_type " +
		"from lr b, station s where b.lr_type = 'Topay' and (b.lr_status = 3) and " +
		" b.delivered_station != b.from_station and date(b.last_inward_date)  between ? and ? " +
		"and a.station_code = s.station_code and s.branch_code = ? " +
		"group by b.delivered_station";*/
		" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
		" 'Delivered' as sundry_type from LR_TRACKING_archive a, lr b, station s  where b.lr_type = 'Topay' and" +
		" a.station_code = s.station_code and s.branch_code = ? and a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
		" between ? and ? group by a.station_code " +
		" union "+
		" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
		" 'Delivered' as sundry_type from LR_TRACKING_archive_history a, lr_history b, station s where b.lr_type = 'Topay' and" +
		"  a.station_code = s.station_code and s.branch_code = ? and a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
		" between ? and ? group by a.station_code ";

		
	
	
	public static final String GET_UNDELIVERED_TOPAY_BRANCH_HISTORY = /*"Select a.station_code, b.from_station, count(b.lr_no) as lr_no, " +
			"sum(b.total) as total,'Not Delivered' as sundry_type from LR_TRACKING a ,lr_history b, station s " +
			"where b.lr_type = 'Topay' and (b.lr_status = 0) and (a.lr_status = 'Arrived') and a.station_code != " +
			"b.from_station AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? " +
			"and a.station_code = s.station_code and s.branch_code = ? group by a.station_code " +
			"union " +
			/*"Select (b.delivered_station) as station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, 'Delivered' as sundry_type " +
			"from lr_history b, station s where b.lr_type = 'Topay' and  " +
			"(b.lr_status = 3) and b.delivered_station != b.from_station and date(b.last_inward_date)  between ? and ? " +
			"and a.station_code = s.station_code and s.branch_code = ? " +
			"group by b.delivered_station";*/
		" Select a.station_code, b.from_station, count(b.lr_no) as lr_no, sum(b.total) as total, " +
		" 'Delivered' as sundry_type from LR_TRACKING_archive_history a, lr_history b, station s where b.lr_type = 'Topay' and" +
		"  a.station_code = s.station_code and s.branch_code = ? and a.station_code != b.from_station and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time)" +
		" between ? and ? group by a.station_code ";

	public static final String GET_UNDELIVERED_TOPAY_DETAILED = "Select a.lr_no,lr_date, from_Station, " +
			"to_station, bft, no_of_articles, actual_weight,total,b.station_code from lr a, station b, lr_tracking l " +
			"where a.lr_no = l.lr_no and  l.station_code != a.from_station and a.lr_type = 'Topay' and " +
			"a.lr_status = 0 and (l.lr_status = 'Arrived') and b.station_code = l.station_code and b.branch_code like ? and " +
			"date(l.inward_time) between ? and ? ";
	
	
	public static final String GET_UNDELIVERED_TOPAY_DETAILED_UNION = "Select a.lr_no,lr_date, from_Station, " +
		"to_station, bft, no_of_articles, actual_weight,total,b.station_code from lr a, station b, lr_tracking l " +
		"where a.lr_no = l.lr_no and  l.station_code != a.from_station and a.lr_type = 'Topay' and " +
		"a.lr_status = 0 and (l.lr_status = 'Arrived') and b.station_code = l.station_code and b.branch_code like ? and " +
		"date(l.inward_time) between ? and ? "+
		" union "+
		"Select a.lr_no,lr_date, from_Station, " +
		"to_station, bft, no_of_articles, actual_weight,total from lr_history a, station b, lr_tracking l " +
		"where a.lr_no = l.lr_no and  l.station_code != a.from_station and a.lr_type = 'Topay' and " +
		"a.lr_status = 0 and (l.lr_status = 'Arrived') and b.station_code = l.station_code and b.branch_code like ? and " +
		"date(l.inward_time) between ? and ? ";
		
		
	public static final String GET_UNDELIVERED_TOPAY_DETAILED_HISTORY = "Select a.lr_no,lr_date, from_Station, " +
			"to_station, bft, no_of_articles, actual_weight,total from lr_history a, station b, lr_tracking l " +
			"where a.lr_no = l.lr_no and  l.station_code != a.from_station and a.lr_type = 'Topay' and " +
			"a.lr_status = 0 and (l.lr_status = 'Arrived') and b.station_code = l.station_code and b.branch_code like ? and " +
			"date(l.inward_time) between ? and ? ";
			

	
	public static final String GET_LR_STATUS = "SELECT station_code, lr_status FROM lr_tracking where "
			+ "lr_no = ? and (lr_status = 'arrived' or lr_status = 'toarrive')";

	public static final String GET_DAILY_BOOKING_ANALYSIS = "select count(*) as count, sum(total) as total, "
			+ "sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) as actual_weight, " 
			+"sum(charged_weight) as charged_weight, from_station,lr_date from lr where lr_status != 1 and  date(lr_date)"
			+ " between ? and ? group by from_station, date(lr_date)";
	
	public static final String GET_DAILY_BOOKING_ANALYSIS_UNION = "select count(*) as count, sum(total) as total, "
		+ "sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) as actual_weight, " 
		+"sum(charged_weight) as charged_weight, from_station,lr_date from lr where lr_status != 1 and  date(lr_date)"
		+ " between ? and ? group by from_station, date(lr_date)" +
		" union "+
		"select count(*) as count, sum(total) as total, "
		+ "sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) as actual_weight, " 
		+"sum(charged_weight) as charged_weight, from_station,lr_date from lr_history where lr_status != 1 and  date(lr_date)"
		+ " between ? and ? group by from_station, date(lr_date)";
	
	public static final String GET_DAILY_BOOKING_ANALYSIS_HISTORY = "select count(*) as count, sum(total) as total, "
		+ "sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) as actual_weight, " 
		+"sum(charged_weight) as charged_weight, from_station,lr_date from lr_history where lr_status != 1 and  date(lr_date)"
		+ " between ? and ? group by from_station, date(lr_date)";
	

	public static final String GET_COUNTER_CONSOLIDATED = "Select from_station, lr_type, rate_type, lr_Status"
			+ " from lr a, station b where b.branch_code = ? and a.from_station = b.station_code and "
			+ "date(lr_date) between ? and ?";
	
	public static final String GET_COUNTER_CONSOLIDATED_HISTORY = "Select from_station, lr_type, rate_type, lr_Status"
		+ " from lr_history a, station b where b.branch_code = ? and a.from_station = b.station_code and "
		+ "date(lr_date) between ? and ?";

	
	/*public static final String GET_SUNDRY_BOOKING_ANALYSIS = "Select from_station, count(lr_no) as lr_no, sum(total) as total, "
			+ "sum(bft) as bft, sum(no_of_articles) as no_of_articles,  sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as sundry_type from lr b,station a  where "
			+ " lr_status != 1 and a.station_code = b.from_station and a.station_code in (select station_code from station where branch_code like ? ) and (rate_type <=3 || rate_type between 7 and 9) and date(lr_date) between ? and ?  group by from_station "
			+ "union all "
			+ "Select from_station, count(lr_no) as lr_no, sum(total) as total , sum(bft) as bft, sum(no_of_articles) as no_of_articles,  sum(actual_weight) as actual_weight,"
			+ " sum(charged_weight) as charged_weight, 'Non Sundry' as sundry_type from lr b,station a where lr_status != 1 and a.station_code = b.from_station " +
			 "and a.station_code in (select station_code from station where branch_code like ? ) and (rate_type between 4 and 6 || rate_type > 9) and date(lr_date) "
			+ "between ? and ?  group by from_station ";

	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS_HISTORY =  "Select from_station, count(lr_no) as lr_no, sum(total) as total, "
		+ "sum(bft) as bft, sum(no_of_articles) as no_of_articles,  sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as sundry_type from lr_history b,station a where "
		+ " lr_status != 1 and a.station_code = b.from_station and a.station_code in (select station_code from station where branch_code like ? ) and (rate_type <=3 || rate_type between 7 and 9) and date(lr_date) between ? and ?  group by from_station "
		+ "union all "
		+ "Select from_station, count(lr_no) as lr_no, sum(total) as total , sum(bft) as bft, sum(no_of_articles) as no_of_articles,  sum(actual_weight) as actual_weight,"
		+ " sum(charged_weight) as charged_weight, 'Non Sundry' as sundry_type from lr_history b,station a where lr_status != 1 and a.station_code = b.from_station and " +
		 "a.station_code in (select station_code from station where branch_code like ? ) and (rate_type between 4 and 6 || rate_type > 9) and date(lr_date) "
		+ "between ? and ?  group by from_station ";*/

	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS = "Select from_station, count(lr_no) as lr_no, " +
			"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
			"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as " +
			"sundry_type from lr b,station a where lr_status != 1 and a.station_code = b.from_station and " +
			"a.branch_code like ? and station_code like ? and ((rate_type <=3) || (rate_type between 7 and 9)) " +
			"and date(lr_date) between ? and ? group by from_station " +
			"union " +
			"Select from_station, count(lr_no) as lr_no, " +
			"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
			"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as " +
			"sundry_type from lr b,station a where lr_status != 1 and a.station_code = b.from_station and " +
			"a.branch_code like ? and station_code like ? and ((rate_type between 4 and 6) || (rate_type > 9)) " +
			"and date(lr_date) between ? and ? group by from_station";
	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS_GROUP = "Select from_station, count(lr_no) as lr_no, " +
			"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
			"as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as sundry_type from lr b, " +
			"station a where lr_status != 1 and a.station_code = b.from_station and " +
			"a.branch_code like ?  and ((rate_type <=3) || (rate_type between 7 and 9)) and " +
			"date(lr_date) between ? and ? group by branch_code " +
			"union " +
			"Select from_station, count(lr_no) as lr_no, " +
			"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
			"as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as sundry_type from lr b, " +
			"station a where lr_status != 1 and a.station_code = b.from_station and " +
			"a.branch_code like ?  and ((rate_type between 4 and 6) || (rate_type > 9)) and " +
			"date(lr_date) between ? and ? group by branch_code";
	
	
	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS_UNION = "Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
	"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as " +
	"sundry_type from lr b,station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ? and station_code like ? and ((rate_type <=3) || (rate_type between 7 and 9)) " +
	"and date(lr_date) between ? and ? group by from_station " +
	"union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
	"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as " +
	"sundry_type from lr b,station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ? and station_code like ? and ((rate_type between 4 and 6) || (rate_type > 9)) " +
	"and date(lr_date) between ? and ? group by from_station"+
	" union " + 
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
	"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as " +
	"sundry_type from lr_history b,station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ? and station_code like ? and ((rate_type <=3) || (rate_type between 7 and 9)) " +
	"and date(lr_date) between ? and ? group by from_station " +
	"union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
	"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as " +
	"sundry_type from lr_history b,station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ? and station_code like ? and ((rate_type between 4 and 6) || (rate_type > 9)) " +
	"and date(lr_date) between ? and ? group by from_station";
	
	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS_GROUP_UNION = "Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
	"as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as sundry_type from lr b, " +
	"station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ?  and ((rate_type <=3) || (rate_type between 7 and 9)) and " +
	"date(lr_date) between ? and ? group by branch_code " +
	"union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
	"as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as sundry_type from lr b, " +
	"station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ?  and ((rate_type between 4 and 6) || (rate_type > 9)) and " +
	"date(lr_date) between ? and ? group by branch_code"+
	" union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
	"as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as sundry_type from lr_history b, " +
	"station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ?  and ((rate_type <=3) || (rate_type between 7 and 9)) and " +
	"date(lr_date) between ? and ? group by branch_code " +
	"union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
	"as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as sundry_type from lr_history b, " +
	"station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ?  and ((rate_type between 4 and 6) || (rate_type > 9)) and " +
	"date(lr_date) between ? and ? group by branch_code";
	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS_HISTORY = "Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
	"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as " +
	"sundry_type from lr_history b,station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ? and station_code like ? and ((rate_type <=3) || (rate_type between 7 and 9)) " +
	"and date(lr_date) between ? and ? group by from_station " +
	"union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, " +
	"sum(actual_weight) as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as " +
	"sundry_type from lr_history b,station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ? and station_code like ? and ((rate_type between 4 and 6) || (rate_type > 9)) " +
	"and date(lr_date) between ? and ? group by from_station";
	
	public static final String GET_SUNDRY_BOOKING_ANALYSIS_GROUP_HISTORY = "Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
	"as actual_weight, sum(charged_weight) as charged_weight, 'Sundry' as sundry_type from lr_history b, " +
	"station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ?  and ((rate_type <=3) || (rate_type between 7 and 9)) and " +
	"date(lr_date) between ? and ? group by branch_code " +
	"union " +
	"Select from_station, count(lr_no) as lr_no, " +
	"sum(total) as total, sum(bft) as bft, sum(no_of_articles) as no_of_articles, sum(actual_weight) " +
	"as actual_weight, sum(charged_weight) as charged_weight, 'Non Sundry' as sundry_type from lr_history b, " +
	"station a where lr_status != 1 and a.station_code = b.from_station and " +
	"a.branch_code like ?  and ((rate_type between 4 and 6) || (rate_type > 9)) and " +
	"date(lr_date) between ? and ? group by branch_code";

	
	
	public static final String GET_UPD_READY = " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) > ? and "
	    + "c.branch_code like ? and  c.Station_code like ? ";
			
	
	public static final String GET_UPD_READY_UNION = " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) > ? and "
	    + "c.branch_code like ? and  c.Station_code like ? "+
	    " union "+
	    " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr_history b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) > ? and "
	    + "c.branch_code like ? and  c.Station_code like ? ";
	
	
	public static final String GET_UPD_READY_HISTORY = " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr_history b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) > ? and "
	    + "c.branch_code like ? and  c.Station_code like ? ";
	

	public static final String GET_UPD_READY_EQUAL = " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) = ? and "
	    + "c.branch_code like ? and  c.Station_code like ? ";
	
	
	public static final String GET_UPD_READY_EQUAL_UNION = " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) = ? and "
	    + "c.branch_code like ? and  c.Station_code like ? "+
	    " union "+
	    " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr_history b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) = ? and "
	    + "c.branch_code like ? and  c.Station_code like ? ";

	
	public static final String GET_UPD_READY_EQUAL_HISTORY = " Select (a.station_code) as from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type, "
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total, "
	    + " datediff(now(),a.inward_time) as inwardDays ,b.cnorphone,b.cneephone from lr_tracking a, "
		+ "	lr_history b ,station c  where b.lr_status != 1 and b.from_station != a.station_code "
	    + " and c.station_code = a.station_code and a.lr_no = b.lr_no and "
	    + " a.lr_status = 'Arrived' and datediff(now(),a.inward_time) = ? and "
	    + "c.branch_code like ? and  c.Station_code like ? ";

	
	
	
	/*public static final String GET_UPD_READY = "Select b.from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type,"
			+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total,datediff(now(),a.inward_time) as inwardDays from lr_tracking a,"
			+ " lr b ,station c  where b.lr_status != 1 and b.from_station != a.station_code  and c.station_code = b.from_station and a.lr_no = b.lr_no and a.lr_status = 'Arrived' "
			+ "and datediff(now(),a.inward_time) > ? and a.station_code in (select station_code "
			+ "from station where branch_code like ? and  Station_code like ?)";
	
	public static final String GET_UPD_READY_HISTORY = "Select b.from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type,"
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total,datediff(now(),a.inward_time) as inwardDays from lr_tracking a,"
		+ " lr_history b ,station c  where b.lr_status != 1 and b.from_station != a.station_code and c.station_code = b.from_station and a.lr_no = b.lr_no and a.lr_status = 'Arrived' "
		+ "and datediff(now(),a.inward_time) > ? and a.station_code in (select station_code "
		+ "from station where branch_code like ? and  Station_code like ?)";
	

	public static final String GET_UPD_READY_EQUAL = "Select b.from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type,"
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total,datediff(now(),a.inward_time) as inwardDays from lr_tracking a,"
		+ " lr b ,station c  where b.lr_status != 1 and b.from_station != a.station_code and c.station_code = b.from_station and a.lr_no = b.lr_no and a.lr_status = 'Arrived' "
		+ "and  datediff(now(),a.inward_time) = ? and a.station_code in (select station_code "
		+ "from station where branch_code like ? and  Station_code like ?)";
	
	
	public static final String GET_UPD_READY_EQUAL_HISTORY = "Select b.from_station, b.to_station, b.lr_no, b.lr_date, b.lr_type,"
		+ " b.consignor_name, b.consignee_name, a.inward_time, a.outward_time,b.total,datediff(now(),a.inward_time) as inwardDays from lr_tracking a,"
		+ " lr_history b ,station c  where b.lr_status != 1 and b.from_station != a.station_code and c.station_code = b.from_station and a.lr_no = b.lr_no and a.lr_status = 'Arrived' "
		+ "and  datediff(now(),a.inward_time) = ? and a.station_code in (select station_code "
		+ "from station where branch_code like ? and  Station_code like ?)";*/


	public static final String GET_INWARD_REGISTER = "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,"
			+ "A.INWARD_TIME, b.total,B.FROM_STATION, B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,"
			+ "B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM LR_TRACKING A,LR B,station c  WHERE A.STATION_CODE = c.station_code And c.branch_code like ? " +
			" and c.station_code like ? and   b.lr_status != 1 and a.lr_status != 'ToArrive' and   "
			+ "a.station_code != b.from_station AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? "
			+ "union  "
			+ "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,A.INWARD_TIME, b.total,B.FROM_STATION, "
			+ "B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM "
			+ "LR_TRACKING_archive A,LR B ,station c  WHERE A.STATION_CODE = c.station_code And c.branch_code like ? " +
			" and c.station_code like ? and   b.lr_status != 1 and a.station_code != b.from_station "
			+ " and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? ";
	
	public static final String GET_INWARD_REGISTER_UNION = "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,"
		+ "A.INWARD_TIME, b.total,B.FROM_STATION, B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,"
		+ "B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM LR_TRACKING A,LR B,station c  WHERE A.STATION_CODE = c.station_code And c.branch_code like ? " +
		" and c.station_code like ? and   b.lr_status != 1 and a.lr_status != 'ToArrive' and   "
		+ "a.station_code != b.from_station AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? "
		+ "union  "
		+ "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,A.INWARD_TIME, b.total,B.FROM_STATION, "
		+ "B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM "
		+ "LR_TRACKING_archive A,LR B ,station c  WHERE A.STATION_CODE = c.station_code And c.branch_code like ? " +
		" and c.station_code like ? and   b.lr_status != 1 and a.station_code != b.from_station "
		+ " and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? "+
		" union "+
		 "SELECT distinct B.LR_NO,(b.lr_status) as count,A.lr_status, B.LR_DATE, b.lr_type,A.INWARD_TIME, b.total,B.FROM_STATION, "
		+ "B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM "
		+ "LR_TRACKING_archive_history A,LR_history B,station c  WHERE A.STATION_CODE = c.station_code And c.branch_code like ? and c.station_code like ? and   b.lr_status != 1 and  a.station_code != b.from_station "
		+ " and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? ";
	

	/*public static final String GET_INWARD_REGISTER = "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,"
			+ "A.INWARD_TIME, b.total,B.FROM_STATION, B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,"
			+ "B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM LR_TRACKING A,LR B WHERE b.lr_status != 1 and a.lr_status = 'Arrived' and  A.STATION_CODE = ? And "
			+ "a.station_code != b.from_station AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? "
			+ "union  "
			+ "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,A.INWARD_TIME, b.total,B.FROM_STATION, "
			+ "B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM "
			+ "LR_TRACKING_archive A,LR B WHERE b.lr_status != 1 and A.STATION_CODE = ? And a.station_code != b.from_station "
			+ " and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? ";*/

	
	public static final String GET_VEHICLE_NO_DELIVERED = " SELECT vehicle_no from LR_TRACKING_ARCHIVE " +
	   " WHERE lr_no = ? and dispatch_to = ? limit 1 ";
	 
	 public static final String GET_VEHICLE_NO_ARRIVED = " SELECT vehicle_no from LR_TRACKING " +
	 " WHERE lr_no = ? and dispatch_to = ? ";
	
	public static final String GET_INWARD_REGISTER_HISTORY = "SELECT distinct B.LR_NO,(b.lr_status) as count,A.lr_status, B.LR_DATE, b.lr_type,A.INWARD_TIME, b.total,B.FROM_STATION, "
		+ "B.NO_OF_ARTICLES, B.charged_WEIGHT, a.vehicle_no,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM "
		+ "LR_TRACKING_archive_history A,LR_history B,station c  WHERE A.STATION_CODE = c.station_code And c.branch_code like ? and c.station_code like ? and   b.lr_status != 1 and  a.station_code != b.from_station "
		+ " and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? ";
	 
	 /*public static final String GET_INWARD_REGISTER_HISTORY = "SELECT (b.lr_status) as count,A.lr_status, B.LR_NO,B.LR_DATE, b.lr_type,A.INWARD_TIME, b.total,B.FROM_STATION, "
			+ "B.NO_OF_ARTICLES, B.charged_WEIGHT,a.vehicle_no,B.CONSIGNOR_NAME,B.CONSIGNEE_NAME FROM "
			+ "LR_TRACKING_archive_history A,LR_history B WHERE b.lr_status != 1 and A.STATION_CODE = ? And a.station_code != b.from_station "
			+ " and a.lr_status = 'Delivered' AND A.LR_NO = B.LR_NO and date(a.inward_time) between ? and ? ";*/
	
	public static final String GET_VEHICLE_NO_DELIVERED_HISTORY = " SELECT vehicle_no from LR_TRACKING_ARCHIVE_history " +
	   " WHERE lr_no = ? and dispatch_to = ? limit 1 ";
	 

	public static final String GET_INTER_INTRA = "select count(*) as count,s.state as fromState,"
			+ "ss.state as toState,sum(bft) as bft,sum(total) as total,sum(actual_weight) as actual_weight,"
			+ "sum(charged_weight) as charged_weight,sum(no_of_articles) as no_of_articles "
			+ "from lr l , station s, station ss where l.lr_status != 1 and l.from_station = s.station_code "
			+ "and l.to_station = ss.station_code and date(l.lr_date) between ? and ? "
			+ "group by fromState,toState";
	
	public static final String GET_INTER_INTRA_UNION = "select count(*) as count,s.state as fromState,"
		+ "ss.state as toState,sum(bft) as bft,sum(total) as total,sum(actual_weight) as actual_weight,"
		+ "sum(charged_weight) as charged_weight,sum(no_of_articles) as no_of_articles "
		+ "from lr l , station s, station ss where l.lr_status != 1 and l.from_station = s.station_code "
		+ "and l.to_station = ss.station_code and date(l.lr_date) between ? and ? "
		+ "group by fromState,toState"+
		" union "+
		"select count(*) as count,s.state as fromState,"
		+ "ss.state as toState,sum(bft) as bft,sum(total) as total,sum(actual_weight) as actual_weight,"
		+ "sum(charged_weight) as charged_weight,sum(no_of_articles) as no_of_articles "
		+ "from lr_history l , station s, station ss where l.lr_status != 1 and l.from_station = s.station_code "
		+ "and l.to_station = ss.station_code and date(l.lr_date) between ? and ? "
		+ "group by fromState,toState";

	
	
	public static final String GET_INTER_INTRA_HISTORY = "select count(*) as count,s.state as fromState,"
		+ "ss.state as toState,sum(bft) as bft,sum(total) as total,sum(actual_weight) as actual_weight,"
		+ "sum(charged_weight) as charged_weight,sum(no_of_articles) as no_of_articles "
		+ "from lr_history l , station s, station ss where l.lr_status != 1 and l.from_station = s.station_code "
		+ "and l.to_station = ss.station_code and date(l.lr_date) between ? and ? "
		+ "group by fromState,toState";

	
	public static final String GET_MISSING_CUSTOMER_CUR_MONTH = "select c.name,sum(l.bft) as bft, sum(l.charged_weight) as "
			+ "charged_weight from customer c , lr l where l.lr_status != 1 and month(l.lr_date) = month(now()) and "
			+ "year(l.lr_date) = year(now()) and (c.name = l.consignor_name or c.name = l.consignee_name) "
			+ "and c.name in (select name from missing_customers where is_last_month =1) group by (c.name) ";
	
	
	public static final String GET_MISSING_CUSTOMER_CUR_MONTH_HISTORY = "select c.name,sum(l.bft) as bft, sum(l.charged_weight) as "
		+ "charged_weight from customer c , lr_history l where l.lr_status != 1 and month(l.lr_date) = month(now()) and "
		+ "year(l.lr_date) = year(now()) and (c.name = l.consignor_name or c.name = l.consignee_name) "
		+ "and c.name in (select name from missing_customers where is_last_month =1) group by (c.name) ";

	public static final String GET_MISSING_CUSTOMER_LAST_MONTH = "select name,lr_date,bft,charged_weight "
			+ "from missing_customers where is_last_month = 1";

	public static final String GET_MISSING_CUSTOMER_YEAR = "select name,lr_date,bft,charged_weight "
			+ "from missing_customers where is_last_month = 0 "
			+ "and name in (select name from missing_customers "
			+ "where is_last_month = 1)";
	
	public static final String GET_DRS_ATTENDANCE = "SELECT count(a.drs_date) as count, a.station_code, a.drs_date, "
		+ "if(a.drs_date=inwarded_date,'ontime','total') as status, b.branch_code FROM daily_remittance_register a, "
		+ "station b where a.station_code = b.station_code and branch_code like ? and ((month(drs_date) = ? "
		+ "and year(drs_date) = ?) or (month(drs_date) = ? and year(drs_date) = ?) or (month(drs_date) = ? "
		+ "and year(drs_date) = ?)) group by a.drs_date != inwarded_date, month(a.drs_date), a.station_code ";
		/*
		 * "SELECT count(a.drs_date) as
		 * count,a.station_code,a.drs_date,if(a.drs_date=inwarded_date," +
		 * "'ontime','total') as status,b.branch_code FROM
		 * daily_remittance_register a,station b " + "where
		 * a.station_code=b.station_code and branch_code like ? and
		 * ((month(drs_date) =? and " + "year(drs_date)=?) or (month(drs_date) =?
		 * and year(drs_date)=?) or (month(drs_date) =?" + " and
		 * year(drs_date)=?)) and date(drs_date) not in(select holiday_date from
		 * holiday_entry) " + "and dayname(drs_date)!='sunday' group by
		 * a.drs_date!=inwarded_date, month(a.drs_date)," + " a.station_code"
		 */
	
	public static final String GET_DRS_ATTENDANCE_SUMMARY=/*
															 * "SELECT
															 * count(a.drs_date)
															 * as
															 * count,a.station_code," +
															 * "a.drs_date,'status'
															 * as
															 * status,b.branch_code
															 * FROM
															 * daily_remittance_register
															 * a,station b" + "
															 * where
															 * a.station_code=b.station_code
															 * and branch_code
															 * like ? and
															 * date(drs_date) " +
															 * "between ? and ?
															 * group by
															 * day(a.drs_date),a.station_code"
															 */
		/*
		 * "SELECT count(a.drs_date) as count,a.station_code,a.drs_date,'status'
		 * as status,b.branch_code FROM " + "daily_remittance_register a,station
		 * b where a.station_code=b.station_code and branch_code like ?" + " and
		 * date(drs_date) between ? and ? and date(drs_date) not in(select " +
		 * "holiday_date from holiday_entry) and dayname(drs_date)!='sunday'
		 * group by day(a.drs_date)," + "a.station_code"
		 */
		"SELECT	if(to_days(inwarded_date)-to_days(drs_date) > drs_duration, "
				+ "to_days(inwarded_date)-to_days(drs_date),0)as count,a.station_code ,a.drs_date, 'status' as status, "
				+ "b.branch_code FROM daily_remittance_register a,station b where a.station_code = b.station_code and "
				+ "branch_code like ? and date(drs_date) between ? and ? "
				+ "group by day(a.drs_date), a.station_code ";

	
	public static final String GET_OPEN_LR = "select b.branch_code,b.discount,from_station,to_station, lr_no, lr_date,lr_type,consignor_name,consignee_name,actual_bft," +
	"bft,ccc,charged_weight,other_charges,ddc, total from lr a,station b " +
	" where a.lr_status != 1  and a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type >6 and date(lr_date) between ? and ?";
	
	public static final String GET_OPEN_LR_HISTORY = "select b.branch_code,b.discount,from_station,to_station, lr_no, lr_date,lr_type,consignor_name,consignee_name,actual_bft," +
	"bft,ccc,charged_weight,other_charges,ddc, total from lr_history a,station b " +
	" where a.lr_status != 1  and a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type >6 and date(lr_date) between ? and ?";
	
	
	public static final String GET_OPEN_LR_UNION ="select b.branch_code,b.discount,from_station,to_station, lr_no, lr_date,lr_type,consignor_name,consignee_name,actual_bft," +
	"bft,ccc,charged_weight,other_charges,ddc, total from lr a,station b " +
	" where a.lr_status != 1  and a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type >6 and date(lr_date) between ? and ?"+
	" union " +
	"select b.branch_code,b.discount,from_station,to_station, lr_no, lr_date,lr_type,consignor_name,consignee_name,actual_bft," +
	"bft,ccc,charged_weight,other_charges,ddc, total from lr_history a,station b " +
	" where a.lr_status != 1  and a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type >6 and date(lr_date) between ? and ?";
	
	
	public static final String GET_FOC_LR_DETAIL = "select lr_no, lr_date,from_station,to_station,no_of_articles,charged_weight,article_id," +
			"article_value from lr a,station b where a.lr_status != 1 and  a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type =6 and date(lr_date) between ? and ?";
	
	public static final String GET_FOC_LR_DETAIL_UNION = "select lr_no, lr_date,from_station,to_station,no_of_articles,charged_weight,article_id," +
	"article_value from lr a,station b where a.lr_status != 1 and  a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type =6 and date(lr_date) between ? and ?"+
	" union "+
	"select lr_no, lr_date,from_station,to_station,no_of_articles,charged_weight,article_id," +
	"article_value from lr_history a,station b where a.lr_status != 1 and  a.from_station=b.station_code AND b.branch_code like ? " +
	" AND rate_type =6 and date(lr_date) between ? and ?";

	
	public static final String GET_FOC_LR_DETAIL_HISTORY = "select lr_no, lr_date,from_station,to_station,no_of_articles,charged_weight,article_id," +
		"article_value from lr_history a,station b where a.lr_status != 1 and  a.from_station=b.station_code AND b.branch_code like ? " +
		" AND rate_type =6 and date(lr_date) between ? and ?";
	
	
	public static final String GET_FOC_LR_SUMMARY="select branch_code,from_station,count(lr_no)  as count,rate_type," +
			"sum(charged_weight) as charged_weight from lr a,station b where a.lr_status != 1 and rate_type is not null AND branch_code like ? " +
			" and a.from_station=b.station_code AND date(lr_date) between ? AND ?" +
			" group by from_station,rate_type=6";
	
	public static final String GET_FOC_LR_SUMMARY_UNION="select branch_code,from_station,count(lr_no)  as count,rate_type," +
	"sum(charged_weight) as charged_weight from lr a,station b where a.lr_status != 1 and rate_type is not null AND branch_code like ? " +
	" and a.from_station=b.station_code AND date(lr_date) between ? AND ?" +
	" group by from_station,rate_type=6"+
	" union "+
	"select branch_code,from_station,count(lr_no)  as count,rate_type," +
	"sum(charged_weight) as charged_weight from lr_history a,station b where a.lr_status != 1 and rate_type is not null AND branch_code like ? " +
	" and a.from_station=b.station_code AND date(lr_date) between ? AND ?" +
	" group by from_station,rate_type=6";
	
	
	public static final String GET_FOC_LR_SUMMARY_HISTORY="select branch_code,from_station,count(lr_no)  as count,rate_type," +
			"sum(charged_weight) as charged_weight from lr_history a,station b where a.lr_status != 1 and rate_type is not null AND branch_code like ? " +
			" and a.from_station=b.station_code AND date(lr_date) between ? AND ?" +
			" group by from_station,rate_type=6";
	
	
	public static final String  GET_MARKET_VEHICLE_BRANCH="SELECT count(a.name) as count,a.name,to_station,model_no,sum(rate) as rate FROM " +
			"market_vehicle a,station b where a.from_station=b.station_code AND  branch_code like ? " +
			"AND month(vehicle_date)=? AND year(vehicle_date)=?	group by to_station,model_no";
	
	public static final String  GET_MARKET_VEHICLE="SELECT count(a.name) as count,a.name,to_station,model_no,sum(rate) as rate FROM " +
		"market_vehicle a,station b where a.to_station=b.station_code AND  branch_code like ? " +
		"AND month(vehicle_date)=? AND year(vehicle_date)=?	group by branch_code,model_no";

	/*public static final String  GET_HIGH_BPA = "select max(a.bft) as high_bft,max(a.total) as high_total," +
			" max(a.actual_weight) as high_actual_weight,max(a.charged_weight) as high_charged_weight," +
			" max(a.no_of_articles) as high_no_of_articles from lr a, station s where a.lr_status != 1 and date(a.lr_date) " +
			"between date_sub(curdate(), interval 18 month) and curdate() and a.from_station = s.station_code " +
			"and a.from_station = ?";
	
	public static final String  GET_HIGH_BPA_HISTORY = "select max(a.bft) as high_bft,max(a.total) as high_total," +
			" max(a.actual_weight) as high_actual_weight,max(a.charged_weight) as high_charged_weight," +
			" max(a.no_of_articles) as high_no_of_articles from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) " +
			"between date_sub(curdate(), interval 18 month) and curdate() and a.from_station = s.station_code " +
			"and a.from_station = ?";
	
	
	public static final String  GET_HIGH_BPA_BRANCH = "select max(a.bft) as high_bft,max(a.total) as high_total," +
	" max(a.actual_weight) as high_actual_weight,max(a.charged_weight) as high_charged_weight," +
	" max(a.no_of_articles) as high_no_of_articles from lr a, station s where a.lr_status != 1 and date(a.lr_date) " +
	"between date_sub(curdate(), interval 18 month) and curdate() and a.from_station = s.station_code " +
	"and S.BRANCH_CODE = ?";
	
	public static final String  GET_HIGH_BPA_BRANCH_HISTORY = "select max(a.bft) as high_bft,max(a.total) as high_total," +
	" max(a.actual_weight) as high_actual_weight,max(a.charged_weight) as high_charged_weight," +
	" max(a.no_of_articles) as high_no_of_articles from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) " +
	"between date_sub(curdate(), interval 18 month) and curdate() and a.from_station = s.station_code " +
	"and S.BRANCH_CODE = ?";*/
	
	public static final String  GET_BPA = "select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and date(lr_date) between ? and ?" +
	" group by from_station";
	
	public static final String  GET_BPA_UNION = "select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and date(lr_date) between ? and ?" +
	" group by from_station" + 
	" union "+
	" select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr_history a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and date(lr_date) between ? and ?" +
	" group by from_station";
	
	public static final String  GET_BPA_HISTORY = "select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr_history a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and date(lr_date) between ? and ?" +
	" group by from_station";
	
	
	public static final String  GET_BPA_BRANCH = "select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
			" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
			" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
			" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr a,station b where a.lr_status != 1" +
			" and a.from_station = b.station_code and b.branch_code like ? and date(lr_date) between ? and ?" +
			" group by from_station";
	
	
	public static final String  GET_BPA_BRANCH_UNION = "select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and b.branch_code like ? and date(lr_date) between ? and ?" +
	" group by from_station"+
	" union " +
	"select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr_history a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and b.branch_code like ? and date(lr_date) between ? and ?" +
	" group by from_station";

	
	public static final String  GET_BPA_BRANCH_HISTORY = "select from_station,count(a.lr_no) as lr_no, sum(a.no_of_articles) as no_of_articles," +
	" sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as charged_weight, sum(a.bft) as bft," +
	" sum(a.lrc) as lrc ,sum(a.dhc) as dhc,sum(a.ccc) as ccc ,sum(a.dcc) as dcc, sum(ddc) as ddc, sum(iec) as iec ," +
	" sum(a.lc) as lc , sum(a.stax) as stax,sum(a.total) as total	from lr_history a,station b where a.lr_status != 1" +
	" and a.from_station = b.station_code and b.branch_code like ? and date(lr_date) between ? and ?" +
	" group by from_station";
	
	
	public static final String GET_UPD_READY_PHONE = "Select phone,landline from customer where name = ?";

	public static final String INSERT_LESS_REMITTANCE = "insert into daily_remittance_details "
		+ "(dr_no,date,station_code,lr_no,lr_amount)values(?,?,?,?,?)";

	public static final String UPDATE_ADD_REMITTANCE = "update daily_remittance_details set paid_station = ?,paid_date = ? "
		+ " where lr_no = ?";
	
	public static final String GET_VALID_REMITTANCE_LR = "select 0 as status,a.lr_no,a.total as lrAmount "
		+ "from lr a,customer b where ((a.from_station = ? and a.lr_type='paid' and b.no_of_days > 0 "
		+ "and b.remittance_shortage = 1 and b.quotation_id is not null and (b.name=a.consignor_name or b.name=a.consignee_name)) or "
		+ "(a.delivered_station = ? and a.lr_type='topay' and a.lr_status=3 and b.no_of_days > 0 and "
		+ "b.remittance_shortage = 1 and b.quotation_id is not null and (b.name=a.consignor_name or b.name=a.consignee_name))) and "
		+ "a.lr_no not in(select lr_no from daily_remittance_details) "
		+ "union all "
		+ "select 1 as status,b.lr_no,b.lr_amount as lrAmount from daily_remittance_details b "
		+ "where b.paid_station is  null and b.paid_date is null";

	public static final String GET_VALID_REMITTANCE_LR_HISTORY = "select 0 as status,a.lr_no,a.total as lrAmount "
		+ "from lr_history a,customer b where ((a.from_station = ? and a.lr_type='paid' and b.no_of_days > 0 "
		+ "and b.remittance_shortage = 1 and b.quotation_id is not null and (b.name=a.consignor_name or b.name=a.consignee_name)) or "
		+ "(a.delivered_station = ? and a.lr_type='topay' and a.lr_status=3 and b.no_of_days > 0 and "
		+ "b.remittance_shortage = 1 and b.quotation_id is not null and (b.name=a.consignor_name or b.name=a.consignee_name))) and "
		+ "a.lr_no not in(select lr_no from daily_remittance_details) "
		+ "union all "
		+ "select 1 as status,b.lr_no,b.lr_amount as lrAmount from daily_remittance_details b "
		+ "where b.paid_station is  null and b.paid_date is null";
	
		public static final String CALCULATE_DOOR_DELIVERY_HISTORY = "SELECT "
			+ "sum(a.ddc) + sum(b.ddextra) as DDCHARGES "
			+ "FROM "
			+ "lr_history a, cash_receipt_history b "
			+ "WHERE  b.station_code = ? and b.status != 1 and date(b.date) = ? and  "
			+ "a.delivered_station= b.station_code and  a.lr_type = 'TOPAY'   and a.lr_no = b.lr_no "
			+
		
			"UNION "
			+
		
			"SELECT "
			+ "sum(a.ddc) + sum(b.ddextra) as DDCHARGES "
			+ "FROM "
			+ "lr_tracking_archive_history c, lr_history a LEFT JOIN cash_receipt_history b on a.lr_no = b.lr_no "
			+ "WHERE " + "c.station_code = ? and c.lr_status ='"
			+ DELIVERED_STATUS + "' and date(c.outward_time) = ? "
			+ "and c.lr_no = a.lr_no and a.lr_type != 'TOPAY'";
		
		
	
	public static final String GET_GOODS_MOVEMENT_VEHICLE_HISTORY = "SELECT "
		+ "a.lr_no, a.from_station, a.to_station, a.consignor_name,"
		+ " a.consignee_name,a.actual_weight,a.no_of_articles, c.station_code, c.lr_status, c.vehicle_no, "
		+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr_history a, lr_tracking c WHERE c.station_code = ? and "
		+ "(c.lr_status = '"
		+ DISPATCH_STATUS
		+ "') and "
		+ "a.lr_no = c.lr_no and "
		+ "date(c.outward_time) = ? and c.vehicle_no = ? group by c.outward_time "
		+ "UNION "
		+ "SELECT "
		+ "a.lr_no, a.from_station, a.to_station, a.consignor_name,"
		+ " a.consignee_name,a.actual_weight,a.no_of_articles, c.station_code, c.lr_status, c.vehicle_no, "
		+ "c.driver_name, c.outward_time, c.dispatch_to FROM lr_history a, lr_tracking c WHERE c.station_code = ? and "
		+ "(c.lr_status = '"
		+ DELIVERED_STATUS
		+ "') and "
		+ "a.lr_no = c.lr_no and "
		+ "date(c.outward_time) = ? and c.vehicle_no = ? group by c.outward_time ";
	
	public static final String GET_CURRENT_STATUS_HISTORY = "SELECT b.ddc,b.total,b.article_value,A.STATION_CODE,"
		+ "A.INWARD_TIME,A.LR_STATUS,B.LR_NO,B.LR_DATE,B.FROM_STATION,B.TO_STATION,B.ACTUAL_WEIGHT,B.NO_OF_ARTICLES,"
		+ "B.CONSIGNOR_NAME,B.CONSIGNEE_NAME,A.LR_NO FROM LR_TRACKING A,LR_history B WHERE B.From_stATION = ? "
		+ "AND A.LR_NO=B.LR_NO AND (A.LR_STATUS = 'Arrived' OR A.LR_STATUS='TOARRIVE')";
	
	public static final String GET_BOOKEDLR_COUNT_HISTORY = "SELECT count(l.lr_no) as lrCount, l.from_station as station_code, "
		+ "l.lr_type FROM lr_history l,station a where l.from_station = a.station_code and "
		+ "date(lr_date) between ? and ? and branch_code = ? group by from_station,lr_type";
	
	public static final String GET_SERVICETAX = "select from_station,sum(total) as total ,sum(stax) as stax from lr where stax != 0 and lr_status != 1 " +
			" and date(lr_date) between ? and ? group by from_station ";
	
	public static final String GET_SERVICETAX_UNION = "select from_station,sum(total) as total ,sum(stax) as stax from lr where stax != 0 and lr_status != 1 " +
	" and date(lr_date) between ? and ? group by from_station "+
	" union " +
	"select from_station,sum(total) as total ,sum(stax) as stax from lr_history where stax != 0 and lr_status != 1" +
	" and date(lr_date) between ? and ? group by from_station ";

	
	public static final String GET_SERVICETAX_HISTORY = "select from_station,sum(total) as total ,sum(stax) as stax from lr_history where stax != 0 and lr_status != 1" +
			" and date(lr_date) between ? and ? group by from_station ";
	
	public static final String GET_SERVICETAX_LR = "select lr_no,date(lr_date) as lr_date ,from_station,to_station,consignor_name,consignee_name,total,stax from lr " +
									"where stax != 0 and lr_status != 1 and date(lr_date) between ? and ? ";
	
	public static final String GET_SERVICETAX_LR_UNION = "select lr_no,date(lr_date) as lr_date ,from_station,to_station,consignor_name,consignee_name,total,stax from lr " +
	"where stax != 0 and lr_status != 1 and date(lr_date) between ? and ? "+
	" union "+
	"select lr_no,date(lr_date) as lr_date,from_station,to_station,consignor_name,consignee_name,total,stax from lr_history " +
	"where stax != 0 and lr_status != 1 and date(lr_date) between ? and ? ";

	
	public static final String GET_SERVICETAX_LR_HISTORY = "select lr_no,date(lr_date) as lr_date,from_station,to_station,consignor_name,consignee_name,total,stax from lr_history " +
									"where stax != 0 and lr_status != 1 and date(lr_date) between ? and ? ";
	
	
	
}
