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

                        <f:facet name="navigation3">
                            <tr:navigationPane hint="list" styleClass="menuLeft">
                            </tr:navigationPane>
                            <%--c:import url="/contents/menu.jsp"/--%>
                        </f:facet>

                        <tr:panelGroupLayout>
                            <tr:spacer height="20px"/>
                            <tr:panelHeader text="Datos personales"/>
                            <tr:spacer height="10px"/>
                            <tr:messages/>
                            <tr:spacer height="10px"/>
                            <tr:commandLink text="Imprimir formulario" action="pdfFormularioCliente" immediate="true"/>
                            <tr:spacer height="5px"/>
                            <tr:panelBorderLayout>
                                <f:facet name="right">
                                    <h:panelGrid width="300px" style="height: 100%; text-align: left; font-size:10pt;">
                                        <tr:outputText value="Para guardar los datos de un nuevo cliente en el sistema, se debe rellenar el formulario. Para introducir más de una dirección de correo electrónico, separar con ;."/>
                                        <tr:spacer height="5"/>
                                        <tr:outputText value="Los datos marcados con * son obligatorios."/>
                                        <tr:spacer height="5"/>
                                        <tr:outputText binding="#{cliente.clienteBinding.txtPrintForm}"
                                                       value="En el caso de que quiera imprimir el formulario pinche en el link que se situa an la parte superior del formulario, tras rellenar el formulario entréguelo a un empleado."/>
                                        <tr:outputText value="Si no desea recibir correos electrónicos con información de interés, contacte con un empleado o envíe un correo electrónico a pfc.gestionentradas@gmail.com."/>
                                    </h:panelGrid>
                                </f:facet>
                                <tr:panelFormLayout>

                                    <tr:inputText label="Nombre:" value="#{cliente.clienteData.nombre}" required="true"/>
                                    <tr:inputText label="Apellidos: " value="#{cliente.clienteData.apellidos}" required="true"/>
                                    <tr:inputText label="NIF:" value="#{cliente.clienteData.nif}"/>
                                    <tr:inputText label="Dirección:" value="#{cliente.clienteData.direccion}"/>
                                    <tr:inputText label="Localidad:" value="#{cliente.clienteData.localidad}"/>
                                    <tr:inputText label="Provincia:" value="#{cliente.clienteData.provincia}"/>
                                    <tr:inputText label="Pais:" value="#{cliente.clienteData.pais}"/>
                                    <tr:inputText label="CP:" value="#{cliente.clienteData.cp}"/>
                                    <tr:inputText label="Nacionalidad:" value="#{cliente.clienteData.nacionalidad}"/>
                                    <tr:inputText label="Email:" value="#{cliente.clienteData.email}" required="true" shortDesc="Para introducir más de una dirección de correo electrónico, separar con ;"/>
                                    <%--tr:inputText label="Email 2:" value="#{cliente.clienteData.email_2}"/--%>
                                    <tr:inputText label="Móvil:" value="#{cliente.clienteData.movil}"/>
                                    <tr:inputText label="Telefono:" value="#{cliente.clienteData.telefono}"/>
                                    <%--tr:selectBooleanCheckbox label="Envío de ofertas por email:"
                                                              value="#{cliente.clienteData.ofertaEmail}"/--%>
                                    <%--tr:selectBooleanCheckbox label="Envío de ofertas por SMS:"
                                                              value="#{cliente.clienteData.ofertaSms}"/>
                                    <tr:selectBooleanCheckbox label="Envío de ofertas por correo ordinario"
                                                              value="#{cliente.clienteData.ofertaPostal}"/--%>
                                    <%--tr:selectBooleanCheckbox label="Activo"
                                                              value="#{cliente.clienteData.activo}"/--%>
                                    <h:panelGrid width="100%" style="text-align: center;">
                                        <tr:panelButtonBar>
                                            <tr:commandButton text="Guardar" action="#{cliente.insertarClienteExterno}"/>
                                            <%--tr:commandButton text="Limpiar datos" action="#{cliente.limpiarDatosClienteExterno}" immediate="true"/--%>
                                            <tr:commandButton text="volver" action="goLogin" immediate="true"/>
                                        </tr:panelButtonBar>
                                    </h:panelGrid>
                                </tr:panelFormLayout>
                            </tr:panelBorderLayout>
                        </tr:panelGroupLayout>
                        <tr:spacer height="20px"/>
                        <f:facet name="appCopyright">
                            <c:import url="/contents/footer.jsp"/>
                        </f:facet>
                    </tr:panelPage>
                </tr:form>
            </trh:body>
        </tr:document>
    </trh:html>
</f:view>


