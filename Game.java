import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.InterruptedException;
import javax.imageio.ImageIO;

public class Game {

        public Player[] players;
        private Deck deck;
        private boolean playing = false;
        JLabel flippedcard;
        public JLabel[] dealercards = new JLabel[11];
        public int dcindex = 0;
        public JLabel[] playercards = new JLabel[11];
        public int pcindex = 0;
        public double playertotal = 1000.00;

        public Game(int playercount) {
                this.players = new Player[playercount];
                this.players[0] = new Dealer();
                if (playercount > 1) {
                        for (int i = 1; i < playercount; i++) {
                                this.players[i] = new Human();
                        }
                }

                this.deck = new Deck(true);
        }

        public Deck deck() {
                return this.deck;
        }

        public boolean playing() {
                return this.playing;
        }

        public void drawDeck(Graphics g) {
                deck.draw(g,0);
        }

        public void dealCards(Panel dp, Panel hp) {
                addCard(deck.drawCard(), dp, 0);
                addCard(deck.drawCard(), dp, 0, true);
                addCard(deck.drawCard(), hp, 1);
                addCard(deck.drawCard(), hp, 1);
        }

        public void addCard(Card card, Panel panel, int p) {
                if (p == 1) {
                        this.playercards[pcindex] = new JLabel(card.Icon());
                        panel.add(playercards[pcindex]);
                        pcindex++;
                } else {
                        this.dealercards[dcindex] = new JLabel(card.Icon());
                        panel.add(dealercards[dcindex]);
                        dcindex++;
                }
                players[p].hand.addCard(card);
        }

        public void addCard(Card card, Panel panel, int p, boolean back) {
                if (back) {
                        flippedcard = new JLabel(card.backIcon());
                        this.dealercards[1] = flippedcard;
                        dcindex++;
                        panel.add(flippedcard);
                        players[p].hand.addCard(card);
                } else {
                        panel.add(new JLabel(card.Icon()));
                        players[p].hand.addCard(card);
                }
        }

        public void dealerTurn(Panel dp) {
                players[0].setPlaying(true);
                turnOverCard();
                while (!this.players[0].hand.isBust() && this.players[0].dealerHit()) {
                        hit(dp, 0);
                }
                players[0].setPlaying(false);
        }

        public void turnOverCard() {
                this.flippedcard.setIcon(players[0].hand.cards()[1].Icon());
        }

        public void hit(Panel panel, int p) {
                if (players[p].playing()) {
                        addCard(deck.drawCard(), panel, p);
                } else {
                        System.out.println("Not turn");
                }
        }

        public void stay(Panel dp) {
                if (players[1].playing()) {
                        players[1].setStay(true);
                        players[1].setPlaying(false);
                        dealerTurn(dp);
                } else {
                        System.out.println("Not turn");
                }
        }

        public void stopPlaying() {
                this.playing = false;
        }

        public int topScore(int p) {
                if (this.players[p].hand.totalvalues()[0] >= 22 && this.players[p].hand.totalvalues()[0]!=this.players[p].hand.totalvalues()[1]) {
                        return this.players[p].hand.totalvalues()[1];
                } else {
                        return this.players[p].hand.totalvalues()[0];
                }
        }

        public int determineOutcome() {
                if (topScore(1) > 21) {
                        return 0;
                } else if (topScore(0) > 21) {
                        return 1;
                } else if (topScore(1) > topScore(0)) {
                        return 1;
                } else if (topScore(0) > topScore(1)) {
                        return 2;
                } else if (topScore(1) == topScore(0)) {
                        return 3;
                }
                System.out.println("Fail");
                return 4;
        }

        public void changePlayerTotal(double c) {
                this.playertotal+=c;
        }
        
}