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

public class WideNarrowRatio {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(WideNarrowRatio.class);

        String dst = dataDir + "code39-wide-narrow-ratio.jpg";

        // Initialize the BarCodeBuilder class
        BarCodeBuilder builder;
        builder = new BarCodeBuilder();
        builder.setSymbologyType(Symbology.Code39Standard);
        builder.setCodeText("1234567");

        //Set the wide to narrow ratio for the barcode
        builder.setWideNarrowRatio(3.0f);

        //Save the image to your system
        //and set its image format to Jpeg
        builder.save(dataDir + "barcode_ratio_3.jpg");

        //Set the wide to narrow ratio for the barcode
        builder.setWideNarrowRatio(5.0f);

        //Save the image to your system
        //and set its image format to Jpeg
        builder.save(dataDir + "barcode_ratio_5.jpg");

        //Set the wide to narrow ratio for the barcode
        builder.setWideNarrowRatio(7.0f);

        //Save the image to your system
        //and set its image format to Jpeg
        builder.save(dataDir + "barcode_ratio_7.jpg");

        //Set the wide to narrow ratio for the barcode
        builder.setWideNarrowRatio(9.0f);

        //Save the image to your system
        //and set its image format to Jpeg
        builder.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
