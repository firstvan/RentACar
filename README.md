# RentACar

### Követelmények:
- Maven
- Basex (http://basex.org/products/download/all-downloads/)

### Fordítás:
mvn clean package

### Futtatási előkövetelmény:
A basex adatbázis kezelő rendszert el kell indítani: (startmenüben basex server (start)-re kell keresni).
Ahhoz, hogy létrejöjjön a rendacardb adatbázis ki kell commentezni a RentACarMain.java, initDB(); sorát.

### Futtatás:
java -jar target/RentACar.jar

### Elérés:
- http://localhost:8888/ böngészőben
