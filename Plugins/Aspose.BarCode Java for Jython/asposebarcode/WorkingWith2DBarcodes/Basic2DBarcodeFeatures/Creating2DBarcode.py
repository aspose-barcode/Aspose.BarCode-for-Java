from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class Creating2DBarcode:

    def __init__(self):
        
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Basic2DBarcodeFeatures/Creating2DBarcode'
        
        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.Pdf417)

        # Width of each module
        builder.setxDimension(0.6)

        # Height of each module
        builder.setyDimension(1.2)
        builder.setCodeText("this is some test code text. \n Second line \n third line.")

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "Creating2DBarcode.jpg")

        # Display Status
        print "Created 2D Barcode Successfully."

if __name__ == '__main__':        
    Creating2DBarcode()