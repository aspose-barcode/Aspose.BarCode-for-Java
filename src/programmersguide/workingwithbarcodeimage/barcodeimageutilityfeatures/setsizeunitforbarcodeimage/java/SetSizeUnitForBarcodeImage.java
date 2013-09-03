/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimageutilityfeatures.setsizeunitforbarcodeimage.java;

import com.aspose.barcode.*;

public class SetSizeUnitForBarcodeImage
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimageutilityfeatures/setsizeunitforbarcodeimage/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder("12345678",Symbology.Code128);

        //Measurement is Milimeter
        bb.setGraphicsUnit(1);

        //set the bar height to 3 points
        bb.setBarHeight(3.0f);
        try
        {
            //Save the image to file
            bb.save(dataDir + "barcode.jpg");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        //Print message
        System.out.println("Barcode image generated successfully.");
    }
}




