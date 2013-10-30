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
                                    <tr:commandNavigationItem text="Crear" action="#{menu.goDatosTicketType}" immediate="true"/>
                                    <tr:commandNavigationItem text="Modificar/Dar de baja" action="#{menu.goBusquedaTicketType}" immediate="true"/>
                                </tr:navigationPane>
                            </tr:panelHorizontalLayout>
                            <tr:panelHorizontalLayout inlineStyle="width: 100%;">
                                <tr:spacer width="15px"/>
                                <tr:panelGroupLayout>
                                    <tr:spacer height="20px"/>
                                    <tr:panelHeader text="Datos del tipo de ticket"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>
                                    <tr:panelBorderLayout>
                                        <f:facet name="right">
                                            <h:panelGrid width="300px" style="height: 100%; color: #4F4C4D; text-align: left; font-size:10pt;">
                                                <tr:outputText value="En esta parte, el administrador puede crear nuevos tipos de tickets, para su posterior uso la opción \"Emitir tickets\" del menú \"Gestión\"."/>
                                                <tr:spacer height="5px"/>
                                                <tr:outputText value="En la parte inferior se pueden consultar los tipos de tickets generados hasta el momento."/>
                                                <tr:spacer height="5px"/>
                                                <tr:outputText value="Una vez creado el tipo de tickets la aplicación no permite cambiar el precio, ya que esto ocasionaría un descuadre en la caja. Tampoco permite crear dos tipos de tickets con el mismo nombre."/>
                                            </h:panelGrid>
                                        </f:facet>
                                        <tr:panelFormLayout>
                                            <tr:inputText label="Nombre:" value="#{ticketType.ticketTypeData.name}"/>
                                            <tr:inputText label="Precio:" value="#{ticketType.ticketTypeData.price}" binding="#{ticketType.ticketTypeBinding.precio}"/>
                                            <%--tr:selectOneChoice label="Categoría:" value="#{ticketType.ticketTypeData.category}">
                                                <f:selectItem itemLabel="<-- Seleccione la categoría -->"/>
                                                <f:selectItems value="#{category.categoriMap}"/>
                                            </tr:selectOneChoice--%>
                                            <tr:selectBooleanCheckbox label="Activo" value="#{ticketType.ticketTypeData.activo}"/>
                                            <h:panelGrid width="100%" style="text-align: center;">
                                                <tr:panelButtonBar>
                                                    <tr:commandButton id="btnGuardar" partialSubmit="true" text="Guardar" action="#{ticketType.guardarTicketType}"/>
                                                </tr:panelButtonBar>
                                            </h:panelGrid>
                                        </tr:panelFormLayout>
                                    </tr:panelBorderLayout>
                                    <tr:showDetail id="showDetail1" undisclosedText="Mostrar los tipo de tickets actuales" disclosedText="Ocultar">
                                        <tr:table width="100%" value="#{ticketType.ticketTypeAL}" var="row" rowBandingInterval="1" partialTriggers="btnGuardar" rows="10">
                                            <tr:column headerText="Nombre">
                                                <tr:outputText value="#{row.name}"/>
                                            </tr:column>
                                            <tr:column headerText="Precio">
                                                <tr:outputText value="#{row.price}"/>
                                            </tr:column>
                                            <tr:column headerText="Activo">
                                                <tr:selectBooleanCheckbox value="#{row.activo}" disabled="true"/>
                                            </tr:column>
                                            <%--tr:column headerText="Modificar">
                                                <f:facet name="header">
                                                    <tr:outputText value="Modificar"/>
                                                </f:facet>
                                            </tr:column--%>
                                        </tr:table>
                                        <%--h:panelGrid width="100%" style="text-align: center;">
                                            <tr:panelButtonBar>
                                                <tr:commandButton text="Buscar" action="#{ticketType.buscarTicketType}"/>
                                            </tr:panelButtonBar>
                                        </h:panelGrid--%>
                                    </tr:showDetail>
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


