package com.aspose.barcode.examples.barcode_image.basic_features;

import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class BarcodeImageBorders {
    public static void main(String[] args) throws IOException {
    	//ExStart: BarcodeImageBorders
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeImageBorders.class) + "BarcodeImage/BasicFeatures/";
        
    	//Instantiate Barcode object, Set the Symbology type to code128 and Set the Code text for the barcode
        BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "1234567");
        
        //Set border style to solid
        bb.getParameters().getBorder().setDashStyle(BorderDashStyle.SOLID);
        
        //Set border margins for Top, Right, Left and Bottom
        bb.getParameters().getBarcode().getPadding().getTop().setPixels(2f);
        bb.getParameters().getBarcode().getPadding().getRight().setPixels(2f);
        bb.getParameters().getBarcode().getPadding().getLeft().setPixels(2f);
        bb.getParameters().getBarcode().getPadding().getBottom().setPixels(2f);
        
        //ExStart: SetBorderWidth
        //Set border width
        bb.getParameters().getBorder().getWidth().setPixels(2.5f);
        //ExEnd: SetBorderWidth
        
        //Set the border's color to red
        bb.getParameters().getBorder().setColor(java.awt.Color.RED);
        
        //ExStart: EnableImageBorder
        //Enable border to be shown in the barcode
        bb.getParameters().getBorder().setVisible(true);
        //ExEnd: EnableImageBorder
        
        // Save the image
        bb.save(dataDir + "barcode-image-borders.jpg");
        //ExEnd: BarcodeImageBorders
    }
}
