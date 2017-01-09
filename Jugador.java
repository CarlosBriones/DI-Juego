/* Player.java
 */

public class Jugador {

    final static int MAX_CARDS = 52;
    public Carta[] cards = new Carta[MAX_CARDS];
    private int N = 0;
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public int enMano() {
        return N;
    }

    public Carta repartir(Carta c) {
        cards[N++] = c;
        return c;
    }

    public void reset() {
        N = 0;
    }

    public int value() {
        int val = 0;
        boolean esAs = false;
        for (int i = 0; i < N; i++) {
            val = val + cards[i].rango();
            if (cards[i].esAs()) {
                esAs = true;
            }
        }
        if (esAs && (val <= 11)) {
            val = val + 10;
        }
        return val;
    }
}
