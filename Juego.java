/* Game.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Juego extends JFrame implements ActionListener {

    private Mesa deck;
    public Jugador jugador = new Jugador("Jugador");
    public Jugador crupier = new Jugador("Crupier");

    private JButton doblarJugada = new JButton("Doblar");
    private JButton plantarseJugada = new JButton("Plantarse");
    private JButton manoJugada = new JButton("Mano");

    
    
    JPanel panelJugador = new JPanel();
    JPanel panelCrupier = new JPanel();
    JPanel buttonsPanel = new JPanel();

    Juego() {
        JFrame gameFrame = new JFrame("BlackJack");
        gameFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("cards/10.png"));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        buttonsPanel.add(doblarJugada);
        buttonsPanel.add(plantarseJugada);
        buttonsPanel.add(manoJugada);

        doblarJugada.addActionListener(this);
        plantarseJugada.addActionListener(this);
        manoJugada.addActionListener(this);

        doblarJugada.setEnabled(false);
        plantarseJugada.setEnabled(false);

        panelCrupier.setBackground(new Color(9,127,25));
        panelJugador.setBackground(new Color(9,127,25));
        buttonsPanel.setBackground(new Color(9,127,25));

        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(panelCrupier, BorderLayout.NORTH);
        gameFrame.add(panelJugador, BorderLayout.CENTER);
        gameFrame.add(buttonsPanel, BorderLayout.SOUTH);
        gameFrame.repaint();
        gameFrame.setSize(450, 350);
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    private void doblaJugador() {
        Carta newCard = jugador.repartir(deck.dealFrom());
        panelJugador.add(new JLabel(new ImageIcon("cards/" + newCard.toString())));
        panelJugador.updateUI();

    }

    private void doblaCrupierB() {
        Carta newCard = crupier.repartir(deck.dealFrom());
        panelCrupier.add(new JLabel(new ImageIcon("cards/b2fv.png")));
        panelCrupier.updateUI();
    }

    private void doblaCrupier() {
        Carta newCard = crupier.repartir(deck.dealFrom());
        panelCrupier.add(new JLabel(new ImageIcon("cards/" + newCard.toString())));
        panelCrupier.updateUI();
    }

    private void deal() {
        panelJugador.removeAll();
        panelCrupier.removeAll();
        panelJugador.updateUI();
        panelCrupier.updateUI();
        jugador.reset();
        crupier.reset();
        if (deck == null || deck.size() < 15) {
            deck = new Mesa();
            deck.shuffle();
        }
        doblaJugador();
        doblaCrupierB();
        doblaJugador();
        doblaCrupier();
    }

    private void checkGanador() {
        panelCrupier.removeAll();
        for (int i = 0; i < crupier.enMano(); i++) {
            panelCrupier.add(new JLabel(new ImageIcon("cards/" + crupier.cards[i].toString())));
        }
        if (jugador.value() > 21) {
        	JOptionPane.showMessageDialog(this, "PIERDES, HAS SUPERADO 21", "", JOptionPane.INFORMATION_MESSAGE);
        	JOptionPane.showMessageDialog(this, "PUNTUACION DEL JUGADOR ES DE "+jugador.value(), "", JOptionPane.INFORMATION_MESSAGE);
        } else if (crupier.value() > 21) {
        	JOptionPane.showMessageDialog(this, "GANAS, CRUPIER HA SUPERADO 21", "", JOptionPane.INFORMATION_MESSAGE);
        	JOptionPane.showMessageDialog(this, "PUNTUACION DEL CRUPIER ES DE "+crupier.value(), "", JOptionPane.INFORMATION_MESSAGE);
        } else if (crupier.value() == jugador.value()) {
        	JOptionPane.showMessageDialog(this, "EMPATE", "", JOptionPane.INFORMATION_MESSAGE);
        } else if (crupier.value() < jugador.value()) {
        	JOptionPane.showMessageDialog(this, "HAS GANADO CON "+jugador.value()+", PIERDE CRUPIER", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(this, "HAS PERDIDO, CRUPIER GANA "+crupier.value()+" a "+jugador.value(), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doblarJugada) {
            doblaJugador();
            if (jugador.value() > 21) {
                checkGanador();
                doblarJugada.setEnabled(false);
                plantarseJugada.setEnabled(false);
                manoJugada.setEnabled(true);
            }
        }

        if (e.getSource() == plantarseJugada) {
            while (crupier.value() < 17 || jugador.value() > crupier.value()) {
                doblaCrupier();
            }
            checkGanador();
            doblarJugada.setEnabled(false);
            plantarseJugada.setEnabled(false);
            manoJugada.setEnabled(true);
        }

        if (e.getSource() == manoJugada) {
            deal();
            doblarJugada.setEnabled(true);
            plantarseJugada.setEnabled(true);
            manoJugada.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new Juego();
    }
}
