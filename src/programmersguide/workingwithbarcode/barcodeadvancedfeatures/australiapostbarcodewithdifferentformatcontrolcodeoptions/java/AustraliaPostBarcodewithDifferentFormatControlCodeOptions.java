/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.australiapostbarcodewithdifferentformatcontrolcodeoptions.java;

import com.aspose.barcode.*;

public class AustraliaPostBarcodewithDifferentFormatControlCodeOptions
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/australiapostbarcodewithdifferentformatcontrolcodeoptions/data/";
        
        // Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        BarCodeBuilder builder = new BarCodeBuilder( "1234567890", Symbology.AustraliaPost);

      //========================================= Standard ===
        // Set format control code to Standard
        builder.setAustraliaPostFormatControlCode( AustraliaPostFormatControlCode.Standard );

        // Save the image to disk in PNG format
        builder.save(dataDir + "barcodeStandard.out.png" );
        
        //====================================== Customer2 ===
        // Set format control code to Customer2
        builder.setAustraliaPostFormatControlCode( AustraliaPostFormatControlCode.Customer2 );

        // Save the image to disk in PNG format
        builder.save(dataDir + "barcodeCustomer2.out.png" );
        
        //====================================================
        
        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}