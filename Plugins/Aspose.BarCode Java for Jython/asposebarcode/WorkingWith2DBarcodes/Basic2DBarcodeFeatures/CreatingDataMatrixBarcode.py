from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class CreatingDataMatrixBarcode:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingDataMatrixBarcode/'

        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.DataMatrix)

        builder.setCodeText("1234567890")

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "CreatingDataMatrixBarcode.jpg")

        # Display Status
        print "Created DataMatrix Barcode Successfully."
    

if __name__ == '__main__':        
    CreatingDataMatrixBarcode()