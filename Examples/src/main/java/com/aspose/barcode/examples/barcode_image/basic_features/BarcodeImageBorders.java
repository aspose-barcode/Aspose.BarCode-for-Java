package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;

public class BarcodeImageBorders {
    public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeImageBorders.class) + "BarcodeImage/BasicFeatures/";
        
        BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setEncodeType(com.aspose.barcode.EncodeTypes.CODE_128);

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
