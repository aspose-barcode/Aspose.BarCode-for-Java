package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;

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

        BarCodeBuilder builder = new BarCodeBuilder(CODICE, EncodeTypes.GS_1_DATA_MATRIX);
        builder.setCodeLocation(CodeLocation.Right);
        builder.setDisplay2DText(displayedText);

        BufferedImage bitmap = builder.generateBarCodeImage();
        File imageFile = new File("Display2DText.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: GS1DatamatrixBarcodeWithWrappingText
    }

}
