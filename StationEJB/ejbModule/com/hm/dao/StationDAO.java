/**
 * 
 * @version 1.0
 */
package com.hm.dao;

import hm.akr.dto.CCProfileDTO;
import hm.akr.dto.CardRateProfileDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.StationsDTO;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * DAO class for Station
 * 
 */
public class StationDAO implements IStationDAO, DAOConstants {

	/**
	 * Constructor Method
	 */
	public StationDAO() {
		super();

	}

	/**
	 * 
	 */
	public boolean addStation(String branch, StationsDTO dto)
			throws SQLException, Exception {

		System.out.println("In StationDAO addStation===>" + dto.getId());
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;
		try {
			con = db.getConnection();
			if (con != null) {

				ps = con.prepareStatement(ADD_STATION);

				if (ps != null) {

					ps.setString(1, dto.getId());
					ps.setString(2, dto.getName());
					// ps.setString(3, dto.getCode());
					ps.setString(4, dto.getType());
					ps.setString(5, dto.getAddress());
					ps.setString(6, dto.getCity());
					ps.setString(7, dto.getState());
					ps.setInt(8, dto.getPin());
					ps.setString(9, dto.getMobile());
					ps.setString(10, dto.getPhone1());
					ps.setString(11, dto.getPhone2());
					ps.setString(12, dto.getOwner());
					ps.setString(13, branch);
					ps.setInt(14, dto.getDiscount());

					result = ps.executeUpdate();

					if (result > 0) {
						flag = true;
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				ps.close();
				ps = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (ps != null)
					ps.close();
				if (con != null)
					ps.close();
			}

		}
		System.out.println("Out StationDAO addStation===>" + flag);
		return flag;
	}

	/**
	 * 
	 */
	public boolean deleteStation(int id) throws SQLException, Exception {
		System.out.println("In StationDAO deleteStation===>" + id);
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;

		try {

			con = db.getConnection();

			if (con != null) {

				ps = con.prepareStatement(DEL_STATION);

				if (ps != null) {

					ps.setInt(1, id);

					result = ps.executeUpdate();

					if (result > 0) {

						flag = true;
					}
				}
			}

		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {

			try {

				ps.close();
				ps = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (ps != null)
					ps.close();

				if (con != null)
					con.close();

			}
		}

		System.out.println("Out StationDAO getAllStations===>" + flag);
		return flag;
	}

	/**
	 * 
	 */
	public boolean addBranch(String region, StationsDTO dto)
			throws SQLException, Exception {

		System.out
				.println("In StationDAO addBranch===>" + dto.getBranch_code());
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;

		try {
			con = db.getConnection();
			if (con != null) {

				ps = con.prepareStatement(ADD_BRANCH);

				if (ps != null) {

					ps.setString(1, dto.getId());
					ps.setString(2, dto.getName());
					// ps.setString(3, dto.getCode());
					ps.setString(4, dto.getAddress());
					ps.setString(5, dto.getCity());
					ps.setString(6, dto.getState());
					ps.setInt(7, dto.getPin());
					ps.setString(8, dto.getMobile());
					ps.setString(9, dto.getPhone1());
					ps.setString(10, dto.getPhone2());
					ps.setString(11, dto.getOwner());
					ps.setString(12, region);
					ps.setInt(13, dto.getDiscount());

					result = ps.executeUpdate();

					if (result > 0) {
						flag = true;
					}
				}

			}

		} catch (SQLException sqlexception) {

			throw sqlexception;

		} catch (Exception exception) {

			throw exception;
		} finally {

			try {

				ps.close();
				ps = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (ps != null)
					ps.close();

				if (con != null)
					con.close();

			}
		}
		System.out.println("Out StationDAO addBranch===>" + flag);
		return flag;
	}

	/**
	 * 
	 */
	public boolean deleteBranch(int id) throws SQLException, Exception {

		System.out.println("In StationDAO deleteBranch===>" + id);
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;

		try {

			con = db.getConnection();

			if (con != null) {

				ps = con.prepareStatement(DEL_BRANCH);

				if (ps != null) {

					ps.setInt(1, id);

					result = ps.executeUpdate();

					if (result > 0) {

						flag = true;
					}
				}
			}

		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {

			try {

				ps.close();
				ps = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (ps != null)
					ps.close();

				if (con != null)
					con.close();

			}
		}
		System.out.println("Out StationDAO deleteBranch===>" + flag);
		return flag;

	}

	/**
	 * 
	 */
	public boolean addRegion(StationsDTO dto) throws SQLException, Exception {

		System.out.println("In StationDAO addRegion===>" + dto.getId());
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;

		try {
			con = db.getConnection();
			if (con != null) {

				ps = con.prepareStatement(ADD_REGION);

				if (ps != null) {

					ps.setString(1, dto.getId());
					ps.setString(2, dto.getName());
					// ps.setString(3, dto.getCode());
					ps.setString(4, dto.getAddress());
					ps.setString(5, dto.getCity());
					ps.setString(6, dto.getState());
					ps.setInt(7, dto.getPin());
					ps.setString(8, dto.getMobile());
					ps.setString(9, dto.getPhone1());
					ps.setString(10, dto.getPhone2());
					ps.setString(11, dto.getOwner());

					result = ps.executeUpdate();

					if (result > 0) {
						flag = true;
					}
				}

			}

		} catch (SQLException sqlexception) {

			throw sqlexception;

		} catch (Exception exception) {

			throw exception;
		} finally {

			try {

				ps.close();
				ps = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (ps != null)
					ps.close();

				if (con != null)
					con.close();

			}
		}
		System.out.println("Out StationDAO addRegion===>" + flag);
		return flag;

	}

	/**
	 * 
	 */
	public boolean deleteRegion(int id) throws SQLException, Exception {

		System.out.println("In StationDAO deleteRegion===>" + id);
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;
		int result = 0;

		try {

			con = db.getConnection();

			if (con != null) {

				ps = con.prepareStatement(DEL_REGION);

				if (ps != null) {

					ps.setInt(1, id);

					result = ps.executeUpdate();

					if (result > 0) {

						flag = true;
					}
				}
			}

		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {

			try {

				ps.close();
				ps = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (ps != null)
					ps.close();

				if (con != null)
					con.close();

			}
		}
		System.out.println("Out StationDAO deleteRegion===>" + flag);
		return flag;

	}

	public StationsDTO[] getStations() throws SQLException, Exception {

		System.out.println("In StationDAO getStations===>");
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
		StationsDTO station = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_STATIONS);

				if (pst != null) {

					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							station = new StationsDTO();

							station.setId(rs.getString(STATION_CODE));
							station.setName(rs.getString(STATION_NAME));
							station.setType(rs.getString(TYPE));
							station.setAddress(rs.getString(ADDRESS));
							station.setCity(rs.getString(CITY));
							station.setState(rs.getString(STATE));

							station.setMobile(rs.getString(MOBILE));
							station.setPhone1(rs.getString(PHONE1));
							// station.setPhone2(rs.getString(PHONE2));
							station.setOwner(rs.getString(OWNER));
							station.setBf_increment(rs.getInt(BFINCREMENT));
							station.setDiscount(rs.getInt(DISCOUNT));
							station.setBranch_code(rs.getString(BRANCH_CODE));
							station.setBpi(rs.getFloat(BPI));
							station.setLrcharge(rs.getFloat(LRCHARGE));
							station.setGsc(rs.getFloat(GSC));
							station.setMin_freight_value(rs
									.getFloat(MIN_FREIGHT));
							station
									.setMin_weight_value(rs
											.getFloat(MIN_WEIGHT));

							station.setCc_limit(rs.getInt(CC_LIMIT));
							station.setCc_special(rs.getInt(CC_SPECIAL));
							station.setCc_commodity(rs.getInt(CC_COMMODITY));
							station.setCc_hlc(rs.getFloat(CC_HLC));
							station.setPin(rs.getByte(LANGUAGE));
							station.setCc_consider(rs.getInt(CC_CONSIDER));
							station.setCc_refund(rs.getInt(CC_REFUND));
							station.setRefundperarticle(rs
									.getFloat(REFUND_ARTICLE));
							station.setDeliveryCommission(rs
									.getFloat(DC_COMMISSION));
							station.setDhc(rs.getFloat(DHC));

							list.add(station);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out StationDAO getStations===>" + size);
		if (size > 0) {
			return (StationsDTO[]) list.toArray(new StationsDTO[size]);
		}

		return null;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getBranches() throws SQLException, Exception {

		System.out.println("In StationDAO getBranches===>");
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
		StationsDTO station = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_BRANCHES);

				if (null != pst) {
					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							station = new StationsDTO();

							station.setId(rs.getString(BRANCH_CODE));
							station.setName(rs.getString(STATION_NAME));
							// station.setType(rs.getString("type"));
							station.setAddress(rs.getString(ADDRESS));
							station.setCity(rs.getString(CITY));
							station.setState(rs.getString(STATE));
							station.setPin(rs.getInt(PIN));
							station.setMobile(rs.getString(MOBILE));
							station.setPhone1(rs.getString(PHONE1));
							station.setPhone2(rs.getString(PHONE2));
							station.setOwner(rs.getString(OWNER));
							// station.setDiscount(rs.getInt("discount"));
							station.setRegion_code(rs.getString("region_code"));

							list.add(station);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out StationDAO getBranches===>" + size);
		if (size > 0) {
			return (StationsDTO[]) list.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	public StationsDTO[] getAllStations() throws SQLException, Exception {
		System.out.println("In StationDAO getAllStations===>");

		StationsDTO[] stations = null;

		try {
			stations = getStations();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return stations;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getSelectedStations(String stationCode)
			throws SQLException, Exception {

		System.out.println("In StationDAO getSelectedStations===>"
				+ stationCode);
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
		StationsDTO station = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_SELECTED_STATIONS);

				if (null != pst) {

					pst.setString(1, stationCode);
					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							station = new StationsDTO();

							station.setId(rs.getString(STATION_CODE));
							station.setName(rs.getString(STATION_NAME));

							list.add(station);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out StationDAO getSelectedStations===>" + size);
		if (size > 0) {
			return (StationsDTO[]) list.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getTRs() throws SQLException, Exception {

		System.out.println("In StationDAO getSelectedStations===>");
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
		StationsDTO station = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_TR);

				if (null != pst) {

					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							station = new StationsDTO();

							station.setId(rs.getString("region_code"));
							station.setName(rs.getString(STATION_NAME));

							list.add(station);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out StationDAO getTRs===>" + size);
		if (size > 0) {
			return (StationsDTO[]) list.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	public boolean setStationaryValues(LRNumberRangeDTO[] lrFormat)
			throws Exception {
		System.out.println("In StationDAO Stationary set===>" + lrFormat);
		Connection con = null;
		PreparedStatement pst = null;
		boolean result = false;
		boolean flag = false;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				con.setAutoCommit(false);
				if (lrFormat != null) {

					flag = checkTable(con, lrFormat[0].getBranchCode());

					if (flag) {
						flag = delStationary(con, lrFormat[0].getBranchCode());

						if (flag) {
							pst = con.prepareStatement(INSERT_STATIONARY);
							if (null != pst) {
								int batch_count = lrFormat.length;
								System.out
										.println("In StationDAO Stationary Size===>"
												+ batch_count);
								for (int i = 0; i < batch_count; i++) {
									pst.setString(1, lrFormat[i]
											.getStationCode());
									pst.setInt(2, lrFormat[i].getTopay());
									pst.setInt(3, lrFormat[i].getPaid());
									pst.setInt(4, lrFormat[i].getBilling());
									pst.setInt(5, lrFormat[i].getCr());
									pst.setString(6, lrFormat[i]
											.getBranchCode());
									pst.addBatch();
								}

								pst.executeBatch();
								result = true;
								con.commit();
							}
						}
					} else {
						pst = con.prepareStatement(INSERT_STATIONARY);
						if (null != pst) {
							int batch_count = lrFormat.length;
							System.out
									.println("In StationDAO Stationary Size===>"
											+ batch_count);
							for (int i = 0; i < batch_count; i++) {
								pst.setString(1, lrFormat[i].getStationCode());
								pst.setInt(2, lrFormat[i].getTopay());
								pst.setInt(3, lrFormat[i].getPaid());
								pst.setInt(4, lrFormat[i].getBilling());
								pst.setInt(5, lrFormat[i].getCr());
								pst.setString(6, lrFormat[i].getBranchCode());
								pst.addBatch();
							}

							pst.executeBatch();
							result = true;
							con.commit();
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		return result;
	}

	/**
	 * 
	 * @param con
	 * @param branchCode
	 * @return
	 * @throws Exception
	 */
	private boolean checkTable(Connection con, String branchCode)
			throws Exception {
		boolean flag = false;
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(CHECK_STATIONARY);
			if (pst != null) {
				pst.setString(1, branchCode);

				ResultSet rs = pst.executeQuery();
				if (null != rs) {
					rs.next();
					if (rs.getInt(1) > 0) {
						flag = true;
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

		return flag;
	}

	/**
	 * 
	 * @param con
	 * @param branchCode
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private boolean delStationary(Connection con, String branchCode)
			throws SQLException, Exception {
		boolean flag = false;
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(DELETE_STATIONARY);
			if (pst != null) {
				pst.setString(1, branchCode);

				int result = pst.executeUpdate();

				if (result > 0) {
					flag = true;
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

		return flag;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryValues() throws SQLException,
			Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<LRNumberRangeDTO> list = new ArrayList<LRNumberRangeDTO>();
		LRNumberRangeDTO lrFormat = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_STATIONARY);

				if (null != pst) {

					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							lrFormat = new LRNumberRangeDTO();

							lrFormat.setBranchCode(rs.getString(BRANCH_CODE));
							lrFormat.setStationCode(rs.getString(STATION_CODE));
							lrFormat.setTopay(rs.getInt(TOPAY));
							lrFormat.setPaid(rs.getInt(PAID));
							lrFormat.setBilling(rs.getInt(BILLING));
							lrFormat.setCr(rs.getInt(CR));
							list.add(lrFormat);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out StationDAO getStationaryValues ===>" + size);
		if (size > 0) {
			return (LRNumberRangeDTO[]) list
					.toArray(new LRNumberRangeDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param stations
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(String[] stations, int[] types)
			throws Exception {

		System.out.println("In StationDAO assignStationary stations===>"
				+ stations);
		Connection con = null;
		PreparedStatement pst = null;
		CallableStatement cs = null;
		boolean result = false;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				int in = 0;
				for (int i = 0; i < stations.length; i++) {

					cs = con
							.prepareCall("{call assignstationary(?,?,?,?,?,?)}");
					cs.setString(1, stations[i]);
					cs.setInt(2, types[0]);
					cs.setInt(3, types[1]);
					cs.setInt(4, types[2]);
					cs.setInt(5, types[3]);
					cs.setInt(6, in);
					cs.registerOutParameter(6, java.sql.Types.INTEGER);

					cs.executeUpdate();

					int status = cs.getInt(6);

					if (status == 1) {
						System.out
								.println("Out StationDAO Stationary Settings Not Assigned");
						return false;
					}
				}
				System.out
						.println("Out StationDAO Stationary Settings Not Assigned");
				result = true;
			}
		} catch (SQLException sqlexception) {
			result = false;
			throw sqlexception;
		} catch (Exception exception) {
			result = false;
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		return result;
	}

	/**
	 * 
	 * @param stationary
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(LRNumberRangeDTO[] stationary)
			throws Exception {
		System.out.println("To assign Stationary II");
		Connection con = null;
		PreparedStatement pst = null;
		CallableStatement cs = null;
		boolean result = false;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				con.setAutoCommit(false);
				int in = 0;
				for (int i = 0; i < stationary.length; i++) {

					cs = con
							.prepareCall("{call assignstationary(?,?,?,?,?,?)}");
					cs.setString(1, stationary[i].getStationCode());
					cs.setInt(2, stationary[i].getTopay());
					cs.setInt(3, stationary[i].getPaid());
					cs.setInt(4, stationary[i].getBilling());
					cs.setInt(5, stationary[i].getCr());
					cs.setInt(6, in);
					cs.registerOutParameter(6, java.sql.Types.INTEGER);

					cs.executeUpdate();

					int status = cs.getInt(6);

					if (status == 1) {
						System.out
								.println("Out StationDAO Stationary Settings Not Assigned");
						con.rollback();
						return false;
					}
				}
				System.out
						.println("Out StationDAO Stationary Settings  Assigned");
				result = true;
				result = removeFromReport(con, stationary);
				if (result) {
					con.commit();
				}
			}
		} catch (SQLException sqlexception) {
			con.rollback();
			result = false;
			throw sqlexception;
		} catch (Exception exception) {
			con.rollback();
			result = false;
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		return result;
	}

	/**
	 * 
	 * @param con
	 * @param stationary
	 * @throws Exception
	 */
	private boolean removeFromReport(Connection con,
			LRNumberRangeDTO[] stationary) throws Exception {

		System.out.println("To Delete Stationary Report");
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			if (con != null) {

				ps = con.prepareStatement(DEL_STATIONARY_REPORT);

				if (ps != null) {
					int len = stationary.length;
					for (int i = 0; i < len; i++) {
						ps.setString(1, stationary[i].getStationCode());

						ps.addBatch();
					}
					ps.executeBatch();
					flag = true;

				}
			}

		} catch (SQLException sqlexception) {
			con.rollback();
			throw sqlexception;
		} catch (Exception exception) {
			con.rollback();
			throw exception;
		} finally {

			try {

				ps.close();
				ps = null;
			} catch (Exception exception) {

				if (ps != null)
					ps.close();

			}
		}
		System.out.println("Removed the stationary report");
		return flag;

	}

	/**
	 * 
	 */
	public boolean updateLRFormat(LRNumberRangeDTO[] lrFormat) throws Exception {

		// System.out.println("In StationDAO getSelectedStations===>");
		Connection con = null;
		PreparedStatement pst = null;
		boolean result = false;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				con.setAutoCommit(false);

				pst = con.prepareStatement(INSERT_LR_RANGE);

				if (null != pst) {
					int batch_count = lrFormat.length;
					if (lrFormat != null) {
						for (int i = 0; i < batch_count; i++) {
							String stationCode = lrFormat[i].getStationCode();
							String type = lrFormat[i].getType();
							pst.setString(1, stationCode);
							pst.setString(2, lrFormat[i].getRangeFrom());
							pst.setString(3, lrFormat[i].getRangeTo());

							int batchId = getBatchId(con, lrFormat[i]
									.getStationCode(), type);

							pst.setString(4, Integer.toString(batchId + 1));
							pst.setString(5, type);
							System.out.println("range from===>"
									+ lrFormat[i].getRangeFrom());
							if ((lrFormat[i].getRangeFrom() != "")
									&& (lrFormat[i].getRangeTo() != "")) {
								pst.addBatch();
							}

						}
					}
					pst.executeBatch();
					con.commit();
					result = true;
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		return result;
	}

	/**
	 * 
	 * @param con
	 * @param stationCode
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private int getBatchId(Connection con, String stationCode, String type)
			throws Exception {
		PreparedStatement pst = null;
		int batchId = 0;

		try {
			pst = con.prepareStatement(GET_BATCH_ID);
			if (pst != null) {
				pst.setString(1, stationCode);
				pst.setString(2, type);
				ResultSet rs = pst.executeQuery();
				if (null != rs) {
					rs.next();
					batchId = rs.getInt(1);
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

		return batchId;
	}

	/**
	 * 
	 * @param stationCode
	 * @param type
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getLRRange(String stationcode)
			throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<LRNumberRangeDTO> list = new ArrayList<LRNumberRangeDTO>();
		LRNumberRangeDTO lrFormat = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_LR_RANGE);

				if (null != pst) {
					pst.setString(1, stationcode);
					pst.setString(2, stationcode);
					pst.setString(3, stationcode);
					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							lrFormat = new LRNumberRangeDTO();

							lrFormat.setStationCode(rs.getString(STATION_CODE));
							lrFormat.setType(rs.getString("type"));
							lrFormat.setRangeFrom(rs.getString("start_number"));
							lrFormat.setRangeTo(rs.getString("end_number"));
							lrFormat.setAllocationDate(rs
									.getDate("allocation_date"));

							list.add(lrFormat);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("getLRAllocation size for the station "
				+ stationcode + "===>" + size);
		if (size > 0) {
			return (LRNumberRangeDTO[]) list
					.toArray(new LRNumberRangeDTO[size]);
		}
		return null;
	}

	@Override
	/**
	 * Set Alarm Settings
	 */
	public boolean setAlarmSettings(String[] stations, int topay, int paid,
			int billing, int cr) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		boolean result = false;
		int[] batchLength;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_ALARM_SETTINGS);

				if (null != pst) {
					int batch_count = stations.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setInt(1, topay);
						pst.setInt(2, paid);
						pst.setInt(3, billing);
						pst.setInt(4, cr);
						pst.setString(5, stations[i]);

						pst.addBatch();
					}
					batchLength = pst.executeBatch();
					if (batchLength.length > 0) {
						result = true;
					}
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

		return result;
	}

	/**
	 * Method to get Stationary Report
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryReport() throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<LRNumberRangeDTO> list = new ArrayList<LRNumberRangeDTO>();
		LRNumberRangeDTO lrFormat = null;
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_STATIONARY_REPORT);

				if (null != pst) {

					rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							lrFormat = new LRNumberRangeDTO();

							lrFormat.setStationCode(rs.getString(STATION_CODE));
							lrFormat.setTopay(rs.getInt(TOPAY));
							lrFormat.setPaid(rs.getInt(PAID));
							lrFormat.setBilling(rs.getInt(BILLING));
							lrFormat.setCr(rs.getInt(CR));
							list.add(lrFormat);
						}
					}

				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {

				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("StationaryReport size===>" + size);
		if (size > 0) {
			return (LRNumberRangeDTO[]) list
					.toArray(new LRNumberRangeDTO[size]);
		}
		return null;
	}

	/**
	 * Method to set the BF_Increment for list of stations. This method is
	 * called for updation of bf_increment data.
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param bf_increment
	 *            BFIncrement
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setBFIncrement(String[] station_code, int bf_increment)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_BF_INCREMENT);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, bf_increment);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

	}

	/**
	 * Method to set the discount for list of stations. This method is called
	 * for updation of discount data.
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param discount
	 *            Discount
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setBFDecrement(String[] station_code, int discount)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_BF_DECREMENT);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, discount);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}
	}

	/**
	 * Method to calculate the distance from the given station code to the rest
	 * of the stations. The method calls the stored procedures which returns
	 * back the record set containing the data.
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @return DistanceListDTO An instance of DistanceListDTO
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public DistanceListDTO[] calculateDistance(String stationCode)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBHelper helper = new DBHelper();
		DistanceListDTO distanceDTO = null;
		ArrayList<DistanceListDTO> list = new ArrayList<DistanceListDTO>();
		int incr = 0;
			
		// HashMap<String, Integer> hm = null;

		try {
			con = helper.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_DISTANCE_LIST);
				pst.setString(1, stationCode);
				pst.setString(2, stationCode);
				rs = pst.executeQuery();

				while (rs.next()) {
					distanceDTO = new DistanceListDTO();
					
					
					if (stationCode.equals(rs.getString(FROM_STATION))) {
						// hm.put(rs.getString(TO_STATION),
						// rs.getInt(DISTANCE));
						
						distanceDTO.setDestStation(rs.getString(TO_STATION));
						distanceDTO.setDistance(rs.getInt(DISTANCE));
						distanceDTO.setCardRate(rs.getFloat(CARD_RATE));
					} else {
						// hm.put(rs.getString(FROM_STATION),
						// rs.getInt(DISTANCE));
						
						distanceDTO.setDestStation(rs.getString(FROM_STATION));
						distanceDTO.setDistance(rs.getInt(DISTANCE));
						distanceDTO.setCardRate(rs.getFloat(CARD_RATE));
					}
					
					incr = getIncrementer(con, stationCode, distanceDTO.getDestStation());
					
					if(incr != 0){
						distanceDTO.setIncrementer(incr);
					}
					
					list.add(distanceDTO);
				}

				int size = list.size();
				System.out.println("Distance size===>" + size);
				if (size > 0) {
					return (DistanceListDTO[]) list
							.toArray(new DistanceListDTO[size]);
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != pst)

					pst.close();
				if (null != con)

					con.close();
			} catch (Exception exception) {
				if (null != pst)

					pst.close();
				if (null != con)

					con.close();
			}
		}

		return null;
	}

	private int getIncrementer(Connection con , String stationCode, String destStation) throws Exception {
		PreparedStatement pst = null;	
		ResultSet rs = null;
		int increment = 0;		
		try {
			pst = con.prepareStatement(GET_INCREMENTER);
			if (null != pst) {
				pst.setString(1, stationCode);
				pst.setString(2, destStation);
				
				rs = pst.executeQuery();
				if(rs.next()){
					increment = rs.getInt("increment");
					
					return increment;
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;					
				}
				if (null != rs) {
					rs.close();
					rs = null;					
				}
				
			} catch (Exception excep) {
				pst = null;
				rs = null;
			}
		}
		
		return 0;
	}

	/**
	 * Method to set the distance between the given stationcode and the rest all
	 * stations which is available in the hashmap. This method is called for
	 * insertion of distance data. This method first inserts the data in a
	 * temporary table using batch statement and then calls the stored procedure
	 * which takes the input from the temporary table and updates the necessary
	 * data. The logic of handling/storing the distance is taken care by the
	 * stored procedures.
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @param distanceList
	 *            Hashmap contains the destination station as the key and
	 *            distance as the value
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	@Override
	public void setDistanceList(String stationCode,
			DistanceListDTO[] distanceList) throws SQLException, Exception {
		Connection con = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				updateDistanceList(con, stationCode, distanceList);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
				}
				con = null;
			} catch (Exception excep) {
				excep = null;
			}
		}
	}

	/**
	 * 
	 */
	public StationsDTO[] getAllBFIncrementAndDiscount() throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> bfList = new ArrayList<StationsDTO>();
		StationsDTO station = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_MANAGE_REGULAR_SUNDRY);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						station = new StationsDTO();
						station.setId(rs.getString(STATION_CODE));
						station.setBf_increment(rs.getInt(BFINCREMENT));
						station.setDiscount(rs.getInt(DISCOUNT));
						bfList.add(station);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = bfList.size();
		if (size > 0) {
			return (StationsDTO[]) bfList.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * Method to set the list of manage sundry using the RegularSundry dto This
	 * method is called for insertion/updation of regular sundry data.
	 * 
	 * @param dto
	 *            RegularSundryDTO list
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setManageSundrySetting(StationsDTO[] dto) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_MANAGE_REGULAR_SUNDRY);
				if (null != pst) {
					int batch_count = dto.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setInt(1, dto[i].getBf_increment());
						pst.setInt(2, dto[i].getDiscount());
						pst.setString(3, dto[i].getId());
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}
	}

	/**
	 * 
	 * @param con
	 * @param stationCode
	 * @param distanceList
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void insertDistanceList(Connection con, String stationCode,
			HashMap<String, Integer> distanceList) throws Exception {
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(INSERT_DISTANCE);

			if (null != pst) {
				Set<Map.Entry<String, Integer>> set = distanceList.entrySet();

				for (Map.Entry<String, Integer> me : set) {
					pst.setString(1, stationCode);
					pst.setString(2, me.getKey());
					pst.setInt(3, me.getValue());
					pst.addBatch();
				}
				pst.executeBatch();
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
			} catch (Exception excep) {
				pst = null;
			}
		}
	}

	/**
	 * 
	 * @param con
	 * @param stationCode
	 * @param distanceList
	 * @throws Exception
	 */
	private void updateDistanceList(Connection con, String stationCode,
			DistanceListDTO[] distanceDTO) throws Exception {
		PreparedStatement pst = null;
		int[] result = null;
		int distance = 0;
		String toStationCode = null;
		float cardRate = 0;

		try {
			pst = con.prepareStatement(UPDATE_DISTANCE);

			if (null != pst) {

				for (int i = 0; i < distanceDTO.length; i++) {
					distance = distanceDTO[i].getDistance();
					toStationCode = distanceDTO[i].getDestStation();
					cardRate = distanceDTO[i].getCardRate();

					pst.setInt(1, distance);
					pst.setFloat(2, cardRate);
					pst.setString(3, toStationCode);
					pst.setString(4, stationCode);
					pst.setString(5, stationCode);
					pst.setString(6, toStationCode);
					pst.addBatch();
				}
				result = pst.executeBatch();
				if (result.length > 0) {
					System.out.println("updated successfully");
				} /*
					 * else {
					 * 
					 * Set<Map.Entry<String, Integer>> set1 = distanceList
					 * .entrySet();
					 * 
					 * for (Map.Entry<String, Integer> me : set1) {
					 * 
					 * distance = me.getValue(); toStationCode = me.getKey();
					 * pst.setInt(1, distance); pst.setString(2, stationCode);
					 * pst.setString(3, toStationCode); pst.addBatch(); }
					 * pst.executeBatch(); }
					 */
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
			} catch (Exception excep) {
				pst = null;
			}
		}
	}

	/**
	 * 
	 */
	public void setDRSFine(StationsDTO[] dto) throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_DRSFINE);
				if (null != pst) {
					int batch_count = dto.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setInt(1, dto[i].getDrs_duration());
						pst.setInt(2, dto[i].getDrs_fine());
						pst.setString(3, dto[i].getId());
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}

	}

	/**
	 * Method to set the discount for list of stations. This method is called
	 * for updation of discount data.
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param discount
	 *            Discount
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCSundryLimt(String[] station_code, int discount)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CC_LIMIT);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, discount);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}
	}

	/**
	 * 
	 */
	public void setMinimumFreight(String[] station_code, int minFT)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		System.out.println("Inside setMinimumFreight" + minFT);
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_MIN_FREIGHT);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, minFT);
						pst.setString(2, station_code[i]);
						System.out.println(station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}
		System.out.println("Out  setMinimumFreight" + minFT);
	}

	/**
	 * 
	 */
	public void setMinimumWeight(String[] station_code, int minWT)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_MIN_WEIGHT);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, minWT);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getManageCommission() throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> commList = new ArrayList<StationsDTO>();
		StationsDTO station = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_MANAGE_COMMISSION);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						station = new StationsDTO();
						station.setId(rs.getString(STATION_CODE));
						station.setProfileName(rs.getString(PROFILE_NAME));
						station.setCc_consider(rs.getInt(CC_CONSIDER));
						station.setCc_refund(rs.getInt(CC_REFUND));
						station.setDeliveryCommission(rs
								.getFloat(Delivery_COMMISSION));
						station.setCc_limit(rs.getInt(CC_LIMIT));
						commList.add(station);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = commList.size();
		if (size > 0) {
			return (StationsDTO[]) commList.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public void updateManageCommisssion(StationsDTO[] dto) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				con.setAutoCommit(false);

				pst = con.prepareStatement(UPDATE_MANAGE_COMMISSION);

				if (null != pst) {

					if (dto != null) {
						int batch_count = dto.length;
						for (int i = 0; i < batch_count; i++) {
							pst.setString(1, dto[i].getProfileName());
							pst.setFloat(2, dto[i].getDeliveryCommission());
							pst.setInt(3, dto[i].getCc_limit());
							pst.setInt(4, dto[i].getCc_consider());
							pst.setInt(5, dto[i].getCc_refund());
							pst.setString(6, dto[i].getId());
							pst.addBatch();
						}
					}
				}
				pst.executeBatch();
				con.commit();
			}

		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

	}

	@Override
	public void setCCForCommodity(String[] station_code, int cc)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CC_COMMODITY);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, cc);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

	}

	@Override
	public void setCCForSpecialSundry(String[] station_code, int cc)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CC_SPECIAL);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, cc);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

	}

	@Override
	public void setCCForHLC(String[] station_code, String customer,
			float ccArticle, float refundArticle) throws RemoteException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {

				con.setAutoCommit(false);

				if (customer.equalsIgnoreCase("ALL")) {
					pst = con.prepareStatement(UPDATE_ALL_CC_HLC_CUSTOMER);
					if (null != pst) {
						int batch_count = station_code.length;

						for (int i = 0; i < batch_count; i++) {
							pst.setFloat(1, ccArticle);
							pst.setFloat(2, refundArticle);
							pst.setString(3, station_code[i]);
							pst.addBatch();
						}

						pst.executeBatch();

						setCCForAllHLC(con, station_code, ccArticle,
								refundArticle);
						con.commit();
					}
				} else {
					pst = con.prepareStatement(UPDATE_CC_HLC_CUSTOMER);
					if (null != pst) {
						pst.setFloat(1, ccArticle);
						pst.setFloat(2, refundArticle);
						pst.setString(3, customer);
						pst.executeUpdate();

						con.commit();
					}
				}

			}
		} catch (Exception exception) {
			con.rollback();
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

	}

