package hm.akr.common;

public class FloatRoundOff {

	static int typeCeil = -1;
	static float value = 0;
	static int digits = 0;
	
	public FloatRoundOff(float value, int type, int digits) {
		FloatRoundOff.typeCeil = type;
		FloatRoundOff.value = value;
		FloatRoundOff.digits = digits;
	}



public static void main(String[] args) {
	FloatRoundOff round = new FloatRoundOff(0,0,0);
	if(typeCeil == 0){
		
	}else if(typeCeil == 1){
		if(digits == 2){
			getRounded2Decimal(value);
		}else if(digits == 4){
			getRoundFourDecimals(value);
		}
	}
}

	private static float getRoundFourDecimals(float priceIndex) {
		if (priceIndex == 0) {
			return 0;
		} else {			
			priceIndex = ((float) (int) (priceIndex * 100000)) / (float) 100000;
			
			priceIndex = priceIndex * 10000;
			priceIndex = (float) Math.ceil(priceIndex);
			priceIndex = priceIndex / 10000;
			
		}
		return priceIndex;
	}
	
	private static float getRounded2Decimal(float value) {
		value = ((float) (int) (value * 1000)) / (float) 1000;

		if (value == 0) {
			return 0;
		} else {
			value = value * 100;
			value = (float) Math.ceil(value);

			value = value / 100;
		}
		return value;
	}

}
