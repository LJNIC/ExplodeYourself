package com.ljnic;

import javafx.scene.control.Toggle;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.blockray.BlockRay;
import org.spongepowered.api.util.blockray.BlockRayHit;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.explosion.Explosion;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.Optional;

public class ExplodeHandler {

   private ExplodeYourself plugin;
   private Logger logger;

   public ExplodeHandler(ExplodeYourself plugin){
       this.plugin = plugin;
       this.logger = plugin.getLogger();

   }

    @Listener(order = Order.POST)
    public void onAttack(InteractBlockEvent.Primary event, @First Player player){
        BlockRay<World> pointer = BlockRay.from(player).skipFilter(BlockRay.continueAfterFilter(BlockRay.onlyAirFilter(), 1)).build();
        Optional<BlockRayHit<World>> hitOpt = pointer.end();
        if(hitOpt.isPresent()){
            BlockRayHit<World> hit = hitOpt.get();
            if(ToggleExplosionCommand.getToggled()) {
                World world = player.getWorld();
                Explosion e = Explosion.builder().location(hit.getLocation()).radius(SetRadiusCommand.radius).shouldPlaySmoke(true).shouldBreakBlocks(true).build();
                world.triggerExplosion(e);
            }
        }
    }

}
