package controller;
import model.board.*;
import model.enumerators.CharacterType;
import model.enumerators.TileType;
import model.exceptions.*;
import model.player.*;
import model.tiles.*;
import view.Gui;
import view.TileButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private final Board board;
    private Player current;
    private Gui gui;
    private int playingPlayer=0;
    private Tile[] tilesDrawnInRound;
    private boolean hasDrawnTiles = false;
    private final TileType[] professorCollection = new TileType[4];

    /**
     * Constructor creates a new Controller, initializes and starts the game
     * Postcondition The game has been initialized
     * Preconditions the tiles for the initialization must exist in the bag
     * @throws TileDoesNotExistException if a tile does not exist during the board initialization
     */
    public Controller() throws TileDoesNotExistException {
        board = new Board();
        for(int i=0; i<4; i++){
            try {
                board.addPlayer(new Player());
            }catch (MoreThanFourPlayersException e){
                return;
            }
        }
        current = whoPlaysFirst();
        gui = new Gui();
        for(int i=0; i<gui.getTileButton().length; i++)
            gui.getTileButton()[i]=new TileButton();
        tilesDrawnInRound=board.getRoundCollectedTiles();  //now contains the 4 tiles of the board initialization
        for(int i=0; i<4; i++) {
            gui.addTile(tilesDrawnInRound[i]);
        }
        gui.playNext(current.getPlayersId());
        setListeners();
    }

    /**
     * Accessor returns a random player who will play first
     * Postconditions a random player has been returned
     * @return a random player who will play first
     */
    public Player whoPlaysFirst(){
        Random random = new Random();
        playingPlayer=random.nextInt(4);
        return board.getPlayer(playingPlayer);
    }

    /**
     * Accessor give the turn at the next player to play
     * Postcondition the playing player will be the next one
     */
    public void nextPlayerTurn(){
        board.getPlayer(playingPlayer).FinishTurn();
        playingPlayer++;
        if(playingPlayer==4)
            playingPlayer=0;
        board.getPlayer(playingPlayer).Play();
        current=board.getPlayer(playingPlayer);
        hasDrawnTiles=false;
        if(!board.hasFinished()) {
            gui.getAssistantButton().setEnabled(!current.getAssistant().isUsed());
            gui.getArcheologistButton().setEnabled(!current.getArcheologist().isUsed());
            gui.getProfessorButton().setEnabled(!current.getProfessor().isUsed());
            gui.getDiggerButton().setEnabled(!current.getDigger().isUsed());
        }
        gui.playNext(current.getPlayersId());
    }

    /**
     * Accessor Draws tiles from an area
     * Postcondition the tiles have been drawn
     */
    public void drawTiles(){
        if(board.hasFinished())
            terminate();
        try {
            board.drawTiles();
        }catch (BagEmptyException | GateClosedException e){
            terminate();
        }
        tilesDrawnInRound=board.getRoundCollectedTiles();
        for(int i=0; i<4; i++){
            gui.addTile(tilesDrawnInRound[i]);
        }
        gui.playNext(current.getPlayersId());  // to refresh
    }

    /**
     * Transformer sets the listeners to all the buttons of the view
     * Postconditions the listeners have been set
     */
    public void setListeners(){
        gui.getArcheologistButton().addActionListener(new characterListeners());
        gui.getAssistantButton().addActionListener(new characterListeners());
        gui.getProfessorButton().addActionListener(new characterListeners());
        gui.getDiggerButton().addActionListener(new characterListeners());
        gui.getEndTurn().addActionListener(new controlButtonsListeners());
        gui.getDrawTiles().addActionListener(new controlButtonsListeners());
        for(int i=0; i<gui.getTileButton().length; i++)
            gui.getTileButton()[i].getButton().addActionListener(new TileListener());
    }

    /**
     * Transformer calculates the points for every player and sets every players TotalPoints field with
     * His total points
     * Postconditions the total points for every player have been calculated
     */
    public void calculatePoints(){
        int maxCaryatid=-1;
        int minCaryatid=13;
        int maxSphinx=-1;
        int minSphinx=13;

        for(int i=0; i<4; i++){
            //find max caryatid player
            if(board.getPlayer(i).getNumberOfStatues()[0]>maxCaryatid)
                maxCaryatid=board.getPlayer(i).getNumberOfStatues()[0];
            //find min caryatid player
            if(board.getPlayer(i).getNumberOfStatues()[0]<minCaryatid)
                minCaryatid=board.getPlayer(i).getNumberOfStatues()[0];
        }

        for(int i=0; i<4; i++){
            //find max sphinx player
            if(board.getPlayer(i).getNumberOfStatues()[1]>maxSphinx)
                maxSphinx=board.getPlayer(i).getNumberOfStatues()[1];
            //find min sphinx player
            if(board.getPlayer(i).getNumberOfStatues()[1]<minSphinx)
                minSphinx=board.getPlayer(i).getNumberOfStatues()[1];
        }

        for(int i=0; i<4; i++){
            if(board.getPlayer(i).getNumberOfStatues()[0]==0)
                board.getPlayer(i).setCaryatidPoints(0);
            else if(board.getPlayer(i).getNumberOfStatues()[0]==maxCaryatid)
                board.getPlayer(i).setCaryatidPoints(6);
            else if(board.getPlayer(i).getNumberOfStatues()[0]==minCaryatid)
                board.getPlayer(i).setCaryatidPoints(0);
            else
                board.getPlayer(i).setCaryatidPoints(3);
        }

        for(int i=0; i<4; i++){
            if(board.getPlayer(i).getNumberOfStatues()[1]==0)
                board.getPlayer(i).setSphinxPoints(0);
            else if(board.getPlayer(i).getNumberOfStatues()[1]==maxSphinx)
                board.getPlayer(i).setSphinxPoints(6);
            else if(board.getPlayer(i).getNumberOfStatues()[1]==minSphinx)
                board.getPlayer(i).setSphinxPoints(0);
            else
                board.getPlayer(i).setSphinxPoints(3);
        }

        //set points for every player
        for(int i=0; i<4; i++){
            board.getPlayer(i).setAmpMosSkelPoints();
            board.getPlayer(i).setTotalPoints();
        }
    }

    /**
     * Accessor terminates the game and prints the winner
     * Postconditon the game has been finished and the winner has been printed
     */
    public void terminate(){
        gui.buttonState(false);
        gui.getEndTurn().setEnabled(true);
        calculatePoints();

        //sort the rank
        int maxPoints=-1;
        for(int i=0; i<4; i++)
            if(board.getPlayer(i).getTotalPoints()>maxPoints)
                maxPoints=board.getPlayer(i).getTotalPoints();
        ArrayList<Player> rank = new ArrayList<>();
        for(int i=0; i<4; i++){
            boolean found=false;
            for(int j=0; j<rank.size(); j++){
                if(rank.get(j).getTotalPoints()<board.getPlayer(i).getTotalPoints()){
                    rank.add(j, board.getPlayer(i));
                    found=true;
                    break;
                }
            }
            if(!found)
                rank.add(board.getPlayer(i));
        }
        gui.showRank(rank);
    }

    /**
     * this class implements the action listeners for the tiles buttons
     */
    public class TileListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent tile){
            if(!hasDrawnTiles)
                return;
            if(current.getTilesToCollect()==current.getHasCollected())
                return;  //has collected all the tiles he was allowed

            for(int i=0; i<gui.getNumberOfTiles(); i++){
                if(tile.getSource()==gui.getTileButton()[i].getButton()){
                    if(current.CanUseCharacter()) {  //if no characters that means player is collecting the first 2 tiles
                        if(current.getHasCollected()!=0) {  //if !=0 player has collected one so we know now the chosen area
                            if (gui.getTileButton()[i].getTile().getType() != current.getChosenArea())
                                return; //player has to collect only from his chosen area
                        }else{
                            switch (gui.getTileButton()[i].getTile().getType()){
                                case AMPHORA:
                                    if(board.getAmphoraTilesOnBoard()==1)
                                        current.setTilesToCollect(1);
                                    break;
                                case MOSAIC:
                                    if(board.getMosaicTilesOnBoard()==1)
                                        current.setTilesToCollect(1);
                                    break;
                                case STATUE:
                                    if(board.getStatueTilesOnBoard()==1)
                                        current.setTilesToCollect(1);
                                    break;
                                case SKELETON:
                                    if(board.getSkeletonTilesOnBoard()==1)
                                        current.setTilesToCollect(1);
                                    break;
                            }
                        }
                    }else{
                        switch (current.getLastUsedCharacter()){
                            case ASSISTANT:
                                if(current.getHasCollected()!=0)  //if !=0 player has collected one so we know now the chosen area
                                    if(gui.getTileButton()[i].getTile().getType()!=current.getChosenArea())
                                        return; //player has to collect only from his chosen area
                                break;
                            case ARCHEOLOGIST:
                                if(current.getHasCollected()==0) {  //has not collected tiles with the character yet
                                    if (gui.getTileButton()[i].getTile().getType() == current.getChosenArea())
                                        return;
                                }else{
                                    if(gui.getTileButton()[i].getTile().getType()!=current.getChosenArea())
                                        return;
                                }
                                break;
                            case PROFESSOR:
                                boolean found=false;
                                for(int j=0; j<4; j++){
                                    if(professorCollection[j]==gui.getTileButton()[i].getTile().getType()) {
                                        found = true;
                                        professorCollection[j]=null;
                                        break;
                                    }
                                }
                                if(!found)
                                    return;
                                break;
                            case DIGGER:
                                if(gui.getTileButton()[i].getTile().getType()!=current.getChosenArea())
                                    return;
                                break;
                        }
                    }
                    // remove the button from the area
                    try {
                        board.getPlayer(playingPlayer).CollectTile(board.popTileFromBoard(gui.getTileButton()[i].getTile()));
                    }catch (TileDoesNotExistException e){
                        return;
                    }
                    Container container=gui.getTileButton()[i].getButton().getParent();
                    container.remove(gui.getTileButton()[i].getButton());
                    container.revalidate();
                    container.repaint();
                    gui.updatePlayersCollection(i);
                    break;
                }
            }
        }
    }

    /**
     * this class contains the action listeners for the control buttons
     */
    class controlButtonsListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent controlButton){
            if(controlButton.getSource()==gui.getEndTurn()){
                nextPlayerTurn();
                if(!board.hasFinished())
                    gui.getDrawTiles().setEnabled(true);
            }else if(controlButton.getSource()==gui.getDrawTiles()){
                drawTiles();
                hasDrawnTiles=true;
                gui.getDrawTiles().setEnabled(false);
                if(board.hasFinished())
                    terminate();
            }
        }
    }

    /**
     * this class contains the listeners for the Character buttons
     */
    class characterListeners implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent character) {
            if(!hasDrawnTiles)
                return;
            if(!current.CanUseCharacter())
                return;
            if(current.getHasCollected()!=current.getTilesToCollect())       //first collect then chose
                return;
            if(character.getSource()==gui.getAssistantButton()) {
                try {
                    current.useCharacter(CharacterType.ASSISTANT);
                }catch (CharacterAlreadyUsedException e){
                    return;
                }
                if(current.getAssistant().isUsed())
                    gui.getAssistantButton().setEnabled(false);
            }else if(character.getSource()==gui.getArcheologistButton()) {
                try {
                    current.useCharacter(CharacterType.ARCHEOLOGIST);
                }catch (CharacterAlreadyUsedException e){
                    return;
                }
                if(current.getArcheologist().isUsed())
                    gui.getArcheologistButton().setEnabled(false);
            }else if(character.getSource()==gui.getProfessorButton()) {
                try {
                    current.useCharacter(CharacterType.PROFESSOR);
                }catch (CharacterAlreadyUsedException e){
                    return;
                }
                professorCollection[0]=TileType.AMPHORA;
                professorCollection[1]=TileType.MOSAIC;
                professorCollection[2]=TileType.STATUE;
                professorCollection[3]=TileType.SKELETON;

                if(current.getProfessor().isUsed())
                    gui.getProfessorButton().setEnabled(false);
            }else if(character.getSource()==gui.getDiggerButton()){
                try {
                    current.useCharacter(CharacterType.DIGGER);
                }catch (CharacterAlreadyUsedException e){
                    return;
                }
                if(current.getDigger().isUsed())
                    gui.getDiggerButton().setEnabled(false);
            }
        }
    }
}