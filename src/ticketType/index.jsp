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
                <tr:form id="container">
                    <tr:panelPage>
                        <f:facet name="branding">
                            <tr:panelPageHeader chromeType="expanded">
                                <f:facet name="branding">
                                    <tr:panelGroupLayout layout="horizontal">
                                        <c:import url="/contents/cabecera.jsp"/>
                                    </tr:panelGroupLayout>
                                </f:facet>
                            </tr:panelPageHeader>
                        </f:facet>

                        <tr:spacer height="10px"/>
                        <tr:panelBorderLayout>
                            <f:facet name="left">
                                <tr:panelGroupLayout>
                                    <c:import url="/contents/menu.jsp"/>
                                </tr:panelGroupLayout>
                            </f:facet>

                            <tr:panelHorizontalLayout>
                                <tr:spacer width="15px"/>
                                <tr:navigationPane hint="bar" styleClass="subMenu">
                                    <tr:commandNavigationItem text="Crear" action="#{menu.goDatosTicketType}" immediate="true"/>
                                    <tr:commandNavigationItem text="Modificar/Dar de baja" action="#{menu.goBusquedaTicketType}" immediate="true"/>
                                </tr:navigationPane>
                            </tr:panelHorizontalLayout>
                            <tr:panelHorizontalLayout inlineStyle="width: 100%;">
                                <tr:spacer width="15px"/>
                                <tr:panelGroupLayout>
                                    <tr:spacer height="20px"/>
                                    <tr:panelHeader text="Gestión de tickets"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>
                                    <tr:outputText inlineStyle="font-size:12pt;text-align: left;"
                                                   value="Dentro de gestión de tickets, se pueden crear nuevos tipos de tickets o modificar los tipos de tickets dados de alta en el sistema."/>
                                    <tr:spacer height="20px"/>
                                </tr:panelGroupLayout>
                            </tr:panelHorizontalLayout>
                        </tr:panelBorderLayout>
                        <f:facet name="appCopyright">
                            <c:import url="/contents/footer.jsp"/>
                        </f:facet>
                    </tr:panelPage>
                </tr:form>
            </trh:body>
        </tr:document>
    </trh:html>
</f:view>


