package com.stalemated.shutupmcd.mixin.client;

import chronosacaria.mcdar.api.CleanlinessHelper;
import com.stalemated.shutupmcd.config.ShutUpMCDConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = {CleanlinessHelper.class})
public class CleanlinessHelperMcdarMixin {

    @Redirect(
            method = "createLoreTTips",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"),
            require = 0
    )
    private static boolean interceptAddTooltip(List<Object> instance, Object textObj) {
        if (!ShutUpMCDConfig.showMCDARFlavorLore) return false;
        return instance.add(textObj);
    }
}