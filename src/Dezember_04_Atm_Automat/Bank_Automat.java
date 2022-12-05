package Dezember_04_Atm_Automat;

import java.util.Scanner;

public class Bank_Automat {

       final static String karteNummer="1234567890123456";
       static  Scanner scan=new Scanner(System.in);
       static String passwort="1234";
       static double kontoGuthaben=20000;

    public static void main(String[] args) {

        eingabe();


    }

    private static void eingabe() {
        System.out.println("Bitte geben Sie Ihre Kartenummer : ");
        String benutzerKarteNummer=scan.nextLine();
        System.out.println("Bitte geben Sie Ihre Passwort : ");
        String benutzerPasswort= scan.nextLine();
        benutzerKarteNummer=benutzerKarteNummer.replaceAll("\\s","");

        if(benutzerPasswort.equals(passwort)&&benutzerKarteNummer.equals(benutzerKarteNummer)){
            menu();
        }else {
            System.out.println("Ihre Passwort ist nicht gültig !!");
            eingabe();
        }
    }

    private static void menu() {
        System.out.println("********Wilkommen zu JavaBank********\n"+
                "Was möchten Sie tun,bitte wählen Sie aus?\n"+
                "1. Konto Details\n"+
                "2. Geld Einzahlung\n"+
                "3. Geld Auszahlung\n"+
                "4. Geld Überweisung\n"+
                "5. Passwort wechseln\n"+
                "6. Beenden ");
            int entscheidung= scan.nextInt();
            switch (entscheidung){
                case 1 : {kontoDetails();}
                case 2 : {
                    System.out.println("Geben Sie den Einzahlungsbetrag ein : ");
                    double betrag = scan.nextDouble();
                    geldEinzahlung(betrag);}
                case 3 : {
                    System.out.println("Geben Sie den Auszahlungsbetrag ein : ");
                    double betragAusZahlung= scan.nextDouble();
                    geldAuszahlung(betragAusZahlung);
                }
                case 4 : {
                    System.out.println("Bitte geben Sie Ihre Iban Nummer : ");
                    String iban=scan.nextLine();
                    scan.nextLine();
                    System.out.println("Bitte geben Sie den Überweisungsbetrag ein : ");
                    double uberweisungsbetrag=scan.nextDouble();

                    geldUberweisung(ibanKontrol(iban),uberweisungsbetrag);
                }
                case 5 : {
                    passwortWechseln();
                }
                case 6 : {
                    System.out.println("*********Tschüss!!***********");
                    System.exit(0);
                }

            }

    }

    private static void passwortWechseln() {
        System.out.println("Bitte geben Sie Ihre Passwort ein : ");

        String benutzerPasswort=scan.nextLine();

        if (benutzerPasswort.equals(passwort)){
            System.out.println("Bitte geben Sie Ihre neue Passwort ein : ");
            passwort=scan.nextLine();
            eingabe();
        }else{
            System.out.println("Bitte geben Sie Ihre gültige Passwort!!");
            passwortWechseln();
        }
    }

    private static void geldUberweisung(String iban, double uberweisungsbetrag) {
   if (uberweisungsbetrag<=kontoGuthaben){
       kontoGuthaben-=uberweisungsbetrag;
       System.out.println(iban+"zu der Ibannummer "+ uberweisungsbetrag + "ist gesendet ");
       kontoDetails();
       menu();
   }else {
       System.out.println("Bitte geben Sie einen gültigen Überweisungsbetrag !!");
   geldUberweisung(ibanKontrol(iban),scan.nextDouble());
   }
    }

    private static String ibanKontrol(String iban) {
    iban=iban.replaceAll("\\s","");
    if (iban.startsWith("DE")&&iban.length()==26){

    }else {
        System.out.println("Bitte geben Sie eine gültige Ibannummer !!");
        ibanKontrol(scan.nextLine());
    }
    return iban;
    }

    private static void geldAuszahlung(double betragAusZahlung) {
        if (betragAusZahlung<=kontoGuthaben){
        kontoGuthaben -=betragAusZahlung;
            kontoDetails();
            menu();

        }else {
            System.out.println("Sie dürfen nicht mehr als kontoguthaben asuzahlen\n +" +
                               "Bitte geben Sie den Auszahlungsbetrag ein");
            geldAuszahlung(scan.nextDouble());
        }

    }

    private static void geldEinzahlung(double betrag) {
        kontoGuthaben += betrag;
        kontoDetails();
        menu();
    }

    private static void kontoDetails() {
        System.out.println("Ihre Guthaben : "+kontoGuthaben);
        menu();
    }


}
