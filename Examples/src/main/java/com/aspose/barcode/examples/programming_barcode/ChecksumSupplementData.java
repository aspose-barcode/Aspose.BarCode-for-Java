//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;

public class ChecksumSupplementData {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(ChecksumSupplementData.class);

        String dst = dataDir + "barcode.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to Code39
        bb.setSymbologyType(Symbology.Code39Standard);

        //Make the checksum to be visible on the barcode
        bb.setEnableChecksum(EnableChecksum.Default);

        // Save the image
        bb.save(dst);

        System.out.println("Barcode with checksum saved successfully.");
    }
}
