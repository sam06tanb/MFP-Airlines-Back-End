package com.example.mfpairlines;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MfpAirlinesApplication {
	public static void main(String[] args) throws IOException {
		connection();
		SpringApplication.run(MfpAirlinesApplication.class, args);
	}

	static Firestore bd;

	public static void connection() throws IOException {
		if (FirebaseApp.getApps().isEmpty()) {
			FileInputStream serviceAccount = new FileInputStream("key.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://mfpp-6a018.firebaseio.com/")
					.build();
			FirebaseApp.initializeApp(options);
			bd = FirestoreClient.getFirestore();
			System.out.println("Firestore client connected");
		} else {
			bd = FirestoreClient.getFirestore();
			System.out.println("Firestore client already initialized");
		}
	}
}
