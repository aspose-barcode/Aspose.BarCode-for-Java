---
title: 1.2.2.2. Foreground and Background color selection
type: docs
weight: 20
url: /java/1-2-2-2-foreground-and-background-color-selection/
---

## **Foreground and Background color selection**
To generate a QR Code with customize foreground and background color image, please refer to the following steps:

1. Select the Type
1. Input the QR Code content data
1. To select a new foreground color, click in foreground button under settings section
1. Similarly, To select a new background color, click in background button under settings section
1. Click **Generate Preview** button

That's It. You will see the generated QR Code of your selected foreground and background color image under preview section.

**How it works?**
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<URL>&foreColor=#8f013c&bgColor=#8a9e91&ecc=&size=&format=

Example:

http://localhost:8080/qrcodegen/api/qrcode/generate?data=http://aspose.com&foreColor=%238f013c&bgColor=%238a9e91&ecc=&size=&format=


{{< /highlight >}}

**Generated QR Code**

![todo:image_alt_text](1-2-2-2-foreground-and-background-color-selection_1.png)
#### **Java Script**
***addRequestSettings*** function is used to add customize QR Code settings options in the API request parameters. Here is the code spinet. 

{{< highlight java >}}

  addRequestSettings : function(requestString){

 requestString = requestString + "&ecc=" + this.$('.errorCorrectionCode option:selected').text();

 requestString = requestString + "&foreColor=" + encodeURIComponent(this.$('.input-fcolor').val());

 requestString = requestString + "&bgColor=" + encodeURIComponent(this.$('.input-bgcolor').val());

 if(this.$('.input-size').val() != "100x100")

 requestString = requestString + "&size=" + this.$('.input-size').val();

 return requestString;

 }

{{< /highlight >}}
#### **Java**
**Core API Method - QRCodeManagementController.generateQRCode** 

{{< highlight java >}}

 @RequestMapping(value = "generate", method = RequestMethod.GET,

    		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType_IMAGE_TIFF_VALUE, MediaType_IMAGE_BMP_VALUE})

    @ApiOperation(value = "Generate QR Code.")

    public ResponseEntity<byte[]>  generateQRCode(

    		@ApiParam( value = "data", name="data" , required = true)

    		@RequestParam("data") String data,

        HttpServletRequest request,HttpServletResponse response,

        @ApiParam( value = "A user-chosen password that can be used with password-based encryption (PBE) Algo PBEWITHMD5AND128BITAES-CBC-OPENSSL)", name="passKey", required=false) @RequestParam(required=false, value="passKey") String passKey,

        @ApiParam( value = "ForeColor e.g #000000 (Black - RGB(hex))", name="foreColor", required=false) @RequestParam(required=false, value="foreColor") String foreColor,

        @ApiParam( value = "BackgroundColor e.g #FFFFFF (White - RGB(hex))", name="bgColor", required=false) @RequestParam(required=false, value="bgColor") String bgColor,

        @ApiParam( value = "L|M|Q|H - Reed-Solomon error correctionCode Level(from low to high) default=Low", name="ecc", required=false) @RequestParam(required=false, value="ecc") String ecc,

        @ApiParam( value = "Image Size e.g #150x150", name="size", required=false) @RequestParam(required=false, value="size") String size,

        @ApiParam( value = "jpeg|tiff|gif|png|bmp - default=png", name="format", required=false) @RequestParam(required=false, value="format") String format,

        @ApiParam( value = "true|false default=false", name="download", required=false) @RequestParam(required=false, value="download") boolean download,

        @ApiIgnore @Value("#{request.getHeader('" +  ACCEPT_HEADER + "')}") String acceptHeaderValue) throws Exception {


{{< /highlight >}}

**Foreground and Background Color Code Spinet** - **QRCodeManagementController.generateQRCode**

{{< highlight java >}}

 builder.setForeColor(getColorValue(foreColor, "#000000"));

builder.setBackColor(getColorValue(bgColor, "#FFFFFF"));


private Color getColorValue(String foreColor, String defautlValue) {

 if(StringUtils.isBlank(foreColor))

 return Color.decode(defautlValue);

 else

 return Color.decode(foreColor);

 }


{{< /highlight >}}
