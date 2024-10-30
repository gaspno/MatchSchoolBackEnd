package com.mathschool.MathSchoolBack.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp init() throws IOException {
        InputStream serviceAccount =
                 getClass().getClassLoader().getResourceAsStream("firebase-key.json");


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("mathschool-949a9.appspot.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}