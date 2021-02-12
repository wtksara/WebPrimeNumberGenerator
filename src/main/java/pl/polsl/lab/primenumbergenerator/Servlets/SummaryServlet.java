package pl.polsl.lab.primenumbergenerator.Servlets;
import pl.polsl.lab.primenumbergenerator.Model.ConsoleModel;
import pl.polsl.lab.primenumbergenerator.Model.DatabaseHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Summary servlet.
 */
@WebServlet("/sum")
public class SummaryServlet extends HttpServlet {


    /**
     * Process request.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession httpSession = request.getSession();
        PrintWriter output = response.getWriter();

        try {
            // Getting the model from session
            ConsoleModel model = (ConsoleModel) httpSession.getAttribute("model");

            Integer sum =  model.getNumberSum();
            Integer average =  (model.getNumberSum()/model.getQuantity());
            Double median =  model.getMedian();

            output.println("<h1>Summary</h1><br>");
            output.println("<h3>Sum : " + Integer.toString(sum) + "</h3>");
            output.println("<h3>Average : " + Integer.toString(average) + "</h3>");
            output.println("<h3>Median : " + Double.toString(median) + "</h3>");

            // Getting the time of generation of prime numbers array
            Cookie [] cookies = request.getCookies();
            if (cookies!=null){

                int i=0;
                for(Cookie cookie:cookies){
                    if(i==0) {
                        output.println("<h3> Time of generation : " + cookie.getValue() + "</h3>");
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                    }
                    if (i==1){
                        output.println("<h3> IP : " + cookie.getValue() + "</h3>");
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                    }
                    if (i==2){
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                    }
                    i++;
                }
            }
            output.println("<br><br><h2>History of generations : </h2><br>");
            DatabaseHandler databaseHandler = new DatabaseHandler(); // Handler to base data
            output.println(databaseHandler.showHistory()); // Access to write out the history
        }
        catch(Exception exception){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Something went wrong. Reload the page");
        }

    }
        /**
         * Pass to function processRequest.
         *
         * @param request  the request
         * @param response the response
         * @throws IOException      the io exception
         * @throws ServletException the servlet exception
         */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request,response);
    }

    /**
     * Pass to function processRequest.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request,response);
    }

}
