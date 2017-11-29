# RentACar

### Követelmények:
- Maven
- Basex (http://basex.org/products/download/all-downloads/)

### Fordítás:
mvn clean package

### Futtatási előkövetelmény:
1. A basex adatbázis kezelő rendszert el kell indítani: (startmenüben basex server (start)-re kell keresni).
2. Ahhoz, hogy létrejöjjön a rendacardb adatbázis ki kell kommentezni a RentACarMain.java, initDB(); sorát.

### Futtatás:
java -jar target/RentACar.jar

### Elérés:
http://localhost:8888/ böngészőben

### XML módosulás esetén:
Ki kell kommentezni a RentACarMain.java, reinitDB(); sorát, és újraindítani a szervert.
