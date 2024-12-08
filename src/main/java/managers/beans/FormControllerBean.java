package managers.beans;


import managers.dataModels.Dot;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("formControllerBean")
@RequestScoped
public class FormControllerBean {

    @Inject
    private MethodControllerBean controller;

    @Inject
    ErrorControllerBean error;

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
        System.out.println("Submitted values:");
        System.out.println("X: " + selectedX);
        System.out.println("Y: " + selectedY);
        System.out.println("R: " + selectedR);
        processFormData();
    }


    private void processFormData() {
        try {
            controller.handleRequest(new Dot(
                    Double.parseDouble(selectedX),
                    Double.parseDouble(selectedY),
                    Double.parseDouble(selectedR))
            );
        }catch (NumberFormatException e) {
            error.send400Error("Wrong parameters");
        }
    }
}
