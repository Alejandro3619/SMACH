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
        <title>Formulario Usuario</title>
    </head>
    
    <jsp:useBean id="unUsuario" class="Modelo.Usuario" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Usuario</h1>
        <table border="1">
            <tr>
                <th>Nombre Usuario</th>
                <th>Email Usuario</th>
                <th>Clave Usuario</th>
                <th>Celular Usuario</th>
                <th>Fecha Registro</th>
                <th>Fecha Ultima Clave</th>
                <th>idRol</th>
                <th></th>
            </tr>
        <c:forEach items="${unUsuario.listar(0)}" var="elUsuario">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorUsuario" method="post">
                <td><input type="hidden" name="fIdUsuario"              value="${elUsuario.idUsuario}">
                    <input type="text"   name="fNombreUsuario"          value="${elUsuario.nombreUsuario}"></td>
                <td><input type="text"   name="fEmailUsuario"           value="${elUsuario.emailUsuario}"></td>
                <td><input type="text"   name="fClaveUsuario"           value="${elUsuario.claveUsuario}"></td>
                <td><input type="text"   name="fCelularUsuario"         value="${elUsuario.celularUsuario}"></td>
                <td><input type="date"   name="fFechaRegistroUsuario"   value="${elUsuario.fechaRegistroUsuario}"></td>
                <td><input type="date"   name="fFechaUltimaClave"       value="${elUsuario.fechaUltimaClave}"></td>
                <td><input type="number" name="fidRol"                  value="${elUsuario.idRol}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorUsuario" method="post">
                <td><input type="hidden" name="fIdUsuario" value="0">
                    <input type="text"   name="fNombreUsuario"></td>
                <td><input type="text"   name="fEmailUsuario"></td>
                <td><input type="text"   name="fClaveUsuario"></td>
                <td><input type="text"   name="fCelularUsuario"></td>
                <td><input type="date"   name="fFechaRegistroUsuario"></td>
                <td><input type="date"   name="fFechaUltimaClave"></td>
                <td><input type="number" name="fidRol" value="0"></td>
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
