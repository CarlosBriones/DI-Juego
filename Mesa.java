/* Deck.java
 */

public class Mesa {

    final static int DECK_SIZE = 52;
    private Carta[] cards;
    private int N = 0;

    public Mesa() {
        cards = new Carta[DECK_SIZE];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[N] = new Carta(N, j, i + "" + j + ".png");
                N++;
            }
        }
    }

    public Carta dealFrom() {
        return cards[--N];
    }

    public boolean estaVacio() {
        return (N == 0);
    }

    public int size() {
        return N;
    }

    public void shuffle() {
        for (int i = 0; i < N; i++) {
            int r = (int) (Math.random() * i);
            Carta cambio = cards[i];
            cards[i] = cards[r];
            cards[r] = cambio;
        }
    }
}
