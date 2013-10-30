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
<f:subview id="cabecera">
    <tr:subform>
        <%--tr:panelGroupLayout layout="horizontal">
            <tr:image source="../media/logo_4.png" shortDesc="corporate"/>
            <h:panelGrid width="100%">
                <tr:outputText value="Desarrollo de aplicación web de gestión de entradas" styleClass="titulocabecera"/>
            </h:panelGrid>
        </tr:panelGroupLayout--%>
        <tr:panelBorderLayout>
            <f:facet name="left">
                <tr:goLink destination="../manual/Manual.pdf" targetFrame="_blank">
                <tr:image source="../media/logo_4.png" shortDesc="corporate"/>
                </tr:goLink>
            </f:facet>
            <f:facet name="right">
                <tr:image source="../media/logo.png"/>
            </f:facet>
            <h:panelGrid width="100%" style="height: 100%; text-align: center;">
                <tr:outputText value="Desarrollo de aplicación web de gestión de entradas" styleClass="titulocabecera"/>
            </h:panelGrid>
        </tr:panelBorderLayout>
    </tr:subform>
</f:subview>



