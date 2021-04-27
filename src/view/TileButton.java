package view;
import model.tiles.Tile;
import javax.swing.*;

public class TileButton {
    private JButton button;
    private Tile tile;

    public TileButton(){
        button=new JButton();
    }

    public void setTile(Tile tile){this.tile=tile;}
    public Tile getTile(){return this.tile;}
    public JButton getButton(){return this.button;}
}
