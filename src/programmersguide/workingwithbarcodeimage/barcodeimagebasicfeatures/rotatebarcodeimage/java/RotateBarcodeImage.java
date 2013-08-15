/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimagebasicfeatures.rotatebarcodeimage.java;

import com.aspose.barcode.*;

public class RotateBarcodeImage
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimagebasicfeatures/rotatebarcodeimage/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text of the barcode
        bb.setCodeText("12345678");

        //Roate clockwise for 180 degree (upside down)
        bb.setRotationAngleF(180);

        //Set the symbology type to code39
        bb.setSymbologyType(Symbology.Code39Extended);

        try
        {
            //Save the image to file
            bb.save(dataDir + "barcode.jpg");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        // Print message
        System.out.println("Barcode image generated and rotated successfully.");
    }
}




