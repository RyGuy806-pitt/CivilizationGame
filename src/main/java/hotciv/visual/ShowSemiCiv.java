package hotciv.visual;

import VersionControl.SemiVersion;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.StubGame2;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import tools.CompositionTool;

public class ShowSemiCiv {

    public static void main(String[] args) {
        GameImpl game = new GameImpl(new SemiVersion());

        DrawingEditor editor =
                new MiniDrawApplication( "Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        // TODO: Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool( new CompositionTool(game, editor) );
        System.out.println("CompositionTool: valid tool selected");
    }
}
