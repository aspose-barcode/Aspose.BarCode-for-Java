from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import CodeLocation
from com.aspose.barcode import BarCodeImageFormat
from java.awt import Color

class CodeText:

    def __init__(self):        
        # Set appearance of the code text
        self.set_appearance()

        # Set code text for Barcode
        self.set_codetext()
        
    def set_appearance(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/UtilityFeatures/CodeText/'

        bb =  BarCodeBuilder()

        # Set up code text (data to be encoded)
        bb.setCodeText("1234567")

        # Set up code text color
        color = Color
        bb.setCodeTextColor(color.RED)

        # Set the location of the code text to above the barcode
        codeLocation= CodeLocation
        bb.setCodeLocation(codeLocation.Above)

        #Increase the space between code text and barcode to 1 point
        bb.setCodeTextSpace(1.0)

        # Set the symbology type to Code128
        symbology= Symbology
        bb.setSymbologyType(symbology.Code128)

        # Save the image to your system and set its image format to Jpeg
        barCodeImageFormat= BarCodeImageFormat
        bb.save(dataDir + "barcode.jpg", barCodeImageFormat.Jpeg)

        # Display Status
        print "Barcode with custom appearance saved as JPEG image successfully."

    

    def set_codetext(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/UtilityFeatures/CodeText/'

        # Instantiate barcode object
        bb =  BarCodeBuilder()

        # Set the code text for the barcode
        bb.setCodeText("Aspose-123")

        # Set the symbology type to Code128
        symbology= Symbology
        bb.setSymbologyType(symbology.Code128)

        # Set the width of the bars to 0.5 millimeter
        bb.setxDimension(0.5)

        # save the barcode image to file
        bb.save(dataDir + "codetext.out.jpg")

        # Print message
        print "Barcode image generated successfully."


if __name__ == '__main__':        
    CodeText()