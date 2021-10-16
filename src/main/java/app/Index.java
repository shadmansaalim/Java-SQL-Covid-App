package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin by writing the raw HTML into a Java
 * String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Home</title> <!-- Custom styles --> <link rel='stylesheet' href='app.css'> <!-- Bootstrap CDN Link --> <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head><body> <nav class='navbar navbar-expand-lg navbar-light bg-light'> <div class='container-fluid'> <a class='navbar-brand' href='#'>Spreading Info</a> <button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'> <span class='navbar-toggler-icon'></span> </button> <div class='collapse navbar-collapse' id='navbarSupportedContent'> <ul class='navbar-nav ms-auto mb-2 mb-lg-0'> <li class='nav-item me-4'> <a class='nav-link active' aria-current='page' href='#'>Home</a> </li> <li class='nav-item me-4'> <a class='nav-link' href='#'>Key Info</a> </li> <li class='nav-item dropdown me-4'> <a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-bs-toggle='dropdown' aria-expanded='false'> Country Data </a> <ul class='dropdown-menu' aria-labelledby='navbarDropdown'> <li><a class='dropdown-item' href='#'>Cases</a></li> <li><a class='dropdown-item' href='#'>Deaths</a></li> </ul> </li> <li class='nav-item me-4'> <a class='nav-link'>Trends</a> </li> <li class='nav-item'> <a class='nav-link'>Compare</a> </li> </ul> </div> </div> </nav> <!--- Bootstrap Bundle Link --> <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js' integrity='sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p' crossorigin='anonymous'></script></body></html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
