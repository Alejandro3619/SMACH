<%-- 
    Document   : FormularioPelicula
    Created on : 26-sep-2017, 9:30:24
    Author     : SENA
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Riesgo</title>
    </head>
    
    <jsp:useBean id="unRiesgo" class="Modelo.Riesgo" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Riesgo</h1>
        <table border="1">
            <tr>
                <th>Punto Riesgo</th>
                <th>Fecha Riesgo</th>
                <th>idValorParametro</th>
                <th></th>
            </tr>
        <c:forEach items="${unRiesgo.listar(0)}" var="laRiesgo">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorRiesgo" method="post">
                <td><input type="hidden" name="fIdRiesgo"             value="${laRiesgo.idRiesgo}">
                    <input type="text"   name="fPuntoRiesgo"          value="${laRiesgo.puntoRiesgo}"></td>
                <td><input type="date"   name="fFechaRiesgo"          value="${laRiesgo.fechaRiesgo}"></td>
                <td><input type="number" name="fidValorParametro"     value="${laRiesgo.idValorParametro}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorRiesgo" method="post">
                <td><input type="hidden" name="fIdRiesgo" value="0">
                    <input type="text"   name="fPuntoRiesgo"></td>
                <td><input type="date"   name="fFechaRiesgo"></td>
                <td><input type="number" name="fidValorParametro" value="0"></td>
                <td><button type="submit" name="fAccion" value="Insertar">Insertar</button>
                    <button type="reset" name="fAccion" value="Limpiar">Limpiar</button></td>
            </form>
            </tr>
        </table>
        
        <c:forEach begin="1" end="5" var="i">
            <c:out value="${i}"/>
        </c:forEach>
        
    </body>
    
</html>