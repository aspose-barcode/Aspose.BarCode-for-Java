package com.aspose.barcode.examples.barcode.basic_features;

import java.awt.Font;
import java.io.IOException;

import com.aspose.barcode.Caption;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class BarcodeCaption {

	public static void main(String[] args) throws IOException {
		//ExStart: BarcodeCaption
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeCaption.class) + "Barcode/BasicFeatures/";
    	
    	// Create instance of BarCodeGenerator, specify codetext and symbology in the constructor
    	BarCodeGenerator bb = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "12345678");
		
		bb.getCaptionAbove().setAlignment(com.aspose.barcode.StringAlignment.CENTER);
		bb.getCaptionAbove().setText("Aspose.Demo");
		bb.getCaptionAbove().setVisible(true);
		bb.getCaptionAbove().getFont().setFamilyName("Pristina");
		bb.getCaptionAbove().getFont().getSize().setPoint(14);
		bb.getCaptionAbove().setColor(java.awt.Color.RED);
		
		bb.getCaptionBelow().setAlignment(com.aspose.barcode.StringAlignment.FAR);
		bb.getCaptionBelow().setText("Aspose.Demo");
		bb.getCaptionBelow().setVisible(true);
		bb.getCaptionBelow().getFont().setFamilyName("Pristina");
		bb.getCaptionBelow().getFont().getSize().setPoint(14);
		bb.getCaptionBelow().setColor(java.awt.Color.RED);

		//Save the Barcode image to file
    	bb.save(dataDir + "barcodeCaption.jpg");
    	//ExEnd: BarcodeCaption
	}

}
