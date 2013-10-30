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
                <%--tr:form id="container" usesUpload="true"--%>
                <h:form id="form" enctype="multipart/form-data">
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
                                    <tr:panelHeader text="Envío de emails"/>
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>
                                    <tr:panelBorderLayout>
                                        <f:facet name="right">
                                            <h:panelGrid width="300px" style="height: 100%; color: #4F4C4D; text-align: left; font-size:10pt;">
                                                <tr:outputText value="En esta parte, se puede enviar información de interés mediante correos electrónicos a los clientes."/>
                                                <tr:spacer height="5px"/>
                                                <tr:outputText value="Se puede enviar correos electrónicos con asunto, contenido y archivos adjuntos."/>
                                            </h:panelGrid>
                                        </f:facet>
                                        <tr:panelFormLayout>

                                            <tr:outputText value="Si hay más de un destinatario separar con ;"/>
                                            <tr:inputListOfValues action="dialog:seleccionarDestinatarios"
                                                                  label="Para:"
                                                                  value="#{email.emailData.to}"
                                                                  shortDesc="Si hay más de un destinatario separar con ;"/>
    
                                            <tr:inputText label="Asunto:" columns="50" value="#{email.emailData.subject}"/>
                                            <tr:inputText label="Contenido:" columns="50" rows="10" value="#{email.emailData.content}"/>
                                            <tr:spacer height="5"/>
                                            <%--tr:inputFile id="fichero" label="Fichero adjunto:" columns="50" value="#{email.emailData.adjunto}"/--%>
                                            <t:inputFileUpload id="fileupload" value="#{email.emailData.adjunto}"/>
                                              <tr:spacer height="15px"/>
                                            <h:panelGrid width="100%" style="text-align: center;">
                                                <tr:panelButtonBar>
                                                    <tr:commandButton text="Enviar" action="#{email.sendEmailCliente}"/>
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
                </h:form>
                <%--/tr:form--%>
            </trh:body>
        </tr:document>
    </trh:html>
</f:view>


