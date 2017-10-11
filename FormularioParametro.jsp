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
        <title>Formulario Parametro</title>
    </head>
    
    <jsp:useBean id="unParametro" class="Modelo.Parametro" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Parametro</h1>
        <table border="1">
            <tr>
                <th>Nombre Parametro</th>
                <th>Tipo Parametro</th>
                <th>Unidad Parametro</th>
                <th>idMetodo</th>
                <th></th>
            </tr>
        <c:forEach items="${unParametro.listar(0)}" var="laParametro">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorParametro" method="post">
                <td><input type="hidden" name="fIdParametro"         value="${laParametro.idParametro}">
                    <input type="text"   name="fNombreParametro"     value="${laParametro.nombreParametro}"></td>
                <td><input type="text"   name="fTipoParametro"       value="${laParametro.tipoParametro}"></td>
                <td><input type="text"   name="fUnidadParametro"     value="${laParametro.unidadParametro}"></td>
                <td><input type="number" name="fidMetodo"            value="${laParametro.idMetodo}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorParametro" method="post">
                <td><input type="hidden" name="fIdParametro" value="0">
                    <input type="text"   name="fNombreParametro"></td>
                <td><input type="text"   name="fTipoParametro"></td>
                <td><input type="text"   name="fUnidadParametro"></td>
                <td><input type="number" name="fidMetodo" value="0"></td>
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