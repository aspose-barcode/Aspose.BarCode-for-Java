//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.GraphicsUnit;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class SetHeightBarcodeBars {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(SetHeightBarcodeBars.class);

        String dst = dataDir + "Code128-bar-height.jpg";

        // Initialize the BarCodeBuilder class
        BarCodeBuilder builder;
        builder = new BarCodeBuilder();
        builder.setSymbologyType(Symbology.Code128);
        builder.setCodeText("1234567");

        //Set the bar height to 3 millimeters
        builder.setBarHeight(3.0f);

        //Set the measuring unit of barcode to millimeter
        builder.setGraphicsUnit(GraphicsUnit.Millimeter);

        //Save the image locally and set its image format to Jpeg
        builder.save(dataDir + "barcode3.jpg");

        //Set the bar height to 3 millimeters
        builder.setBarHeight(7.0f);

        //Save the image locally and set its image format to Jpeg
        builder.save(dataDir + "barcode7.jpg");

        //Set the bar height to 3 millimeters
        builder.setBarHeight(11.0f);

        //Save the image locally and set its image format to Jpeg
        builder.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
