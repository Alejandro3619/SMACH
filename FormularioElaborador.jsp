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
        <title>Formulario Elaborador</title>
    </head>
    
    <jsp:useBean id="unElaborador" class="Modelo.Elaborador" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Elaborador</h1>
        <table border="1">
            <tr>
                <th>Nombre Elaborador</th>
                <th>Profesion Analista</th>
                <th></th>
            </tr>
        <c:forEach items="${unElaborador.listar(0)}" var="laElaborador">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorElaborador" method="post">
                <td><input type="hidden" name="fIdElaborador"        value="${laElaborador.idElaborador}">
                    <input type="text"   name="fNombreElaborador"    value="${laElaborador.nombreElaborador}"></td>
                <td><input type="text"   name="fProfesionAnalista"   value="${laElaborador.profesionAnalista}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorElaborador" method="post">
                <td><input type="hidden" name="fIdElaborador" value="0">
                    <input type="text"   name="fNombreElaborador"></td>
                <td><input type="text"   name="fProfesionAnalista"></td>
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