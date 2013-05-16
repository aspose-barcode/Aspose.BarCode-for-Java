/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.usesupplementdataforbarcodes.java;

import com.aspose.barcode.*;

public class UseSupplementDataforBarcodes
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/usesupplementdataforbarcodes/data/";
        
        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("123456789123");

        //Set the symbology type to EAN13
        bb.setSymbologyType(Symbology.EAN13);
        
        //Set the supplement data (5 Digit)
        bb.setSupplementData("12345");

        //Set space between the supplemental barcode and main barcode
        bb.setSupplementSpace(2.0f);

        //Save the image to your system
        //and set its image format to Jpeg
        bb.save(dataDir + "barcode-supplement-data.out.jpg", "jpg");
        
        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}