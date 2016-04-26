from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class GenerateBarcodeWithEmptyBars:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageBasicFeatures/GenerateBarcodeWithEmptyBars'
        
        # Instantiate barcode object
        symbology = Symbology
        bb = BarCodeBuilder("TEXT", symbology.Code128)

        # Set the FilledBars property to false
        bb.setFilledBars(False)

        bb.save(dataDir + "GenerateBarcodeWithEmptyBars.jpg")

        # Display Status.
        print "Generate Barcode With Empty Bars, please check the output file."


if __name__ == '__main__':        
    GenerateBarcodeWithEmptyBars()