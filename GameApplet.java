import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

public class GameApplet extends Applet implements ActionListener {

        private Game game;
        Button h;
        Button s;
        Button r;
        Button bp10;
        Button bm10;
        Button done;
        Button dd;
        Panel windowpanel;
        Panel buttonpanel;
        Panel cardpanels;
        Panel dp;
        Panel hp;
        Panel sp;
        Panel bp;
        JLabel score;
        JLabel outcome;
        JLabel playerBet;
        JLabel playerMoney;
        public double currentbet = 500.00;

        public void init() {
                this.game = new Game(2);
                windowpanel = new Panel();
                windowpanel.setLayout(new BorderLayout());
                drawButtons();
                drawCardPanels();
                drawBetPanel();
                add(windowpanel);
                betting();
        }

        public void betting() {
                setButtons(false);
                bp10.setEnabled(true);
                bm10.setEnabled(false);
                r.setEnabled(false);
                done.setEnabled(true);
        }

        public void paint(Graphics g) {
                
        }

        public void actualGame() {
                if (this.game.players[1].hand.totalvalues()[0] == 21) {
                        gotBlackjack();
                } else {
                        this.game.players[1].setPlaying(true);
                }
        }

        public void gotBlackjack() {
                System.out.println("Blackjack!");
                outcome.setText("Blackjack!");
                setButtons(false);
                this.game.changePlayerTotal(currentbet*1.5);
                validate();
        }

        public void drawButtons() {
                buttonpanel = new Panel();
                h = new Button("Hit");
                h.setActionCommand("hit");
                s = new Button("Stay");
                s.setActionCommand("stay");
                r = new Button("Reset");
                r.setActionCommand("reset");
                r.setEnabled(false);

                h.addActionListener(this);
                s.addActionListener(this);
                r.addActionListener(this);

                buttonpanel.add(h);
                buttonpanel.add(s);
                buttonpanel.add(r);

                windowpanel.add(buttonpanel, BorderLayout.NORTH);
        }

        public void drawBetPanel() {
                sp = new Panel(new BorderLayout());
                bp = new Panel();

                bp10 = new Button("Bet +10");
                bp10.setActionCommand("bet +10");
                bm10 = new Button("Bet -10");
                bm10.setActionCommand("bet -10");
                bm10.setEnabled(false);
                done = new Button("Done Betting");
                done.setActionCommand("done");
                dd = new Button("Double Down");
                dd.setActionCommand("doubledown");

                bp10.addActionListener(this);
                bm10.addActionListener(this);
                done.addActionListener(this);
                dd.addActionListener(this);

                bp.add(bp10);
                bp.add(bm10);
                bp.add(done);
                bp.add(dd);

                playerMoney = new JLabel("Current amount: " + this.game.playertotal);
                bp.add(playerMoney);

                playerBet = new JLabel("Current bet: " + currentbet);
                sp.add(playerBet, BorderLayout.EAST);

                sp.add(bp, BorderLayout.SOUTH);
                windowpanel.add(sp, BorderLayout.SOUTH);
        }

        public void drawCardPanels() {
                cardpanels = new Panel();
                cardpanels.setLayout(new BorderLayout());
                dp = new Panel();
                hp = new Panel();

                dp.add(new JLabel("Dealer Hand"));
                hp.add(new JLabel("Player Hand"));

                cardpanels.add(dp,BorderLayout.NORTH);
                cardpanels.add(hp,BorderLayout.SOUTH);

                windowpanel.add(cardpanels);
        }

        public void drawScorePanel() {
                score = new JLabel("Current score: " + this.game.topScore(1));
                sp.add(score, BorderLayout.WEST);
                outcome = new JLabel();
                sp.add(outcome, BorderLayout.NORTH);
                validate();
        }

        public void actionPerformed(ActionEvent e) {
                if ("hit".equals(e.getActionCommand())) {
                        this.game.hit(this.hp, 1);
                        resetScore();
                } else if ("stay".equals(e.getActionCommand())) {
                        stayStuff();
                } else if ("reset".equals(e.getActionCommand())) {
                        resetGame();
                } else if ("bet +10".equals(e.getActionCommand())) {
                        bm10.setEnabled(true);
                        bp10method();
                } else if ("bet -10".equals(e.getActionCommand())) {
                        bm10method();
                } else if ("doubledown".equals(e.getActionCommand())) {
                        ddMethod();
                }
                if ("done".equals(e.getActionCommand())) {
                        setButtons(true);
                        done.setEnabled(false);
                        bp10.setEnabled(false);
                        bm10.setEnabled(false);
                        game.dealCards(this.dp, this.hp);
                        drawScorePanel();
                        validate();
                        actualGame();
                } else if ("done2".equals(e.getActionCommand())) {
                        this.game.dcindex = 0;
                        this.game.pcindex = 0;
                        for (int p = 0; p < 2; p++) {
                                this.game.players[p].hand = new Hand();
                        }
                        game.dealCards(this.dp, this.hp);
                        resetScore();
                        setButtons(true);
                        bm10.setEnabled(false);
                        actualGame();
                }
                if (currentbet == 30.00) {
                        bm10.setEnabled(false);
                } else if (currentbet > 30.00) {
                        bm10.setEnabled(true);
                }
                validate();
        }

        public void bp10method() {
                currentbet+=10;
                playerBet.setText("Current bet: " + currentbet);
                if (currentbet == 30.00) {
                        bm10.setEnabled(false);
                } else if (currentbet > 30.00) {
                        bm10.setEnabled(true);
                }
        }

        public void bm10method() {
                currentbet-=10;
                playerBet.setText("Current bet: " + currentbet);
                if (currentbet == 30.00) {
                        bm10.setEnabled(false);
                } else if (currentbet > 30.00) {
                        bm10.setEnabled(true);
                }
        }

        public void ddMethod() {
                currentbet = currentbet*2;
                playerBet.setText("Current bet: " + currentbet);
        }

        public void stayStuff() {
                this.game.stay(this.dp);
                if (this.game.determineOutcome() == 0) {
                        outcome.setText("You bust");
                        this.game.changePlayerTotal(this.currentbet * -1.0);
                } else if (this.game.determineOutcome() == 1) {
                        outcome.setText("You Win");
                        this.game.changePlayerTotal(this.currentbet);
                } else if (this.game.determineOutcome() == 2) {
                        outcome.setText("You lost");
                        this.game.changePlayerTotal(this.currentbet * -1.0);
                } else if (this.game.determineOutcome() == 3) {
                        outcome.setText("Push");
                }
                setButtons(false);
        }

        public void resetGame() {
                for (int p = 0; p < 2; p++) {
                        for (int i = 0; i < 11; i++) {
                                this.game.players[p].hand.cards()[i] = new Card(true);
                                if (p == 0 && i < this.game.dcindex) {
                                        dp.remove(this.game.dealercards[i]);
                                } else if(p == 1 && i < this.game.pcindex) {
                                        hp.remove(this.game.playercards[i]);
                                }
                        }
                }
                done.setActionCommand("done2");
                currentbet = 30.00;
                resetScore();
                score.setText("");
                betting();
                validate();
        }

        public void resetScore() {
                score.setText("Current score: " + this.game.topScore(1));
                playerMoney.setText("Current money total: " + this.game.playertotal);
                outcome.setText("");
                playerBet.setText("Current bet: " + currentbet);
                validate();
                if (this.game.topScore(1) > 21) {
                        stayStuff();
                }
        }

        public void setButtons(boolean on) {
                h.setEnabled(on);
                s.setEnabled(on);
                dd.setEnabled(on);
                r.setEnabled(!on);
        }
}