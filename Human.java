import java.util.Scanner;

public class Human extends Player {


        public void turn(Deck d) {
                Scanner s = new Scanner(System.in);
                String input;

                while (!this.hand.isBust() && !this.stay()) {
                        System.out.print("Type h to hit, s to stay: ");
                        input = s.next();
                        if (input.equals("h")) {
                                hit(d);
                        } else {
                                this.setStay(true);
                        }
                        this.hand.printHand();
                        this.hand.printtotal();
                }
        }
}