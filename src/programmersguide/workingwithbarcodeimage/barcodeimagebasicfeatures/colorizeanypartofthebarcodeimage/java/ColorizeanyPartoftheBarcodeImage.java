/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimagebasicfeatures.colorizeanypartofthebarcodeimage.java;

import com.aspose.barcode.*;
import java.awt.*;

public class ColorizeanyPartoftheBarcodeImage
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimagebasicfeatures/colorizeanypartofthebarcodeimage/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the background color of the barcode
        bb.setBackColor(Color.YELLOW);

        //Set the foreground color of the barcode
        bb.setForeColor(Color.BLUE);

        //Set border's color
        bb.setBorderColor(Color.RED);

        //Set the code text's color
        bb.setCodeTextColor(Color.RED);

        //Caption's color
        bb.getCaptionAbove().setForeColor(Color.darkGray);
        bb.getCaptionBelow().setForeColor(Color.CYAN);

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




