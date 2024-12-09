package managers.beans;

import managers.dataModels.Dot;
import managers.dataModels.Result;
import managers.ResponseSender;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("controller")
@RequestScoped
public class MethodControllerBean {
    @Inject
    private AreaCheckerBean checkerBean;

    @Inject
    private ErrorControllerBean error;


    public void handleRequest(Dot dot) {
        if (checkMethod()) {
            checkerBean.checkArea(dot);
            Result result = checkerBean.getResult();
            ResponseSender.sendResponse(result);
        } else {
            error.send405Error("Method Not Allowed");
        }
    }

    public boolean checkMethod() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getMethod().equals("POST");
    }
}
