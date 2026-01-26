package bonesman.cards;

import bonesman.powers.domino.MatchBasePower;
import bonesman.util.CardStats;
import bonesman.util.DominoUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDomino extends BaseCard{
    protected int left;
    protected int right;
    protected float matchMult;

    public BaseDomino(String ID, CardStats info, int left, int right, float matchMult) {
        super(ID, info);
        this.left = left;
        this.right = right;
        this.matchMult = matchMult;
    }

    protected abstract void leftDominoAction(AbstractPlayer p, AbstractMonster m, float mult);
    protected abstract void rightDominoAction(AbstractPlayer p, AbstractMonster m, float mult);

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String matchLeftPower = makeID(DominoUtils.getMatchPowerName(this.left));
        String matchRightPower = makeID(DominoUtils.getMatchPowerName(this.right));
        List<String> matchPowers = getPlayerMatchPowers(p);
        boolean leftMatched, rightMatched;
        int spaceNeeded;

        boolean isDouble = this.left == this.right;

        if (isDouble){
            spaceNeeded = this.left > 0? 1: 0;
            leftMatched = rightMatched = matchPowers.remove(matchLeftPower);
        } else {
            spaceNeeded = (this.left > 0? 1: 0) + (this.right > 0? 1: 0);
            leftMatched = matchPowers.remove(matchLeftPower);
            rightMatched = matchPowers.remove(matchRightPower);
        }

        boolean leftHeavy = this.left >= this.right;
        boolean forceActive = !leftMatched && !rightMatched && (customVar("ForceMatch") == 1);

        if (leftHeavy){
            leftMatched = resolveSide(p, m, leftMatched, matchLeftPower, matchPowers, forceActive, spaceNeeded, true, false);
            rightMatched = resolveSide(p, m, rightMatched, matchRightPower, matchPowers, forceActive, spaceNeeded, false, isDouble);
        } else {
            rightMatched = resolveSide(p, m, rightMatched, matchRightPower, matchPowers, forceActive, spaceNeeded, false, false);
            leftMatched = resolveSide(p, m, leftMatched, matchLeftPower, matchPowers, forceActive, spaceNeeded, true, isDouble);
        }

        if (this.left == this.right && !leftMatched)
            addToBot(new ApplyPowerAction(p, p, new MatchBasePower(matchLeftPower, p)));
        else {
            if (!leftMatched && this.left > 0)
                addToBot(new ApplyPowerAction(p, p, new MatchBasePower(matchLeftPower, p)));
            if (!rightMatched && this.right > 0)
                addToBot(new ApplyPowerAction(p, p, new MatchBasePower(matchRightPower, p)));
        }
    }

    private List<String> getPlayerMatchPowers(AbstractPlayer p) {
        List<String> matches = new ArrayList<>();
        for (AbstractPower ap : p.powers) {
            if (ap instanceof MatchBasePower)
                matches.add(ap.ID);
        }
        return matches;
    }

    private boolean resolveSide(AbstractPlayer p, AbstractMonster m,
                                boolean specificMatched, String specificPowerId,
                                List<String> matchPowers, boolean forceActive,
                                int spaceNeeded, boolean isLeft, boolean alreadyMatched) {

        int currentAvailable = matchPowers.size();

        if (specificMatched) {
            if (!alreadyMatched)
                addToBot(new RemoveSpecificPowerAction(p, p, specificPowerId));
            runDominoAction(p, m, this.matchMult, isLeft);
            return true;
        }

        if (forceActive && !matchPowers.isEmpty()) {
            if (!alreadyMatched)
                addToBot(new RemoveSpecificPowerAction(p, p, matchPowers.remove(0)));
            runDominoAction(p, m, this.matchMult, isLeft);
            return true;
        }

        if (currentAvailable + spaceNeeded > 2) {
            addToBot(new RemoveSpecificPowerAction(p, p, matchPowers.remove(0)));
            runDominoAction(p, m, 0.75f, isLeft);
            return true;
        }

        runDominoAction(p, m, 1.0f, isLeft);
        return false;
    }

    private void runDominoAction(AbstractPlayer p, AbstractMonster m, float mult, boolean isLeft) {
        if (isLeft) {
            leftDominoAction(p, m, mult);
        } else {
            rightDominoAction(p, m, mult);
        }
    }
}
