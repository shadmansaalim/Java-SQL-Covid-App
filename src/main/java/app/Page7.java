package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples.
 * This page currently:
 *  - Provides a link back to the index page
 *  - Displays the list of movies from the Movies Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page7 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page7.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>About Us</title>";

        // Add some CSS (external file)
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&display=swap' rel='stylesheet'>'";
        html = html + "<link rel='stylesheet' type='text/css' href='covid.css' />";
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>"; 
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&family=Poppins:wght@300&display=swap' rel='stylesheet'  d>";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";

        // Add the body
        html = html + "<body>";

        html = html + "<a href='/'>";
        html = html + "<img class='logo' src='logo.png'/>";
        html = html + "</a>";
        html = html + "<h3 class = 'spread'>Spreading Info</h3>";

        html = html + "<div class='topnav'>";
        html = html + "<a class = 'home' href='/'>Home</a>";
        html = html + "<a class = 'update' href='page1.html'>Covid Updates</a>";
        html = html + "<a class='countries' href='page2.html'>Countries</a>";
        /*html = html + "<div class='dropdown'>";
        html = html + "<button onclick='myFunction()' class='dropbtn'>Countries <span class='arrow'>&#9660;</span></button>";
        html = html + "<div id = 'myDropdown' class='dropdown-content'>";
        html = html + "<a href='page2.html'>Australia</a>";
        html = html + "</div>" + "</div>";
        html = html + "<script>";
        html = html + "  function myFunction() {console.log('myfunction ran');document.getElementsByClassName('dropdown')[0].classList.toggle('show');}";
        html = html + "  window.onclick = function(event) {if (!event.target.matches('.dropbtn')) {var dropdowns = document.getElementsByClassName('dropdown-content');var i;for (i = 0; i < dropdowns.length; i++) {var openDropdown = dropdowns[i];if (openDropdown.classList.contains('show')) {openDropdown.classList.remove('show');}}}};";
        html = html + "</script>";*/

        
        html = html + "<a class = 'deaths' href='page3.html'>Deaths</a>";
        html = html + "<a class = 'data' href='page4.html'>Data</a>";
        html = html + "<a class = 'analysis' href='page5.html'>Analysis</a>";
        html = html + "<a class = 'about' href='page6.html'>About Us</a>";
        html = html + "</div>";


        String countrylist_drop = context.formParam("countrylist_drop");

       

        

    

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }




    public double getDistance(double lat1,
    double lat2, double lon1,
                 double lon2)
{

// The math module contains a function
// named toRadians which converts from
// degrees to radians.
lon1 = Math.toRadians(lon1);
lon2 = Math.toRadians(lon2);
lat1 = Math.toRadians(lat1);
lat2 = Math.toRadians(lat2);

// Haversine formula
double dlon = lon2 - lon1;
double dlat = lat2 - lat1;
double a = Math.pow(Math.sin(dlat / 2), 2)
+ Math.cos(lat1) * Math.cos(lat2)
* Math.pow(Math.sin(dlon / 2),2);

double c = 2 * Math.asin(Math.sqrt(a));

// Radius of earth in kilometers. Use 3956
// for miles
double r = 6371;

// calculate the result
return(c * r);
}
    public String outputCountryNameNearby1500(String name) {
        String html = "";
        int count = 0;
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryList();
        Double Lat1 = jdbc.countCountryLatitude(name);
        Double Lon1 = jdbc.countCountryLongitude(name);




        
        
        html = html + "<table class = 'fourDataTableTwo'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Population</th>";
        html = html + "<th id=''>Cases</th>";
        html = html + "<th id=''>Infection Rate</th>";
        html = html + "</tr>";
        for (String country : countries) {
            if (!country.equals(name)){
                Double Lat2 = jdbc.countCountryLatitude(country);
                Double Lon2 = jdbc.countCountryLongitude(country);
                Double distance = getDistance(Lat1,Lat2,Lon1,Lon2);

                if (distance <= 1500){
                    int NearbyPop = jdbc.countCountryPop(country);
                    int NearbyCases = jdbc.countTotalCases(country);
                    Double NearbyIR = jdbc.countIR(country);
                    html = html + "<tr>";
                    html = html + "<td>" + country + "</td>";
                    html = html + "<td>" + NearbyPop + "</td>";
                    html = html + "<td>" + NearbyCases  + "</td>";
                    html = html + "<td>" + NearbyIR  + "</td>";
                    html = html + "</tr>";
                    count = count + 1;
                    if (count == 5){
                        break;
                    }
                }
                
            }
            
        }
        if (count < 1){
            html = html + "<h2 id = 'report2'><strong><p>Oops!</p><p>There are no nearby countries within 1500KM of  " + name + " </p><p>Select 2500KM or 4000Km to get nearby countries if available.</p><p><b><i>Note : </i></b>Large countries have less nearby countries around them.</p></strong></h2>";
        }
        html = html + "</table>";
        
    
        return html;
    }
 
   
    
    public String outputCountryNameNearby2500(String name) {
        String html = "";
        int count = 0;
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryList();
        Double Lat1 = jdbc.countCountryLatitude(name);
        Double Lon1 = jdbc.countCountryLongitude(name);




        
        html = html + "<table class = 'fourDataTableTwo'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Population</th>";
        html = html + "<th id=''>Cases</th>";
        html = html + "<th id=''>Infection Rate</th>";
        html = html + "</tr>";
        for (String country : countries) {
            if (!country.equals(name)){
                Double Lat2 = jdbc.countCountryLatitude(country);
                Double Lon2 = jdbc.countCountryLongitude(country);
                Double distance = getDistance(Lat1,Lat2,Lon1,Lon2);

                if (distance <= 2500){
                    int NearbyPop = jdbc.countCountryPop(country);
                    int NearbyCases = jdbc.countTotalCases(country);
                    Double NearbyIR = jdbc.countIR(country);
                    html = html + "<tr>";
                    html = html + "<td>" + country + "</td>";
                    html = html + "<td>" + NearbyPop + "</td>";
                    html = html + "<td>" + NearbyCases  + "</td>";
                    html = html + "<td>" + NearbyIR  + "</td>";
                    html = html + "</tr>";
                    count = count + 1;
                    if (count == 5){
                        break;
                    }
                }
                
            }
            
        }
        if (count < 1){
            html = html + "<h2 id = 'report2'><strong><p>Oops!</p><p>There are no nearby countries within 2500KM of  " + name + " </p><p>Select 4000Km to get nearby countries if available.</p><p><b><i>Note : </i></b>Large countries have less nearby countries around them.</p></strong></h2>";

        }
        html = html + "</table>";
        
    
        return html;
    }
 
    
    

    
    public String outputCountryNameNearby4000(String name) {
        String html = "";
        int count = 0;
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryList();
        Double Lat1 = jdbc.countCountryLatitude(name);
        Double Lon1 = jdbc.countCountryLongitude(name);




        
        html = html + "<table class = 'fourDataTableTwo'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Population</th>";
        html = html + "<th id=''>Cases</th>";
        html = html + "<th id=''>Infection Rate</th>";
        html = html + "</tr>";
        for (String country : countries) {
            if (!country.equals(name)){
                Double Lat2 = jdbc.countCountryLatitude(country);
                Double Lon2 = jdbc.countCountryLongitude(country);
                Double distance = getDistance(Lat1,Lat2,Lon1,Lon2);

                if (distance <= 4000){
                    int NearbyPop = jdbc.countCountryPop(country);
                    int NearbyCases = jdbc.countTotalCases(country);
                    Double NearbyIR = jdbc.countIR(country);
                    html = html + "<tr>";
                    html = html + "<td>" + country + "</td>";
                    html = html + "<td>" + NearbyPop + "</td>";
                    html = html + "<td>" + NearbyCases  + "</td>";
                    html = html + "<td>" + NearbyIR  + "</td>";
                    html = html + "</tr>";
                    count = count + 1;
                    if (count == 5){
                        break;
                    }
                }
                
            }
            
        }
        if (count < 1){
            html = html + "<h2 id = 'report2'><strong><p>Oops!</p><p>There are no nearby countries within 4000KM of  " + name + " </p><p>" + name + " is a very large country.</p><p><b><i>Note : </i></b>Large countries have less nearby countries around them.</p></strong></h2>";

        }
        html = html + "</table>";
        
    
        return html;
    }

}






