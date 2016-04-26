from asposebarcode import Settings
from com.aspose.barcode import BarCodeBuilder
from com.aspose.barcode import Symbology
from jarray import array

class GenerateMultipleMacroPdf417Barcodes:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWith2DBarcodes/Utility2DBarcodeFeatures/GenerateMultipleMacroPdf417Barcodes/'
        
        # Instantiate barcode object
        builder =  BarCodeBuilder()

        symbology= Symbology
        builder.setSymbologyType(symbology.MacroPdf417)

        # Create array for storing multiple barcodes
        i = 1
        size = 4
        list_code_text = array("code-1", "code-2", "code-3", "code-last")
        str_file_id = 1

        # Check the listbox for getting codetext and generating the barcodes
        while (i<=size):
            builder.setCodeText(list_code_text[i - 1])
            # fileID should be same for all the generated barcodes
            builder.setMacroPdf417FileID(str_file_id)

            # Assign segmentID in increasing order (1,2,3,....)
            builder.setMacroPdf417SegmentID(i)

            # Save the barcode (fileid_segmentid.png)
            builder.save(dataDir + "#{i}.png")
        
        # Display Status
        print "Saved Images Successfully."


if __name__ == '__main__':        
    GenerateMultipleMacroPdf417Barcodes()