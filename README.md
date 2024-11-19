# BackEnd do App MathSchool 
# Crie um projeto no firebase apos isso va em configurações do projeto ,contas do serviço e gerar nova chave privada,renomeie o arquivo baixado para firebase-key.json e o transfira para a pasta resources do projeto.

# troque na classe FirebaseConfig o bucket para o ser bucket criado com o serviço storage
FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("seu-bucket.appspot.com")
                .build();
