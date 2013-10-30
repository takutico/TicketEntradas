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
                <tr:form id="container" defaultCommand="btnLogin">
                    <tr:panelPage>
                        <f:facet name="branding">
                            <tr:panelPageHeader chromeType="expanded">
                                <f:facet name="branding">
                                    <tr:panelGroupLayout layout="horizontal">
                                        <c:import url="/contents/cabecera_login.jsp"/>
                                    </tr:panelGroupLayout>
                                </f:facet>
                            </tr:panelPageHeader>
                        </f:facet>
     
                        <tr:panelGroupLayout>
                            <tr:spacer height="10px"/>
                            <tr:messages/>
                            <tr:spacer height="10px"/>

                            <tr:panelHorizontalLayout>
                                <tr:spacer width="15px"></tr:spacer>
                                <tr:outputText value="  En este proyecto fin de carrera se ha desarrollado una aplicación Web para la gestión de entradas a espectáculos, locales, museos, etc. Esta idea surge a raíz de una aplicación que fue desarrollada por el autor del proyecto fin de carrera.">
                                </tr:outputText>
                            </tr:panelHorizontalLayout>

                            <tr:spacer height="20px"/>
                            <tr:panelHorizontalLayout>
                                <tr:spacer width="15px"></tr:spacer>
                                <tr:outputText value="  La aplicación que usa xxx es una aplicación de escritorio, es decir, se ejecuta en la máquina local. Los problemas que tienen este tipo de aplicaciones son; la instalación y el mantenimiento que requieren, ya que hace falta entrar en la máquina para realizar cualquier gestión. Por tanto, se ha desarrollado una aplicación Web para que cualquier usuario autorizado pueda acceder a ella a través de un navegador, con cualquier dispositivo y así garantizamos que la aplicación necesite de pocos recursos para ser ejecutada, así como la facilidad de actualización, mantenimiento y distribución en las máquinas servidoras.">
                                </tr:outputText>

                            </tr:panelHorizontalLayout>
                            <tr:spacer height="30"/>
                            <tr:panelCaptionGroup>
                                <tr:panelFormLayout>
                                    <tr:inputText label="Login: " required="true" value="#{userLogin.userLoginData.login}"/>
                                    <tr:inputText label="Password: " required="true" secret="true" value="#{userLogin.userLoginData.pass}"/>
                                    <h:panelGrid width="100%" style="text-align: center;">
                                        <tr:panelButtonBar>
                                            <tr:commandButton id="btnLogin" text="Entrar" action="#{userLogin.loginUser}"/>
                                        </tr:panelButtonBar>
                                    </h:panelGrid>
                                </tr:panelFormLayout>
                            </tr:panelCaptionGroup>
                            <tr:commandLink action="datosClienteExterno" text="Pulse en este enlace si quiere recibir noticias de interés" immediate="true"/>
                            <tr:spacer height="20px"/>
                        </tr:panelGroupLayout>
                        <f:facet name="appCopyright">
                            <c:import url="/contents/footer_login.jsp"/>
                        </f:facet>
                    </tr:panelPage>
                </tr:form>
            </trh:body>
        </tr:document>
    </trh:html>
</f:view>


