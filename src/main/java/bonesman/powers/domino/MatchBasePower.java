package bonesman.powers.domino;

import bonesman.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import bonesman.util.DominoUtils;

import static bonesman.BonesmanMod.makeID;

public abstract class MatchBasePower extends BasePower {
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public MatchBasePower(String powerId, AbstractCreature owner) {
        super(powerId, TYPE, TURN_BASED, owner, 0);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if (isPlayer) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
