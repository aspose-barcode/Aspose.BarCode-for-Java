from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import AustraliaPostFormatControlCode

class ManageDimension:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/ManageDimension/'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        # Set up code text (data to be encoded)
        bb.setCodeText("1234567")

        # Set the symbology type to Code128
        symbology= Symbology
        bb.setSymbologyType(symbology.Code128)

        # Save the image to file
        bb.save(dataDir + "barcode.jpg")

        # Set the x-dimension for the bars of the barcode
        bb.setxDimension(0.5)
        # Save the image to file
        bb.save(dataDir + "barcodeXDimensionChanged.jpg")


        # Instantiate barcode object
        bb1 = BarCodeBuilder()

        # Set the code text of the barcode
        bb1.setCodeText("12345678")

        # Set the symbology type to code128
        bb1.setSymbologyType(symbology.Pdf417)

        # Set the x-dimension for the bars of the barcode
        bb1.setxDimension(0.5)

        # Save the image to file
        bb1.save(dataDir + "barcodeYDimensionChanged.jpg")

        # Display Status.
        print "BarCodes with different dimensions have been created successfully."


if __name__ == '__main__':        
    ManageDimension()