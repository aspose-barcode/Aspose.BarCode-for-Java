/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.usechecksumandsupplementdata.java;

import com.aspose.barcode.*;

public class UseChecksumAndSupplementData
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/usechecksumandsupplementdata/data/";

        // Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        BarCodeBuilder builder = new BarCodeBuilder("12345678", Symbology.Code128);

        // Set Code128 code set to Auto
        builder.setCode128CodeSet(Code128CodeSet.Auto);

        // Save the image to disk in JPG format
        builder.save(dataDir + "barcode.jpg");

        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}




