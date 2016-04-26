from asposebarcode import Settings
from com.aspose.barcoderecognition import BarCodeReadType
from com.aspose.barcoderecognition import BarCodeReader
from com.aspose.barcoderecognition import RecognitionMode
from com.aspose.barcoderecognition import ManualHint

class SwitchBarcodeRecognitionModes:

    def __init__(self):
        dataDir = Settings.dataDir + 'WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/SwitchBarcodeRecognitionModes/'
        
        img = dataDir + "barcode.jpg"

        # initialize barcode reader
        barcode_reader_type = BarCodeReadType
        reader = BarCodeReader(img, barcode_reader_type.Code39Standard)

        # Set recognition mode
        recognitionMode = RecognitionMode
        reader.setRecognitionMode(recognitionMode.ManualHints)

        # Set manual hints
        manualHint = ManualHint
        reader.setManualHints(manualHint.InvertImage)
        reader.setManualHints(manualHint.IncorrectBarcodes)

        # Call read method
        while (reader.read()):
            print "Barcode CodeText: " + reader.getCodeText()
       

        # Close reader
        reader.close()

if __name__ == '__main__':        
    SwitchBarcodeRecognitionModes()