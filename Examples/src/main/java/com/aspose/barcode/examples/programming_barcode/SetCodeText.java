//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class SetCodeText {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(SetCodeText.class);

        String dst = dataDir + "barcode-codetext.jpg";

        // Initialize the BarCodeBuilder class
        BarCodeBuilder builder;
        builder = new BarCodeBuilder();
        builder.setSymbologyType(Symbology.Code128);
        builder.setCodeText("1234567");

        //Set the width of the bars to 1 millimeters
        builder.setxDimension(1f);

        // Save the image
        builder.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
