class Player {
        //state
        public Hand hand = new Hand();
        private boolean stay = false;
        private boolean playing = false;

        //constructor
        public Player() {

        }

        public void hit(Deck d) {
                this.hand.addCard(d.drawCard());
        }

        public Hand hand() {
                return this.hand;
        }

        public void turn() {
                
        }

        public void setStay (boolean stay) {
                this.stay = stay;
        }

        public boolean stay() {
                return this.stay;
        }

        public boolean playing() {
                return this.playing;
        }

        public void setPlaying(boolean playing) {
                this.playing = playing;
        }

        public boolean dealerHit() {
                if (this.hand.totalvalues()[0] < 17 && this.hand.totalvalues()[0] == this.hand.totalvalues()[1]) {
                        return true;
                } else if (this.hand.totalvalues()[0] <= 17 && this.hand.totalvalues()[0] != this.hand.totalvalues()[1]) {
                        return true;
                } else if (this.hand.totalvalues()[1] < 17 && this.hand.totalvalues()[0] != this.hand.totalvalues()[1]) {
                        return true;
                }
                return false;
        }
}