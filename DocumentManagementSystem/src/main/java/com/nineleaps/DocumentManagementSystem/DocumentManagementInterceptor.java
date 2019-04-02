//package com.nineleaps.DocumentManagementSystem;
//
//import com.nineleaps.DocumentManagementSystem.dto.TokenRequestedData;
//import com.nineleaps.DocumentManagementSystem.exceptions.SignInInvalidTokenError;
//import com.nineleaps.DocumentManagementSystem.exceptions.SignInUserDataNotFound;
//import com.nineleaps.DocumentManagementSystem.repository.EmployeeAccountsRepository;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//@CrossOrigin
//@Component
//public class DocumentManagementInterceptor implements HandlerInterceptor {
//    @Autowired
//    EmployeeAccountsRepository employeeAccountsRepo;
//    @Autowired
//    TokenRequestedData tokenRequestedData;
//
//    @Override
//    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
//
//        String tokenId = req.getHeader("tokenId");
//        System.out.println("TOKENID " + req.getHeaders(tokenId));
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpGet request = new HttpGet("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + tokenId);
//        CloseableHttpResponse response = null;
//        response = client.execute(request);
//        int status = response.getStatusLine().getStatusCode();
//        if (!(status >= 200 && status < 300)) {
//            System.out.println("Unexpected response status: " + status);
//            //GIVES ERROR IF THE TOKEN IS INVALID
//
//            throw new SignInInvalidTokenError("the token provided is INVALID");
//        }
//
//        HttpEntity entity = response.getEntity();
//        String responseString = EntityUtils.toString(entity, "UTF-8");
//        System.out.println(responseString);
//
//        //VALIDATING THE USER INFORMATION PROVIDED IN THE TOKEN
//
//        JSONObject json = (JSONObject) new JSONParser().parse(responseString);
//        String email = (String) json.get("email");
//        String googleid = (String) json.get("sub");
//        String username = (String) json.get("sub");
//        System.out.println(email);
//        System.out.println(googleid);
//
//        boolean value = employeeAccountsRepo.existsByEmailId(email);
//        System.out.println(value);
//
//        if (value == false) {
//            throw new SignInUserDataNotFound("The User Records is not available in the database");
//        }
//        tokenRequestedData.setGoogleId(googleid);
//        tokenRequestedData.setUserEmail(email);
//        tokenRequestedData.setUserName(username);
//
//        return value;
//
//
//    }
//
//
//}
