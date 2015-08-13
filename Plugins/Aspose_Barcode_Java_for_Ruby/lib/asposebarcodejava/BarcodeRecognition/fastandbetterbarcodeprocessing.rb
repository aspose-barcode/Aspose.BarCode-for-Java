module Asposebarcodejava
  module FastAndBetterBarcodeProcessing
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        img = data_dir + "test.png"

        # initialize barcode reader
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

        # Set recognition hints
        reader.setImageBinarizationHints(Rjb::import('com.aspose.barcoderecognition.RecognitionHints$ImageBinarization').MedianSmoothing)
        reader.setMedianSmoothingWindowSize(4)

        # Call read method
        while reader.read()
            puts "Barcode CodeText: " + reader.getCodeText().to_s
        end

        # Close reader
        reader.close()
    end
  end
end
