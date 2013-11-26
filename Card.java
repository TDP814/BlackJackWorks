import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


class Card {
        //state
        private int[] values;
        private int number;
        private int suit;
        private Image image;
        private Image backImage;

        //Constructor
        public Card(int suit, int number) {
                setNumber(number);
                this.suit = suit;
                this.image = this.loadImage();
                this.backImage = this.loadBackImage();
        }

        public Card(boolean blank) {
                if (blank) {
                        this.number = 0;
                        this.suit = 0;
                }
        }

        public ImageIcon Icon() {
                ImageIcon icon = new ImageIcon(this.image);
                return icon;
        }

        public ImageIcon backIcon() {
                ImageIcon icon = new ImageIcon(this.backImage);
                return icon;
        }

        public Image loadImage() {
                String path = null;
                Image image = null;

                try {
                        path = "cards" + File.separator + this.fileNum() + this.suitString() + ".png";
                        image = ImageIO.read(new File(path));
                } catch(IOException e) {
                        System.out.println("Could not load image");
                        //System.exit(1);
                }

                return image;
        }

        public Image loadBackImage() {
                String path = null;
                Image image = null;

                try {
                        path = "cards" + File.separator + "back-red.png";
                        image = ImageIO.read(new File(path));
                } catch(IOException e) {
                        System.out.println("Error loading Image");
                }

                return image;
        }
        public void draw(Graphics g, Rectangle r) {
                g.drawImage(image, r.x, r.y, r.width, r.height, null);
        }
        public void draw(Graphics g, Rectangle r, boolean back) {
                if (back) {
                        try {
                                String path = null;
                                path = "cards" + File.separator + "back-red.png";
                                this.image = ImageIO.read(new File(path));
                        } catch(IOException e) {
                                System.out.println("Error");
                        }
                }
                g.drawImage(this.image, r.x, r.y, r.width, r.height, null);
        }
        public void setNumber(int number) {
                this.number = number;
                if (number == 1) {
                        this.values = new int[2];
                        this.values[0] = 11;
                        this.values[1] = 1;
                } else if (number > 10 && number < 14) {
                        this.values = new int[1];
                        this.values[0] = 10;
                } else {
                        this.values = new int[1];
                        this.values[0] = this.number;
                }
        }
        public int[] values() {
                return this.values;
        }
        public int number() {
                return this.number;
        }
        public String fileNum() {
                if (this.number == 11) {
                        return "J";
                } else if (this.number == 12) {
                        return "Q";
                } else if (this.number == 13) {
                        return "K";
                } else if (this.number == 1) {
                        return "A";
                }
                return "" + this.number;
        }
        public String numString() {
                if (this.number == 1) {
                        return "Ace";
                } else if (this.number == 2) {
                        return "Two";
                } else if (this.number == 3) {
                        return "Three";
                } else if (this.number == 4) {
                        return "Four";
                } else if (this.number == 5) {
                        return "Five";
                } else if (this.number == 6) {
                        return "Six";
                } else if (this.number == 7) {
                        return "Seven";
                } else if (this.number == 8) {
                        return "Eight";
                } else if (this.number == 9) {
                        return "Nine";
                } else if (this.number == 10) {
                        return "Ten";
                } else if (this.number == 11) {
                        return "Jack";
                } else if (this.number == 12) {
                        return "Queen";
                } else if (this.number == 13) {
                        return "King";
                }
                return "Null";
        }
        public int suit() {
                return this.suit;
        }
        public String suitString() {
                if (this.suit == 1) {
                        return "Clubs";
                } else if (this.suit == 2) {
                        return "Hearts";
                } else if (this.suit == 3) {
                        return "Diamonds";
                } else if (this.suit == 4) {
                        return "Spades";
                }
                return "null";
        }
}