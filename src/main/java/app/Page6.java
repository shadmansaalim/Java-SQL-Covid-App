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
public class Page6 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page6.html";

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

        html = html + "</script>";

        
        
        String statelist_drop = context.formParam("statelist_drop");


        if (statelist_drop == null || statelist_drop == "") {
            html = html + "<h2></h2>";
                
        }
        else{
            html = html + "<h3 class='infeeee'>The data table shows states having similar infections like <b>"+statelist_drop+"</b></h3>";
            html = html + outputStateData(statelist_drop,outputCountryN(statelist_drop));
            html = html + "<h3 class='infeeeee'>The data table shows countries having similar infections like <b>"+statelist_drop+"</b></h3>";
            html = html + outputStateDataB(statelist_drop);
        }

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
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
    public String outputCountryN(String name){

        JDBCConnection jdbc = new JDBCConnection();
        String country = jdbc.getCountryNameFromState(name);

        return country;
    }














    public String outputStateDataB(String name) {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();

        String statesMain = name;
        int infectionsMain = jdbc.countInfectionsPage5MainStates(name);
        int deathsMain = jdbc.countDeathsPage5MainStates(name);
        double dirMain = jdbc.countDirPage5MainStates(name);
        int mdiMain = jdbc.countMdiPage5MainStates(name);
        int mddMain = jdbc.countMddPage5MainStates(name);


        String countries = jdbc.getCountryListPage6States(name);
        int infections = jdbc.countInfectionsPage6States(name);
        int deaths = jdbc.countDeathsPage6States(name);
        double dir = jdbc.countDirPage6States(name);
        int mdi = jdbc.countMdiPage6States(name);
        int mdd = jdbc.countMddPage6States(name);
        String countriestwo = jdbc.getCountryListPage6TwoStates(name);
        int infectionstwo = jdbc.countInfectionsPage6TwoStates(name);
        int deathstwo = jdbc.countDeathsPage6TwoStates(name);
        double dirtwo = jdbc.countDirPage6TwoStates(name);
        int mditwo = jdbc.countMdiPage6TwoStates(name);
        int mddtwo = jdbc.countMddPage6TwoStates(name);


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
        
        html = html + "<table class = 'fiveDataTableThree'>";
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
    public String outputCountry(String name){

        JDBCConnection jdbc = new JDBCConnection();
        String country = jdbc.getCountryNameFromState(name);

        return country;
    }
}






