package managers.beans;


import managers.DebugTool;
import managers.dataModels.Dot;
import managers.databaseManager.DatabaseCreator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Logger;

@Named("formControllerBean")
@RequestScoped
public class FormControllerBean implements Serializable {

    @Inject
    private  MethodControllerBean controller;
    @Inject
    private  ErrorControllerBean error;

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

    public void submit() {
        processFormData();
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

    private void processFormData() {
        try {
            double xValue = Double.parseDouble(selectedX);
            double yValue = Double.parseDouble(selectedY);
            double rValue = Double.parseDouble(selectedR);
            Dot dot = new Dot(xValue, yValue, rValue);
            controller.handleRequest(dot);
        }catch (NumberFormatException e) {
            error.send400Error("Wrong parameters");
        }
    }
}
