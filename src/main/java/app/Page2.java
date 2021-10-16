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

public class Page2 implements Handler {

    
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page2.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Australia</title>";

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
        html = html + "<a class = 'countries' href='page2.html'>Countries</a>";
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




    
        
        html = html + "<div class='pgfourBUTTON'>";
        html = html + "<form id='viewview' action='/page2.html' method='post'>";
        html = html + "   <div class='pgfourform-group'>";
        html = html + "      <label for='countrylist_drop'><i>Select a country and click on Submit</i></label>" + "</div>";
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
        else {
            html = html + "<div id='textTwo' style='display:block'>";
            html = html + outputCountryData(countrylist_drop);
            //html = html + outputStateTotalCases(countrylist_drop);
            //html = html + outputStateLastWeek(countrylist_drop);
            html = html + "</div>";

            html = html + "<div id='text' style='display: none'>";
            html = html + outputCountryDataWorstToLeast(countrylist_drop);
            //html = html + outputStateTotalCasesWorstToLeast(countrylist_drop);
            //html = html + outputStateLastWeekWorstToLeast(countrylist_drop);
            html = html + "</div>";
    
            html = html + "<div class = 'BUTTON'>";
            html = html + "<div class='viewOption2'>";
            html = html + "<label for='checkboxview'><i>View by worst affected to least affected state</label></i>" + "</div>";
            html = html + "<input  name='checkboxview' type='checkbox' class='viewOption3' id='checkboxview' onclick='myFunctionView()''></input>";
            html = html + "</div>";
            
            html = html + "<script>";
            html = html + "function myFunctionView() {";
            html = html + "var checkBox = document.getElementById('checkboxview');";
            html = html + "var text = document.getElementById('text');var hello = document.getElementById('textTwo');";
            html = html + "if (checkBox.checked == true){    text.style.display = 'block';hello.style.display = 'none';}";
            html = html + "else {hello.style.display = 'block';text.style.display = 'none';}}";
            html = html + "</script>";

            
            
            
            
            html = html + "<div class='l'>";
            html = html + "<p class='counter'>Live Counter</p>" + "</div>";
            html = html + "<table id ='tableTwo'>";
            html = html + "<tr>";
            html = html + "<th >Total Cases</th>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<td class='o'>" + outputCountryTotalCases(countrylist_drop) + "</td>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<th >Cases (Last Week)</th>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<td class='o'>" + outputCountryLastWeek(countrylist_drop) + "</td>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<th >Highest Daily Cases</th>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<td class='o'>" + outputCountryHighest(countrylist_drop) + "</td>";
            html = html + "</tr>";
            html = html + "</table>";
            
        }
        
        

        
        

        





        //html = html + "<div class='white'>" + "</div>";
        //html = html + "<div class='pagetwowhitetwo'>" +"</div>";
        
        

        /*html = html + "<div class='pgtwoltwo'>";
        html = html + "<p class='pagetwoone'>Total Cases</p>";
        
        html = html + "<p class='pagetwothree'>Cases(Last Week)</p>";
        
        html = html + "<p class='pagetwofive'>Highest Daily Cases</p>";
        html = html + "</div>";*/


        /*html = html + "<div class='statebar'>";
        html = html + "<p class='state'>State</p>" + "</div>";
        html = html + "<div class='pgtwowhite'>" + "</div>";
        html = html + "<div class='totalbar'>";
        html = html + "<p class='total'>Total</p>" + "</div>";
        html = html + "<div class='pgtwowhitetwo'>" + "</div>";
        html = html + "<div class='lastweekbar'>";
        html = html + "<p class='lastweek'>Last Week</p>" + "</div>";
        html = html + "<div class='pgtwowhitethree'>" + "</div>";
        html = html + "<div class='pgtwodataback'>" + "</div>";*/
        
        


