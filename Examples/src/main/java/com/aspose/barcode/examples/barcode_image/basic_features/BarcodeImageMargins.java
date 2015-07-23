//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BorderDashStyle;
import com.aspose.barcode.MarginsF;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class BarcodeImageMargins {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(BarcodeImageMargins.class);

        String dst = dataDir + "barcode-image-margins.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Code128);

        // sets the left margin
        bb.getMargins().setLeft(0.5f);

        // sets the right margin
        bb.getMargins().setRight(0f);

        // sets the top margin
        bb.getMargins().setTop(0f);

        // sets the bottom margin
        bb.getMargins().setBottom(0f);

        // Save the image
        bb.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
