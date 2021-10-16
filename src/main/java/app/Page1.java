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
public class Page1 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page1.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Covid Updates</title>";

        // Add some CSS (external file)
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>";
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&display=swap' rel='stylesheet'>'";
        html = html + "<link rel='stylesheet' type='text/css' href='covid.css' />";
        html = html + "<link rel='preconnect' href='https://fonts.gstatic.com'>"; 
        html = html + "<link href='https://fonts.googleapis.com/css2?family=Open+Sans&family=Poppins:wght@300&display=swap' rel='stylesheet'  d>";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>";

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

        html = html + "<h1 class='up'>COVID UPDATES</h1>";


        html = html + "<div class= 'ra'>";
        html = html + "<a class='record' href=''>Record a case</a>";
        html = html + "</div>";

        html = html + "<div class='white'>" + "</div>";
        html = html + "<div class='whitetwo'>" +"</div>";
        
        html = html + "<div class='l'>";
        html = html + "<p class='counter'>Live Counter</p>" + "</div>";
        html = html + "<table id ='tableTwo'>";
        html = html + "<tr>";
        html = html + "<th >Coronavirus Cases</th>";
        html = html + "</tr>";
        html = html + "<tr>";
        html = html + "<td class='o'>160,074,267</td>";
        html = html + "</tr>";
        html = html + "<tr>";
        html = html + "<th >Deaths</th>";
        html = html + "</tr>";
        html = html + "<tr>";
        html = html + "<td class='o'>3,352,620</td>";
        html = html + "</tr>";
        html = html + "<tr>";
        html = html + "<th >Recovered</th>";
        html = html + "</tr>";
        html = html + "<tr>";
        html = html + "<td class='o'>138,261,612</td>";
        html = html + "</tr>";
        html = html + "</table>";

        /*html = html + "<div class='ltwo'>";
        html = html + "<p class='pageoneone'>Coronavirus Cases</p>";
        html = html + "<p class='pageonetwo'>160,074,267</p>";
        html = html + "<p class='pageonethree'>Deaths</p>";
        html = html + "<p class='pageonefour'>3,352,620</p>";
        html = html + "<p class='pageonefive'>Recovered</p>";
        html = html + "<p class='pageonesix'>138,261,612</p>";
        html = html + "</div>";*/

        html = html + "<h2 class='affect'>Most affected Countries</h2>";
        html = html + "<h2 class='usa'>USA</h2>";
        html = html + "<div class='usad'>" ;
        html = html + "<p>The United States on Tuesday recorded</p>" +
        "<p>32,229,327 novel coronavirus cases,</p>" + 
        "<p>according to Johns Hopkins University.</p>" +
        "<p>The pandemic has now claimed the lives</p>" +
        "<p>of at least 578,407 people in the United</p>" +
        "<p>States, which leads the world in the number</p>" +
        "<p>of confirmed infections.</p>";
        html = html + "</div>";
        html = html + "<img class='usastat' src='usastat.png' />";

        html = html + "<h2 class='india'>India</h2>";
        html = html + "<div class='indiad'>" ;
        html = html + "<p>India's novel Coronavirus tally crossed </p>" +
        "<p>the 20.2 million mark on Thursday with</p>" + 
        "<p>detection of 3,82,315 new cases in</p>" +
        "<p>the last 24 hours,the Indian</p>" +
        "<p>Health Ministry said.</p>";
        html = html + "</div>";
        html = html + "<img class='indiastat' src='indiastat.jpeg' />";

        html = html + "<h2 class='brazil'>Brazil</h2>";
        html = html + "<div class='brazild'>" ;
        html = html + "<p>Brazil has more than 14.8 million confirmed</p>" +
        "<p>cases, according to Johns Hopkins </p>" + 
        "<p>University.The country has so far reported</p>" +
        "<p>14,856,888 total cases and 411,588 deaths</p>" +
        "<p>due to coronavirus.</p>";
        html = html + "</div>";
        html = html + "<img class='brazilstat' src='brazilstat.png' />";


        html = html + "<a class='rzone' href=''>Red Zones</a>";
        html = html + "<a class='gzone' href=''>Green Zones</a>";
        html = html + "<a class='impact' href=''>Impact of Covid</a>";
        html = html + "<a class='more' href=''>Find Out More</a>";
   



       


        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
