package tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.SelectionTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EndOfTurnTool extends SelectionTool {
    private Game game;
    public EndOfTurnTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;
    }
    public void mouseDown(MouseEvent e, int x, int y) {
        Drawing model = editor().drawing();

        model.lock();

        if(y >= GfxConstants.TURN_SHIELD_Y
                && y <= GfxConstants.TURN_SHIELD_Y + 39
                && x >= GfxConstants.TURN_SHIELD_X
                && x <= GfxConstants.TURN_SHIELD_X + 27) {

            game.endOfTurn();
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        editor().drawing().unlock();
    }
}
