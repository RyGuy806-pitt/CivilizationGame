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
    private ActionTool ActionTool;
    private EndOfTurnTool EndOfTurnTool;
    private SetFocusTool SetFocusTool;
    private UnitMoveTool UnitMoveTool;
    public CompositionTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;

        ActionTool = new ActionTool(game, editor);
        EndOfTurnTool = new EndOfTurnTool(game, editor);
        SetFocusTool = new SetFocusTool(game, editor);
        UnitMoveTool = new UnitMoveTool(game, editor);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);

        ActionTool.mouseDown(e,x,y);
        EndOfTurnTool.mouseDown(e,x,y);
        SetFocusTool.mouseDown(e,x,y);
        UnitMoveTool.mouseDown(e,x,y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e,x,y);

        UnitMoveTool.mouseUp(e,x,y);
    }

//    public Position getCityInFocus() {
//        return cityInFocus;
//    }
//
//    public void setCityInFocus(Position cityInFocus) {
//        this.cityInFocus = cityInFocus;
//    }
}
