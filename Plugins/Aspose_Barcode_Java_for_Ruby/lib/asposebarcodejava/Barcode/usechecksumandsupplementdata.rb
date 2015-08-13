module Asposebarcodejava
  module UseChecksumAndSupplementData
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set the Code text for the barcode
        bb.setCodeText("1234567")

        # Set the symbology type to Code39
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Standard)

        # Make the checksum to be visible on the barcode
        bb.setEnableChecksum(Rjb::import('com.aspose.barcode.EnableChecksum').Default)

        # Save the image to disk in JPG format
        bb.save(data_dir + "barcode.jpg")

        # Print message
        puts "Barcode image generated successfully."
    end
  end
end
