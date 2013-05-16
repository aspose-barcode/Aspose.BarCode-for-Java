/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeutilityfeatures.setcodetextforbarcode.java;

import com.aspose.barcode.*;

public class SetCodetextforBarcode
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeutilityfeatures/setcodetextforbarcode/data/";
        
        //Instantiate BarCodeBuilder object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text for the barcode
        bb.setCodeText("Aspose-123");

        //Set the symbology type to Code128
        bb.setSymbologyType(Symbology.Code128);

        //Set the width of the bars to 0.5 millimeter
        bb.setxDimension(0.5f);
        
        try
        {
            //save the barcode image to file
            bb.save(dataDir + "codetext.out.jpg");
            
            // Print message
            System.out.println("Barcode image generated successfully.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}