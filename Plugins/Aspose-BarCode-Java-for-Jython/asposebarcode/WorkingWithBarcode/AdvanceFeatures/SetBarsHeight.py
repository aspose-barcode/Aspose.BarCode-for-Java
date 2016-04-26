from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class SetBarsHeight:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/SetBarsHeight'
        
        bb = BarCodeBuilder()

        # Set up code text (data to be encoded)
        bb.setCodeText("1234567")

        # Set the symbology type to Code128
        symbology = Symbology
        bb.setSymbologyType(symbology.Code128)

        # Set the bar height to be 3 milimeter
        bb.setBarHeight(3.0)
        bb.save(dataDir + "barcode3.jpg")

        # Set the bar height to be 7 milimeter
        bb.setBarHeight(7.0)
        bb.save(dataDir + "barcode7.jpg")

        # Set the bar height to be 11 milimeter
        bb.setBarHeight(11.0)
        bb.save(dataDir + "barcode11.jpg")

        # Display Status.
        print "Barcode with different heights has been created successfully."

if __name__ == '__main__':        
    SetBarsHeight()