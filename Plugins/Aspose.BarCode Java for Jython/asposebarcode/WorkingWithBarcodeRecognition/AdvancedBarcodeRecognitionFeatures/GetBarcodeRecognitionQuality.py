from asposebarcode import Settings
from com.aspose.barcoderecognition import BarCodeReadType
from com.aspose.barcoderecognition import BarCodeReader

class GetBarcodeRecognitionQuality:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRecognitionQuality/'
        
        img = dataDir + "barcode.jpg"

        # initialize barcode reader
        barcode_reader_type = BarCodeReadType
        reader = BarCodeReader(img, barcode_reader_type.Code39Standard)

        # Call read method
        while (reader.read()):
            print "Barcode CodeText: " + reader.getCodeText()
            print " Barcode Type: "
            print reader.getReadType()
            percent = reader.getRecognitionQuality()
            print "Barcode Quality Percentage: "
            print percent
        

        # Close reader
        reader.close()


if __name__ == '__main__':        
    GetBarcodeRecognitionQuality()