<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" %>
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

<f:facet name="appCopyright">
    <tr:panelGroupLayout>
        <tr:outputText value="Autor: Takuya Yamaguchi Padilla" inlineStyle="color: #4F4C4D"/>
        <tr:spacer width="20"/>
        <tr:outputText value="" inlineStyle="color: #4F4C4D"/>
        <tr:spacer width="20"/>
        <tr:outputText value="" inlineStyle="color: #4F4C4D"/>
    </tr:panelGroupLayout>
</f:facet>



