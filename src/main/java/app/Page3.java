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
public class Page3 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page3.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Deaths</title>";

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

        
        html = html + "<a class = 'deaths' href='page3.html'>Deaths</a>";
        html = html + "<a class = 'data' href='page4.html'>Data</a>";
        html = html + "<a class = 'analysis' href='page5.html'>Analysis</a>";
        html = html + "<a class = 'about' href='page6.html'>About Us</a>";
        html = html + "</div>";

 
        html = html + "<div id='text' style='display:none'>";
        html = html + outputCountryNameWorstToLeast();
        /*html = html + outputCountryTotalCasesAllCountriesWorstToLeast();
        html = html + outputCountryTotalDeathsAllCountriesWorstToLeast();
        html = html + outputCountryCasesLastWeekWorstToLeast();
        html = html + outputCountryDeathsLastWeekWorstToLeast();
        html = html + outputCountryDIRWorstToLeast();*/
        html = html + "</div>";



        html = html + "<div id='textTwo' style='display:block'>";
        html = html + outputCountryName();
        /*html = html + outputCountryTotalCasesAllCountries();
        html = html + outputCountryTotalDeathsAllCountries();
        html = html + outputCountryCasesLastWeek();
        html = html + outputCountryDeathsLastWeek();
        html = html + outputCountryDIR();*/
        html = html + "</div>";


        html = html + "<div id='dir' style='display:none'>";
        html = html + outputCountryNameDIR();
        /*html = html + outputCountryTotalCasesAllCountriesDIR();
        html = html + outputCountryTotalDeathsAllCountriesDIR();
        html = html + outputCountryCasesLastWeekDIR();
        html = html + outputCountryDeathsLastWeekDIR();
        html = html + outputCountryDIRDIR();*/
        html = html + "</div>";



       


        html = html + "<h2 class='countryhead'>Deaths Worldwide</h2>";
        html = html + "<div class='pgthreeviewbar' id='js'>";
        html = html + "<div class='pgthreeviewone'>";
        html = html + "<label for='checkboxviewone'><i>View by highest deaths to lowest</label></i>" + "</div>";
        html = html + "<input  name='checkboxviewone' type='checkbox' class='pgthreeviewonebox' id='checkboxviewone' onclick='myFunctionView()''></input>";

        html = html + "<div class='pgthreeviewtwo'>";
        html = html + "<label for='checkboxviewtwo'><i>View by highest (death-infection ratio) to lowest </label></i>" + "</div>";
        html = html + "<input  name='checkboxviewtwo' type='checkbox' class='pgthreeviewtwobox' id='checkboxviewtwo' onclick='myFunctionView()''></input>";
        html = html + "<h3 class = 'pgthreeNote'><b>Note : </b>  Click one checkbox at a time</h3>";
        html = html + "</div>";

        /*html = html + "<div class='pgthreedataback'>";
        html = html + "</div>";*/

        html = html + "<script>";
        html = html + "function myFunctionView() {";
        html = html + "var checkBoxOne = document.getElementById('checkboxviewone');";
        html = html + "var checkBoxTwo = document.getElementById('checkboxviewtwo');";
        html = html + "var text = document.getElementById('text');var hello = document.getElementById('textTwo');";
        html = html + "var dir = document.getElementById('dir');";

        html = html + "if (checkBoxOne.checked == true && checkBoxTwo.checked == false){    text.style.display = 'block';hello.style.display = 'none';dir.style.display = 'none';}";
        html = html + "else if(checkBoxOne.checked == false && checkBoxTwo.checked == true) {    dir.style.display = 'block';text.style.display = 'none';hello.style.display = 'none';}";
        html = html + "else if(checkBoxOne.checked == false && checkBoxTwo.checked == false) {    hello.style.display = 'block';text.style.display = 'none';dir.style.display = 'none';}";
        html = html + "else{ dir.style.display = 'none';text.style.display = 'none';hello.style.display = 'none';}}";
        html = html + "</script>";


      

        /*html = html + "<div class='pgthreeBlack'>";
        html = html + "<h4 class='c'>Country</h4>";
        html = html + "<h4 class='tc'>Total Cases</h4>";
        html = html + "<h4 class='td'>Total Deaths</h4>";
        html = html + "<h4 class='clw'>Cases Last Week</h4>";
        html = html + "<h4 class='dlw'>Deaths Last Week</h4>";
        html = html + "<h4 class='dir'>Deaths-Infection Ratio</h4>";
        html = html + "</div>";
        html = html + "<div class='pgthreewhite'></div>";*/

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputCountryName() {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryList();
        ArrayList<Integer> casesTotal = jdbc.countTotalCasesAllCountries();
        ArrayList<Integer> deathsTotal = jdbc.countTotalDeathsAllCountries();
        ArrayList<Integer> casesLastWeek = jdbc.countCountryCasesLastWeekAll();
        ArrayList<Integer> deathsLastWeek = jdbc.countCountryDeathsLastWeekAll();
        ArrayList<Double> ratios = jdbc.countDIR();
        
        // Add HTML for the movies list
        int i =0;
        html = html + "<table class = 'threeDataTable'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Cases</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Cases Last Week</th>";
        html = html + "<th id=''>Deaths Last Week</th>";
        html = html + "<th id=''>Death Infection Ratio</th>";
        html = html + "</tr>";
        for (String country : countries) {
            html = html + "<tr>";
            html = html + "<td>" + country + "</td>";
            html = html + "<td>" + casesTotal.get(i)  + "</td>";
            html = html + "<td>" + deathsTotal.get(i)  + "</td>";
            html = html + "<td>" + casesLastWeek.get(i)  + "</td>";
            html = html + "<td>" + deathsLastWeek.get(i)  + "</td>";
            html = html + "<td>" + ratios.get(i)  + "</td>";
            html = html + "</tr>";
            i = i + 1;
        }
        html = html + "</table>";
       

        return html;
    }
    

    public String outputCountryTotalCasesAllCountries() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countTotalCasesAllCountries();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreetotalcaseslist'>";
        html = html + "<ul>";
        for (Integer abc : cases) {
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryTotalDeathsAllCountries() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> deaths = jdbc.countTotalDeathsAllCountries();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreetotaldeathslist'>";
        html = html + "<ul>";
        for (Integer death : deaths) {
            html = html + "<ul>" + death + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryCasesLastWeek() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countCountryCasesLastWeekAll();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreecountrylastweek'>";
        html = html + "<ul>";
        for (Integer cd : cases) {
            html = html + "<ul>" + cd + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }

    public String outputCountryDeathsLastWeek() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countCountryDeathsLastWeekAll();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreecountrylastweekd'>";
        html = html + "<ul>";
        for (Integer cda : cases) {
            html = html + "<ul>" + cda + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryNameWorstToLeast() {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryListWorstToLeast();
        ArrayList<Integer> casesTotal = jdbc.countTotalCasesAllCountriesWorstToLeast();
        ArrayList<Integer> deathsTotal = jdbc.countTotalDeathsAllCountriesWorstToLeast();
        ArrayList<Integer> casesLastWeek = jdbc.countCountryCasesLastWeekAllWorstToLeast();
        ArrayList<Integer> deathsLastWeek = jdbc.countCountryDeathsLastWeekAllWorstToLeast();
        ArrayList<Double> ratios = jdbc.countDIRWorstToLeast();
        
        // Add HTML for the movies list
        int i =0;
        html = html + "<table class = 'threeDataTable'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Cases</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Cases Last Week</th>";
        html = html + "<th id=''>Deaths Last Week</th>";
        html = html + "<th id=''>Death Infection Ratio</th>";
        html = html + "</tr>";
        for (String country : countries) {
            html = html + "<tr>";
            html = html + "<td>" + country + "</td>";
            html = html + "<td>" + casesTotal.get(i)  + "</td>";
            html = html + "<td>" + deathsTotal.get(i)  + "</td>";
            html = html + "<td>" + casesLastWeek.get(i)  + "</td>";
            html = html + "<td>" + deathsLastWeek.get(i)  + "</td>";
            html = html + "<td>" + ratios.get(i)  + "</td>";
            html = html + "</tr>";
            i = i + 1;
        }
        html = html + "</table>";
        
        return html;
    }
    

    public String outputCountryTotalCasesAllCountriesWorstToLeast() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countTotalCasesAllCountriesWorstToLeast();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreetotalcaseslist'>";
        html = html + "<ul>";
        for (Integer abc : cases) {
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryTotalDeathsAllCountriesWorstToLeast() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> deaths = jdbc.countTotalDeathsAllCountriesWorstToLeast();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreetotaldeathslist'>";
        html = html + "<ul>";
        for (Integer death : deaths) {
            html = html + "<ul>" + death + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryCasesLastWeekWorstToLeast() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countCountryCasesLastWeekAllWorstToLeast();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreecountrylastweek'>";
        html = html + "<ul>";
        for (Integer cd : cases) {
            html = html + "<ul>" + cd + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }

    public String outputCountryDeathsLastWeekWorstToLeast() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countCountryDeathsLastWeekAllWorstToLeast();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreecountrylastweekd'>";
        html = html + "<ul>";
        for (Integer cda : cases) {
            html = html + "<ul>" + cda + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }

    public String outputCountryDIR(){
        String html = "";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> ratios = jdbc.countDIR();

        html = html + "<div class='pgthreecountryDIR'>";
        html = html + "<ul>";
        for (Double cda : ratios) {
            html = html + "<ul>" + cda + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;

    }

    public String outputCountryDIRWorstToLeast(){
        String html = "";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> ratios = jdbc.countDIRWorstToLeast();

        html = html + "<div class='pgthreecountryDIR'>";
        html = html + "<ul>";
        for (Double cda : ratios) {
            html = html + "<ul>" + cda + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;

    }
    public String outputCountryNameDIR() {
        String html = "";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> countries = jdbc.getCountryListDIR();
        ArrayList<Integer> casesTotal = jdbc.countTotalCasesAllCountriesDIR();
        ArrayList<Integer> deathsTotal = jdbc.countTotalDeathsAllCountriesDIR();
        ArrayList<Integer> casesLastWeek = jdbc.countCountryCasesLastWeekAllDIR();
        ArrayList<Integer> deathsLastWeek = jdbc.countCountryDeathsLastWeekAllDIR();
        ArrayList<Double> ratios = jdbc.countDIRDIR();
        
        // Add HTML for the movies list
        int i =0;
        html = html + "<table class = 'threeDataTable'>";
        html = html + "<tr>";
        html = html + "<th id=''>Country</th>";
        html = html + "<th id =''>Total Cases</th>";
        html = html + "<th id=''>Total Deaths</th>";
        html = html + "<th id=''>Cases Last Week</th>";
        html = html + "<th id=''>Deaths Last Week</th>";
        html = html + "<th id=''>Death Infection Ratio</th>";
        html = html + "</tr>";
        for (String country : countries) {
            html = html + "<tr>";
            html = html + "<td>" + country + "</td>";
            html = html + "<td>" + casesTotal.get(i)  + "</td>";
            html = html + "<td>" + deathsTotal.get(i)  + "</td>";
            html = html + "<td>" + casesLastWeek.get(i)  + "</td>";
            html = html + "<td>" + deathsLastWeek.get(i)  + "</td>";
            html = html + "<td>" + ratios.get(i)  + "</td>";
            html = html + "</tr>";
            i = i + 1;
        }
        html = html + "</table>";

        return html;
    }
    

    public String outputCountryTotalCasesAllCountriesDIR() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countTotalCasesAllCountriesDIR();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreetotalcaseslist'>";
        html = html + "<ul>";
        for (Integer abc : cases) {
            html = html + "<ul>" + abc + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryTotalDeathsAllCountriesDIR() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> deaths = jdbc.countTotalDeathsAllCountriesDIR();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreetotaldeathslist'>";
        html = html + "<ul>";
        for (Integer death : deaths) {
            html = html + "<ul>" + death + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryCasesLastWeekDIR() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countCountryCasesLastWeekAllDIR();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreecountrylastweek'>";
        html = html + "<ul>";
        for (Integer cd : cases) {
            html = html + "<ul>" + cd + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }

    public String outputCountryDeathsLastWeekDIR() {
        String html = "";
        

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> cases = jdbc.countCountryDeathsLastWeekAllDIR();
        
        // Add HTML for the movies list
        html = html + "<div class='pgthreecountrylastweekd'>";
        html = html + "<ul>";
        for (Integer cda : cases) {
            html = html + "<ul>" + cda + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;
    }
    public String outputCountryDIRDIR(){
        String html = "";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Double> ratios = jdbc.countDIRDIR();

        html = html + "<div class='pgthreecountryDIR'>";
        html = html + "<ul>";
        for (Double cda : ratios) {
            html = html + "<ul>" + cda + "</ul>";
        }
        html = html + "</ul>";
        html = html + "</div>";
        return html;

    }
    

    

}
