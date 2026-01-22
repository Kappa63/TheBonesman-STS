package bonesman.powers.domino;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static bonesman.BonesmanMod.makeID;

public class MatchThreePower extends MatchBasePower {
    public static final String POWER_ID = makeID(MatchThreePower.class.getSimpleName());

    public MatchThreePower(AbstractCreature owner) {
        super(POWER_ID, owner);
    }
}
