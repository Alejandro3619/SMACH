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
        <title>Formulario Resultado</title>
    </head>
    
    <jsp:useBean id="unResultado" class="Modelo.Resultado" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Resultado</h1>
        <table border="1">
            <tr>
                <th>Valor Resultado</th>
                <th>idParametro</th>
                <th>idAnalisis</th>
                <th></th>
            </tr>
        <c:forEach items="${unResultado.listar(0)}" var="laResultado">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorResultado" method="post">
                <td><input type="hidden" name="fIdResultado"         value="${laResultado.idResultado}">
                    <input type="text"   name="fValorResultado"      value="${laResultado.valorResultado}"></td>
                <td><input type="number" name="fidParametro"         value="${laResultado.idParametro}"></td>
                <td><input type="number" name="fidAnalisis"          value="${laResultado.idAnalisis}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorResultado" method="post">
                <td><input type="hidden" name="fIdResultado" value="0">
                    <input type="text"   name="fValorResultado"></td>
                <td><input type="number" name="fidParametro" value="0"></td>
                <td><input type="number" name="fidAnalisis" value="0"></td>
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