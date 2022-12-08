package hotciv.visual;

import VersionControl.AlphaVersion;
import VersionControl.BetaVersion;
import VersionControl.EpsilonVersion;
import VersionControl.SemiVersion;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.stub.StubGame2;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import tools.CompositionTool;

public class ShowSemiCiv {
    public static void main(String[] args) {
            Game game = new GameImpl(new SemiVersion());

            DrawingEditor editor =
                    new MiniDrawApplication( "SemiCiv",
                            new HotCivFactory4(game) );
            editor.open();

            editor.setTool(new CompositionTool(game, editor));
        }

    }
