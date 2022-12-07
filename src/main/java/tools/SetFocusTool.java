package tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    public SetFocusTool(Game game, DrawingEditor editor) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);
        game.setTileFocus(GfxConstants.getPositionFromXY(x, y));
    }
}
