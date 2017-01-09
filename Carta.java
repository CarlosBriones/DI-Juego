/* Card.java
 */

public class Carta {

    private int numCarta;
    private int rango;
    private String front;

    Carta(int numCarta, int rango, String front) {
        this.numCarta = numCarta;
        this.rango = rango;
        this.front = front;
    }

    public boolean esAs() {
        return rango == 0;
    }

    public int rango() {
        if (rango == 0) {
            return 1;
        }
        if (rango >= 9) {
            return 10;
        }
        return rango + 1;
    }

    public String toString() {
        return this.front;
    }
}
