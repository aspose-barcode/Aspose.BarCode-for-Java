from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import AustraliaPostFormatControlCode

class AustraliaPostBarcode:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/AustraliaPostBarcode/'
        
        # Instantiate barcode object
        symbology = Symbology
        builder = BarCodeBuilder("1234567890", symbology.AustraliaPost)

        # Set format control code to standard
        australiaPostFormatControlCode=AustraliaPostFormatControlCode
        builder.setAustraliaPostFormatControlCode(australiaPostFormatControlCode.Standard)

        # Save the image to your system and set its image format to Jpeg
        builder.save(dataDir + "AusraliaPost-Barcode.jpg")

        # Display Status
        print "Generated Austrailia Post Barcode Successfully."


if __name__ == '__main__':        
    AustraliaPostBarcode()