package bonesman.cards.starting;

import bonesman.cards.BaseCard;
import bonesman.character.BonesmanCharacter;
import bonesman.powers.domino.MatchBasePower;
import bonesman.powers.domino.MatchFourPower;
import bonesman.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class VierThrow extends BaseCard {
    public static final String ID = makeID(VierThrow.class.getSimpleName());

    private static final CardStats info = new CardStats(
            BonesmanCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 0;

    private static final int FORCE_MATCH = 0;
    private static final int UPG_FORCE_MATCH = 1;

    public VierThrow() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setCustomVar("ForceMatch", FORCE_MATCH, UPG_FORCE_MATCH);

        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(MatchFourPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(p, p, MatchFourPower.POWER_ID));
            addToBot(new DamageAction(m, new DamageInfo(p, damage*2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        } else {
            if (customVar("ForceMatch") == 1){
                addToBot(new DamageAction(m, new DamageInfo(p, damage*2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                for (AbstractPower ap : p.powers)
                    if (ap instanceof MatchBasePower)
                        addToBot(new RemoveSpecificPowerAction(p, p, ap));
            } else {
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
            addToBot(new ApplyPowerAction(p, p, new MatchFourPower(p)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new VierThrow();
    }
}
