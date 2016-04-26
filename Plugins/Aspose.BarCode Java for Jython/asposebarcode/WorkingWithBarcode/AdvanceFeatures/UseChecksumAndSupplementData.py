from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import EnableChecksum

class UseChecksumAndSupplementData:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/UseChecksumAndSupplementData/'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        # Set the Code text for the barcode
        bb.setCodeText("1234567")

        # Set the symbology type to Code39
        symbology=Symbology
        bb.setSymbologyType(symbology.Code39Standard)

        # Make the checksum to be visible on the barcode
        enableChecksum=EnableChecksum
        bb.setEnableChecksum(enableChecksum.Default)

        # Save the image to disk in JPG format
        bb.save(dataDir + "barcode.jpg")

        # Print message
        print "Barcode image generated successfully."

if __name__ == '__main__':        
    UseChecksumAndSupplementData()