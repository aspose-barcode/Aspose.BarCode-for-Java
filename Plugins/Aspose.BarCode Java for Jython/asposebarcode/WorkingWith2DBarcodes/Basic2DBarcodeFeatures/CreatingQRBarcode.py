from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import CodeLocation

class CreatingQRBarcode:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingQRBarcode/'
        
        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.QR)

        builder.setCodeText("1234567890")

        # Hide code text
        codeLocation= CodeLocation
        builder.setCodeLocation(codeLocation.None)

        builder.setRotationAngleF(90)

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "CreatingQRBarcode.jpg")

        # Display Status
        print "Created QR Barcode Successfully."


if __name__ == '__main__':        
    CreatingQRBarcode()