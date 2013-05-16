/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.usechecksumforbarcodes.java;

import com.aspose.barcode.*;

public class UseChecksumforBarcodes
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/usechecksumforbarcodes/data/";
        
        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("12345678");

        //Set the symbology type to Code128
        bb.setSymbologyType(Symbology.Code128);

        //Make the checksum to be visible on the barcode
        bb.setAlwaysShowChecksum(true);

        //Save the image to your system
        //and set its image format to Jpeg
        bb.save(dataDir + "barcode-checksum.out.jpg", "jpg");
     
        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}