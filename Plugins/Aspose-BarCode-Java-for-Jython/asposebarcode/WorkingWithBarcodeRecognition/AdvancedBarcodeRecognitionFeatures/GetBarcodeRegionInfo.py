from asposebarcode import Settings
from com.aspose.barcoderecognition import BarCodeReadType
from com.aspose.barcoderecognition import BarCodeReader

class GetBarcodeRegionInfo:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRegionInfo/'
        
        # initialize barcode reader
        img = dataDir + "barcode.jpg"
        barcode_reader_type = BarCodeReadType
        reader = BarCodeReader(img, barcode_reader_type.Code39Standard)

        # Try to recognize all possible barcodes in the image
        while (reader . read()):
        # Get the region information
            region = reader.getRegion()

            if (region != ""):
                # Display x and y coordinates of barcode detected
                point = region.getPoints()
                print "Top left coordinates: X = " 
                print point[0].x 
                print ", Y = " 
                print point[0].y
                print "Bottom left coordinates: X = "
                print point[1].x 
                print ", Y = "
                print point[1].y
                print "Bottom right coordinates: X = " 
                print point[2].x 
                print ", Y = " 
                print point[2].y
                print "Top right coordinates: X = "
                print point[3].x
                print ", Y = " 
                print point[3].y

            print "Codetext: " + reader.getCodeText()
        
        # Close reader
        reader.close()


if __name__ == '__main__':        
    GetBarcodeRegionInfo()