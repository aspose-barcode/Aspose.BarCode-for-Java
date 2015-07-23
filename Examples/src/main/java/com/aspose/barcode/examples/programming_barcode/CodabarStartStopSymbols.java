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

public class CodabarStartStopSymbols {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(CodabarStartStopSymbols.class);

        String dst = dataDir + "barcode.jpg";

        // Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        BarCodeBuilder builder = new BarCodeBuilder("11112222333344", Symbology.Codabar);

        // Set the codabar start symbol to A
        builder.setCodabarStartSymbol(CodabarSymbol.A);

        // Set the codabar stop symbol to D
        builder.setCodabarStopSymbol(CodabarSymbol.D);

        // Save the image
        builder.save(dst);

        System.out.println("Barcode saved successfully.");
    }
}
