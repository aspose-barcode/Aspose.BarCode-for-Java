from asposebarcode import Settings
from com.aspose.barcoderecognition import BarCodeReadType
from com.aspose.barcoderecognition import BarCodeReader

class GetAllOneDBarcodes:

    def __init__(self):
        
        dataDir = Settings.dataDir + 'WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetAllOneDBarcodes/'
        
        img = dataDir + "barcode+jpg"

        # initialize barcode reader
        barcode_reader_type = BarCodeReadType
        reader = BarCodeReader(img, barcode_reader_type.Code39Standard)

        # Call read method
        reader.read()

        # Now get all possible barcodes
        barcodes = reader.getAllPossibleBarCodes()

        i = 0
        while(java_values(i < strlen(barcodes))):

            # Display code text, symbology, detected angle, recognition percentage of the barcode
            print "Code Text: " + barcodes[i].getCodetext() + " Symbology: " + barcodes[i].getBarCodeReadType() + " Recognition percentage: " + barcodes[i].getAngle()

            # Display x and y coordinates of barcode detected
            point = barcodes[i].getRegion().getPoints()

            print "Top left coordinates: X = " + point[0].getX() + ", Y = " + point[0].getY()
            print "Bottom left coordinates: X = " + point[1].getX() + ", Y = " + point[1].getY()
            print "Bottom right coordinates: X = " + point[2].getX() + ", Y = " + point[2].getY()
            print "Top right coordinates: X = " + point[3].getX()+ ", Y = " + point[3].getY()
            break

        # Close reader
        reader.close()


if __name__ == '__main__':        
    GetAllOneDBarcodes()