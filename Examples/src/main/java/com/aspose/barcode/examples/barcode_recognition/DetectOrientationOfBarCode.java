//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2015 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.BarCode. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////

package com.aspose.barcode.examples.barcode_recognition;

import com.aspose.barcode.Alignment;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Caption;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;

public class DetectOrientationOfBarCode {
    public static void main(String[] args) {
        // The path to the documents directory.
        String dataDir = Utils.getDataDir(DetectOrientationOfBarCode.class);

        String dst = dataDir + "Code39.jpg";

        // Instantiate BarCodeReader object
        BarCodeReader reader = new BarCodeReader(dst, BarCodeReadType.Code39Standard);

        try
        {
            // read Code39 bar code
            while (reader.read())
            {
                // detect bar code orientation
                System.out.println("Rotaion Angle: " + reader.getAngle());
            }
            reader.close();
        }
        catch (Exception exp)
        {
            System.out.println(exp.getMessage());
        }
    }
}
