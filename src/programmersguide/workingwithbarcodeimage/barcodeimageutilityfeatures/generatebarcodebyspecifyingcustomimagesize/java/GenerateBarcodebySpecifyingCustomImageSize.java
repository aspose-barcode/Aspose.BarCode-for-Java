/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimageutilityfeatures.generatebarcodebyspecifyingcustomimagesize.java;

import com.aspose.barcode.*;

public class GenerateBarcodebySpecifyingCustomImageSize
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimageutilityfeatures/generatebarcodebyspecifyingcustomimagesize/data/";
        
        //Instantiate barcode object
        BarCodeBuilder builder = new BarCodeBuilder("1234567890", Symbology.Code39Standard);

        // Set auto size false
        builder.setAutoSize(false);

        // Set height
        builder.setImageHeight(50);

        // Set width
        builder.setImageWidth(120);

        // Save image to disk
        builder.save(dataDir + "barcode.out.png");

        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}