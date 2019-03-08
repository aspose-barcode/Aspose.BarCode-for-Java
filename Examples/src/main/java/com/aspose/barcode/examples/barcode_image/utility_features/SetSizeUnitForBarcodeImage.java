//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_image.utility_features;

import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class SetSizeUnitForBarcodeImage {
    public static void main(String[] args) throws IOException {
    	
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SetSizeUnitForBarcodeImage.class) + "BarcodeImage/UtilityFeatures/";
    	
    	//Instantiate barcode object, Set the symbology type to code128 and Set the Code text for the barcode
        BarCodeGenerator bb = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_128, "1234567");
        
        //Set the bar height to 3 points
        bb.getBarHeight().setPoint(3.0f);

        // Save the image
        bb.save(dataDir + "barcode-size-unit.jpg");
    }
}
