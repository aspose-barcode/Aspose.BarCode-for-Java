from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import MarginsF
from java.awt import Color

class SetBarcodeImageBorders:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageBorders/'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        margins = MarginsF()
        # 6 milimeter space from left, right, top and bottom side of border
        margins.setLeft(2)
        margins.setRight(2)
        margins.setTop(2)
        margins.setBottom(2)

        # Set border's width to be 0.5 milimeter
        bb.setBorderWidth(0.5)

        # Border will be visible
        bb.setBorderVisible(True)

        # Set the border's color to red
        color=Color
        bb.setBorderColor(color.RED)

        # Set margins
        bb.setMargins(margins)

        bb.save(dataDir + "border.jpg")

        # Display Status.
        print "Set border margins, please check the output file."


if __name__ == '__main__':        
    SetBarcodeImageBorders()