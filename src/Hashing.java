import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public class Hashing {
    int[] zahlen;
    int[] kollisionen;
    double[] zahlen1;
    int mod;

    public static void main(String[] args){
        System.out.println("Gebe die Zahl an, nach der gehasht werden soll!");
        int pMod= 10;
        /**
        try {
            pMod = System.in.read(); //fuer einlesen
            System.out.println("Modulo-Wert wurde erfolgreich gespeichert!"+pMod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        Hashing hash= new Hashing(pMod);
        System.out.println(Arrays.toString(hash.gibZahlen()));
        hash.fuegeEin(12042004, 3);
        System.out.println(Arrays.toString(hash.gibZahlen()));
        System.out.println(Arrays.toString(hash.gibZahlen1()));
        hash.fuegeEin(12032004, 4);
        System.out.println(Arrays.toString(hash.gibZahlen()));
        System.out.println(Arrays.toString(hash.gibZahlen1()));
        hash.suchePositionVon(12032004);
    }
  
    public Hashing(int pMod){
    this.mod=pMod;
    //Arrays der Groeße, nach der der Modulo berechnet wird, da equivalent mit Anzahl Reste bzw. Hashwerte
    zahlen= new int[mod];
    zahlen1= new double[mod];
    kollisionen=new int[mod];
    //Initialisierung mit 0
        Arrays.fill(zahlen, 0);
        Arrays.fill(zahlen1, 0);
        Arrays.fill(kollisionen, 0);
    }
  
    public int berechneHash(int pSchluessel){
        return pSchluessel%mod; //berechnet Hashwert als durch modulo
    }
  
      public boolean arrayVoll(){
        int anzahl=0;
        boolean x=false;
          for (int j : zahlen) { //das ganze Array wird durchlaufen und die Anzahl an gefüllten Feldern ermittelt
              if (j != 0) {
                  anzahl++;
              }
          }
        if(anzahl==zahlen.length) x=true; //falls die Anzahl an gefuellten Stellen der Gesamtanzhal entspricht, so ist das Array voll
        return x;
      }
  
    public int fuegeEin(int pSchluessel, double wert){
        if (arrayVoll()){ //falls das Array voll, so kann kein Wert eingefuegt werden
            System.out.println("Das Array ist voll!");
            return -1;
        }
        int hash=berechneHash(pSchluessel);
        int index=hash; //Indesx wird auf gehashte Stelle im Array gesetzt
        int kolli=0; //Variable die Kollisionen zaehlt
        while (zahlen[index]!=0) { //falls die Stelle besetzt ist, so wird die nach rechts nächste freie Stelle gesucht
            index++;
            kolli++; //Index und Kollisionen erhoehen sich
            if (index==zahlen.length) index=0; //falls das Array durchlaufen wurde bzw. um eine IndexOutOfBoundsException zu verhindern, wird der Index wieder auf null gesetzt
        }
        zahlen[index]=pSchluessel;
        zahlen1[index]=wert;
        kollisionen[hash]=kolli; //Zahl
        System.out.println("Schluessel mit Wert erfolgreich eingefuegt!");
        return index;

    }

    public int suchePositionVon(int pSchluessel){
    int hash=berechneHash(pSchluessel);
    int index=hash;
    int maxKolli=kollisionen[hash]; //Bereich, in welchem sich der Schluessel befinden koennte, wird durch maximale Anzahl gespeichert in kollisionen ermittelt
    int schritte=0; //schritte als Zaehlvariabel fuer Vergleiche
    while (pSchluessel != zahlen[index] && schritte<=maxKolli) { //schritte muessen sich im potenziellen Bereich befinden
      index++;
      schritte++;
      if (index==zahlen.length) index=0;
    }           
    if(zahlen[index]!=pSchluessel) { //der Schluessel wurde nicht gefunden
        index = -1;
        System.out.println("Schluessel wurde nicht gefunden!");
    }
    System.out.println("Schluessel befindet sich an folgender Stelle: "+(index-1));
    return index;
    }
    public int fuegeEin(int pSchluessel){
    fuegeEin(pSchluessel,0);
    return 0;
    }
    public void aktualisiereWert(int pSchluessel, double wert){
    int pos=suchePositionVon(pSchluessel);
    if(pos!=-1) zahlen1[pos] = wert;
    else{
      System.out.println("Schluessel muss erst eingefuegt werden!");
    }  
    }
    
    public int[] gibZahlen(){
    return zahlen;
    }
    public double[] gibZahlen1() {
    return zahlen1;
    }
    public double gibWertVon(int pGeburtsdatum){
    int pos=suchePositionVon(pGeburtsdatum);
    return zahlen1[pos];
    }

}
