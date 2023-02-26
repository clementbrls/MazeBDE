package util;

public class MathsPro {
    private MathsPro(){}

    public static float random(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    public static float random(float max) {
        return (float) (Math.random() * max);
    }

    public static int random(int max) {
        return (int) (Math.random() * max);
    }

    public static int random(int min, int max){
        return (int) (Math.random()*(max-min)+min);
    }

    public static float randomUdensity(float min, float max) {
        return (float)Math.random();
    }
}

