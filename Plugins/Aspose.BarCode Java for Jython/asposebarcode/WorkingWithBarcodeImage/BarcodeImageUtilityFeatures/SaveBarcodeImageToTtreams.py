from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import BarCodeImageFormat
from java.io import ByteArrayOutputStream

class SaveBarcodeImageToTtreams:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SaveBarcodeImageToTtreams/'
        
        # Instantiate barcode object
        builder = BarCodeBuilder()

        # Set symbology of the barcode
        symbology=Symbology
        builder.setSymbologyType(symbology.Code128)

        # Set code text
        builder.setCodeText("123456")

        # Initialize ByteArrayOutputStream object
        out_stream = ByteArrayOutputStream()

        # Save barcode image
        barCodeImageFormat = BarCodeImageFormat
        builder.save(out_stream, barCodeImageFormat.Jpeg)

        # Display Status.
        print "Save Barcode Image to Streams, please check the output file."


if __name__ == '__main__':        
    SaveBarcodeImageToTtreams()