package managers.beans;


import managers.DebugTool;
import managers.ErrorController;
import managers.dataModels.Dot;
import managers.databaseManager.DatabaseCreator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named("controller")
@RequestScoped
public class FormControllerBean implements Serializable {

    @Inject
    private MethodControllerBean controller;

    private final DebugTool logger = new DebugTool();

    private String selectedX;
    private String selectedY;
    private String selectedR;

    public String getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(String selectedX) {
        this.selectedX = selectedX;
    }

    public String getSelectedY() {
        return selectedY;
    }

    public void setSelectedY(String selectedY) {
        this.selectedY = selectedY;
    }

    public String getSelectedR() {
        return selectedR;
    }

    public void setSelectedR(String selectedR) {
        this.selectedR = selectedR;
    }

    @PostConstruct
    public void init() {
        try {
            DatabaseCreator.createTables();
            logger.info("Tables created");
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    public void resetFormState() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent root = facesContext.getViewRoot().findComponent("frm");

        if (root != null) {
            resetComponentState(root);
        }
    }

    private void resetComponentState(UIComponent component) {
        if (component instanceof EditableValueHolder) {
            EditableValueHolder input = (EditableValueHolder) component;
            input.resetValue();
            input.setValid(true);

        }
        for (UIComponent child : component.getChildren()) {
            resetComponentState(child);
        }
    }


    public void submit() {
        resetFormState();
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            // Проверяем значения
            double xValue = Double.parseDouble(selectedX);
            double yValue = Double.parseDouble(selectedY);
            double rValue = Double.parseDouble(selectedR);

            if (xValue < -4 || xValue > 4) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "X must be between -4 and 4.", null));
                return;
            }

            if (yValue < -5 || yValue > 5) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Y must be between -5 and 5.", null));
                return;
            }

            if (rValue < 2 || rValue > 5) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "R must be between 2 and 5.", null));
                return;
            }

            Dot dot = new Dot(xValue, yValue, rValue);
            controller.handleRequest(dot);
        } catch (NumberFormatException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid input format. Please enter numbers.", null));
        }
    }


}
