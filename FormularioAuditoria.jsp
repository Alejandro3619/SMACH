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
        <title>Formulario Auditoria</title>
    </head>
    
    <jsp:useBean id="unAuditoria" class="Modelo.Auditoria" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Auditoria</h1>
        <table border="1">
            <tr>
                <th>Fecha Auditoria</th>
                <th>Descripcion Auditoria</th>
                <th>idUsuario</th>
                <th></th>
            </tr>
        <c:forEach items="${unAuditoria.listar(0)}" var="laAuditoria">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorAuditoria" method="post">
                <td><input type="hidden" name="fIdAuditoria"            value="${laAuditoria.idAuditoria}">
                    <input type="date"   name="fFechaAuditoria"         value="${laAuditoria.fechaAuditoria}"></td>
                <td><input type="text"   name="fDescripcionAuditoria"   value="${laAuditoria.descripcionAuditoria}"></td>
                <td><input type="number" name="fidUsuario"              value="${laAuditoria.idUsuario}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorAuditoria" method="post">
                <td><input type="hidden" name="fIdAuditoria" value="0">
                    <input type="date"   name="fFechaAuditoria"></td>
                <td><input type="text"   name="fDescripcionAuditoria"></td>
                <td><input type="number" name="fidUsuario" value="0"></td>
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
