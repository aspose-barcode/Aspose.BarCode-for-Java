//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class SpecifySymbology {
	public static void main(String[] args) {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/BasicFeatures/";
				
		// Generate and save the image to file
		BarCodeBuilder builder = new BarCodeBuilder();
		// Set code text
		builder.setCodeText("test-123");
		// Set Symbology type
		builder.setSymbologyType(Symbology.Code39Standard);
		
		builder.save(dataDir + "Code39Standard.jpg");
	}
}
