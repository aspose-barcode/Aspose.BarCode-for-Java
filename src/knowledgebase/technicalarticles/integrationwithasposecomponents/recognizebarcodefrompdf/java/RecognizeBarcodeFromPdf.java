/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package knowledgebase.technicalarticles.integrationwithasposecomponents.recognizebarcodefrompdf.java;

import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;
import com.aspose.pdf.kit.PdfExtractor;

public class RecognizeBarcodeFromPdf
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/knowledgebase/technicalarticles/integrationwithasposecomponents/recognizebarcodefrompdf/data/";

        try
        {
            String strBarCodeImage = "";

            //Instantiate PdfExtractor object
            PdfExtractor extractor = new PdfExtractor();

            //Bind the input PDF document to extractor
            extractor.bindPdf(dataDir + "document.pdf");

            //Extract images from the input PDF document
            extractor.extractImage();
            String suffix = ".jpg";
            int imageCount = 1;
            while (extractor.hasNextImage()) {
                System.out.println("Extracting image " + imageCount);
                strBarCodeImage = dataDir + "tmpbarcode" + imageCount + suffix;
                extractor.getNextImage(strBarCodeImage);

                // recognize barcode from image
                BarCodeReader reader = new BarCodeReader(strBarCodeImage,BarCodeReadType.Code39Standard);
                while (reader.read())
                {
                    System.out.println("codetext: " + reader.getCodeText());
                }
                imageCount++;
                reader.close();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}




