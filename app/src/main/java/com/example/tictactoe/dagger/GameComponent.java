package com.example.tictactoe.dagger;

import com.example.tictactoe.model.Game;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface GameComponent {

    Game getGame();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder name1(@Named("name one") String name1);

        @BindsInstance
        Builder name2(@Named("name two") String name2);

        GameComponent build();

    }

}
