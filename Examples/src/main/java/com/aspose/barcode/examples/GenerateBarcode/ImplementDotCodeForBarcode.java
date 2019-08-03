package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarcodeGenerator;

import javax.imageio.ImageIO;

public class ImplementDotCodeForBarcode {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementDotCodeForBarcode
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DOT_CODE);
        generator.getParameters().getBarcode().getDotCode().setDotCodeMask(2);
        BufferedImage bitmap = generator.generateBarCodeImage();
        File imageFile = new File(dataDir + "DotCode.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: ImplementDotCodeForBarcode
    }

}
