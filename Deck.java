import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

class Deck {
        //state
        public Card[] entireDeck = new Card[52];
        private int cardsdrawn = 0;

        //constructor
        public Deck() {
                makeDeck();
                this.shuffle();
        }

        public Deck(boolean shuffle) {
                makeDeck();
                if (shuffle) {
                        this.shuffle();
                }
        }

        public void makeDeck() {
                int i = 0;

                for (int s = 1; s <= 4; s++) {
                        for (int n = 1; n <= 13; n++) {
                                entireDeck[i] = new Card(s,n);
                                i++;
                        }
                }
        }

        public void print() {
                for (int i = 0; i < 51; i++) {
                        System.out.print(entireDeck[i].numString() + " of " + entireDeck[i].suitString() + ", ");
                }
                System.out.println(entireDeck[51].numString() + " of " + entireDeck[51].suitString());
        }

        public void shuffle() {
                Random r = new Random();
                int nextcard;
                Card tempCard = new Card(1,2);

                for (int i = 51; i > 0; i--) {
                        nextcard = r.nextInt(i+1);
                        tempCard = entireDeck[i];
                        entireDeck[i] = entireDeck[nextcard];
                        entireDeck[nextcard] = tempCard;
                }
        }

        public Card drawCard() {
                if (cardsdrawn < 52) {
                        this.cardsdrawn++;
                        return this.entireDeck[this.cardsdrawn-1];        
                } else {
                        shuffle();
                        this.cardsdrawn = 0;
                        return this.entireDeck[this.cardsdrawn];
                }
        }

        public Card drawCard(boolean back) {
                if (cardsdrawn < 52) {
                        this.cardsdrawn++;
                        return this.entireDeck[this.cardsdrawn-1];        
                } else {
                        shuffle();
                        this.cardsdrawn = 0;
                        return this.entireDeck[this.cardsdrawn-1];
                }
        }

        public void draw(Graphics g, int y) {
                for (int i = 0; i < entireDeck.length; i++) {
                        entireDeck[i].draw(g, new Rectangle(10+(20*i),50+(100*y),40,60));
                }
        }
}