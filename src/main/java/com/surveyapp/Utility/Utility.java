package com.surveyapp.Utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return "http://localhost:3000"; //siteURL.replace(request.getServletPath(),"");
    }
}
