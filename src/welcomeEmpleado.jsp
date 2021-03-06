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

                        <tr:panelBorderLayout>
                            <f:facet name="left">
                                <tr:panelGroupLayout>
                                    <c:import url="/contents/menu.jsp"/>
                                </tr:panelGroupLayout>
                            </f:facet>
                            <tr:panelGroupLayout layout="horizontal">
                                <tr:spacer width="15px"/>
                                <tr:panelGroupLayout inlineStyle="min-height: 40px; position: relative;">
                                    <tr:spacer height="10px"/>
                                    <tr:messages/>
                                    <tr:spacer height="10px"/>

                                    <tr:panelGroupLayout layout="default">
                                        <tr:outputText value="Usted, desde el menú \"Gestión\" puede emitir tickets y gestionar los clientes. Por último se muestran las opciones para cambiar la contraseña y desconectarse del sistema. A continuación se detalla cada una de las opciones del menú."/>
                                        <tr:spacer height="20px"/>
                                        <tr:panelHeader text="Menú \"Gestión\""/>
                                        <tr:spacer height="10px"/>
                                        <tr:panelGroupLayout layout="horizontal">
                                            <tr:outputText styleClass="fuenteIndentadoNegrita" value="- Emitir ticket: "/>
                                            <tr:spacer width="2px"/>
                                            <tr:outputText styleClass="fuenteIndentado" value="Usted podrá generar y emitir los ticket's de entradas."/>
                                        </tr:panelGroupLayout>
                                        <tr:spacer height="5px"/>
                                        <tr:panelGroupLayout layout="horizontal">
                                            <tr:outputText styleClass="fuenteIndentadoNegrita" value="- Clientes: "/>
                                            <tr:spacer width="2px"/>
                                            <tr:outputText styleClass="fuenteIndentado" value="Usted podrá dar de alta o modificar los datos de los clientes."/>
                                        </tr:panelGroupLayout>
                                        <tr:spacer height="20px"/>
                                        <tr:panelHeader text="Menú \"Usuario\""/>
                                        <tr:spacer height="10px"/>
                                        <tr:panelGroupLayout layout="horizontal">
                                            <tr:outputText styleClass="fuenteIndentadoNegrita" value="- Cambiar pass: "/>
                                            <tr:spacer width="2px"/>
                                            <tr:outputText styleClass="fuenteIndentado" value="Usted podrá cambiar la contraseña de acceso al sistema."/>
                                        </tr:panelGroupLayout>
                                        <tr:spacer height="5px"/>
                                        <tr:panelGroupLayout layout="horizontal">
                                            <tr:outputText styleClass="fuenteIndentadoNegrita" value="- Desconectar: "/>
                                            <tr:spacer width="2px"/>
                                            <tr:outputText styleClass="fuenteIndentado" value="Usted podrá desconectarse del sistema."/>
                                        </tr:panelGroupLayout>
                                        <tr:spacer height="10px"/>
                                    </tr:panelGroupLayout>

                                    <tr:spacer height="20px"/>
                                </tr:panelGroupLayout>
                            </tr:panelGroupLayout>
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


