//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;

import java.awt.*;

public class CodetextAppearance {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(CodetextAppearance.class);

        String dst = dataDir + "barcode.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the location of the code text to above the barcode
        bb.setCodeLocation(CodeLocation.Above);

        //Set up code text color
        bb.setCodeTextColor(Color.RED);

        // Save the image
        bb.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
