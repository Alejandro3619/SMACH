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
        <title>Formulario PuntoToma</title>
    </head>
    
    <jsp:useBean id="unPuntoToma" class="Modelo.PuntoToma" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario PuntoToma</h1>
        <table border="1">
            <tr>
                <th>Nombre Punto</th>
                <th>Descripcion Punto</th>
                <th>Direccion Punto</th>
                <th>Celular</th>
                <th>Fecha Registro</th>
                <th>Fecha Ultima Clave</th>
                <th>idRol</th>
                <th></th>
            </tr>
        <c:forEach items="${unPuntoToma.listar(0)}" var="laPuntoToma">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorPuntoToma" method="post">
                <td><input type="hidden" name="fIdPuntoToma"                value="${laPuntoToma.idPuntoToma}">
                    <input type="text"   name="fNombrePunto"                value="${laPuntoToma.nombrePunto}"></td>
                <td><input type="text"   name="fDescripcionPunto"           value="${laPuntoToma.descripcionPunto}"></td>
                <td><input type="text"   name="fDireccionPunto"             value="${laPuntoToma.direccionPunto}"></td>
                <td><input type="text"   name="fEstradomiciliarioPunto"     value="${laPuntoToma.estradomiciliarioPunto}"></td>
                <td><input type="number" name="fidVereda"                   value="${laPuntoToma.idVereda}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorPuntoToma" method="post">
                <td><input type="hidden" name="fIdPuntoToma" value="0">
                    <input type="text"   name="fNombrePunto"></td>
                <td><input type="text"   name="fDescripcionPunto"></td>
                <td><input type="text"   name="fDireccionPunto"></td>
                <td><input type="text"   name="fEstradomiciliarioPunto"></td>
                <td><input type="number" name="fidVereda" value="0"></td>
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