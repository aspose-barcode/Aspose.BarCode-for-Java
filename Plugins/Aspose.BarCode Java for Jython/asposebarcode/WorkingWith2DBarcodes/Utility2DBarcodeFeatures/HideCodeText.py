from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import CodeLocation
from java.awt import Font

class HideCodeText:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Utility2DBarcodeFeatures/HideCodeText/'
        
        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.DataMatrix)

        builder.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n")

        codeLocation= CodeLocation
        builder.setCodeLocation(codeLocation.None)

        font =  Font

        builder.setCodeTextFont( Font("Serif", font.BOLD + font.ITALIC, 20))

        # Save the image
        builder.save(dataDir + "HideCodeText.jpg")

        # Display Status
        print "Hide Code Text Successfully."


if __name__ == '__main__':        
    HideCodeText()