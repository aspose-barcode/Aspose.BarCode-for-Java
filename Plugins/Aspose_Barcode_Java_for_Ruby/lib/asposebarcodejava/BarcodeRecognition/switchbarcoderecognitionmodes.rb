module Asposebarcodejava
  module SwitchBarcodeRecognitionModes
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        img = data_dir + "barcode.jpg"

        # initialize barcode reader
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

        # Set recognition mode
        reader.setRecognitionMode(Rjb::import('com.aspose.barcoderecognition.RecognitionMode').ManualHints)

        # Set manual hints
        reader.setManualHints(Rjb::import('com.aspose.barcoderecognition.ManualHint').InvertImage || Rjb::import('com.aspose.barcoderecognition.ManualHint').IncorrectBarcodes)

        # Call read method
        while reader.read()
            puts "Barcode CodeText: " + reader.getCodeText().to_s
        end

        # Close reader
        reader.close()
    end
  end
end
