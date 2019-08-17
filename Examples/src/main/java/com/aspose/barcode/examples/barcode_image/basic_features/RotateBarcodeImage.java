//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_image.basic_features;

import java.io.IOException;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class RotateBarcodeImage {
    public static void main(String[] args) throws IOException {
		//ExStart: RotateBarcodeImage
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(RotateBarcodeImage.class) + "BarcodeImage/BasicFeatures/";
		
		BarcodeGenerator bb = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.CODE_39_EXTENDED,"1234567");
		
		//Rotate clockwise for 180 degree (upside down)
		bb.getParameters().setRotationAngle(180);
		
		// Save the image
		bb.save(dataDir + "barcode-image-rotate.jpg");
		//ExEnd: RotateBarcodeImage
    }
}