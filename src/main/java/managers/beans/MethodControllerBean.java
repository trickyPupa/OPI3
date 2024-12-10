package managers.beans;

import managers.ErrorController;
import managers.dataModels.Dot;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("controller")
@RequestScoped
public class MethodControllerBean {

    @Inject
    private DataSaverBean dataSaverBean;


    public void handleRequest(Dot dot) {
        if (checkMethod()) {
            dataSaverBean.saveData(dot);
        } else {
            ErrorController.send405Error("Method Not Allowed");
        }
    }

    public boolean checkMethod() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getMethod().equals("POST");
    }
}
