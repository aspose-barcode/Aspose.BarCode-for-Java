module Asposebarcodejava
  module BarcodeOrientation
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        # initialize barcode reader
        img = data_dir + "orientation.jpg"
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)
        
        # Set orientation
        reader.setOrientationHints(Rjb::import('com.aspose.barcoderecognition.RecognitionHints$Orientation').Rotate90)

        # Try to recognize all possible barcodes in the image
        while reader.read()
            # print the code text, if barcode found
            puts "CodeText: " + reader.getCodeText().to_s
        end

        # Close reader
        reader.close()
    end
  end
end
