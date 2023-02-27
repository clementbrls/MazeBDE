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

    public static double randomUdensity() {
        double rand = Math.random();
        if (rand < 0.5) {
            return Math.pow(rand*2,2)/2;
        } else {
            return 1 - Math.pow((1 - rand)*2,2)/2;
        }
    }

    public static float randomUdensity(float max) {
        return (float) (randomUdensity() * max);
    }

    public static int randomUdensity(int max) {
        return (int) (randomUdensity() * max);
    }
}

