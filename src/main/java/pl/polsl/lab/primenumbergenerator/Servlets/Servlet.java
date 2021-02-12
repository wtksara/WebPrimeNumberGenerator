package pl.polsl.lab.primenumbergenerator.Servlets;

import pl.polsl.lab.primenumbergenerator.Controller.ConsoleController;
import pl.polsl.lab.primenumbergenerator.Model.ConsoleModel;
import pl.polsl.lab.primenumbergenerator.Model.DatabaseHandler;
import pl.polsl.lab.primenumbergenerator.View.ConsoleView;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * The type Servlet.
 */

@WebServlet("/gen")
public class Servlet extends HttpServlet {



    /**
     * Creation of cookie with data and time of generation
     *
     * @param request  the request
     * @param response the response
     */

    private String createCookie ( HttpServletRequest request, HttpServletResponse response) throws UnknownHostException
    {
        InetAddress IP = null;
        try {
            IP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        response.addCookie(new Cookie ("IP",  IP.toString() ));
        return IP.toString();
    }

    /**
     * Process request.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */

    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


            ConsoleModel model = new ConsoleModel();
            ConsoleView view = new ConsoleView(model);
            ConsoleController controller = new ConsoleController(view,model);

            boolean state;
            try {

                state = view.getParametersFromUser(request.getParameter("valueOfPrimeNumber"), request.getParameter("valueOfQuantity")) ; // Get params and check them
            }
            // Catching the exceptions
            catch(NumberFormatException exception){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect format of inputs. Only numbers");
                state=false;
            }
            catch(InputMismatchException exception){
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Incorrect range of the inputs. Quantity has to be above 0 and below or equal to 300. Prime number has to be natural number  ) ");
                state=false;
            }

             if (state) {
            // Generation of whole model
            controller.generatePrimeNumbers();
            controller.generateSpacingValues();
            controller.calculateMedianValue();

            // Creating cookies
            Date date = new Date();
            SimpleDateFormat  formatter = new SimpleDateFormat("dd-M-yyyy_hh:mm:ss");
            String generationTime = formatter.format(date);
            response.addCookie(new Cookie("timeOfGeneration", generationTime));
            String IP = createCookie(request,response);

            // Conversion to String [] arrayOfPrimeNumber and arrayOfSpacingValues
            List<String> stringListOfPrimeNumbers = new ArrayList<>(model.getArrayOfPrimeNumbers().size());
            for (Integer myInt : model.getArrayOfPrimeNumbers()) {
                stringListOfPrimeNumbers.add(String.valueOf(myInt));
            }
            String[] stringArrayOfPrimeNumbers = stringListOfPrimeNumbers.toArray(new String[0]);

            List<String> stringListOfSpacingValues = new ArrayList<>(model.getArrayOfSpacingValues().size());
            for (Integer myInt : model.getArrayOfSpacingValues()) {
                     stringListOfSpacingValues.add(String.valueOf(myInt));
            }
            String[] stringArrayOfSpacingValues = stringListOfSpacingValues.toArray(new String[0]);

                 // Database handling
                 DatabaseHandler databaseHandler = new DatabaseHandler();
                 Integer lastID = databaseHandler.getLastId(IP,generationTime); // Get the last ID of generation information
                 databaseHandler.insertGenerationResult(lastID,model); // Generate result of generation

                 // Saving actual session
                 HttpSession httpSession =request.getSession();
                 httpSession.setAttribute("model", model);

                 // Setting all attributes for usage
                 request.setAttribute("quantity", model.getQuantity());
                 request.setAttribute("theHighestSpacingValue",Integer.toString(model.getTheHighestSpacingValue()));

                 // Setting both arrays
                 request.setAttribute("arrayOfPrimeNumbers",stringArrayOfPrimeNumbers);
                 request.setAttribute("arrayOfSpacingValues",stringArrayOfSpacingValues);

                 // Dispatcher to generation.jsp
                 RequestDispatcher dispatcher = request.getRequestDispatcher("/generation.jsp");
                 dispatcher.forward(request,response);
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