        /*html = html + "<div class='Rzone'>";
        html = html + "<a class='rzone' href=''>Red Zones</a>";
        html = html + "</div>";

        html = html + "<div class='Gzone'>";
        html = html + "<a class='gzone' href=''>Green Zones</a>";
        html = html + "</div>";

        html = html + "<div class='Impact'>";
        html = html + "<a class='impact' href=''>Impact of Covid</a>";
        html = html + "</div>";

        html = html + "<div class='More'>";
        html = html + "<a class='more' href=''>Find Out More</a>";
        html = html + "</div>";*/



        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }




    public String outputCountryData(String name) {
        String html = "";
        html = html + "<h2 class='countryhead'>" + name + " Cases</h2>";

        
        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getStates(name);
        ArrayList<Integer> cases = jdbc.countStateTotalCases(name);
        ArrayList<Integer> caseslw = jdbc.countStateLastWeek(name);
        
        // Add HTML for the movies list
        //html = html + "<div class='statelist'>";
        if ((countries.size()) == 1){
            html = html + "<h2 id = 'report'><strong><p>Sorry! No states to view for selected country</p><p>Please select another country from the drop-down box that has states</p></strong></h2>";
        }
        else{
            int i =0;
            html = html + "<table>";
            html = html + "<tr>";
            html = html + "<th id='state'>State</th>";
            html = html + "<th id ='total'>Total</th>";
            html = html + "<th id='lastWeek'>Last Week</th>";
            html = html + "</tr>";
            for (String country : countries) {
                
                html = html + "<tr>";
                html = html + "<td>" + country +  "</td>";
                html = html + "<td>" + cases.get(i)  + "</td>";
                html = html + "<td>" + caseslw.get(i)+ "</td>";
                html = html + "</tr>";
                i = i + 1;
            }
            html = html + "</table>";
        }
        

        
        //html = html + "</div>";

        return html;
    }


    public String outputCountryTotalCases(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        Integer cases = jdbc.countTotalCases(name);
        
        // Add HTML for the movies list
        //html = html + "<div class='pgtwototalcases'>";
        html = html + cases;
        //html = html + "</div>";
        return html;
    }

    public String outputCountryLastWeek(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        Integer cases = jdbc.countCountryLastWeek(name);
        
        // Add HTML for the movies list
        //html = html + "<div class='pgtwocountrylastweek'>";
        html = html + cases;
        //html = html + "</div>";
        return html;
    }

    public String outputCountryHighest(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        Integer cases = jdbc.countCountryHighest(name);
        
        // Add HTML for the movies list
        //html = html + "<div class='pgtwocountryhighest'>";
        html = html + cases;
        //html = html + "</div>";
        return html;
    }

    public String outputStateTotalCases(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countStateTotalCases(name);
        
        
        // Add HTML for the movies list
        html = html + "<div class='pgtwostatetotalcases'>";
        
        for (Integer ab : cases) {
            html = html + "<tr>";
            html = html + "<td>" + ab + "</td>";
            html = html + "</tr>";
        }
        
        html = html + "</div>";
        return html;
    }

    public String outputStateLastWeek(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countStateLastWeek(name);
        
        // Add HTML for the movies list
        html = html + "<div class='pgtwostatelastweek'>";
        
        for (Integer cd : cases) {
            html = html + "<tr>";
            html = html + "<td>" + cd + "</td>";
            html = html + "</tr>";
        }
        
        html = html + "</div>";
        return html;
    }


    public String outputCountryDataWorstToLeast(String name) {
        String html = "";
        html = html + "<h2 class='countryhead'>" + name  + " Cases</h2>";
    

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getStatesByWorstToLeast(name);
        ArrayList<Integer> caseswtol = jdbc.countStateTotalCasesWorstToLeast(name);
        ArrayList<Integer> caseslwwtol= jdbc.countStateLastWeekWorstToLeast(name);
        
        // Add HTML for the movies list
        //html = html + "<div class='statelist'>";

        if ((countries.size()) == 1){
            html = html + "<h2 id = 'report'><strong><p>Sorry! No states to view for selected country</p><p>Please select another country from the drop-down box that has states</p></strong></h2>";        }
        else{
            int i =0;
            html = html + "<table>";
            html = html + "<tr>";
            html = html + "<th id='state'>State</th>";
            html = html + "<th id ='total'>Total</th>";
            html = html + "<th id='lastWeek'>Last Week</th>";
            html = html + "</tr>";
            for (String country : countries) {
                
                html = html + "<tr>";
                html = html + "<td>" + country +  "</td>";
                html = html + "<td>" + caseswtol.get(i)  + "</td>";
                html = html + "<td>" + caseslwwtol.get(i)+ "</td>";
                html = html + "</tr>";
                i = i + 1;
            }
            html = html + "</table>";
        }
        return html;
    }

    public String outputStateTotalCasesWorstToLeast(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countStateTotalCasesWorstToLeast(name);
        
        
        // Add HTML for the movies list
        html = html + "<div class='pgtwostatetotalcases'>";
        html = html + "<ul>";
        for (Integer ab : cases) {
            html = html + "<ul>" + ab + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }

    public String outputStateLastWeekWorstToLeast(String name) {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countStateLastWeekWorstToLeast(name);
        
        // Add HTML for the movies list
        html = html + "<div class='pgtwostatelastweek'>";
        html = html + "<ul>";
        for (Integer cd : cases) {
            html = html + "<ul>" + cd + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }



   

    


}
