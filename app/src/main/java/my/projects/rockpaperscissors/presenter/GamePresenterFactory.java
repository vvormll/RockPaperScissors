package my.projects.rockpaperscissors.presenter;

import java.util.concurrent.TimeUnit;

import my.projects.rockpaperscissors.model.GameMode;
import my.projects.rockpaperscissors.model.info.GameInfo;
import my.projects.rockpaperscissors.view.GameView;

public class GamePresenterFactory {

    private static boolean isTestMode;

    public static void setTestMode(boolean isTestMode) {
        GamePresenterFactory.isTestMode = isTestMode;
    }

    public static GamePresenter buildGamePresenter() {
        if (isTestMode) {
            return new TestGamePresenterImpl();
        } else {
            return new GamePresenterImpl();
        }
    }

    public static GamePresenter buildGamePresenter(GameMode gameMode) {
        if (isTestMode) {
            return new TestGamePresenterImpl();
        } else {
            return new GamePresenterImpl(gameMode);
        }
    }

    public static class TestGamePresenterImpl extends GamePresenterImpl {
        public boolean onStartedGameCalled;
        public boolean onQuitGameCalled;
        public boolean onAttachViewCalled;
        public boolean onDetachViewCalled;
        public boolean onPickedSymbolCalled;
        public boolean onRestoreStateCalled;

        @Override
        public void onStartedGame() {
            onStartedGameCalled = true;
            super.onStartedGame();
        }

        @Override
        public void onQuitGame() {
            onQuitGameCalled = true;
            super.onQuitGame();
        }

        @Override
        public void onAttachView(GameView gameView) {
            onAttachViewCalled = true;
            super.onAttachView(gameView);
        }

        @Override
        public void onDetachView() {
            onDetachViewCalled = true;
            super.onDetachView();
        }

        @Override
        public void onPickedSymbol(String symbolString) {
            onPickedSymbolCalled = true;
            super.onPickedSymbol(symbolString);
        }

        @Override
        public void onRestoreState(GameInfo gameInfo) {
            onRestoreStateCalled = true;
            super.onRestoreState(gameInfo);
        }

        @Override
        public GameInfo getGameInfo() {
            return super.getGameInfo();
        }

        @Override
        public boolean isViewAttached() {
            return super.isViewAttached();
        }
    }

}