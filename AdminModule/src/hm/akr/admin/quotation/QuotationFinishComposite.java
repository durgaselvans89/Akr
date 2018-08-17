package hm.akr.admin.quotation;

import hm.akr.admin.distance.handler.DistanceCompositeHandler;
import hm.akr.common.FloatValidation;
import hm.akr.dto.BftDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.QuotationDTO;
import hm.akr.dto.QuotationDetailsDTO;
import hm.akr.dto.StationsDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class QuotationFinishComposite extends Composite {

	Properties properties = null;

	private static Table tblBFT;

	private TableColumn column;

	private TableItem item;

	private Canvas canvas2;	

	DistanceCompositeHandler handler = null;

	private String[] COLUMN_HEAD = null;

	private static int COLUMN_SIZE = 0;

	private TableEditor editor;

	static Text[][] txtCardRate;
	
	public QuotationDTO quotDTO = null;	
	
	QuotationDetailsDTO[] QdetailsDTO = null;

	private DecimalFormat decimalFormat;
	
	int roundDigits = 0;

	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public QuotationFinishComposite(Composite composite, int swtValue, QuotationDTO quotationDTO) throws Exception {
		super(composite, swtValue);		
		this.quotDTO = quotationDTO;
		this.roundDigits = quotationDTO.getRoundOffDigits();		
		try {					
			handler = new DistanceCompositeHandler();		
			QdetailsDTO = quotationDTO.getQuotationDetails();			
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			
				this.setSize(800, 460);			
				
			{
				canvas2 = new Canvas(this, SWT.NONE);
				canvas2.setBounds(10, 0, 800, 460);			

				{					
					String stationCode = checkCityStation(quotDTO.getStationCode());
					
					DistanceListDTO[] distDTO = handler.getDisanceList(stationCode);
					
					int columns = 2;
					String[] chargedWt = null;
					COLUMN_HEAD = new String[2];
					chargedWt = new String[1];					
					float priceIndex = quotDTO.getPriceIndex();
					//System.out.println("in finish==>"+priceIndex);
					float chargedWeight = 0;					
					if(quotDTO.getType() == 0){
						//Weight Based			
						COLUMN_HEAD[0] = "Station";
						COLUMN_HEAD[1] = "Card Rate";
						chargedWt[0] = "weight";
					}else if(quotDTO.getType() == 1){
						//Article Based					
						chargedWeight = QdetailsDTO[0].getChargedWeight();
						COLUMN_HEAD[0] = "Station";
						COLUMN_HEAD[1] = QdetailsDTO[0].getArticleName();
						chargedWt[0] = String.valueOf(chargedWeight);
					}else if(quotDTO.getType() == 2){
						//Mixed Article						
						columns = QdetailsDTO.length;						
						COLUMN_HEAD = new String[columns+1];
						COLUMN_HEAD[0] = "Station";
						chargedWt = new String[columns];
						for(int i = 0; i < columns; i++){
							chargedWt[i] = String.valueOf(QdetailsDTO[i].getChargedWeight());
							COLUMN_HEAD[i+1] = QdetailsDTO[i].getArticleName();
						}
					}
					
					if(distDTO != null)
						createTable(COLUMN_HEAD, distDTO, chargedWt, priceIndex);
					
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	

	// Create Table and Filling Card Rates
	private void createTable(String[] column_name,DistanceListDTO[] distDTO, String[] chargedWt, float priceIndex) {

		
		COLUMN_SIZE = column_name.length;
		int rows =  distDTO.length;
		
		tblBFT = new Table(canvas2, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblBFT.setLinesVisible(true);
		tblBFT.setHeaderVisible(true);

		for (int i = 0; i < COLUMN_SIZE; i++) {
			column = new TableColumn(tblBFT, SWT.NONE);

			if (i == 0) {
				column.setText(column_name[i]);
				column.setWidth(160);
			} else {
				column.setText(column_name[i]);
				column.setWidth(80);
			}
		}

		decimalFormat = new DecimalFormat("0.00");
		txtCardRate = new Text[rows][COLUMN_SIZE];
		String stnCode = "";
		float calculatedCardRate = 0;
	
		BftDTO[] availBFT = null;
		// Drawing initial table items		
		final int len = rows;
		for (int i = 0; i < rows; i++) {
			//Columns For a Row			
			item = new TableItem(tblBFT, SWT.NONE);

			//First Column Station Name
			stnCode = distDTO[i].getDestStation();
			item.setText(0, getStationName(stnCode) +" - "+ stnCode);
			
			//Card Rate Values
			for (int j = 1; j < COLUMN_SIZE; j++) {
				
				//if(j<availLen+1){
					availBFT = QdetailsDTO[j-1].getBft();
					//System.out.println("art name==>"+QdetailsDTO[j-1].getArticleName()+"bft--->"+QdetailsDTO[j-1].getBft()[i].getBpi());
				//}
				
				
				// Draw Text Field and set Card Rates
				editor = new TableEditor(tblBFT);
				txtCardRate[i][j] = new Text(tblBFT, SWT.NONE);
				
				if(chargedWt[j-1].equals("weight")){
					calculatedCardRate = getCustomRoundOff((distDTO[i].getDistance() * priceIndex), roundDigits);
				}else{
					calculatedCardRate = getCustomRoundOff((getCustomRoundOff((distDTO[i].getDistance() *  priceIndex), 2) * Float.parseFloat(chargedWt[j-1])) , roundDigits);					
				}
				
				
				item.setText(j, decimalFormat.format(calculatedCardRate));
				//System.out.println("BFT==>"+calculatedCardRate);
				if(availBFT != null && availBFT[i].getBpi() != -1){	
					//System.out.println("availBFT==>"+availBFT[i].getBpi());
					//First Column Station Name
					stnCode = availBFT[i].getStationCode();
					item.setText(0, getStationName(stnCode) +" - "+ stnCode);
					txtCardRate[i][j].setText(decimalFormat.format(getCustomRoundOff(availBFT[i].getBpi(), roundDigits)));
					availBFT = null;
				}else{					
					txtCardRate[i][j].setText(decimalFormat.format(calculatedCardRate));
				}
				txtCardRate[i][j].addVerifyListener(new FloatValidation());
				
				final int index_i = i;
				final int index_j = j;				
				

				txtCardRate[index_i][index_j].addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {
						
					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (event.keyCode == 13) {
							// Scrollbar
							Rectangle rt = txtCardRate[index_i][index_j]
									.getBounds();
							int yPosition = rt.y;
							int topIndex = tblBFT.getTopIndex();
							int width = 0;
							// System.out.println(yPosition);
							if (index_i != (len - 1)
									&& (yPosition < 470 && yPosition > 460)) {
								tblBFT.setTopIndex(topIndex + 15);
								tblBFT.setSelection(index_i + 1, index_j);

								width = tblBFT.getColumn(index_j).getWidth();
								tblBFT.getColumn(index_j).pack();
								tblBFT.getColumn(index_j).setWidth(width);
								tblBFT.redraw();
							}

							if (index_i != (len - 1)) {
								txtCardRate[index_i + 1][index_j].setFocus();
								txtCardRate[index_i + 1][index_j].selectAll();								
							}
						}
					}

				});
				
				txtCardRate[index_i][index_j].addFocusListener(new FocusListener(){

					@Override
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void focusLost(FocusEvent arg0) {						

						// Scrollbar
						Rectangle rt = txtCardRate[index_i][index_j]
								.getBounds();
						int yPosition = rt.y;
						int topIndex = tblBFT.getTopIndex();
						int width = 0;
						// System.out.println(yPosition);
						if (index_i != (len - 1)
								&& (yPosition < 470 && yPosition > 460)) {
							tblBFT.setTopIndex(topIndex + 15);
							tblBFT.setSelection(index_i + 1, index_j);

							width = tblBFT.getColumn(index_j).getWidth();
							tblBFT.getColumn(index_j).pack();
							tblBFT.getColumn(index_j).setWidth(width);
							tblBFT.redraw();
						}

						if (index_i != (len - 1)) {
							txtCardRate[index_i + 1][index_j].setFocus();
							txtCardRate[index_i + 1][index_j].selectAll();							
						}					
						
						
					}
					
				});
				
				editor.grabHorizontal = true;
				editor.setEditor(txtCardRate[i][j], item, j);
			}
		}
		int width = 80 * (COLUMN_SIZE - 1) + 187;
		if(width < 780){
			tblBFT.setBounds(10, 20, width, 400);
		}else{
			tblBFT.setBounds(10, 20, 780, 400);
		}

	}
	
	
	/**
	 * Setting BFT values in QuotationDTO
	 */
	public QuotationDTO getBFTDetails() {
		BftDTO bftDTO = null;
		ArrayList<BftDTO> list = null;
		float calcBFT = 0;
		float finalBFT = 0;
		String stationCode = "";
		int index = -1;
		int len = 0;
		TableItem[] items = null;
		
		if(tblBFT != null){
			items = tblBFT.getItems();
		}		
		
		if(items != null){
			len = items.length;
		}
		if(len > 0){			
		for (int j = 1; j < COLUMN_SIZE; j++) {
			list = new ArrayList<BftDTO>();
			for (int i = 0; i < len; i++) {
				bftDTO = new BftDTO();
				
				stationCode = items[i].getText(0);				
				
				index = stationCode.indexOf("-");
				if(index > -1)
					stationCode = stationCode.substring(index + 2);				
				bftDTO.setStationCode(stationCode);
				
				calcBFT = getCustomRoundOff(Float.parseFloat(txtCardRate[i][j].getText()), roundDigits);
				
				finalBFT = getCustomRoundOff(Float.parseFloat(items[i].getText(j)), roundDigits);
				
				if(!txtCardRate[i][j].getText().equals("")){
					
					if(calcBFT != finalBFT){						
						bftDTO.setBftChanged((byte)1);
					}else{
						bftDTO.setBftChanged((byte)0);
					}
					bftDTO.setBpi(Float.parseFloat(txtCardRate[i][j].getText()));
				}
				
				list.add(bftDTO);					
			}
			int size = list.size();
			if(size > 0){
				QdetailsDTO[j-1].setBft((BftDTO[]) list.toArray(new BftDTO[size]));
			}
		}
		}			
		quotDTO.setQuotationDetails(QdetailsDTO);
		
		return quotDTO;
	}
	
	
	/**
	 * Method to check the station is city station. If it is city station , the branch will be returned.
	 * @param stationCode
	 * @return
	 */
	private String checkCityStation(String stationCode) {
		String station = null;
		try {
			StationsDTO[] stationsDTO = handler.getAllStations();
			
			for(int i = 0; i < stationsDTO.length; i++){
				if(stationsDTO[i].getId().equalsIgnoreCase(stationCode) && 
						 stationsDTO[i].getType().equalsIgnoreCase("City")){
					station = stationsDTO[i].getBranch_code();					
				}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		if(station == null){
			station = stationCode;
		}
		
		return station;
	}
	
	private float getCustomRoundOff(float value, int digits) {
		
		if (value == 0) {
			return 0;
		} else {
			//System.out.println("digits==>"+digits);
			// To crop N+1 digits
			//System.out.println("value==>"+ value);
			int limit = (int)(Math.pow(10, (digits+1)));
			value = ((float)(int)(value * limit)) / (float) limit;	
			//System.out.println("aftr value==>"+ value );
			
			int scale = (int)(Math.pow(10, digits));
			value = value * scale;
			value = ((float)(int)(value * 10)) / (float)10;		
			value = (float) Math.ceil(value);
			value = value / scale;
		}		
		return value;
	}

	private String getStationName(String stnCode) {
		int size = 0;
		try {
			StationsDTO[] stations = handler.getAvailableStations();
			size = stations.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (stations[i].getId().equals(stnCode)) {
						return stations[i].getName();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
}