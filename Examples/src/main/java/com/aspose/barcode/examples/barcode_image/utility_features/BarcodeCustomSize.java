//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_image.utility_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class BarcodeCustomSize {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(BarcodeCustomSize.class);

        String dst = dataDir + "barcode-custom-size.jpg";

        //Instantiate barcode object
        BarCodeBuilder builder = new BarCodeBuilder();

        //Set the Code text for the barcode
        builder.setCodeText("1234567890");

        //Set the symbology type to Code39Standard
        builder.setSymbologyType(Symbology.Code39Standard);

        // Set auto size false
        builder.setAutoSize(false);

        // Set height
        builder.setImageHeight(50);

        // Set width
        builder.setImageWidth(120);

        // Save the image
        builder.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
