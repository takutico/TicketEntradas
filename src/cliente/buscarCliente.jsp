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
                                <tr:spacer width="15px"></tr:spacer>
                                <tr:navigationPane hint="bar" styleClass="subMenu">
                                    <tr:commandNavigationItem text="Crear" action="#{menu.goDatosCliente}" immediate="true"/>
                                    <tr:commandNavigationItem text="Modificar/Dar de baja" action="#{menu.goBusquedaCliente}" immediate="true"/>
                                </tr:navigationPane>
                            </tr:panelHorizontalLayout>
                            <tr:panelHorizontalLayout inlineStyle="width: 100%;">
                                <tr:spacer width="15px"/>
                                <tr:panelGroupLayout id="bodyTicket">
                                    <tr:panelHeader text="Búsqueda de clientes"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>

                                    <tr:group rendered="#{cliente.renderClientesAL}">
                                        <tr:table width="100%" value="#{cliente.clientesAL}" var="row" rowBandingInterval="1" rows="10">
                                            <%--tr:column >
                                                <tr:outputText value="#{row.id}"/>
                                            </tr:column--%>
                                            <tr:column headerText="Apellidos">
                                                <tr:outputText value="#{row.apellidos}"/>
                                            </tr:column>
                                            <tr:column headerText="Nombre">
                                                <tr:outputText value="#{row.nombre}"/>
                                            </tr:column>
                                            <tr:column headerText="NIF">
                                                <tr:outputText value="#{row.nif}"/>
                                            </tr:column>
                                            <%--tr:column headerText="Dirección">
                                                <tr:outputText value="#{row.direccion}"/>
                                            </tr:column>
                                            <tr:column headerText="Localidad">
                                                <tr:outputText value="#{row.localidad}"/>
                                            </tr:column>
                                            <tr:column headerText="Provincia">
                                                <tr:outputText value="#{row.provincia}"/>
                                            </tr:column>
                                            <tr:column headerText="CP">
                                                <tr:outputText value="#{row.cp}"/>
                                            </tr:column--%>
                                            <tr:column headerText="Email">
                                                <tr:outputText value="#{row.email}"/>
                                            </tr:column>
                                            <%--tr:column headerText="Email 2">
                                                <tr:outputText value="#{row.email_2}"/>
                                            </tr:column--%>
                                            <tr:column headerText="Teléfono">
                                                <tr:outputText value="#{row.telefono}"/>
                                            </tr:column>
                                            <tr:column headerText="Móvil">
                                                <tr:outputText value="#{row.movil}"/>
                                            </tr:column>
                                            <tr:column headerText="Oferta email">
                                                <tr:selectBooleanCheckbox value="#{row.ofertaEmail}" disabled="true"/>
                                            </tr:column>
                                            <%--tr:column headerText="Oferta SMS">
                                                <tr:selectBooleanCheckbox value="#{row.ofertaSms}"/>
                                            </tr:column>
                                            <tr:column headerText="Oferta postal">
                                                <tr:selectBooleanCheckbox value="#{row.ofertaPostal}"/>
                                            </tr:column--%>
                                            <tr:column headerText="Activo">
                                                <tr:selectBooleanCheckbox value="#{row.activo}" disabled="true"/>
                                            </tr:column>
                                            <tr:column headerText="Modificar">
                                                <tr:commandLink text="Modificar" action="#{cliente.modificarDatosCliente}">
                                                    <t:updateActionListener property="#{cliente.clienteData}" value="#{row}"/>
                                                </tr:commandLink>
                                            </tr:column>

                                        </tr:table>
                                        <tr:spacer height="20px"/>
                                        <tr:panelButtonBar>
                                            <tr:commandButton text="XLS" action="cliente_xls"/>
                                        </tr:panelButtonBar>
                                    </tr:group>
                                    <tr:spacer height="20px"/>
                                    <tr:panelCaptionGroup>
                                        <tr:panelBorderLayout>
                                            <f:facet name="right">
                                                <h:panelGrid width="300px" style="height: 100%; text-align: left; font-size:10pt;">
                                                    <tr:outputText value="Podrá realizar un filtrado de los datos de la tabla, modificando los datos del formulario y pulsando el botón de búsqueda. Si deja algún campo vacío, no se aplicará el filtrado por ese campo."/>
                                                    <tr:spacer height="5px"/>
                                                </h:panelGrid>
                                            </f:facet>
                                            <tr:panelFormLayout>
                                                <tr:selectOneRadio id="variants" label="Estado:" layout="horizontal" value="#{cliente.clienteData.opcionActivo}">
                                                    <f:selectItem itemLabel="Activos" itemValue="ACTIVO"/>
                                                    <f:selectItem itemLabel="No activos" itemValue="NO_ACTIVO"/>
                                                    <f:selectItem itemLabel="Todos" itemValue="TODOS"/>
                                                </tr:selectOneRadio>
                                                <tr:inputText label="Nombre:" value="#{cliente.clienteData.nombre}"/>
                                                <tr:inputText label="Apellidos: " value="#{cliente.clienteData.apellidos}"/>
                                                <tr:inputText label="NIF:" value="#{cliente.clienteData.nif}"/>
                                                <tr:inputText label="Email:" value="#{cliente.clienteData.email}"/>
                                                <tr:inputText label="Móvil:" value="#{cliente.clienteData.movil}"/>
                                                <tr:inputText label="Telefono:" value="#{cliente.clienteData.telefono}"/>
                                                <tr:spacer height="10"/>
                                                <tr:panelButtonBar>
                                                    <tr:commandButton text="Buscar" action="#{cliente.buscarClientes}"/>
                                                    <tr:commandButton text="Limpiar" action="#{cliente.limpiarFormBusqueda}"/>
                                                </tr:panelButtonBar>

                                            </tr:panelFormLayout>
                                        </tr:panelBorderLayout>
                                    </tr:panelCaptionGroup>
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


