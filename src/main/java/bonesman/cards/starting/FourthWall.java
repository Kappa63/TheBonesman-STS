package bonesman.cards.starting;

import bonesman.cards.BaseDomino;
import bonesman.character.BonesmanCharacter;
import bonesman.util.CardStats;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FourthWall extends BaseDomino {
    public static final String ID = makeID(FourthWall.class.getSimpleName());

    private static final CardStats info = new CardStats(
            BonesmanCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 0;

    private static final int FORCE_MATCH = 0;
    private static final int UPG_FORCE_MATCH = 1;

    public FourthWall() {
        super(ID, info, BLOCK, 0, 2.0f);
        setBlock(BLOCK, UPG_BLOCK);
        setCustomVar("ForceMatch", FORCE_MATCH, UPG_FORCE_MATCH);

        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    protected void leftDominoAction(AbstractPlayer p, AbstractMonster m, float mult) {
        addToBot(new GainBlockAction(p, p, MathUtils.floor(block*mult)));
    }

    @Override
    protected void rightDominoAction(AbstractPlayer p, AbstractMonster m, float mult) {}
}
