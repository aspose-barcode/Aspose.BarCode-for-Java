package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;

import javax.imageio.ImageIO;

public class ImplementDotCodeForBarcode {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementDotCodeForBarcode
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.DOT_CODE);
        generator.setDotCodeMask(2);
        BufferedImage bitmap = generator.generateBarCodeImage();
        File imageFile = new File("DotCode.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: ImplementDotCodeForBarcode
    }

}
