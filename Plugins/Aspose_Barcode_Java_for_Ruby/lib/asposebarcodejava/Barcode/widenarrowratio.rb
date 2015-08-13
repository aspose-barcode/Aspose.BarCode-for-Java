module Asposebarcodejava
  module WideNarrowRatio
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set the code text of the barcode
        bb.setCodeText("12345678")

        # Set the symbology type to code39
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Extended)

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(3.0)
        # Save the image to file
        bb.save(data_dir + "barcode_ratio_3.jpg")

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(5.0)
        # Save the image to file
        bb.save(data_dir + "barcode_ratio_5.jpg")

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(7.0)
        # Save the image to file
        bb.save(data_dir + "barcode_ratio_7.jpg")

        # Set the wide to narrow ratio for the barcode
        bb.setWideNarrowRatio(9.0)
        # Save the image to file
        bb.save(data_dir + "barcode_ratio_9.jpg")

        # Display Status.
        puts "BarCodes with different wide narrow ratios have been created successfully."
    end
  end
end
