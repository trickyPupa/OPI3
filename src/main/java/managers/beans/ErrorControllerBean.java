package managers.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named("errorController")
@RequestScoped
public class ErrorControllerBean {

    public void send400Error(String message) {
        FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(400);

        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Wrong parameters", message);

        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void send405Error(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().setResponseStatus(405);
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Method Not Allowed", message);
        facesContext.addMessage(null, facesMessage);
    }

    public void send500Error(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().setResponseStatus(500);
        FacesMessage facesMessage = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Internal Server Error", message);
        facesContext.addMessage(null, facesMessage);
    }
}