	/**
	 * 
	 * @param con
	 * @param station_code
	 * @param ccArticle
	 * @param refundArticle
	 * @throws Exception
	 */
	private void setCCForAllHLC(Connection con, String[] station_code,
			float ccArticle, float refundArticle) throws Exception {
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_ALL_CC_HLC_STATION);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, ccArticle);
						pst.setFloat(2, refundArticle);
						pst.setString(3, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
			} catch (Exception excep) {
				pst = null;
			}
		}

	}

	/**
	 * 
	 * @param station_code
	 * @param tamil
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setStationLanguage(String[] station_code, byte tamil)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_LANGUAGE);

				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, tamil);
						pst.setString(2, station_code[i]);
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

	}

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public CardRateProfileDTO[] getCardRateProfile(String station_code)
			throws RemoteException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CardRateProfileDTO> profileList = new ArrayList<CardRateProfileDTO>();
		CardRateProfileDTO cardRateProfileDTO = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_CARD_RATE_PROFILE);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						cardRateProfileDTO = new CardRateProfileDTO();

						cardRateProfileDTO.setEquals_card_rate(rs
								.getFloat(EQUALS_CARD_RATE));
						cardRateProfileDTO.setAbove_card_rate(rs
								.getFloat(ABOVE_CARD_RATE));
						cardRateProfileDTO.setUpto_20_card_rate(rs
								.getFloat(UPTO_20__CARD_RATE));
						cardRateProfileDTO.setAbove_20_card_rate(rs
								.getFloat(ABOVE_20_CARD_RATE));
						cardRateProfileDTO.setStation_code(rs
								.getString(STATION_CODE));
						cardRateProfileDTO.setLr_type(rs.getString(LR_TYPE));

						profileList.add(cardRateProfileDTO);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = profileList.size();
		System.out.println("Profile Size" + size);
		if (size > 0) {
			return (CardRateProfileDTO[]) profileList
					.toArray(new CardRateProfileDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param station_code
	 * @return
	 */
	public CCProfileDTO[] getCCDetails(String station_code)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CCProfileDTO> profileList = new ArrayList<CCProfileDTO>();
		CCProfileDTO ccProfileDTO = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_CC_PROFILE_DETAILS);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						ccProfileDTO = new CCProfileDTO();

						ccProfileDTO
								.setStation_code(rs.getString(STATION_CODE));
						ccProfileDTO.setCc_commodity(rs.getInt(CC_COMMODITY));
						ccProfileDTO.setCc_consider(rs.getInt(CC_CONSIDER));
						ccProfileDTO.setCc_limit(rs.getInt(CC_LIMIT));
						ccProfileDTO.setCc_refund(rs.getInt(CC_REFUND));
						ccProfileDTO.setCc_special(rs.getInt(CC_SPECIAL));

						profileList.add(ccProfileDTO);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = profileList.size();

		if (size > 0) {
			return (CCProfileDTO[]) profileList.toArray(new CCProfileDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	public DCProfileDTO[] getDCDetails(String station_code)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<DCProfileDTO> profileList = new ArrayList<DCProfileDTO>();
		DCProfileDTO dcProfileDTO = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DC_PROFILE_DETAILS);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						dcProfileDTO = new DCProfileDTO();

						dcProfileDTO
								.setStation_code(rs.getString(STATION_CODE));
						dcProfileDTO.setDc_per_ton(rs
								.getFloat(Delivery_COMMISSION));

						profileList.add(dcProfileDTO);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = profileList.size();

		if (size > 0) {
			return (DCProfileDTO[]) profileList.toArray(new DCProfileDTO[size]);
		}
		return null;
	}

	@Override
	public float getRefundPerArticleForHLC(String customer, String station_code)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		float refund = 0;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_HLC_REFUND);
				if (ps != null) {

					ps.setString(1, customer);
					ps.setString(2, station_code);
					rs = ps.executeQuery();
					while (rs.next()) {
						refund = rs.getFloat(REFUND_ARTICLE);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return refund;
	}
	
	//holiday
	public void setHolidayEntry(Date holidayDate, String holidayDesc)
	throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String holiday_date = dateFormat.format(holidayDate);
				pst = con.prepareStatement(HOLIDAY_ENTRY);
				if (pst != null) {
					pst.setString(1, holiday_date);
					pst.setString(2, holidayDesc);
					pst.executeUpdate();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}
	}

}
