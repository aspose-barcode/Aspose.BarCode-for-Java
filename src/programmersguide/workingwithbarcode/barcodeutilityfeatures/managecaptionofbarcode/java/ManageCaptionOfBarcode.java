/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package programmersguide.workingwithbarcode.barcodeutilityfeatures.managecaptionofbarcode.java;

import com.aspose.barcode.Alignment;
import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Caption;
import com.aspose.barcode.Symbology;
import com.aspose.words.SaveFormat;

public class ManageCaptionOfBarcode
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/programmersguide/workingwithbarcode/barcodeutilityfeatures/managecaptionofbarcode/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the code text of the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to code128
        bb.setSymbologyType(Symbology.Code128);

        Caption caption = new Caption();
        caption.setText("Captions");
        caption.setTextAlign(Alignment.Middle);

        bb.setCaptionAbove(caption);
        bb.setCaptionBelow(caption);

        bb.getCaptionAbove().setTextAlign(Alignment.Left);
        bb.getCaptionAbove().setText("Aspose");
        bb.getCaptionAbove().setVisible(true);
        //bb.getCaptionAbove().setFont(new java.awt.Font("Pristina", java.awt.Font.PLAIN, 14));
        //bb.getCaptionAbove().setForeColor(java.awt.Color.RED);

        bb.getCaptionBelow().setTextAlign(Alignment.Right);
        bb.getCaptionBelow().setText("Aspose.BarCode Caption Below");
        bb.getCaptionBelow().setVisible(true);
        //bb.getCaptionBelow().setFont(new java.awt.Font("Pristina", Font.PLAIN, 14));
        //bb.getCaptionBelow().setForeColor(java.awt.Color.RED);

        bb.save(dataDir + "barcode.jpg", SaveFormat.JPEG);

        System.out.println("BarCode with Captions saved successfully.");
    }
}




