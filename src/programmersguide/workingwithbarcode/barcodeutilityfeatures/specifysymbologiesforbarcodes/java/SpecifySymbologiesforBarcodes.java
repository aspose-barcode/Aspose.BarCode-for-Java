/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeutilityfeatures.specifysymbologiesforbarcodes.java;

import com.aspose.barcode.*;

public class SpecifySymbologiesforBarcodes
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeutilityfeatures/specifysymbologiesforbarcodes/data/";
        
        // generate and save the image to file
        BarCodeBuilder builder = new BarCodeBuilder();

        // ============ Code39Standard ===================== 
        // set symbology type
        builder.setSymbologyType(Symbology.Code39Standard);
        
        // Save image to disk
        builder.save(dataDir + "code39Standard.out.png");

        // ================== QR =========================== 
        // set symbology type
        builder.setSymbologyType(Symbology.QR);
        
        // Save image to disk
        builder.save(dataDir + "QR.out.png");
        
        // =============== Code128 ========================= 
        // set symbology type
        builder.setSymbologyType(Symbology.Code128);
        
        // Save image to disk
        builder.save(dataDir + "code128.out.png");
        
        // Print message
        System.out.println("Barcode image(s) generated successfully.");
    }
}