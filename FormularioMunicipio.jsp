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
        <title>Formulario Municipio</title>
    </head>
    
    <jsp:useBean id="unMunicipio" class="Modelo.Municipio" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Municipio</h1>
        <table border="1">
            <tr>
                <th>Nombre Municipio</th>
                <th>idDepartamento</th>
                <th></th>
            </tr>
        <c:forEach items="${unMunicipio.listar(0)}" var="laMunicipio">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorMunicipio" method="post">
                <td><input type="hidden" name="fIdMunicipio"          value="${laMunicipio.idMunicipio}">
                    <input type="text"   name="fNombreMunicipio"      value="${laMunicipio.nombreMunicipio}"></td>
                <td><input type="number" name="fidDepartamento"       value="${laMunicipio.idDepartamento}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorMunicipio" method="post">
                <td><input type="hidden" name="fIdMunicipio" value="0">
                    <input type="text"   name="fNombreMunicipio"></td>
                <td><input type="number" name="fidDepartamento" value="0"></td>
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