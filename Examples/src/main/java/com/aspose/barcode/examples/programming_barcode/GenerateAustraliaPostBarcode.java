//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.programming_barcode;

import com.aspose.barcode.AustraliaPostFormatControlCode;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class GenerateAustraliaPostBarcode {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(GenerateAustraliaPostBarcode.class);

        // Set codetext
        String codeText = "12345678";

        // Initialize BarCodeBuilder
        BarCodeBuilder builder = new BarCodeBuilder(codeText, Symbology.AustraliaPost);

        // Set format control code to standard
        builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.Standard);
        // Save the image to disk in PNG format
        builder.save(dataDir + "AustraliaPost-Standard.png");

        // Set format control code to ReplyPaid
        builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.ReplyPaid);
        // Save the image to disk in PNG format
        builder.save(dataDir + "AustraliaPost-ReplyPaid.png");

        // Set format control code to Customer2
        builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.Customer2);
        // Save the image to disk in PNG format
        builder.save(dataDir + "AustraliaPost-Customer2.png");

        // Set format control code to Customer3
        builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.Customer3);
        // Save the image to disk in PNG format
        builder.save(dataDir + "AustraliaPost-Customer3.png");

        // Set format control code to Routing
        builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.Routing);
        // Save the image to disk in PNG format
        builder.save(dataDir + "AustraliaPost-Routing.png");

        // Set format control code to Redirection
        builder.setAustraliaPostFormatControlCode(AustraliaPostFormatControlCode.Redirection);
        // Save the image to disk in PNG format
        builder.save(dataDir + "AustraliaPost-Redirection.png");

        System.out.println("Barcode saved successfully.");
    }
}
