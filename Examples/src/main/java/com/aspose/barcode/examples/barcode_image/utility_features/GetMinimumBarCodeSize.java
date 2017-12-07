package com.aspose.barcode.examples.barcode_image.utility_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class GetMinimumBarCodeSize 
{
    public static void main(String[] args) 
    {
    	
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(BarcodeCustomSize.class) + "BarcodeImage/UtilityFeatures/";

        // Create an instance of BarCodeBuilder class
        // Set barcode text
        // Set encoding type
        com.aspose.barcode.BarCodeBuilder builder = 
                new com.aspose.barcode.BarCodeBuilder("1234567890", com.aspose.barcode.EncodeTypes.CODE_128);
        
        // Set graphic unit
        builder.setGraphicsUnit(com.aspose.barcode.GraphicsUnit.Pixel);

        // Call GetMinimumBarCodeSize method to get the minimum size required
        
        java.awt.geom.Dimension2D minSize = builder.getMinimumBarCodeSize();

        // Set Auto size to false
        builder.setAutoSize(false);

        // Set image height & width with the help of min size got from GetMinimumBarCodeSize method
        builder.setImageWidth((float) minSize.getWidth());
        builder.setImageHeight((float)minSize.getHeight());

        // Save the barcode image
        javax.imageio.ImageIO.write( builder.getBarCodeImage(), "PNG", new java.io.File("minimumresult.png") );
        
    }
}
