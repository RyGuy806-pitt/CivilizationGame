package tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class ActionTool extends SelectionTool {
    private Game game;
    private Position p;

    public ActionTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;
    }
    @Override
    public void mouseDown(MouseEvent e, int x, int y){
        super.mouseDown(e, x, y);
        Drawing model = editor().drawing();
        model.lock();
        System.out.println("inside mouse down ");
        Figure figure = model.findFigure(x, y);
        if ( figure != null && figure.getClass() == UnitFigure.class && e.isShiftDown() ) {
                    System.out.println("inside mouse down if");
                    p = GfxConstants.getPositionFromXY(x, y);
                    // game.performUnitActionAt(p);
        }
    }
    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        if (e.isShiftDown()) {
            if (game.getUnitAt(p) != null) {
                game.performUnitActionAt(p);
            }
        }
    }

    //@Override
    //protected Tool createAreaTracker() { return new NullTool(); }
}
