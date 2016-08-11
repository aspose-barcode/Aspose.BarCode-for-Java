package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class BarcodeImageBorders {
    public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "BarcodeImage/BasicFeatures/";
        
        BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setSymbologyType(Symbology.Code128);

        //Set border style to solid
        bb.setBorderDashStyle(BorderDashStyle.Solid);
        //Set border margins by assigning an instance of MarginsF
        bb.setMargins(new MarginsF(2f, 2f, 2f, 2f));
        //Set border width
        bb.setBorderWidth(0.5f);
        //Set the border's color to red
        bb.setBorderColor(java.awt.Color.RED);
        //Enable border to be shown in the barcode
        bb.setBorderVisible(true);
        
        // Save the image
        bb.save(dataDir + "barcode-image-borders.jpg");
    }
}
