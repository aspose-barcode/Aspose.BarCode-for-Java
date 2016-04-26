from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from com.aspose.barcode import Caption
from com.aspose.barcode import Alignment
from com.aspose.barcode import BarCodeImageFormat

class SpecifySymbologies:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcode/UtilityFeatures/SpecifySymbologies'
        
        builder = BarCodeBuilder()

        # ============ Code39Standard =====================
        # set symbology type
        symbology=Symbology
        builder.setSymbologyType(symbology.Code39Standard)

        # Save image to disk
        builder.save(dataDir + "code39Standard.out.png")

        # ================== QR ===========================
        # set symbology type
        builder.setSymbologyType(symbology.QR)

        # Save image to disk
        builder.save(dataDir + "QR.out.png")

        # =============== Code128 =========================
        # set symbology type
        builder.setSymbologyType(symbology.Code128)

        # Save image to disk
        builder.save(dataDir + "code128.out.png")

        # Print message
        print "Barcode image(s) generated successfully."


if __name__ == '__main__':        
    SpecifySymbologies()