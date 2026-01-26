package bonesman.powers.domino;

import bonesman.powers.BasePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import bonesman.util.DominoUtils;

import static bonesman.BonesmanMod.makeID;

public class MatchBasePower extends BasePower {
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public MatchBasePower(int value, AbstractCreature owner) {
        super(DominoUtils.getMatchPowerName(value), TYPE, TURN_BASED, owner, 0);
    }
    public MatchBasePower(String powerID, AbstractCreature owner) {
        super(powerID, TYPE, TURN_BASED, owner, 0);
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
