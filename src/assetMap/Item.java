package assetMap;

public class Item {
    public String jenis;
    public int x,y;
    public boolean sudahDiambil;
    public boolean asli;

    public Item(String jenis, int x, int y, boolean asli) {
        this.jenis = jenis;
        this.x = x;
        this.y = y;
        this.sudahDiambil = false;
        this.asli = asli;
    }
    
}
