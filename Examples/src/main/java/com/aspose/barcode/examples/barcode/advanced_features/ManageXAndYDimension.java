package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class ManageXAndYDimension {

	public static void main(String[] args) {
		ManageXAndYDimension.setXDimension();
		ManageXAndYDimension.setYDimension();
	}
	
	public static void setXDimension() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ManageXAndYDimension.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("12345678");
		bb.setSymbologyType(Symbology.Code128);

		//Set the x-dimension for the bars of the barcode
		bb.setxDimension(0.5f);
		
		//Save the Barcode image to file
    	bb.save(dataDir + "xDimention.jpg");
	}
	
	public static void setYDimension() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ManageXAndYDimension.class) + "Barcode/AdvancedFeatures/";
    	
		BarCodeBuilder bb = new BarCodeBuilder();
		bb.setCodeText("12345678");
		bb.setSymbologyType(Symbology.Pdf417);

		//Set the Y-Dimension for the bars of the barcode
		bb.setyDimension(4);
		
		//Save the Barcode image to file
    	bb.save(dataDir + "yDimention.jpg");
	}

}
