package bonesman.cards.common;

import bonesman.cards.BaseDomino;
import bonesman.character.BonesmanCharacter;
import bonesman.util.CardStats;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Zweip extends BaseDomino {
    public static final String ID = makeID(Zweip.class.getSimpleName());

    private static final CardStats info = new CardStats(
            BonesmanCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 0;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 0;

    public Zweip() {
        super(ID, info, 0, DAMAGE, 2.0f);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true, false);
        setCostUpgrade(1);

        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    protected void leftDominoAction(AbstractPlayer p, AbstractMonster m, float mult) {
        addToBot(new DrawCardAction(MathUtils.floor(magicNumber*mult)));
    }

    @Override
    protected void rightDominoAction(AbstractPlayer p, AbstractMonster m, float mult) {
        addToBot(new DamageAction(m, new DamageInfo(p, MathUtils.floor(damage*mult), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}
