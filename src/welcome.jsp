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
                            <tr:panelGroupLayout inlineStyle="min-height: 40px; position: relative;">
                                <tr:spacer height="10px"/>
                                <tr:messages/>
                                <tr:spacer height="10px"/>

                                <tr:panelHorizontalLayout>
                                    <tr:spacer width="15px"></tr:spacer>
                                    <tr:outputText value="#{userLogin.userLoginData.textoWelcome}"/>

                                </tr:panelHorizontalLayout>

                                <tr:spacer height="20px"/>
                                <tr:panelHorizontalLayout>
                                    <tr:spacer width="15px"></tr:spacer>
                                    <tr:outputText value="#{userLogin.userLoginData.textoWelcome2}"/>
                                </tr:panelHorizontalLayout>
                                <tr:panelHorizontalLayout>
                                    <tr:spacer width="15px"></tr:spacer>
                                    <tr:outputText value="#{userLogin.userLoginData.textoWelcome3}"/>


                                </tr:panelHorizontalLayout>
                                <tr:panelHorizontalLayout>
                                    <tr:spacer width="15px"></tr:spacer>
                                    <tr:outputText value="#{userLogin.userLoginData.textoWelcome4}"/>


                                </tr:panelHorizontalLayout>
                                <tr:panelHorizontalLayout>
                                    <tr:spacer width="15px"></tr:spacer>
                                    <tr:outputText value="#{userLogin.userLoginData.textoWelcome5}"/>


                                </tr:panelHorizontalLayout>
                                <tr:panelHorizontalLayout>
                                    <tr:spacer width="15px"></tr:spacer>
                                    <tr:outputText value="#{userLogin.userLoginData.textoWelcome6}"/>


                                </tr:panelHorizontalLayout>
                                <tr:spacer height="20px"/>
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


