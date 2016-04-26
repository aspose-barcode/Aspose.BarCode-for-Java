from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import Caption
from com.aspose.barcode import Alignment
from com.aspose.barcode import BarCodeImageFormat

class ManageCaption:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/UtilityFeatures/ManageCaption/'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        # Set up code text (data to be encoded)
        bb.setCodeText("1234567")

        # Set the symbology type to Code128
        symbology=Symbology
        bb.setSymbologyType(symbology.Code128)

        caption = Caption()
        alignment=Alignment
        caption.setText("Captions")
        caption.setTextAlign(alignment.Middle)

        bb.setCaptionAbove(caption)
        bb.setCaptionBelow(caption)

        bb.getCaptionAbove().setTextAlign(alignment.Left)
        bb.getCaptionAbove().setText("Aspose")
        bb.getCaptionAbove().setVisible(True)
        #bb.getCaptionAbove().setFont(java.awt.Font("Pristina", java.awt.Font.PLAIN, 14))
        #bb.getCaptionAbove().setForeColor(java.awt.Color.RED)

        bb.getCaptionBelow().setTextAlign(alignment.Right)
        bb.getCaptionBelow().setText("Aspose.BarCode Caption Below")
        bb.getCaptionBelow().setVisible(True)
        #bb.getCaptionBelow().setFont(java.awt.Font("Pristina", Font.PLAIN, 14))
        #bb.getCaptionBelow().setForeColor(java.awt.Color.RED)

        # Save the image to your system and set its image format to Jpeg
        barCodeImageFormat=BarCodeImageFormat
        bb.save(dataDir + "barcode.jpg", barCodeImageFormat.Jpeg)

        # Display Status
        print "Barcode with Captions saved successfully."


if __name__ == '__main__':        
    ManageCaption()