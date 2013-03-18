/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package knowledgebase.technicalarticles.integrationwithasposecomponents.integratewithasposewords.java;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.Symbology;
import com.aspose.words.*;

public class IntegrateWithAsposeWords
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/knowledgebase/technicalarticles/integrationwithasposecomponents/integratewithasposewords/data/";

        //Instantiate barcode object
        BarCodeBuilder bb = new BarCodeBuilder();

        //Set the Code text for the barcode
        bb.setCodeText("1234567");

        //Set the symbology type to Code39Standard
        bb.setSymbologyType(Symbology.Code39Standard);

        //Save the image to your system and set its image format to Jpeg
        bb.save(dataDir + "barcode.bmp", BarCodeImageFormat.Bmp);

        // user Aspose.Words to insert the barcode image into the Word document
        Document document = new Document();
        DocumentBuilder docBuilder = new DocumentBuilder(document);
        docBuilder.insertImage(dataDir + "barcode.bmp");
        document.save(dataDir + "Myfile.doc");
        document.save(dataDir + "document.pdf");
        // Display Status.
        System.out.println("Barcode saved to Word Document successfully.");
    }
}




