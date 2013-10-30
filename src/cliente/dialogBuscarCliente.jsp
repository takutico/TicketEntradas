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
            <trh:body>
                <tr:messages/>
                <tr:form id="container">
                    <tr:panelPage>
                        <tr:panelGroupLayout>
                            <tr:spacer height="20px"/>
                            <tr:panelHeader text="Búsqueda de clientes"/>
                            <tr:spacer height="10px"/>
                            <tr:messages/>
                            <tr:spacer height="10px"/>

                            <tr:table width="100%" value="#{cliente.clientesAL}" var="row"
                                      rendered="#{cliente.renderClientesAL}"
                                      rowSelection="multiple"
                                      binding="#{cliente.clienteBinding.tblClientes}">
                                <f:facet name="footer">
                                    <tr:panelButtonBar>
                                        <tr:commandButton text="Aceptar" action="#{email.obtnerEmailClientes}"/>
                                        <%--tr:commandButton text="Cancelar" action="#{cliente.limpiarDatos}"/--%>
                                    </tr:panelButtonBar>
                                </f:facet>
                                <tr:column headerText="Apellidos">
                                    <tr:outputText value="#{row.apellidos}"/>
                                </tr:column>
                                <tr:column headerText="Nombre">
                                    <tr:outputText value="#{row.nombre}"/>
                                </tr:column>
                                <tr:column headerText="NIF">
                                    <tr:outputText value="#{row.nif}"/>
                                </tr:column>
                                <tr:column headerText="Dirección">
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
                                </tr:column>
                                <tr:column headerText="Email">
                                    <tr:outputText value="#{row.email}"/>
                                </tr:column>
                                <tr:column headerText="Telefono">
                                    <tr:outputText value="#{row.telefono}"/>
                                </tr:column>
                                <tr:column headerText="Móvil">
                                    <tr:outputText value="#{row.movil}"/>
                                </tr:column>
                                <%--tr:column headerText="Oferta email">
                                    <tr:outputText value="#{row.ofertaEmail}"/>
                                </tr:column--%>
                                <%--tr:column headerText="Activo">
                                    <tr:selectBooleanCheckbox value="#{row.activo}" disabled="true"/>
                                </tr:column--%>
                            </tr:table>
                            <tr:spacer height="20px"/>
                            <tr:panelFormLayout>
                                <tr:group>
                                    <tr:inputText label="Nombre:" value="#{cliente.clienteData.nombre}"/>
                                    <tr:inputText label="Apellidos: " value="#{cliente.clienteData.apellidos}"/>
                                    <tr:inputText label="NIF:" value="#{cliente.clienteData.nif}"/>
                                    <tr:inputText label="Email:" value="#{cliente.clienteData.email}"/>
                                    <tr:inputText label="Móvil:" value="#{cliente.clienteData.movil}"/>
                                    <tr:inputText label="Telefono:" value="#{cliente.clienteData.telefono}"/>
                                </tr:group>
                                <h:panelGrid width="100%" style="text-align: center;">
                                    <tr:panelButtonBar>
                                        <tr:commandButton text="Buscar" action="#{cliente.buscarClientesActivosConEnvioEmail}"/>
                                    </tr:panelButtonBar>
                                </h:panelGrid>
                            </tr:panelFormLayout>
                        </tr:panelGroupLayout>
                    </tr:panelPage>
                </tr:form>
            </trh:body>
        </tr:document>
    </trh:html>
</f:view>


