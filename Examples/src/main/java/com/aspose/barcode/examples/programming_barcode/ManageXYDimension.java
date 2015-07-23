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

public class ManageXYDimension {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(ManageXYDimension.class);

        String dstCode128 = dataDir + "code128-YDimensionChanged.jpg";
        String dstPdf417 = dataDir + "pdf417-YDimensionChanged.jpg";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to Code128
        bb.setSymbologyType(Symbology.Code128);

        //Save the image to your system
        //and set its image format to Jpeg
        bb.save(dstCode128);

        //Set the X-Dimension for the bars of the barcode
        bb.setxDimension(0.5f);

        //Set the measuring unit of barcode to millimeter
        bb.setGraphicsUnit(GraphicsUnit.Millimeter);

        //Save the image to your system
        //and set its image format to Jpeg
        bb.save(dstCode128);


        //Instantiate barcode object
        BarCodeBuilder bb1 = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb1.setCodeText("1234567");

        //Set the symbology type to Pdf417
        bb1.setSymbologyType(Symbology.Pdf417);

        //Set the Y-Dimension for the bars of the barcode
        bb1.setyDimension(4);

        //Save the image to your system
        //and set its image format to Jpeg
        bb1.save(dstPdf417);

        System.out.println("Barcode saved successfully.");
    }
}
