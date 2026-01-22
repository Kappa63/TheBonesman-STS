package bonesman.powers.domino;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static bonesman.BonesmanMod.makeID;

public class MatchOnePower extends MatchBasePower {
    public static final String POWER_ID = makeID(MatchOnePower.class.getSimpleName());

    public MatchOnePower(AbstractCreature owner) {
        super(POWER_ID, owner);
    }
}
