package view;

import model.player.Player;
import model.tiles.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gui extends JFrame{
    private final JLayeredPane basicPane = new JLayeredPane();

    private final CardLayout cl = new CardLayout();
    private ImageIcon icon;

    private final JLabel board = new JLabel();
    private final JPanel amphoraPlace = new JPanel();
    private final JPanel mosaicPlace = new JPanel();
    private final JPanel gate = new JPanel();
    private final JPanel statuePlace = new JPanel();
    private final JPanel skeletonPlace = new JPanel();

    private final JLabel amphoraPic = new JLabel();
    private final JLabel mosaicPic = new JLabel();
    private final JLabel caryatidPic = new JLabel();
    private final JLabel sphinxPic = new JLabel();
    private final JLabel skeletonPic = new JLabel();

    private final JTextArea player = new JTextArea();

    private final JPanel collectionContainer = new JPanel();
    private final JPanel[] collection = new JPanel[4];

    private final JPanel control = new JPanel();
    private final JButton endTurn = new JButton("END TURN");
    private final JButton drawTiles = new JButton("DRAW TILES");

    private final JPanel characters = new JPanel();
    private final JButton assistant = new JButton();
    private final JButton professor = new JButton();
    private final JButton archeologist = new JButton();
    private final JButton digger = new JButton();

    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gbc = new GridBagConstraints();

    private final TileButton[] tileButton = new TileButton[111];
    private final JLabel[] landslideTiles = new JLabel[24];

    private int numberOfLandslides=0;
    private int numberOfTiles=0;
    private int currentCollection;

    /**
     * Constructor creates a new gui and initialize the board
     * Postconditions the board has been initialized
     */
    public Gui(){
        basicPane.setBounds(0, 0, 1066, 735);
        this.setBounds(140, 0, 1066, 735);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        initializeBoard();
        initializeCharacters();
        initializeCollections();
        initializeControl();
        //initializeAreaButtons();

        player.setBounds(890, 25, 75, 30);
        player.setFont(new Font("Serif", Font.PLAIN, 20));
        player.setEditable(false);
        player.setText("player 1");
        basicPane.add(player);
        cl.show(collectionContainer, ""+1);
        this.add(basicPane);
        this.setVisible(true);
    }

    /**
     * Accessor Displays the rank at the end of the game
     * @param rank an arraylist with the payers sorted by the score
     */
    public void showRank(ArrayList<Player> rank){
        JPanel panel=new JPanel();
        JLabel label;
        panel.setLayout(new GridLayout(4, 1, 20, 0));
        for(int i=0; i<4; i++) {
            label=new JLabel((i+1)+"------"+rank.get(i).getPlayersId()+" with "+rank.get(i).getTotalPoints()+" ponts");
            panel.add(label);
        }
        JOptionPane.showMessageDialog(null, panel, "hi", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Transformer sets all the buttons enable or disable
     * Postconditions the button state has been set
     * @param state a boolean that represents the state of the buttons
     */
    public void buttonState(boolean state){
        professor.setEnabled(state);
        archeologist.setEnabled(state);
        assistant.setEnabled(state);
        digger.setEnabled(state);
        drawTiles.setEnabled(state);
        endTurn.setEnabled(state);
        for(int i=0; i<numberOfTiles; i++){
            tileButton[i].getButton().setEnabled(state);
        }
    }

    /**
     * Transformer adds a tile button on the board when a tile is popped from the bag
     * Postconditions the tile has been added
     * @param tile the tile to be added on the board
     */
    public void addTile(Tile tile){
        switch (tile.getType()){
            case AMPHORA:
                AmphoraTile amphora=(AmphoraTile) tile;
                switch (amphora.getColour()){
                    case BLUE:
                        icon=new ImageIcon(getClass().getResource("images_2020/amphora_blue.png"));
                        break;
                    case BROWN:
                        icon=new ImageIcon(getClass().getResource("images_2020/amphora_brown.png"));
                        break;
                    case RED:
                        icon=new ImageIcon(getClass().getResource("images_2020/amphora_red.png"));
                        break;
                    case PURPLE:
                        icon=new ImageIcon(getClass().getResource("images_2020/amphora_purple.png"));
                        break;
                    case YELLOW:
                        icon=new ImageIcon(getClass().getResource("images_2020/amphora_yellow.png"));
                        break;
                    case GREEN:
                        icon=new ImageIcon(getClass().getResource("images_2020/amphora_green.png"));
                        break;
                }
                tileButton[numberOfTiles].getButton().setIcon(icon);
                tileButton[numberOfTiles].setTile(amphora);
                amphoraPlace.add(tileButton[numberOfTiles].getButton());
                numberOfTiles++;
                break;
            case MOSAIC:
                MosaicTile mosaic=(MosaicTile) tile;
                switch (mosaic.getColour()){
                    case RED:
                        icon=new ImageIcon(getClass().getResource("images_2020/mosaic_red.png"));
                        tileButton[numberOfTiles].getButton().setIcon(icon);
                        tileButton[numberOfTiles].setTile(mosaic);
                        mosaicPlace.add(tileButton[numberOfTiles].getButton());
                        break;
                    case YELLOW:
                        icon=new ImageIcon(getClass().getResource("images_2020/mosaic_yellow.png"));
                        break;
                    case GREEN:
                        icon=new ImageIcon(getClass().getResource("images_2020/mosaic_green.png"));
                        break;
                }
                tileButton[numberOfTiles].getButton().setIcon(icon);
                tileButton[numberOfTiles].setTile(mosaic);
                mosaicPlace.add(tileButton[numberOfTiles].getButton());
                numberOfTiles++;
                break;
            case STATUE:
                StatueTile statue=(StatueTile)tile;
                switch (statue.getStatueType()){
                    case SPHINX:
                        icon=new ImageIcon(getClass().getResource("images_2020/sphinx.png"));
                        tileButton[numberOfTiles].getButton().setIcon(icon);
                        tileButton[numberOfTiles].setTile(statue);
                        statuePlace.add(tileButton[numberOfTiles].getButton());
                        break;
                    case CARYATID:
                        icon=new ImageIcon(getClass().getResource("images_2020/caryatid.png"));
                        break;
                }
                tileButton[numberOfTiles].getButton().setIcon(icon);
                tileButton[numberOfTiles].setTile(statue);
                statuePlace.add(tileButton[numberOfTiles].getButton());
                numberOfTiles++;
                break;
            case SKELETON:
                SkeletonTile skeleton=(SkeletonTile)tile;
                switch (skeleton.getAge()){
                    case ADULT:
                        switch (skeleton.getBodyPart()){
                            case UPPER:
                                icon=new ImageIcon(getClass().getResource("images_2020/skeleton_big_top.png"));
                                tileButton[numberOfTiles].getButton().setIcon(icon);
                                tileButton[numberOfTiles].setTile(skeleton);
                                skeletonPlace.add(tileButton[numberOfTiles].getButton());
                                break;
                            case LOWER:
                                icon=new ImageIcon(getClass().getResource("images_2020/skeleton_big_bottom.png"));
                                break;
                        }
                        break;
                    case CHILD:
                        switch (skeleton.getBodyPart()){
                            case UPPER:
                                icon=new ImageIcon(getClass().getResource("images_2020/skeleton_small_top.png"));
                                break;
                            case LOWER:
                                icon=new ImageIcon(getClass().getResource("images_2020/skeleton_small_bottom.png"));
                                break;
                        }
                        break;
                }
                tileButton[numberOfTiles].getButton().setIcon(icon);
                tileButton[numberOfTiles].setTile(skeleton);
                skeletonPlace.add(tileButton[numberOfTiles].getButton());
                numberOfTiles++;
                break;
            case LANDSLIDE:
                if(numberOfLandslides==16)
                    break;
                landslideTiles[numberOfLandslides]= new JLabel();
                icon=new ImageIcon(getClass().getResource("images_2020/landslide.png"));
                landslideTiles[numberOfLandslides].setIcon(icon);
                landslideTiles[numberOfLandslides].setSize(59, 59);
                gbc.gridx=numberOfLandslides/4;
                gbc.gridy=numberOfLandslides%4;
                gate.add(landslideTiles[numberOfLandslides], gbc);
                numberOfLandslides++;
                break;
        }
    }

    /**
     * Transformer initializes the board of the game
     * Postconditions the board has been initialized
     */
    public void initializeBoard(){
        icon = new ImageIcon(getClass().getResource("images_2020/background.png"));
        board.setIcon(icon);
        board.setBounds(0, 0, 800, 600);
        basicPane.add(board, Integer.valueOf(0));

        mosaicPlace.setBounds(23, 15, 234, 176);
        statuePlace.setBounds(543, 15, 234, 176);
        skeletonPlace.setBounds(543, 407, 234, 176);
        amphoraPlace.setBounds(23, 407, 234, 176);
        gate.setBounds(283, 237, 236, 236);

        mosaicPlace.setLayout(new GridLayout(3, 4));
        amphoraPlace.setLayout(new GridLayout(3, 4));
        statuePlace.setLayout(new GridLayout(3, 4));
        skeletonPlace.setLayout(new GridLayout(3, 4));
        gate.setLayout(gbl);

        basicPane.add(mosaicPlace, Integer.valueOf(1));
        basicPane.add(statuePlace, Integer.valueOf(1));
        basicPane.add(skeletonPlace, Integer.valueOf(1));
        basicPane.add(amphoraPlace, Integer.valueOf(1));
        basicPane.add(gate, Integer.valueOf(1));

        icon = new ImageIcon(getClass().getResource("images_for_board/mosaic.png"));
        mosaicPic.setIcon(icon);
        mosaicPic.setBounds(113, 191, 55, 55);
        basicPane.add(mosaicPic, Integer.valueOf(2));

        icon = new ImageIcon(getClass().getResource("images_for_board/amphora.png"));
        amphoraPic.setIcon(icon);
        amphoraPic.setBounds(113, 352, 55 ,55);
        basicPane.add(amphoraPic, Integer.valueOf(2));

        icon = new ImageIcon(getClass().getResource("images_for_board/skeleton.png"));
        skeletonPic.setIcon(icon);
        skeletonPic.setBounds(632, 352, 58, 58);
        basicPane.add(skeletonPic, Integer.valueOf(2));

        icon = new ImageIcon(getClass().getResource("images_for_board/caryatid.png"));
        caryatidPic.setIcon(icon);
        caryatidPic.setBounds(595, 191, 58, 58);
        basicPane.add(caryatidPic, Integer.valueOf(2));

        icon = new ImageIcon(getClass().getResource("images_for_board/sphinx.png"));
        sphinxPic.setIcon(icon);
        sphinxPic.setBounds(660, 191, 58, 58);
        basicPane.add(sphinxPic, Integer.valueOf(2));
    }

    /**
     * Transformer initializes the character panel
     * Postcondition the character panel has been initialized
     */
    public void initializeCharacters(){
        characters.setLayout(null);
        characters.setBackground(Color.RED);
        characters.setBounds(800, 80, 250, 400);

        archeologist.setBounds(0, 0, 125, 200);
        icon = new ImageIcon(getClass().getResource("images_2020/archaeologist.png"));
        archeologist.setIcon(icon);
        characters.add(archeologist);

        assistant.setBounds(125, 0, 125, 200);
        icon = new ImageIcon(getClass().getResource("images_2020/assistant.png"));
        assistant.setIcon(icon);
        characters.add(assistant);

        professor.setBounds(0, 200, 125, 200);
        icon = new ImageIcon(getClass().getResource("images_2020/professor.png"));
        professor.setIcon(icon);
        characters.add(professor);

        digger.setBounds(125, 200, 125, 200);
        icon = new ImageIcon(getClass().getResource("images_2020/digger.png"));
        digger.setIcon(icon);
        characters.add(digger);
        basicPane.add(characters);
    }

    /**
     * Transformer initializes the player's colections
     * Postconditions the players collections have been initialized
     */
    public void initializeCollections(){
        collectionContainer.setLayout(cl);
        Color color=Color.YELLOW;
        for(int i=0; i<4; i++){
            switch (i){
                case 0:
                    color=Color.YELLOW;
                    break;
                case 1:
                    color=Color.RED;
                    break;
                case 2:
                    color=Color.BLUE;
                    break;
                case 3:
                    color=Color.GREEN;
                    break;
            }
            collection[i]=new JPanel();
            collection[i].setBackground(color);
            collection[i].setLayout(new FlowLayout());
            collection[i].setBounds(0, 600, 1050, 135);
            collectionContainer.add(collection[i], ""+i);
        }
        collectionContainer.setBounds(0, 600, 1050, 135);
        basicPane.add(collectionContainer);
    }

    /**
     * Transformer initializes the panel with the control buttons. End turn and Draw tiles
     * Postconditions the control buttons have been inititalized
     */
    public void initializeControl(){
        control.setBounds(800, 480, 250, 120);
        control.setBackground(Color.black);
        control.setLayout(null);

        drawTiles.setFocusable(false);
        drawTiles.setBounds(50, 15, 150, 45);
        control.add(drawTiles);

        endTurn.setFocusable(false);
        endTurn.setBounds(50, 65, 150, 45);
        control.add(endTurn);
        basicPane.add(control);
    }

    /**
     * Transformer Gives the turn to the player with id 'id'
     * Postconditions the id-player's character panel and collection will be popped
     * @param id the players id
     */
    public void playNext(String id){
        player.setText(id);
        int temp = id.hashCode()%4; //to get a number between 0 and 3
        cl.show(collectionContainer, ""+temp);
        currentCollection=temp;
    }

    /**
     * Accessor returns the archaeologist button
     * Postconditions the archaeologist button has been returned
     * @return the archaeologist button
     */
    public JButton getArcheologistButton(){return this.archeologist;}

    /**
     * Accessor returns the assistant button
     * Postconditions the assistant button has been returned
     * @return the assistant button
     */
    public JButton getAssistantButton(){return this.assistant;}

    /**
     * Accessor returns the professor button
     * Postconditions the professor button has been returned
     * @return the professor button
     */
    public JButton getProfessorButton(){return this.professor;}

    /**
     * Accessor returns the digger button
     * Postconditions the digger button has been returned
     * @return the digger button
     */
    public JButton getDiggerButton(){return this.digger;}

    /**
     * Accessor returns the DrawTiles button
     * Postconditions the Draw Tiles button has been returned
     * @return the DrawTiles button
     */
    public JButton getDrawTiles(){return this.drawTiles;}

    /**
     * Accessor returns the End turn button
     * Postconditions the End Turn button has been returned
     * @return the End turn button
     */
    public JButton getEndTurn(){return this.endTurn;}

    /**
     * Accessor returns an array that contains all the tile button existing on the board
     * Postconditions a JButton array has been returned
     * @return an array that contains all the tile button existing on the board
     */
    public TileButton[] getTileButton(){return this.tileButton;}

    /**
     * Accessor returns the total number of the tiles
     * Postconditions an integer has been returned
     * @return the total number of the tiles
     */
    public int getNumberOfTiles() {return this.numberOfTiles;}

    /**
     * Transformer Updates the players collection panel with the collected tile
     * @param tileButtonIndex the index of the button the player has collected
     */
    public void updatePlayersCollection(int tileButtonIndex){
        JLabel label = new JLabel();
        label.setIcon(tileButton[tileButtonIndex].getButton().getIcon());
        collection[currentCollection].add(label);
    }
}