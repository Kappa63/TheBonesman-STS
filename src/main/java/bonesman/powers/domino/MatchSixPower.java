package bonesman.powers.domino;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static bonesman.BonesmanMod.makeID;

public class MatchSixPower extends MatchBasePower {
    public static final String POWER_ID = makeID(MatchSixPower.class.getSimpleName());

    public MatchSixPower(AbstractCreature owner) {
        super(POWER_ID, owner);
    }
}
