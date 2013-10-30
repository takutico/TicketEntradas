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

<tr:panelAccordion discloseMany="true" styleClass="menuLeft">
    <tr:showDetailItem text="Administración" styleClass="menuLeft" rendered="#{menu.renderPnlAdministracion}"
                       binding="#{menu.menuBinding.pnlAdmin}" immediate="true">
        <tr:navigationPane hint="list" styleClass="menuLeft">
            <tr:commandNavigationItem text="Gestión de ticket" action="#{menu.goIndexTicketType}"
                                      binding="#{menu.menuBinding.itemGestionTicket}" immediate="true"/>
            <tr:commandNavigationItem text="Incidencia ticket" action="#{menu.goBusquedaTicket}"
                                      binding="#{menu.menuBinding.itemIncidenciaTicket}" immediate="true"/>
            <tr:commandNavigationItem text="Informe" action="#{menu.goInformePorGrupo}" immediate="true"
                                      binding="#{menu.menuBinding.itemInforme}"/>
            <tr:commandNavigationItem text="Estadisticas" action="#{menu.goGrafica}" immediate="true"
                                      binding="#{menu.menuBinding.itemGrafica}">
                <t:updateActionListener property="#{ticketChartModel}" value="#{null}"/>
            </tr:commandNavigationItem>
            <tr:commandNavigationItem text="Empleados" action="#{menu.goIndexUsuario}" immediate="true"
                                      binding="#{menu.menuBinding.itemEmpleados}"/>
            <tr:commandNavigationItem text="Email" action="#{menu.goEmail}" immediate="true"
                                      binding="#{menu.menuBinding.itemEmail}"/>
        </tr:navigationPane>
    </tr:showDetailItem>
    <tr:showDetailItem text="Gestión" binding="#{menu.menuBinding.pnlEmpleado}" immediate="true">
        <tr:navigationPane hint="list" styleClass="menuLeft">
            <%--tr:commandNavigationItem text="Emitir ticket" action="#{menu.goIndexTicket}" immediate="true"
                                      binding="#{menu.menuBinding.itemEmitirTicket}"/--%>
            <tr:commandNavigationItem text="Emitir ticket" action="#{menu.goIndexTicket}" immediate="true"
                                      binding="#{menu.menuBinding.itemEmitirTicket}"/>
            <tr:commandNavigationItem text="Clientes" action="#{menu.goIndexCliente}" immediate="true"
                                      binding="#{menu.menuBinding.itemClientes}"/>
        </tr:navigationPane>
    </tr:showDetailItem>
</tr:panelAccordion>

<%--tr:separator inlineStyle="color: #D3D2D2;"/>
<tr:outputText value="--------------------------------" inlineStyle="background-color: #D3D2D2; width: 200px;"/--%>

<tr:group>
    <tr:spacer height="10px"/>
    <tr:panelCaptionGroup captionText="Usuario">
        <tr:navigationPane hint="list" styleClass="menuLeft">
            <tr:commandNavigationItem text="Cambiar pass" action="#{menu.goChangePass}" immediate="true"
                                      binding="#{menu.menuBinding.itemCambiarPass}"/>
            <tr:commandNavigationItem text="Desconectar" action="#{userLogin.logoutUser}" immediate="true"
                                      onclick="if (!confirm('¿Está seguro de desconectar la sesión?')) return false"/>
        </tr:navigationPane>
    </tr:panelCaptionGroup>
</tr:group>
