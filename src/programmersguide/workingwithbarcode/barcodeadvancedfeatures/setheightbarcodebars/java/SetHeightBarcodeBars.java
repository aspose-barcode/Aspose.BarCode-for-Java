/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.setheightbarcodebars.java;

import com.aspose.barcode.*;
import com.aspose.words.SaveFormat;

public class SetHeightBarcodeBars
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/setheightbarcodebars/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text of the barcode
        bb.setCodeText("12345678");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Code128);

        //Set the bar height to be 3 milimeter
        bb.setBarHeight(3.0f);

        //
        bb.save(dataDir + "barcode3.jpg", SaveFormat.JPEG);

        //Set the bar height to be 7 milimeter
        bb.setBarHeight(7.0f);

        //
        bb.save(dataDir + "barcode7.jpg", SaveFormat.JPEG);

        //Set the bar height to be 11 milimeter
        bb.setBarHeight(11.0f);

        //
        bb.save(dataDir + "barcode11.jpg", SaveFormat.JPEG);

        // Display Status.
        System.out.println("Barcode with different heights has been created successfully.");
    }
}




