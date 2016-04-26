from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from java.awt import Color

class SetBarcodeImageColor:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageColor/'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        color = Color

        # Set the background color of the barcode
        bb.setBackColor(color.YELLOW)

        # Set the foreground color of the barcode
        bb.setForeColor(color.BLUE)

        # Set border's color
        bb.setBorderColor(color.RED)

        # Set the code text's color
        bb.setCodeTextColor(color.RED)

        # Caption's color
        bb.getCaptionAbove().setForeColor(color.darkGray)
        bb.getCaptionBelow().setForeColor(color.CYAN)

        bb.save(dataDir + "color.jpg")

        # Display Status.
        print "Applied color to barcode image, please check the output file."


if __name__ == '__main__':        
    SetBarcodeImageColor()