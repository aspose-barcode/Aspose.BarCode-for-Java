//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.Alignment;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Caption;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

import java.awt.*;

public class BarcodeCaption {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(BarcodeCaption.class);

        String dst = dataDir + "barcode.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Code128);

        Caption caption = new Caption();
        caption.setText("Captions");
        caption.setTextAlign(Alignment.Middle);

        bb.setCaptionAbove(caption);
        bb.setCaptionBelow(caption);

        // Save the image
        bb.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
