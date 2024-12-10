package managers.beans;


import managers.DebugTool;
import managers.ErrorController;
import managers.dataModels.Dot;
import managers.databaseManager.DatabaseCreator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named("formControllerBean")
@RequestScoped
public class FormControllerBean implements Serializable {

    @Inject
    private  MethodControllerBean controller;

    private final DebugTool logger = new DebugTool();

    private String selectedX;
    private String selectedY;
    private String selectedR;

    boolean validated;

    double xValue;
    double yValue;
    double rValue;

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

    public void submit() {
        if (validate(selectedX, selectedY, selectedR)) processFormData();
    }

    private boolean validate(String selectedX, String selectedY, String selectedR) {
        try {
            if (selectedX == null || selectedY == null || selectedR == null) {
                return false;
            }

            this.xValue = Double.parseDouble(selectedX);
            this.yValue = Double.parseDouble(selectedY);
            this.rValue = Double.parseDouble(selectedR);

            return xValue >= -4 && xValue <= 4 && yValue >= -5 && yValue <= 5 && rValue >= 2 && rValue <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    };

    private void processFormData() {
        try {
            Dot dot = new Dot(xValue, yValue, rValue);
            controller.handleRequest(dot);
        }catch (NumberFormatException e) {
            ErrorController.send400Error("Wrong parameters");
        }
    }
}
