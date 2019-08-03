package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.CodeLocation;

import javax.imageio.ImageIO;

public class GS1DatamatrixBarcodeWithWrappingText {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: GS1DatamatrixBarcodeWithWrappingText
        String CODICE = "(90)0843110730<<<<452287005001T8";
        String displayedText = "(90)0843" + "\r\n" +
                "110730<<<<" + "\r\n" +
                "452287" + "\r\n" +
                "005001T8" + "\r\n";

        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.GS_1_DATA_MATRIX, CODICE);
        generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.ABOVE);
        generator.getParameters().getBarcode().getCodeTextParameters().setTwoDDisplayText(displayedText);

        BufferedImage bitmap = generator.generateBarCodeImage();
        File imageFile = new File(dataDir + "Display2DText.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: GS1DatamatrixBarcodeWithWrappingText
    }

}
