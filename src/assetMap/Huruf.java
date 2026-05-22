package assetMap;
public class Huruf {
    public String kata;
    public int posisiX;
    public int posisiY;
    public boolean sudahDiambil;

    public Huruf(String kata, int posisiX, int posisiY) {
        this.kata = kata;
        this.posisiX = posisiX;
        this.posisiY = posisiY;
        this.sudahDiambil = false;
    }
}
