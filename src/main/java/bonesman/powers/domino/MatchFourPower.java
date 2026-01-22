package bonesman.powers.domino;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static bonesman.BonesmanMod.makeID;

public class MatchFourPower extends MatchBasePower {
    public static final String POWER_ID = makeID(MatchFourPower.class.getSimpleName());

    public MatchFourPower(AbstractCreature owner) {
        super(POWER_ID, owner);
    }
}
