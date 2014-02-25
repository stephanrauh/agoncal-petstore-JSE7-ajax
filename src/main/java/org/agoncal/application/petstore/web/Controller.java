package org.agoncal.application.petstore.web;

import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.agoncal.application.petstore.util.Loggable;

/**
 * @author Antonio Goncalves http://www.antoniogoncalves.org --
 */

@Loggable
abstract class Controller {

    // ======================================
    // = Attributes =
    // ======================================

    @Inject
    private transient Logger logger;

    // ======================================
    // = Protected Methods =
    // ======================================

    protected void addErrorMessage(String message, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage(context, message, args), null));
    }

    protected void addInformationMessage(String message, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage(context, message, args), null));
    }

    protected void addWarningMessage(String message, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage(context, message, args), null));
    }

    private String getMessage(FacesContext facesContext, String msgKey, Object... args) {
        Locale locale = facesContext.getViewRoot().getLocale();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale, classLoader);
        String msgValue = bundle.getString(msgKey);
        return MessageFormat.format(msgValue, args);
    }

    protected String getParam(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        return map.get(param);
    }

    protected Long getParamId(String param) {
        return Long.valueOf(getParam(param));
    }
}