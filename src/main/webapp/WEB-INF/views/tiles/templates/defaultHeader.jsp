<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<script src="http://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="http://cdn.datatables.net/1.10.3/js/jquery.dataTables.min.js"    type="text/javascript"></script>
<link href="http://cdn.datatables.net/1.10.3/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
    $(function() {
        $("#dataTable").dataTable();
    });
</script>
<header>
    <link href="https://stackpath.bootstrapcdn.com/bootswatch/4.3.1/flatly/bootstrap.min.css" rel="stylesheet" integrity="sha384-T5jhQKMh96HMkXwqVMSjF3CmLcL1nT9//tCqu9By5XSdj7CwR0r+F3LTzUdfkkQf" crossorigin="anonymous">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <h3>ict-nearby-earthquakes</h3>
    </nav>
</header>