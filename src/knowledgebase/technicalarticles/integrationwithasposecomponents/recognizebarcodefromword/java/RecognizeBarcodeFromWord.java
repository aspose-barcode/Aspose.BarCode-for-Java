/* 
 * Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
 *
 * This file is part of Aspose.BarCode. The source code in this file
 * is only intended as a supplement to the documentation, and is provided
 * "as is", without warranty of any kind, either expressed or implied.
 */
 
package knowledgebase.technicalarticles.integrationwithasposecomponents.recognizebarcodefromword.java;

import com.aspose.barcoderecognition.BarCodeReadType;
import com.aspose.barcoderecognition.BarCodeReader;
import com.aspose.words.Document;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Shape;

import java.text.MessageFormat;

public class RecognizeBarcodeFromWord
{
    public static void main(String[] args) throws Exception
    {
        // The path to the documents directory.
        String dataDir = "src/knowledgebase/technicalarticles/integrationwithasposecomponents/recognizebarcodefromword/data/";

        try
        {
            // Describes how to create barcode in word document.
            // Generate barcode image.
            /*
            BarCodeBuilder builder = new BarCodeBuilder();
            builder.setSymbologyType(Symbology.Code39Standard);
            builder.setCodeText("1234567");
            String strBarCodeImageSave = "img.jpg";
            builder.save(strBarCodeImageSave);

            // add this image to word doc.
            Document doc = new Document();
            DocumentBuilder docBuilder = new DocumentBuilder(doc);
            docBuilder.insertImage(strBarCodeImageSave);
            String strWordFile = dataDir + "input.doc";
            doc.save(strWordFile);
            */

            // Open the input word document which contains the barcode.
            Document wordDocument = new Document(dataDir + "input.doc");

            // recognition part.
            // extract image from word document.
            NodeCollection<Shape> shapes = wordDocument.getChildNodes(NodeType.SHAPE, true, false);
            int imageIndex = 0;

            for(Shape shape :  shapes)
            {
                if (shape.hasImage())
                {
                    // if this shape is an image, extract image to file
                    String extension = ImageTypeToExtension(shape.getImageData().getImageType());
                    String imageFileName = MessageFormat.format("Image.ExportImages.{0} Out.{1}", imageIndex, extension);
                    String strBarCodeImageExtracted = dataDir + "" + imageFileName;
                    shape.getImageData().save(strBarCodeImageExtracted);

                    // recognize barcode from this image
                    BarCodeReader reader = new BarCodeReader(strBarCodeImageExtracted,BarCodeReadType.Code39Standard);
                    while (reader.read())
                    {
                        System.out.println("codetext: " + reader.getCodeText());
                    }
                    imageIndex++;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static String ImageTypeToExtension(int imageType) throws Exception
    {
        switch (imageType)
        {
            case com.aspose.words.ImageType.BMP:
                return "bmp";
            case com.aspose.words.ImageType.EMF:
                return "emf";
            case com.aspose.words.ImageType.JPEG:
                return "jpeg";
            case com.aspose.words.ImageType.PICT:
                return "pict";
            case com.aspose.words.ImageType.PNG:
                return "png";
            case com.aspose.words.ImageType.WMF:
                return "wmf";
            default:
                throw new Exception("Unknown image type.");
        }
    }
}




