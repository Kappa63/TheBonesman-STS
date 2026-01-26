package bonesman.cards.starting;

import bonesman.cards.BaseDomino;
import bonesman.character.BonesmanCharacter;
import bonesman.util.CardStats;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Swans extends BaseDomino {
    public static final String ID = makeID(Swans.class.getSimpleName());

    private static final CardStats info = new CardStats(
            BonesmanCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            0
    );

    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 0;

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 0;

    private static final int FORCE_MATCH = 0;
    private static final int UPG_FORCE_MATCH = 1;

    public Swans() {
        super(ID, info, BLOCK, DAMAGE, 2.0f);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        setCustomVar("ForceMatch", FORCE_MATCH, UPG_FORCE_MATCH);

        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    protected void leftDominoAction(AbstractPlayer p, AbstractMonster m, float mult) {
        addToBot(new GainBlockAction(p, p, MathUtils.floor(block*mult)));
    }

    @Override
    protected void rightDominoAction(AbstractPlayer p, AbstractMonster m, float mult) {
        addToBot(new DamageAction(m, new DamageInfo(p, MathUtils.floor(damage*mult), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}
