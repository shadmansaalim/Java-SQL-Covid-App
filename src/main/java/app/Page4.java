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
public class Page4 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Bangladesh</title>";

        // Add some CSS (external file)
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&display=swap' rel='stylesheet'>'";
        html = html + "<link rel='stylesheet' type='text/css' href='covid.css' />";
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>"; 
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&family=Poppins:wght@300&display=swap' rel='stylesheet'  d>";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";

        html = html + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x' crossorigin='anonymous'>";
        // Add the body
        html = html + "<style>";
        html = html + ".jumbb{position : absolute;top : 240px; left: 60px;background-color: silver;} body{background-color: #2F303A;} .fourDataTableOne{position : absolute;left : 200px;top : 550px;border: 2px solid white;}table {position : relative;top : 402px;left : 364px;width : 1068px;}th, td {padding: 15px;text-align: center;color : white;font-family: Poppins;}d{color : black;}tr{background-color: #B1C6E7;}th{background-color : black;text-align : center;}table,th,td{border: 1px solid white;border-collapse: collapse;}#m{position : absolute; left: 225px;}";
        html = html + "</style>";
        html = html + "<body>";

        html = html + "<a href='/'>";
        html = html + "<img class='logo' src='logo.png'/>";
        html = html + "</a>";
        html = html + "<h3 class = 'spreadtwo'>Spreading Info</h3>";

        html = html + "<div class='topnav'>";
        html = html + "<a class = 'home' href='/'>Home</a>";
        html = html + "<a class = 'update' href='page1.html'>Covid Updates</a>";
        html = html + "<a class = 'countries' href='page2.html'>Countries</a>";
        html = html + "<a class = 'deaths' href='page3.html'>Deaths</a>";
        html = html + "<a class = 'data' href='page4.html'>Data</a>";
        html = html + "<a class = 'analysis' href='page5.html'>Analysis</a>";
        html = html + "<a class = 'about' href='page6.html'>About Us</a>";
        html = html + "</div>";

       
        /*html = html + "<div class= 'pgfourdataback'>";
        html = html + "<div class=pgfourcountryBlack>" + "</div>";
        html = html +  "<h3 class='fourCountry'>Country</h3>" ;
        html = html +  "<h3 class= 'fourPop'>Population</h3>" ;
        html = html +  "<h3 class='fourCases'>Cases</h3>" ;
        html = html +  "<h3 class='fourDeaths'>Deaths</h3>" ;
        html = html +  "<h3 class='fourIR'>Infection Rate</h3>" ;
        html = html +  "<h3 class='fourDR'>Death Rate</h3>" ;
        html = html + "</div>" ;*/

        


        html = html + "<div class='pgfourBUTTON'>";
        html = html + "<form id='viewview' action='/page4.html' method='post'>";
        html = html + "   <div class='pgfourform-group'>";
        html = html + "      <label for='countrylist_drop'><i>Select a country and click on Submit to get Data</i></label>" + "</div>";
        html = html + "      <select class='pgfoursss' id='countrylist_drop' name='countrylist_drop'>";


    
        JDBCConnection jdbc = new JDBCConnection();
       
        ArrayList<String> countries = jdbc.getCountryList();
       
        for (String country : countries) {
            html = html + "<li>" + country + "</li>";
        }
        html = html + "<option value='' selected disabled hidden>Choose Country</option>";
        for (String country : countries) {
            html = html + "<option>" + country + "</option>";
        } 

        html = html + "      </select>";
        html = html + "   <button type='submit' class='pgfoursub'>Submit</button>";
        html = html + "</form>" + "</div>";


        String countrylist_drop = context.formParam("countrylist_drop");


       

        

        if (countrylist_drop == null || countrylist_drop == "") {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2 class='geoTitle'>The following GEO Chart might help you to select a country and get it's data.</h2>";
            html = html + "<p class='geo'><iframe width='698' height='430' seamless frameborder='0' scrolling='no' src='https://docs.google.com/spreadsheets/d/e/2PACX-1vSsn8lCXDer9K06fB9s_O58uFQ-GWWdN5DVP9q3BkmRoWMgwNWD7WBrENBt2RSfxTVlS56DTENCGcWN/pubchart?oid=433928853&amp;format=interactive'></iframe></p>";
            


        }
        else{
            
            html = html + "<p class='pgfourtext'>";
            //html = html + outputCountryDescription(countrylist_drop);
            html = html + "</p>";
            
            

            html = html + "<div class='jumbb'>";
            html = html + "<div class='container'>";
            html = html + "<div class='jumbotron'>";
            html = html + "<h1 class='display-4'>Locational and Cumulative Information</h1>";
            html = html + "<p class='lead'>" + outputCountryDescription(countrylist_drop) + "</p>";
            html = html + "<hr class='my-4'>";
            html = html + "<p>Find more about Nearby Countries and Data Ratio of "+ countrylist_drop +"</p>";
            html = html + "<p class='lead'>";
            html = html + "<a class='btn btn-primary btn-lg' href='#nearbyBlack' role='button'>Nearby Countries</a>";
            html = html + "<a class='btn btn-primary btn-lg' href='#timeBlack' role='button' id='m'>Data Ratio</a>";
            html = html + "</p>";
            html = html + "</div>";
            html = html + "</div>";
            html = html + "</div>";


            html = html + outputCountryClimateName(countrylist_drop);

        html = html + "<div id='ut' style='display:block'>";
        html = html + outputCountryNameNearbyDeafult(countrylist_drop);
        html = html + "</div>";  

            html = html + "<div id='one' style='display:none'>";
        html = html + outputCountryNameNearby1500(countrylist_drop);
        html = html + "</div>";  
        
        html = html + "<div id='two' style='display:none'>";
        html = html + outputCountryNameNearby2500(countrylist_drop);
        html = html + "</div>";
        html = html + "<div id='three' style='display:none'>";
        html = html + outputCountryNameNearby4000(countrylist_drop);    
        html = html + "</div>";
        
        html = html + "<div id='four' style='display:none'>";
        html = html + outputCountryIDR7(countrylist_drop);
        html = html + outputCountryIDPR7(countrylist_drop);
        html = html + "</div>";
        html = html + "<div id='five' style='display:none'>";
        html = html + outputCountryIDRBeginning(countrylist_drop);
        html = html + outputCountryIDPRBeginning(countrylist_drop); 
        html = html + "</div>";

        //html = html + "<h1 class = 'pgfourhead'>Locational and Cumulative Information</h1>";
            

            

        html = html + "<script>";
        html = html + "function myFunctionViewFour() {";
        html = html + "var checkBoxOne4 = document.getElementById('checkboxviewone4');";
        html = html + "var checkBoxTwo4 = document.getElementById('checkboxviewtwo4');";
        html = html + "var checkBoxThree4 = document.getElementById('checkboxviewthree4');";
        html = html + "var one = document.getElementById('one');";
        html = html + "var two = document.getElementById('two');";
        html = html + "var three = document.getElementById('three');";
        html = html + "var ut = document.getElementById('ut');";

        html = html + "if (checkBoxOne4.checked == true && (checkBoxTwo4.checked == false && checkBoxThree4.checked == false)){    one.style.display = 'block';two.style.display = 'none';three.style.display = 'none';ut.style.display = 'none';}";
        html = html + "else if (checkBoxTwo4.checked == true && (checkBoxOne4.checked == false && checkBoxThree4.checked == false)){    two.style.display = 'block';one.style.display = 'none';three.style.display = 'none';ut.style.display = 'none';}";
        html = html + "else if (checkBoxThree4.checked == true && (checkBoxOne4.checked == false && checkBoxTwo4.checked == false)){    three.style.display = 'block';one.style.display = 'none';two.style.display = 'none';ut.style.display = 'none';}";
        html = html + "else if (checkBoxThree4.checked == false && (checkBoxOne4.checked == false && checkBoxTwo4.checked == false)){    ut.style.display = 'block';one.style.display = 'none';two.style.display = 'none';three.style.display = 'none';}";
        html = html + "else{ one.style.display = 'none';two.style.display = 'none';three.style.display = 'none';ut.style.display = 'none';}}";
        html = html + "</script>";

        html = html + "<script>";
        html = html + "function myFunctionViewFourA() {";
        html = html + "var checkBoxFour = document.getElementById('checkboxviewfour');";
        html = html + "var checkBoxFive = document.getElementById('checkboxviewfive');";
        html = html + "var four = document.getElementById('four');";
        html = html + "var five = document.getElementById('five');";
        html = html + "if (checkBoxFour.checked == true && checkBoxFive.checked == false){    four.style.display = 'block';five.style.display = 'none';}";
        html = html + "else if (checkBoxFive.checked == true && checkBoxFour.checked == false){    five.style.display = 'block';four.style.display = 'none';}";
        html = html + "else{ four.style.display = 'none';five.style.display = 'none';}}";
        html = html + "</script>";

        html = html + "<div class = 'pgfourcountryNearbyBlack' id='nearbyBlack'>" + "</div>";
        html = html + "<i class='nearbytext'>Top 5 Nearby countries of " + countrylist_drop + " within</i>";
        html = html + "<div class='pgfourviewone'>";
        html = html + "<label for='checkboxviewone4'><i>1500 Km</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewone4' type='checkbox' class='pgfourviewonebox' id='checkboxviewone4' onclick='myFunctionViewFour()'></input>";

        html = html + "<div class='pgfourviewtwo'>";
        html = html + "<label for='checkboxviewtwo4'><i>2500 Km</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewtwo4' type='checkbox' class='pgfourviewtwobox' id='checkboxviewtwo4' onclick='myFunctionViewFour()'></input>";
        html = html + "<div class='pgfourviewthree'>";
        html = html + "<label for='checkboxviewthree4'><i>4000 Km</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewthree4' type='checkbox' class='pgfourviewthreebox' id='checkboxviewthree4' onclick='myFunctionViewFour()'></input>";
        html = html + "<h3 class = 'pgfourNote'><b>Note : </b>  Click one checkbox at a time</h3>";
        //html = html + "</div>";
    
        html = html + "<div class = 'pgfourcountryTimeBlack' id='timeBlack'>" + "</div>";
        html = html + "<i class='timetext'>Select Time Period </i>";
        html = html + "<div class='pgfourviewfour'>";
        html = html + "<label for='checkboxviewfour'><i>Last 7 days</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewfour' type='checkbox' class='pgfourviewfourbox' id='checkboxviewfour' onclick='myFunctionViewFourA()'></input>";

        html = html + "<div class='pgfourviewfive'>";
        html = html + "<label for='checkboxviewfive'><i>From the beginning</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewfive' type='checkbox' class='pgfourviewfivebox' id='checkboxviewfive' onclick='myFunctionViewFourA()'></input>";

        html = html + "<h3 class = 'pgfourNoteTime'><b>Note : </b>  Click one checkbox at a time</h3>";
        html = html + "</div>";

        html = html + "<div class = 'pgfourIDRback'>";
        html = html + "<h3 class='IDRtext'>Infection to Death Ratio</h3>";
        html = html + "</div>";
        html = html + "<div class='IDRvalueback'>";
        html = html + "</div>";
        html = html + "<div class='pgfourIDPRback'>";
        html = html + "<h3 class='IDPRtext'>Infection and Death to Total Population Ratio</h3>";
        html = html + "</div>";
        html = html + "<div class='IDPRvalueback'>";
        html = html + "</div>";
            
            
        

            
            
        }
        

       
        
        

        


        //html = html + "<div class = 'pgfournearbydataback'>";
        /*html = html + "<div class=pgfourcountryBlackNearbyHead>" + "</div>";
        html = html +  "<h3 class='fourCountryNearby'>Country</h3>" ;
        html = html +  "<h3 class= 'fourPopNearby'>Population</h3>" ;
        html = html +  "<h3 class='fourCasesNearby'>Cases</h3>" ;
        html = html +  "<h3 class='fourIRNearby'>Infection Rate</h3>" ;
        html = html + "</div>";*/



        

        
       

     

        

        // Finish the HTML webpage
        html = html + "<div class='xyz'></div>";
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
    public String outputCountryName(String name) {
        String html = "";
        html = html + "<p class='pgfourcountryname'>" + name + "</p>";
        return html;
    }

    public String outputCountryDescription(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        String cases = jdbc.getCountryDescription(name);
        
        html = html + cases;
        return html;
    }
    public String outputCountryClimateName(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryClimateName(name);
        ArrayList<Integer> pop = jdbc.getCountryClimateNamePop(name);
        ArrayList<Integer> cases = jdbc.countTotalCasesAllCountriesClimate(name);
        ArrayList<Integer> deaths = jdbc.countTotalDeathsAllCountriesClimate(name);
        ArrayList<Double> ir = jdbc.countClimateIR(name);
        ArrayList<Double> dr = jdbc.countClimateDR(name);

        int i =0; 
        html = html + "<table class = 'fourDataTableOne'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Population</th>";
        html = html + "<th id=''>Cases</th>";
        html = html + "<th id=''>Deaths</th>";
        html = html + "<th id=''>Infection Rate</th>";
        html = html + "<th id=''>Death Rate</th>";
        for (String country : countries) {
            html = html + "<tr>";
            html = html + "<td>" + country + "</td>";
            html = html + "<td>" + pop.get(i)  + "</td>";
            html = html + "<td>" + cases.get(i)  + "</td>";
            html = html + "<td>" + deaths.get(i)  + "</td>";
            html = html + "<td>" + ir.get(i)  + "</td>";
            html = html + "<td>" + dr.get(i)  + "</td>";
            html = html + "</tr>";
            i = i + 1;
        }
        html = html + "</table>";
        
    
        return html;
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
public String outputCountryNameNearbyDeafult(String name) {
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

            if (distance <= 8000){
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
        html = html + "<h2 id = 'report2'><strong><p>Oops!</p><p>There are no nearby countries of  " + name + " </p><p><b><i>Note : </i></b>Large countries have less nearby countries around them.</p></strong></h2>";
    }
    html = html + "</table>";
    

    return html;
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
 
    

   

    public String outputCountryIDR7(String name){
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> countries = jdbc.getIDR7(name);

        html = html + "<div class='IDR7'>";
        html = html + "<ul>";
        for (Double abc : countries){
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";

        return html;
    }
    public String outputCountryIDPR7(String name){
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> countries = jdbc.getIDPR7(name);

        html = html + "<div class='IDPR7'>";
        html = html + "<ul>";
        for (Double abc : countries){
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";

        return html;
    }
    public String outputCountryIDRBeginning(String name){
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> countries = jdbc.getIDRBeginning(name);

        html = html + "<div class='IDR7'>";
        html = html + "<ul>";
        for (Double abc : countries){
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";

        return html;
    }
    public String outputCountryIDPRBeginning(String name){
        String html = "";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> countries = jdbc.getIDPRBeginning(name);

        html = html + "<div class='IDPR7'>";
        html = html + "<ul>";
        for (Double abc : countries){
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";

        return html;
    }





}
