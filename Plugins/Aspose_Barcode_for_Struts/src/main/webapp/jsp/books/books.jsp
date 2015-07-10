<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/tags/AsposeAPI.tld" prefix="aspose"%>
<html>
<head>
<style>
.imgClass {
	background-image:
		url(https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSppkh09ZjwnSeBbZ4yj9Q3PjP69JD-B8OoWYkNDtnHGbHtX9oCTw);
	background-position: 0px 0px;
	background-repeat: no-repeat;
	background-color: none;
	cursor: pointer;
	width: 130px;
	height: 53px;
}
</style>
</head>
<body>
	<p>
	<h2>
		<img src="images/aspose-struts-logo.jpg" alt="Aspose.Barcode for Java">
		Aspose.Barcode Struts Example - Simple Book Store App
	</h2>
	<b>Available Books</b>
	<form name="bookform" action="/StrutsbookApp/bookaction.do">
		<table style="background-color: #82CAFA; border: 1px solid black"
			width="800">
			<tr style="color: white; background-color: red">
				<th>&nbsp;</th>
				<th>Book Name</th>
				<th>Author Name</th>
				<th>Book Cost</th>
				<th>BarCode</th>
			</tr>
			<%
				List bookList = (ArrayList) request.getAttribute("booksList");
				Iterator itr = bookList.iterator();
				while (itr.hasNext()) {
					Map map = (HashMap) itr.next();
			%>
			<tr style="color: white; background-color: blue">
				<td><input type="radio" name="bookId"
					value='<%=map.get("BookId")%>'
					onclick="javascript:enableEditDelete();"></td>
				<td><%=map.get("BookName")%></td>
				<td><%=map.get("AuthorName")%></td>
				<td><%=map.get("BookCost")%></td>
				<td><aspose:getBarcodeUrl symbology="Code128"
						billAmount='<%=map.get("BookCost").toString()%>' var="barcodeUrl" /><img
					src="${barcodeUrl}" alt="Sponsored by Aspose" /></td>
			</tr>
			<%
				}
			%>
		</table>
		</p>
		<p>
		<table>
			<tr>
				<td>
				<td align="left"><input type="submit" name="actionMethod"
					value="AddBook" /></td>
				<td align="left"><input type="submit" name="actionMethod"
					id="editbutton" value="EditBook" disabled="true" /></td>
				<td align="left"><input type="submit" name="actionMethod"
					id="deletebutton" value="DeleteBook" disabled="true"
					onclick="return checkDelete();" /></td>
				</td>
			</tr>
		</table>
	</form>
	</p>
	<script>
		function checkDelete() {
			return confirm("Are u sure to delete this book..?");
		}
		function enableEditDelete() {
			document.getElementById('editbutton').disabled = false;
			document.getElementById('deletebutton').disabled = false;
		}
	</script>
</body>
</html>