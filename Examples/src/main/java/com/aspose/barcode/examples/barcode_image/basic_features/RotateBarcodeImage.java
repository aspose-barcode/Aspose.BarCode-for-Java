//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_image.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class RotateBarcodeImage {
    public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(RotateBarcodeImage.class) + "BarcodeImage/BasicFeatures/";

    	BarCodeBuilder bb = new BarCodeBuilder();
        bb.setCodeText("1234567");
        bb.setSymbologyType(Symbology.Code39Extended);

        //Rotate clockwise for 180 degree (upside down)
        bb.setRotationAngleF(180);

        // Save the image
        bb.save(dataDir + "barcode-image-rotate.jpg");
    }
}