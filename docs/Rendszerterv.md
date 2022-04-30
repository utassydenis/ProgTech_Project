## 1. Rendszer célja
A rendszer célja egy ablakos alkalmazás, amivel különböző űrhajókat tudunk összerakni. Az alkalmazás használatához szükség lesz egy account-hoz, hogy bejelentkezés után el lehessen kezdeni az űrhajók konfigurálását. Az összerakott űrhajót el lehet menteni a saját űrhajóink közé. Az alkalmazás ingyenes lesz. Az űrhajót nem lehet megrendelni az alkalmazáson keresztül, csak tájékoztatás jelleggel lesznek az árak feltüntetve.

## 2. Üzleti folyamatok modellje
![image](https://raw.githubusercontent.com/utassydenis/ProgTech_Project/main/docs/%C3%BCzleti%20terv.drawio.png)

## 3. Követelmények

**Funkcionális követelmények**
  - **Felhasználók adatainak tárolása**
  - **Felhasználók tudjanak űrhajókat konfigurálni**
  - **Felhasználók tudják a mentett űrhajóikat megtekinteni**

  **Nem funkcionális követelmények**
  - **A felhasználók nem férnek hozzá egymás adataihoz**

  **Törvényi előírások, szabványok:**
  - **GDPR-nek való megfelelés**

## 4. Funkcionális terv

**Rendszerszereplők:**
  - **Felhasználó**
  - **Vendég**

  **Rendszerhasználati esetek és lefutásaik:**
  - **Felhasználó**
    - **Készíthet új űrhajót**
    - **Megtekintheti az elmentett űrhajóit**
    - **Törölheti a korábban mentett űrhajóit**

  - **Vendég**
    - **Képes regisztrálni és bejelentkezni**

  - **Menü-hierarchiák:**
    - **Bejelentkezés**
    - **Regisztráció**
    - **Űrhajó konfigurálása**

    - **Bejelentkezés után:**
      - **Űrhajók szerkesztése**
      - **Saját űrhajók megtekintése**
      - **Kijelentkezés**

## 5. Fizikai környezet
- **Feljeszői környezet:**
    - **IntelliJ Idea**
    - **Git**
    - **MySQL/Xampp**
    - **UML**


## 6. Architekturális terv
- **Felhasznált technológiák:**
 1. MySQL:
    A MySQL kezeli az adatbázisunkat, itt tároljuk el az adatokat.
    A program "query"-n keresztül kommunikál az adatbázissal, lekérdezéseket, müveleket végez el benne.
    XAMPP-al vezérelt, localhost-on elérhető.
  2. Java:
  A fejlesztés IntelliJ Idea fejlesztői környezetben folyik a programozás.
  A program logolásához SLF4J-t használunk.

## 7. Implementációs terv:
  Az alkalmazás Windows operációs rendszerre klszül IntelliJ Ideaban, Java nyelven.

## 8. Tesztterv:
**Unit teszt:**
A metódusok működésének ellenőrzésére unit teszteket kell írni.
A metódusoknak át kell mennie a teszteken, hogy elkészültnek tekintsük.

## 9. Telepítési terv

