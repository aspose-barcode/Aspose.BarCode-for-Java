from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import ImageQualityMode

class SetBarcodeImageQuality:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageQuality/'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        # Set the code text of the barcode
        bb.setCodeText("12345678")

        # Set the graphics drawing hint to be Anti Alias
        imageQualityMode = ImageQualityMode
        bb.setImageQuality(imageQualityMode.AntiAlias)

        # Set the symbology type to code39
        symbology=Symbology
        bb.setSymbologyType(symbology.Code39Extended)

        bb.save(dataDir + "SetBarcodeImageQuality.jpg")

        # Display Status.
        print "Set Barcode Image Quality, please check the output file."


if __name__ == '__main__':        
    SetBarcodeImageQuality()