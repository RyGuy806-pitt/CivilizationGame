package tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.SelectionTool;

public class CompositionTool extends SelectionTool {
    private Game game;
    public CompositionTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;
    }
}
