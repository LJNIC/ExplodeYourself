package com.ljnic;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class SetRadiusCommand implements CommandExecutor {
    static int radius = 5;
    private Exploder plugin;
    private CommandSpec setRadiusCommand;

    public SetRadiusCommand(Exploder plugin){
        this.plugin = plugin;
        setRadiusCommand = CommandSpec.builder()
                .description(Text.of("Sets the explosion's radius"))
                .arguments(GenericArguments.integer(Text.of("radius")))
                .executor(this::execute)
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException{
        radius = args.<Integer>getOne("radius").get();
        return CommandResult.success();
    }

    public CommandSpec getSetRadiusCommand() {
        return setRadiusCommand;
    }
}
