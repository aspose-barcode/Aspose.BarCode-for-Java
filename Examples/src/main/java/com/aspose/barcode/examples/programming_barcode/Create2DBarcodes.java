//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

import java.awt.*;

public class Create2DBarcodes {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(Create2DBarcodes.class);

        String dst = dataDir + "barcode.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text of the barcode
        bb.setCodeText("12345678");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Pdf417);

        //Set the x-dimension for the bars of the barcode
        bb.setxDimension(0.5f);

        // Save the image
        bb.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
