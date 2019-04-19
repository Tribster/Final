/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 *
 * @author caleb
 */
public class Final {
    
    public static void main (String[] args)
    {
    
        Final programm = new Final();
        programm.start();
        programm.gui();
    }

    public void start() 
    {
        GameLogic game = new GameLogic();
        game.dealCards();
        game.showCards();
    }


        
           


   
  public class Card {
     private String face;
    private String suit;
    //two-argument constructor initializes Cards face and suit
    public Card(String face, String suit) {
        super();
        this.face = face;
        this.suit = suit;
    }
    //getter method to return the face value
    public String getFace() {
        return face;
    }
    //setter method to initialize the face
    public void setFace(String face) {
        this.face = face;
    }
    //getter method to return the suit value
    public String getSuit() {
        return suit;
    }
    //setter method to initialize the suit
    public void setSuit(String suit) {
        this.suit = suit;
    }
    //return String representation of Card object
     @Override
    public String toString() {
        return face + " of " + suit;
    }
}//end of card class
    
    
    
    
    
    
public class Deck {
    private final String faces[] = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    private final String suits[]={"Hearts","Diamonds","Clubs","Spades"};
    private final Card deck[];
    private final int TOTAL_CARDS=52;
    private final Random randNum;    
    //no-argument constructor fills the deck of cards
    public Deck(){        
        deck = new Card[TOTAL_CARDS];
        randNum = new Random();
        for(int i=0;i<deck.length;i++){
            deck[i] = new Card(faces[i%13],suits[i/13]);
        }
    }
    
    //shuffles the deck
    public void shuffle(){
        for(int i=0;i<deck.length;i++){
            int j = randNum.nextInt(TOTAL_CARDS);
            Card c = deck[i];
            deck[i] = deck[j];
            deck[j] = c;
        }
    }        

    //returns the individual card in the deck
    public Card getCard(int index){
        return deck[index];
    }
}//end of class
   


public class Player {
    public final static int MAX_CARD = 5;
    private Card cards[];
    //constructor initializes 5 cards in each hand
    public Player() {
        cards = new Card[MAX_CARD];
    }
    //returns all the cards in hand
    public Card[] getCards() {
        return cards;
    }
    //get the cards at a particular position
    public Card getCardAtIndex(int index) {
        if (index >= 0 && index < MAX_CARD)
            return cards[index];
        else
            return null;
    }
   //sets the card at particular position
    public void setCardAtIndex(Card c, int index) {
        if(index >= 0 && index < MAX_CARD)
            cards[index] = c;
    }
    //counts number of matched pair
    public int countPair() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[i].getFace().equals(cards[j].getFace())){
                    count++;
                }
            }
        }
        return count;
    }

    //checks if it is a flush or not i.e all five cards of same suit
    public boolean isFlush() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[i].getSuit().equals(cards[j].getSuit())) {
                    count++;
                }
            }
        }
        if(count == 5)
            return true;
        else
            return false;
    }    
}






public class GameLogic {
     private Player[] players;
    private Deck deck;
    //constructor initializes the deck and cards
    public GameLogic() {
        deck = new Deck();
        players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        deck.shuffle();
    }
    //deals the card to each player
    public void dealCards() {
        int count = 0;
        for (int i = 0; i < players[0].getCards().length; i++) {
            for (int j = 0; j < players.length; j++) {
                players[j].setCardAtIndex(deck.getCard(count++), i);
            }
        }
    }
    //simulates the game and shows the result
    public void showCards() {
        for (int i = 0; i < players.length; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            System.out.println("");
            for (int j = 0; j < players[0].getCards().length; j++) {
                System.out.print("{" + players[i].getCardAtIndex(j).toString()+"} ");
                
            }
            System.out.println("");
            if(players[i].countPair()> 0)
                System.out.print(players[i].countPair()+ " Pair");
            if(players[i].isFlush())
                System.out.print("FLUSH!!\n");
            System.out.println("\n------------------------------------");
        }
    }
}//end of class
    
public class GameApp {
        GameLogic game;

        public GameApp() {
            this.game = new GameLogic();
        game.dealCards();
        game.showCards();
        }
        
        
        
    }//end of main

 public void gui()
 {
        JFrame frame = new JFrame("Game Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1370,725);
        
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        JPanel card3 = new JPanel();
        JPanel card4 = new JPanel();
        JPanel card5 = new JPanel();
        
        
        card1.setPreferredSize(new Dimension(200, 100));
        card2.setPreferredSize(new Dimension(200, 100));
        card3.setPreferredSize(new Dimension(200, 100));
        card4.setPreferredSize(new Dimension(200, 100));
        card5.setPreferredSize(new Dimension(200, 100));
        
        
        frame.getContentPane().add(BorderLayout.SOUTH, card1);
        frame.getContentPane().add(BorderLayout.NORTH, card2);
        frame.getContentPane().add(BorderLayout.EAST, card3);
        frame.getContentPane().add(BorderLayout.WEST, card4);
        frame.getContentPane().add(BorderLayout.SOUTH, card5);

        
        card1.setVisible(true);
        card2.setVisible(true);
        card3.setVisible(true);
        card4.setVisible(true);
        card5.setVisible(true);
        
        
        JLabel label = new JLabel("A");
        JLabel label2 = new JLabel("B");
        JLabel label3 = new JLabel("C");
        JLabel label4 = new JLabel("D");
        JLabel label5 = new JLabel("E");
        
        card1.add(label);
        card2.add(label2);
        card3.add(label3);
        card4.add(label4);
        card5.add(label5);

        /*
        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
*/
        frame.setVisible(true);
        
      
        
    }
 }
    
    
    
    
   
