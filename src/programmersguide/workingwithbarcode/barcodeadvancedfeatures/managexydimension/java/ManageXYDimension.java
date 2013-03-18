/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.managexydimension.java;

import com.aspose.barcode.*;
import com.aspose.words.SaveFormat;

public class ManageXYDimension
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/managexydimension/data/";

        try
        {
            //Instantiate barcode object
            BarCodeBuilder bb = new BarCodeBuilder();

            //Set the code text of the barcode
            bb.setCodeText("12345678");

            //Set the symbology type to code128
            bb.setSymbologyType(Symbology.Code128);

            //Save the image to file
            bb.save(dataDir + "barcode.jpg", SaveFormat.JPEG);

            //Set the x-dimension for the bars of the barcode
            bb.setxDimension(0.5f);

            //Save the image to file
            bb.save(dataDir + "barcodeXDimensionChanged.jpg", SaveFormat.JPEG);


            //Instantiate barcode object
            BarCodeBuilder bb1 = new BarCodeBuilder();

            //Set the code text of the barcode
            bb1.setCodeText("12345678");

            //Set the symbology type to code128
            bb1.setSymbologyType(Symbology.Pdf417);

            //Set the x-dimension for the bars of the barcode
            bb1.setxDimension(0.5f);

            //Save the image to file
            bb1.save(dataDir + "barcodeYDimensionChanged.jpg", SaveFormat.JPEG);

            // Display Status.
            System.out.println("BarCodes with different dimensions have been created successfully.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}




