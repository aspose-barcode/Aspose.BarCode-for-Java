module Asposebarcodejava
  module RotateImage
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set the code text of the barcode
        bb.setCodeText("12345678")

        # Roate clockwise for 180 degree (upside down)
        bb.setRotationAngleF(180)

        # Set the symbology type to code39
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Extended)

        bb.save(data_dir + "Rotate.jpg")

        # Display Status.
        puts "Done with image rotation, please check the output file."
    end
  end
end
