package bonesman.powers.domino;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static bonesman.BonesmanMod.makeID;

public class MatchFivePower extends MatchBasePower {
    public static final String POWER_ID = makeID(MatchFivePower.class.getSimpleName());

    public MatchFivePower(AbstractCreature owner) {
        super(POWER_ID, owner);
    }
}
