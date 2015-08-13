module Asposebarcodejava
  module GetBarcodeRecognitionQuality
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        img = data_dir + "barcode.jpg"

        # initialize barcode reader
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

        # Call read method
        while reader.read()
            puts "Barcode CodeText: " + reader.getCodeText().to_s + " Barcode Type: " + reader.getReadType().to_s
            percent = reader.getRecognitionQuality()
            puts "Barcode Quality Percentage: " + percent.to_s
        end

        # Close reader
        reader.close()
    end
  end
end
