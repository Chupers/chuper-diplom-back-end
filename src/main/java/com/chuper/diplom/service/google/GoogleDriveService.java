package com.chuper.diplom.service.google;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.client.auth.oauth2.Credential;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleDriveService {
    private static final String APPLICATION_NAME = "Google Drive API";
    private static final JacksonFactory JACKSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIAL_FILE_PATH = "/client_secret2.json";
    public static  String GOOGLE_PATH = "https://drive.google.com/uc?export=view&id=";
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException{
        InputStream inputStream = GoogleDriveService.class.getResourceAsStream(CREDENTIAL_FILE_PATH);
        if(inputStream == null){
            throw new FileNotFoundException();
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JACKSON_FACTORY,new InputStreamReader(inputStream));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT,JACKSON_FACTORY,clientSecrets,SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow,receiver).authorize("user");
    }

    public static com.google.api.services.drive.model.File saveImage(MultipartFile multipart)
            throws IOException, GeneralSecurityException{
        final NetHttpTransport HTT_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTT_TRANSPORT,JACKSON_FACTORY,getCredentials(HTT_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        com.google.api.services.drive.model.File file = new com.google.api.services.drive.model.File();
        file.setName(multipart.getOriginalFilename());
        File convFile = new File(multipart.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(multipart.getBytes());
        fileOutputStream.close();
        FileContent mediaContent = new FileContent("image/jpeg",convFile);
        return service.files().create(file,mediaContent)
                .setFields("id")
                .execute();
    }
}
