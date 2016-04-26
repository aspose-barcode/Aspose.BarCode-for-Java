from asposebarcode import Settings
from com.aspose.barcoderecognition import BarCodeReadType
from com.aspose.barcoderecognition import BarCodeReader
from javax.imageio import ImageIO
from java.io import File
from java.awt import Color
from java.awt import GradientPaint

class MakingBarcodeRegions:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/MakingBarcodeRegions/'
        
        # initialize barcode reader
        img = dataDir + "barcode.jpg"
        barcode_reader_type = BarCodeReadType
        reader = BarCodeReader(img, barcode_reader_type.Code39Standard)

        # Try to recognize all possible barcodes in the image
        while (reader.read()):
            # Display the symbology type
            print "BarCode Type: " 
            print reader.getReadType()
            # Display the codetext
            print "BarCode CodeText: "
            print reader.getCodeText()
            # Get the barcode region
            region = reader.getRegion()

            if (region != ""):
            # Initialize an object of type BufferedImage to get the Graphics object
                imageIO=ImageIO
                bufferedImage = imageIO . read(File(img))

                # Initialize graphics object from the image
                g = bufferedImage . getGraphics()

                color = Color
                # Initialize paint object
                p = GradientPaint(0, 0, color.red, 100, 100, color.pink, True)

                region.drawBarCodeEdges(g, color.RED)

                # Save the image

                imageIO.write(bufferedImage, "png", File(dataDir + "Code39StdOut.png"))
      
        # Close reader
        reader.close()


if __name__ == '__main__':        
    MakingBarcodeRegions()