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
                                    <tr:panelHeader text="Búsqueda de tipo de ticket"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>
                                    <tr:table width="100%" value="#{ticketType.ticketTypeAL}" var="row" rowBandingInterval="1" rows="10">
                                        <tr:column headerText="Nombre">
                                            <tr:outputText value="#{row.name}"/>
                                        </tr:column>
                                        <tr:column headerText="Precio">
                                            <tr:outputText value="#{row.price}"/>
                                        </tr:column>
                                        <tr:column headerText="Activo">
                                            <tr:selectBooleanCheckbox value="#{row.activo}" disabled="true"/>
                                        </tr:column>
                                        <tr:column headerText="Modificar">
                                            <f:facet name="header">
                                                <tr:outputText value="Modificar"/>
                                            </f:facet>
                                            <tr:commandLink text="Modificar" action="#{ticketType.goModificarTicketType}">
                                                <t:updateActionListener property="#{ticketType.ticketTypeData.id}" value="#{row.id}"/>
                                                <t:updateActionListener property="#{ticketType.ticketTypeData.name}" value="#{row.name}"/>
                                                <t:updateActionListener property="#{ticketType.ticketTypeData.price}" value="#{row.price}"/>
                                                <t:updateActionListener property="#{ticketType.ticketTypeData.category}" value="#{row.category}"/>
                                                <t:updateActionListener property="#{ticketType.ticketTypeData.activo}" value="#{row.activo}"/>
                                            </tr:commandLink>
                                        </tr:column>
                                    </tr:table>

                                    <tr:spacer height="20px"/>
                                    <tr:panelButtonBar>
                                        <tr:commandButton text="XLS" action="ticketType_xls"/>
                                    </tr:panelButtonBar>
                                    <tr:spacer height="20px"/>
                                    <tr:panelBorderLayout>
                                        <f:facet name="right">
                                            <h:panelGrid width="300px" style="height: 100%; color: #4F4C4D; text-align: left; font-size:10pt;">
                                                <tr:outputText value="En esta parte, se puede modificar los datos de los tickets dados de alta en el sistema de gestión de tickets de entradas. Para ello se pincha en el link \"Modificar\" de la fila del tipo de ticket a modificar."/>
                                                <tr:spacer height="5px"/>
                                                <tr:outputText value="En la parte inferior se encuentran las opciones para realizar el filtrado de la tabla, para ello se selecciona el estado de los tickets que se quieren visualizar y se pulsa el botón \"Buscar."/>
                                            </h:panelGrid>
                                        </f:facet>
                                        <tr:panelFormLayout>
                                            <tr:selectOneRadio id="variants" label="Estado" layout="horizontal" value="#{ticketType.opcionActivo}">
                                                <f:selectItem itemLabel="Activos" itemValue="ACTIVO"/>
                                                <f:selectItem itemLabel="No activos" itemValue="NO_ACTIVO"/>
                                                <f:selectItem itemLabel="Todos" itemValue="TODOS"/>
                                            </tr:selectOneRadio>
                                            <h:panelGrid width="100%" style="text-align: center;">
                                                <tr:panelButtonBar>
                                                    <tr:commandButton text="Buscar" action="#{ticketType.buscarTicketType}"/>
                                                </tr:panelButtonBar>
                                            </h:panelGrid>
                                        </tr:panelFormLayout>
                                    </tr:panelBorderLayout>
                                    <tr:spacer height="20px"/>
                                </tr:panelGroupLayout></tr:panelHorizontalLayout>
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


