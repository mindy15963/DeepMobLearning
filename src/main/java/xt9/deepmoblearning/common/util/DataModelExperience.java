package xt9.deepmoblearning.common.util;

import xt9.deepmoblearning.DeepConstants;

/**
 * Created by xt9 on 2017-06-14.
 */
public class DataModelExperience {
    // Todo [Not urgent] configurable exp
    // Kills required per  tier 6, 12, 30, 50
    public static final int[] maxExperience = {48, 120, 420, 1100};
    public static final int[] killMultiplier = {8, 10, 14, 22, 22};
    // Simulations have no multipliers, they are always 1x

    /* tier is CURRENT tier, kc is killcount for CURRENT tier, sc is simulationcount for CURRENT  tier */
    public static boolean shouldIncreaseTier(int tier, int kc, int sc) {
        if(tier == DeepConstants.MOB_CHIP_MAXIMUM_TIER) {
            return false;
        }
        int roof = maxExperience[tier];
        int killExperience = kc * killMultiplier[tier];

        return killExperience + sc >= roof;
    }

    public static double getCurrentTierKillCountWithSims(int tier, int kc, int sc) {
        if(tier == DeepConstants.MOB_CHIP_MAXIMUM_TIER) {
            return 0;
        }
        return kc + ((double) sc / killMultiplier[tier]);
    }

    public static int getCurrentTierSimulationCountWithKills(int tier, int kc, int sc) {
        if(tier == DeepConstants.MOB_CHIP_MAXIMUM_TIER) {
            return 0;
        }
        return sc + (kc * killMultiplier[tier]);
    }

    public static double getKillsToNextTier(int tier, int kc, int sc) {
        if(tier == DeepConstants.MOB_CHIP_MAXIMUM_TIER) {
            return 0;
        }
        int killRoof = getTierRoof(tier, true);
        return killRoof - getCurrentTierKillCountWithSims(tier, kc, sc);
    }

    public static int getSimulationsToNextTier(int tier, int kc, int sc) {
        if(tier == DeepConstants.MOB_CHIP_MAXIMUM_TIER) {
            return 0;
        }
        int roof = getTierRoof(tier, false);
        return roof - getCurrentTierSimulationCountWithKills(tier, kc, sc);
    }

    public static int getTierRoof(int tier, boolean asKills) {
        if(tier == DeepConstants.MOB_CHIP_MAXIMUM_TIER) {
            return 0;
        }
        if(!asKills) {
            return maxExperience[tier];
        } else {
            return maxExperience[tier] / killMultiplier[tier];
        }
    }

    public static int getKillMultiplier(int tier) {
        return killMultiplier[tier];
    }
}