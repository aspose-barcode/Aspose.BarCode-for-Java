module Asposebarcodejava
  module SetBarcodeImageBorders
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        margins = Rjb::import('com.aspose.barcode.MarginsF').new
        # 6 milimeter space from left, right, top and bottom side of border
        margins.setLeft(2)
        margins.setRight(2)
        margins.setTop(2)
        margins.setBottom(2)

        # Set border's width to be 0.5 milimeter
        bb.setBorderWidth(0.5)

        # Border will be visible
        bb.setBorderVisible(true)

        # Set the border's color to red
        bb.setBorderColor(Rjb::import('java.awt.Color').RED)

        # Set margins
        bb.setMargins(margins)

        bb.save(data_dir + "border.jpg")

        # Display Status.
        puts "Set border margins, please check the output file."
    end
  end
end
