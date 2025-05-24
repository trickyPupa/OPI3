package managers.beans;

import managers.DebugTool;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ErrorController {

    static DebugTool debugTool = new DebugTool();


    public static void send400Error(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        facesContext.getExternalContext().setResponseStatus(400);
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Wrong parameters", message);
    }


    public static void send405Error(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().setResponseStatus(405);
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Method Not Allowed", message);

    }

    public static void send500Error(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().setResponseStatus(500);
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Internal Server Error", message);

    }
}