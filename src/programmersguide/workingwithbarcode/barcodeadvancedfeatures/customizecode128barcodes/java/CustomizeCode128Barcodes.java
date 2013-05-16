/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.customizecode128barcodes.java;

import com.aspose.barcode.*;

public class CustomizeCode128Barcodes
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/customizecode128barcodes/data/";
        
        // Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        BarCodeBuilder builder = new BarCodeBuilder("12345678", Symbology.Code128);
        
        // Set Code128 code set to A == 
        builder.setCode128CodeSet(Code128CodeSet.A);
        
        // Save the image to disk in PNG format
        builder.save(dataDir + "barcode-setA.out.png");
        
        // Set Code128 code set to C ==
        builder.setCode128CodeSet(Code128CodeSet.C);
        
        // Save the image to disk in PNG format
        builder.save(dataDir + "barcode-setC.out.png");
        
        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}