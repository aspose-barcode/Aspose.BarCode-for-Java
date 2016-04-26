from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class CreatingPdf417Barcode:

    def __init__(self):
        
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingPdf417Barcode/'

        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.Pdf417)

        builder.setCodeText("12345")

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "CreatingPdf417Barcode.jpg")

        # Display Status
        print "Created Pdf417 Barcode Successfully."
        
if __name__ == '__main__':        
    CreatingPdf417Barcode()