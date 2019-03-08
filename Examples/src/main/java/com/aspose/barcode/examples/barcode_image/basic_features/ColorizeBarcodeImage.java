package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

import java.awt.*;
import java.io.IOException;

public class ColorizeBarcodeImage {
    public static void main(String[] args) throws IOException {
    	//ExStart: ColorizeBarcodeImage
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ColorizeBarcodeImage.class) + "BarcodeImage/BasicFeatures/";

        BarCodeGenerator bb = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128,"1234567");

        //Set background color of the barcode
        bb.setBackColor(Color.YELLOW);
        
        //Set the foreground color of the barcode
        bb.setForeColor(Color.BLUE);
        
        //Set border's color
        bb.getBorder().setColor(Color.RED);
        
        //Set the code text's color
        bb.getCodeTextStyle().setColor(Color.RED);
        
        // Save the image
        bb.save(dataDir + "colorizeBarcode.png");
        //ExEnd: ColorizeBarcodeImage
    }
}
