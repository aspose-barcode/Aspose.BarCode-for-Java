/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimagebasicfeatures.workingwithimageborders.java;

import com.aspose.barcode.*;

public class WorkingWithImageBorders
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimagebasicfeatures/workingwithimageborders/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Border will be visible
        bb.setBorderVisible(true);

        //Set border's width to be 0.5 milimeter
        bb.setBorderWidth(0.5f);

        //Set the border's color to red
        bb.setBorderColor(java.awt.Color.RED);

        try
        {
            //Save the image to file
            bb.save(dataDir + "barcode.jpg");

            //Print message
            System.out.println("Process completed successfully.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}