<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" %>
<%--@page errorPage="errores.jsp"--%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@taglib uri="http://myfaces.apache.org/trinidad" prefix="tr"%>
<%@taglib uri="http://myfaces.apache.org/trinidad/html" prefix="trh"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <trh:html>
        <tr:document>
            <c:import url="/contents/head.jsp"/>
            <trh:body>
                <tr:messages/>
                <tr:form id="container">
                    <tr:panelPage>
                        <f:facet name="branding">
                            <c:import url="/contents/cabecera.jsp"/>
                        </f:facet>
                        <f:facet name="navigation2">
                            <c:import url="/contents/menu.jsp"/>
                        </f:facet>
                        <tr:spacer height="20px"/>
                        <tr:panelHeader text="Bienvenido a la aplicación de gestion de entradas"/>
                        <tr:spacer height="20px"/>
                        
                        <c:import url="../user/buscarUser_1.jsp"/>
                        <tr:spacer height="20px"/>
                    </tr:panelPage>
                </tr:form>
            </trh:body>
        </tr:document>
    </trh:html>
</f:view>


