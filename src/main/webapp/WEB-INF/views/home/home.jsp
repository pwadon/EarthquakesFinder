<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/home" method="post">
    <h3>ADD City Longtitude and Latitude and check out 10 closest earthquakes in last 30 days !</h3>
    <div class="form" STYLE="width: 200px ">
    <input type="number" max ="90" min="-90" step="0.000001" name="latitude" placeholder="latitude" required="true"> <br>
    <input type="number" max ="180" min="-180" step="0.000001" name="longtitude" placeholder="longtitude" required="true" ><br>
    <input type="submit" name="button" value="add" ><br>
    </div>
</form>

<table>
<h4>Closest earthquakes :</h4>
    <c:forEach items="${earthquakeList}" var="earthquake">
        <tr>
            <td>
                ${earthquake.title} || ${earthquake.distanceFromGivenPoint}
            </td>
        </tr>
    </c:forEach>
</table>