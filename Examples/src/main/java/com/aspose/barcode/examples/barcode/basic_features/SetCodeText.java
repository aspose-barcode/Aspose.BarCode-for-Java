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

public class SetCodeText {
    public static void main(String[] args) {
    	// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SetCodeText.class) + "Barcode/BasicFeatures/";
    	
    	//Instantiate BarCodeBuilder object
    	BarCodeBuilder bb = new BarCodeBuilder();
    	//Set the code text for the barcode
    	bb.setCodeText("12345678");
    	//Set the symbology type to Code128
    	bb.setSymbologyType(Symbology.Code128);
    	//Set the width of the bars to 0.5 millimeter
    	bb.setxDimension(0.5f);
    	
    	//Save the barcode image to file
    	bb.save(dataDir + "setCodeText.jpg");
    }
}
