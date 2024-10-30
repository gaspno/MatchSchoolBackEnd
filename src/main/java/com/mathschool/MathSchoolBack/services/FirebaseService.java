package com.mathschool.MathSchoolBack.services;

import com.google.cloud.storage.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;


@Service
public class FirebaseService {
    public String uploadFile(MultipartFile multipartFile, String folder) throws IOException {

        InputStream inputStream=multipartFile.getInputStream();
        Bucket bucket= StorageClient.getInstance().bucket();
        Blob blobInfo = bucket.create(folder+"/"+multipartFile.getOriginalFilename(),inputStream,multipartFile.getContentType());
        return  blobInfo.getName();
    }
    public FirebaseToken getTokenValid(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        if (decodedToken == null) {
            return null;
        }
        return decodedToken;

    }
}

