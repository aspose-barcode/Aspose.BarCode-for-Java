from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import CodabarSymbol

class StartStopSymbol:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/StartStopSymbol/'
        
        # Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        symbology=Symbology
        builder = BarCodeBuilder(" 123:50", symbology.Codabar)

        # Set the codabar start symbol to A
        codabarSymbol = CodabarSymbol
        builder.setCodabarStartSymbol(codabarSymbol.A)

        # Set the codabar stop symbol to D
        builder.setCodabarStopSymbol(codabarSymbol.D)

        # Save the image to disk in PNG format
        builder.save(dataDir + "barcode.out.png")

        # Print message
        print "Barcode image generated successfully."

if __name__ == '__main__':        
    StartStopSymbol()