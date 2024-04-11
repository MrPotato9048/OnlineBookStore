<!DOCTYPE html>
<html>
<head><title>Book Store</title></head>
<body>
<h2>Add a Book</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm">
    <form:label path="title">Title</form:label><br/>
    <form:input type="text" path="title"/><br/><br/>
    <form:label path="author">Author</form:label><br/>
    <form:input type="text" path="author"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:textarea rows="5" cols="30" path="description"/><br/><br/>
    <form:label path="stock">Stock</form:label><br/>
    <form:input type="text" path="stock"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="text" path="price"/><br/><br/>
    <form:label path="cover">Cover image</form:label><br/>
    <form:input type="file" path="image" accept="image/*"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>