module Asposebarcodejava
  module GetAllOneDBarcodes
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

        img = data_dir + "barcode.jpg"

        # initialize barcode reader
        barcode_reader_type = Rjb::import('com.aspose.barcoderecognition.BarCodeReadType')
        reader = Rjb::import('com.aspose.barcoderecognition.BarCodeReader').new(img, barcode_reader_type.Code39Standard)

        # Call read method
        reader.read()

        # Now get all possible barcodes
        barcodes = reader.getAllPossibleBarCodes()

        i = 0
        while i < barcodes.length
            # Display code text, symbology, detected angle, recognition percentage of the barcode
            puts "Code Text: " + barcodes[i].getCodetext().to_s + " Symbology: " + barcodes[i].getBarCodeReadType().to_s + " Recognition percentage: " + barcodes[i].getAngle().to_s

            # Display x and y coordinates of barcode detected
            point = barcodes[i].getRegion().getPoints()

            puts "Top left coordinates: X = " + point[0].getX().to_s + ", Y = " + point[0].getY().to_s
            puts "Bottom left coordinates: X = " + point[1].getX().to_s + ", Y = " + point[1].getY().to_s
            puts "Bottom right coordinates: X = " + point[2].getX().to_s + ", Y = " + point[2].getY().to_s
            puts "Top right coordinates: X = " + point[3].getX().to_s + ", Y = " + point[3].getY().to_s
            i +=1
        end

        # Close reader
        reader.close()
    end
  end
end
