//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.ImageQualityMode;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class RotateBarcodeImage {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(RotateBarcodeImage.class);

        String dst = dataDir + "barcode-image-rotate.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Code128);

        // Set the rotation angle of the barcode to 180 degree
        bb.setRotationAngleF(180);

        // Save the image
        bb.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
