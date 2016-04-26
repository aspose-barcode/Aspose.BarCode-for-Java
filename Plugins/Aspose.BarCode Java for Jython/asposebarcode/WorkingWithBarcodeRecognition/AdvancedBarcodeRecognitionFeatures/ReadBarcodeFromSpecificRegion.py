from asposebarcode import Settings
from com.aspose.barcoderecognition import BarCodeReadType
from com.aspose.barcoderecognition import BarCodeReader
from java.io import FileInputStream
from java.awt import Rectangle

class ReadBarcodeFromSpecificRegion:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/ReadBarcodeFromSpecificRegion/'
        
        # Open the stream. Read only access is enough for Aspose.BarCode to load an image.
        stream = FileInputStream(dataDir + "test.png")

        # Create an instance of BarCodeReader class
        # and specify an area to look for the barcode

        barcode_reader_type =  BarCodeReadType
        reader =  BarCodeReader(stream,  Rectangle(0, 0, 10, 50), barcode_reader_type.Code39Standard)

        # TRead all barcodes in the provided area
        while (reader.read()):
            # Display the codetext and symbology type of the barcode found
            print "Codetext: "
            print reader.getCodeText()
            print " Symbology: "
            print reader.getReadType()        

        # Close reader
        reader.close()

if __name__ == '__main__':        
    ReadBarcodeFromSpecificRegion()