module Asposebarcodejava
  module SetBarcodeImageColor
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        color = Rjb::import('java.awt.Color')

        # Set the background color of the barcode
        bb.setBackColor(color.YELLOW)

        # Set the foreground color of the barcode
        bb.setForeColor(color.BLUE)

        # Set border's color
        bb.setBorderColor(color.RED)

        # Set the code text's color
        bb.setCodeTextColor(color.RED)

        # Caption's color
        bb.getCaptionAbove().setForeColor(color.darkGray)
        bb.getCaptionBelow().setForeColor(color.CYAN)

        bb.save(data_dir + "color.jpg")

        # Display Status.
        puts "Applied color to barcode image, please check the output file."
    end
  end
end
