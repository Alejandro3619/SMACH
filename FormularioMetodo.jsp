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
        <title>Formulario Metodo</title>
    </head>
    
    <jsp:useBean id="unMetodo" class="Modelo.Metodo" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Metodo</h1>
        <table border="1">
            <tr>
                <th>Nombre Metodo</th>
                <th>Norma Metodo</th>
                <th></th>
            </tr>
        <c:forEach items="${unMetodo.listar(0)}" var="laMetodo">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorMetodo" method="post">
                <td><input type="hidden" name="fIdMetodo"          value="${laMetodo.idMetodo}">
                    <input type="text"   name="fNombreMetodo"      value="${laMetodo.nombreMetodo}"></td>
                <td><input type="text"   name="fNormaMetodo"       value="${laMetodo.normaMetodo}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorMetodo" method="post">
                <td><input type="hidden" name="fIdMetodo" value="0">
                    <input type="text"   name="fNombreMetodo"></td>
                <td><input type="text"   name="fNormaMetodo"></td>
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