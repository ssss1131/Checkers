package main.java.ssss.coordinate;

public enum File {
    A, B, C, D, E, F, G, H;


    public static File fromChar(char fileChar) {
        try {
            return File.valueOf(String.valueOf(fileChar).toUpperCase());
        }catch (IllegalArgumentException e){
            return null;
        }

    }
}
