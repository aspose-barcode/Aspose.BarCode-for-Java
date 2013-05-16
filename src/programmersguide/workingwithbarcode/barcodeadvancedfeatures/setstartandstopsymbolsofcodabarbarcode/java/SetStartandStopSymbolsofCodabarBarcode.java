/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeadvancedfeatures.setstartandstopsymbolsofcodabarbarcode.java;

import com.aspose.barcode.*;

public class SetStartandStopSymbolsofCodabarBarcode
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeadvancedfeatures/setstartandstopsymbolsofcodabarbarcode/data/";
        
        // Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        BarCodeBuilder builder = new BarCodeBuilder("$ 123:50", Symbology.Codabar);
        
        // Set the codabar start symbol to A
        builder.setCodabarStartSymbol(CodabarSymbol.A);
        
        // Set the codabar stop symbol to D
        builder.setCodabarStopSymbol(CodabarSymbol.D);
        
        // Save the image to disk in PNG format
        builder.save(dataDir + "barcode.out.png");
        
        // Print message
        System.out.println("Barcode image generated successfully.");
    }
}