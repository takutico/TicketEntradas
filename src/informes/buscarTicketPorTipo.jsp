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
                            <tr:panelHorizontalLayout inlineStyle="width: 100%;">
                                <tr:spacer width="15px"/>
                            <tr:panelGroupLayout>
                                <tr:panelHeader text="Informe"/>
                                <tr:spacer height="10px"/>
                                <tr:messages/>
                                <tr:spacer height="10px"/>
                                <tr:group rendered="#{ticket.renderTicketAL}">
                                    <tr:table width="100%" rows="10" emptyText="No hay datos." value="#{ticket.ticketAL}" var="row" rowBandingInterval="1">
                                        <tr:column headerText="Dia">
                                            <tr:outputText value="#{row.dia}"/>
                                        </tr:column>
                                        <tr:column headerText="Hora">
                                            <tr:outputText value="#{row.hora}"/>
                                        </tr:column>
                                        <tr:column headerText="Tipo ticket">
                                            <tr:outputText value="#{row.nombreTipoTicket}"/>
                                        </tr:column>
                                        <tr:column headerText="Nº entradas">
                                            <tr:outputText value="#{row.numeroEntradas}"/>
                                        </tr:column>
                                    </tr:table>
                                    <tr:spacer height="20px"/>
                                    <tr:panelButtonBar>
                                        <tr:commandButton text="XLS" action="ticket_xls"/>
                                        <tr:commandButton text="PDF" action="pdfTicketGroupByTyte"/>
                                        <%--tr:commandButton text="PDF Agrupado por tipo" action="pdfTicketGroupByTyte"/--%>
                                    </tr:panelButtonBar>
                                </tr:group>
                                <tr:panelBorderLayout>
                                    <f:facet name="right">
                                        <h:panelGrid width="300px" style="height: 100%; text-align: left; font-size:10pt;">
                                            <tr:outputText value="En esta parte el administrador podrá generar informes en formato \"xls\" y \"pdf\" de los tickets de entradas emitidos."/>
                                            <tr:spacer height="5px"/>
                                            <tr:outputText value="Para generar el informe se realiza una búsqueda de los tickets, insertando la fecha de inicio, fecha de fin y el tipo de ticket."/>
                                            <tr:spacer height="5px"/>
                                            <tr:outputText value="Si se dejan los parámetros de fechas vacíos, se realizará una búsqueda en el día actual. Si se deja el parámetro de tipo de ticket vacío se realizará una búsqueda entre todos los tipos de ticket."/>
                                        </h:panelGrid>
                                    </f:facet>
                                    <tr:panelFormLayout>
                                        <tr:spacer height="20px"/>
                                        <tr:inputDate label="Fecha de inicio (dd/mm/aa):" value="#{ticket.ticketData.fechaInicio}">
                                            <f:convertDateTime dateStyle="short"/>
                                        </tr:inputDate>
                                        <tr:inputDate label="Fecha de fin (dd/mm/aa):" value="#{ticket.ticketData.fechaFin}">
                                            <f:convertDateTime dateStyle="short"/>
                                        </tr:inputDate>
                                        <tr:selectOneChoice label="Tipo de ticket:" value="#{ticket.ticketData.idType}">
                                            <f:selectItem itemLabel="Todos" itemValue=""/>
                                            <f:selectItems value="#{ticketType.ticketTypeMap}"/>
                                        </tr:selectOneChoice>
                                        <tr:spacer height="10px"/>
                                        <tr:commandButton text="Buscar" action="#{ticket.buscarTicketPorTipo}"/>
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


