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
                                    <tr:commandNavigationItem text="Crear" action="#{menu.goDatosUsuario}" immediate="true"/>
                                    <tr:commandNavigationItem text="Modificar/Dar de baja" action="#{menu.goBusquedaUsuario}" immediate="true"/>
                                </tr:navigationPane>
                            </tr:panelHorizontalLayout>
                            <tr:panelHorizontalLayout inlineStyle="width: 100%;">
                                <tr:spacer width="15px"/>
                                <tr:panelGroupLayout>
                                    <tr:spacer height="20px"/>
                                    <tr:panelHeader text="Búsqueda de empleado"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>
                                    <tr:panelGroupLayout rendered="#{user.renderUserAL}">
                                        <tr:table width="100%" value="#{user.userAL}" var="row" rowBandingInterval="1" rows="10">
                                            <%--tr:column>
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
                                            </tr:column--%>
                                            <%--tr:column headerText="Localidad">
                                                <tr:outputText value="#{row.localidad}"/>
                                            </tr:column--%>
                                            <%--tr:column headerText="Provincia">
                                                <tr:outputText value="#{row.provincia}"/>
                                            </tr:column--%>
                                            <%--tr:column headerText="CP">
                                                <tr:outputText value="#{row.cp}"/>
                                            </tr:column--%>
                                            <tr:column headerText="Email">
                                                <tr:outputText value="#{row.email}"/>
                                            </tr:column>
                                            <tr:column headerText="Teléfono">
                                                <tr:outputText value="#{row.telefono}"/>
                                            </tr:column>
                                            <tr:column headerText="Móvil">
                                                <tr:outputText value="#{row.movil}"/>
                                            </tr:column>
                                            <tr:column headerText="Activo">
                                                <tr:selectBooleanCheckbox value="#{row.activo}" disabled="true"/>
                                            </tr:column>
                                            <tr:column headerText="Admin.">
                                                <tr:selectBooleanCheckbox value="#{row.admin}" disabled="true"/>
                                            </tr:column>
                                            <tr:column headerText="Modificar">
                                                <tr:commandLink text="Modificar" action="#{user.modificarUser}">
                                                    <t:updateActionListener property="#{user.userData}" value="#{row}"/>
                                                </tr:commandLink>
                                            </tr:column>
                                        </tr:table>
                                        <tr:spacer height="20px"/>
                                        <tr:panelButtonBar>
                                            <tr:commandButton text="XLS" action="user_xls"/>
                                            <%--tr:commandButton text="PDF" action="pdfUser"/--%>
                                        </tr:panelButtonBar>
                                    </tr:panelGroupLayout>
                                    <tr:spacer height="20px"/>
                                    <%--tr:showDetail undisclosedText="Filtrar la búsqueda" disclosedText="Ocultar" --%>
                                    <tr:panelCaptionGroup>
                                        <tr:panelBorderLayout>
                                            <f:facet name="right">
                                                <h:panelGrid width="300px" style="height: 100%; text-align: left; font-size:10pt;">
                                                    <tr:outputText value="Podrá hacer un filtrado de los datos de la tabla, modificando los datos del formulario y pulsando el botón de búsqueda. Si deja los campos vacíos no se aplicará el filtrado por ese campo."/>
                                                    <tr:spacer height="5px"/>
                                                </h:panelGrid>
                                            </f:facet>
                                            <tr:panelFormLayout>
                                                <tr:inputText label="Nombre:" value="#{user.userData.nombre}"/>
                                                <tr:inputText label="Apellidos: " value="#{user.userData.apellidos}"/>
                                                <tr:inputText label="NIF:" value="#{user.userData.nif}"/>
                                                <tr:inputText label="Email:" value="#{user.userData.email}"/>
                                                <tr:inputText label="Móvil:" value="#{user.userData.movil}"/>
                                                <tr:inputText label="Telefono:" value="#{user.userData.telefono}"/>
                                                <tr:spacer height="10"/>
                                                <tr:panelButtonBar>
                                                    <tr:commandButton text="Buscar" action="#{user.buscarUser}"/>
                                                    <tr:commandButton text="Limpiar" action="#{user.limpiarDatos}"/>
                                                </tr:panelButtonBar>
                                            </tr:panelFormLayout>
                                        </tr:panelBorderLayout>
                                    </tr:panelCaptionGroup>
                                    <%--/tr:showDetail--%>
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


