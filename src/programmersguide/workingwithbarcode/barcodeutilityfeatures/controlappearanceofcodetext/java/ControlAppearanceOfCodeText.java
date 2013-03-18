/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeutilityfeatures.controlappearanceofcodetext.java;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.Symbology;

import java.awt.*;

public class ControlAppearanceOfCodeText
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeutilityfeatures/controlappearanceofcodetext/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set up code text (data to be encoded)
        bb.setCodeText("1234567");

        //Set up code text color
        bb.setCodeTextColor(Color.RED);

        //Set the location of the code text to above the barcode
        bb.setCodeLocation(CodeLocation.Above);

        //Increase the space between code text and barcode to 1 point
        bb.setCodeTextSpace(1.0f);

        //Set the symbology type to Code128
        bb.setSymbologyType(Symbology.Code128);

        //Save the image to your system and set its image format to Jpeg
        bb.save(dataDir + "barcode.jpg", BarCodeImageFormat.Jpeg);

        // Display Status.
        System.out.println("Barcode with custom appearance saved as JPEG image successfully.");
    }
}




