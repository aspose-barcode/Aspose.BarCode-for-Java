/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.widenarrowratio.java;

import com.aspose.barcode.*;

public class WideNarrowRatio
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/widenarrowratio/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text of the barcode
        bb.setCodeText("12345678");

        //Set the symbology type to code39
        bb.setSymbologyType(Symbology.Code39Extended);

        try
        {
            //Set the wide to narrow ratio for the barcode
            bb.setWideNarrowRatio(3.0f);

            //Save the image to file
            bb.save(dataDir + "barcode_ratio_3.jpg");

            //Set the wide to narrow ratio for the barcode
            bb.setWideNarrowRatio(5.0f);

            //Save the image to file
            bb.save(dataDir + "barcode_ratio_5.jpg");

            //Set the wide to narrow ratio for the barcode
            bb.setWideNarrowRatio(7.0f);

            //Save the image to file
            bb.save(dataDir + "barcode_ratio_7.jpg");

            //Set the wide to narrow ratio for the barcode
            bb.setWideNarrowRatio(9.0f);

            //Save the image to file
            bb.save(dataDir + "barcode_ratio_9.jpg");

            // Display Status.
            System.out.println("BarCodes with different wide narrow ratios have been created successfully.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}




