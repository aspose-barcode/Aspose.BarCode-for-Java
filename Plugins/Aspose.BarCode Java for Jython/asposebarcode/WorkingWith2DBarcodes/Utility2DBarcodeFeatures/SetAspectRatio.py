from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class SetAspectRatio:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Utility2DBarcodeFeatures/SetAspectRatio/'
        
        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.Pdf417)

        builder.setCodeText("1234567890")

        # Set Aspect Ratio to 3:2 or 1.5
        builder.setAspectRatio(1.5)

        # Save the image
        builder.save(dataDir + "SetAspectRatio.jpg")

        # Display Status
        print "Set Aspect Ratio Successfully."


if __name__ == '__main__':        
    SetAspectRatio()