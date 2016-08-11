package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

import java.awt.*;

public class ColorizeBarcodeImage {
    public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ColorizeBarcodeImage.class) + "BarcodeImage/BasicFeatures/";

        BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setSymbologyType(Symbology.Code128);

        //Set background color of the barcode
        bb.setBackColor(Color.YELLOW);
        //Set the foreground color of the barcode
        bb.setForeColor(Color.BLUE);
        //Set border's color
        bb.setBorderColor(Color.RED);
        //Set the code text's color
        bb.setCodeTextColor(Color.RED);
        
        //Caption's color
        bb.getCaptionAbove().setForeColor(Color.darkGray);
        bb.getCaptionBelow().setForeColor(Color.CYAN);
        
        // Save the image
        bb.save(dataDir + "colorizeBarcode.png");
    }
}
