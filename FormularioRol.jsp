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
        <title>Formulario Rol</title>
    </head>
    
    <jsp:useBean id="unRol" class="Modelo.Rol" scope="request" />
    <body>
        <jsp:include page="jspf/Menu.jspf"></jsp:include>
        <h1>Formulario Rol</h1>
        <table border="1">
            <tr>
                <th>Nombre Rol</th>
                <th>Analista Rol</th>
                <th>Elaborador Rol</th>
                <th>Analisis Rol</th>
                <th>Solicitante Rol</th>
                <th>Municipio Rol</th>
                <th>Punto Toma Rol</th>
                <th>Departamento Rol</th>
                <th>Prestador Rol</th>
                <th>Resultado Rol</th>
                <th>Vereda Rol</th>
                <th>Parametro Rol</th>
                <th>Riesgo Rol</th>
                <th>Metodo Rol</th>
                <th>Valor Rol</th>
                <th>Usuario Rol</th>
                <th>Auditoria Rol</th>
                <th>Rol Rol</th>
                <th></th>
            </tr>
        <c:forEach items="${unRol.listar(0)}" var="laRol">
            <tr>
                <!--- El nombre del controlador tiene que ser exactamente el mismo de urlPatterns  --->
            <form action="ControladorRol" method="post">
                <td><input type="hidden" name="fIdRol"              value="${laRol.idRol}">
                    <input type="text"   name="fNombreRol"          value="${laRol.nombreRol}"></td>
                <td><input type="text"   name="fAnalistaRol"        value="${laRol.analistaRol}"></td>
                <td><input type="text"   name="fElaboradorRol"      value="${laRol.elaboradorRol}"></td>
                <td><input type="text"   name="fAnalisisRol"        value="${laRol.analisisRol}"></td>
                <td><input type="text"   name="fSolicitanteRol"     value="${laRol.solicitanteRol}"></td>
                <td><input type="text"   name="fMunicipioRol"       value="${laRol.municipioRol}"></td>
                <td><input type="text"   name="fPuntoTomaRol"       value="${laRol.puntoTomaRol}"></td>
                <td><input type="text"   name="fDepartamentoRol"    value="${laRol.departamentoRol}"></td>
                <td><input type="text"   name="fPrestadorRol"       value="${laRol.prestadorRol}"></td>
                <td><input type="text"   name="fResultadoRol"       value="${laRol.resultadoRol}"></td>
                <td><input type="text"   name="fVeredaRol"          value="${laRol.veredaRol}"></td>
                <td><input type="text"   name="fParametroRol"       value="${laRol.parametroRol}"></td>
                <td><input type="text"   name="fRiesgoRol"          value="${laRol.riesgoRol}"></td>
                <td><input type="text"   name="fMetodoRol"          value="${laRol.metodoRol}"></td>
                <td><input type="text"   name="fValorRol"           value="${laRol.valorRol}"></td>
                <td><input type="text"   name="fUsuarioRol"         value="${laRol.usuarioRol}"></td>
                <td><input type="text"   name="fAuditoriaRol"       value="${laRol.auditoriaRol}"></td>
                <td><input type="text"   name="fRolRol"             value="${laRol.rolRol}"></td>
                <td><button type="submit" name="fAccion" value="Modificar">Modificar</button>
                    <button type="submit" name="fAccion" value="Eliminar">Eliminar</button></td>
            </form>
            </tr>
        </c:forEach>
            <tr>
            <form action="ControladorRol" method="post">
                <td><input type="hidden" name="fIdRol" value="0">
                    <input type="text"   name="fNombreRol"></td>
                <td><input type="text"   name="fAnalistaRol"></td>
                <td><input type="text"   name="fElaboradorRol"></td>
                <td><input type="text"   name="fAnalisisRol"></td>
                <td><input type="text"   name="fSolicitanteRol"></td>
                <td><input type="text"   name="fMunicipioRol"></td>
                <td><input type="text"   name="fPuntoTomaRol"></td>
                <td><input type="text"   name="fDepartamentoRol"></td>
                <td><input type="text"   name="fPrestadorRol"></td>
                <td><input type="text"   name="fResultadoRol"></td>
                <td><input type="text"   name="fVeredaRol"></td>
                <td><input type="text"   name="fParametroRol"></td>
                <td><input type="text"   name="fRiesgoRol"></td>
                <td><input type="text"   name="fMetodoRol"></td>
                <td><input type="text"   name="fValorRol"></td>
                <td><input type="text"   name="fUsuarioRol"></td>
                <td><input type="text"   name="fAuditoriaRol"></td>
                <td><input type="text"   name="fRolRol"></td>
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
