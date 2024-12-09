package managers;

import com.google.gson.Gson;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseSender {

    public static void sendResponse(Object result) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonResponse = new Gson().toJson(result);

            response.getWriter().write(jsonResponse);

            facesContext.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
