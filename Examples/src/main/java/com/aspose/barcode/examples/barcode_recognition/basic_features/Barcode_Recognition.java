package com.aspose.barcode.examples.barcode_recognition.basic_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class Barcode_Recognition 
{

	public static void main(String[] args) throws Exception {
		
		ApplyALicense.applyALicense();
		
		//ExStart: Barcode_Recognition
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(Barcode_Recognition.class) + "BarcodeReader/basic_features/";
    	
		//Initialize barcode reader
	    BarCodeReader rd = new BarCodeReader(dataDir + "CodeText.jpg");
	    // read barcode of type Code39Extended
	    while(rd.read())
	    {
	        // print the code text, if barcode found
	        System.out.println("CodeText: " + rd.getCodeText().toString());
	        // print the symbology type
	        System.out.println("Symbology type: " + rd.getCodeType());
	    }
	    //ExEnd: Barcode_Recognition
	}
}
