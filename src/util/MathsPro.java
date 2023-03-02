package util;

/**
 * The MathsPro class provides a set of static methods for generating random numbers and applying different probability density functions.
 * All methods in this class are static and cannot be instantiated.
 */
public class MathsPro {
    private MathsPro(){}//Private constructor to prevent instantiation of this class.

    /**
     * Returns a random float number between min (inclusive) and max (exclusive).
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return A random float number between min (inclusive) and max (exclusive).
     */
    public static float random(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    /**
     * Returns a random float number between 0 (inclusive) and max (exclusive).
     * @param max The maximum value of the range.
     * @return A random float number between 0 (inclusive) and max (exclusive).
     */
    public static float random(float max) {
        return (float) (Math.random() * max);
    }

    /**
     * Returns a random integer between 0 (inclusive) and max (exclusive).
     * @param max The maximum value of the range.
     * @return A random integer between 0 (inclusive) and max (exclusive).
     */
    public static int random(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * Returns a random integer between min (inclusive) and max (exclusive).
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     * @return A random integer between min (inclusive) and max (exclusive).
     */
    public static int random(int min, int max){
        return (int) (Math.random()*(max-min)+min);
    }

    /**
     * Returns a random double number between 0 and 1, using the uniform density function.
     * @return A random double number between 0 and 1, using the uniform density function.
     */
    public static double randomUdensity() {
        double rand = Math.random();
        if (rand < 0.5) {
            return Math.pow(rand*2,2)/2;
        } else {
            return 1 - Math.pow((1 - rand)*2,2)/2;
        }
    }

    /**
     * Returns a random float number between 0 and max, using the uniform density function.
     * @param max The maximum value of the range.
     * @return A random float number between 0 and max, using the uniform density function.
     */
    public static float randomUdensity(float max) {
        return (float) (randomUdensity() * max);
    }

    /**
     * Returns a random integer between 0 and max, using the uniform density function.
     * @param max The maximum value of the range.
     * @return A random integer between 0 and max, using the uniform density function.
     */
    public static int randomUdensity(int max) {
        return (int) (randomUdensity() * max);
    }

    /**
     * Returns a random double number between 0 and 1, using the triangular (V-shaped) density function.
     * @return A random double number between 0 and 1, using the triangular (V-shaped) density function.
     */
    public static double randomVdensity() {
        double rand = Math.random();
        //On utilise la technique de l'inverse de la fonction de répartition
        if(rand < 0.5){
            return 0.5*(1- Math.sqrt(1 - rand*2));
        } else {
            return 0.5*(1 + Math.sqrt(2*rand - 1));
        }
    }

    /**
     * Returns a random float number between 0 and max, using the triangular (V-shaped) density function.
     * @param max The maximum value of the range.
     * @return A random float number between 0 and max, using the triangular (V-shaped) density function.
     */
    public static float randomVdensity(float max) {
        return (float) (randomVdensity() * max);
    }

    /**
     * Returns a random integer between 0 and max, using the triangular (V-shaped) density function.
     * @param max The maximum value of the range.
     * @return A random integer between 0 and max, using the triangular (V-shaped) density function.
     */
    public static int randomVdensity(int max) {
        return (int) (randomVdensity() * max);
    }
}

