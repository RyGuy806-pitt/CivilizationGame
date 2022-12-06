package tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.RubberBandSelectionStrategy;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class UnitMoveTool extends SelectionTool {
    private Game game;
    private Unit unit;
    private Position from;

    public UnitMoveTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;

    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);

        if (!(draggedFigure instanceof UnitFigure)) {
            fChild = new NullTool();
            return;
        }

        from = GfxConstants.getPositionFromXY(x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e, x, y);
        game.moveUnit(from, GfxConstants.getPositionFromXY(x, y));
    }

    @Override
    protected Tool createAreaTracker() { return new NullTool(); }
}
