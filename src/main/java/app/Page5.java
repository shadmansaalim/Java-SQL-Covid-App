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
public class Page5 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page5.html";
    

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Analysis</title>";

        // Add some CSS (external file)
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&display=swap' rel='stylesheet'>'";
        html = html + "<link rel='stylesheet' type='text/css' href='covid.css' />";
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>"; 
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&family=Poppins:wght@300&display=swap' rel='stylesheet'  d>";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";

        html = html + "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x' crossorigin='anonymous'>";

        html = html + "<style>";
        html = html + ".bumbb{position : absolute;top : 240px; left: 60px;background-color: silver;width: 1300px;} body{background-color: #2F303A;} .fourDataTableOne{position : absolute;left : 200px;top : 550px;border: 2px solid white;}table {position : relative;top : 402px;left : 364px;width : 1068px;}th, td {padding: 15px;text-align: center;color : white;font-family: Poppins;}d{color : black;}tr{background-color: #B1C6E7;}th{background-color : black;text-align : center;}table,th,td{border: 1px solid white;border-collapse: collapse;}#p{position : absolute; left: 180px;}.infeee{position : absolute;top : 520px;left : 54px;font-size: 25px;font-family: Poppins;color: white;}";
        html = html + "</style>";

        // Add the body
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


        /*html = html + "<div class='pgfiveviewone'>";
        html = html + "<label for='checkboxviewone5'><i>Last 7 days</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewone5' type='checkbox' class='pgfiveviewonebox' id='checkboxviewone5' onclick='myFunctionViewFive()''></input>";
        html = html + "<div class='pgfiveviewtwo'>";
        html = html + "<label for='checkboxviewtwo5'><i>From the Beginning</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewtwo5' type='checkbox' class='pgfiveviewtwobox' id='checkboxviewtwo5' onclick='myFunctionViewFive()''></input>";*/



        


        /*html = html + "<div class= 'pgfivedatabackone'>";
        html = html + "<div class=pgfivecountryBlack>" + "</div>";
        html = html +  "<h3 class='fiveCountry'>Country</h3>" ;
        html = html +  "<h3 class= 'fiveInfections'>Infections</h3>" ;
        html = html +  "<h3 class='fiveDIR'>Death to Infection Ratio</h3>" ;
        html = html +  "<h3 class='fiveMDI'>Max Daily Infections</h3>" ;
        html = html +  "<h3 class='fiveMDD'>Max Daily Deaths</h3>" ;
        html = html + "</div>" ;*/

        

        html = html + "<div class='pgfourBUTTON'>";
        html = html + "<form id='viewview' action='/page5.html' method='post'>";
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
            html = html + "<h2></h2>";
            
        }
        else{
            html = html + "<div class='bumbb'>";
            html = html + "<div class='container'>";
            html = html + "<div class='jumbotron'>";
            html = html + "<h1 class='display-4'>Comparing countries around the world</h1>";
            html = html + "<p class='lead'>The following  data table shows a comparison between " + countrylist_drop + " and other countries having similar situation.</p>";
            html = html + "<hr class='my-4'>";
            html = html + "<p>Find information about states of "+ countrylist_drop +"</p>";
            html = html + "<p class='lead'>";
            html = html + "<a class='btn btn-primary btn-lg' href='#stateee' role='button'>States</a>";
            html = html + "</p>";
            html = html + "</div>";
            html = html + "</div>";
            html = html + "</div>";

            

            
            
            


            html = html + "<div id='six' style='display:block'>";
            html = html + "<h3 class='infeee'>The data table shows countries having similar infections like "+countrylist_drop+"</h3>";
            html = html + outputCountryPage5(countrylist_drop);
            html = html + "</div>";


            html = html + "<div id='four' style='display:none'>";
            html = html + "<h3 class='infeee'>The data table shows countries having similar Death-Infection ratio like "+countrylist_drop+"</h3>";
            html = html + outputCountryNameDIR(countrylist_drop);
            html = html + "</div>";

            html = html + "<div id='five' style='display:none'>";
            html = html + "<h3 class='infeee'>The data table shows countries having similar Max Daily Infections like "+countrylist_drop+"</h3>";
            html = html + outputCountryNameMAX(countrylist_drop);
            html = html + "</div>";


            
            html = html + "<script>";
            html = html + "function myFunctionViewFourA() {";
            html = html + "var checkBoxFour = document.getElementById('checkboxviewfour');";
            html = html + "var checkBoxFive = document.getElementById('checkboxviewfive');";
            html = html + "var four = document.getElementById('four');";
            html = html + "var five = document.getElementById('five');";
            html = html + "var six = document.getElementById('six');";
            html = html + "if (checkBoxFour.checked == true && checkBoxFive.checked == false){    four.style.display = 'block';five.style.display = 'none';six.style.display = 'none';}";
            html = html + "else if (checkBoxFive.checked == true && checkBoxFour.checked == false){    five.style.display = 'block';four.style.display = 'none';six.style.display = 'none';}";
            html = html + "else if (checkBoxFive.checked == true && checkBoxFour.checked == true){    five.style.display = 'none';four.style.display = 'none';six.style.display = 'none';}";
            html = html + "else{ four.style.display = 'none';five.style.display = 'none';six.style.display = 'block'}}";
            html = html + "</script>";


            html = html + "<div class='pgfiveviewthree'>";
            html = html + "<label for='checkboxviewtwo4'><i><b>Show countries having similar death to infection ratio</b></i></label>" + "</div>";
            html = html + "<input  name='checkboxviewtwo4' type='checkbox' class='pgfiveviewthreebox' id='checkboxviewfour' onclick='myFunctionViewFourA()'></input>";
            html = html + "<div class='pgfiveviewfour'>";
            html = html + "<label for='checkboxviewthree4'><i><b>Show countries having similar number of maximum daily infections</b></i></label>" + "</div>";
            html = html + "<input  name='checkboxviewthree4' type='checkbox' class='pgfiveviewfourbox' id='checkboxviewfive' onclick='myFunctionViewFourA()'></input>";
            //html = html + "<h3 class = 'pgfourNote'><b>Note : </b>  Click one checkbox at a time</h3>";

            html = html + "<div class='pgfivefirstviewbar'>";
            html = html + "</div>";
           

            ArrayList<String> states = jdbc.getStates(countrylist_drop);
    
            if ((states.size()) == 1){
                html = html + "<h2 id = 'stateee' class = 'report3'><strong><p>Sorry! No states to view for selected country</p><p>Please select another country from the drop-down box that has states</p></strong></h2>";
            }
            else{
                html = html + "<div id = 'stateee' class='pgfiveBUTTON'>";
                html = html + "<form id='viewviewtwo' action='/page6.html' method='post'>";
                html = html + "   <div class='pgfourform-group'>";
                html = html + "      <label for='statelist_drop'><i>Select a state and click Submit to get Data</i></label>" + "</div>";
                html = html + "      <select class='pgfoursss' id='statelist_drop' name='statelist_drop'>";
    
           
                for (String state : states){
                    html = html + "<li>" + state + "</li>";
                }
                html = html + "<option value='' selected disabled hidden>Choose State</option>";
                for (String state : states){
                    html = html + "<option>" + state + "</option>";
                } 
    
                html = html + "      </select>";
                html = html + "   <button type='submit' class='pgfoursub'>Submit</button>";
                html = html + "</form>" + "</div>";
    
            }
            

            
            
        

        }
        






        // Finish the HTML webpage
        html = html + "<div class='xyz'></div>";
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputCountryPage5(String name) {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();

        String countriesMain = name;
        int infectionsMain = jdbc.countInfectionsPage5Main(name);
        int deathsMain = jdbc.countDeathsPage5Main(name);
        double dirMain = jdbc.countDirPage5Main(name);
        int mdiMain = jdbc.countMdiPage5Main(name);
        int mddMain = jdbc.countMddPage5Main(name);


        String countries = jdbc.getCountryListPage5(name);
        int infections = jdbc.countInfectionsPage5(name);
        int deaths = jdbc.countDeathsPage5(name);
        double dir = jdbc.countDirPage5(name);
        int mdi = jdbc.countMdiPage5(name);
        int mdd = jdbc.countMddPage5(name);
        String countriestwo = jdbc.getCountryListPage5Two(name);
        int infectionstwo = jdbc.countInfectionsPage5Two(name);
        int deathstwo = jdbc.countDeathsPage5Two(name);
        double dirtwo = jdbc.countDirPage5Two(name);
        int mditwo = jdbc.countMdiPage5Two(name);
        int mddtwo = jdbc.countMddPage5Two(name);


        String countriesthree = jdbc.getCountryListPage5(countries);
        int infectionsthree = jdbc.countInfectionsPage5(countries);
        int deathsthree = jdbc.countDeathsPage5(countries);
        double dirthree = jdbc.countDirPage5(countries);
        int mdithree = jdbc.countMdiPage5(countries);
        int mddthree = jdbc.countMddPage5(countries);

        String countriesfour = jdbc.getCountryListPage5Two(countriestwo);
        int infectionsfour = jdbc.countInfectionsPage5Two(countriestwo);
        int deathsfour = jdbc.countDeathsPage5Two(countriestwo);
        double dirfour = jdbc.countDirPage5Two(countriestwo);
        int mdifour = jdbc.countMdiPage5Two(countriestwo);
        int mddfour = jdbc.countMddPage5Two(countriestwo);

        String countriesfive = jdbc.getCountryListPage5(countriesthree);
        int infectionsfive = jdbc.countInfectionsPage5(countriesthree);
        int deathsfive = jdbc.countDeathsPage5(countriesthree);
        double dirfive = jdbc.countDirPage5(countriesthree);
        int mdifive = jdbc.countMdiPage5(countriesthree);
        int mddfive = jdbc.countMddPage5(countriesthree);

        String countriessix = jdbc.getCountryListPage5Two(countriesfour);
        int infectionssix = jdbc.countInfectionsPage5Two(countriesfour);
        int deathssix = jdbc.countDeathsPage5Two(countriesfour);
        double dirsix = jdbc.countDirPage5Two(countriesfour);
        int mdisix = jdbc.countMdiPage5Two(countriesfour);
        int mddsix = jdbc.countMddPage5Two(countriesfour);
        
        
        // Add HTML for the movies list
        
        html = html + "<table class = 'fiveDataTable'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Infections</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Death-Infection Ratio</th>";
        html = html + "<th id=''>Max Daily Infections</th>";
        html = html + "<th id=''>Max Daily Deaths</th>";
        html = html + "</tr>";
       

        html = html + "<tr>";
        html = html + "<td>" + countriesMain + "</td>";
        html = html + "<td>" + infectionsMain + "</td>";
        html = html + "<td>" + deathsMain + "</td>";
        html = html + "<td>" + dirMain  + "</td>";
        html = html + "<td>" + mdiMain  + "</td>";
        html = html + "<td>" + mddMain + "</td>";
        html = html + "</tr>";

        

        if (countries.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfour + "</td>";
            html = html + "<td>" + infectionsfour + "</td>";
            html = html + "<td>" + deathsfour + "</td>";
            html = html + "<td>" + dirfour  + "</td>";
            html = html + "<td>" + mdifour  + "</td>";
            html = html + "<td>" + mddfour + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriessix + "</td>";
            html = html + "<td>" + infectionssix + "</td>";
            html = html + "<td>" + deathssix + "</td>";
            html = html + "<td>" + dirsix + "</td>";
            html = html + "<td>" + mdisix + "</td>";
            html = html + "<td>" + mddsix + "</td>";
            html = html + "</tr>";


        }
        
        else if(countriestwo.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";
         
            html = html + "<tr>";
            html = html + "<td>" + countriesthree + "</td>";
            html = html + "<td>" + infectionsthree + "</td>";
            html = html + "<td>" + deathsthree + "</td>";
            html = html + "<td>" + dirthree  + "</td>";
            html = html + "<td>" + mdithree  + "</td>";
            html = html + "<td>" + mddthree + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfive + "</td>";
            html = html + "<td>" + infectionsfive + "</td>";
            html = html + "<td>" + deathsfive + "</td>";
            html = html + "<td>" + dirfive + "</td>";
            html = html + "<td>" + mdifive+ "</td>";
            html = html + "<td>" + mddfive + "</td>";
            html = html + "</tr>";

        }
        
        else{
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            if ((infectionsMain - infectionsthree) > (infectionsfour - infectionsMain)){
                html = html + "<tr>";
                html = html + "<td>" + countriesfour + "</td>";
                html = html + "<td>" + infectionsfour + "</td>";
                html = html + "<td>" + deathsfour + "</td>";
                html = html + "<td>" + dirfour  + "</td>";
                html = html + "<td>" + mdifour  + "</td>";
                html = html + "<td>" + mddfour + "</td>";
                html = html + "</tr>";
            }
            else{
                html = html + "<tr>";
                html = html + "<td>" + countriesthree + "</td>";
                html = html + "<td>" + infectionsthree + "</td>";
                html = html + "<td>" + deathsthree + "</td>";
                html = html + "<td>" + dirthree  + "</td>";
                html = html + "<td>" + mdithree  + "</td>";
                html = html + "<td>" + mddthree + "</td>";
                html = html + "</tr>";
            }
            
        }
        

        
       
   
        html = html + "</table>";
       

        return html;
    }




    public String outputCountryNameDIR(String name) {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();

        String countriesMain = name;
        int infectionsMain = jdbc.countInfectionsPage5Main(name);
        int deathsMain = jdbc.countDeathsPage5Main(name);
        double dirMain = jdbc.countDirPage5Main(name);
        int mdiMain = jdbc.countMdiPage5Main(name);
        int mddMain = jdbc.countMddPage5Main(name);


        String countries = jdbc.getCountryListPage5DIR(name);
        int infections = jdbc.countInfectionsPage5DIR(name);
        int deaths = jdbc.countDeathsPage5DIR(name);
        double dir = jdbc.countDirPage5DIR(name);
        int mdi = jdbc.countMdiPage5DIR(name);
        int mdd = jdbc.countMddPage5DIR(name);
        String countriestwo = jdbc.getCountryListPage5TwoDIR(name);
        int infectionstwo = jdbc.countInfectionsPage5TwoDIR(name);
        int deathstwo = jdbc.countDeathsPage5TwoDIR(name);
        double dirtwo = jdbc.countDirPage5TwoDIR(name);
        int mditwo = jdbc.countMdiPage5TwoDIR(name);
        int mddtwo = jdbc.countMddPage5TwoDIR(name);


        String countriesthree = jdbc.getCountryListPage5(countries);
        int infectionsthree = jdbc.countInfectionsPage5(countries);
        int deathsthree = jdbc.countDeathsPage5(countries);
        double dirthree = jdbc.countDirPage5(countries);
        int mdithree = jdbc.countMdiPage5(countries);
        int mddthree = jdbc.countMddPage5(countries);

        String countriesfour = jdbc.getCountryListPage5Two(countriestwo);
        int infectionsfour = jdbc.countInfectionsPage5Two(countriestwo);
        int deathsfour = jdbc.countDeathsPage5Two(countriestwo);
        double dirfour = jdbc.countDirPage5Two(countriestwo);
        int mdifour = jdbc.countMdiPage5Two(countriestwo);
        int mddfour = jdbc.countMddPage5Two(countriestwo);

        String countriesfive = jdbc.getCountryListPage5(countriesthree);
        int infectionsfive = jdbc.countInfectionsPage5(countriesthree);
        int deathsfive = jdbc.countDeathsPage5(countriesthree);
        double dirfive = jdbc.countDirPage5(countriesthree);
        int mdifive = jdbc.countMdiPage5(countriesthree);
        int mddfive = jdbc.countMddPage5(countriesthree);

        String countriessix = jdbc.getCountryListPage5Two(countriesfour);
        int infectionssix = jdbc.countInfectionsPage5Two(countriesfour);
        int deathssix = jdbc.countDeathsPage5Two(countriesfour);
        double dirsix = jdbc.countDirPage5Two(countriesfour);
        int mdisix = jdbc.countMdiPage5Two(countriesfour);
        int mddsix = jdbc.countMddPage5Two(countriesfour);
        
        
        // Add HTML for the movies list
        
        html = html + "<table class = 'fiveDataTable'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Infections</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Death-Infection Ratio</th>";
        html = html + "<th id=''>Max Daily Infections</th>";
        html = html + "<th id=''>Max Daily Deaths</th>";
        html = html + "</tr>";
       

        html = html + "<tr>";
        html = html + "<td>" + countriesMain + "</td>";
        html = html + "<td>" + infectionsMain + "</td>";
        html = html + "<td>" + deathsMain + "</td>";
        html = html + "<td>" + dirMain  + "</td>";
        html = html + "<td>" + mdiMain  + "</td>";
        html = html + "<td>" + mddMain + "</td>";
        html = html + "</tr>";

        

        if (countries.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfour + "</td>";
            html = html + "<td>" + infectionsfour + "</td>";
            html = html + "<td>" + deathsfour + "</td>";
            html = html + "<td>" + dirfour  + "</td>";
            html = html + "<td>" + mdifour  + "</td>";
            html = html + "<td>" + mddfour + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriessix + "</td>";
            html = html + "<td>" + infectionssix + "</td>";
            html = html + "<td>" + deathssix + "</td>";
            html = html + "<td>" + dirsix + "</td>";
            html = html + "<td>" + mdisix + "</td>";
            html = html + "<td>" + mddsix + "</td>";
            html = html + "</tr>";


        }
        
        else if(countriestwo.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";
         
            html = html + "<tr>";
            html = html + "<td>" + countriesthree + "</td>";
            html = html + "<td>" + infectionsthree + "</td>";
            html = html + "<td>" + deathsthree + "</td>";
            html = html + "<td>" + dirthree  + "</td>";
            html = html + "<td>" + mdithree  + "</td>";
            html = html + "<td>" + mddthree + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfive + "</td>";
            html = html + "<td>" + infectionsfive + "</td>";
            html = html + "<td>" + deathsfive + "</td>";
            html = html + "<td>" + dirfive + "</td>";
            html = html + "<td>" + mdifive+ "</td>";
            html = html + "<td>" + mddfive + "</td>";
            html = html + "</tr>";

        }
        
        else{
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            if ((infectionsMain - infectionsthree) > (infectionsfour - infectionsMain)){
                html = html + "<tr>";
                html = html + "<td>" + countriesfour + "</td>";
                html = html + "<td>" + infectionsfour + "</td>";
                html = html + "<td>" + deathsfour + "</td>";
                html = html + "<td>" + dirfour  + "</td>";
                html = html + "<td>" + mdifour  + "</td>";
                html = html + "<td>" + mddfour + "</td>";
                html = html + "</tr>";
            }
            else{
                html = html + "<tr>";
                html = html + "<td>" + countriesthree + "</td>";
                html = html + "<td>" + infectionsthree + "</td>";
                html = html + "<td>" + deathsthree + "</td>";
                html = html + "<td>" + dirthree  + "</td>";
                html = html + "<td>" + mdithree  + "</td>";
                html = html + "<td>" + mddthree + "</td>";
                html = html + "</tr>";
            }
            
        }
        

        
       
   
        html = html + "</table>";
       

        return html;
    }



    public String outputCountryNameMAX(String name) {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();

        String countriesMain = name;
        int infectionsMain = jdbc.countInfectionsPage5Main(name);
        int deathsMain = jdbc.countDeathsPage5Main(name);
        double dirMain = jdbc.countDirPage5Main(name);
        int mdiMain = jdbc.countMdiPage5Main(name);
        int mddMain = jdbc.countMddPage5Main(name);


        String countries = jdbc.getCountryListPage5MAX(name);
        int infections = jdbc.countInfectionsPage5MAX(name);
        int deaths = jdbc.countDeathsPage5MAX(name);
        double dir = jdbc.countDirPage5MAX(name);
        int mdi = jdbc.countMdiPage5MAX(name);
        int mdd = jdbc.countMddPage5MAX(name);
        String countriestwo = jdbc.getCountryListPage5TwoMAX(name);
        int infectionstwo = jdbc.countInfectionsPage5TwoMAX(name);
        int deathstwo = jdbc.countDeathsPage5TwoMAX(name);
        double dirtwo = jdbc.countDirPage5TwoMAX(name);
        int mditwo = jdbc.countMdiPage5TwoMAX(name);
        int mddtwo = jdbc.countMddPage5TwoMAX(name);


        String countriesthree = jdbc.getCountryListPage5(countries);
        int infectionsthree = jdbc.countInfectionsPage5(countries);
        int deathsthree = jdbc.countDeathsPage5(countries);
        double dirthree = jdbc.countDirPage5(countries);
        int mdithree = jdbc.countMdiPage5(countries);
        int mddthree = jdbc.countMddPage5(countries);

        String countriesfour = jdbc.getCountryListPage5Two(countriestwo);
        int infectionsfour = jdbc.countInfectionsPage5Two(countriestwo);
        int deathsfour = jdbc.countDeathsPage5Two(countriestwo);
        double dirfour = jdbc.countDirPage5Two(countriestwo);
        int mdifour = jdbc.countMdiPage5Two(countriestwo);
        int mddfour = jdbc.countMddPage5Two(countriestwo);

        String countriesfive = jdbc.getCountryListPage5(countriesthree);
        int infectionsfive = jdbc.countInfectionsPage5(countriesthree);
        int deathsfive = jdbc.countDeathsPage5(countriesthree);
        double dirfive = jdbc.countDirPage5(countriesthree);
        int mdifive = jdbc.countMdiPage5(countriesthree);
        int mddfive = jdbc.countMddPage5(countriesthree);

        String countriessix = jdbc.getCountryListPage5Two(countriesfour);
        int infectionssix = jdbc.countInfectionsPage5Two(countriesfour);
        int deathssix = jdbc.countDeathsPage5Two(countriesfour);
        double dirsix = jdbc.countDirPage5Two(countriesfour);
        int mdisix = jdbc.countMdiPage5Two(countriesfour);
        int mddsix = jdbc.countMddPage5Two(countriesfour);
        
        
        // Add HTML for the movies list
        
        html = html + "<table class = 'fiveDataTable'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Infections</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Death-Infection Ratio</th>";
        html = html + "<th id=''>Max Daily Infections</th>";
        html = html + "<th id=''>Max Daily Deaths</th>";
        html = html + "</tr>";
       

        html = html + "<tr>";
        html = html + "<td>" + countriesMain + "</td>";
        html = html + "<td>" + infectionsMain + "</td>";
        html = html + "<td>" + deathsMain + "</td>";
        html = html + "<td>" + dirMain  + "</td>";
        html = html + "<td>" + mdiMain  + "</td>";
        html = html + "<td>" + mddMain + "</td>";
        html = html + "</tr>";

        

        if (countries.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfour + "</td>";
            html = html + "<td>" + infectionsfour + "</td>";
            html = html + "<td>" + deathsfour + "</td>";
            html = html + "<td>" + dirfour  + "</td>";
            html = html + "<td>" + mdifour  + "</td>";
            html = html + "<td>" + mddfour + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriessix + "</td>";
            html = html + "<td>" + infectionssix + "</td>";
            html = html + "<td>" + deathssix + "</td>";
            html = html + "<td>" + dirsix + "</td>";
            html = html + "<td>" + mdisix + "</td>";
            html = html + "<td>" + mddsix + "</td>";
            html = html + "</tr>";


        }
        
        else if(countriestwo.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";
         
            html = html + "<tr>";
            html = html + "<td>" + countriesthree + "</td>";
            html = html + "<td>" + infectionsthree + "</td>";
            html = html + "<td>" + deathsthree + "</td>";
            html = html + "<td>" + dirthree  + "</td>";
            html = html + "<td>" + mdithree  + "</td>";
            html = html + "<td>" + mddthree + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfive + "</td>";
            html = html + "<td>" + infectionsfive + "</td>";
            html = html + "<td>" + deathsfive + "</td>";
            html = html + "<td>" + dirfive + "</td>";
            html = html + "<td>" + mdifive+ "</td>";
            html = html + "<td>" + mddfive + "</td>";
            html = html + "</tr>";

        }
        
        else{
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            if ((infectionsMain - infectionsthree) > (infectionsfour - infectionsMain)){
                html = html + "<tr>";
                html = html + "<td>" + countriesfour + "</td>";
                html = html + "<td>" + infectionsfour + "</td>";
                html = html + "<td>" + deathsfour + "</td>";
                html = html + "<td>" + dirfour  + "</td>";
                html = html + "<td>" + mdifour  + "</td>";
                html = html + "<td>" + mddfour + "</td>";
                html = html + "</tr>";
            }
            else{
                html = html + "<tr>";
                html = html + "<td>" + countriesthree + "</td>";
                html = html + "<td>" + infectionsthree + "</td>";
                html = html + "<td>" + deathsthree + "</td>";
                html = html + "<td>" + dirthree  + "</td>";
                html = html + "<td>" + mdithree  + "</td>";
                html = html + "<td>" + mddthree + "</td>";
                html = html + "</tr>";
            }
            
        }
        

        
       
   
        html = html + "</table>";
       

        return html;
    }


    


    public String outputStateData(String name,String country) {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();

        String statesMain = name;
        int infectionsMain = jdbc.countInfectionsPage5MainStates(name);
        int deathsMain = jdbc.countDeathsPage5MainStates(name);
        double dirMain = jdbc.countDirPage5MainStates(name);
        int mdiMain = jdbc.countMdiPage5MainStates(name);
        int mddMain = jdbc.countMddPage5MainStates(name);


        String countries = jdbc.getCountryListPage5States(name,country);
        int infections = jdbc.countInfectionsPage5States(name, country);
        int deaths = jdbc.countDeathsPage5States(name, country);
        double dir = jdbc.countDirPage5States(name, country);
        int mdi = jdbc.countMdiPage5States(name, country);
        int mdd = jdbc.countMddPage5States(name, country);
        String countriestwo = jdbc.getCountryListPage5TwoStates(name,country);
        int infectionstwo = jdbc.countInfectionsPage5TwoStates(name, country);
        int deathstwo = jdbc.countDeathsPage5TwoStates(name, country);
        double dirtwo = jdbc.countDirPage5TwoStates(name, country);
        int mditwo = jdbc.countMdiPage5TwoStates(name, country);
        int mddtwo = jdbc.countMddPage5TwoStates(name, country);


        String countriesthree = jdbc.getCountryListPage5States(countries,country);
        int infectionsthree = jdbc.countInfectionsPage5States(countries,country);
        int deathsthree = jdbc.countDeathsPage5States(countries,country);
        double dirthree = jdbc.countDirPage5States(countries,country);
        int mdithree = jdbc.countMdiPage5States(countries,country);
        int mddthree = jdbc.countMddPage5States(countries,country);

        String countriesfour = jdbc.getCountryListPage5TwoStates(countriestwo,country);
        int infectionsfour = jdbc.countInfectionsPage5TwoStates(countriestwo,country);
        int deathsfour = jdbc.countDeathsPage5TwoStates(countriestwo,country);
        double dirfour = jdbc.countDirPage5TwoStates(countriestwo,country);
        int mdifour = jdbc.countMdiPage5TwoStates(countriestwo,country);
        int mddfour = jdbc.countMddPage5TwoStates(countriestwo,country);

       
        String countriesfive = jdbc.getCountryListPage5States(countriesthree,country);
        int infectionsfive = jdbc.countInfectionsPage5States(countriesthree,country);
        int deathsfive = jdbc.countDeathsPage5States(countriesthree,country);
        double dirfive = jdbc.countDirPage5States(countriesthree,country);
        int mdifive = jdbc.countMdiPage5States(countriesthree,country);
        int mddfive = jdbc.countMddPage5States(countriesthree,country);

        String countriessix = jdbc.getCountryListPage5TwoStates(countriesfour,country);
        int infectionssix = jdbc.countInfectionsPage5TwoStates(countriesfour,country);
        int deathssix = jdbc.countDeathsPage5TwoStates(countriesfour,country);
        double dirsix = jdbc.countDirPage5TwoStates(countriesfour,country);
        int mdisix = jdbc.countMdiPage5TwoStates(countriesfour,country);
        int mddsix = jdbc.countMddPage5TwoStates(countriesfour,country);

        
        
        
        // Add HTML for the movies list
        
        html = html + "<table class = 'fiveDataTableTwo'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Infections</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Death-Infection Ratio</th>";
        html = html + "<th id=''>Max Daily Infections</th>";
        html = html + "<th id=''>Max Daily Deaths</th>";
        html = html + "</tr>";
       

        html = html + "<tr>";
        html = html + "<td>" + statesMain + "</td>";
        html = html + "<td>" + infectionsMain + "</td>";
        html = html + "<td>" + deathsMain + "</td>";
        html = html + "<td>" + dirMain  + "</td>";
        html = html + "<td>" + mdiMain  + "</td>";
        html = html + "<td>" + mddMain + "</td>";
        html = html + "</tr>";

        

        if (countries.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfour + "</td>";
            html = html + "<td>" + infectionsfour + "</td>";
            html = html + "<td>" + deathsfour + "</td>";
            html = html + "<td>" + dirfour  + "</td>";
            html = html + "<td>" + mdifour  + "</td>";
            html = html + "<td>" + mddfour + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriessix + "</td>";
            html = html + "<td>" + infectionssix + "</td>";
            html = html + "<td>" + deathssix + "</td>";
            html = html + "<td>" + dirsix + "</td>";
            html = html + "<td>" + mdisix + "</td>";
            html = html + "<td>" + mddsix + "</td>";
            html = html + "</tr>";


        }
        
        else if(countriestwo.isEmpty()){
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";
         
            html = html + "<tr>";
            html = html + "<td>" + countriesthree + "</td>";
            html = html + "<td>" + infectionsthree + "</td>";
            html = html + "<td>" + deathsthree + "</td>";
            html = html + "<td>" + dirthree  + "</td>";
            html = html + "<td>" + mdithree  + "</td>";
            html = html + "<td>" + mddthree + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriesfive + "</td>";
            html = html + "<td>" + infectionsfive + "</td>";
            html = html + "<td>" + deathsfive + "</td>";
            html = html + "<td>" + dirfive + "</td>";
            html = html + "<td>" + mdifive+ "</td>";
            html = html + "<td>" + mddfive + "</td>";
            html = html + "</tr>";

        }
        
        else{
            html = html + "<tr>";
            html = html + "<td>" + countries + "</td>";
            html = html + "<td>" + infections + "</td>";
            html = html + "<td>" + deaths + "</td>";
            html = html + "<td>" + dir  + "</td>";
            html = html + "<td>" + mdi  + "</td>";
            html = html + "<td>" + mdd  + "</td>";
            html = html + "</tr>";

            html = html + "<tr>";
            html = html + "<td>" + countriestwo + "</td>";
            html = html + "<td>" + infectionstwo + "</td>";
            html = html + "<td>" + deathstwo + "</td>";
            html = html + "<td>" + dirtwo  + "</td>";
            html = html + "<td>" + mditwo  + "</td>";
            html = html + "<td>" + mddtwo + "</td>";
            html = html + "</tr>";

            if ((infectionsMain - infectionsthree) > (infectionsfour - infectionsMain)){
                html = html + "<tr>";
                html = html + "<td>" + countriesfour + "</td>";
                html = html + "<td>" + infectionsfour + "</td>";
                html = html + "<td>" + deathsfour + "</td>";
                html = html + "<td>" + dirfour  + "</td>";
                html = html + "<td>" + mdifour  + "</td>";
                html = html + "<td>" + mddfour + "</td>";
                html = html + "</tr>";
            }
            else{
                html = html + "<tr>";
                html = html + "<td>" + countriesthree + "</td>";
                html = html + "<td>" + infectionsthree + "</td>";
                html = html + "<td>" + deathsthree + "</td>";
                html = html + "<td>" + dirthree  + "</td>";
                html = html + "<td>" + mdithree  + "</td>";
                html = html + "<td>" + mddthree + "</td>";
                html = html + "</tr>";
            }
            
        }
        

        
       
   
        html = html + "</table>";
       

        return html;
    }

}
