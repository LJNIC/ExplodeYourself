package com.ljnic;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.UUID;


public class ToggleExplosionCommand implements CommandExecutor{

    private Exploder plugin;
    private CommandSpec toggleCommand;

    public ToggleExplosionCommand(Exploder plugin){
        this.plugin = plugin;
        toggleCommand = CommandSpec.builder()
                .description(Text.of("Toggle blowing up"))
                .executor(this::execute)
                .build();
    }
    private static boolean toggled = false;


    public static boolean getToggled(){
        return toggled;
    }

    public CommandSpec getToggleCommand() {
        return toggleCommand;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException{
        toggled = !toggled;
        return CommandResult.success();
    }
}
