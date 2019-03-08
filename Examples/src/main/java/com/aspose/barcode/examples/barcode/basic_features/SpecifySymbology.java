//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class SpecifySymbology {
	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/BasicFeatures/";
				
		// Create instance of BarCodeGenerator, specify codetext and symbology in the constructor
    	BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODE_39_STANDARD, "Test-123");
		
    	generator.save(dataDir + "Code39Standard.jpg");
	}
}
