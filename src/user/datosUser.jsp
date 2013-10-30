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
                                    <tr:panelHeader text="Datos del empleado"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>
                                    <tr:panelBorderLayout>
                                        <f:facet name="right">
                                            <h:panelGrid width="300px" style="height: 100%; color: #4F4C4D; text-align: left; font-size:10pt;">
                                                <tr:outputText value="En esta pantalla el administrador podrá guardar los datos empleados y administradoes que interactuarán con el sistema."/>
                                                <tr:spacer height="5px"/>
                                                <tr:outputText value="Los campos marcados con * son obligatorios."/>
                                                <tr:spacer height="5px"/>
                                                <tr:outputText value="El sistema no permite insetar empleados o administradores con un nif que ya se encuentra dado de alta. Tampoco permite que hayan mas de un usuario con el mismo login."/>
                                            </h:panelGrid>
                                        </f:facet>
                                        <tr:panelFormLayout>

                                            <tr:inputText label="Nombre:" value="#{user.userData.nombre}" required="true"/>
                                            <tr:inputText label="Apellidos: " value="#{user.userData.apellidos}" required="true"/>
                                            <tr:inputText label="NIF:" value="#{user.userData.nif}" required="true">
                                                <f:validator validatorId="NIFValidator"/>
                                            </tr:inputText>
                                            <tr:inputText label="Login:" value="#{user.userData.login}" required="true"/>
                                            <tr:inputText label="Dirección:" value="#{user.userData.direccion}"/>
                                            <tr:inputText label="Localidad:" value="#{user.userData.localidad}"/>
                                            <tr:inputText label="Provincia:" value="#{user.userData.provincia}"/>
                                            <tr:inputText label="Pais:" value="#{user.userData.pais}"/>
                                            <tr:inputText label="CP:" value="#{user.userData.cp}"/>
                                            <tr:inputText label="Email:" value="#{user.userData.email}" required="true">
                                                <f:validator validatorId="EmailValidator"/>
                                            </tr:inputText>
                                            <tr:inputText label="Móvil:" value="#{user.userData.movil}"/>
                                            <tr:inputText label="Telefono:" value="#{user.userData.telefono}"/>
                                            <tr:selectBooleanCheckbox label="Administrador:"
                                                                      value="#{user.userData.admin}"/>
                                            <tr:selectBooleanCheckbox label="Activo:"
                                                                      value="#{user.userData.activo}"/>
                                            <h:panelGrid width="100%" style="text-align: center;">
                                                <tr:panelButtonBar>
                                                    <tr:commandButton text="Guardar" action="#{user.guardarUser}"/>
                                                    <tr:commandButton text="Solicitar nueva contraseña"
                                                                      action="#{user.nuevoPass}"
                                                                      binding="#{user.userBinding.btnNuevoPass}"/>
                                                </tr:panelButtonBar>
                                            </h:panelGrid>
                                        </tr:panelFormLayout>
                                    </tr:panelBorderLayout>
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


