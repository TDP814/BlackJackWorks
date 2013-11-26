class Hand {
        //state
        private Card[] cards = new Card[11];
        private int cardindex = 0;
        private int aces = 0;
        private int[] totalvalues = {0,0};
        private boolean bust;

        //constructor
        public Hand() {
                
        }

        public void addCard(Card c) {
                this.cards[this.cardindex] = c;
                this.cardindex++;
                if (c.values().length == 2) {
                        this.aces++;
                        for (int i = 0; i < this.totalvalues.length; i++) {
                                this.totalvalues[i] = this.totalvalues[i] + c.values()[i];
                        }
                } else {
                        for (int i = 0; i < this.totalvalues.length; i++) {
                                this.totalvalues[i] = this.totalvalues[i] + c.values()[0];
                        }
                }
                if (this.totalvalues[0] > 21 && this.totalvalues[1] > 21) {
                        this.bust = true;
                }
        }

        public boolean isBust() {
                return this.bust;
        }

        public void printHand() {
                System.out.print("Current hand: ");
                for (int i = 0; i < this.cardindex; i++) {
                        if (this.cards[i].number() > 0) {
                                System.out.print(this.cards[i].numString() + " of " + this.cards[i].suitString() + ", ");
                        }
                }
                System.out.println();
        }

        public void printtotal() {
                System.out.println("Possible totals for the value of this hand are: " + this.totalvalues[0] + ", and " + this.totalvalues[1]);
                if (this.bust) {
                        System.out.println("You busted");
                } else if (this.totalvalues[0] == 21 || this.totalvalues[1] == 21) {
                        System.out.println("Blackjack");
                } else {
                        System.out.println("You didn't bust");
                }
        }

        public int[] totalvalues() {
                return this.totalvalues;
        }

        public Card[] cards() {
                return this.cards;
        }
}