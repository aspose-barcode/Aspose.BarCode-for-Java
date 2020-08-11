---
title: 1.2.3. Saving QR Code
type: docs
weight: 30
url: /java/1-2-3-saving-qr-code/
---

## **Saving QR Code**
To Save a generated QR Code image, please refer to the following steps:

1. Select the Type
1. Input the QR Code content data
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button
1. Click the desired image format link such as PNG, GIF, JPEG, TIFF to download under **Download As** section

That's It. You will see **File Download** dialog box on your browser to save the generated QR Code on your system.

**How it works?**
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<URL>&foreColor=&bgColor=&ecc=&size=&format=[png|jpeg|gif|tiff|bmp]&download=true

Example:

http://localhost:8080/qrcodegen/api/qrcode/generate?data=http://aspose.com&foreColor=&bgColor=&ecc=&size=&format=png&download=true


{{< /highlight >}}
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

**Saving QR Code image Code Spinet** - **QRCodeManagementController.generateQRCode**

{{< highlight java >}}

 if(download){

 MediaType responseType = responseImageTypeDto.getMediaType();

         responseHeaders.setContentType(responseType);

         responseHeaders.add("Content-Disposition", "attachment; filename="+"Aspose_BarCode_QRCodeGen." + responseType.getSubtype());

 }

{{< /highlight >}}
