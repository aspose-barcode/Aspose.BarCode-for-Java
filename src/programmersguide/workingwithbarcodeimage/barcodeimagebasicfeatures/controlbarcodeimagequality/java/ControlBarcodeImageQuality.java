/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimagebasicfeatures.controlbarcodeimagequality.java;

import com.aspose.barcode.*;

public class ControlBarcodeImageQuality
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimagebasicfeatures/controlbarcodeimagequality/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text of the barcode
        bb.setCodeText("12345678");

        //Set the graphics drawing hint to be Anti Alias
        bb.setImageQuality(ImageQualityMode.AntiAlias);

        //Set the symbology type to code39
        bb.setSymbologyType(Symbology.Code39Extended);
        try
        {
            //Save the image to file
            bb.save(dataDir + "barcode.jpg");

            //Print message
            System.out.println("Barcode image generated successfully.");
        }
        catch (Exception ex)
        {
            System.out.println("Some problem occurred while saving barcode image.");
        }
    }
}




