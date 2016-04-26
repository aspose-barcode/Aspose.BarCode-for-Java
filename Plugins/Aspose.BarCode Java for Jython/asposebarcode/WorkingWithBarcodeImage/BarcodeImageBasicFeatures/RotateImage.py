from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class RotateImage:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageBasicFeatures/RotateImage'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        # Set the code text of the barcode
        bb.setCodeText("12345678")

        # Roate clockwise for 180 degree (upside down)
        bb.setRotationAngleF(180)

        # Set the symbology type to code39
        symbology = Symbology
        bb.setSymbologyType(symbology.Code39Extended)
        bb.save(dataDir + "Rotate.jpg");

        # Display Status.
        print "Done with image rotation, please check the output file."


if __name__ == '__main__':        
    RotateImage()