/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimageutilityfeatures.customizebarcodeimageresolution.java;

import com.aspose.barcode.*;

public class CustomizeBarcodeImageResolution
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimageutilityfeatures/customizebarcodeimageresolution/data/";

        //Instantiate barcode object
        BarCodeBuilder bb=new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to Code128
        bb.setSymbologyType(Symbology.Code128);

        //Create an instance of resolution
        bb.setResolution(new Resolution(200f,400f,ResolutionMode.Graphics));

        //Save the image to your system
        //and set its image format to Jpeg
        bb.save(dataDir + "barcode.jpg","jpg");

        //Print message
        System.out.println("Barcode image generated successfully.");

    }
}




