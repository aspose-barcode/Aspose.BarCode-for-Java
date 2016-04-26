from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class PatchCode:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/PatchCode/'
        
       # Instantiate barcode object
        builder = BarCodeBuilder()

        # Set Symbology type
        symbology = Symbology
        builder.setSymbologyType(symbology.PatchCode)

        # Set code text
        builder.setCodeText("Patch T")

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "PatchCode.jpg")

        # Display Status
        print "Generated PatchCode Successfully."

if __name__ == '__main__':        
    PatchCode()