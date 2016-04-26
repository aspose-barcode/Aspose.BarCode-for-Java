from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import Resolution
from com.aspose.barcode import ResolutionMode

class GenerateBarcodeByCustomImageSize:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/GenerateBarcodeByCustomImageSize/'
        
        # Instantiate barcode object
        symbology = Symbology
        builder = BarCodeBuilder("1234567890", symbology.Code39Standard)

        # Set auto size false
        builder.setAutoSize(False)

        # Set height
        builder.setImageHeight(50)

        # Set width
        builder.setImageWidth(120)

        builder.save(dataDir + "GenerateBarcodeByCustomImageSize.jpg")

        # Display Status.
        print "Generate Barcode By Custom Image Size, please check the output file."


if __name__ == '__main__':        
    GenerateBarcodeByCustomImageSize()