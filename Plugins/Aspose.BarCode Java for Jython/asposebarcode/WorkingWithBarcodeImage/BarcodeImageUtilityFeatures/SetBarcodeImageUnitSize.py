from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import BarCodeImageFormat
from com.aspose.barcode import GraphicsUnit

class SetBarcodeImageUnitSize:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SetBarcodeImageUnitSize/'
        
        # Instantiate barcode object
        symbology = Symbology
        bb = BarCodeBuilder("12345678", symbology.Code128)

        # Measurement is Milimeter
        graphicsUnit = GraphicsUnit
        bb.setGraphicsUnit(graphicsUnit.Point)

        # Set the bar height to 3 points
        bb.setBarHeight(3.0)

        bb.save(dataDir + "SetBarcodeImageUnitSize.jpg")

        # Display Status.
        print "Set Barcode Image Unit Size, please check the output file."


if __name__ == '__main__':        
    SetBarcodeImageUnitSize()