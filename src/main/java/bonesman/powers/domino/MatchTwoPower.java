package bonesman.powers.domino;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static bonesman.BonesmanMod.makeID;

public class MatchTwoPower extends MatchBasePower {
    public static final String POWER_ID = makeID(MatchTwoPower.class.getSimpleName());

    public MatchTwoPower(AbstractCreature owner) {
        super(POWER_ID, owner);
    }
}
