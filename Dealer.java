public class Dealer extends Player {
        
        public void turn(Deck d) {
                while (!this.hand.isBust() && this.dealerHit()) {
                        hit(d);
                }
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