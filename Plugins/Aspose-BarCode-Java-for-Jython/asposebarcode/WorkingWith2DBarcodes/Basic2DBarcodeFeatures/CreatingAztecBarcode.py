from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class CreatingAztecBarcode:

    def __init__(self):
        
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingAztecBarcode/'

        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.Aztec)

        builder.setCodeText("1234567890")

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "CreatingAztecBarcode.jpg")

        # Display Status
        print "Created Aztec Barcode Successfully."
    
if __name__ == '__main__':        
    CreatingAztecBarcode()