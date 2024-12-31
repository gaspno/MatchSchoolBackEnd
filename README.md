# BackEnd do App MathSchool 
# Crie um projeto no firebase apos isso va em configurações do projeto ,contas do serviço e gerar nova chave privada,renomeie o arquivo baixado para firebase-key.json e o transfira para a pasta resources do projeto.

# troque na classe FirebaseConfig o bucket para o ser bucket criado com o serviço storage
FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("seu-bucket.appspot.com")
                .build();
<img width="941" alt="Captura de Tela (54)" src="https://github.com/user-attachments/assets/8d435faa-499d-4f7a-88ca-d2eb6a69b38f" />
<img width="948" alt="Captura de Tela (55)" src="https://github.com/user-attachments/assets/57c35148-0958-4858-8b04-7377f3f6ab92" />
<img width="965" alt="Captura de Tela (46)" src="https://github.com/user-attachments/assets/6546bb10-2d46-4538-afc6-2c8511b4fea6" />
<img width="948" alt="Captura de Tela (48)" src="https://github.com/user-attachments/assets/528e985b-829a-480b-8c3b-5a1d65e19249" />
<img width="957" alt="Captura de Tela (49)" src="https://github.com/user-attachments/assets/0ee601d5-ddf4-40bf-8b50-4bf8edf67593" />
<img width="957" alt="Captura de Tela (50)" src="https://github.com/user-attachments/assets/2f165882-f90e-44f3-ab4e-825b9737f91a" />
<img width="957" alt="Captura de Tela (51)" src="https://github.com/user-attachments/assets/1ee4b3b2-933f-4213-a48d-0d8f0bd377ee" />
<img width="955" alt="Captura de Tela (52)" src="https://github.com/user-attachments/assets/289f88fa-ebdf-46ef-84ee-7eabfef5c4bb" />
<img width="954" alt="Captura de Tela (53)" src="https://github.com/user-attachments/assets/29e9a4b5-3670-4e24-b5e9-954f12acf92d" />
