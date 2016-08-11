package com.aspose.barcode.examples.barcode.basic_features;

import java.awt.Font;

import com.aspose.barcode.Alignment;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Caption;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class BarcodeCaption {

	public static void main(String[] args) {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/BasicFeatures/";
    	
		//Instantiate Barcode object
		BarCodeBuilder bb = new BarCodeBuilder();
		//Set the code text of the barcode
		bb.setCodeText("1234567");
		//Set the symbology type to code128
		bb.setSymbologyType(Symbology.Code128);

		Caption caption = new Caption();
		caption.setText("Captions");
		caption.setTextAlign(Alignment.Middle);

		bb.setCaptionAbove(caption);
		bb.setCaptionBelow(caption);
		
		bb.getCaptionAbove().setTextAlign(Alignment.Left);
		bb.getCaptionAbove().setText("Aspose.Demo");
		bb.getCaptionAbove().setVisible(true);
		bb.getCaptionAbove().setFont(new java.awt.Font("Pristina", Font.PLAIN, 14));
		bb.getCaptionAbove().setForeColor(java.awt.Color.RED);

		bb.getCaptionBelow().setTextAlign(Alignment.Right);
		bb.getCaptionBelow().setText("Aspose.Demo");
		bb.getCaptionBelow().setVisible(true);
		bb.getCaptionBelow().setFont(new java.awt.Font("Pristina", Font.PLAIN, 14));
		bb.getCaptionBelow().setForeColor(java.awt.Color.RED);
		
		//Save the Barcode image to file
    	bb.save(dataDir + "barcodeCaption.jpg");
	}

}