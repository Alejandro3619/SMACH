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
        <title>Formulario ValorParametro</title>
    </head>
    
    <jsp:useBean id="unValorParametro" class="Modelo.ValorParametro" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario ValorParametro</h1>
        <table border="1">
            <tr>
                <th>ValorParametroMax</th>
                <th>ValorParametroMin</th>
                <th>idParametro</th>
                <th></th>
            </tr>
        <c:forEach items="${unValorParametro.listar(0)}" var="laValorParametro">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorValorParametro" method="post">
                <td><input type="hidden" name="fIdValorParametro"      value="${laValorParametro.idValorParametro}">
                    <input type="text"   name="fValorParametroMax"     value="${laValorParametro.valorParametroMax}"></td>
                <td><input type="text"   name="fValorParametroMin"     value="${laValorParametro.valorParametroMin}"></td>
                <td><input type="number" name="fidParametro"           value="${laValorParametro.idParametro}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorValorParametro" method="post">
                <td><input type="hidden" name="fIdValorParametro" value="0">
                    <input type="text"   name="fValorParametroMax"></td>
                <td><input type="text"   name="fValorParametroMin"></td>
                <td><input type="number" name="fidParametro" value="0"></td>
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
