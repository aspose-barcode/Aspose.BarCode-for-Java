from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class SetBarcodeImageMargins:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageMargins/'
        
        # Instantiate barcode object
        symbology = Symbology
        bb = BarCodeBuilder("12345678", symbology.Code128)

        # sets the top margin
        bb.getMargins().setTop(4)

        # sets the bottom margin
        bb.getMargins().setBottom(5)

        # sets the left margin
        bb.getMargins().setLeft(2)

        # sets the right margin
        bb.getMargins().setRight(3)

        bb.save(dataDir + "SetBarcodeImageMargins.jpg")

        # Display Status.
        print "Set Barcode Image Margins, please check the output file."


if __name__ == '__main__':        
    SetBarcodeImageMargins()