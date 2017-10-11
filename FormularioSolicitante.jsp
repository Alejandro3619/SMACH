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
        <title>Formulario Solicitante</title>
    </head>
    
    <jsp:useBean id="unSolicitante" class="Modelo.Solicitante" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Solicitante</h1>
        <table border="1">
            <tr>
                <th>Nombre Solicitante</th>
                <th>Documento Solicitante</th>
                <th>Email Solicitante</th>
                <th>Telefono Solicitante</th>
                <th>idPrestador</th>
                <th>idPuntoToma</th>
                <th></th>
            </tr>
        <c:forEach items="${unSolicitante.listar(0)}" var="laSolicitante">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorSolicitante" method="post">
                <td><input type="hidden" name="fIdSolicitante"            value="${laSolicitante.idSolicitante}">
                    <input type="text"   name="fNombreSolicitante"        value="${laSolicitante.nombreSolicitante}"></td>
                <td><input type="text"   name="fDocumentoSolicitante"     value="${laSolicitante.documentoSolicitante}"></td>
                <td><input type="text"   name="fEmailSolicitante"         value="${laSolicitante.emailSolicitante}"></td>
                <td><input type="text"   name="fTelefonoSolicitante"      value="${laSolicitante.telefonoSolicitante}"></td>
                <td><input type="number" name="fidPrestador"              value="${laSolicitante.idPrestador}"></td>
                <td><input type="number" name="fidPuntoToma"              value="${laSolicitante.idPuntoToma}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorSolicitante" method="post">
                <td><input type="hidden" name="fIdSolicitante" value="0">
                    <input type="text"   name="fNombreSolicitante"></td>
                <td><input type="text"   name="fDocumentoSolicitante"></td>
                <td><input type="text"   name="fEmailSolicitante"></td>
                <td><input type="text"   name="fTelefonoSolicitante"></td>
                <td><input type="number" name="fidPrestador" value="0"></td>
                <td><input type="number" name="fidPuntoToma" value="0"></td>
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
