/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcodeimage.barcodeimagebasicfeatures.setbarcodeimagemargins.java;

import com.aspose.barcode.*;

public class SetBarcodeImageMargins
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcodeimage/barcodeimagebasicfeatures/setbarcodeimagemargins/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder("12345678", Symbology.Code128);

        //sets the top margin
        bb.getMargins().setTop(1f);

        //sets the bottom margin
        bb.getMargins().setBottom(5f);

        //sets the left margin
        bb.getMargins().setLeft(10f);

        //sets the right margin
        bb.getMargins().setRight(3f);

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




