package com.serrano.app.helpdesk.utils;

public class EmailBuilder {
    
    public static String build(String name, String link){
        return 
        "<div style=\"font-family: sans-serif;width: 100%;font-size: 16px;\"> \n" +
        "   <div><h1>Hello, " + name + "</h1><div>We're happy you signed up. <p style=\"margin: 0;\">To start exploring the app, please confirm your email address</p></div></div> \n" +
        "   <div style=\"margin: 20px 0px;width: 100%;display: flex;justify-content: center;align-content: center;align-items: center;\"> \n" +
        "       <a href=\"" + link + "\" style=\"background-color: #0928a2;text-decoration: none;color: white;padding: 10px;border-radius: 10px;font-weight: bold;/*! font-size: 20px; */\">Verify now</a> \n" +
        "   </div> \n" +
        "   <div> \n" +
        "       <p style=\"border-left: 5px solid #fb7600;padding-left: 10px;\">This verification link will expire in 1 minute</p> \n" +
        "   </div> \n" +
        "</div>";
    }
}
