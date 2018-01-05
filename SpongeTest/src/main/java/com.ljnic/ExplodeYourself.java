package com.ljnic;

import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.explosion.Explosion;
import java.io.IOException;
import java.nio.file.Path;


@Plugin(id="spongetest", name="Sponge Test", version = "1.0.0")
public class ExplodeYourself {

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultConig;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    @Inject
    private Logger logger;

    private ConfigurationNode config;

    @Listener
    public void preInit(GamePreInitializationEvent event){
        try{
            config = loader.load();

            if(!defaultConig.toFile().exists()){
                config.getNode("placeholder").setValue(true);
                loader.save(config);
            }
        }catch(IOException e){
            logger.warn("Error loading default config!");
        }
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

    @Listener(order = Order.POST)
    public void onAttack(InteractBlockEvent.Primary event, @First Player player){
        World world = player.getWorld();
        Explosion e = Explosion.builder().location(player.getLocation()).radius(5).shouldPlaySmoke(true).build();
        Entity tnt = world.createEntity(EntityTypes.PRIMED_TNT, player.getLocation().getPosition());
        logger.info("Player attacked");
        world.spawnEntity(e);
    }



}
