from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology

class WideNarrowRatio:

    def __init__(self):
        dataDir = dataDir = Settings.dataDir + 'WorkingWithBarcode/AdvanceBarcodeFeatures/WideNarrowRatio'
        
        # Instantiate barcode object
        bb = BarCodeBuilder()

        # Set the code text of the barcode
        bb.setCodeText("12345678")

        # Set the symbology type to code39
        symbology = Symbology
        bb.setSymbologyType(symbology.Code39Extended)

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(3.0)
        # Save the image to file
        bb.save(dataDir + "barcode_ratio_3.jpg")

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(5.0)
        # Save the image to file
        bb.save(dataDir + "barcode_ratio_5.jpg")

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(7.0)
        # Save the image to file
        bb.save(dataDir + "barcode_ratio_7.jpg")

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(9.0)
        # Save the image to file
        bb.save(dataDir + "barcode_ratio_9.jpg")

        # Display Status.
        print "BarCodes with different wide narrow ratios have been created successfully."

if __name__ == '__main__':        
    WideNarrowRatio()