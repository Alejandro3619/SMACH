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
        <title>Formulario Analista</title>
    </head>
    
    <jsp:useBean id="unAnalista" class="Modelo.Analista" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Analista</h1>
        <table border="1">
            <tr>
                <th>Nombre Analista</th>
                <th>Cargo Analista</th>
                <th>Matricula Analista</th>
                <th></th>
            </tr>
        <c:forEach items="${unAnalista.listar(0)}" var="laAnalista">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorAnalista" method="post">
                <td><input type="hidden" name="fIdAnalista"              value="${laAnalista.idAnalista}">
                    <input type="text"   name="fNombreAnalista"          value="${laAnalista.nombreAnalista}"></td>
                <td><input type="text"   name="fCargoAnalista"           value="${laAnalista.cargoAnalista}"></td>
                <td><input type="text"   name="fMatriculaAnalista"       value="${laAnalista.matriculaAnalista}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorAnalista" method="post">
                <td><input type="hidden" name="fIdAnalista" value="0">
                    <input type="text"   name="fNombreAnalista"></td>
                <td><input type="text"   name="fCargoAnalista"></td>
                <td><input type="text"   name="fMatriculaAnalista"></td>
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