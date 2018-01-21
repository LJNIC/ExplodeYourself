package com.ljnic;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventManager;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.explosion.Explosion;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;


@Plugin(id="explodeyourself", name="Explode Yourself", version = "1.0.0")
public class ExplodeYourself {


    @Inject
    private PluginManager pluginManager;

    @Inject
    private Game game;

    @Inject
    private Logger logger;

    private ExplodeHandler explodeHandler;
    private ToggleExplosionCommand toggleExplosionCommand;
    private SetRadiusCommand setRadiusCommand;


    @Listener
    public void preInit(GamePreInitializationEvent event){
        explodeHandler = new ExplodeHandler(this);
        toggleExplosionCommand = new ToggleExplosionCommand(this);
        setRadiusCommand = new SetRadiusCommand(this);
    }

    @Inject
    public ExplodeYourself() {

    }

    public PluginManager getPluginManager(){
        return pluginManager;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event){
        logger.info("hello");
    }

    @Listener
    public void onServerStop(GameStoppedServerEvent event){
        logger.info("stopping...");
    }

    @Listener
    public void onServerAboutToStart(GameAboutToStartServerEvent event) {
        logger.info("Hi");
    }

    @Listener
    public void init(GameInitializationEvent event){
        game.getEventManager().registerListeners(this, explodeHandler);
        game.getCommandManager().register(this, toggleExplosionCommand.getToggleCommand(), "toggle");
        game.getCommandManager().register(this, setRadiusCommand.getSetRadiusCommand(), "setradius");
    }

    public Logger getLogger(){
        return logger;
    }

}
