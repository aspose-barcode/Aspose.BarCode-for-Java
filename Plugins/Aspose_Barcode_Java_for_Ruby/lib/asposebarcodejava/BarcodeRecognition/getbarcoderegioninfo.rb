module Asposebarcodejava
  module GetBarcodeRegionInfo
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        # initialize barcode reader
        img = data_dir + "barcode.jpg"
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

        # Try to recognize all possible barcodes in the image
        while reader.read()
            # Get the region information
            region = reader.getRegion()
            
            if region != nil
                # Display x and y coordinates of barcode detected
                point = region.getPoints()
                puts "Top left coordinates: X = " + point[0].x.to_s + ", Y = " + point[0].y.to_s
                puts "Bottom left coordinates: X = " + point[1].x.to_s + ", Y = " + point[1].y.to_s
                puts "Bottom right coordinates: X = " + point[2].x.to_s + ", Y = " + point[2].y.to_s
                puts "Top right coordinates: X = " + point[3].x.to_s + ", Y = " + point[3].y.to_s
            end
            puts "Codetext: " + reader.getCodeText().to_s
        end

        # Close reader
        reader.close()
    end
  end
end
