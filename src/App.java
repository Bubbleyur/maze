public class App {
    public static void main(String[] args) throws Exception {
        int[][] map1 = Map.pembuatanMap("assets/Map(angka)/Map_01.txt");
        GameData.loadingMap(map1);
        new GameFrame();
    }
}
