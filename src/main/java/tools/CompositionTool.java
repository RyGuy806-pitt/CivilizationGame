package tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;
import minidraw.standard.handlers.StandardRubberBandSelectionStrategy;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class CompositionTool extends SelectionTool {
    private Game game;
    private Tool tool;
    public CompositionTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;
        tool = new NullTool();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);

        if(e.isShiftDown()) {
            tool = new ActionTool(game, editor());
            tool.mouseDown(e, x, y);
        } else if(y >= GfxConstants.MAP_OFFSET_Y
                && y <= GfxConstants.MAP_OFFSET_Y+GameConstants.WORLDSIZE*GfxConstants.TILESIZE
                && x >= GfxConstants.MAP_OFFSET_X
                && x <= GfxConstants.MAP_OFFSET_X+GameConstants.WORLDSIZE*GfxConstants.TILESIZE) {
            tool = new SetFocusTool(game, editor());
            tool.mouseDown(e, x, y);
            Figure figure = editor().drawing().findFigure(x, y);
            if(figure != null && figure.getClass().equals(UnitFigure.class)) {
                tool = new UnitMoveTool(game, editor());
                tool.mouseDown(e, x, y);
            }
        } else {
            if(y <= GfxConstants.UNIT_SHIELD_Y - 20) {
                tool = new EndOfTurnTool(game, editor());
            }
            tool.mouseDown(e, x, y);
        }
    }
    public void mouseUp(MouseEvent e, int x, int y) {
        tool.mouseUp(e, x, y);
    }

}
