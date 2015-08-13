module Asposebarcodejava
  module RecognizeSpecificSymbology
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        # initialize barcode reader
        img = data_dir + "barcode.jpg"
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        rd = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)
        
        # read barcode
        while rd.read()
            # print the code text, if barcode found
            puts "CodeText: " + rd.getCodeText().to_s
            
            # print the symbology type
            puts "Type: " + rd.getReadType().to_s
        end
    end
  end
end
