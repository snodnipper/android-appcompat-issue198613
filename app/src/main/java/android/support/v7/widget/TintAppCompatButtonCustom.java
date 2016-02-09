package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class TintAppCompatButtonCustom extends AppCompatButton {

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;

    public TintAppCompatButtonCustom(Context context) {
        super(context);
    }

    public TintAppCompatButtonCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TintAppCompatButtonCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        PorterDuff.Mode tintMode = PorterDuff.Mode.SRC_IN;
        Drawable[] ds = getCompoundDrawables();
        tint(ds[LEFT], Color.RED, tintMode);
        tint(ds[TOP], Color.YELLOW, tintMode);
        tint(ds[RIGHT], Color.GREEN, tintMode);
        tint(ds[BOTTOM], Color.BLUE, tintMode);
    }

    private void tint(Drawable d, int color, PorterDuff.Mode tintMode) {
        TintInfo ti = new TintInfo();
        ti.mTintMode = tintMode;
        ti.mTintList = ColorStateList.valueOf(color);
        ti.mHasTintList = true;
        ti.mHasTintMode = true;

        TintManager.tintDrawable(d, ti, new int[]{0});
    }
}
