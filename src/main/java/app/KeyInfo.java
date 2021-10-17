package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples. This page currently: - Provides
 * a link back to the index page - Displays the list of movies from the Movies
 * Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class KeyInfo implements Handler {

        // URL of this page relative to http://localhost:7000/
        public static final String URL = "/key-info.html";

        @Override
        public void handle(Context context) throws Exception {
                // Create a simple HTML webpage in a String
                String html = "<!DOCTYPE html><html lang='en'><head> <meta charset='UTF-8'> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta name='viewport' content='width=device-width, initial-scale=1.0'> <title>Key Info</title> <!-- FontAwesome CDN For App Icon --> <script src='https://kit.fontawesome.com/3f16fb12e4.js' crossorigin='anonymous'></script> <!-- Custom styles --> <link rel='stylesheet' href='app.css'> <!-- Bootstrap CDN Link --> <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head><body> <header> <nav class='navbar navbar-expand-lg navbar-light bg-light '> <div class='container-fluid'> <a class='navbar-brand' href='/'>Spreading Info</a> <button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'> <span class='navbar-toggler-icon'></span> </button> <div class='collapse navbar-collapse' id='navbarSupportedContent'> <ul class='navbar-nav ms-auto mb-2 mb-lg-0'> <li class='nav-item me-4'> <a class='nav-link' aria-current='page' href='/'>Home</a> </li> <li class='nav-item me-4'> <a class='nav-link active' href='key-info.html'>Key Info</a> </li> <li class='nav-item dropdown me-4'> <a class='nav-link dropdown-toggle' id='navbarDropdown' role='button' data-bs-toggle='dropdown' aria-expanded='false'> Country Data </a> <ul class='dropdown-menu' aria-labelledby='navbarDropdown'> <li><a class='dropdown-item' href='country-data-cases.html'>Cases</a></li> <li><a class='dropdown-item' href='country-data-deaths.html'>Deaths</a></li> </ul> </li> <li class='nav-item me-4'> <a class='nav-link' href='trends.html'>Trends</a> </li> <li class='nav-item'> <a class='nav-link' href='compare.html'>Compare</a> </li> </ul> </div> </div> </nav> </header>";

                String[] topStoriesImgLink = {
                                "https://i.guim.co.uk/img/media/9fbc1e5c856311e2679063d97c6f87606565b403/0_100_3000_1800/master/3000.jpg?width=620&quality=45&auto=format&fit=max&dpr=2&s=c6b81504be3c420b368eda44d03977ab",
                                "https://ichef.bbci.co.uk/news/976/cpsprodpb/16BB3/production/_118870139_vaccinationcentretiruvannamalai.jpg",
                                "https://static.ffx.io/images/$zoom_0.159%2C$multiply_0.4431%2C$ratio_1.5%2C$width_756%2C$x_0%2C$y_0/t_crop_custom/q_86%2Cf_auto/d82b0c2d19eabaae30c983be6bd691ff8f47af31",
                                "https://static01.nyt.com/images/2021/06/16/science/00virus-pill2/merlin_188625387_cc66deef-2f70-46a9-8523-b84462118fa5-articleLarge.jpg?quality=75&auto=webp&disable=upscale",
                                "https://api.time.com/wp-content/uploads/2021/06/Afghanistan.jpg?w=800&quality=85",
                                "https://img.etimg.com/thumb/msid-83706225,width-300,imgsize-361178,,resizemode-4,quality-100/.jpg",
                                "https://images.theconversation.com/files/404506/original/file-20210604-21-8ba1qb.jpg?ixlib=rb-1.1.0&rect=27%2C68%2C4544%2C2940&q=45&auto=format&w=754&fit=clip",
                                "https://img.etimg.com/thumb/msid-83692877,width-300,imgsize-50119,,resizemode-4,quality-100/covid-vaccine1r.jpg",
                                "https://i.guim.co.uk/img/media/6f3d63a20831e0e175e0a85585268728750dd030/0_0_3504_2102/master/3504.jpg?width=445&quality=45&auto=format&fit=max&dpr=2&s=0ed106b4eecc046d703b7cc70c51dab2",
                                "https://i.guim.co.uk/img/media/0cb1c6f25ea41b3893a647a8b4c9299ae894be39/0_31_3500_2100/master/3500.jpg?width=445&quality=45&auto=format&fit=max&dpr=2&s=5cc7f13a7e49ee0e520e0165597bb7ec",
                                "https://a57.foxnews.com/cf-images.us-east-1.prod.boltdns.net/v1/static/694940094001/dc8c058d-d952-4443-987f-1a6684cced8a/7bcc5c2b-cb38-43aa-90c1-d6181a65f7e8/1280x720/match/931/524/image.jpg?ve=1&tl=1",
                                "https://ichef.bbci.co.uk/news/976/cpsprodpb/12EAF/production/_120878477_brazil.png" };
                String[] topStoriesLink = {
                                "https://www.theguardian.com/australia-news/live/2021/oct/16/australia-covid-live-news-update-tasmania-lockdown-begins-as-pm-and-perrottet-at-odds-over-borders",
                                "https://www.bbc.com/news/world-asia-india-57400620",
                                "https://www.theage.com.au/national/victoria/one-new-coronavirus-case-as-melburnians-head-to-the-regions-20210619-p582ea.html",
                                "https://www.nytimes.com/2021/06/17/health/covid-pill-antiviral.html",
                                "https://time.com/6074378/afghanistan-covid19/",
                                "https://economictimes.indiatimes.com/news/international/world-news/covid-19-leads-to-cognitive-behavioural-problems-in-patients-study/articleshow/83706225.cms",
                                "https://theconversation.com/covid-19-recovery-some-economies-will-take-longer-to-rebound-this-is-bad-for-everyone-162023",
                                "https://economictimes.indiatimes.com/news/international/world-news/china-says-one-billion-covid-19-vaccine-doses-administered/articleshow/83692840.cms",
                                "https://www.theguardian.com/australia-news/2021/jun/21/covid-19-list-queensland-public-exposure-sites-hotspots-brisbane-regional-south-east-qld-sunshine-coast-caloundra-toowoomba-venues-case-location-alerts-metro-coronavirus-cases-outbreak-locations",
                                "https://www.theguardian.com/australia-news/2021/jun/21/covid-border-restrictions-travel-victoria-vic-nsw-qld-queensland-wa-sa-where-you-can-not-go-australia",
                                "https://www.foxnews.com/health/smoking-marijuana-could-lead-to-breakthrough-covid-cases-study-finds",
                                "https://www.bbc.com/news/health-58170809" };
                String[] topStoriesName = {
                                "NSW hits 80% vaccination target; Victoria’s seven deaths include 15-year-old girl – as it happened",
                                "India's vaccine drive: Stories from the best and worst districts",
                                "Virus ‘absolutely still out there’ as Victoria records one new COVID-19 case",
                                "A Pill to Treat Covid-19? The U.S. Is Betting on It.",
                                "Afghanistan Running Out of Oxygen as COVID-19 Surge Worsens",
                                "COVID-19 leads to cognitive, behavioural problems in patients: Study",
                                "COVID-19 recovery: some economies will take longer to rebound – this is bad for everyone",
                                "China says one billion COVID-19 vaccine doses administered",
                                "Queensland Covid-19 exposure sites: list of Qld coronavirus hotspots and case location alerts",
                                "Covid travel restrictions: where you can and can’t go within Australia",
                                "Smoking marijuana could lead to breakthrough COVID cases, study finds",
                                "Ivermectin: How false science created a Covid 'miracle' drug" };
                String[] topStoriesTimestamp = { "1 hour ago", "2 hours ago", "3 days ago", "5 days ago", "4 days ago",
                                "5 hours ago", "14 days ago", "2 days ago", "19 days ago", "18 hours ago", "1 day ago",
                                "5 days ago" };

                html = html + "<main class='container my-5'> <section class='mb-5'> <h2>Global Information</h2> <div class='row row-cols-1 row-cols-md-1 row-cols-lg-3 g-4'> <div class='col'> <div class='p-5' style='background-color: #ECEFF1;'> <h1 class='fw-bold'>91,439,210</h1> <h4>Overall cases in the US</h4> </div> </div> <div class='col'> <div class='p-5' style='background-color: #ECEFF1;'> <h1 class='fw-bold'>86,013</h1> <h4>Global Deaths Last Week</h4> </div> </div> <div class='col'> <div class='p-5' style='background-color: #ECEFF1;'> <h1 class='fw-bold'>86,013</h1> <h4>Global Deaths Last Week</h4> </div> </div> </div> </section> <section> <h2 style='margin-top: 50px;'>Most Affected Countries</h2> <div class='row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4'> <div class='col'> <div class='card h-100'> <img src='https://www.businessinsider.in/thumb/msid-76275944,width-700,height-525,imgsize-410050/the-united-states-tops-34174752-coronavirus-cases.jpg' class='card-img-top' alt='...'> <div class='card-body'> <a target='_blank' href='https://www.usa.gov/coronavirus' class='card-title'>The United States tops 34,174,752 coronavirus cases</a> <p class='card-text'>The United States on Thursday recorded 34,174,752 novel coronavirus cases, according to Johns Hopkins University. The pandemic has now claimed the lives of at least 611,611 people in the United States, which leads the world in the number of confirmed infections.</p> </div> </div> </div> <div class='col'> <div class='card h-100'> <img src='https://www.businessinsider.in/thumb/msid-76276067,width-700,height-525,imgsize-143546/india-is-now-the-second-worst-affected-country-by-covid-19.jpg' class='card-img-top' alt='...'> <div class='card-body'> <a target='_blank' href='https://www.mohfw.gov.in/' class='card-title'>India is now the second worst-affected country by COVID-19</a> <p class='card-text'>India's novel Coronavirus tally crossed the 28,574,350 mark on Thursday the Indian Health Ministry said. The state of Maharashtra, Kerala, Karnataka, Uttar Pradesh, and Tamil Nadu were among the five worst-affected states in India.</p> </div> </div> </div> <div class='col'> <div class='card h-100'> <img src='https://www.businessinsider.in/thumb/msid-76275973,width-700,height-525,imgsize-77842/brazil-with-more-than-16803472-cases.jpg' class='card-img-top' alt='...'> <div class='card-body'> <a target='_blank' href='https://www.bbc.com/news/world-latin-america-58891004' class='card-title'>Brazil with more than 16,803,472 cases</a> <p class='card-text'>Second, in the list of the worst affected countries, Brazil has more than 16.8 million confirmed cases, according to Johns Hopkins University. The country has so far reported 16,803,472 total cases and 469,784 deaths due to coronavirus.</p> </div> </div> </div> </div> </section><h2 style='margin-top: 50px;'>Latest Updates</h2><article class='row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4'>";

                // html = html + "<div class='row row-cols-1 row-cols-lg-3 g-4'>";
                for (int i = 0; i < topStoriesImgLink.length; i++) {
                        html = html + "<div class='col'> <div class='card h-100'> <img src='" + topStoriesImgLink[i]
                                        + "' class='card-img-top img-fluid' alt='...'> <div class='card-body'> <a target='_blank' href='"
                                        + topStoriesLink[i] + "' class='card-title'>" + topStoriesName[i]
                                        + "</a> </div> <div class='card-footer'> <small class='text-muted'>Last updated "
                                        + topStoriesTimestamp[i] + "</small> </div> </div> </div>";
                }

                html = html + "</article></main>";

                // Finish the HTML webpage
                html = html + " <!-- Footer --> <footer class='text-center text-white bg-dark'> <!-- Grid container --> <div class='container p-4 pb-0'> <!-- Section: CTA --> <section class=''> <p class='d-flex justify-content-center align-items-center'> <span class='me-3'>Register for free</span> <button type='button' class='btn btn-outline-light btn-rounded'> Sign up! </button> </p> </section> <!-- Section: CTA --> </div> <!-- Grid container --> <!-- Copyright --> <div class='text-center p-3' style='background-color: rgba(0, 0, 0, 0.2);'> © 2021 Copyright : Application Developed By Saalim Shadman, A Computer Science Student at RMIT Australia. </div> <!-- Copyright --> </footer> <!-- Footer --> <!--- Bootstrap Bundle Link --> <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js' integrity='sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p' crossorigin='anonymous'></script> <!-- Swiper JS --> <script src='https://unpkg.com/swiper/swiper-bundle.min.js'></script> <!-- Initialize Swiper --> <script> var swiper = new Swiper('.mySwiper', { slidesPerView: 1, spaceBetween: 10, pagination: { el: '.swiper-pagination', clickable: true, }, breakpoints: { 640: { slidesPerView: 2, spaceBetween: 20, }, 768: { slidesPerView: 4, spaceBetween: 40, }, 1024: { slidesPerView: 4, spaceBetween: 50, }, }, }); </script></body></html>";

                // DO NOT MODIFY THIS
                // Makes Javalin render the webpage
                context.html(html);
        }

}
