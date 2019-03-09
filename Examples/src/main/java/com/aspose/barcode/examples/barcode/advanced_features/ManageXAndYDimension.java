package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class ManageXAndYDimension {

	public static void main(String[] args) throws IOException {
		ManageXAndYDimension.setXDimension();
		ManageXAndYDimension.setYDimension();
	}
	
	public static void setXDimension() throws IOException {
		//ExStart: setXDimension
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ManageXAndYDimension.class) + "Barcode/AdvancedFeatures/";
    	
    	BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128,"12345678");
		
    	//Set the x-dimension for the bars of the barcode
    	generator.getXDimension().setMillimeters(0.5f);
		
		//Save the Barcode image to file
    	generator.save(dataDir + "xDimention.jpg");
    	//ExEnd: setXDimension
	}
	
	public static void setYDimension() throws IOException {
		//ExStart: setYDimension
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ManageXAndYDimension.class) + "Barcode/AdvancedFeatures/";
    	
    	BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.PDF_417,"12345678");
		
    	//Set the Y-Dimension for the bars of the barcode
    	generator.getBarHeight().setMillimeters(4);
		
		//Save the Barcode image to file
    	generator.save(dataDir + "yDimention.jpg");
    	//ExEnd: setYDimension
	}

}
