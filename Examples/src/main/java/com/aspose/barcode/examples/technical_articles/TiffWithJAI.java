package com.aspose.barcode.examples.technical_articles;
//ExStart: TiffWithJAI
import com.aspose.barcode.*;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.TIFFDecodeParam;
 
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
 
/**
 * Read barcodes from multi-page TIFF image sample
 * Integreted Sun's JAI (Java Advanced Imaging) Library to working with tiff files
 * Details information about JAI: http://java.sun.com/javase/technologies/desktop/media/
 */
public class TiffWithJAI {
 
    public static void main(String[] args) throws Exception {
    	String fileName = "C:\\multi_page.tiff";
        Iterator readers = javax.imageio.ImageIO.getImageReadersBySuffix("tiff");
        if (readers.hasNext()) {
            File fi = new File(fileName);
            ImageInputStream iis = javax.imageio.ImageIO.createImageInputStream(fi);
            TIFFDecodeParam param = null;
            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", fi, param);
            // Get the page count of the TIFF image
            int pageCount = dec.getNumPages();
            ImageReader _imageReader = (ImageReader) (readers.next());
            if (_imageReader != null) {
                _imageReader.setInput(iis, true);
                //Feed each page to the BarCodeReader
                for (int i = 0; i < pageCount; i++) {
                    BufferedImage _bufferedImage = _imageReader.read(i);
                    BarCodeReader reader = new BarCodeReader(_bufferedImage, DecodeType.DATA_MATRIX);
                    //Read the barcodes in a single page
                    while (reader.read()){
                            System.out.println(reader.getCodeText());
                    }
                }
 
            }
        }
    }
}
//ExEnd: TiffWithJAI